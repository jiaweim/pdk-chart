package pdk.chart.entity;

import pdk.chart.data.flow.NodeKey;
import pdk.chart.util.Args;
import pdk.chart.plot.flow.FlowPlot;

import java.awt.*;

/**
 * A chart entity representing a node in a {@link FlowPlot}.
 *
 * @since 1.5.3
 */
public class NodeEntity extends ChartEntity {

    /**
     * The node key.
     */
    private NodeKey key;

    /**
     * Creates a new instance.
     *
     * @param key         the node key ({@code null} not permitted).
     * @param area        the outline of the entity ({@code null} not permitted).
     * @param toolTipText the tool tip text.
     */
    public NodeEntity(NodeKey key, Shape area, String toolTipText) {
        super(area, toolTipText);
        Args.nullNotPermitted(key, "key");
        this.key = key;
    }

    /**
     * Creates a new instance.
     *
     * @param area        the outline of the entity ({@code null} not permitted).
     * @param toolTipText the tool tip text.
     * @param urlText     the URL text.
     */
    public NodeEntity(Shape area, String toolTipText, String urlText) {
        super(area, toolTipText, urlText);
    }

    /**
     * Returns the node key.
     *
     * @return The node key (never {@code null}).
     */
    public NodeKey getKey() {
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
        return "[NodeEntity: " + this.key + "]";
    }

}
