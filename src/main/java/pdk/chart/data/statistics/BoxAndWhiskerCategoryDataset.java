package pdk.chart.data.statistics;

import pdk.chart.data.category.CategoryDataset;

import java.util.List;

/**
 * A category dataset that defines various medians, outliers and an average
 * value for each item.
 *
 * @param <R> the row key type.
 * @param <C> the column key type.
 */
public interface BoxAndWhiskerCategoryDataset<R extends Comparable<R>,
        C extends Comparable<C>> extends CategoryDataset<R, C> {

    /**
     * Returns the mean value for an item.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The mean value.
     */
    Number getMeanValue(int row, int column);

    /**
     * Returns the average value for an item.
     *
     * @param rowKey    the row key.
     * @param columnKey the columnKey.
     * @return The average value.
     */
    Number getMeanValue(R rowKey, C columnKey);

    /**
     * Returns the median value for an item.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The median value.
     */
    Number getMedianValue(int row, int column);

    /**
     * Returns the median value for an item.
     *
     * @param rowKey    the row key.
     * @param columnKey the columnKey.
     * @return The median value.
     */
    Number getMedianValue(R rowKey, C columnKey);

    /**
     * Returns the q1median value for an item.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The q1median value.
     */
    Number getQ1Value(int row, int column);

    /**
     * Returns the q1median value for an item.
     *
     * @param rowKey    the row key.
     * @param columnKey the columnKey.
     * @return The q1median value.
     */
    Number getQ1Value(R rowKey, C columnKey);

    /**
     * Returns the q3median value for an item.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The q3median value.
     */
    Number getQ3Value(int row, int column);

    /**
     * Returns the q3median value for an item.
     *
     * @param rowKey    the row key.
     * @param columnKey the columnKey.
     * @return The q3median value.
     */
    Number getQ3Value(R rowKey, C columnKey);

    /**
     * Returns the minimum regular (non-outlier) value for an item.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The minimum regular value.
     */
    Number getMinRegularValue(int row, int column);

    /**
     * Returns the minimum regular (non-outlier) value for an item.
     *
     * @param rowKey    the row key.
     * @param columnKey the columnKey.
     * @return The minimum regular value.
     */
    Number getMinRegularValue(R rowKey, C columnKey);

    /**
     * Returns the maximum regular (non-outlier) value for an item.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The maximum regular value.
     */
    Number getMaxRegularValue(int row, int column);

    /**
     * Returns the maximum regular (non-outlier) value for an item.
     *
     * @param rowKey    the row key.
     * @param columnKey the columnKey.
     * @return The maximum regular value.
     */
    Number getMaxRegularValue(R rowKey, C columnKey);

    /**
     * Returns the minimum outlier (non-farout) for an item.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The minimum outlier.
     */
    Number getMinOutlier(int row, int column);

    /**
     * Returns the minimum outlier (non-farout) for an item.
     *
     * @param rowKey    the row key.
     * @param columnKey the columnKey.
     * @return The minimum outlier.
     */
    Number getMinOutlier(R rowKey, C columnKey);

    /**
     * Returns the maximum outlier (non-farout) for an item.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The maximum outlier.
     */
    Number getMaxOutlier(int row, int column);

    /**
     * Returns the maximum outlier (non-farout) for an item.
     *
     * @param rowKey    the row key.
     * @param columnKey the columnKey.
     * @return The maximum outlier.
     */
    Number getMaxOutlier(R rowKey, C columnKey);

    /**
     * Returns a list of outlier values for an item.  The list may be empty,
     * but should never be {@code null}.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return A list of outliers for an item.
     */
    List<? extends Number> getOutliers(int row, int column);

    /**
     * Returns a list of outlier values for an item.  The list may be empty,
     * but should never be {@code null}.
     *
     * @param rowKey    the row key.
     * @param columnKey the columnKey.
     * @return A list of outlier values for an item.
     */
    List<? extends Number> getOutliers(R rowKey, C columnKey);

}
