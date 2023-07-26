package growthcraft.lib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

public class GrowthcraftBlock extends Block {

    public GrowthcraftBlock() {
        this(getInitProperties(Blocks.OAK_LOG, SoundType.WOOD));
    }

    public GrowthcraftBlock(Block block) {
        this(getInitProperties(block, SoundType.WOOD));
    }

    public GrowthcraftBlock(Block block, SoundType soundType) {
        this(getInitProperties(block, soundType));
    }

    public GrowthcraftBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Block block, SoundType soundType) {
        Properties properties = Properties.copy(block);
        properties.sound(SoundType.WOOD);
        return properties;
    }
}
