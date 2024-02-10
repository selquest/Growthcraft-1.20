package growthcraft.rice.init;

import growthcraft.core.utils.CompostableUtils;
import growthcraft.lib.item.GrowthcraftFoodItem;
import growthcraft.lib.item.GrowthcraftItem;
import growthcraft.rice.item.CultivatorItem;
import growthcraft.rice.item.RiceSeedItem;
import growthcraft.rice.shared.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class GrowthcraftRiceItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<CultivatorItem> CULTIVATOR = ITEMS.register(
      Reference.UnlocalizedName.CULTIVATOR,
      CultivatorItem::new
    );

    public static final RegistryObject<GrowthcraftItem> KNIFE = ITEMS.register(
            Reference.UnlocalizedName.KNIFE,
            GrowthcraftItem::new
    );

    public static final RegistryObject<RiceSeedItem> RICE = ITEMS.register(
            Reference.UnlocalizedName.RICE,
            RiceSeedItem::new
    );

    public static final RegistryObject<GrowthcraftFoodItem> RICE_COOKED = ITEMS.register(
            Reference.UnlocalizedName.RICE_COOKED,
            GrowthcraftFoodItem::new
    );

    public static final RegistryObject<GrowthcraftItem> RICE_STALK = ITEMS.register(
            Reference.UnlocalizedName.RICE_STALK,
            GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftFoodItem> SUSHI_ROLL = ITEMS.register(
            Reference.UnlocalizedName.SUSHI_ROLL,
            GrowthcraftFoodItem::new
    );

    public static final RegistryObject<GrowthcraftItem> YEAST_SEISHU = ITEMS.register(
            Reference.UnlocalizedName.YEAST_SEISHU, GrowthcraftItem::new
    );

    public static void registerCompostables() {
        // Add rice as a compostables
        CompostableUtils.registerCompostable(
                GrowthcraftRiceItems.RICE.get(), CompostableUtils.COMPOSTABLE.NORMAL);
        CompostableUtils.registerCompostable(
                GrowthcraftRiceItems.RICE_COOKED.get(), CompostableUtils.COMPOSTABLE.HIGH);
        CompostableUtils.registerCompostable(
                GrowthcraftRiceItems.RICE_STALK.get(), CompostableUtils.COMPOSTABLE.LOW);
        CompostableUtils.registerCompostable(
                GrowthcraftRiceItems.SUSHI_ROLL.get(), CompostableUtils.COMPOSTABLE.HIGHEST);
        CompostableUtils.registerCompostable(
                GrowthcraftRiceItems.YEAST_SEISHU.get(), CompostableUtils.COMPOSTABLE.HIGHEST);
    }

    public static boolean excludeItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeItems = new ArrayList<>();
        //excludeItems.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);
        return excludeItems.contains(registryName.toString());
    }

    private GrowthcraftRiceItems() {
        /* Prevent default public constructor */
    }
}
