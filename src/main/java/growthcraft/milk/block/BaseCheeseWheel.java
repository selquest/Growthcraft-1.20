package growthcraft.milk.block;

import growthcraft.milk.block.entity.CheeseWheelBlockEntity;
import growthcraft.milk.datagen.Cheese;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class BaseCheeseWheel extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty AGED = BooleanProperty.create("aged");
    public static final IntegerProperty SLICE_COUNT_TOP = IntegerProperty.create("slicestop", 0, 4);
    public static final IntegerProperty SLICE_COUNT_BOTTOM = IntegerProperty.create("slicesbottom", 0, 4);

    public static final VoxelShape BOUNDING_BOX = Block.box(
            1.00F, 0.0F, 1.0F,
            15.0F, 16.0F, 15.0F
    );

    public static final VoxelShape BOUNDING_BOX_HALF = Block.box(
            1.00F, 0.0F, 1.0F,
            15.0F, 8.0F, 15.0F
    );

    private final int color;
    private final Cheese variant;

    public BaseCheeseWheel(Color color) {
        super(getInitProperties());
        this.color = color.getRGB();
        this.variant = null;
    }

    public BaseCheeseWheel(Cheese variant) {
        super(getInitProperties());
        this.color = 0;
        this.variant = variant;
    }

    public static Properties getInitProperties() {
        Properties properties = Properties.copy(Blocks.CAKE);
        properties.noOcclusion();
        return properties;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return GrowthcraftMilkBlockEntities.CHEESE_WHEEL_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(
                blockEntityType,
                GrowthcraftMilkBlockEntities.CHEESE_WHEEL_BLOCK_ENTITY.get(),
                (worldLevel, pos, state, blockEntity) -> (blockEntity).tick()
        );    }

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return blockState.getValue(SLICE_COUNT_TOP) > 0
                ? BOUNDING_BOX
                : BOUNDING_BOX_HALF;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(FACING, AGED, SLICE_COUNT_BOTTOM, SLICE_COUNT_TOP));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(SLICE_COUNT_BOTTOM, 4).setValue(SLICE_COUNT_TOP, 0).setValue(AGED, false);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_60584_) {
        return PushReaction.DESTROY;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
                .setValue(SLICE_COUNT_BOTTOM, state.getValue(SLICE_COUNT_BOTTOM))
                .setValue(SLICE_COUNT_TOP, state.getValue(SLICE_COUNT_TOP))
                .setValue(AGED, state.getValue(AGED));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)))
                .setValue(SLICE_COUNT_BOTTOM, state.getValue(SLICE_COUNT_BOTTOM))
                .setValue(SLICE_COUNT_TOP, state.getValue(SLICE_COUNT_TOP))
                .setValue(AGED, state.getValue(AGED));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (level.isClientSide()) {
            return InteractionResult.CONSUME;
        }

        // handle stacking wheels
        if (player.getItemInHand(interactionHand).getItem() == this.asItem()) {
            if (canAddWheel(blockState)) {
                BlockState newBlock = addSlice(blockState, 4);
                if (!player.isCreative()) player.getItemInHand(interactionHand).shrink(1);
                level.setBlockAndUpdate(blockPos, newBlock);
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }

        // handle picking up wheels
        } else if (player.isCrouching() && canRemoveWheel(blockState)) {
            BlockState newBlock = takeSlice(blockState, 4);
            player.getInventory().add(new ItemStack(this.asItem()));
            level.setBlockAndUpdate(blockPos, newBlock);
            if(getSliceCount(newBlock) == 0) level.destroyBlock(blockPos, false);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public static void updateWheel(Level level, BlockState blockState, BlockPos blockPos) {
        level.setBlockAndUpdate(blockPos, blockState);
    };

    public static int getSliceCount(BlockState block) {
        return block.getValue(SLICE_COUNT_BOTTOM) + block.getValue(SLICE_COUNT_TOP);
    }

    public boolean canAddWheel(BlockState block) {
        return getSliceCount(block) <= 4;
    }

    public boolean canRemoveWheel(BlockState block) {
        return getSliceCount(block ) >= 4;
    }

    public int getWheelCount(BlockState block) {
        return getSliceCount(block) / 4;
    }

    public BlockState takeSlice(BlockState block, int count) {
        int sliceCountTop = block.getValue(SLICE_COUNT_TOP);
        int sliceCountBottom = block.getValue(SLICE_COUNT_BOTTOM);
        if (sliceCountTop >= count) {
            sliceCountTop -= count;
        } else if (sliceCountBottom >= count - sliceCountTop) {
            sliceCountBottom -= count - sliceCountTop;
            sliceCountTop = 0;
        }
        return block.setValue(SLICE_COUNT_BOTTOM, sliceCountBottom)
                .setValue(SLICE_COUNT_TOP, sliceCountTop);
    }

    public BlockState addSlice(BlockState block, int count) {
        int newTotal = block.getValue(SLICE_COUNT_TOP) + block.getValue(SLICE_COUNT_BOTTOM) + count;

        if (newTotal > 4) {
            return block.setValue(SLICE_COUNT_BOTTOM, 4)
                    .setValue(SLICE_COUNT_TOP, newTotal - 4);
        } else {
            return block.setValue(SLICE_COUNT_BOTTOM, newTotal)
                    .setValue(SLICE_COUNT_TOP, 0);
        }
    }

    @Deprecated
    public int getColor() {
        return this.color;
    }

    @Deprecated
    public int getColor(int i) {
        return i == 0 ? this.color : 0xFFFFFF;
    }

    public Cheese getVariant() {
        return variant;
    }
}
