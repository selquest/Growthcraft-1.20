package growthcraft.lib.utils;

import net.minecraft.resources.ResourceLocation;

public class TextureHelper {
    public static ResourceLocation getTextureGui(String modid, String name) {
        return new ResourceLocation(modid, String.format("textures/gui/%s.png", name));
    }
}
