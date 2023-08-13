package growthcraft.cellar.compat.jei.category;

import growthcraft.cellar.compat.jei.JEIGrowthcraftCellarModPlugin;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.recipe.FruitPressRecipe;
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

public class FruitPressRecipeCategory implements IRecipeCategory<FruitPressRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Reference.MODID, Reference.UnlocalizedName.FRUIT_PRESS);

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, Reference.UnlocalizedName.FRUIT_PRESS);

    private final IDrawable background;
    private final IDrawable icon;

    public FruitPressRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(
                TEXTURE, 10, 10, 160, 70
        );

        this.icon = guiHelper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(GrowthcraftCellarBlocks.FRUIT_PRESS.get())
        );
    }

    @Override
    public RecipeType<FruitPressRecipe> getRecipeType() {
        return JEIGrowthcraftCellarModPlugin.FRUIT_PRESS_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.growthcraft_cellar.category.fruit_press");
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
    public void setRecipe(IRecipeLayoutBuilder builder, FruitPressRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 42, 43)
                .addItemStack(recipe.getIngredientItemStack());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 62, 7)
                .setFluidRenderer(4000, true, 50, 52)
                .addFluidStack(recipe.getResultingFluid().getFluid(), recipe.getResultingFluid().getAmount());
    }

    @Override
    public void draw(FruitPressRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        Font font = Minecraft.getInstance().font;



        guiGraphics.drawString(font, "(" + TickUtils.toHoursMinutesSeconds(recipe.getProcessingTime()) + ")", 0, 55, 0x404040, false);

        guiGraphics.pose().scale(0.8F, 0.8F, 0.8F);

        guiGraphics.drawWordWrap(font, FormattedText.of("Watch for drainage particles until completed."), 145, 7, 60, 0x404040);

    }
}
