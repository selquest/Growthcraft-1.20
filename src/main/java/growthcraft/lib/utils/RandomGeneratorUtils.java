package growthcraft.lib.utils;

import java.security.SecureRandom;

public class RandomGeneratorUtils {

    public static int getRandomInt() {
        return new SecureRandom().nextInt();
    }

    public static double getRandomDouble() {
        return new SecureRandom().nextDouble();
    }

    public static double getRandomDouble(double minValue) {
        return new SecureRandom().nextDouble() + minValue;
    }

}
