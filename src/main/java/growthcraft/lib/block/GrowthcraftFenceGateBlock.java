package growthcraft.lib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class GrowthcraftFenceGateBlock extends FenceGateBlock {
    public GrowthcraftFenceGateBlock() {
        this(getInitProperties(Blocks.OAK_FENCE_GATE));
    }

    public GrowthcraftFenceGateBlock(Properties properties) {
        super(properties, WoodType.OAK);
    }

    private static Properties getInitProperties(Block material) {
        Properties properties = Properties.copy(material);
        properties.mapColor(material.defaultMapColor());
        properties.sound(SoundType.WOOD);
        properties.strength(2.0F, 3.0F);
        return properties;
    }
}
