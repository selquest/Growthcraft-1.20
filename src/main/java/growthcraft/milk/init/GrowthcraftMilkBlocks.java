package growthcraft.milk.init;

import growthcraft.lib.utils.CheeseUtils;
import growthcraft.milk.block.*;
import growthcraft.milk.shared.Reference;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class GrowthcraftMilkBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    public static final RegistryObject<Block> CHEESE_PRESS = registerBlock(
            Reference.UnlocalizedName.CHEESE_PRESS,
            CheesePressBlock::new
    );

    public static final RegistryObject<Block> CHURN = registerBlock(
        Reference.UnlocalizedName.CHURN,
            ChurnBlock::new
    );

    public static final RegistryObject<Block> MIXING_VAT = registerBlock(
            Reference.UnlocalizedName.MIXING_VAT,
            MixingVatBlock::new
    );

    public static final RegistryObject<Block> PANCHEON = registerBlock(
            Reference.UnlocalizedName.PANCHEON,
            PancheonBlock::new
    );

    public static final RegistryObject<Block> THISTLE_CROP = registerBlock(
            Reference.UnlocalizedName.THISTLE_CROP,
            ThistleCropBlock::new,
            true
    );

    //////////////////////////////////
    ////// Unprocessed Cheeses ///////
    //////////////////////////////////
    public static final RegistryObject<Block> APPENZELLER_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.APPENZELLER).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.APPENZELLER_CHEESE.getColor())
    );

    public static final RegistryObject<Block> ASIAGO_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.ASIAGO).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.ASIAGO_CHEESE.getColor())
    );

    public static final RegistryObject<Block> CASU_MARZU_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CASU_MARZU).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.CASU_MARZU_CHEESE.getColor())
    );

    public static final RegistryObject<Block> CHEDDAR_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CHEDDAR).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.CHEDDAR_CHEESE.getColor())
    );

    public static final RegistryObject<Block> EMMENTALER_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.EMMENTALER).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.EMMENTALER_CHEESE.getColor())
    );

    public static final RegistryObject<Block> GORGONZOLA_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GORGONZOLA).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.GORGONZOLA_CHEESE.getColor())
    );

    public static final RegistryObject<Block> GOUDA_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GOUDA).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.GOUDA_CHEESE.getColor())
    );

    public static final RegistryObject<Block> MONTEREY_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.MONTEREY).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.MONTEREY_CHEESE.getColor())
    );

    public static final RegistryObject<Block> PARMESAN_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PARMESAN).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.PARMESAN_CHEESE.getColor())
    );

    public static final RegistryObject<Block> PROVOLONE_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PROVOLONE).get(CheeseUtils.CHEESE),
            () -> new CheeseWheelBlock(Reference.ItemColor.PROVOLONE_CHEESE.getColor())
    );

    /////////////////////////////////////////////////////
    ////////////////// AGED CHEESES /////////////////////
    /////////////////////////////////////////////////////

    public static final RegistryObject<Block> AGED_APPENZELLER_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.APPENZELLER).get(CheeseUtils.AGED),
            () -> new CheeseWheelBlock(Reference.ItemColor.APPENZELLER_CHEESE.getColor())
    );

    public static final RegistryObject<Block> AGED_ASIAGO_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.ASIAGO).get(CheeseUtils.AGED),
            () -> new CheeseWheelBlock(Reference.ItemColor.ASIAGO_CHEESE.getColor())
    );

    public static final RegistryObject<Block> AGED_CASU_MARZU_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CASU_MARZU).get(CheeseUtils.AGED),
            () -> new CheeseWheelBlock(Reference.ItemColor.CASU_MARZU_CHEESE.getColor())
    );

    public static final RegistryObject<Block> WAXED_CHEDDAR_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CHEDDAR).get(CheeseUtils.WAXED),
            () -> new CheeseWheelBlock(Reference.ItemColor.CHEDDAR_CHEESE.getColor())
    );

    public static final RegistryObject<Block> AGED_EMMENTALER_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.EMMENTALER).get(CheeseUtils.AGED),
            () -> new CheeseWheelBlock(Reference.ItemColor.EMMENTALER_CHEESE.getColor())
    );

    public static final RegistryObject<Block> AGED_GORGONZOLA_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GORGONZOLA).get(CheeseUtils.AGED),
            () -> new CheeseWheelBlock(Reference.ItemColor.GORGONZOLA_CHEESE.getColor())
    );

    public static final RegistryObject<Block> WAXED_GOUDA_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GOUDA).get(CheeseUtils.WAXED),
            () -> new CheeseWheelBlock(Reference.ItemColor.GOUDA_CHEESE.getColor())
    );

    public static final RegistryObject<Block> WAXED_MONTEREY_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.MONTEREY).get(CheeseUtils.WAXED),
            () -> new CheeseWheelBlock(Reference.ItemColor.MONTEREY_CHEESE.getColor())
    );

    public static final RegistryObject<Block> AGED_PARMESAN_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PARMESAN).get(CheeseUtils.AGED),
            () -> new CheeseWheelBlock(Reference.ItemColor.PARMESAN_CHEESE.getColor())
    );

    public static final RegistryObject<Block> WAXED_PROVOLONE_CHEESE = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PROVOLONE).get(CheeseUtils.WAXED),
            () -> new CheeseWheelBlock(Reference.ItemColor.PROVOLONE_CHEESE.getColor())
    );

    //////////////////////////////////
    /////////// Cheese Curds /////////
    //////////////////////////////////


    public static final RegistryObject<Block> APPENZELLER_CHEESE_CURDS = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.APPENZELLER).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.APPENZELLER_CHEESE.getColor())
    );

    public static final RegistryObject<Block> ASIAGO_CHEESE_CURDS = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.ASIAGO).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.ASIAGO_CHEESE.getColor())
    );

    public static final RegistryObject<Block> CASU_MARZU_CHEESE_CURDS = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CASU_MARZU).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.CASU_MARZU_CHEESE.getColor())
    );

    public static final RegistryObject<Block> CHEDDAR_CHEESE_CURDS = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CHEDDAR).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.CHEDDAR_CHEESE.getColor())
    );

    public static final RegistryObject<Block> EMMENTALER_CHEESE_CURDS = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.EMMENTALER).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.EMMENTALER_CHEESE.getColor())
    );

    public static final RegistryObject<Block> GORGONZOLA_CHEESE_CURDS = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GORGONZOLA).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.GORGONZOLA_CHEESE.getColor())
    );

    public static final RegistryObject<Block> GOUDA_CHEESE_CURDS = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GOUDA).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.GOUDA_CHEESE.getColor())
    );

    public static final RegistryObject<Block> MONTEREY_CHEESE_CURDS = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.MONTEREY).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.MONTEREY_CHEESE.getColor())
    );

    public static final RegistryObject<Block> PARMESAN_CHEESE_CURDS = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PARMESAN).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.PARMESAN_CHEESE.getColor())
    );

    public static final RegistryObject<Block> RICOTTA_CHEESE_CURDS = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.RICOTTA).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.RICOTTA_CHEESE.getColor())
    );

    public static final RegistryObject<Block> PROVOLONE_CHEESE_CURDS = registerBlock(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PROVOLONE).get(CheeseUtils.CURDS),
            () -> new CheeseCurdBlock(Reference.ItemColor.PROVOLONE_CHEESE.getColor())
    );

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        return registerBlock(name, block, false);
    }

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block, boolean excludeBlockItemRegistry) {
        RegistryObject<Block> registryObject = BLOCKS.register(name, block);
        if (!excludeBlockItemRegistry) {
            registerBlockItem(name, registryObject);
        }
        return registryObject;
    }

    private static void registerBlockItem(String name, RegistryObject<Block> blockRegistryObject) {
        GrowthcraftMilkItems.ITEMS.register(
                name,
                () -> new BlockItem(blockRegistryObject.get(), getDefaultItemProperties())
        );
    }

    private static Item.Properties getDefaultItemProperties() {
        Item.Properties properties = new Item.Properties();
        return properties;
    }

    private GrowthcraftMilkBlocks() {
        /* Disable default public constructor */
    }


}
