package growthcraft.lib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class GrowthcraftTrapDoorBlock extends TrapDoorBlock {
    public GrowthcraftTrapDoorBlock() {
        this(getInitProperties(Blocks.OAK_TRAPDOOR));
    }

    public GrowthcraftTrapDoorBlock(Properties properties) {
        super(properties, BlockSetType.OAK);
    }

    private static Properties getInitProperties(Block material) {
        Properties properties = Properties.copy(material);
        properties.mapColor(material.defaultMapColor());
        properties.strength(2.0F, 3.0F);
        properties.sound(SoundType.WOOD);
        properties.noOcclusion();
        return properties;
    }
}
