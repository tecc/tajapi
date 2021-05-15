package me.tecc.tajapi.util;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A direct way of finding certain elements from a collection
 * by using streams and predicates.
 *
 * @param <T> Type of the objects.
 * @param <L> Type of the collection.
 * @see Collection
 * @see Stream
 */
public interface Filter<T, L extends Collection<T>> {

    /**
     * Finds the first (singular) element that meets the
     * given condition.
     *
     * @param filter The filter => condition.
     * @return The first element that met the given condition.
     */
    default Optional<T> findFirst(Predicate<T> filter) {
        return find(filter).findFirst();
    }

    /**
     * Finds the any (singular) element that meets the
     * given condition.
     * <p>Remember, the output is in the form of {@link Optional}
     *
     * @param filter The filter => condition.
     * @return The first (any) element that met the given condition.
     */
    default Optional<T> findAny(Predicate<T> filter) {
        return find(filter).findAny();
    }

    /**
     * Returns a stream consisting of the elements of this stream
     * that match the given predicate (condition).
     *
     * @param filter Determine what elements should be included in the output stream.
     * @return Brand new stream with elements that met the given condition.
     * @see Stream
     */
    default Stream<T> find(Predicate<T> filter) {
        return getCollection().stream().filter(filter);
    }

    /**
     * In order to filter anything, this interface
     * needs a valid input. The input must be provided
     * by the implementation class as a collection.
     *
     * @return List of elements.
     */
    L getCollection();
}
