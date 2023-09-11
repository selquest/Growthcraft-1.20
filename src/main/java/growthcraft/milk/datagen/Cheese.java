package growthcraft.milk.datagen;


import growthcraft.apiary.init.GrowthcraftApiaryItems;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.init.GrowthcraftMilkItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

public enum Cheese {
    APPENZELLER(
            GrowthcraftMilkItems.CHEESE_APPENZELLER_SLICE,
            GrowthcraftMilkBlocks.APPENZELLER_CHEESE,
            GrowthcraftMilkBlocks.AGED_APPENZELLER_CHEESE
    ),
    ASIAGO(
            GrowthcraftMilkItems.CHEESE_ASIAGO_SLICE,
            GrowthcraftMilkBlocks.ASIAGO_CHEESE,
            GrowthcraftMilkBlocks.AGED_ASIAGO_CHEESE
    ),
    CASU_MARZU(
            GrowthcraftMilkItems.CHEESE_CASU_MARZU_SLICE,
            GrowthcraftMilkBlocks.CASU_MARZU_CHEESE,
            GrowthcraftMilkBlocks.AGED_CASU_MARZU_CHEESE
    ),
    CHEDDAR(
            GrowthcraftMilkItems.CHEESE_CHEDDAR_SLICE,
            GrowthcraftMilkBlocks.CHEDDAR_CHEESE,
            GrowthcraftMilkBlocks.WAXED_CHEDDAR_CHEESE,
            GrowthcraftMilkBlocks.AGED_CHEDDAR_CHEESE,
            GrowthcraftApiaryItems.BEES_WAX_RED
    ),
    EMMENTALER(
            GrowthcraftMilkItems.CHEESE_EMMENTALER_SLICE,
            GrowthcraftMilkBlocks.EMMENTALER_CHEESE,
            GrowthcraftMilkBlocks.AGED_EMMENTALER_CHEESE
    ),
    GORGONZOLA(
            GrowthcraftMilkItems.CHEESE_GORGONZOLA_SLICE,
            GrowthcraftMilkBlocks.GORGONZOLA_CHEESE,
            GrowthcraftMilkBlocks.AGED_GORGONZOLA_CHEESE
    ),
    GOUDA(
            GrowthcraftMilkItems.CHEESE_GOUDA_SLICE,
            GrowthcraftMilkBlocks.GOUDA_CHEESE,
            GrowthcraftMilkBlocks.WAXED_GOUDA_CHEESE,
            GrowthcraftMilkBlocks.AGED_GOUDA_CHEESE,
            GrowthcraftApiaryItems.BEES_WAX
    ),
    MONTEREY(
            GrowthcraftMilkItems.CHEESE_MONTEREY_SLICE,
            GrowthcraftMilkBlocks.MONTEREY_CHEESE,
            GrowthcraftMilkBlocks.WAXED_MONTEREY_CHEESE,
            GrowthcraftMilkBlocks.AGED_MONTEREY_CHEESE,
            GrowthcraftApiaryItems.BEES_WAX_BLACK
    ),
    PARMESAN(
            GrowthcraftMilkItems.CHEESE_PARMESAN_SLICE,
            GrowthcraftMilkBlocks.PARMESAN_CHEESE,
            GrowthcraftMilkBlocks.AGED_PARMESAN_CHEESE
    ),
    PROVOLONE(
            GrowthcraftMilkItems.CHEESE_PROVOLONE_SLICE,
            GrowthcraftMilkBlocks.PROVOLONE_CHEESE,
            GrowthcraftMilkBlocks.WAXED_PROVOLONE_CHEESE,
            GrowthcraftMilkBlocks.AGED_PROVOLONE_CHEESE,
            GrowthcraftApiaryItems.BEES_WAX_WHITE
    );

    Cheese() {}

    Cheese(
            RegistryObject<? extends Item> slice,
            RegistryObject<? extends Block> unprocessed,
            RegistryObject<? extends Block> waxed,
            RegistryObject<? extends Block> aged,
            RegistryObject<? extends Item> wax) {
        this.process = Processing.WAXING;
        this.slice = slice;
        this.wax = wax;
        this.unprocessed = unprocessed;
        this.waxed = waxed;
        this.aged = aged;
    }

    Cheese(
            RegistryObject<? extends Item> slice,
            RegistryObject<? extends Block> unprocessed,
            RegistryObject<? extends Block> aged) {
        this.process = Processing.AGING;
        this.slice = slice;
        this.unprocessed = unprocessed;
        this.aged = aged;
    }

    private Processing process;
    private RegistryObject<? extends Item> slice ;
    private RegistryObject<? extends Item> wax;

    private RegistryObject<? extends Block> unprocessed;
    private RegistryObject<? extends Block> aged;

    private RegistryObject<? extends Block> waxed;


    public boolean isAgeable() {
        return process == Processing.AGING;
    }

    public boolean isWaxable() {
        return process == Processing.WAXING;
    }

    public Item getWax() {
        return wax.get().asItem();
    }

    public ItemStack getSlices(int count) {
        return new ItemStack(slice.get(), count);
    }

    @Deprecated
    public Block getProcessed() {
        return null;
    }

    public Block getAged() {
        return aged.get();
    }

    public Block getWaxed() {
        return waxed.get();
    }

    private enum Processing {
        WAXING,
        AGING
    }
}
