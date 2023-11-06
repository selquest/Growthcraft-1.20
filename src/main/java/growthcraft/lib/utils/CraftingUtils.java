package growthcraft.lib.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class CraftingUtils {

    public static FluidStack getFluidStack(JsonObject json) {
        ResourceLocation fluidName = new ResourceLocation(GsonHelper.getAsString(json, "fluid"));
        int fluidAmount = GsonHelper.getAsInt(json, "amount", 1000);

        Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidName);

        if(fluid == null) {
            throw new JsonSyntaxException("Unknown fluid '" + fluidName + "' in Json.");
        }

        return new FluidStack(fluid, fluidAmount);
    }

    public static NonNullList<Ingredient> readIngredient(JsonObject ingredientObject) {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(Ingredient.fromJson(ingredientObject));
        return nonnulllist;
    }

    public static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();

        for (int i = 0; i < ingredientArray.size(); ++i) {
            Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
            if (!ingredient.isEmpty()) {
                nonnulllist.add(ingredient);
            }
        }

        return nonnulllist;
    }

    private CraftingUtils() { /* Prevent automatic public constructor */ }
}
