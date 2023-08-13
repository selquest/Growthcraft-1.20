package growthcraft.milk.compat.jei.category;

import growthcraft.lib.utils.TextureHelper;
import growthcraft.milk.compat.jei.JEIGrowthcraftMilkModPlugin;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.recipe.ChurnRecipe;
import growthcraft.milk.shared.Reference;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
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

public class ChurnRecipeCategory implements IRecipeCategory<ChurnRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(Reference.MODID, Reference.UnlocalizedName.CHURN);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, Reference.UnlocalizedName.CHURN);

    private final IDrawable background;
    private final IDrawable icon;

    public ChurnRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(
                TEXTURE, 10, 10, 160, 70
        );

        this.icon = guiHelper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(GrowthcraftMilkBlocks.CHURN.get())
        );
    }

    @Override
    public RecipeType<ChurnRecipe> getRecipeType() {
        return JEIGrowthcraftMilkModPlugin.CHURN_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.growthcraft_milk.category.churn");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ChurnRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 55, 8)
                .setFluidRenderer(4000, true, 8, 52)
                .addFluidStack(recipe.getInputFluidStack().getFluid(), recipe.getInputFluidStack().getAmount());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 84, 25)
                .addItemStack(recipe.getResultItemStack());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 64, 8)
                .setFluidRenderer(4000, true, 7, 52)
                .addFluidStack(recipe.getOutputFluidStack().getFluid(), recipe.getOutputFluidStack().getAmount());
    }

    @Override
    public void draw(ChurnRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        Font font = Minecraft.getInstance().font;

        Float s = 0.9F;
        guiGraphics.pose().scale(s, s, s);

        guiGraphics.drawWordWrap(font, FormattedText.of("Toggle plunge " + recipe.getPlungesNeeded() + " times."), 0, 70, 170, 0x404040);
    }
}
