package pdk.chart.data.xy;

import pdk.chart.data.ComparableObjectItem;
import pdk.chart.data.ComparableObjectSeries;
import pdk.chart.data.general.SeriesChangeEvent;

/**
 * A list of (x,y, deltaX, deltaY) data items.
 *
 * @param <S> the series key type.
 * @see VectorSeriesCollection
 */
public class VectorSeries<S extends Comparable<S>> extends ComparableObjectSeries<S> {

    /**
     * Creates a new empty series.
     *
     * @param key the series key ({@code null} not permitted).
     */
    public VectorSeries(S key) {
        this(key, false, true);
    }

    /**
     * Constructs a new series that contains no data.  You can specify
     * whether duplicate x-values are allowed for the series.
     *
     * @param key                   the series key ({@code null} not permitted).
     * @param autoSort              a flag that controls whether the items in the
     *                              series are sorted.
     * @param allowDuplicateXValues a flag that controls whether duplicate
     *                              x-values are allowed.
     */
    public VectorSeries(S key, boolean autoSort, boolean allowDuplicateXValues) {
        super(key, autoSort, allowDuplicateXValues);
    }

    /**
     * Adds a data item to the series.
     *
     * @param x      the x-value.
     * @param y      the y-value.
     * @param deltaX the vector x.
     * @param deltaY the vector y.
     */
    public void add(double x, double y, double deltaX, double deltaY) {
        add(new VectorDataItem(x, y, deltaX, deltaY), true);
    }

    /**
     * Adds a data item to the series and, if requested, sends a
     * {@link SeriesChangeEvent} to all registered listeners.
     *
     * @param item   the data item ({@code null} not permitted).
     * @param notify notify listeners?
     * @since 1.0.18
     */
    public void add(VectorDataItem item, boolean notify) {
        super.add(item, notify);
    }

    /**
     * Removes the item at the specified index and sends a
     * {@link SeriesChangeEvent} to all registered listeners.
     *
     * @param index the index.
     * @return The item removed.
     */
    @Override
    public ComparableObjectItem remove(int index) {
        VectorDataItem result = (VectorDataItem) this.data.remove(index);
        fireSeriesChanged();
        return result;
    }

    /**
     * Returns the x-value for the specified item.
     *
     * @param index the item index.
     * @return The x-value.
     */
    public double getXValue(int index) {
        VectorDataItem item = (VectorDataItem) this.getDataItem(index);
        return item.getXValue();
    }

    /**
     * Returns the y-value for the specified item.
     *
     * @param index the item index.
     * @return The y-value.
     */
    public double getYValue(int index) {
        VectorDataItem item = (VectorDataItem) getDataItem(index);
        return item.getYValue();
    }

    /**
     * Returns the x-component of the vector for an item in the series.
     *
     * @param index the item index.
     * @return The x-component of the vector.
     */
    public double getVectorXValue(int index) {
        VectorDataItem item = (VectorDataItem) getDataItem(index);
        return item.getVectorX();
    }

    /**
     * Returns the y-component of the vector for an item in the series.
     *
     * @param index the item index.
     * @return The y-component of the vector.
     */
    public double getVectorYValue(int index) {
        VectorDataItem item = (VectorDataItem) getDataItem(index);
        return item.getVectorY();
    }

    /**
     * Returns the data item at the specified index.
     *
     * @param index the item index.
     * @return The data item.
     */
    @Override
    public ComparableObjectItem getDataItem(int index) {
        // overridden to make public
        return super.getDataItem(index);
    }

}
