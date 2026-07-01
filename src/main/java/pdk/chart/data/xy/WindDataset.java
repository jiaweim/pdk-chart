package pdk.chart.data.xy;

/**
 * Interface for a dataset that supplies wind intensity and direction values
 * observed at various points in time.
 *
 * @param <S> the series key type.
 */
public interface WindDataset<S extends Comparable<S>> extends XYDataset<S> {

    /**
     * Returns the wind direction (should be in the range 0 to 12,
     * corresponding to the positions on an upside-down clock face).
     *
     * @param series the series (in the range {@code 0} to
     *               {@code getSeriesCount() - 1}).
     * @param item   the item (in the range {@code 0} to
     *               {@code getItemCount(series) - 1}).
     * @return The wind direction.
     */
    Number getWindDirection(int series, int item);

    /**
     * Returns the wind force on the Beaufort scale (0 to 12).  See:
     * <p>
     * <a href="http://en.wikipedia.org/wiki/Beaufort_scale">http://en.wikipedia.org/wiki/Beaufort_scale</a>
     *
     * @param series the series (in the range {@code 0} to
     *               {@code getSeriesCount() - 1}).
     * @param item   the item (in the range {@code 0} to
     *               {@code getItemCount(series) - 1}).
     * @return The wind force.
     */
    Number getWindForce(int series, int item);

}
