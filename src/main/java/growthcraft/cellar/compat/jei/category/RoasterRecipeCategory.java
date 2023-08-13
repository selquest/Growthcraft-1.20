package growthcraft.cellar.compat.jei.category;

import growthcraft.cellar.compat.jei.JEIGrowthcraftCellarModPlugin;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.recipe.RoasterRecipe;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.utils.TextureHelper;
import growthcraft.lib.utils.TickUtils;
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

public class RoasterRecipeCategory implements IRecipeCategory<RoasterRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Reference.MODID, Reference.UnlocalizedName.ROASTER);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, Reference.UnlocalizedName.ROASTER);

    private final IDrawable background;
    private final IDrawable icon;

    public RoasterRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(
                TEXTURE, 10, 10, 160, 70
        );

        this.icon = guiHelper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(GrowthcraftCellarBlocks.ROASTER.get())
        );
    }

    @Override
    public RecipeType<RoasterRecipe> getRecipeType() {
        return JEIGrowthcraftCellarModPlugin.ROASTER_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.growthcraft_cellar.category.roaster");    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RoasterRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 44, 32)
                .addItemStack(recipe.getInputItemStack());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 96, 32)
                .addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(RoasterRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        Font font = Minecraft.getInstance().font;

        guiGraphics.drawWordWrap(font, FormattedText.of("Roasting time is the same regardless of amount."), 0, 1, 150, 0x404040);

        guiGraphics.drawWordWrap(font, FormattedText.of("Roasting Level " + recipe.getRecipeProcessingTime() + " for " + TickUtils.toHoursMinutesSeconds(recipe.getRecipeProcessingTime() * 30 * 20)), 0, 63, 150, 0x404040);

    }
}
