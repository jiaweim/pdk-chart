package pdk.chart.data.general;

import pdk.chart.data.category.CategoryDataset;

/**
 * A dataset containing (key, value) data items.This dataset is equivalent
 * to a {@link CategoryDataset} and is included for completeness only.
 *
 * @param <R> The type for the row (series) keys.
 * @param <C> The type for the column (item) keys.
 */
public interface KeyedValues2DDataset<R extends Comparable<R>, C extends Comparable<C>>
        extends CategoryDataset<R, C> {

    // no new methods required

}
