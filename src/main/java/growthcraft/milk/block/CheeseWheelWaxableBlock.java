package growthcraft.milk.block;

import growthcraft.milk.block.entity.CheeseWheelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class CheeseWheelWaxableBlock extends BaseCheeseWheel {
    public CheeseWheelWaxableBlock(Cheese variant) {
        super(variant);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (level.isClientSide()) return InteractionResult.CONSUME;

        CheeseWheelBlockEntity entity = (CheeseWheelBlockEntity) level.getBlockEntity(blockPos);
        assert entity != null;

        ItemStack handItem = player.getItemInHand(interactionHand);
        // handle waxing cheese
        if (handItem.is(getVariant().getWax()) && handItem.getCount() >= entity.getWheelCount()) {
            CompoundTag nbt = entity.serializeNBT();
            BlockState thisBlock = level.getBlockState(blockPos);
            BlockState waxedBlock = getWaxedBlock().withPropertiesOf(thisBlock);
            level.setBlockAndUpdate(blockPos, waxedBlock);
            BlockEntity newEntity = level.getBlockEntity(blockPos);
            newEntity.deserializeNBT(nbt);
            if (!player.isCreative()) player.getItemInHand(interactionHand).shrink(entity.getWheelCount());
            return InteractionResult.SUCCESS;
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    private Block getWaxedBlock() {
        return getVariant().getWaxed();
    }
}
