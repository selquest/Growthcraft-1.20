package growthcraft.cellar.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import growthcraft.cellar.screen.container.RoasterMenu;
import growthcraft.cellar.shared.Reference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoasterScreen extends AbstractContainerScreen<RoasterMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
            Reference.MODID, "textures/gui/roaster_screen.png"
    );

    public RoasterScreen(RoasterMenu menu, Inventory inventory, Component component){
        super(menu, inventory, component);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Full background image
        poseStack.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        poseStack.blit(TEXTURE,
                x + 76, y + 48,
                76, 0,
                menu.getProgressionScaled(28), 9
        );

        // Heat indicator
        if(this.menu.isHeated()) {
            poseStack.blit(TEXTURE,
                    x + 81, y + 57,
                    176, 28,
                    13, 13
            );
        }
    }

    @Override
    public void render(GuiGraphics poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        // Render any tooltips for this mouse over location.
        renderTooltip(poseStack, mouseX, mouseY);    }

    @Override
    protected void renderLabels(GuiGraphics poseStack, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Screen Title
        poseStack.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
        // Inventory Title
        poseStack.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);

        renderProgressToolTip(poseStack, mouseX, mouseY, x, y);
    }

    private void renderProgressToolTip(GuiGraphics poseStack, int mouseX, int mouseY, int x, int y) {
        List<Component> tooltip = new ArrayList<>();

        MutableComponent progressString = Component.translatable(Reference.MODID.concat(".tooltip.roaster.progress"), menu.getRoastingLevel(), menu.getPercentProgress());
        tooltip.add(progressString);

        if (isMouseAboveArea(mouseX, mouseY, x + 74, y + 45, 28, 9, 28, 9)) {
            poseStack.renderTooltip(
                    this.font,
                    tooltip,
                    Optional.empty(),
                    mouseX - x,
                    mouseY - y
            );
        }
    }

    private boolean isMouseAboveArea(int mouseX, int mouseY, int baseX, int baseY, int offsetX, int offsetY, int width, int height) {
        return (mouseX >= baseX && mouseX <= (baseX + offsetX)) && (mouseY >= baseY && mouseY <= (baseY + offsetY));
    }

}
