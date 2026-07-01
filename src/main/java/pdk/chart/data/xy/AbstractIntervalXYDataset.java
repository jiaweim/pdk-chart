package pdk.chart.data.xy;

/**
 * An base class that you can use to create new implementations of the
 * {@link IntervalXYDataset} interface.
 *
 * @param <S> the series key type.
 */
public abstract class AbstractIntervalXYDataset<S extends Comparable<S>>
        extends AbstractXYDataset<S>
        implements IntervalXYDataset<S> {

    /**
     * Creates a new empty dataset.
     */
    public AbstractIntervalXYDataset() {
        super();
    }

    /**
     * Returns the start x-value (as a double primitive) for an item within a
     * series.
     *
     * @param series the series index (zero-based).
     * @param item   the item index (zero-based).
     * @return The value.
     */
    @Override
    public double getStartXValue(int series, int item) {
        double result = Double.NaN;
        Number x = getStartX(series, item);
        if (x != null) {
            result = x.doubleValue();
        }
        return result;
    }

    /**
     * Returns the end x-value (as a double primitive) for an item within a
     * series.
     *
     * @param series the series index (zero-based).
     * @param item   the item index (zero-based).
     * @return The value.
     */
    @Override
    public double getEndXValue(int series, int item) {
        double result = Double.NaN;
        Number x = getEndX(series, item);
        if (x != null) {
            result = x.doubleValue();
        }
        return result;
    }

    /**
     * Returns the start y-value (as a double primitive) for an item within a
     * series.
     *
     * @param series the series index (zero-based).
     * @param item   the item index (zero-based).
     * @return The value.
     */
    @Override
    public double getStartYValue(int series, int item) {
        double result = Double.NaN;
        Number y = getStartY(series, item);
        if (y != null) {
            result = y.doubleValue();
        }
        return result;
    }

    /**
     * Returns the end y-value (as a double primitive) for an item within a
     * series.
     *
     * @param series the series (zero-based index).
     * @param item   the item (zero-based index).
     * @return The value.
     */
    @Override
    public double getEndYValue(int series, int item) {
        double result = Double.NaN;
        Number y = getEndY(series, item);
        if (y != null) {
            result = y.doubleValue();
        }
        return result;
    }

}
