package growthcraft.apiary.datagen.providers;

import java.util.function.Consumer;

import growthcraft.apiary.init.GrowthcraftApiaryBlocks;
import growthcraft.apiary.init.GrowthcraftApiaryItems;
import growthcraft.apiary.init.GrowthcraftApiaryTags;
import growthcraft.apiary.shared.Reference;
import growthcraft.lib.item.GrowthcraftItem;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApiaryRecipes extends RecipeProvider{

	public GrowthcraftApiaryRecipes(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GrowthcraftApiaryBlocks.BEE_BOX_ACACIA.get())
		.pattern("SPS")
		.pattern("PBP")
		.pattern("SPS")
		.define('B', Items.ACACIA_SLAB)
		.define('P', Items.ACACIA_PLANKS)
		.define('S', Tags.Items.RODS_WOODEN)
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ACACIA_PLANKS))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GrowthcraftApiaryBlocks.BEE_BOX_BIRCH.get())
		.pattern("SPS")
		.pattern("PBP")
		.pattern("SPS")
		.define('B', Items.BIRCH_SLAB)
		.define('P', Items.BIRCH_PLANKS)
		.define('S', Tags.Items.RODS_WOODEN)
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BIRCH_PLANKS))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GrowthcraftApiaryBlocks.BEE_BOX_CRIMSON.get())
		.pattern("SPS")
		.pattern("PBP")
		.pattern("SPS")
		.define('B', Items.CRIMSON_SLAB)
		.define('P', Items.CRIMSON_PLANKS)
		.define('S', Tags.Items.RODS_WOODEN)
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRIMSON_PLANKS))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GrowthcraftApiaryBlocks.BEE_BOX_DARK_OAK.get())
		.pattern("SPS")
		.pattern("PBP")
		.pattern("SPS")
		.define('B', Items.DARK_OAK_SLAB)
		.define('P', Items.DARK_OAK_PLANKS)
		.define('S', Tags.Items.RODS_WOODEN)
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DARK_OAK_PLANKS))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GrowthcraftApiaryBlocks.BEE_BOX_JUNGLE.get())
		.pattern("SPS")
		.pattern("PBP")
		.pattern("SPS")
		.define('B', Items.JUNGLE_SLAB)
		.define('P', Items.JUNGLE_PLANKS)
		.define('S', Tags.Items.RODS_WOODEN)
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUNGLE_PLANKS))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GrowthcraftApiaryBlocks.BEE_BOX_OAK.get())
		.pattern("SPS")
		.pattern("PBP")
		.pattern("SPS")
		.define('B', Items.ACACIA_SLAB)
		.define('P', Items.ACACIA_PLANKS)
		.define('S', Tags.Items.RODS_WOODEN)
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ACACIA_PLANKS))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GrowthcraftApiaryBlocks.BEE_BOX_SPRUCE.get())
		.pattern("SPS")
		.pattern("PBP")
		.pattern("SPS")
		.define('B', Items.SPRUCE_SLAB)
		.define('P', Items.SPRUCE_PLANKS)
		.define('S', Tags.Items.RODS_WOODEN)
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPRUCE_PLANKS))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GrowthcraftApiaryBlocks.BEE_BOX_WARPED.get())
		.pattern("SPS")
		.pattern("PBP")
		.pattern("SPS")
		.define('B', Items.WARPED_SLAB)
		.define('P', Items.WARPED_PLANKS)
		.define('S', Tags.Items.RODS_WOODEN)
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WARPED_PLANKS))
		.save(consumer);
		
		GrowthcraftApiaryItems.ITEMS.getEntries().forEach(item -> {
			if(item.getId().getPath().contains("bees_wax_")) {
				
				ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item.get(), 8)
				.pattern("AAA")
				.pattern("ABA")
				.pattern("AAA")
				.define('A', GrowthcraftApiaryItems.BEES_WAX.get())
				.define('B', ((GrowthcraftItem) item.get()).getDyeColor().getTag())
				.group(Reference.MODID)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftApiaryItems.BEES_WAX.get()))
				.save(consumer);
			}
		});
		
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.CANDLE)
		.pattern("S")
		.pattern("H")
		.define('S', Items.STRING)
		.define('H', GrowthcraftApiaryTags.Items.HONEY_COMB)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HONEYCOMB))
		.save(consumer);
		
		for (DyeColor dye : DyeColor.values()) {
			
			ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:"+dye+"_candle")))
			.requires(Items.CANDLE)
			.requires(dye.getTag())
			.group("dyed_candle")
			.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CANDLE))
			.save(consumer);
		}
	}
	
    @Override
    public String getName() {
        return "Growthcraft Apiary Recipes";
    }
}
