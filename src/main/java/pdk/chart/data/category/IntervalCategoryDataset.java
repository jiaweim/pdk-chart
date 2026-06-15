package pdk.chart.data.category;

/**
 * A category dataset that defines a value range for each series/category
 * combination.
 *
 * @param <R> the row key type.
 * @param <C> the column key type.
 */
public interface IntervalCategoryDataset<R extends Comparable<R>,
        C extends Comparable<C>> extends CategoryDataset<R, C> {

    /**
     * Returns the start value for the interval for a given series and category.
     *
     * @param series   the series (zero-based index).
     * @param category the category (zero-based index).
     * @return The start value (possibly {@code null}).
     * @see #getEndValue(int, int)
     */
    Number getStartValue(int series, int category);

    /**
     * Returns the start value for the interval for a given series and category.
     *
     * @param series   the series key.
     * @param category the category key.
     * @return The start value (possibly {@code null}).
     * @see #getEndValue(Comparable, Comparable)
     */
    Number getStartValue(R series, C category);

    /**
     * Returns the end value for the interval for a given series and category.
     *
     * @param series   the series (zero-based index).
     * @param category the category (zero-based index).
     * @return The end value (possibly {@code null}).
     * @see #getStartValue(int, int)
     */
    Number getEndValue(int series, int category);

    /**
     * Returns the end value for the interval for a given series and category.
     *
     * @param series   the series key.
     * @param category the category key.
     * @return The end value (possibly {@code null}).
     * @see #getStartValue(Comparable, Comparable)
     */
    Number getEndValue(R series, C category);

}
