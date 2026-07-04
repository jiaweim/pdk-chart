package pdk.chart.data.flow;

import pdk.chart.api.PublicCloneable;
import pdk.chart.util.Args;

import java.io.Serializable;
import java.util.Objects;

/**
 * A key that identifies a flow within a dataset.
 *
 * @param <K> the type for the keys used to identify sources and destinations
 *            ({@code String} is a good default choice).
 * @since 1.5.3
 */
public class FlowKey<K extends Comparable<K>> implements PublicCloneable, Serializable {

    /**
     * The key for a flow property that, if defined (at the dataset level),
     * contains a {@code Boolean} value for the selection status of the flow.
     */
    public static final String SELECTED_PROPERTY_KEY = "selected";

    /**
     * The stage.
     */
    private final int stage;

    /**
     * The source node.
     */
    private final K source;

    /**
     * The destination node.
     */
    private final K destination;

    /**
     * Creates a new instance.
     *
     * @param stage       the stage.
     * @param source      the source identifier ({@code null} not permitted).
     * @param destination the destination identifier ({@code null} not permitted).
     */
    public FlowKey(int stage, K source, K destination) {
        Args.nullNotPermitted(source, "source");
        Args.nullNotPermitted(destination, "destination");
        this.stage = stage;
        this.source = source;
        this.destination = destination;
    }

    /**
     * Returns the stage number for the flow.
     *
     * @return The stage number.
     */
    public int getStage() {
        return this.stage;
    }

    /**
     * Returns the source identifier.
     *
     * @return The source identifier (never {@code null}).
     */
    public K getSource() {
        return source;
    }

    /**
     * Returns the destination identifier.
     *
     * @return The destination identifier (never {@code null}).
     */
    public K getDestination() {
        return destination;
    }

    /**
     * Returns a string representation of this instance, primarily for
     * debugging purposes.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[FlowKey: " + this.stage + ", " + this.source + " -> " + this.destination + "]";
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FlowKey<?> other = (FlowKey<?>) obj;
        if (this.stage != other.stage) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hashcode for this instance.
     *
     * @return A hashcode.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.stage;
        hash = 67 * hash + Objects.hashCode(this.source);
        hash = 67 * hash + Objects.hashCode(this.destination);
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

