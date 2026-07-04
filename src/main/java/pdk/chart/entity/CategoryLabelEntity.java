package pdk.chart.entity;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.util.HashUtils;

import java.awt.*;
import java.util.Objects;

/**
 * An entity to represent the labels on a {@link CategoryAxis}.
 *
 * @param <C> the type for the category keys.
 */
public class CategoryLabelEntity<C extends Comparable<C>> extends TickLabelEntity {

    /**
     * The category key.
     */
    private final C key;

    /**
     * Creates a new entity.
     *
     * @param key         the category key.
     * @param area        the hotspot.
     * @param toolTipText the tool tip text.
     * @param urlText     the URL text.
     */
    public CategoryLabelEntity(C key, Shape area, String toolTipText,
            String urlText) {
        super(area, toolTipText, urlText);
        this.key = key;
    }

    /**
     * Returns the category key.
     *
     * @return The category key.
     */
    public C getKey() {
        return this.key;
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
        if (!(obj instanceof CategoryLabelEntity)) {
            return false;
        }
        CategoryLabelEntity<C> that = (CategoryLabelEntity) obj;
        if (!Objects.equals(this.key, that.key)) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = HashUtils.hashCode(result, this.key);
        return result;
    }

    /**
     * Returns a string representation of this entity.  This is primarily
     * useful for debugging.
     *
     * @return A string representation of this entity.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CategoryLabelEntity: ");
        sb.append("category=");
        sb.append(this.key);
        sb.append(", tooltip=").append(getToolTipText());
        sb.append(", url=").append(getURLText());
        return sb.toString();
    }
}
