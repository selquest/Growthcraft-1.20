package growthcraft.milk.datagen;

import growthcraft.milk.datagen.loot.GrowthcraftMilkLootTables;
import growthcraft.milk.init.GrowthcraftMilkItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

public class DataGeneration {
    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(
                event.includeServer(),
                new GrowthcraftMilkLootTables(output)
        );


        generator.addProvider(event.includeClient(), new GrowthcraftMilkBlockStates(output, helper));

    }

    //@SubscribeEvent
    public static void modelBake(ModelEvent.ModifyBakingResult event) {
        GrowthcraftMilkItems.addItemModelProperties();
    }
}
