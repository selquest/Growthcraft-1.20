package growthcraft.cellar.compat.jei.category;

import growthcraft.cellar.compat.jei.JEIGrowthcraftCellarModPlugin;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.recipe.FermentationBarrelRecipe;
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

public class FermentationBarrelRecipeCategory implements IRecipeCategory<FermentationBarrelRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(Reference.MODID, Reference.UnlocalizedName.FERMENT_BARREL);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, Reference.UnlocalizedName.FERMENT_BARREL);

    private final IDrawable background;
    private final IDrawable icon;

    public FermentationBarrelRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(
                TEXTURE, 10, 10, 160, 70
        );

        this.icon = guiHelper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(GrowthcraftCellarBlocks.FERMENTATION_BARREL_OAK.get())
        );
    }

    @Override
    public RecipeType<FermentationBarrelRecipe> getRecipeType() {
        return JEIGrowthcraftCellarModPlugin.FERMENTATION_BARREL_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.growthcraft_cellar.category.fermentation_barrel");
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
    public void setRecipe(IRecipeLayoutBuilder builder, FermentationBarrelRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 42, 43)
                .addItemStack(recipe.getIngredientItemStack());

        builder.addSlot(RecipeIngredientRole.INPUT, 67, 7)
                .setFluidRenderer(4000, true, 16, 52)
                .addFluidStack(recipe.getIngredientFluidStack().getFluid(), recipe.getIngredientFluidStack().getAmount());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 88, 7)
                .setFluidRenderer(4000, true, 16, 52)
                .addFluidStack(recipe.getResultingFluid().getFluid(), recipe.getResultingFluid().getAmount());
    }

    @Override
    public void draw(FermentationBarrelRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        Font font = Minecraft.getInstance().font;

        guiGraphics.drawWordWrap(font, FormattedText.of("Time multiplies based on amount."), 114, 7, 50, 0x404040);

        guiGraphics.drawString(font, "(" + TickUtils.toHoursMinutesSeconds(recipe.getProcessingTime()) + ")", 0, 55, 0x404040, false);
    }
}
