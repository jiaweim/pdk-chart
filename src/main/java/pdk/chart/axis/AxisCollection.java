package pdk.chart.axis;

import pdk.chart.api.RectangleEdge;
import pdk.chart.util.Args;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of axes that have been assigned to the TOP, BOTTOM, LEFT or
 * RIGHT of a chart.  This class is used internally by JFreeChart, you won't
 * normally need to use it yourself.
 */
public class AxisCollection {

    /**
     * The axes that need to be drawn at the top of the plot area.
     */
    private final List<Axis> axesAtTop;

    /**
     * The axes that need to be drawn at the bottom of the plot area.
     */
    private final List<Axis> axesAtBottom;

    /**
     * The axes that need to be drawn at the left of the plot area.
     */
    private final List<Axis> axesAtLeft;

    /**
     * The axes that need to be drawn at the right of the plot area.
     */
    private final List<Axis> axesAtRight;

    /**
     * Creates a new empty collection.
     */
    public AxisCollection() {
        this.axesAtTop = new ArrayList<>();
        this.axesAtBottom = new ArrayList<>();
        this.axesAtLeft = new ArrayList<>();
        this.axesAtRight = new ArrayList<>();
    }

    /**
     * Returns a list of the axes (if any) that need to be drawn at the top of
     * the plot area.
     *
     * @return A list of axes.
     */
    public List<Axis> getAxesAtTop() {
        return this.axesAtTop;
    }

    /**
     * Returns a list of the axes (if any) that need to be drawn at the bottom
     * of the plot area.
     *
     * @return A list of axes.
     */
    public List<Axis> getAxesAtBottom() {
        return this.axesAtBottom;
    }

    /**
     * Returns a list of the axes (if any) that need to be drawn at the left
     * of the plot area.
     *
     * @return A list of axes.
     */
    public List<Axis> getAxesAtLeft() {
        return this.axesAtLeft;
    }

    /**
     * Returns a list of the axes (if any) that need to be drawn at the right
     * of the plot area.
     *
     * @return A list of axes.
     */
    public List<Axis> getAxesAtRight() {
        return this.axesAtRight;
    }

    /**
     * Adds an axis to the collection.
     *
     * @param axis the axis ({@code null} not permitted).
     * @param edge the edge of the plot that the axis should be drawn on
     *             ({@code null} not permitted).
     */
    public void add(Axis axis, RectangleEdge edge) {
        Args.nullNotPermitted(axis, "axis");
        Args.nullNotPermitted(edge, "edge");
        switch (edge) {
            case TOP:
                this.axesAtTop.add(axis);
                break;
            case BOTTOM:
                this.axesAtBottom.add(axis);
                break;
            case LEFT:
                this.axesAtLeft.add(axis);
                break;
            case RIGHT:
                this.axesAtRight.add(axis);
                break;
            default:
                break;
        }
    }

}
