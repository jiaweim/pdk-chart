package pdk.chart.data.statistics;

import pdk.chart.data.category.CategoryDataset;

/**
 * A category dataset that defines a mean and standard deviation value for
 * each item.
 *
 * @param <R> the row key type.
 * @param <C> the column key type.
 */
public interface StatisticalCategoryDataset<R extends Comparable<R>,
        C extends Comparable<C>> extends CategoryDataset<R, C> {

    /**
     * Returns the mean value for an item.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The mean value (possibly {@code null}).
     */
    Number getMeanValue(int row, int column);

    /**
     * Returns the mean value for an item.
     *
     * @param rowKey    the row key.
     * @param columnKey the columnKey.
     * @return The mean value (possibly {@code null}).
     */
    Number getMeanValue(R rowKey, C columnKey);

    /**
     * Returns the standard deviation value for an item.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The standard deviation (possibly {@code null}).
     */
    Number getStdDevValue(int row, int column);

    /**
     * Returns the standard deviation value for an item.
     *
     * @param rowKey    the row key.
     * @param columnKey the columnKey.
     * @return The standard deviation (possibly {@code null}).
     */
    Number getStdDevValue(R rowKey, C columnKey);

}

