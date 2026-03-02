package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Global metrics registry — thread-safe, lazy-initialized Singleton.
 *
 * Guarantees:
 * - Single instance via static holder idiom (lazy + thread-safe)
 * - Reflection-safe: constructor throws if instance already exists
 * - Serialization-safe: readResolve() returns the singleton
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static volatile boolean instanceCreated = false;

    private final Map<String, Long> counters = new HashMap<>();

    // Static holder idiom — lazy, thread-safe, no synchronization overhead
    private static class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    // Private constructor with reflection guard
    private MetricsRegistry() {
        if (instanceCreated) {
            throw new IllegalStateException("MetricsRegistry is a singleton — use getInstance()");
        }
        instanceCreated = true;
    }

    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    // Serialization guard — always return the singleton
    @Serial
    private Object readResolve() {
        return getInstance();
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
