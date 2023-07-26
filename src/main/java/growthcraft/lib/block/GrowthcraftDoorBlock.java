package growthcraft.lib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class GrowthcraftDoorBlock extends DoorBlock {
    public GrowthcraftDoorBlock() {
        this(getInitProperties(Blocks.OAK_DOOR));
    }

    public GrowthcraftDoorBlock(Properties properties) {
        super(properties, BlockSetType.OAK);
    }

    private static Properties getInitProperties(Block material) {
        Properties properties = Properties.copy(material);
        properties.sound(SoundType.WOOD);
        properties.strength(3.0F);
        properties.noOcclusion();
        return properties;
    }
}
