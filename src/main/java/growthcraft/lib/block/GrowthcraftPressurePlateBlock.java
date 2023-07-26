package growthcraft.lib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class GrowthcraftPressurePlateBlock extends PressurePlateBlock {
    public GrowthcraftPressurePlateBlock() {
        this(Sensitivity.EVERYTHING, getInitProperties(Blocks.OAK_PRESSURE_PLATE));
    }

    public GrowthcraftPressurePlateBlock(Sensitivity sensitivity, Properties properties) {
        super(sensitivity, properties, BlockSetType.OAK);
    }

    private static Properties getInitProperties(Block material) {
        Properties properties = Properties.copy(material);
        properties.mapColor(material.defaultMapColor());
        properties.strength(1.5F);
        properties.sound(SoundType.WOOD);
        return properties;
    }
}
