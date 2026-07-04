package pdk.chart.axis;

import pdk.chart.util.Args;
import pdk.chart.text.TextAnchor;

import java.util.Date;
import java.util.Objects;

/**
 * A tick used by the {@link DateAxis} class.
 */
public class DateTick extends ValueTick {

    /**
     * The date.
     */
    private Date date;

    /**
     * Creates a new date tick.
     *
     * @param date           the date.
     * @param label          the label.
     * @param textAnchor     the part of the label that is aligned to the anchor
     *                       point.
     * @param rotationAnchor defines the rotation point relative to the text.
     * @param angle          the rotation angle (in radians).
     */
    public DateTick(Date date, String label,
            TextAnchor textAnchor, TextAnchor rotationAnchor,
            double angle) {
        this(TickType.MAJOR, date, label, textAnchor, rotationAnchor, angle);
    }

    /**
     * Creates a new date tick.
     *
     * @param tickType       the tick type ({@code null} not permitted).
     * @param date           the date.
     * @param label          the label.
     * @param textAnchor     the part of the label that is aligned to the anchor
     *                       point.
     * @param rotationAnchor defines the rotation point relative to the text.
     * @param angle          the rotation angle (in radians).
     */
    public DateTick(TickType tickType, Date date, String label,
            TextAnchor textAnchor, TextAnchor rotationAnchor,
            double angle) {
        super(tickType, date.getTime(), label, textAnchor, rotationAnchor,
                angle);
        Args.nullNotPermitted(tickType, "tickType");
        this.date = date;
    }

    /**
     * Returns the date.
     *
     * @return The date.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Tests this tick for equality with an arbitrary object.
     *
     * @param obj the object to test ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DateTick)) {
            return false;
        }
        DateTick that = (DateTick) obj;
        if (!Objects.equals(this.date, that.date)) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns a hash code for this object.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        return this.date.hashCode();
    }

}
