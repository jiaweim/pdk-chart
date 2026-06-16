package pdk.chart.fluent.prop;

import org.jspecify.annotations.NonNull;
import pdk.chart.Chart;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.fluent.XYChart;

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
}
