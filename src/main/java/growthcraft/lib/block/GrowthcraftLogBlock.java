package growthcraft.lib.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class GrowthcraftLogBlock extends RotatedPillarBlock {
    public GrowthcraftLogBlock() {
        this(getInitProperties(Blocks.OAK_LOG));
    }

    public GrowthcraftLogBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Block material) {
        Properties properties = Properties.copy(material);
        properties.strength(2.0F);
        properties.sound(SoundType.WOOD);
        return properties;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

}
