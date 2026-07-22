package pdk.chart.data.general;

/**
 * A dataset that represents a rectangular grid of (x, y, z) values.  The x
 * and y values appear at regular intervals in the dataset, while the z-values
 * can take any value (including {@code null} for unknown values).
 */
public interface HeatMapDataset {

    /**
     * Returns the number of x values across the width of the dataset.  The
     * values are evenly spaced between {@link #getMinimumXValue()} and
     * {@link #getMaximumXValue()}.
     *
     * @return The number of x-values (always &gt; 0).
     */
    int getXSampleCount();

    /**
     * Returns the number of y values (or samples) for the dataset.  The
     * values are evenly spaced between {@link #getMinimumYValue()} and
     * {@link #getMaximumYValue()}.
     *
     * @return The number of y-values (always &gt; 0).
     */
    int getYSampleCount();

    /**
     * Returns the lowest x-value represented in this dataset.  A requirement
     * of this interface is that this method must never return infinite or
     * Double.NAN values.
     *
     * @return The lowest x-value represented in this dataset.
     */
    double getMinimumXValue();

    /**
     * Returns the highest x-value represented in this dataset.  A requirement
     * of this interface is that this method must never return infinite or
     * Double.NAN values.
     *
     * @return The highest x-value represented in this dataset.
     */
    double getMaximumXValue();

    /**
     * Returns the lowest y-value represented in this dataset.  A requirement
     * of this interface is that this method must never return infinite or
     * Double.NAN values.
     *
     * @return The lowest y-value represented in this dataset.
     */
    double getMinimumYValue();

    /**
     * Returns the highest y-value represented in this dataset.  A requirement
     * of this interface is that this method must never return infinite or
     * Double.NAN values.
     *
     * @return The highest y-value represented in this dataset.
     */
    double getMaximumYValue();

    /**
     * A convenience method that returns the x-value for the given index.
     *
     * @param xIndex the xIndex.
     * @return The x-value.
     */
    double getXValue(int xIndex);

    /**
     * A convenience method that returns the y-value for the given index.
     *
     * @param yIndex the yIndex.
     * @return The y-value.
     */
    double getYValue(int yIndex);

    /**
     * Returns the z-value at the specified sample position in the dataset.
     * For a missing or unknown value, this method should return Double.NAN.
     *
     * @param xIndex the position of the x sample in the dataset.
     * @param yIndex the position of the y sample in the dataset.
     * @return The z-value.
     */
    double getZValue(int xIndex, int yIndex);

    /**
     * Returns the z-value at the specified sample position in the dataset.
     * This method can return {@code null} to indicate a missing/unknown
     * value.
     * <br><br>
     * Bear in mind that the class implementing this interface may
     * store its data using primitives rather than objects, so calling this
     * method may require a new {@code Number} object to be allocated...
     * for this reason, it is generally preferable to use the
     * {@link #getZValue(int, int)} method unless you *know* that the dataset
     * implementation stores the z-values using objects.
     *
     * @param xIndex the position of the x sample in the dataset.
     * @param yIndex the position of the y sample in the dataset.
     * @return The z-value (possibly {@code null}).
     */
    Number getZ(int xIndex, int yIndex);

}
