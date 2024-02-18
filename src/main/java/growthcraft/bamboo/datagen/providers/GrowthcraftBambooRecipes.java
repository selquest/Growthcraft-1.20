package growthcraft.bamboo.datagen.providers;

import java.util.function.Consumer;

import growthcraft.bamboo.init.GrowthcraftBambooBlocks;
import growthcraft.bamboo.shared.Reference;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

public class GrowthcraftBambooRecipes extends RecipeProvider{

	public GrowthcraftBambooRecipes(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_PLANK_BUTTON.get())
		.requires(GrowthcraftBambooBlocks.BAMBOO_PLANK.get().asItem())
		.group("wooden_button")
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftBambooBlocks.BAMBOO_PLANK.get()))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_PLANK_DOOR.get(), 3)
		.pattern("##")
		.pattern("##")
		.pattern("##")
		.define('#', GrowthcraftBambooBlocks.BAMBOO_PLANK.get())
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftBambooBlocks.BAMBOO_PLANK.get()))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_PLANK_FENCE_GATE.get(), 3)
		.pattern("121")
		.pattern("121")
		.define('1', Items.STICK)
		.define('2', GrowthcraftBambooBlocks.BAMBOO_PLANK.get())
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftBambooBlocks.BAMBOO_PLANK.get()))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_PLANK_FENCE.get(), 3)
		.pattern("212")
		.pattern("212")
		.define('1', Items.STICK)
		.define('2', GrowthcraftBambooBlocks.BAMBOO_PLANK.get())
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftBambooBlocks.BAMBOO_PLANK.get()))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_PLANK_PRESSURE_PLATE.get())
		.pattern("##")
		.define('#', GrowthcraftBambooBlocks.BAMBOO_PLANK.get())
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftBambooBlocks.BAMBOO_PLANK.get()))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_PLANK_SLAB.get(), 6)
		.pattern("###")
		.define('#', GrowthcraftBambooBlocks.BAMBOO_PLANK.get())
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftBambooBlocks.BAMBOO_PLANK.get()))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_PLANK_STAIRS.get(), 4)
		.pattern("#  ")
		.pattern("## ")
		.pattern("###")
		.define('#', GrowthcraftBambooBlocks.BAMBOO_PLANK.get())
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftBambooBlocks.BAMBOO_PLANK.get()))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_PLANK_TRAPDOOR.get())
		.pattern("###")
		.pattern("###")
		.define('#', GrowthcraftBambooBlocks.BAMBOO_PLANK.get())
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftBambooBlocks.BAMBOO_PLANK.get()))
		.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_PLANK.get(), 4)
		.requires(GrowthcraftBambooBlocks.BAMBOO_WOOD_LOG.get().asItem())
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftBambooBlocks.BAMBOO_WOOD_LOG.get()))
		.save(consumer);
		
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(Items.BAMBOO), RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_WOOD_LOG.get())
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BAMBOO))
		.save(consumer);;
		
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_WOOD.get(), 3)
		.pattern("##")
		.pattern("##")
		.define('#', GrowthcraftBambooBlocks.BAMBOO_WOOD_LOG.get())
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftBambooBlocks.BAMBOO_WOOD_LOG.get()))
		.save(consumer);
		
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GrowthcraftBambooBlocks.BAMBOO_PLANK_BEE_BOX.get())
		.pattern("SPS")
		.pattern("PBP")
		.pattern("SPS")
		.define('B', GrowthcraftBambooBlocks.BAMBOO_PLANK_SLAB.get())
		.define('P', GrowthcraftBambooBlocks.BAMBOO_PLANK.get())
		.define('S', Tags.Items.RODS_WOODEN)
		.group(Reference.MODID)
		.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(GrowthcraftBambooBlocks.BAMBOO_PLANK.get()))
		.save(consumer);	
	}
	
    @Override
    public String getName() {
        return "Growthcraft Bamboo Recipes";
    }
}
