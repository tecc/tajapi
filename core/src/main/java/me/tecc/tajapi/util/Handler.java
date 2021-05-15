package me.tecc.tajapi.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Handles a certain type of values in a list.
 *
 * @param <T> Type of the values.
 */
public class Handler<T> implements Filter<T, List<T>> {
    /**
     * List of all stored values.
     */
    protected List<T> list = new ArrayList<>();

    /**
     * Adds a value to the list.
     *
     * @param value The value to be added.
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    public boolean add(T value) {
        return list.add(value);
    }

    /**
     * Works as same as {@link #add(T)}, however
     * supports the addition of multiple elements at once.
     *
     * @param value The value(s) to be added.
     * @return <tt>true</tt> (as specified by {@link Collection#addAll})
     */
    public boolean add(T... value) {
        return Collections.addAll(list, value);
    }

    /**
     * Removes the given element from the list.
     *
     * @param value The value to be removed.
     * @return <tt>true</tt> (as specified by {@link Collection#remove})
     */
    public boolean remove(T value) {
        return list.remove(value);
    }

    /**
     * Gets the list of all values.
     *
     * @return List of all values.
     * @see #list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * This function is used by the {@link Filter} interface.
     * For some deeper functionalities that interface has
     * to have its own way of getting a collection.
     *
     * @return The output of {@link #getList()}
     * @see Filter
     */
    @Override
    public List<T> getCollection() {
        return getList();
    }
}
