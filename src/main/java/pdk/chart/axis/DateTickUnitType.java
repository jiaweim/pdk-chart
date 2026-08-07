package pdk.chart.axis;

import java.util.Calendar;

/**
 * An enumeration of the unit types for a {@link DateTickUnit} instance.
 */
public enum DateTickUnitType {

    /**
     * Year.
     */
    YEAR(Calendar.YEAR),

    /**
     * Month.
     */
    MONTH(Calendar.MONTH),

    /**
     * Day.
     */
    DAY(Calendar.DATE),

    /**
     * Hour.
     */
    HOUR(Calendar.HOUR_OF_DAY),

    /**
     * Minute.
     */
    MINUTE(Calendar.MINUTE),

    /**
     * Second.
     */
    SECOND(Calendar.SECOND),

    /**
     * Millisecond.
     */
    MILLISECOND(Calendar.MILLISECOND);

    /**
     * The corresponding field value in Java's Calendar class.
     */
    private int calendarField;

    /**
     * Private constructor.
     *
     * @param calendarField the calendar field.
     */
    DateTickUnitType(int calendarField) {
        this.calendarField = calendarField;
    }

    /**
     * Returns the calendar field.
     *
     * @return The calendar field.
     */
    public int getCalendarField() {
        return this.calendarField;
    }

}
