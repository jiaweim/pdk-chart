package pdk.chart.entity;

import pdk.chart.data.flow.FlowKey;
import pdk.chart.util.Args;
import pdk.chart.plot.flow.FlowPlot;

import java.awt.*;
import java.util.Objects;

/**
 * A chart entity representing the flow between two nodes in a {@link FlowPlot}.
 *
 * @param <K> the data key type.
 */
public class FlowEntity<K extends Comparable<K>> extends ChartEntity {

    /**
     * The key.
     */
    private final FlowKey<K> key;

    /**
     * Creates a new instance.
     *
     * @param key         the key identifying the flow ({@code null} not permitted).
     * @param area        the outline of the entity ({@code null} not permitted).
     * @param toolTipText the tool tip text.
     * @param urlText     the URL text.
     */
    public FlowEntity(FlowKey<K> key, Shape area, String toolTipText, String urlText) {
        super(area, toolTipText, urlText);
        Args.nullNotPermitted(key, "key");
        this.key = key;
    }

    /**
     * Returns the key identifying the flow.
     *
     * @return The flow key (never {@code null}).
     */
    public FlowKey<K> getKey() {
        return this.key;
    }

    /**
     * Returns a string representation of this instance, primarily for
     * debugging purposes.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[FlowEntity: " + this.key + "]";
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FlowEntity)) {
            return false;
        }
        FlowEntity that = (FlowEntity) obj;
        if (!this.key.equals(that.key)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.key);
        return hash;
    }

}
