package pdk.chart.data.xy;

/**
 * A dataset containing one or more data series containing (x, y) data items,
 * where all series in the dataset share the same set of x-values.  This is a
 * restricted form of the {@link XYDataset} interface (which allows independent
 * x-values between series). This is used primarily by the
 * {@link pdk.chart.renderer.xy.StackedXYAreaRenderer}.
 *
 * @param <S> the series key type.
 */
public interface TableXYDataset<S extends Comparable<S>> extends XYDataset<S> {

    /**
     * Returns the number of items every series.
     *
     * @return The item count.
     */
    int getItemCount();
}
