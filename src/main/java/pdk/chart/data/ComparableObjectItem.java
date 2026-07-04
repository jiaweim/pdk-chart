package pdk.chart.data;

import pdk.chart.util.Args;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents one (Comparable, Object) data item for use in a
 * {@link ComparableObjectSeries}.
 */
public class ComparableObjectItem implements Comparable<ComparableObjectItem>,
        Cloneable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 2751513470325494890L;

    /**
     * The x-value.
     */
    private Comparable x;

    /**
     * The y-value.
     */
    private Object obj;

    /**
     * Constructs a new data item.
     *
     * @param x the x-value ({@code null} NOT permitted).
     * @param y the y-value ({@code null} permitted).
     */
    public ComparableObjectItem(Comparable x, Object y) {
        Args.nullNotPermitted(x, "x");
        this.x = x;
        this.obj = y;
    }

    /**
     * Returns the x-value.
     *
     * @return The x-value (never {@code null}).
     */
    protected Comparable getComparable() {
        return this.x;
    }

    /**
     * Returns the y-value.
     *
     * @return The y-value (possibly {@code null}).
     */
    protected Object getObject() {
        return this.obj;
    }

    /**
     * Sets the y-value for this data item.  Note that there is no
     * corresponding method to change the x-value.
     *
     * @param y the new y-value ({@code null} permitted).
     */
    protected void setObject(Object y) {
        this.obj = y;
    }

    /**
     * Returns an integer indicating the order of this object relative to
     * another object.
     * <p>
     * For the order we consider only the x-value:
     * negative == "less-than", zero == "equal", positive == "greater-than".
     *
     * @param other the object being compared to.
     * @return An integer indicating the order of this data pair object
     * relative to another object.
     */
    @Override
    public int compareTo(ComparableObjectItem other) {
        return this.x.compareTo(other.getComparable());
    }

    /**
     * Returns a clone of this object.
     *
     * @return A clone.
     * @throws CloneNotSupportedException not thrown by this class, but
     *                                    subclasses may differ.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
        if (!(obj instanceof ComparableObjectItem)) {
            return false;
        }
        ComparableObjectItem that = (ComparableObjectItem) obj;
        if (!this.x.equals(that.x)) {
            return false;
        }
        if (!Objects.equals(this.obj, that.obj)) {
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
        result = 29 * result + (this.obj != null ? this.obj.hashCode() : 0);
        return result;
    }

}
