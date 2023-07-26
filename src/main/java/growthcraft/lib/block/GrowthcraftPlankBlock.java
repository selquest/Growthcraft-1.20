package growthcraft.lib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

public class GrowthcraftPlankBlock extends Block {
    public GrowthcraftPlankBlock() {
        this(getInitProperties(Blocks.OAK_PLANKS));
    }

    public GrowthcraftPlankBlock(Block material) {
        this(getInitProperties(material));
    }

    public GrowthcraftPlankBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Block material) {
        Properties properties = Properties.copy(material);
        properties.strength(2.0F, 3.0F);
        properties.sound(SoundType.WOOD);
        return properties;
    }
}
