package pdk.chart.fluent;

import org.jspecify.annotations.NonNull;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.*;
import pdk.chart.internal.Args;
import pdk.chart.internal.ArrayUtils;

import java.util.Objects;

/**
 * Utilities for build dataset.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 17 Jun 2026, 9:35 AM
 */
public interface Data {

    /**
     * Create a {@link CategoryDatasetBuilder} to build {@link pdk.chart.data.category.CategoryDataset}
     *
     * @param <T1> row (series) key type
     * @param <T2> column (category) key type
     * @return {@link CategoryDatasetBuilder} instance.
     */
    static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> CategoryDatasetBuilder<T1, T2> category() {
        return new CategoryDatasetBuilder<>();
    }

    /**
     * Creates a {@link CategoryDataset} that contains a copy of the data in
     * an array (instances of {@code double} are created to represent the
     * data items).
     * <p>
     * Row and column keys are taken from the supplied arrays.
     *
     * @param rowKeys    the row keys ({@code null} not permitted).
     * @param columnKeys the column keys ({@code null} not permitted).
     * @param data       the data.
     * @param <R>        the type for the row keys.
     * @param <C>        the type for the column keys.
     * @return The dataset.
     */
    static <R extends Comparable<R>, C extends Comparable<C>>
    CategoryDataset<R, C> createCategoryDataset(R[] rowKeys, C[] columnKeys,
            double[][] data) {
        Objects.requireNonNull(rowKeys, "rowKeys must not be null");
        Objects.requireNonNull(columnKeys, "columnKeys must not be null");

        if (ArrayUtils.hasDuplicateItems(rowKeys)) {
            throw new IllegalArgumentException("Duplicate items in 'rowKeys'.");
        }
        if (ArrayUtils.hasDuplicateItems(columnKeys)) {
            throw new IllegalArgumentException("Duplicate items in 'columnKeys'.");
        }
        if (rowKeys.length != data.length) {
            throw new IllegalArgumentException(
                    "The number of row keys does not match the number of rows in "
                            + "the data array.");
        }
        int columnCount = 0;
        for (int r = 0; r < data.length; r++) {
            columnCount = Math.max(columnCount, data[r].length);
        }
        if (columnKeys.length != columnCount) {
            throw new IllegalArgumentException(
                    "The number of column keys does not match the number of "
                            + "columns in the data array.");
        }

        // now do the work...
        DefaultCategoryDataset<R, C> result = new DefaultCategoryDataset<>();
        for (int r = 0; r < data.length; r++) {
            R rowKey = rowKeys[r];
            for (int c = 0; c < data[r].length; c++) {
                C columnKey = columnKeys[c];
                result.addValue(data[r][c], rowKey, columnKey);
            }
        }
        return result;
    }

    /**
     * Creates a {@link CategoryDataset} with one series.
     * <p>
     * Column keys are taken from the supplied arrays.
     *
     * @param rowKey     the row key.
     * @param columnKeys the column keys.
     * @param data       the data.
     * @param <R>        the type for the row key.
     * @param <C>        the type for the column keys.
     * @return The dataset.
     */
    static <R extends Comparable<R>, C extends Comparable<C>>
    CategoryDataset<R, C> createCategoryDataset(@NonNull R rowKey, @NonNull C[] columnKeys,
            double[] data) {
        Objects.requireNonNull(rowKey, "rowKeys must not be null");
        Objects.requireNonNull(columnKeys, "columnKeys must not be null");

        if (ArrayUtils.hasDuplicateItems(columnKeys)) {
            throw new IllegalArgumentException("Duplicate items in 'columnKeys'.");
        }

        if (columnKeys.length != data.length) {
            throw new IllegalArgumentException(
                    "The number of column keys does not match the number of "
                            + "columns in the data array.");
        }

        DefaultCategoryDataset<R, C> result = new DefaultCategoryDataset<>();
        result.addSeries(rowKey, columnKeys, data);
        return result;
    }


    /**
     * Auxiliary class for creating DefaultCategoryDataset
     */
    class CategoryDatasetBuilder<T1 extends Comparable<T1>, T2 extends Comparable<T2>> {

        private final DefaultCategoryDataset<T1, T2> dataset = new DefaultCategoryDataset<>();

        /**
         * Add a new series.
         *
         * @param seriesName series name.
         * @param categories categories.
         * @param values     values for the series.
         * @return this.
         */
        public CategoryDatasetBuilder<T1, T2> addSeries(T1 seriesName, T2[] categories, double[] values) {
            dataset.addSeries(seriesName, categories, values);
            return this;
        }

        public DefaultCategoryDataset<T1, T2> build() {
            return dataset;
        }
    }


    /**
     * Create an {@link IntervalXYDataset}.
     *
     * @param name     series name
     * @param x        x values.
     * @param y        y values.
     * @param barWidth bar width.
     * @param <S>      series name type.
     * @return {@link IntervalXYDataset}
     */
    static <S extends Comparable<S>> IntervalXYDataset<S> createIntervalXYDataset(S name,
            double[] x, double[] y, double barWidth) {
        Args.requireEqualLength(x, y);

        XYSeries<S> series = new XYSeries<>(name);
        for (int i = 0; i < x.length; i++) {
            series.add(x[i], y[i]);
        }
        XYSeriesCollection<S> dataset = new XYSeriesCollection<>(series);
        return new XYBarDataset<>(dataset, barWidth);
    }

    /**
     * Create an {@link IntervalXYDataset} from {@link TimeSeries}
     *
     * @param series {@link TimeSeries}
     * @param <S>    series name type.
     * @return {@link IntervalXYDataset}
     */
    @SafeVarargs
    static <S extends Comparable<S>> IntervalXYDataset<S> createIntervalXYDataset(TimeSeries<S>... series) {
        TimeSeriesCollection<S> tc = new TimeSeriesCollection<>();
        for (TimeSeries<S> sTimeSeries : series) {
            tc.addSeries(sTimeSeries);
        }
        return tc;
    }

    /**
     * Create an {@link IntervalXYDataset}.
     * <p>
     * This dataset is flexible, allowing the start and end points of each bar to be specified.
     *
     * @param name   series name
     * @param x      x value
     * @param startX start x for each bar
     * @param endX   end x for each bar
     * @param y      y values.
     * @param startY start y for each bar
     * @param endY   end y for each bar
     * @param <S>    name type
     * @return {@link IntervalXYDataset}
     */
    static <S extends Comparable<S>> IntervalXYDataset<S> createIntervalXYDataset(S name,
            double[] x, double[] startX, double[] endX,
            double[] y, double[] startY, double[] endY
    ) {
        DefaultIntervalXYDataset<S> dataset = new DefaultIntervalXYDataset<>();
        dataset.addSeries(name, new double[][]{x, startX, endX, y, startY, endY});
        return dataset;
    }

    /**
     * Create a {@link TableXYDataset} for stacked bar chart.
     *
     * @param series {@link XYSeries}
     * @param <S>    series name type.
     * @return {@link TableXYDataset}
     */
    @SafeVarargs
    static <S extends Comparable<S>> TableXYDataset<S> createTableXYDataset(XYSeries<S>... series) {
        DefaultTableXYDataset<S> dataset = new DefaultTableXYDataset<>();
        for (XYSeries<S> s : series) {
            dataset.addSeries(s);
        }
        return dataset;
    }

}
