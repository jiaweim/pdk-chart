package pdk.chart.renderer;

import org.jspecify.annotations.NonNull;
import pdk.chart.api.PublicCloneable;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * A continuous gradient paint scale based on color interpolation.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 22 Jul 2026, 9:34 AM
 */
public class GradientPaintScale
        implements PaintScale, PublicCloneable, Serializable {

    /**
     * The lower bound.
     */
    private final double lowerBound;
    /**
     * The upper bound.
     */
    private final double upperBound;
    /**
     * Paint returned when no interpolation is possible.
     */
    private transient Paint defaultPaint;
    /**
     * Sorted color stops.
     */
    private ArrayList<PaintItem> lookupTable;

    /**
     * Creates a new paint scale.
     */
    public GradientPaintScale() {
        this(0.0, 1.0, Color.LIGHT_GRAY);
    }

    /**
     * Creates a new paint scale with the specified default paint.
     *
     * @param lowerBound   the lower bound.
     * @param upperBound   the upper bound.
     * @param defaultPaint the default paint.
     */
    public GradientPaintScale(double lowerBound, double upperBound,
            @NonNull Paint defaultPaint) {
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("Requires lowerBound < upperBound.");
        }

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.defaultPaint = Objects.requireNonNull(defaultPaint, "defaultPaint must not be null.");
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
     * Adds a color stop.
     *
     * @param value the data value.
     * @param paint the paint.
     */
    public void add(double value, Paint paint) {
        Objects.requireNonNull(paint, "Paint must not be null.");

        PaintItem item = new PaintItem(value, paint);
        int index = Collections.binarySearch(this.lookupTable, item);
        if (index >= 0) {
            this.lookupTable.set(index, item);
        } else {
            this.lookupTable.add(-(index + 1), item);
        }
    }

    @Override
    public double getLowerBound() {
        return lowerBound;
    }

    @Override
    public double getUpperBound() {
        return upperBound;
    }

    @Override
    public Paint getPaint(double value) {
        int size = lookupTable.size();
        if (size == 0) {
            return defaultPaint;
        }

        PaintItem first = lookupTable.get(0);
        PaintItem last = lookupTable.get(size - 1);

        if (value <= first.getValue()) {
            return first.getPaint();
        }
        if (value >= last.getValue()) {
            return last.getPaint();
        }
        int index = findIndex(value);
        // exact match
        if (index >= 0) {
            return lookupTable.get(index).getPaint();
        }

        int rightIndex = -(index + 1);
        PaintItem right = lookupTable.get(rightIndex);
        PaintItem left = lookupTable.get(rightIndex - 1);
        Paint p1 = left.getPaint();
        Paint p2 = right.getPaint();

        // only support color
        if (!(p1 instanceof Color c1) || !(p2 instanceof Color c2)) {
            return p1;
        }

        double fraction = (value - left.getValue())
                / (right.getValue() - left.getValue());

        return interpolate(c1, c2, fraction);
    }

    private Color interpolate(Color c1, Color c2,
            double fraction) {

        fraction = Math.clamp(fraction, 0, 1);

        int r = (int) (c1.getRed() + fraction *
                (c2.getRed() - c1.getRed()));

        int g = (int) (c1.getGreen() + fraction *
                (c2.getGreen() - c1.getGreen()));

        int b = (int) (c1.getBlue() + fraction *
                (c2.getBlue() - c1.getBlue()));

        int a = (int) (c1.getAlpha() + fraction *
                (c2.getAlpha() - c1.getAlpha()));

        return new Color(r, g, b, a);
    }


    private int findIndex(double value) {
        int low = 0;
        int high = lookupTable.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            double midValue = lookupTable.get(mid).getValue();

            if (midValue < value) {
                low = mid + 1;
            } else if (midValue > value) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -(low + 1);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GradientPaintScale clone =
                (GradientPaintScale) super.clone();
        clone.lookupTable = new ArrayList<>(this.lookupTable);
        return clone;
    }
}
