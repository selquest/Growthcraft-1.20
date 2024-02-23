package growthcraft.bamboo.datagen.providers;

import java.util.Map;
import java.util.stream.Collectors;

import growthcraft.bamboo.init.GrowthcraftBambooBlocks;
import growthcraft.bamboo.shared.Reference;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftBambooLootTables extends VanillaBlockLoot{
	
	@Override
	protected void generate() {
		getKnownBlocks().forEach(entry -> {
			if(entry.equals(GrowthcraftBambooBlocks.BAMBOO_PLANK_DOOR)) {
				createDoorTable(entry);
			}
			else if(entry.equals(GrowthcraftBambooBlocks.BAMBOO_PLANK_SLAB)){
				createSlabItemTable(entry);
			}
			else {
				dropSelf(entry);
			}
		});	
		
	}
	
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(Reference.MODID))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
