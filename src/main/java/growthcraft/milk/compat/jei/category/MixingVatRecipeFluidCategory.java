package growthcraft.milk.compat.jei.category;

import growthcraft.lib.utils.TextureHelper;
import growthcraft.lib.utils.TickUtils;
import growthcraft.milk.compat.jei.JEIGrowthcraftMilkModPlugin;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.recipe.MixingVatFluidRecipe;
import growthcraft.milk.shared.Reference;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MixingVatRecipeFluidCategory implements IRecipeCategory<MixingVatFluidRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(Reference.MODID, Reference.UnlocalizedName.MIXING_VAT_FLUID_RECIPE);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, Reference.UnlocalizedName.MIXING_VAT);

    private final IDrawable background;
    private final IDrawable icon;

    private final IDrawableStatic overlayHeated;

    public MixingVatRecipeFluidCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(
                TEXTURE, 10, 10, 160, 70
        );

        this.icon = guiHelper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(GrowthcraftMilkBlocks.MIXING_VAT.get())
        );

        // Heated Overlay
        overlayHeated = guiHelper.createDrawable(
                TEXTURE, 176, 28, 12, 13
        );
    }

    @Override
    public @NotNull RecipeType<MixingVatFluidRecipe> getRecipeType() {
        return JEIGrowthcraftMilkModPlugin.MIXING_VAT_FLUID_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("jei.growthcraft_milk.category.mixing_vat");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, MixingVatFluidRecipe recipe, @NotNull IFocusGroup focuses) {

        int ingredientCount = recipe.getIngredients().size();

        // Input Inventory
        for(int i = 0; i < ingredientCount; i++) {
            builder.addSlot(RecipeIngredientRole.INPUT, 61, 8 + ( i * 16))
                    .addIngredients(recipe.getIngredients().get(i));
        }

        // Fluid Tank 0
        builder.addSlot(RecipeIngredientRole.INPUT, 39, 8)
                .setFluidRenderer(1000, true, 8, 9)
                .addFluidStack(recipe.getReagentFluidStack().getFluid(), recipe.getReagentFluidStack().getAmount());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 48, 8)
                .setFluidRenderer(1000, true, 7, 9)
                .addFluidStack(recipe.getWasteFluidStack().getFluid(), recipe.getWasteFluidStack().getAmount());

        // Fluid Tank 1
        builder.addSlot(RecipeIngredientRole.INPUT, 39, 22)
                .setFluidRenderer(4000, true, 8, 38)
                .addFluidStack(recipe.getInputFluidStack().getFluid(), recipe.getInputFluidStack().getAmount());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 48, 22)
                .setFluidRenderer(4000, true, 7, 38)
                .addFluidStack(recipe.getOutputFluidStack().getFluid(), recipe.getOutputFluidStack().getAmount());

        // Output Inventory (Not needed for Fluid recipe.

        if(!recipe.getActivationTool().isEmpty()) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 0, 0)
                    .addItemStack(recipe.getActivationTool());
        }

    }

    @Override
    public void draw(@NotNull MixingVatFluidRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        Font font = Minecraft.getInstance().font;

        overlayHeated.draw(guiGraphics, 89, 48);

        guiGraphics.drawWordWrap(font, FormattedText.of("Mixing Time " + TickUtils.toHoursMinutesSeconds(recipe.getProcessingTime())), 0, 63, 170, 0x404040);
    }
}
