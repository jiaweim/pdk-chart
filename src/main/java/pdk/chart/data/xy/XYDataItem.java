package pdk.chart.data.xy;

import org.jspecify.annotations.NonNull;
import pdk.chart.util.Args;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents one (x, y) data item for an {@link XYSeries}.  Note that
 * subclasses are REQUIRED to support cloning.
 */
public class XYDataItem implements Cloneable, Comparable<XYDataItem>, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 2751513470325494890L;

    /**
     * The x-value ({@code null} not permitted).
     */
    private Number x;

    /**
     * The y-value.
     */
    private Number y;

    /**
     * Constructs a new data item.
     *
     * @param x the x-value ({@code null} NOT permitted).
     * @param y the y-value ({@code null} permitted).
     */
    public XYDataItem(@NonNull Number x, Number y) {
        Args.nullNotPermitted(x, "x");
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new data item.
     *
     * @param x the x-value.
     * @param y the y-value.
     */
    public XYDataItem(double x, double y) {
        this(Double.valueOf(x), Double.valueOf(y));
    }

    /**
     * Returns the x-value.
     *
     * @return The x-value (never {@code null}).
     */
    public Number getX() {
        return this.x;
    }

    /**
     * Returns the x-value as a double primitive.
     *
     * @return The x-value.
     * @see #getX()
     * @see #getYValue()
     */
    public double getXValue() {
        // this.x is not allowed to be null...
        return this.x.doubleValue();
    }

    /**
     * Returns the y-value.
     *
     * @return The y-value (possibly {@code null}).
     */
    public Number getY() {
        return this.y;
    }

    /**
     * Returns the y-value as a double primitive.
     *
     * @return The y-value.
     * @see #getY()
     * @see #getXValue()
     */
    public double getYValue() {
        double result = Double.NaN;
        if (this.y != null) {
            result = this.y.doubleValue();
        }
        return result;
    }

    /**
     * Sets the y-value for this data item.  Note that there is no
     * corresponding method to change the x-value.
     *
     * @param y the new y-value.
     */
    public void setY(double y) {
        setY(Double.valueOf(y));
    }

    /**
     * Sets the y-value for this data item.  Note that there is no
     * corresponding method to change the x-value.
     *
     * @param y the new y-value ({@code null} permitted).
     */
    public void setY(Number y) {
        this.y = y;
    }

    /**
     * Returns an integer indicating the order of this object relative to
     * another object.
     * <p>
     * For the order we consider only the x-value:
     * negative == "less-than", zero == "equal", positive == "greater-than".
     *
     * @param other the data item being compared to.
     * @return An integer indicating the order of this data pair object
     * relative to another object.
     */
    @Override
    public int compareTo(XYDataItem other) {
        int result;
        double compare = this.x.doubleValue() - other.getX().doubleValue();
        result = Double.compare(compare, 0.0);
        return result;
    }

    /**
     * Returns a clone of this object.
     *
     * @return A clone.
     */
    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) { // won't get here...
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * Tests if this object is equal to another.
     *
     * @param obj the object to test against for equality ({@code null}
     *            permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYDataItem)) {
            return false;
        }
        XYDataItem that = (XYDataItem) obj;
        if (!this.x.equals(that.x)) {
            return false;
        }
        if (!Objects.equals(this.y, that.y)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int result;
        result = this.x.hashCode();
        result = 29 * result + (this.y != null ? this.y.hashCode() : 0);
        return result;
    }

    /**
     * Returns a string representing this instance, primarily for debugging
     * use.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[" + getXValue() + ", " + getYValue() + "]";
    }

}
