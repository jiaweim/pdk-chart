package pdk.chart.data.xy;

/**
 * An base class that you can use to create new implementations of the
 * {@link XYZDataset} interface.
 *
 * @param <S> the series key type.
 */
public abstract class AbstractXYZDataset<S extends Comparable<S>>
        extends AbstractXYDataset<S> implements XYZDataset<S> {

    /**
     * Creates a new empty dataset.
     */
    protected AbstractXYZDataset() {
        super();
    }

    /**
     * Returns the z-value (as a double primitive) for an item within a series.
     *
     * @param series the series (zero-based index).
     * @param item   the item (zero-based index).
     * @return The z-value.
     */
    @Override
    public double getZValue(int series, int item) {
        double result = Double.NaN;
        Number z = getZ(series, item);
        if (z != null) {
            result = z.doubleValue();
        }
        return result;
    }
}
