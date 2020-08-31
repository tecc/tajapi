/*
 * Copyright (c) tecc, 2020.
 * This project is licensed under tecc's license.
 */

package me.tecc.tajapi.util;

/**
 * Provides integer utilities such as wrapping.
 */
public class IntUtil {

    /**
     * Wraps an integer to the closest boundary.
     * For example, if it is called with 744 as value, 360 as maximum and 45 as minimum,
     * it'll get rounded down to 360.
     * @param value The value to round down
     * @param min Minimum value
     * @param max Maximum value
     * @return The wrapped number.
     */
    public static int wrapToClosest(int value, int min, int max) {
        if (value < min)
            return min;
        // neat little trick
        // if the value is less than the minimum the smallest number
        // of the maximum and the value must be wrapped.
        // thanks intellij
        return Math.min(value, max);
    }
}
