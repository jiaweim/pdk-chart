package pdk.chart.axis;

import pdk.chart.api.RectangleEdge;
import pdk.chart.util.Args;

import java.util.ArrayList;
import java.util.List;

/**
 * Instances of this class are used to carry state information for an axis
 * during the drawing process.  By retaining this information in a separate
 * object, it is possible for multiple threads to draw the same axis to
 * different output targets (each drawing will maintain separate state
 * information).
 */
public class AxisState {

    /**
     * The cursor position.
     */
    private double cursor;

    /**
     * The axis ticks.
     */
    private List<ValueTick> ticks;

    /**
     * The maximum width/height.
     */
    private double max;

    /**
     * Creates a new axis state.
     */
    public AxisState() {
        this(0.0);
    }

    /**
     * Creates a new axis state.
     *
     * @param cursor the cursor.
     */
    public AxisState(double cursor) {
        this.cursor = cursor;
        this.ticks = new ArrayList<>();
    }

    /**
     * Returns the cursor position.
     *
     * @return The cursor position.
     */
    public double getCursor() {
        return this.cursor;
    }

    /**
     * Sets the cursor position.
     *
     * @param cursor the cursor position.
     */
    public void setCursor(double cursor) {
        this.cursor = cursor;
    }

    /**
     * Moves the cursor outwards by the specified number of units.
     *
     * @param units the units.
     * @param edge  the edge ({@code null} not permitted).
     */
    public void moveCursor(double units, RectangleEdge edge) {
        Args.nullNotPermitted(edge, "edge");
        switch (edge) {
            case TOP:
                cursorUp(units);
                break;
            case BOTTOM:
                cursorDown(units);
                break;
            case LEFT:
                cursorLeft(units);
                break;
            case RIGHT:
                cursorRight(units);
                break;
            default:
                throw new IllegalStateException("Unexpected enum value " + edge);
        }
    }

    /**
     * Moves the cursor up by the specified number of Java 2D units.
     *
     * @param units the units.
     */
    public void cursorUp(double units) {
        this.cursor = this.cursor - units;
    }

    /**
     * Moves the cursor down by the specified number of Java 2D units.
     *
     * @param units the units.
     */
    public void cursorDown(double units) {
        this.cursor = this.cursor + units;
    }

    /**
     * Moves the cursor left by the specified number of Java 2D units.
     *
     * @param units the units.
     */
    public void cursorLeft(double units) {
        this.cursor = this.cursor - units;
    }

    /**
     * Moves the cursor right by the specified number of Java 2D units.
     *
     * @param units the units.
     */
    public void cursorRight(double units) {
        this.cursor = this.cursor + units;
    }

    /**
     * Returns the list of ticks.
     *
     * @return The list of ticks.
     */
    public List<ValueTick> getTicks() {
        return this.ticks;
    }

    /**
     * Sets the list of ticks.
     *
     * @param ticks the ticks.
     */
    public void setTicks(List<ValueTick> ticks) {
        this.ticks = ticks;
    }

    /**
     * Returns the maximum width/height.
     *
     * @return The maximum width/height.
     */
    public double getMax() {
        return this.max;
    }

    /**
     * Sets the maximum width/height.
     *
     * @param max the maximum width/height.
     */
    public void setMax(double max) {
        this.max = max;
    }
}
