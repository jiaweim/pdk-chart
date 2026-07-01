package pdk.chart.data.xy;

import pdk.chart.data.Range;

import java.util.List;

/**
 * An interface that can (optionally) be implemented by a dataset to assist in
 * determining the minimum and maximum x-values in the dataset.
 *
 * @param <S> the series key type.
 */
public interface XYDomainInfo<S extends Comparable<S>> {

    /**
     * Returns the range of the values in this dataset's domain.
     *
     * @param visibleSeriesKeys the keys of the visible series.
     * @param includeInterval   a flag that determines whether the
     *                          y-interval is taken into account.
     * @return The range (or {@code null} if the dataset contains no
     * values).
     */
    Range getDomainBounds(List<S> visibleSeriesKeys, boolean includeInterval);

}