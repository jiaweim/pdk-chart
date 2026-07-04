package pdk.chart.data.xy;

import pdk.chart.api.PublicCloneable;
import pdk.chart.util.Args;
import pdk.chart.util.CloneUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a collection of {@link MatrixSeries} that can be used as a
 * dataset.
 *
 * @param <S> the series key type.
 * @see MatrixSeries
 */
public class MatrixSeriesCollection<S extends Comparable<S>>
        extends AbstractXYZDataset
        implements XYZDataset, PublicCloneable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = -3197705779242543945L;

    /**
     * The series that are included in the collection.
     */
    private List<MatrixSeries<S>> seriesList;

    /**
     * Constructs an empty dataset.
     */
    public MatrixSeriesCollection() {
        this(null);
    }


    /**
     * Constructs a dataset and populates it with a single matrix series.
     *
     * @param series the time series.
     */
    public MatrixSeriesCollection(MatrixSeries<S> series) {
        super();
        this.seriesList = new ArrayList<>();

        if (series != null) {
            this.seriesList.add(series);
            series.addChangeListener(this);
        }
    }

    /**
     * Returns the number of items in the specified series.
     *
     * @param seriesIndex zero-based series index.
     * @return The number of items in the specified series.
     */
    @Override
    public int getItemCount(int seriesIndex) {
        return getSeries(seriesIndex).getItemCount();
    }


    /**
     * Returns the series having the specified index.
     *
     * @param seriesIndex zero-based series index.
     * @return The series.
     */
    public MatrixSeries<S> getSeries(int seriesIndex) {
        Args.requireInRange(seriesIndex, "seriesIndex", 0, this.seriesList.size() - 1);
        return this.seriesList.get(seriesIndex);
    }


    /**
     * Returns the number of series in the collection.
     *
     * @return The number of series in the collection.
     */
    @Override
    public int getSeriesCount() {
        return this.seriesList.size();
    }


    /**
     * Returns the key for a series.
     *
     * @param seriesIndex zero-based series index.
     * @return The key for a series.
     */
    @Override
    public S getSeriesKey(int seriesIndex) {
        return getSeries(seriesIndex).getKey();
    }


    /**
     * Returns the j index value of the specified Mij matrix item in the
     * specified matrix series.
     *
     * @param seriesIndex zero-based series index.
     * @param itemIndex   zero-based item index.
     * @return The j index value for the specified matrix item.
     * @see XYDataset#getXValue(int, int)
     */
    @Override
    public Number getX(int seriesIndex, int itemIndex) {
        MatrixSeries series = this.seriesList.get(seriesIndex);
        return series.getItemColumn(itemIndex);
    }


    /**
     * Returns the i index value of the specified Mij matrix item in the
     * specified matrix series.
     *
     * @param seriesIndex zero-based series index.
     * @param itemIndex   zero-based item index.
     * @return The i index value for the specified matrix item.
     * @see XYDataset#getYValue(int, int)
     */
    @Override
    public Number getY(int seriesIndex, int itemIndex) {
        MatrixSeries series = this.seriesList.get(seriesIndex);
        return series.getItemRow(itemIndex);
    }


    /**
     * Returns the Mij item value of the specified Mij matrix item in the
     * specified matrix series.
     *
     * @param seriesIndex the series (zero-based index).
     * @param itemIndex   zero-based item index.
     * @return The Mij item value for the specified matrix item.
     * @see XYZDataset#getZValue(int, int)
     */
    @Override
    public Number getZ(int seriesIndex, int itemIndex) {
        MatrixSeries series = this.seriesList.get(seriesIndex);
        return series.getItem(itemIndex);
    }

    /**
     * Adds a series to the collection.
     * <P>
     * Notifies all registered listeners that the dataset has changed.
     * </p>
     *
     * @param series the series ({@code null} not permitted).
     */
    public void addSeries(MatrixSeries<S> series) {
        Args.nullNotPermitted(series, "series");
        // FIXME: Check that there isn't already a series with the same key

        // add the series...
        this.seriesList.add(series);
        series.addChangeListener(this);
        fireDatasetChanged();
    }


    /**
     * Tests this collection for equality with an arbitrary object.
     *
     * @param obj the object.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj instanceof MatrixSeriesCollection) {
            MatrixSeriesCollection<S> c = (MatrixSeriesCollection) obj;

            return Objects.equals(this.seriesList, c.seriesList);
        }

        return false;
    }

    /**
     * Returns a hash code.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        return (this.seriesList != null ? this.seriesList.hashCode() : 0);
    }

    /**
     * Returns a clone of this instance.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if there is a problem.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        MatrixSeriesCollection<S> clone = (MatrixSeriesCollection) super.clone();
        clone.seriesList = CloneUtils.cloneList(this.seriesList);
        return clone;
    }

    /**
     * Removes all the series from the collection.
     * <P>
     * Notifies all registered listeners that the dataset has changed.
     * </p>
     */
    public void removeAllSeries() {
        // Unregister the collection as a change listener to each series in
        // the collection.
        for (MatrixSeries series : this.seriesList) {
            series.removeChangeListener(this);
        }

        // Remove all the series from the collection and notify listeners.
        this.seriesList.clear();
        fireDatasetChanged();
    }


    /**
     * Removes a series from the collection.
     * <P>
     * Notifies all registered listeners that the dataset has changed.
     * </p>
     *
     * @param series the series ({@code null}).
     */
    public void removeSeries(MatrixSeries<S> series) {
        Args.nullNotPermitted(series, "series");
        if (this.seriesList.contains(series)) {
            series.removeChangeListener(this);
            this.seriesList.remove(series);
            fireDatasetChanged();
        }
    }


    /**
     * Removes a series from the collection.
     * <p>
     * Notifies all registered listeners that the dataset has changed.
     *
     * @param seriesIndex the series (zero based index).
     */
    public void removeSeries(int seriesIndex) {
        Args.requireInRange(seriesIndex, "seriesIndex", 0, this.seriesList.size() - 1);

        // fetch the series, remove the change listener, then remove the series.
        MatrixSeries series = this.seriesList.get(seriesIndex);
        series.removeChangeListener(this);
        this.seriesList.remove(seriesIndex);
        fireDatasetChanged();
    }

}
