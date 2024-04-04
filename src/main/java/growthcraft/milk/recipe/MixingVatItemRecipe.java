package growthcraft.milk.recipe;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonObject;

import growthcraft.lib.utils.CraftingUtils;
import growthcraft.lib.utils.RecipeUtils;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.shared.Reference;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;

public class MixingVatItemRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation recipeId;
    private final RecipeUtils.Category category;
    private final ItemStack activationTool;
    private final NonNullList<Ingredient> ingredients;
    private final FluidStack inputFluidStack;
    private final int processingTime;
    private final ItemStack resultItemStack;
    private final ItemStack resultActivationTool;
    private final boolean requiresHeat;


    public MixingVatItemRecipe(ResourceLocation recipeId, RecipeUtils.Category category,
                               FluidStack inputFluidStack, NonNullList<Ingredient> ingredients, int processingTime,
                               ItemStack resultItemStack, ItemStack activationTool,
                               ItemStack resultActivationTool,
                               boolean requiresHeat) {
        this.recipeId = recipeId;
        this.category = category;
        this.inputFluidStack = inputFluidStack;
        this.ingredients = ingredients;
        this.processingTime = processingTime;
        this.resultItemStack = resultItemStack;
        this.activationTool = activationTool;
        this.resultActivationTool = resultActivationTool;
        this.requiresHeat = requiresHeat;
    }


    @Override
    public boolean matches(@NotNull SimpleContainer p_44002_, @NotNull Level p_44003_) {
        return false;
    }

    public boolean matches(FluidStack testFluidStack, List<ItemStack> testIngredients, boolean hasHeatSource) {

        boolean fluidMatches = CraftingUtils.doesFluidMatch(testFluidStack, this.getInputFluidStack());

        boolean ingredientMatches = false;

        if (this.getIngredients().size() == testIngredients.size()) {
            int itemCount = this.getIngredients().size();
            int matchCount = 0;
            for (int i = 0; i < this.getIngredients().size(); i++) {
                if (this.getIngredients().get(i).getItems()[0].getItem() == testIngredients.get(i).getItem() &&
                        this.getIngredients().get(i).getItems()[0].getCount() == testIngredients.get(i).getCount()) {
                    matchCount++;
                }
            }
            ingredientMatches = itemCount == matchCount;
        }

        return fluidMatches && ingredientMatches && hasHeatSource == isHeatRequired();
    }

    public boolean matchResult(ItemStack itemStack) {
        return this.resultItemStack.getItem() == itemStack.getItem() && this.resultItemStack.getCount() == itemStack.getCount();
    }

    public FluidStack getInputFluidStack() {
        return this.inputFluidStack;
    }

    public ItemStack getActivationTool() {
        return this.activationTool;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public ItemStack getResultItemStack() {
        return this.resultItemStack.copy();
    }

    public ItemStack getResultActivationTool() {
        return this.resultActivationTool;
    }


    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return resultItemStack;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return resultItemStack;
    }

    public boolean isHeatRequired() {
        return this.requiresHeat;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    public RecipeUtils.Category getCategory() {
        return this.category;
    }

    public boolean is(RecipeUtils.Category category) {
        return this.category == category;
    }

    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public static class Type implements RecipeType<MixingVatItemRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final MixingVatItemRecipe.Type INSTANCE = new MixingVatItemRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.MIXING_VAT_ITEM_RECIPE;
    }


    public static class Serializer implements RecipeSerializer<MixingVatItemRecipe> {

        public static final MixingVatItemRecipe.Serializer INSTANCE = new MixingVatItemRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.MIXING_VAT_RECIPE);

        private static final int maxIngredients = 3;

        @Override
        public MixingVatItemRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            RecipeUtils.Category category
                    = RecipeUtils.Category.with(GsonHelper.getAsString(json, "result_type"));

            int processingTime = GsonHelper.getAsInt(json, "processing_time", 1200);
            boolean requiresHeat = GsonHelper.getAsBoolean(json, "requires_heat");

            FluidStack inputFluid = CraftingUtils.getFluidStack(
                    GsonHelper.getAsJsonObject(json, "input_fluid"));

            ItemStack activationTool = CraftingHelper.getItemStack(
                    GsonHelper.getAsJsonObject(json, "activation_tool"), false);

            NonNullList<Ingredient> ingredients = CraftingUtils.readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));

            if (category == RecipeUtils.Category.ITEM) {
                ItemStack resultItemStack = CraftingHelper.getItemStack(
                        GsonHelper.getAsJsonObject(json, "result_item"), false);
                ItemStack resultActivationTool = CraftingHelper.getItemStack(
                        GsonHelper.getAsJsonObject(json, "result_activation_tool"), false);

                return new MixingVatItemRecipe(recipeId, RecipeUtils.Category.ITEM,
                        inputFluid, ingredients, processingTime, resultItemStack, activationTool, resultActivationTool, requiresHeat);
            }


            return null;
        }

        @Override
        public @Nullable MixingVatItemRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            try {
                RecipeUtils.Category category = RecipeUtils.Category.with(buffer.readUtf());

                int processingTime = buffer.readVarInt();
                boolean requiresHeat = buffer.readBoolean();

                FluidStack inputFluidStack = buffer.readFluidStack();
                ItemStack activationTool = buffer.readItem();

                int ingredientSize = buffer.readVarInt();
                NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientSize, Ingredient.EMPTY);

                for (int i = 0; i < ingredientSize; i++) {
                    ingredients.set(i, Ingredient.fromNetwork(buffer));
                }

                ItemStack resultingItemStack = buffer.readItem();
                ItemStack resultActivationTool = buffer.readItem();

                return new MixingVatItemRecipe(recipeId, category, inputFluidStack, ingredients,
                        processingTime, resultingItemStack, activationTool, resultActivationTool, requiresHeat);
            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftMilk.LOGGER.error(message);
                throw ex;
            }
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MixingVatItemRecipe recipe) {
            buffer.writeUtf(recipe.getCategory().toString());

            buffer.writeVarInt(recipe.getProcessingTime());
            buffer.writeBoolean(recipe.isHeatRequired());

            buffer.writeFluidStack(recipe.getInputFluidStack());
            buffer.writeItemStack(recipe.getActivationTool(), false);

            buffer.writeVarInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItemStack(recipe.getResultItemStack(), false);
            buffer.writeItemStack(recipe.getResultActivationTool(), false);

        }

    }
}
