package growthcraft.milk.compat.jei.category;

import growthcraft.lib.utils.TextureHelper;
import growthcraft.lib.utils.TickUtils;
import growthcraft.milk.compat.jei.JEIGrowthcraftMilkModPlugin;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.recipe.PancheonRecipe;
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

public class PancheonRecipeCategory implements IRecipeCategory<PancheonRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(Reference.MODID, Reference.UnlocalizedName.PANCHEON_RECIPE);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, Reference.UnlocalizedName.PANCHEON);

    private final IDrawable background;
    private final IDrawable icon;

    public PancheonRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(
                TEXTURE, 10, 10, 160, 70
        );

        this.icon = guiHelper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(GrowthcraftMilkBlocks.PANCHEON.get())
        );
    }

    @Override
    public RecipeType<PancheonRecipe> getRecipeType() {
        return JEIGrowthcraftMilkModPlugin.PANCHEON_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.growthcraft_milk.category.pancheon");
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
    public void setRecipe(IRecipeLayoutBuilder builder, PancheonRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 52, 8)
                .setFluidRenderer(2000, true, 16, 52)
                .addFluidStack(recipe.getFluidStack("input").getFluid(), recipe.getFluidStack("input").getAmount());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 88, 8)
                .setFluidRenderer(1000, true, 16, 23)
                .addFluidStack(recipe.getFluidStack("output0").getFluid(), recipe.getFluidStack("output0").getAmount());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 88, 37)
                .setFluidRenderer(1000, true, 16, 23)
                .addFluidStack(recipe.getFluidStack("output1").getFluid(), recipe.getFluidStack("output1").getAmount());
    }

    @Override
    public void draw(PancheonRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        Font font = Minecraft.getInstance().font;

        guiGraphics.drawWordWrap(font, FormattedText.of("Processing Time " + TickUtils.toHoursMinutesSeconds(recipe.getRecipeProcessingTime())), 0, 63, 170, 0x404040);
    }
}
