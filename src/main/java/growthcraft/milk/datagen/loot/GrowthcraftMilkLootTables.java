package growthcraft.milk.datagen.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;

public class GrowthcraftMilkLootTables extends LootTableProvider {
    public GrowthcraftMilkLootTables(PackOutput output) {
        super(output, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(CheeseWheelLoot::new, LootContextParamSets.BLOCK)
        ));
    }
}
