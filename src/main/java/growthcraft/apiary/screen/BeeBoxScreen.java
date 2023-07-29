package growthcraft.apiary.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import growthcraft.apiary.shared.Reference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BeeBoxScreen extends AbstractContainerScreen<BeeBoxMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID,
            "textures/gui/bee_box.png");

    public BeeBoxScreen(BeeBoxMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.width = 0;
        this.height = 0;
        this.imageWidth = 176;
        this.imageHeight = 200;
    }

    @Override
    protected void renderBg(GuiGraphics poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        poseStack.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics poseStack, int mouseX, int mouseY) {
        // Do nothing as this isn't a standard size GUI.
        // We will have to render the GUI title and inventory label ourselves.
        poseStack.drawString(this.font, this.title,
                this.titleLabelX, this.titleLabelY,
                4210752, false);
        poseStack.drawString(this.font, this.playerInventoryTitle,
                this.inventoryLabelX, this.inventoryLabelY - 32,
                4210752, false);
    }
}
