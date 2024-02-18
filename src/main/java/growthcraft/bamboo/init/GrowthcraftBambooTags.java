package growthcraft.bamboo.init;

import growthcraft.bamboo.shared.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class GrowthcraftBambooTags {

    public static void init() {
        Blocks.init();
        Items.init();
        Fluids.init();
        EntityTypes.init();
    }

    public static class Blocks {

        public static void init() {
            // Do nothing, simply instantiate static variables
        }

    	public static final TagKey<Block> BAMBOO_WOOD_LOGS = tag(Reference.UnlocalizedName.TAG_BAMBOO_WOOD_LOGS);
    	public static final TagKey<Block> BAMBOO_WOOD = tag(Reference.UnlocalizedName.TAG_APPLBAMBOO_WOOD);

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Reference.MODID, name));
        }
    }

    public static class Items {

        public static void init() {
            // Do nothing, simply instantiate static variables
        }
        
    	public static final TagKey<Item> BAMBOO_WOOD_LOGS = tag(Reference.UnlocalizedName.TAG_BAMBOO_WOOD_LOGS);
    	public static final TagKey<Item> BAMBOO_WOOD = tag(Reference.UnlocalizedName.TAG_APPLBAMBOO_WOOD);

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Reference.MODID, name));
        }
    }

    public static class Fluids {

        private static void init() {
            // Do nothing, simply instantiate static variables
        }

        private static TagKey<Fluid> tag(String name) {
            return FluidTags.create(new ResourceLocation(Reference.MODID, name));
        }
    }

    public static class EntityTypes {

        private static void init() {
            // Do nothing, simply instantiate static variables
        }

        //private static TagKey<EntityType<?>> tag(String name) {
        //    return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(Reference.MODID, name));
        //}
    }
}
