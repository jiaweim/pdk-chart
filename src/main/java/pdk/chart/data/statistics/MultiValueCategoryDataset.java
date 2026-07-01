package pdk.chart.data.statistics;

import pdk.chart.data.category.CategoryDataset;

import java.util.List;

/**
 * A category dataset that defines multiple values for each item.
 *
 * @param <R> the row key type.
 * @param <C> the column key type.
 */
public interface MultiValueCategoryDataset<R extends Comparable<R>, C extends Comparable<C>>
        extends CategoryDataset<R, C> {

    /**
     * Returns a list (possibly empty) of the values for the specified item.
     * The returned list should be unmodifiable.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The list of values.
     */
    List<? extends Number> getValues(int row, int column);

    /**
     * Returns a list (possibly empty) of the values for the specified item.
     * The returned list should be unmodifiable.
     *
     * @param rowKey    the row key ({@code null} not permitted).
     * @param columnKey the column key ({@code null} not permitted).
     * @return The list of values.
     */
    List<? extends Number> getValues(R rowKey, C columnKey);

}