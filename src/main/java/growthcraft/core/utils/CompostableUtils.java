package growthcraft.core.utils;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostableUtils {

    /**
     * Registers an item as compostable with a specific compostability value.
     *
     * @param item        the item to register as compostable
     * @param compostable the compostability value of the item
     */
    public static void registerCompostable(Item item, COMPOSTABLE compostable) {
        ComposterBlock.COMPOSTABLES.put(item, compostable.getValue());
    }

    /**
     * Registers an item as compostable with a custom compostability value.
     *
     * @param item        the item to register as compostable
     * @param compostable the custom compostability value of the item (between 0.0 and 1.0)
     */
    public static void registerCompostableCustom(Item item, float compostable) {
        ComposterBlock.COMPOSTABLES.put(item, compostable);
    }

    /**
     * Retrieves the compostable value associated with the given key.
     *
     * @param key the key to look up the compostable value for
     * @return the compostable value associated with the given key, or NORMAL if no matching key is found
     */
    public static COMPOSTABLE getCompostableByString(String key) {
        switch (key) {
            case "lowest":
                return COMPOSTABLE.LOWEST;
            case "low":
                return COMPOSTABLE.LOW;
            case "high":
                return COMPOSTABLE.HIGH;
            case "highest":
                return COMPOSTABLE.HIGHEST;
            default:
                return COMPOSTABLE.NORMAL;
        }
    }

    public enum COMPOSTABLE {
        LOWEST(0.3F),
        LOW(0.5F),
        NORMAL(0.65F),
        HIGH(0.85F),
        HIGHEST(1.0F);

        private final float value;

        COMPOSTABLE(float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }
    }

}
