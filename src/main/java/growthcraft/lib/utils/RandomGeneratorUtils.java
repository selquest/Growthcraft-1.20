package growthcraft.lib.utils;

import java.security.SecureRandom;

public class RandomGeneratorUtils {

    public static int getRandomInt() {
        return getRandomInt(100);
    }

    public static int getRandomInt(int max) {
        return getRandomIntRange(0, max);
    }

    public static int getRandomIntRange(int min, int max) {
        return new SecureRandom().nextInt(min, max);
    }

    public static double getRandomDouble() {
        return new SecureRandom().nextDouble();
    }

    public static double getRandomDouble(double minValue) {
        return new SecureRandom().nextDouble() + minValue;
    }

}
