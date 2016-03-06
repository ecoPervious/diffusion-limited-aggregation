package bostock.michael.random;


import bostock.michael.model.Direction;

import java.util.Random;

public class Randomizer {
    private static final Random random = new Random(System.currentTimeMillis());

    private Randomizer() {
        // Prevent instantiation of utils class
    }

    public static int randomBetween(final int min, final int max) {
        return min + random.nextInt((max - min) + 1);
    }

    private static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        T[] enumConstants = clazz.getEnumConstants();
        int x = random.nextInt(enumConstants.length);
        return enumConstants[x];
    }

    public static Direction randomDirection() {
        return randomEnum(Direction.class);
    }
}
