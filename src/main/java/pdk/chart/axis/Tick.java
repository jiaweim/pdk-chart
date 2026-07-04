package pdk.chart.axis;

import pdk.chart.util.Args;
import pdk.chart.text.TextAnchor;

import java.io.Serializable;
import java.util.Objects;

/**
 * The base class used to represent labeled ticks along an axis.
 */
public abstract class Tick implements Serializable, Cloneable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 6668230383875149773L;

    /**
     * A text version of the tick value.
     */
    private String text;

    /**
     * The text anchor for the tick label.
     */
    private TextAnchor textAnchor;

    /**
     * The rotation anchor for the tick label.
     */
    private TextAnchor rotationAnchor;

    /**
     * The rotation angle.
     */
    private double angle;

    /**
     * Creates a new tick.
     *
     * @param text           the formatted version of the tick value.
     * @param textAnchor     the text anchor ({@code null} not permitted).
     * @param rotationAnchor the rotation anchor ({@code null} not
     *                       permitted).
     * @param angle          the angle.
     */
    public Tick(String text, TextAnchor textAnchor, TextAnchor rotationAnchor,
            double angle) {
        Args.nullNotPermitted(textAnchor, "textAnchor");
        Args.nullNotPermitted(rotationAnchor, "rotationAnchor");
        this.text = text;
        this.textAnchor = textAnchor;
        this.rotationAnchor = rotationAnchor;
        this.angle = angle;
    }

    /**
     * Returns the text version of the tick value.
     *
     * @return A string (possibly {@code null});
     */
    public String getText() {
        return this.text;
    }

    /**
     * Returns the text anchor.
     *
     * @return The text anchor (never {@code null}).
     */
    public TextAnchor getTextAnchor() {
        return this.textAnchor;
    }

    /**
     * Returns the text anchor that defines the point around which the label is
     * rotated.
     *
     * @return A text anchor (never {@code null}).
     */
    public TextAnchor getRotationAnchor() {
        return this.rotationAnchor;
    }

    /**
     * Returns the angle.
     *
     * @return The angle.
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * Tests this tick for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Tick) {
            Tick t = (Tick) obj;
            if (!Objects.equals(this.text, t.text)) {
                return false;
            }
            if (!Objects.equals(this.textAnchor, t.textAnchor)) {
                return false;
            }
            if (!Objects.equals(this.rotationAnchor, t.rotationAnchor)) {
                return false;
            }
            if (!(this.angle == t.angle)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns a clone of the tick.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Tick clone = (Tick) super.clone();
        return clone;
    }

    /**
     * Returns a string representation of the tick.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return this.text;
    }
}
