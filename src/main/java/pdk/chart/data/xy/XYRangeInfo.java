package pdk.chart.data.xy;

import pdk.chart.data.Range;

import java.util.List;

/**
 * An interface that can (optionally) be implemented by a dataset to assist in
 * determining the minimum and maximum y-values.
 */
public interface XYRangeInfo {

    /**
     * Returns the range of the values in this dataset's range.
     *
     * @param visibleSeriesKeys the keys of the visible series.
     * @param xRange            the x-range ({@code null} not permitted).
     * @param includeInterval   a flag that determines whether the
     *                          y-interval is taken into account.
     * @return The range (or {@code null} if the dataset contains no
     * values).
     */
    Range getRangeBounds(List visibleSeriesKeys, Range xRange, boolean includeInterval);

}