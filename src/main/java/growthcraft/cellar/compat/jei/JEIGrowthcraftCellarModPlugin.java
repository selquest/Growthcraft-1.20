package growthcraft.cellar.compat.jei;

import growthcraft.cellar.compat.jei.category.*;
import growthcraft.cellar.recipe.*;
import growthcraft.cellar.shared.Reference;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIGrowthcraftCellarModPlugin implements IModPlugin {

    public static RecipeType<BrewKettleRecipe> BREW_KETTLE_RECIPE_TYPE =
            new RecipeType<>(BrewKettleRecipeCategory.UID, BrewKettleRecipe.class);

    public static RecipeType<CultureJarRecipe> CULTURE_JAR_RECIPE_TYPE =
            new RecipeType<>(CultureJarRecipeCategory.UID, CultureJarRecipe.class);

    public static RecipeType<CultureJarStarterRecipe> CULTURE_JAR_STARTER_RECIPE_TYPE =
            new RecipeType<>(CultureJarStarterRecipeCategory.UID, CultureJarStarterRecipe.class);

    public static RecipeType<FermentationBarrelRecipe> FERMENTATION_BARREL_RECIPE_TYPE =
            new RecipeType<>(FermentationBarrelRecipeCategory.UID, FermentationBarrelRecipe.class);

    public static RecipeType<FruitPressRecipe> FRUIT_PRESS_RECIPE_TYPE =
            new RecipeType<>(FruitPressRecipeCategory.UID, FruitPressRecipe.class);

    public static RecipeType<RoasterRecipe> ROASTER_RECIPE_TYPE =
            new RecipeType<>(RoasterRecipeCategory.UID, RoasterRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Reference.MODID, "jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new BrewKettleRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new CultureJarRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new CultureJarStarterRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new FermentationBarrelRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new FruitPressRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new RoasterRecipeCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(
                Minecraft.getInstance().level).getRecipeManager();

        List<BrewKettleRecipe> brewKettleRecipes = recipeManager.getAllRecipesFor(BrewKettleRecipe.Type.INSTANCE);
        registration.addRecipes(BREW_KETTLE_RECIPE_TYPE, brewKettleRecipes);

        List<CultureJarRecipe> cultureJarRecipes = recipeManager.getAllRecipesFor(CultureJarRecipe.Type.INSTANCE);
        registration.addRecipes(CULTURE_JAR_RECIPE_TYPE, cultureJarRecipes);

        List<CultureJarStarterRecipe> cultureJarStarterRecipes = recipeManager.getAllRecipesFor(CultureJarStarterRecipe.Type.INSTANCE);
        registration.addRecipes(CULTURE_JAR_STARTER_RECIPE_TYPE, cultureJarStarterRecipes);

        List<FermentationBarrelRecipe> fermentationBarrelRecipes = recipeManager.getAllRecipesFor(FermentationBarrelRecipe.Type.INSTANCE);
        registration.addRecipes(FERMENTATION_BARREL_RECIPE_TYPE, fermentationBarrelRecipes);

        List<FruitPressRecipe> fruitPressRecipes = recipeManager.getAllRecipesFor(FruitPressRecipe.Type.INSTANCE);
        registration.addRecipes(FRUIT_PRESS_RECIPE_TYPE, fruitPressRecipes);

        List<RoasterRecipe> roasterRecipes = recipeManager.getAllRecipesFor(RoasterRecipe.Type.INSTANCE);
        registration.addRecipes(ROASTER_RECIPE_TYPE, roasterRecipes);

    }
}
