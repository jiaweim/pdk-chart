package pdk.chart.plot;

import pdk.chart.api.LengthAdjustmentType;
import pdk.chart.event.MarkerChangeEvent;
import pdk.chart.util.Args;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A marker for a category.
 * <br><br>
 * Note that for serialization to work correctly, the category key must be an
 * instance of a serializable class.
 *
 * @see CategoryPlot#addDomainMarker(CategoryMarker)
 */
public class CategoryMarker extends Marker implements Cloneable, Serializable {

    /**
     * The category key.
     */
    private Comparable<?> key;

    /**
     * A hint that the marker should be drawn as a line rather than a region.
     */
    private boolean drawAsLine = false;

    /**
     * Creates a new category marker for the specified category.
     *
     * @param key the category key.
     */
    public CategoryMarker(Comparable<?> key) {
        this(key, Color.GRAY, new BasicStroke(1.0f));
    }

    /**
     * Creates a new category marker.
     *
     * @param key    the key.
     * @param paint  the paint ({@code null} not permitted).
     * @param stroke the stroke ({@code null} not permitted).
     */
    public CategoryMarker(Comparable<?> key, Paint paint, Stroke stroke) {
        this(key, paint, stroke, paint, stroke, 1.0f);
    }

    /**
     * Creates a new category marker.
     *
     * @param key           the key.
     * @param paint         the paint ({@code null} not permitted).
     * @param stroke        the stroke ({@code null} not permitted).
     * @param outlinePaint  the outline paint ({@code null} permitted).
     * @param outlineStroke the outline stroke ({@code null} permitted).
     * @param alpha         the alpha transparency.
     */
    public CategoryMarker(Comparable<?> key, Paint paint, Stroke stroke,
            Paint outlinePaint, Stroke outlineStroke,
            float alpha) {
        super(paint, stroke, outlinePaint, outlineStroke, alpha);
        this.key = key;
        setLabelOffsetType(LengthAdjustmentType.EXPAND);
    }

    /**
     * Returns the key.
     *
     * @return The key.
     */
    public Comparable<?> getKey() {
        return this.key;
    }

    /**
     * Sets the key and sends a {@link MarkerChangeEvent} to all registered
     * listeners.
     *
     * @param key the key ({@code null} not permitted).
     */
    public void setKey(Comparable<?> key) {
        Args.nullNotPermitted(key, "key");
        this.key = key;
        notifyListeners(new MarkerChangeEvent(this));
    }

    /**
     * Returns the flag that controls whether the marker is drawn as a region
     * or a line.
     *
     * @return A line.
     */
    public boolean getDrawAsLine() {
        return this.drawAsLine;
    }

    /**
     * Sets the flag that controls whether the marker is drawn as a region or
     * as a line, and sends a {@link MarkerChangeEvent} to all registered
     * listeners.
     *
     * @param drawAsLine the flag.
     */
    public void setDrawAsLine(boolean drawAsLine) {
        this.drawAsLine = drawAsLine;
        notifyListeners(new MarkerChangeEvent(this));
    }

    /**
     * Tests the marker for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CategoryMarker)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        CategoryMarker that = (CategoryMarker) obj;
        if (!this.key.equals(that.key)) {
            return false;
        }
        if (this.drawAsLine != that.drawAsLine) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.key);
        hash = 89 * hash + (this.drawAsLine ? 1 : 0);
        return hash;
    }

}
