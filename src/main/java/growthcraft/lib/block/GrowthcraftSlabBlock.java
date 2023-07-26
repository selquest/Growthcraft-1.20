package growthcraft.lib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;

public class GrowthcraftSlabBlock extends SlabBlock {
    public GrowthcraftSlabBlock() {
        this(getInitProperties(Blocks.OAK_SLAB));
    }

    public GrowthcraftSlabBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Block material) {
        Properties properties = Properties.copy(material);
        properties.mapColor(material.defaultMapColor());
        properties.strength(2.0F, 3.0F);
        properties.noOcclusion();
        return properties;
    }
}
