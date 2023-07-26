package growthcraft.lib.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;

public class GrowthcraftLeavesBlock extends LeavesBlock {
    public GrowthcraftLeavesBlock() {
        this(getInitProperties());
    }

    public GrowthcraftLeavesBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.copy(Blocks.OAK_LEAVES);
        properties.mapColor(Blocks.OAK_LEAVES.defaultMapColor());
        properties.randomTicks();
        properties.strength(0.2F, 0.2F);
        properties.sound(SoundType.CROP);
        properties.noOcclusion();
        return properties;
    }
}
