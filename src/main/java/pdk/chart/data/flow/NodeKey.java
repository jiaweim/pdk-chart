package pdk.chart.data.flow;

import pdk.chart.api.PublicCloneable;
import pdk.chart.util.Args;

import java.io.Serializable;
import java.util.Objects;

/**
 * A key that identifies a node in a {@link FlowDataset}.  Instances of this
 * class are immutable.
 *
 * @param <K> the type for the keys used to identify sources and destinations
 *            ({@code String} is a good default choice).
 * @since 1.5.3
 */
public class NodeKey<K extends Comparable<K>> implements PublicCloneable, Serializable {

    /**
     * The key for a node property that, if defined (at the dataset level),
     * contains a {@code Boolean} value for the selection status of the node.
     */
    public static final String SELECTED_PROPERTY_KEY = "selected";

    /**
     * The stage.
     */
    private final int stage;

    /**
     * The source node.
     */
    private final K node;

    /**
     * Creates a new key referencing a node in a {@link FlowDataset}.
     *
     * @param stage the stage.
     * @param node  the node key.
     */
    public NodeKey(int stage, K node) {
        Args.nullNotPermitted(node, "node");
        this.stage = stage;
        this.node = node;
    }

    /**
     * Returns the stage number.
     *
     * @return The stage number.
     */
    public int getStage() {
        return this.stage;
    }

    /**
     * Returns the identifier for the node.
     *
     * @return The identifier for the node (never {@code null}).
     */
    public K getNode() {
        return this.node;
    }

    /**
     * Returns a string representation of this instance, primarily for
     * debugging purposes.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[NodeKey: " + stage + ", " + node + "]";
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
        final NodeKey<?> other = (NodeKey<?>) obj;
        if (this.stage != other.stage) {
            return false;
        }
        if (!Objects.equals(this.node, other.node)) {
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
        int hash = 3;
        hash = 53 * hash + this.stage;
        hash = 53 * hash + Objects.hashCode(this.node);
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}