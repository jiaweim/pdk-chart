package pdk.chart.fluent.prop;

import org.jspecify.annotations.Nullable;
import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.SymbolAxis;
import pdk.chart.axis.TickUnitSource;
import pdk.chart.event.AxisChangeEvent;
import pdk.chart.fluent.XYChart;

import java.awt.*;
import java.text.AttributedString;
import java.text.NumberFormat;

/**
 * A class for configuring properties of NumberAxis, designed with a fluent style API.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 9:12 AM
 */
public class NumberAxisProps {

    protected final XYChart chart_;
    private final NumberAxis axis_;

    public NumberAxisProps(XYChart chart, NumberAxis axis) {
        this.chart_ = chart;
        this.axis_ = axis;
    }

    /**
     * Return the {@link Chart} the axis belongs to.
     *
     * @return {@link Chart}.
     */
    public XYChart done() {
        return chart_;
    }

    /**
     * Set Axis title.
     *
     * @param axisTitle axis title.
     * @return this.
     */
    public NumberAxisProps name(String axisTitle) {
        axis_.setLabel(axisTitle);
        return this;
    }

    /**
     * Sets the flag that indicates whether the axis range, if
     * automatically calculated, is forced to include zero.
     * <p>
     * If the flag is changed to {@code true}, the axis range is
     * recalculated.
     *
     * @param axisIncludesZero the new value of the flag.
     */
    public NumberAxisProps autoRangeIncludesZero(boolean axisIncludesZero) {
        axis_.setAutoRangeIncludesZero(axisIncludesZero);
        return this;
    }

    /**
     * Sets the source for obtaining standard tick units for the axis.
     * <p>
     * The axis will try to select the smallest tick unit from the source that does not cause
     * the tick labels to overlap.
     *
     * @param source the source for standard tick units.
     */
    public NumberAxisProps standardTickUnits(@Nullable TickUnitSource source) {
        axis_.setStandardTickUnits(source);
        return this;
    }

    /**
     * Sets the range for the axis.
     * <p>
     * As a side effect, the auto-range flag is set to {@code false}.
     *
     * @param lower the lower axis limit.
     * @param upper the upper axis limit.
     */
    public NumberAxisProps range(double lower, double upper) {
        axis_.setRange(lower, upper);
        return this;
    }


    /**
     * Sets the flag that indicates whether the minor tick marks are
     * showing.
     *
     * @param flag the flag.
     */
    public NumberAxisProps minorTickMarksVisible(boolean flag) {
        axis_.setMinorTickMarksVisible(flag);
        return this;
    }

    /**
     * Sets the number of minor tick marks to display.
     * <p>
     * 2 means dividing the interval between two adjacent major ticks into two segments and inserting one minor tick.
     *
     * @param count the count.
     */
    public NumberAxisProps minorTickCount(int count) {
        axis_.setMinorTickCount(count);
        return this;
    }

    /**
     * Sets the inside length of the tick marks.
     *
     * @param length the new length.
     */
    public NumberAxisProps tickMarkInsideLength(float length) {
        axis_.setTickMarkInsideLength(length);
        return this;
    }

    /**
     * Sets the outside length of the tick marks.
     *
     * @param length the new length.
     */
    public NumberAxisProps tickMarkOutsideLength(float length) {
        axis_.setTickMarkOutsideLength(length);
        return this;
    }

    /**
     * Sets the auto range minimum size.
     * <p>
     * The minimum display range of the axis values.
     *
     * @param size the size.
     */
    public NumberAxisProps autoRangeMinimumSize(double size) {
        axis_.setAutoRangeMinimumSize(size, false);
        return this;
    }

    /**
     * Sets the lower margin for the axis (as a percentage of the axis range)
     * and sends an {@link AxisChangeEvent} to all registered listeners.  This
     * margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     *
     * @param margin the margin percentage (for example, 0.05 is five percent).
     */
    public NumberAxisProps lowerMargin(double margin) {
        axis_.setLowerMargin(margin);
        return this;
    }

    /**
     * Sets the upper margin for the axis (as a percentage of the axis range)
     * and sends an {@link AxisChangeEvent} to all registered listeners.
     * <p>
     * This margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     *
     * @param margin the margin percentage (for example, 0.05 is five percent).
     */
    public NumberAxisProps upperMargin(double margin) {
        axis_.setUpperMargin(margin);
        return this;
    }

    /**
     * Sets  whether the axis is visible.
     *
     * @param flag the flag.
     */
    public NumberAxisProps visible(boolean flag) {
        axis_.setVisible(flag);
        return this;
    }

    /**
     * Sets the paint used to draw tick marks.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public NumberAxisProps tickMarkPaint(Paint paint) {
        axis_.setTickMarkPaint(paint);
        return this;
    }

    /**
     * Sets the number format override.  If this is non-null, then it will be
     * used to format the numbers on the axis.
     *
     * @param formatter the number formatter.
     */
    public NumberAxisProps numberFormatOverride(@Nullable NumberFormat formatter) {
        axis_.setNumberFormatOverride(formatter);
        return this;
    }

    /**
     * Sets the direction of values on the axis.
     *
     * @param flag the flag.
     */
    public NumberAxisProps inverted(boolean flag) {
        axis_.setInverted(flag);
        return this;
    }

    /**
     * Sets the attributed label for the axis.
     *
     * @param label the label ({@code null} permitted).
     */
    public NumberAxisProps attributedLabel(AttributedString label) {
        axis_.setAttributedLabel(label);
        return this;
    }


    /**
     * Sets the list of symbols to display instead of the numeric values.
     *
     * @param symbols symbols List of symbols.
     * @return this.
     */
    public NumberAxisProps symbols(String[] symbols) {
        if (axis_ instanceof SymbolAxis symbolAxis) {
            symbolAxis.setSymbols(symbols);
        }
        return this;
    }

    /**
     * Sets the flag that controls whether grid bands are drawn for this
     * axis and notifies registered listeners that the axis has been modified.
     * <p>
     * Each band is the area between two adjacent gridlines
     * running perpendicular to the axis.  When the bands are drawn they are
     * filled with the colors {@link #getGridBandPaint()} and
     * {@link #getGridBandAlternatePaint()} in an alternating sequence.
     *
     * @param flag the new setting.
     */
    public NumberAxisProps gridBandsVisible(boolean flag) {
        if (axis_ instanceof SymbolAxis symbolAxis) {
            symbolAxis.setGridBandsVisible(flag);
        }
        return this;
    }
}
