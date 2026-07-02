package pdk.chart.data.time.ohlc;

import pdk.chart.data.ComparableObjectItem;
import pdk.chart.data.ComparableObjectSeries;
import pdk.chart.data.time.RegularTimePeriod;

import java.util.Objects;

/**
 * A list of ({@link RegularTimePeriod}, open, high, low, close) data items.
 *
 * @param <S> the series key type.
 * @see OHLCSeriesCollection
 */
public class OHLCSeries<S extends Comparable<S>> extends ComparableObjectSeries<S> {

    /**
     * Creates a new empty series.  By default, items added to the series will
     * be sorted into ascending order by period, and duplicate periods will
     * not be allowed.
     *
     * @param key the series key ({@code null} not permitted).
     */
    public OHLCSeries(S key) {
        super(key, true, false);
    }

    /**
     * Returns the time period for the specified item.
     *
     * @param index the item index.
     * @return The time period.
     */
    public RegularTimePeriod getPeriod(int index) {
        OHLCItem item = (OHLCItem) getDataItem(index);
        return item.getPeriod();
    }

    /**
     * Returns the data item at the specified index.
     *
     * @param index the item index.
     * @return The data item.
     */
    @Override
    public ComparableObjectItem getDataItem(int index) {
        return super.getDataItem(index);
    }

    /**
     * Adds a data item to the series.
     *
     * @param period the period.
     * @param open   the open-value.
     * @param high   the high-value.
     * @param low    the low-value.
     * @param close  the close-value.
     */
    public void add(RegularTimePeriod period, double open, double high,
            double low, double close) {
        if (getItemCount() > 0) {
            OHLCItem item0 = (OHLCItem) this.getDataItem(0);
            if (!period.getClass().equals(item0.getPeriod().getClass())) {
                throw new IllegalArgumentException(
                        "Can't mix RegularTimePeriod class types.");
            }
        }
        super.add(new OHLCItem(period, open, high, low, close), true);
    }

    /**
     * Adds a data item to the series.  The values from the item passed to
     * this method will be copied into a new object.
     *
     * @param item the item ({@code null} not permitted).
     */
    public void add(OHLCItem item) {
        Objects.requireNonNull(item, "item");
        add(item.getPeriod(), item.getOpenValue(), item.getHighValue(),
                item.getLowValue(), item.getCloseValue());
    }

    /**
     * Removes the item with the specified index.
     *
     * @param index the item index.
     * @return The item removed.
     * @since 1.0.14
     */
    @Override
    public ComparableObjectItem remove(int index) {
        return super.remove(index);
    }

}
