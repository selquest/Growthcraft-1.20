package growthcraft.lib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class GrowthcraftStairsBlock extends StairBlock {

    public GrowthcraftStairsBlock() {
        this(
                Blocks.OAK_STAIRS.defaultBlockState(),
                getInitProperties(Blocks.OAK_STAIRS)
        );
    }

    @SuppressWarnings("deprecation")
    public GrowthcraftStairsBlock(BlockState state, Properties properties) {
        super(state, properties);
    }

    public GrowthcraftStairsBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }

    private static Properties getInitProperties(Block material) {
        Properties properties = Properties.copy(material);
        properties.mapColor(material.defaultMapColor());
        properties.strength(1.0F);
        properties.sound(SoundType.STONE);
        return properties;
    }
}
