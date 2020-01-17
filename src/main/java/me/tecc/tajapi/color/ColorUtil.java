/*
 * Copyright (c) TechnotypeO, 2020.
 * This project is licensed under tecc's license (located in "LICENSE.txt").
 */

package me.tecc.tajapi.color;

import java.awt.*;

/**
 * ColorUtil class is a utility class for working with colors.
 *
 */

public final class ColorUtil {
    private ColorUtil() {}
    /**
     * Gets a color as a byte from its RGB values.
     * Useful when you don't want to use the {@link Color}
     * class.
     * <br>
     * Note, it is recommended to use the {@link Color#Color(int, int, int)} constructor when you need a Color instance.
     *
     * @param r The red color channels value.
     *          Minimum value 0 and maximum 255. If a negative value is entered, it will default to 0. If a value greater than 255 is entered, it will default to 255.
     * @param g The green color channels value.
     *          Minimum value 0 and maximum 255. If a negative value is entered, it will default to 0. If a value greater than 255 is entered, it will default to 255.
     * @param b The blue color channels value.
     *          Minimum value 0 and maximum 255. If a negative value is entered, it will default to 0. If a value greater than 255 is entered, it will default to 255.
     * @return A value between {@code 0x000000} (0) and {@code 0xffffff} (2<sup>24</sup>-1)
     * @see Color
     */
    public static byte getColorFromRGB(int r, int g, int b) {
        // set c to r
        byte c = (byte) r;
        // shift c left by 8 bits
        // to make place for g
        c <<= 8;
        // add g
        c += g;
        // shift c left by 8 bits
        // to make place for b
        c <<= 8;
        // add b
        c += b;
        return c;
    }
}
