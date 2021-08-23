package me.tecc.tajapi.util;

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
    public static <T> T[] insertValue(T[] array, T object, int index) {
        int totalIndex = array.length + 1;

        T[] tempArray = (T[]) new Object[totalIndex];

        for (int i = 0; i < totalIndex; i++) {

            if (i == index) {
                tempArray[i] = object;
            } else {
                tempArray[i] = array[i];
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
    public static <T> T[] appendValue(T[] array, T value) {
        int totalIndex = array.length + 2;

        T[] tempArray = (T[]) new Object[totalIndex];

        System.arraycopy(array, 0, tempArray, 0, totalIndex);

        tempArray[totalIndex - 1] = value;

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
        int totalIndex = array.length + other.length;

        T[] tempArray = (T[]) new Object[totalIndex];

        for (int i = 0; i < totalIndex; i++) {
            if ((array.length - totalIndex) > 0) {
                tempArray[i] = array[i];
            } else {
                tempArray[i] = other[totalIndex - other.length];
            }
        }

        return tempArray;
    }

}
