package me.tecc.tajapi.util;

import java.lang.reflect.Array;
import java.util.function.IntFunction;

public class ArrayUtil {

    /**
     * Inserts a value into an array
     *
     * @param array the array for the value to be inserted into
     * @param object the value to be inserted
     * @param index the index at which the value will be inserted
     * @param <T> the type of the array
     * @return array with the inserted value
     * @author Hammer86gn
     */
    public static <T> T[] insertValue(T[] array, T object, int index, IntFunction<T[]> instantiate) {
        int totalLen = array.length + 1;

        T[] tempArray = instantiate.apply(totalLen);

        int offset = 0;
        for (int i = 0; i < totalLen; i++) {

            if (i == index) {
                tempArray[i] = object;
                offset++;
            } else {
                tempArray[i] = array[i - offset];
            }

        }

        return tempArray;
    }

    /**
     * Appends a value to the end of an array
     *
     * @param array the array to append to
     * @param value the value to append
     * @param <T> the type of the array
     * @return array with the value appended
     * @author Hammer86gn
     */
    public static <T> T[] appendValue(T[] array, T value, IntFunction<T[]> instantiate) {
        int totalLen = array.length + 1;

        T[] tempArray = instantiate.apply(totalLen);

        System.arraycopy(array, 0, tempArray, 0, totalLen);

        tempArray[totalLen] = value;



        return tempArray;
    }

    /**
     * Merge two arrays together
     *
     * @param array the first array to merge
     * @param other the array to merge to the end of the first array
     * @param <T> the type of the arrays
     * @return array with the values of both arrays
     * @author Hammer86gn
     */
    public static <T> T[] mergeArray(T[] array, T[] other) {
        int totalLen = array.length + other.length;

        T[] tempArray = (T[]) Array.newInstance(Object.class,totalLen);

        System.arraycopy(array,0,tempArray,0,totalLen);
        System.arraycopy(other,0,tempArray,array.length + 1,totalLen);

        return tempArray;
    }

}
