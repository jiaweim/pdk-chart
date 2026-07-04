package pdk.chart.data.xy;

import pdk.chart.util.Args;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a single (open-high-low-close) data item in
 * an {@link DefaultOHLCDataset}.  This data item is commonly used
 * to summarise the trading activity of a financial commodity for
 * a fixed period (most often one day).
 */
public class OHLCDataItem implements Comparable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 7753817154401169901L;

    /**
     * The date.
     */
    private Date date;

    /**
     * The open value.
     */
    private Number open;

    /**
     * The high value.
     */
    private Number high;

    /**
     * The low value.
     */
    private Number low;

    /**
     * The close value.
     */
    private Number close;

    /**
     * The trading volume (number of shares, contracts or whatever).
     */
    private Number volume;

    /**
     * Creates a new item.
     *
     * @param date   the date ({@code null} not permitted).
     * @param open   the open value.
     * @param high   the high value.
     * @param low    the low value.
     * @param close  the close value.
     * @param volume the volume.
     */
    public OHLCDataItem(Date date, double open, double high, double low,
            double close, double volume) {
        Args.nullNotPermitted(date, "date");
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    /**
     * Returns the date that the data item relates to.
     *
     * @return The date (never {@code null}).
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Returns the open value.
     *
     * @return The open value (never {@code null}).
     */
    public Number getOpen() {
        return this.open;
    }

    /**
     * Returns the high value.
     *
     * @return The high value (never {@code null}).
     */
    public Number getHigh() {
        return this.high;
    }

    /**
     * Returns the low value.
     *
     * @return The low value (never {@code null}).
     */
    public Number getLow() {
        return this.low;
    }

    /**
     * Returns the close value.
     *
     * @return The close value (never {@code null}).
     */
    public Number getClose() {
        return this.close;
    }

    /**
     * Returns the volume.
     *
     * @return The volume (never {@code null}).
     */
    public Number getVolume() {
        return this.volume;
    }

    /**
     * Checks this instance for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OHLCDataItem)) {
            return false;
        }
        OHLCDataItem that = (OHLCDataItem) obj;
        if (!this.date.equals(that.date)) {
            return false;
        }
        if (!this.high.equals(that.high)) {
            return false;
        }
        if (!this.low.equals(that.low)) {
            return false;
        }
        if (!this.open.equals(that.open)) {
            return false;
        }
        if (!this.close.equals(that.close)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param object the object to compare to.
     * @return A negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object object) {
        if (object instanceof OHLCDataItem) {
            OHLCDataItem item = (OHLCDataItem) object;
            return this.date.compareTo(item.date);
        } else {
            throw new ClassCastException("OHLCDataItem.compareTo().");
        }
    }

}
