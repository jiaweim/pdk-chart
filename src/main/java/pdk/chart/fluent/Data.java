package pdk.chart.fluent;

import org.jspecify.annotations.NonNull;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.data.statistics.HistogramDataset;
import pdk.chart.data.time.RegularTimePeriod;
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
     * Create a {@link XYDatasetBuilder} to build {@link XYDataset}
     *
     * @param <S> series key type.
     * @return {@link CategoryDatasetBuilder} instance.
     */
    static <S extends Comparable<S>> XYDatasetBuilder<S> xy() {
        return new XYDatasetBuilder<>();
    }

    /**
     * Create a {@link XYZDatasetBuilder} to build {@link XYZDataset}.
     *
     * @param <S> series key type.
     * @return {@link CategoryDatasetBuilder} instance.
     */
    static <S extends Comparable<S>> XYZDatasetBuilder<S> xyz() {
        return new XYZDatasetBuilder<>();
    }

    /**
     * Create a {@link XYDataset}.
     *
     * @param seriesKey series key.
     * @param x         x values.
     * @param y         y values.
     * @param <S>       series name type.
     * @return {@link XYDataset}.
     */
    static <S extends Comparable<S>> XYDataset<S> createXY(@NonNull S seriesKey,
            double[] x, double[] y) {
        Args.requireEqualLength(x, y);
        XYSeries<S> series = new XYSeries<>(seriesKey);
        series.add(x, y);
        return new XYSeriesCollection<>(series);
    }

    /**
     * Create a {@link XYDataset} where the x values are of date type.
     *
     * @param seriesKey   series key
     * @param timePeriods {@link RegularTimePeriod} array.
     * @param values      y values.
     * @param <S>         series name type.
     * @return a {@link XYDataset}.
     */
    static <S extends Comparable<S>> IntervalXYDataset<S> createTime(@NonNull S seriesKey,
            RegularTimePeriod[] timePeriods, double[] values) {
        TimeSeries<S> series = new TimeSeries<>(seriesKey);
        series.add(timePeriods, values);
        return new TimeSeriesCollection<>(series);
    }

    /**
     * Create a {@link XYDataset}.
     *
     * @param seriesKey series key.
     * @param x         x values.
     * @param y         y values.
     * @param <S>       series name type.
     * @return {@link XYDataset}.
     */
    static <S extends Comparable<S>> XYDataset<S> createXY(@NonNull S seriesKey,
            Double[] x, Double[] y) {
        Objects.requireNonNull(x);
        Objects.requireNonNull(y);
        if (x.length != y.length) {
            throw new IllegalArgumentException("The length of x and y are not equal");
        }

        XYSeries<S> series = new XYSeries<>(seriesKey);
        for (int i = 0; i < x.length; i++) {
            series.add(x[i], y[i], false);
        }
        return new XYSeriesCollection<>(series);
    }

    /**
     * Create a {@link XYDataset}.
     *
     * @param seriesKey series key.
     * @param x         x values.
     * @param y         y values.
     * @param <S>       series name type.
     * @return {@link XYDataset}.
     */
    static <S extends Comparable<S>> XYZDataset<S> createXYZ(@NonNull S seriesKey,
            double[] x, double[] y, double[] z) {
        DefaultXYZDataset<S> dataset = new DefaultXYZDataset<>();
        dataset.addSeries(seriesKey, new double[][]{x, y, z});
        return dataset;
    }

    /**
     * Create a {@link XYDataset}.
     *
     * @param seriesKey series key.
     * @param x         x values.
     * @param y         y values.
     * @param z         z values.
     * @param <S>       series name type.
     * @return {@link XYDataset}.
     */
    static <S extends Comparable<S>> XYZDataset<S> createXYZ(@NonNull S seriesKey,
            Double[] x, Double[] y, Double[] z) {
        return new XYZDatasetBuilder<S>()
                .addSeries(seriesKey, x, y, z)
                .build();
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
        for (double[] datum : data) {
            columnCount = Math.max(columnCount, datum.length);
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


    static double getMin(Double[] values) {
        double min = Double.MAX_VALUE;
        for (Double value : values) {
            min = Math.min(value, min);
        }
        return min;
    }

    static double getMax(Double[] values) {
        double max = -Double.MAX_VALUE;
        for (Double value : values) {
            max = Math.max(value, max);
        }
        return max;
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

    class XYDatasetBuilder<T extends Comparable<T>> {

        private final XYSeriesCollection<T> dataset = new XYSeriesCollection<>();

        /**
         * Adda new series.
         *
         * @param key series key.
         * @param x   x values
         * @param y   y values.
         * @return this.
         */
        public XYDatasetBuilder<T> addSeries(@NonNull T key, double[] x, double[] y) {
            Args.requireEqualLength(x, y);
            XYSeries<T> series = new XYSeries<>(key);
            series.add(x, y);
            dataset.addSeries(series);
            return this;
        }

        public XYSeriesCollection<T> build() {
            return dataset;
        }
    }

    class XYZDatasetBuilder<S extends Comparable<S>> {

        private final DefaultXYZDataset<S> dataset = new DefaultXYZDataset<>();

        /**
         * Add a new series
         *
         * @param x x values.
         * @param y y values.
         * @param z z values.
         * @return this.
         */
        public XYZDatasetBuilder<S> addSeries(S key, double[] x, double[] y, double[] z) {
            dataset.addSeries(key, new double[][]{x, y, z});
            return this;
        }

        /**
         * Add a new series
         *
         * @param x x values.
         * @param y y values.
         * @param z z values.
         * @return this.
         */
        public XYZDatasetBuilder<S> addSeries(S key, Double[] x, Double[] y, Double[] z) {
            Objects.requireNonNull(x);
            Objects.requireNonNull(y);
            Objects.requireNonNull(z);
            dataset.addSeries(key, new double[][]{convert(x), convert(y), convert(z)});
            return this;
        }

        private double[] convert(Double[] array) {
            double[] p = new double[array.length];
            for (int i = 0; i < array.length; i++) {
                p[i] = array[i];
            }
            return p;
        }

        public DefaultXYZDataset<S> build() {
            return dataset;
        }
    }

    /**
     * Create a histogram dataset.
     * <p>
     * Any data less than minimum will be assigned to the first bin, and any
     * data greater than the maximum will be assigned to the last bin.
     * <p>
     * Values falling on the boundary of adjacent bins will be assigned to the higher indexed bin.
     *
     * @param key     series key
     * @param values  observations.
     * @param bins    number of bins.
     * @param minimum the lower bound of the bin range.
     * @param maximum the upper bound of the bin range.
     * @param <S>     series key type.
     * @return {@link HistogramDataset} instance.
     */
    static <S extends Comparable<S>> HistogramDataset createHis(S key, double[] values, int bins,
            double minimum, double maximum) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries(key, values, bins, minimum, maximum);
        return dataset;
    }

    /**
     * Create a {@link HistogramDatasetBuilder} to build {@link HistogramDataset}.
     *
     * @return {@link HistogramDatasetBuilder} instance.
     */
    static HistogramDatasetBuilder his() {
        return new HistogramDatasetBuilder();
    }

    class HistogramDatasetBuilder {

        private final HistogramDataset dataset = new HistogramDataset();

        /**
         * Add a series to the dataset.
         * <p>
         * Any data less than minimum will be assigned to the first bin, and any
         * data greater than the maximum will be assigned to the last bin.
         * <p>
         * Values falling on the boundary of adjacent bins will be assigned to the higher indexed bin.
         *
         * @param key     the series key.
         * @param values  the raw values.
         * @param bins    number of bins.
         * @param minimum the lower bound of the bin range.
         * @param maximum the upper bound of the bin range.
         * @return this.
         */
        public HistogramDatasetBuilder addSeries(@NonNull Comparable key, double[] values, int bins,
                double minimum, double maximum) {
            dataset.addSeries(key, values, bins, minimum, maximum);
            return this;
        }

        public HistogramDataset build() {
            return dataset;
        }
    }

    static <S extends Comparable<S>> TimeSeriesDatasetBuilder<S> time() {
        return new TimeSeriesDatasetBuilder<>();
    }

    class TimeSeriesDatasetBuilder<S extends Comparable<S>> {

        private final TimeSeriesCollection<S> dataset = new TimeSeriesCollection<>();

        public TimeSeriesDatasetBuilder<S> addSeries(S key, RegularTimePeriod[] times, double[] values) {
            TimeSeries<S> series = new TimeSeries<>(key);
            series.add(times, values);
            dataset.addSeries(series);
            return this;
        }

        public TimeSeriesDatasetBuilder<S> addSeries(TimeSeries<S> series) {
            dataset.addSeries(series);
            return this;
        }

        public TimeSeriesCollection<S> build() {
            return dataset;
        }
    }
}
