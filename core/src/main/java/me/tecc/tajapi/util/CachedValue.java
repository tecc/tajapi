package me.tecc.tajapi.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * Simple caching of values.
 * @param <T> The type of the cached value.
 */
public class CachedValue<T> {
    /**
     * The default cache duration.
     * If the value is gotten after this duration has passed from the last get,
     * it gets (and caches) the value again.
     */
    public static long CACHE_DURATION = 60000;

    /**
     * The last cached value.
     */
    protected T value;
    /**
     * When {@link #value} was last cached.
     * @see #value
     */
    protected long lastCache = 0;
    /**
     * The interval of which the cached value is valid.
     *
     * @see #CACHE_DURATION
     */
    protected long duration;
    /**
     * The get function.
     */
    @NotNull
    protected Supplier<T> getFunction;

    /**
     * Creates a new {@link CachedValue} with the default cache interval.
     * Alias for {@link #CachedValue(Supplier, long)}
     *
     * @param getFunction A supplier for refreshing the value.
     * @see #CachedValue(Supplier, long)
     */
    public CachedValue(@NotNull Supplier<T> getFunction) {
        this(getFunction, CACHE_DURATION);
    }
    /**
     * Creates a new {@link CachedValue} with the specified cache duration.
     *
     * @param getFunction A supplier for refreshing the value.
     * @param duration The duration that a cached value is valid for.
     */
    public CachedValue(@NotNull Supplier<T> getFunction, long duration) {
        this.getFunction = getFunction;
        this.duration = duration;
    }

    /**
     * Gets the value. If the values duration has expired, refreshes it.
     *
     * @return The value.
     */
    @Nullable
    public T getValue() {
        if (System.currentTimeMillis() - lastCache > duration) {
            value = getFunction.get();
            lastCache = System.currentTimeMillis();
        }
        return value;
    }

    /**
     * Sets the get function that's used when refreshing the cache.
     *
     * @param getFunction A new supplier for the value.
     * @see #getFunction
     * @see #getValue()
     */
    public void setGetFunction(@NotNull Supplier<T> getFunction) {
        this.getFunction = getFunction;
    }

    /**
     * Sets the duration of which a cached value is valid.
     * @param duration The duration
     */
    public void setCacheDuration(long duration) {
        this.duration = duration;
    }
}
