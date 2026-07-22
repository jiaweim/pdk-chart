package pdk.chart.renderer;

import org.jspecify.annotations.NonNull;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.PaintUtils;
import pdk.chart.util.SerialUtils;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A paint scale that uses a lookup table to associate paint instances
 * with data value ranges.
 */
public class LookupPaintScale
        implements PaintScale, PublicCloneable, Serializable {

    /**
     * Stores the paint for a value.
     */
    static class PaintItem implements Comparable<PaintItem>, Serializable {

        /**
         * For serialization.
         */
        static final long serialVersionUID = 698920578512361570L;

        /**
         * The value.
         */
        double value;

        /**
         * The paint.
         */
        transient Paint paint;

        /**
         * Creates a new instance.
         *
         * @param value the value.
         * @param paint the paint.
         */
        public PaintItem(double value, Paint paint) {
            this.value = value;
            this.paint = paint;
        }

        /**
         * Compares this item to an arbitrary object.
         *
         * @param that the object.
         * @return An int defining the relative order of the objects.
         */
        @Override
        public int compareTo(PaintItem that) {
            double d1 = this.value;
            double d2 = that.value;
            if (d1 > d2) {
                return 1;
            }
            if (d1 < d2) {
                return -1;
            }
            return 0;
        }

        /**
         * Tests this item for equality with an arbitrary object.
         *
         * @param obj the object ({@code null} permitted).
         * @return A boolean.
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PaintItem that)) {
                return false;
            }
            if (this.value != that.value) {
                return false;
            }
            if (!PaintUtils.equal(this.paint, that.paint)) {
                return false;
            }
            return true;
        }

        /**
         * Provides serialization support.
         *
         * @param stream the output stream.
         * @throws IOException if there is an I/O error.
         */
        @Serial
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            SerialUtils.writePaint(this.paint, stream);
        }

        /**
         * Provides serialization support.
         *
         * @param stream the input stream.
         * @throws IOException            if there is an I/O error.
         * @throws ClassNotFoundException if there is a classpath problem.
         */
        private void readObject(ObjectInputStream stream)
                throws IOException, ClassNotFoundException {
            stream.defaultReadObject();
            this.paint = SerialUtils.readPaint(stream);
        }

    }

    /**
     * For serialization.
     */
    static final long serialVersionUID = -5239384246251042006L;

    /**
     * The lower bound.
     */
    private double lowerBound;

    /**
     * The upper bound.
     */
    private double upperBound;

    /**
     * The default paint.
     */
    private transient Paint defaultPaint;

    /**
     * The lookup table.
     */
    private List<PaintItem> lookupTable;

    /**
     * Creates a new paint scale.
     */
    public LookupPaintScale() {
        this(0.0, 1.0, Color.LIGHT_GRAY);
    }

    /**
     * Creates a new paint scale with the specified default paint.
     *
     * @param lowerBound   the lower bound.
     * @param upperBound   the upper bound.
     * @param defaultPaint the default paint.
     */
    public LookupPaintScale(double lowerBound, double upperBound,
            @NonNull Paint defaultPaint) {
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException(
                    "Requires lowerBound < upperBound.");
        }
        Objects.requireNonNull(defaultPaint);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.defaultPaint = defaultPaint;
        this.lookupTable = new ArrayList<>();
    }

    /**
     * Returns the default paint (never {@code null}).
     *
     * @return The default paint.
     */
    public Paint getDefaultPaint() {
        return this.defaultPaint;
    }

    /**
     * Returns the lower bound.
     *
     * @return The lower bound.
     * @see #getUpperBound()
     */
    @Override
    public double getLowerBound() {
        return this.lowerBound;
    }

    /**
     * Returns the upper bound.
     *
     * @return The upper bound.
     * @see #getLowerBound()
     */
    @Override
    public double getUpperBound() {
        return this.upperBound;
    }

    /**
     * Adds an entry to the lookup table.  Any values from {@code n} up
     * to but not including the next value in the table take on the specified
     * {@code Paint}.
     *
     * @param value the data value.
     * @param paint the paint.
     */
    public void add(double value, Paint paint) {
        PaintItem item = new PaintItem(value, paint);
        int index = Collections.binarySearch(this.lookupTable, item);
        if (index >= 0) {
            this.lookupTable.set(index, item);
        } else {
            this.lookupTable.add(-(index + 1), item);
        }
    }

    /**
     * Returns the paint associated with the specified value.
     *
     * @param value the value.
     * @return The paint.
     * @see #getDefaultPaint()
     */
    @Override
    public Paint getPaint(double value) {

        // handle value outside bounds...
        if (value < this.lowerBound) {
            return this.defaultPaint;
        }
        if (value > this.upperBound) {
            return this.defaultPaint;
        }

        int count = this.lookupTable.size();
        if (count == 0) {
            return this.defaultPaint;
        }

        // handle special case where value is less that item zero
        PaintItem item = this.lookupTable.get(0);
        if (value < item.value) {
            return this.defaultPaint;
        }

        // for value in bounds, do the lookup...
        int low = 0;
        int high = this.lookupTable.size() - 1;
        while (high - low > 1) {
            int current = (low + high) / 2;
            item = this.lookupTable.get(current);
            if (value >= item.value) {
                low = current;
            } else {
                high = current;
            }
        }
        if (high > low) {
            item = this.lookupTable.get(high);
            if (value < item.value) {
                item = this.lookupTable.get(low);
            }
        }
        return (item != null ? item.paint : this.defaultPaint);
    }


    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LookupPaintScale)) {
            return false;
        }
        LookupPaintScale that = (LookupPaintScale) obj;
        if (this.lowerBound != that.lowerBound) {
            return false;
        }
        if (this.upperBound != that.upperBound) {
            return false;
        }
        if (!PaintUtils.equal(this.defaultPaint, that.defaultPaint)) {
            return false;
        }
        if (!this.lookupTable.equals(that.lookupTable)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a clone of the instance.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if there is a problem cloning the
     *                                    instance.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        LookupPaintScale clone = (LookupPaintScale) super.clone();
        clone.lookupTable = new java.util.ArrayList(this.lookupTable);
        return clone;
    }

    /**
     * Provides serialization support.
     *
     * @param stream the output stream.
     * @throws IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtils.writePaint(this.defaultPaint, stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream the input stream.
     * @throws IOException            if there is an I/O error.
     * @throws ClassNotFoundException if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.defaultPaint = SerialUtils.readPaint(stream);
    }

}
