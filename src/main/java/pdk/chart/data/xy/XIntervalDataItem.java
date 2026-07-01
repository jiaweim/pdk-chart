package pdk.chart.data.xy;

import pdk.chart.data.ComparableObjectItem;

/**
 * An item representing data in the form (x, x-low, x-high, y).
 */
public class XIntervalDataItem extends ComparableObjectItem {

    /**
     * Creates a new instance of {@code XIntervalDataItem}.
     *
     * @param x     the x-value.
     * @param xLow  the lower bound of the x-interval.
     * @param xHigh the upper bound of the x-interval.
     * @param y     the y-value.
     */
    public XIntervalDataItem(double x, double xLow, double xHigh, double y) {
        super(x, new YWithXInterval(y, xLow, xHigh));
    }

    /**
     * Returns the x-value.
     *
     * @return The x-value (never {@code null}).
     */
    public Number getX() {
        return (Number) getComparable();
    }

    /**
     * Returns the y-value.
     *
     * @return The y-value.
     */
    public double getYValue() {
        YWithXInterval interval = (YWithXInterval) getObject();
        if (interval != null) {
            return interval.getY();
        } else {
            return Double.NaN;
        }
    }

    /**
     * Returns the lower bound of the x-interval.
     *
     * @return The lower bound of the x-interval.
     */
    public double getXLowValue() {
        YWithXInterval interval = (YWithXInterval) getObject();
        if (interval != null) {
            return interval.getXLow();
        } else {
            return Double.NaN;
        }
    }

    /**
     * Returns the upper bound of the x-interval.
     *
     * @return The upper bound of the x-interval.
     */
    public double getXHighValue() {
        YWithXInterval interval = (YWithXInterval) getObject();
        if (interval != null) {
            return interval.getXHigh();
        } else {
            return Double.NaN;
        }
    }

}
