package pdk.chart.data;

import pdk.chart.util.Args;

import java.io.Serializable;
import java.util.Objects;

/**
 * A reference to an item in a {link KeyedValues} instance.
 *
 * @param <K> The key type.
 */
public class KeyedValuesItemKey<K extends Comparable<K>> implements ItemKey,
        Serializable {

    /**
     * The key for the item.
     */
    K key;

    /**
     * Creates a new instance.
     *
     * @param key the key ({@code null} not permitted).
     */
    public KeyedValuesItemKey(K key) {
        Args.nullNotPermitted(key, "key");
        this.key = key;
    }

    /**
     * Returns the key.
     *
     * @return The key (never {@code null}).
     */
    public K getKey() {
        return this.key;
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} not permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof KeyedValuesItemKey)) {
            return false;
        }
        KeyedValuesItemKey that = (KeyedValuesItemKey) obj;
        if (!this.key.equals(that.key)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.key);
        return hash;
    }

    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"key\": \"").append(this.key.toString()).append("\"}");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyedValuesItemKey[");
        sb.append(this.key.toString());
        sb.append("]");
        return sb.toString();
    }
}
