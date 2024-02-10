package growthcraft.apples.init;

import growthcraft.apples.item.AppleSeedsItem;
import growthcraft.apples.shared.Reference;
import growthcraft.core.utils.CompostableUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class GrowthcraftApplesItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<Item> APPLE_SEEDS = ITEMS.register(
            Reference.UnlocalizedName.APPLE_SEEDS,
            AppleSeedsItem::new
    );

    private GrowthcraftApplesItems() {
        /* Prevent default public constructor */
    }

    public static void registerCompostables() {
        CompostableUtils.registerCompostable(GrowthcraftApplesItems.APPLE_SEEDS.get(), CompostableUtils.COMPOSTABLE.LOW);
    }

    public static boolean excludeItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeItems = new ArrayList<>();
        //excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);
        return excludeItems.contains(registryName.toString());
    }
}
