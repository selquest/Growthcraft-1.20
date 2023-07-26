package growthcraft.lib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class GrowthcraftButtonBlock extends ButtonBlock {

    public GrowthcraftButtonBlock() {
        super(getInitProperties(Blocks.OAK_BUTTON), BlockSetType.OAK, 30, true);
    }

    private static Properties getInitProperties(Block material) {
        Properties properties = Properties.copy(material);
        properties.strength(0.5F);
        properties.noCollission();
        properties.sound(SoundType.WOOD);
        return properties;
    }
}
