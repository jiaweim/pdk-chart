package pdk.chart.data.general;

import pdk.chart.data.category.DefaultCategoryDataset;

import java.io.Serializable;

/**
 * A default implementation of the {@link KeyedValues2DDataset} interface.
 *
 * @param <R> The type for the row (series) keys.
 * @param <C> The type for the column (item) keys.
 */
public class DefaultKeyedValues2DDataset<R extends Comparable<R>, C extends Comparable<C>>
        extends DefaultCategoryDataset<R, C>
        implements KeyedValues2DDataset<R, C>, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 4288210771905990424L;

    /**
     * Creates a new empty dataset.
     */
    public DefaultKeyedValues2DDataset() {
        super();
    }
}
