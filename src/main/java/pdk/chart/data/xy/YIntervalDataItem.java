package pdk.chart.data.xy;

import pdk.chart.data.ComparableObjectItem;

/**
 * An item representing data in the form (x, y, y-low, y-high).
 */
public class YIntervalDataItem extends ComparableObjectItem {

    /**
     * Creates a new instance of {@code YIntervalDataItem}.
     *
     * @param x     the x-value.
     * @param y     the y-value.
     * @param yLow  the lower bound of the y-interval.
     * @param yHigh the upper bound of the y-interval.
     */
    public YIntervalDataItem(double x, double y, double yLow, double yHigh) {
        super(x, new YInterval(y, yLow, yHigh));
    }

    /**
     * Returns the x-value.
     *
     * @return The x-value (never {@code null}).
     */
    public Double getX() {
        return (Double) getComparable();
    }

    /**
     * Returns the y-value.
     *
     * @return The y-value.
     */
    public double getYValue() {
        YInterval interval = (YInterval) getObject();
        if (interval != null) {
            return interval.getY();
        } else {
            return Double.NaN;
        }
    }

    /**
     * Returns the lower bound of the y-interval.
     *
     * @return The lower bound of the y-interval.
     */
    public double getYLowValue() {
        YInterval interval = (YInterval) getObject();
        if (interval != null) {
            return interval.getYLow();
        } else {
            return Double.NaN;
        }
    }

    /**
     * Returns the upper bound of the y-interval.
     *
     * @return The upper bound of the y-interval.
     */
    public double getYHighValue() {
        YInterval interval = (YInterval) getObject();
        if (interval != null) {
            return interval.getYHigh();
        } else {
            return Double.NaN;
        }
    }

}
