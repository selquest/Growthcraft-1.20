package growthcraft.milk.compat.jei;

import growthcraft.milk.compat.jei.category.*;
import growthcraft.milk.recipe.*;
import growthcraft.milk.shared.Reference;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIGrowthcraftMilkModPlugin implements IModPlugin {

    public static RecipeType<CheesePressRecipe> CHEESE_PRESS_RECIPE_TYPE =
            new RecipeType<>(CheesePressRecipeCategory.UID, CheesePressRecipe.class);

    public static RecipeType<ChurnRecipe> CHURN_RECIPE_TYPE =
            new RecipeType<>(ChurnRecipeCategory.UID, ChurnRecipe.class);

    public static RecipeType<MixingVatFluidRecipe> MIXING_VAT_FLUID_RECIPE_TYPE =
            new RecipeType<>(MixingVatRecipeFluidCategory.UID, MixingVatFluidRecipe.class);

    public static RecipeType<MixingVatItemRecipe> MIXING_VAT_ITEM_RECIPE_TYPE =
            new RecipeType<>(MixingVatRecipeItemCategory.UID, MixingVatItemRecipe.class);

    public static RecipeType<PancheonRecipe> PANCHEON_RECIPE_TYPE =
            new RecipeType<>(PancheonRecipeCategory.UID, PancheonRecipe.class);

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(Reference.MODID, "jei");
    }

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new CheesePressRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new ChurnRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new MixingVatRecipeFluidCategory(registration.getJeiHelpers().getGuiHelper()),
                new MixingVatRecipeItemCategory(registration.getJeiHelpers().getGuiHelper()),
                new PancheonRecipeCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(
                Minecraft.getInstance().level).getRecipeManager();

        List<CheesePressRecipe> cheesePressRecipes =
                recipeManager.getAllRecipesFor(CheesePressRecipe.Type.INSTANCE);
        registration.addRecipes(CHEESE_PRESS_RECIPE_TYPE, cheesePressRecipes);

        List<ChurnRecipe> churnRecipes =
                recipeManager.getAllRecipesFor(ChurnRecipe.Type.INSTANCE);
        registration.addRecipes(CHURN_RECIPE_TYPE, churnRecipes);

        List<MixingVatFluidRecipe> mixingVatFluidRecipes =
                recipeManager.getAllRecipesFor(MixingVatFluidRecipe.Type.INSTANCE);
        registration.addRecipes(MIXING_VAT_FLUID_RECIPE_TYPE, mixingVatFluidRecipes);

        List<MixingVatItemRecipe> mixingVatItemRecipes =
                recipeManager.getAllRecipesFor(MixingVatItemRecipe.Type.INSTANCE);
        registration.addRecipes(MIXING_VAT_ITEM_RECIPE_TYPE, mixingVatItemRecipes);

        List<PancheonRecipe> pancheonRecipes =
                recipeManager.getAllRecipesFor(PancheonRecipe.Type.INSTANCE);
        registration.addRecipes(PANCHEON_RECIPE_TYPE, pancheonRecipes);
    }
}
