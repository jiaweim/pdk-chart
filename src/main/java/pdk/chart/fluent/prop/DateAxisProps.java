package pdk.chart.fluent.prop;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.Chart;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.fluent.XYChart;

import java.text.DateFormat;

/**
 * A class for configuring properties of DateAxis, designed with a fluent style API.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 1:56 PM
 */
public class DateAxisProps {

    protected final Chart chart_;
    private final DateAxis axis_;

    public DateAxisProps(Chart chart, DateAxis axis) {
        this.chart_ = chart;
        this.axis_ = axis;
    }

    /**
     * Return the {@link Chart} the axis belongs to.
     *
     * @return {@link Chart}.
     */
    public XYChart doneXY() {
        return (XYChart) chart_;
    }

    /**
     * Sets the label for the axis.
     *
     * @param name the new axis name.
     */
    public DateAxisProps name(@Nullable String name) {
        axis_.setLabel(name);
        return this;
    }

    /**
     * Sets the tick mark position (start, middle or end of the time period).
     *
     * @param position the position ({@code null} not permitted).
     */
    public DateAxisProps tickMarkPosition(@NonNull DateTickMarkPosition position) {
        axis_.setTickMarkPosition(position);
        return this;
    }

    /**
     * Sets the lower margin for the axis (as a percentage of the axis range).
     * <p>
     * This margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     *
     * @param margin the margin percentage (for example, 0.05 is five percent).
     */
    public DateAxisProps lowerMargin(double margin) {
        axis_.setLowerMargin(margin);
        return this;
    }

    /**
     * Sets the upper margin for the axis (as a percentage of the axis range).
     * <p>
     * This margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     *
     * @param margin the margin percentage (for example, 0.05 is five percent).
     */
    public DateAxisProps upperMargin(double margin) {
        axis_.setUpperMargin(margin);
        return this;
    }

    /**
     * Sets the direction of values on the axis.
     *
     * @param flag the flag.
     */
    public DateAxisProps inverted(boolean flag) {
        axis_.setInverted(flag);
        return this;
    }

    /**
     * Sets the date format override.
     * <p>
     * If this is non-null, then it will be
     * used to format the dates on the axis.
     *
     * @param formatter the date formatter ({@code null} permitted).
     */
    public DateAxisProps dateFormatOverride(DateFormat formatter) {
        axis_.setDateFormatOverride(formatter);
        return this;
    }
}
