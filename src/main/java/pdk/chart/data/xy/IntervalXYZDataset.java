package pdk.chart.data.xy;

/**
 * An extension of the {@link XYZDataset} interface that allows a range of data
 * to be defined for any of the X values, the Y values, and the Z values.
 *
 * @param <S> the series key type.
 */
public interface IntervalXYZDataset<S extends Comparable<S>>
        extends XYZDataset<S> {

    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series the series (zero-based index).
     * @param item   the item within a series (zero-based index).
     * @return The starting X value for the specified series and item.
     */
    Number getStartXValue(int series, int item);

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series the series (zero-based index).
     * @param item   the item within a series (zero-based index).
     * @return The ending X value for the specified series and item.
     */
    Number getEndXValue(int series, int item);

    /**
     * Returns the starting Y value for the specified series and item.
     *
     * @param series the series (zero-based index).
     * @param item   the item within a series (zero-based index).
     * @return The starting Y value for the specified series and item.
     */
    Number getStartYValue(int series, int item);

    /**
     * Returns the ending Y value for the specified series and item.
     *
     * @param series the series (zero-based index).
     * @param item   the item within a series (zero-based index).
     * @return The ending Y value for the specified series and item.
     */
    Number getEndYValue(int series, int item);

    /**
     * Returns the starting Z value for the specified series and item.
     *
     * @param series the series (zero-based index).
     * @param item   the item within a series (zero-based index).
     * @return The starting Z value for the specified series and item.
     */
    Number getStartZValue(int series, int item);

    /**
     * Returns the ending Z value for the specified series and item.
     *
     * @param series the series (zero-based index).
     * @param item   the item within a series (zero-based index).
     * @return The ending Z value for the specified series and item.
     */
    Number getEndZValue(int series, int item);

}
