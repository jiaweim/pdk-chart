package pdk.chart.ms.label;

import pdk.chart.data.xy.XYDataset;

/**
 * Searches the visible items in an XYDataset.
 * <p>
 * The dataset is assumed to be sorted by ascending x.
 */
public final class VisiblePeakSearcher {

    private VisiblePeakSearcher() {}

    /**
     * Returns the first visible item.
     *
     * @param dataset dataset
     * @param series  series index
     * @param lower   lower x
     * @return first visible item
     */
    public static int findFirst(
            XYDataset dataset,
            int series,
            double lower) {

        int low = 0;
        int high = dataset.getItemCount(series) - 1;

        while (low <= high) {

            int mid = (low + high) >>> 1;

            double x = dataset.getXValue(series, mid);

            if (x < lower) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return Math.min(low,
                dataset.getItemCount(series) - 1);
    }

    /**
     * Returns the last visible item.
     *
     * @param dataset dataset
     * @param series  series
     * @param upper   upper x
     * @return last visible item
     */
    public static int findLast(
            XYDataset dataset,
            int series,
            double upper) {

        int low = 0;
        int high = dataset.getItemCount(series) - 1;

        while (low <= high) {

            int mid = (low + high) >>> 1;

            double x = dataset.getXValue(series, mid);

            if (x <= upper) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return Math.max(high, 0);
    }

    /**
     * Returns the visible item range.
     *
     * @param dataset dataset
     * @param series  series
     * @param lower   lower x
     * @param upper   upper x
     * @return visible range
     */
    public static VisibleRange findRange(
            XYDataset dataset,
            int series,
            double lower,
            double upper) {

        int first = findFirst(dataset, series, lower);

        int last = findLast(dataset, series, upper);

        if (last < first) {
            return VisibleRange.EMPTY;
        }

        return new VisibleRange(first, last);
    }

}