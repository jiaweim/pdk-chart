package pdk.chart.data.general;

import org.jspecify.annotations.NonNull;
import pdk.chart.data.*;
import pdk.chart.data.category.*;
import pdk.chart.data.statistics.*;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.*;
import pdk.chart.ms.PeptideDataset;
import pdk.chart.util.Args;
import pdk.chart.util.ArrayUtils;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import static java.util.Objects.requireNonNull;

/**
 * A collection of useful static methods relating to datasets.
 */
public final class DatasetUtils {

    private DatasetUtils() {}


    /**
     * Create a {@link HistogramDatasetBuilder} to build {@link HistogramDataset}.
     *
     * @return {@link HistogramDatasetBuilder} instance.
     */
    public static HistogramDatasetBuilder his() {
        return new HistogramDatasetBuilder();
    }

    public static class HistogramDatasetBuilder {

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
    public static <S extends Comparable<S>> HistogramDataset createHis(S key, double[] values, int bins,
            double minimum, double maximum) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries(key, values, bins, minimum, maximum);
        return dataset;
    }

    public static <R extends Comparable<R>, C extends Comparable<C>> IntervalCategoryDatasetBuilder<R, C> intervalCategory() {
        return new IntervalCategoryDatasetBuilder<>();
    }

    public static class IntervalCategoryDatasetBuilder<R extends Comparable<R>, C extends Comparable<C>> {

        private String[] categoryKeys;
        private final List<String> seriesNames = new ArrayList<>();
        private final List<double[]> starts = new ArrayList<>();
        private final List<double[]> ends = new ArrayList<>();

        public IntervalCategoryDatasetBuilder<R, C> categories(String[] categories) {
            this.categoryKeys = categories;
            return this;
        }

        public IntervalCategoryDatasetBuilder<R, C> addSeries(double[] starts, double[] ends) {
            requireNonNull(starts);
            requireNonNull(ends);

            this.starts.add(starts);
            this.ends.add(ends);
            return this;
        }

        public IntervalCategoryDatasetBuilder<R, C> addSeries(String seriesName, double[] starts, double[] ends) {
            requireNonNull(seriesName);
            requireNonNull(starts);
            requireNonNull(ends);

            this.seriesNames.add(seriesName);
            this.starts.add(starts);
            this.ends.add(ends);
            return this;
        }

        public IntervalCategoryDataset<R, C> build() {
            double[][] startArray = new double[this.starts.size()][];
            double[][] endArray = new double[this.ends.size()][];
            for (int i = 0; i < starts.size(); i++) {
                startArray[i] = starts.get(i);
                endArray[i] = ends.get(i);
            }
            String[] seriesNameArray = null;
            if (seriesNames.size() == starts.size()) {
                seriesNameArray = seriesNames.toArray(new String[0]);
            }

            Number[][] starts = DataUtils.createNumberArray2D(startArray);
            Number[][] ends = DataUtils.createNumberArray2D(endArray);

            return new DefaultIntervalCategoryDataset(seriesNameArray, categoryKeys, starts, ends);
        }
    }


    /**
     * Create a {@link TableXYDataset} for stacked bar chart.
     *
     * @param series {@link XYSeries}
     * @param <S>    series name type.
     * @return {@link TableXYDataset}
     */
    @SafeVarargs
    public static <S extends Comparable<S>> TableXYDataset<S> createTableXYDataset(XYSeries<S>... series) {
        DefaultTableXYDataset<S> dataset = new DefaultTableXYDataset<>();
        for (XYSeries<S> s : series) {
            dataset.addSeries(s);
        }
        return dataset;
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
    public static <S extends Comparable<S>> IntervalXYDataset<S> createIntervalXY(S name,
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
    public static <S extends Comparable<S>> IntervalXYDataset<S> createIntervalXY(S name,
            double[] x, double[] startX, double[] endX,
            double[] y, double[] startY, double[] endY) {
        DefaultIntervalXYDataset<S> dataset = new DefaultIntervalXYDataset<>();
        dataset.addSeries(name, new double[][]{x, startX, endX, y, startY, endY});
        return dataset;
    }

    public static <S extends Comparable<S>> TimeSeriesDatasetBuilder<S> time() {
        return new TimeSeriesDatasetBuilder<>();
    }

    public static class TimeSeriesDatasetBuilder<S extends Comparable<S>> {

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

    /**
     * Create a {@link XYDataset} where the x values are of date type.
     *
     * @param seriesKey   series key
     * @param timePeriods {@link RegularTimePeriod} array.
     * @param values      y values.
     * @param <S>         series name type.
     * @return a {@link XYDataset}.
     */
    public static <S extends Comparable<S>> IntervalXYDataset<S> createTime(@NonNull S seriesKey,
            RegularTimePeriod[] timePeriods, double[] values) {
        TimeSeries<S> series = new TimeSeries<>(seriesKey);
        series.add(timePeriods, values);
        return new TimeSeriesCollection<>(series);
    }

    /**
     * Create an {@link IntervalXYDataset} from {@link TimeSeries}
     *
     * @param series {@link TimeSeries}
     * @param <S>    series name type.
     * @return {@link IntervalXYDataset}
     */
    @SafeVarargs
    public static <S extends Comparable<S>> IntervalXYDataset<S> createTime(TimeSeries<S>... series) {
        TimeSeriesCollection<S> tc = new TimeSeriesCollection<>();
        for (TimeSeries<S> sTimeSeries : series) {
            tc.addSeries(sTimeSeries);
        }
        return tc;
    }

    /**
     * Create a {@link BoxAndWhiskerCategoryDatasetBuilder} to build {@link BoxAndWhiskerCategoryDataset}
     *
     * @param <R> series key type
     * @param <C> column key type
     * @return {@link BoxAndWhiskerCategoryDatasetBuilder}
     */
    public static <R extends Comparable<R>, C extends Comparable<C>> BoxAndWhiskerCategoryDatasetBuilder<R, C> boxAndWhisker() {
        return new BoxAndWhiskerCategoryDatasetBuilder<>();
    }

    public static class BoxAndWhiskerCategoryDatasetBuilder<R extends Comparable<R>, C extends Comparable<C>> {

        private final DefaultBoxAndWhiskerCategoryDataset<R, C> dataset = new DefaultBoxAndWhiskerCategoryDataset<>();

        public BoxAndWhiskerCategoryDatasetBuilder<R, C> add(List<? extends Number> list, R rowKey, C columnKey) {
            dataset.add(list, rowKey, columnKey);
            return this;
        }

        public DefaultBoxAndWhiskerCategoryDataset<R, C> build() {
            return dataset;
        }
    }

    /**
     * Create a {@link XYDataset}.
     *
     * @param x x values.
     * @param y y values.
     * @param z z values.
     * @return {@link XYDataset}.
     */
    public static XYZDataset<String> createXYZ(
            Double[] x, Double[] y, Double[] z) {
        return createXYZ("", x, y, z);
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
    public static <S extends Comparable<S>> XYZDataset<S> createXYZ(@NonNull S seriesKey,
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
    public static <S extends Comparable<S>> XYZDataset<S> createXYZ(@NonNull S seriesKey,
            Double[] x, Double[] y, Double[] z) {
        return new XYZDatasetBuilder<S>()
                .addSeries(seriesKey, x, y, z)
                .build();
    }

    /**
     * Create a {@link XYZDatasetBuilder} to build {@link XYZDataset}.
     *
     * @param <S> series key type.
     * @return {@link XYZDatasetBuilder} instance.
     */
    public static <S extends Comparable<S>> XYZDatasetBuilder<S> xyz() {
        return new XYZDatasetBuilder<>();
    }

    public static class XYZDatasetBuilder<S extends Comparable<S>> {

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
            requireNonNull(x);
            requireNonNull(y);
            requireNonNull(z);
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
     * Create a {@link XYDataset}.
     *
     * @param seriesKey series key.
     * @param x         x values.
     * @param y         y values.
     * @param <S>       series name type.
     * @return {@link XYDataset}.
     */
    public static <S extends Comparable<S>> XYDataset<S> createXY(@NonNull S seriesKey,
            double[] x, double[] y) {
        Args.requireEqualLength(x, y);
        XYSeries<S> series = new XYSeries<>(seriesKey);
        series.add(x, y);
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
    public static <S extends Comparable<S>> XYDataset<S> createXY(@NonNull S seriesKey,
            Double[] x, Double[] y) {
        requireNonNull(x);
        requireNonNull(y);
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
     * @param series {@link XYSeries} array.
     * @param <S>    series name type.
     * @return {@link XYDataset}.
     */
    @SafeVarargs
    public static <S extends Comparable<S>> XYDataset<S> createXY(XYSeries<S>... series) {
        XYSeriesCollection<S> dataset = new XYSeriesCollection<>();
        for (XYSeries<S> s : series) {
            dataset.addSeries(s);
        }
        return dataset;
    }

    /**
     * Create a {@link XYDatasetBuilder} to build {@link XYDataset}
     *
     * @param <S> series key type.
     * @return {@link XYDatasetBuilder} instance.
     */
    public static <S extends Comparable<S>> XYDatasetBuilder<S> xy() {
        return new XYDatasetBuilder<>();
    }

    public static class XYDatasetBuilder<T extends Comparable<T>> {

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

        /**
         * Adda new series.
         *
         * @param key series key.
         * @param x   x values
         * @param y   y values.
         * @return this.
         */
        public XYDatasetBuilder<T> addSeries(@NonNull T key, List<? extends Number> x, List<? extends Number> y) {
            requireNonNull(key);
            requireNonNull(x);
            requireNonNull(y);
            if (x.size() != y.size()) {
                throw new IllegalArgumentException("The size of x is not equal to the size of y.");
            }

            XYSeries<T> series = new XYSeries<>(key);
            for (int i = 0; i < x.size(); i++) {
                series.add(x.get(i), y.get(i));
            }
            dataset.addSeries(series);
            return this;
        }


        /**
         * Add a new series.
         *
         * @param series {@link XYSeries} instance.
         * @return this.
         */
        public XYDatasetBuilder<T> addSeries(XYSeries<T> series) {
            dataset.addSeries(series);
            return this;
        }

        public XYSeriesCollection<T> build() {
            return dataset;
        }
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
    CategoryDataset<R, C> createCategory(R[] rowKeys, C[] columnKeys,
            double[][] data) {
        requireNonNull(rowKeys, "rowKeys must not be null");
        requireNonNull(columnKeys, "columnKeys must not be null");

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
    public static <R extends Comparable<R>, C extends Comparable<C>>
    DefaultCategoryDataset<R, C> createCategory(@NonNull R rowKey, @NonNull C[] columnKeys,
            double[] data) {
        requireNonNull(rowKey, "rowKeys must not be null");
        requireNonNull(columnKeys, "columnKeys must not be null");

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
     * Create a {@link CategoryDatasetBuilder} to build {@link CategoryDataset}
     *
     * @param <T1> row (series) key type
     * @param <T2> column (category) key type
     * @return {@link CategoryDatasetBuilder} instance.
     */
    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> CategoryDatasetBuilder<T1, T2> category() {
        return new CategoryDatasetBuilder<>();
    }

    /**
     * Create a {@link CategoryDatasetBuilder} to build {@link CategoryDataset}
     *
     * @param <T1> row (series) key type
     * @param <T2> column (category) key type
     * @return {@link CategoryDatasetBuilder} instance.
     */
    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> CategoryDatasetBuilder<T1, T2> category(T2[] categories) {
        return new CategoryDatasetBuilder<>(categories);
    }

    /**
     * Auxiliary class for creating {@link CategoryDataset}.
     */
    public static class CategoryDatasetBuilder<T1 extends Comparable<T1>, T2 extends Comparable<T2>> {

        private final DefaultCategoryDataset<T1, T2> dataset = new DefaultCategoryDataset<>();

        private T2[] categories;

        public CategoryDatasetBuilder() {}

        /**
         * Create a {@link CategoryDataset} with given categories.
         *
         * @param categories categories.
         */
        public CategoryDatasetBuilder(T2[] categories) {
            requireNonNull(categories);
            this.categories = categories;
        }

        /**
         * Add a new series, the categories must have been set via the constructor.
         *
         * @param seriesName series name.
         * @param values     values for the series.
         * @return this.
         */
        public CategoryDatasetBuilder<T1, T2> addSeries(T1 seriesName, double[] values) {
            dataset.addSeries(seriesName, categories, values);
            return this;
        }

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
     * Returns the first {@code count} full English month names, mirroring
     * Chart.js {@code Utils.months({count: 7})}.
     *
     * @param count the number of months, between 1 and 12
     * @return an array such as
     * {"January", "February", ..., "July"} when {@code count} is 7
     */
    public static String[] months(int count) {
        return months(count, TextStyle.FULL, Locale.ENGLISH);
    }

    /**
     * Returns the first {@code count} month names using the given text style
     * (full name or short form) and locale.
     *
     * @param count  the number of months, between 1 and 12
     * @param style  {@link TextStyle#FULL} for full names,
     *               {@link TextStyle#SHORT} for abbreviations
     * @param locale the locale
     * @return an array of month names
     * @throws IllegalArgumentException if {@code count} is not between 1 and 12
     */
    public static String[] months(int count, TextStyle style, Locale locale) {
        if (count < 1 || count > 12) {
            throw new IllegalArgumentException("count must be between 1 and 12, but was " + count);
        }
        String[] result = new String[count];
        for (int i = 0; i < count; i++) {
            result[i] = Month.of(i + 1).getDisplayName(style, locale);
        }
        return result;
    }

    /**
     * Calculates the total of all the values in a {@link PieDataset}.  If
     * the dataset contains negative or {@code null} values, they are
     * ignored.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <K>     the type for the keys.
     * @return The total.
     */
    public static <K extends Comparable<K>> double calculatePieDatasetTotal(PieDataset<K> dataset) {
        requireNonNull(dataset, "dataset");

        double totalValue = 0;
        for (K key : dataset.getKeys()) {
            if (key != null) {
                Number value = dataset.getValue(key);
                double v = 0.0;
                if (value != null) {
                    v = value.doubleValue();
                }
                if (v > 0) {
                    totalValue = totalValue + v;
                }
            }
        }
        return totalValue;
    }

    /**
     * Creates a pie dataset from a table dataset by taking all the values
     * for a single row.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param rowKey  the row key.
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return A pie dataset.
     */
    public static <R extends Comparable<R>, C extends Comparable<C>>
    PieDataset<C> createPieDatasetForRow(CategoryDataset<R, C> dataset, R rowKey) {
        int row = dataset.getRowIndex(rowKey);
        return createPieDatasetForRow(dataset, row);
    }

    /**
     * Creates a pie dataset from a table dataset by taking all the values
     * for a single row.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param row     the row (zero-based index).
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return A pie dataset.
     */
    public static <R extends Comparable<R>, C extends Comparable<C>>
    PieDataset<C> createPieDatasetForRow(CategoryDataset<R, C> dataset, int row) {
        DefaultPieDataset<C> result = new DefaultPieDataset<>();
        int columnCount = dataset.getColumnCount();
        for (int current = 0; current < columnCount; current++) {
            C columnKey = dataset.getColumnKey(current);
            result.setValue(columnKey, dataset.getValue(row, current));
        }
        return result;
    }

    /**
     * Creates a pie dataset from a table dataset by taking all the values
     * for a single column.
     *
     * @param dataset   the dataset ({@code null} not permitted).
     * @param columnKey the column key.
     * @param <R>       the type for the row keys.
     * @param <C>       the type for the column keys.
     * @return A pie dataset.
     */
    public static <R extends Comparable<R>, C extends Comparable<C>>
    PieDataset<R> createPieDatasetForColumn(CategoryDataset<R, C> dataset, C columnKey) {
        int column = dataset.getColumnIndex(columnKey);
        return createPieDatasetForColumn(dataset, column);
    }

    /**
     * Creates a pie dataset from a {@link CategoryDataset} by taking all the
     * values for a single column.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param column  the column (zero-based index).
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return A pie dataset.
     */
    public static <R extends Comparable<R>, C extends Comparable<C>>
    PieDataset<R> createPieDatasetForColumn(CategoryDataset<R, C> dataset, int column) {
        DefaultPieDataset<R> result = new DefaultPieDataset<>();
        int rowCount = dataset.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            R rowKey = dataset.getRowKey(i);
            result.setValue(rowKey, dataset.getValue(i, column));
        }
        return result;
    }

    /**
     * Creates a new pie dataset based on the supplied dataset, but modified
     * by aggregating all the low value items (those whose value is lower
     * than the {@code percentThreshold}) into a single item with the
     * key "Other".
     *
     * @param source         the source dataset ({@code null} not permitted).
     * @param key            a new key for the aggregated items ({@code null} not
     *                       permitted).
     * @param minimumPercent the percent threshold.
     * @param <K>            the type for the data keys.
     * @return The pie dataset with (possibly) aggregated items.
     */
    public static <K extends Comparable<K>> PieDataset<K> createConsolidatedPieDataset(PieDataset<K> source,
            K key, double minimumPercent) {
        return DatasetUtils.createConsolidatedPieDataset(source, key,
                minimumPercent, 2);
    }

    /**
     * Creates a new pie dataset based on the supplied dataset, but modified
     * by aggregating all the low value items (those whose value is lower
     * than the {@code percentThreshold}) into a single item.  The
     * aggregated items are assigned the specified key.  Aggregation only
     * occurs if there are at least {@code minItems} items to aggregate.
     *
     * @param source         the source dataset ({@code null} not permitted).
     * @param key            the key to represent the aggregated items.
     * @param minimumPercent the percent threshold (ten percent is 0.10).
     * @param minItems       only aggregate low values if there are at least this
     *                       many.
     * @param <K>            the type for the data keys.
     * @return The pie dataset with (possibly) aggregated items.
     */
    public static <K extends Comparable<K>> PieDataset<K> createConsolidatedPieDataset(
            PieDataset<K> source, K key, double minimumPercent, int minItems) {

        DefaultPieDataset<K> result = new DefaultPieDataset<>();
        double total = DatasetUtils.calculatePieDatasetTotal(source);

        //  Iterate and find all keys below threshold percentThreshold
        List<K> keys = source.getKeys();
        List<K> otherKeys = new ArrayList<>();
        Iterator<K> iterator = keys.iterator();
        while (iterator.hasNext()) {
            K currentKey = iterator.next();
            Number dataValue = source.getValue(currentKey);
            if (dataValue != null) {
                double value = dataValue.doubleValue();
                if (value / total < minimumPercent) {
                    otherKeys.add(currentKey);
                }
            }
        }

        //  Create new dataset with keys above threshold percentThreshold
        iterator = keys.iterator();
        double otherValue = 0;
        while (iterator.hasNext()) {
            K currentKey = iterator.next();
            Number dataValue = source.getValue(currentKey);
            if (dataValue != null) {
                if (otherKeys.contains(currentKey)
                        && otherKeys.size() >= minItems) {
                    //  Do not add key to dataset
                    otherValue += dataValue.doubleValue();
                } else {
                    //  Add key to dataset
                    result.setValue(currentKey, dataValue);
                }
            }
        }
        //  Add other category if applicable
        if (otherKeys.size() >= minItems) {
            result.setValue(key, otherValue);
        }
        return result;
    }

    /**
     * Creates a {@link CategoryDataset} that contains a copy of the data in an
     * array (instances of {@code double} are created to represent the
     * data items).
     * <p>
     * Row and column keys are created by appending 0, 1, 2, ... to the
     * supplied prefixes.
     *
     * @param rowKeyPrefix    the row key prefix.
     * @param columnKeyPrefix the column key prefix.
     * @param data            the data.
     * @return The dataset.
     */
    public static CategoryDataset<String, String> createCategoryDataset(
            String rowKeyPrefix, String columnKeyPrefix, double[][] data) {

        DefaultCategoryDataset<String, String> result = new DefaultCategoryDataset<>();
        for (int r = 0; r < data.length; r++) {
            String rowKey = rowKeyPrefix + (r + 1);
            for (int c = 0; c < data[r].length; c++) {
                String columnKey = columnKeyPrefix + (c + 1);
                result.addValue(data[r][c], rowKey, columnKey);
            }
        }
        return result;

    }

    /**
     * Creates a {@link CategoryDataset} that contains a copy of the data in
     * an array.
     * <p>
     * Row and column keys are created by appending 0, 1, 2, ... to the
     * supplied prefixes.
     *
     * @param rowKeyPrefix    the row key prefix.
     * @param columnKeyPrefix the column key prefix.
     * @param data            the data.
     * @return The dataset.
     */
    public static CategoryDataset<String, String> createCategoryDataset(
            String rowKeyPrefix, String columnKeyPrefix, Number[][] data) {

        DefaultCategoryDataset<String, String> result
                = new DefaultCategoryDataset<>();
        for (int r = 0; r < data.length; r++) {
            String rowKey = rowKeyPrefix + (r + 1);
            for (int c = 0; c < data[r].length; c++) {
                String columnKey = columnKeyPrefix + (c + 1);
                result.addValue(data[r][c], rowKey, columnKey);
            }
        }
        return result;

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
    public static <R extends Comparable<R>, C extends Comparable<C>>
    CategoryDataset<R, C> createCategoryDataset(R[] rowKeys, C[] columnKeys,
            double[][] data) {
        requireNonNull(rowKeys, "rowKeys");
        requireNonNull(columnKeys, "columnKeys");

        if (ArrayUtils.hasDuplicateItems(rowKeys)) {
            throw new IllegalArgumentException("Duplicate items in 'rowKeys'.");
        }
        if (ArrayUtils.hasDuplicateItems(columnKeys)) {
            throw new IllegalArgumentException(
                    "Duplicate items in 'columnKeys'.");
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
     * Creates a {@link CategoryDataset} by copying the data from the supplied
     * {@link KeyedValues} instance.
     *
     * @param rowKey  the row key ({@code null} not permitted).
     * @param rowData the row data ({@code null} not permitted).
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return A dataset.
     */
    public static <R extends Comparable<R>, C extends Comparable<C>>
    CategoryDataset<R, C> createCategoryDataset(R rowKey,
            KeyedValues<C> rowData) {

        Args.nullNotPermitted(rowKey, "rowKey");
        Args.nullNotPermitted(rowData, "rowData");
        DefaultCategoryDataset<R, C> result = new DefaultCategoryDataset<>();
        for (int i = 0; i < rowData.getItemCount(); i++) {
            result.addValue(rowData.getValue(i), rowKey, rowData.getKey(i));
        }
        return result;
    }

    /**
     * Returns {@code true} if the dataset is empty (or {@code null}),
     * and {@code false} otherwise.
     *
     * @param dataset the dataset ({@code null} permitted).
     * @return A boolean.
     */
    public static boolean isEmptyOrNull(PieDataset<?> dataset) {
        if (dataset == null) {
            return true;
        }
        int itemCount = dataset.getItemCount();
        if (itemCount == 0) {
            return true;
        }
        for (int item = 0; item < itemCount; item++) {
            Number y = dataset.getValue(item);
            if (y != null) {
                double yy = y.doubleValue();
                if (yy > 0.0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns {@code true} if the dataset is empty (or {@code null}),
     * and {@code false} otherwise.
     *
     * @param dataset the dataset ({@code null} permitted).
     * @return A boolean.
     */
    public static boolean isEmptyOrNull(PeptideDataset dataset) {
        if (dataset == null) {
            return true;
        }
        char[] value = dataset.getValue();
        return value == null || value.length == 0;
    }

    /**
     * Returns {@code true} if the dataset is empty (or {@code null}),
     * and {@code false} otherwise.
     *
     * @param dataset the dataset ({@code null} permitted).
     * @return A boolean.
     */
    public static boolean isEmptyOrNull(CategoryDataset<?, ?> dataset) {
        if (dataset == null) {
            return true;
        }
        int rowCount = dataset.getRowCount();
        int columnCount = dataset.getColumnCount();
        if (rowCount == 0 || columnCount == 0) {
            return true;
        }
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                if (dataset.getValue(r, c) != null) {
                    return false;
                }

            }
        }
        return true;
    }

    /**
     * Returns {@code true} if the dataset is empty (or {@code null}),
     * and {@code false} otherwise.
     *
     * @param dataset the dataset ({@code null} permitted).
     * @param <S>     the type for the series keys.
     * @return A boolean.
     */
    public static <S extends Comparable<S>> boolean isEmptyOrNull(
            XYDataset<S> dataset) {
        if (dataset != null) {
            for (int s = 0; s < dataset.getSeriesCount(); s++) {
                if (dataset.getItemCount(s) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the range of values in the domain (x-values) of a dataset.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <S>     the type for the series keys.
     * @return The range of values (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Range findDomainBounds(XYDataset<S> dataset) {
        return findDomainBounds(dataset, true);
    }

    /**
     * Returns the range of values in the domain (x-values) of a dataset.
     *
     * @param dataset         the dataset ({@code null} not permitted).
     * @param includeInterval determines whether the x-interval is taken
     *                        into account (only applies if the dataset is an
     *                        {@link IntervalXYDataset}).
     * @param <S>             the type for the series keys.
     * @return The range of values (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Range findDomainBounds(
            XYDataset<S> dataset, boolean includeInterval) {
        requireNonNull(dataset, "dataset");
        Range result;
        // if the dataset implements DomainInfo, life is easier
        if (dataset instanceof DomainInfo info) {
            result = info.getDomainBounds(includeInterval);
        } else {
            result = iterateDomainBounds(dataset, includeInterval);
        }
        return result;
    }

    /**
     * Returns the bounds of the x-values in the specified {@code dataset}
     * taking into account only the visible series and including any x-interval
     * if requested.
     *
     * @param dataset           the dataset ({@code null} not permitted).
     * @param visibleSeriesKeys the visible series keys ({@code null}
     *                          not permitted).
     * @param includeInterval   include the x-interval (if any)?
     * @param <S>               the type for the series keys.
     * @return The bounds (or {@code null} if the dataset contains no
     * values).
     * @since 1.0.13
     */
    public static <S extends Comparable<S>> Range findDomainBounds(
            XYDataset<S> dataset, List<S> visibleSeriesKeys,
            boolean includeInterval) {
        requireNonNull(dataset, "dataset");
        Range result;
        if (dataset instanceof XYDomainInfo) {
            @SuppressWarnings("unchecked")
            XYDomainInfo<S> info = (XYDomainInfo) dataset;
            result = info.getDomainBounds(visibleSeriesKeys, includeInterval);
        } else {
            result = iterateToFindDomainBounds(dataset, visibleSeriesKeys,
                    includeInterval);
        }
        return result;
    }

    /**
     * Iterates over the items in an {@link XYDataset} to find
     * the range of x-values.  If the dataset is an instance of
     * {@link IntervalXYDataset}, the starting and ending x-values
     * will be used for the bounds calculation.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <S>     the type for the series keys.
     * @return The range (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Range iterateDomainBounds(
            XYDataset<S> dataset) {
        return iterateDomainBounds(dataset, true);
    }

    /**
     * Iterates over the items in an {@link XYDataset} to find
     * the range of x-values.
     *
     * @param dataset         the dataset ({@code null} not permitted).
     * @param includeInterval a flag that determines, for an
     *                        {@link IntervalXYDataset}, whether the x-interval or just the
     *                        x-value is used to determine the overall range.
     * @param <S>             the type for the series keys.
     * @return The range (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Range iterateDomainBounds(
            XYDataset<S> dataset, boolean includeInterval) {
        requireNonNull(dataset, "dataset");
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        int seriesCount = dataset.getSeriesCount();
        double lvalue, uvalue;
        if (includeInterval && dataset instanceof IntervalXYDataset) {
            @SuppressWarnings("unchecked")
            IntervalXYDataset<S> intervalXYData = (IntervalXYDataset) dataset;
            for (int series = 0; series < seriesCount; series++) {
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double value = intervalXYData.getXValue(series, item);
                    lvalue = intervalXYData.getStartXValue(series, item);
                    uvalue = intervalXYData.getEndXValue(series, item);
                    if (!Double.isNaN(value)) {
                        minimum = Math.min(minimum, value);
                        maximum = Math.max(maximum, value);
                    }
                    if (!Double.isNaN(lvalue)) {
                        minimum = Math.min(minimum, lvalue);
                        maximum = Math.max(maximum, lvalue);
                    }
                    if (!Double.isNaN(uvalue)) {
                        minimum = Math.min(minimum, uvalue);
                        maximum = Math.max(maximum, uvalue);
                    }
                }
            }
        } else {
            for (int series = 0; series < seriesCount; series++) {
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    lvalue = dataset.getXValue(series, item);
                    uvalue = lvalue;
                    if (!Double.isNaN(lvalue)) {
                        minimum = Math.min(minimum, lvalue);
                        maximum = Math.max(maximum, uvalue);
                    }
                }
            }
        }
        if (minimum > maximum) {
            return null;
        } else {
            return new Range(minimum, maximum);
        }
    }

    /**
     * Returns the range of values in the range for the dataset.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return The range (possibly {@code null}).
     */
    public static <R extends Comparable<R>, C extends Comparable<C>> Range
    findRangeBounds(CategoryDataset<R, C> dataset) {
        return findRangeBounds(dataset, true);
    }

    /**
     * Returns the range of values in the range for the dataset.
     *
     * @param dataset         the dataset ({@code null} not permitted).
     * @param includeInterval a flag that determines whether the
     *                        y-interval is taken into account.
     * @param <R>             the type for the row keys.
     * @param <C>             the type for the column keys.
     * @return The range (possibly {@code null}).
     */
    public static <R extends Comparable<R>, C extends Comparable<C>> Range
    findRangeBounds(CategoryDataset<R, C> dataset,
            boolean includeInterval) {
        Args.nullNotPermitted(dataset, "dataset");
        Range result;
        if (dataset instanceof RangeInfo) {
            RangeInfo info = (RangeInfo) dataset;
            result = info.getRangeBounds(includeInterval);
        } else {
            result = iterateRangeBounds(dataset, includeInterval);
        }
        return result;
    }

    /**
     * Finds the bounds of the y-values in the specified dataset, including
     * only those series that are listed in visibleSeriesKeys.
     *
     * @param dataset           the dataset ({@code null} not permitted).
     * @param visibleSeriesKeys the keys for the visible series
     *                          ({@code null} not permitted).
     * @param includeInterval   include the y-interval (if the dataset has a
     *                          y-interval).
     * @param <R>               the type for the row keys.
     * @param <C>               the type for the column keys.
     * @return The data bounds.
     * @since 1.0.13
     */
    public static <R extends Comparable<R>, C extends Comparable<C>>
    Range findRangeBounds(CategoryDataset<R, C> dataset,
            List<R> visibleSeriesKeys, boolean includeInterval) {
        Args.nullNotPermitted(dataset, "dataset");
        Range result;
        if (dataset instanceof CategoryRangeInfo) {
            CategoryRangeInfo info = (CategoryRangeInfo) dataset;
            result = info.getRangeBounds(visibleSeriesKeys, includeInterval);
        } else {
            result = iterateToFindRangeBounds(dataset, visibleSeriesKeys,
                    includeInterval);
        }
        return result;
    }

    /**
     * Returns the range of values in the range for the dataset.  This method
     * is the partner for the {@link #findDomainBounds(XYDataset)} method.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <S>     the type for the series keys.
     * @return The range (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Range findRangeBounds(
            XYDataset<S> dataset) {
        return findRangeBounds(dataset, true);
    }

    /**
     * Returns the range of values in the range for the dataset.  This method
     * is the partner for the {@link #findDomainBounds(XYDataset, boolean)}
     * method.
     *
     * @param dataset         the dataset ({@code null} not permitted).
     * @param includeInterval a flag that determines whether the
     *                        y-interval is taken into account.
     * @param <S>             the type for the series keys.
     * @return The range (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Range findRangeBounds(
            XYDataset<S> dataset, boolean includeInterval) {
        Args.nullNotPermitted(dataset, "dataset");
        Range result;
        if (dataset instanceof RangeInfo) {
            RangeInfo info = (RangeInfo) dataset;
            result = info.getRangeBounds(includeInterval);
        } else {
            result = iterateRangeBounds(dataset, includeInterval);
        }
        return result;
    }

    /**
     * Finds the bounds of the y-values in the specified dataset, including
     * only those series that are listed in visibleSeriesKeys, and those items
     * whose x-values fall within the specified range.
     *
     * @param dataset           the dataset ({@code null} not permitted).
     * @param visibleSeriesKeys the keys for the visible series
     *                          ({@code null} not permitted).
     * @param xRange            the x-range ({@code null} not permitted).
     * @param includeInterval   include the y-interval (if the dataset has a
     *                          y-interval).
     * @param <S>               the type for the series keys.
     * @return The data bounds.
     * @since 1.0.13
     */
    public static <S extends Comparable<S>> Range findRangeBounds(
            XYDataset<S> dataset, List<S> visibleSeriesKeys, Range xRange,
            boolean includeInterval) {
        Args.nullNotPermitted(dataset, "dataset");
        Range result;
        if (dataset instanceof XYRangeInfo) {
            XYRangeInfo info = (XYRangeInfo) dataset;
            result = info.getRangeBounds(visibleSeriesKeys, xRange,
                    includeInterval);
        } else {
            result = iterateToFindRangeBounds(dataset, visibleSeriesKeys,
                    xRange, includeInterval);
        }
        return result;
    }

    /**
     * Iterates over the data item of the category dataset to find
     * the range bounds.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return The range (possibly {@code null}).
     * @since 1.0.10
     */
    public static <R extends Comparable<R>, C extends Comparable<C>> Range
    iterateRangeBounds(CategoryDataset<R, C> dataset) {
        return iterateRangeBounds(dataset, true);
    }

    /**
     * Iterates over the data item of the category dataset to find
     * the range bounds.
     *
     * @param dataset         the dataset ({@code null} not permitted).
     * @param includeInterval a flag that determines whether the
     *                        y-interval is taken into account.
     * @param <R>             the type for the row keys.
     * @param <C>             the type for the column keys.
     * @return The range (possibly {@code null}).
     * @since 1.0.10
     */
    public static <R extends Comparable<R>, C extends Comparable<C>> Range
    iterateRangeBounds(CategoryDataset<R, C> dataset,
            boolean includeInterval) {
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        int rowCount = dataset.getRowCount();
        int columnCount = dataset.getColumnCount();
        if (includeInterval && dataset instanceof IntervalCategoryDataset) {
            // handle the special case where the dataset has y-intervals that
            // we want to measure
            @SuppressWarnings("unchecked")
            IntervalCategoryDataset<R, C> icd = (IntervalCategoryDataset) dataset;
            Number value, lvalue, uvalue;
            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    value = icd.getValue(row, column);
                    double v;
                    if ((value != null)
                            && !Double.isNaN(v = value.doubleValue())) {
                        minimum = Math.min(v, minimum);
                        maximum = Math.max(v, maximum);
                    }
                    lvalue = icd.getStartValue(row, column);
                    if (lvalue != null
                            && !Double.isNaN(v = lvalue.doubleValue())) {
                        minimum = Math.min(v, minimum);
                        maximum = Math.max(v, maximum);
                    }
                    uvalue = icd.getEndValue(row, column);
                    if (uvalue != null
                            && !Double.isNaN(v = uvalue.doubleValue())) {
                        minimum = Math.min(v, minimum);
                        maximum = Math.max(v, maximum);
                    }
                }
            }
        } else {
            // handle the standard case (plain CategoryDataset)
            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    Number value = dataset.getValue(row, column);
                    if (value != null) {
                        double v = value.doubleValue();
                        if (!Double.isNaN(v)) {
                            minimum = Math.min(minimum, v);
                            maximum = Math.max(maximum, v);
                        }
                    }
                }
            }
        }
        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        } else {
            return new Range(minimum, maximum);
        }
    }

    /**
     * Iterates over the data item of the category dataset to find
     * the range bounds.
     *
     * @param dataset           the dataset ({@code null} not permitted).
     * @param includeInterval   a flag that determines whether the
     *                          y-interval is taken into account.
     * @param visibleSeriesKeys the visible series keys.
     * @param <R>               the type for the row keys.
     * @param <C>               the type for the column keys.
     * @return The range (possibly {@code null}).
     * @since 1.0.13
     */
    public static <R extends Comparable<R>, C extends Comparable<C>>
    Range iterateToFindRangeBounds(CategoryDataset<R, C> dataset,
            List<R> visibleSeriesKeys, boolean includeInterval) {

        Args.nullNotPermitted(dataset, "dataset");
        Args.nullNotPermitted(visibleSeriesKeys, "visibleSeriesKeys");

        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        int columnCount = dataset.getColumnCount();
        if (includeInterval
                && dataset instanceof BoxAndWhiskerCategoryDataset) {
            // handle special case of BoxAndWhiskerDataset
            @SuppressWarnings("unchecked")
            BoxAndWhiskerCategoryDataset<R, C> bx
                    = (BoxAndWhiskerCategoryDataset) dataset;
            for (R seriesKey : visibleSeriesKeys) {
                int series = dataset.getRowIndex(seriesKey);
                int itemCount = dataset.getColumnCount();
                for (int item = 0; item < itemCount; item++) {
                    Number lvalue = bx.getMinRegularValue(series, item);
                    if (lvalue == null) {
                        lvalue = bx.getValue(series, item);
                    }
                    Number uvalue = bx.getMaxRegularValue(series, item);
                    if (uvalue == null) {
                        uvalue = bx.getValue(series, item);
                    }
                    if (lvalue != null) {
                        minimum = Math.min(minimum, lvalue.doubleValue());
                    }
                    if (uvalue != null) {
                        maximum = Math.max(maximum, uvalue.doubleValue());
                    }
                }
            }
        } else if (includeInterval
                && dataset instanceof IntervalCategoryDataset) {
            // handle the special case where the dataset has y-intervals that
            // we want to measure
            @SuppressWarnings("unchecked")
            IntervalCategoryDataset<R, C> icd = (IntervalCategoryDataset) dataset;
            Number lvalue, uvalue;
            for (R seriesKey : visibleSeriesKeys) {
                int series = dataset.getRowIndex(seriesKey);
                for (int column = 0; column < columnCount; column++) {
                    lvalue = icd.getStartValue(series, column);
                    uvalue = icd.getEndValue(series, column);
                    if (lvalue != null && !Double.isNaN(lvalue.doubleValue())) {
                        minimum = Math.min(minimum, lvalue.doubleValue());
                    }
                    if (uvalue != null && !Double.isNaN(uvalue.doubleValue())) {
                        maximum = Math.max(maximum, uvalue.doubleValue());
                    }
                }
            }
        } else if (includeInterval
                && dataset instanceof MultiValueCategoryDataset) {
            // handle the special case where the dataset has y-intervals that
            // we want to measure
            @SuppressWarnings("unchecked")
            MultiValueCategoryDataset<R, C> mvcd
                    = (MultiValueCategoryDataset) dataset;
            for (R seriesKey : visibleSeriesKeys) {
                int series = dataset.getRowIndex(seriesKey);
                for (int column = 0; column < columnCount; column++) {
                    List<? extends Number> values = mvcd.getValues(series, column);
                    for (Number n : values) {
                        double v = n.doubleValue();
                        if (!Double.isNaN(v)) {
                            minimum = Math.min(minimum, v);
                            maximum = Math.max(maximum, v);
                        }
                    }
                }
            }
        } else if (includeInterval
                && dataset instanceof StatisticalCategoryDataset) {
            // handle the special case where the dataset has y-intervals that
            // we want to measure
            @SuppressWarnings("unchecked")
            StatisticalCategoryDataset<R, C> scd
                    = (StatisticalCategoryDataset) dataset;
            for (R seriesKey : visibleSeriesKeys) {
                int series = dataset.getRowIndex(seriesKey);
                for (int column = 0; column < columnCount; column++) {
                    Number meanN = scd.getMeanValue(series, column);
                    if (meanN != null) {
                        double std = 0.0;
                        Number stdN = scd.getStdDevValue(series, column);
                        if (stdN != null) {
                            std = stdN.doubleValue();
                            if (Double.isNaN(std)) {
                                std = 0.0;
                            }
                        }
                        double mean = meanN.doubleValue();
                        if (!Double.isNaN(mean)) {
                            minimum = Math.min(minimum, mean - std);
                            maximum = Math.max(maximum, mean + std);
                        }
                    }
                }
            }
        } else {
            // handle the standard case (plain CategoryDataset)
            for (R seriesKey : visibleSeriesKeys) {
                int series = dataset.getRowIndex(seriesKey);
                for (int column = 0; column < columnCount; column++) {
                    Number value = dataset.getValue(series, column);
                    if (value != null) {
                        double v = value.doubleValue();
                        if (!Double.isNaN(v)) {
                            minimum = Math.min(minimum, v);
                            maximum = Math.max(maximum, v);
                        }
                    }
                }
            }
        }
        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        } else {
            return new Range(minimum, maximum);
        }
    }

    /**
     * Iterates over the data item of the xy dataset to find
     * the range bounds.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <S>     the type for the series keys.
     * @return The range (possibly {@code null}).
     * @since 1.0.10
     */
    public static <S extends Comparable<S>> Range iterateRangeBounds(
            XYDataset<S> dataset) {
        return iterateRangeBounds(dataset, true);
    }

    /**
     * Iterates over the data items of the xy dataset to find
     * the range bounds.
     *
     * @param dataset         the dataset ({@code null} not permitted).
     * @param includeInterval a flag that determines, for an
     *                        {@link IntervalXYDataset}, whether the y-interval or just the
     *                        y-value is used to determine the overall range.
     * @param <S>             the type for the series keys.
     * @return The range (possibly {@code null}).
     * @since 1.0.10
     */
    public static <S extends Comparable<S>> Range iterateRangeBounds(
            XYDataset<S> dataset, boolean includeInterval) {
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        int seriesCount = dataset.getSeriesCount();

        // handle three cases by dataset type
        if (includeInterval && dataset instanceof IntervalXYDataset) {
            // handle special case of IntervalXYDataset
            @SuppressWarnings("unchecked")
            IntervalXYDataset<S> ixyd = (IntervalXYDataset) dataset;
            for (int series = 0; series < seriesCount; series++) {
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double value = ixyd.getYValue(series, item);
                    double lvalue = ixyd.getStartYValue(series, item);
                    double uvalue = ixyd.getEndYValue(series, item);
                    if (!Double.isNaN(value)) {
                        minimum = Math.min(minimum, value);
                        maximum = Math.max(maximum, value);
                    }
                    if (!Double.isNaN(lvalue)) {
                        minimum = Math.min(minimum, lvalue);
                        maximum = Math.max(maximum, lvalue);
                    }
                    if (!Double.isNaN(uvalue)) {
                        minimum = Math.min(minimum, uvalue);
                        maximum = Math.max(maximum, uvalue);
                    }
                }
            }
        } else if (includeInterval && dataset instanceof OHLCDataset) {
            // handle special case of OHLCDataset
            OHLCDataset ohlc = (OHLCDataset) dataset;
            for (int series = 0; series < seriesCount; series++) {
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double lvalue = ohlc.getLowValue(series, item);
                    double uvalue = ohlc.getHighValue(series, item);
                    if (!Double.isNaN(lvalue)) {
                        minimum = Math.min(minimum, lvalue);
                    }
                    if (!Double.isNaN(uvalue)) {
                        maximum = Math.max(maximum, uvalue);
                    }
                }
            }
        } else {
            // standard case - plain XYDataset
            for (int series = 0; series < seriesCount; series++) {
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double value = dataset.getYValue(series, item);
                    if (!Double.isNaN(value)) {
                        minimum = Math.min(minimum, value);
                        maximum = Math.max(maximum, value);
                    }
                }
            }
        }
        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        } else {
            return new Range(minimum, maximum);
        }
    }

    /**
     * Returns the range of values in the z-dimension for the dataset. This
     * method is the partner for the {@link #findRangeBounds(XYDataset)}
     * and {@link #findDomainBounds(XYDataset)} methods.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <S>     the type for the series keys.
     * @return The range (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Range findZBounds(
            XYZDataset<S> dataset) {
        return findZBounds(dataset, true);
    }

    /**
     * Returns the range of values in the z-dimension for the dataset.  This
     * method is the partner for the
     * {@link #findRangeBounds(XYDataset, boolean)} and
     * {@link #findDomainBounds(XYDataset, boolean)} methods.
     *
     * @param dataset         the dataset ({@code null} not permitted).
     * @param includeInterval a flag that determines whether the
     *                        z-interval is taken into account.
     * @param <S>             the type for the series keys.
     * @return The range (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Range findZBounds(
            XYZDataset<S> dataset, boolean includeInterval) {
        Args.nullNotPermitted(dataset, "dataset");
        Range result = iterateZBounds(dataset, includeInterval);
        return result;
    }

    /**
     * Finds the bounds of the z-values in the specified dataset, including
     * only those series that are listed in visibleSeriesKeys, and those items
     * whose x-values fall within the specified range.
     *
     * @param dataset           the dataset ({@code null} not permitted).
     * @param visibleSeriesKeys the keys for the visible series
     *                          ({@code null} not permitted).
     * @param xRange            the x-range ({@code null} not permitted).
     * @param includeInterval   include the z-interval (if the dataset has a
     *                          z-interval).
     * @param <S>               the type for the series keys.
     * @return The data bounds.
     */
    public static <S extends Comparable<S>> Range findZBounds(
            XYZDataset<S> dataset, List<S> visibleSeriesKeys, Range xRange,
            boolean includeInterval) {
        Args.nullNotPermitted(dataset, "dataset");
        Range result = iterateToFindZBounds(dataset, visibleSeriesKeys,
                xRange, includeInterval);
        return result;
    }

    /**
     * Iterates over the data item of the xyz dataset to find
     * the z-dimension bounds.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <S>     the type for the series keys.
     * @return The range (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Range iterateZBounds(
            XYZDataset<S> dataset) {
        return iterateZBounds(dataset, true);
    }

    /**
     * Iterates over the data items of the xyz dataset to find
     * the z-dimension bounds.
     *
     * @param dataset         the dataset ({@code null} not permitted).
     * @param includeInterval include the z-interval (if the dataset has a
     *                        z-interval.
     * @param <S>             the type for the series keys.
     * @return The range (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Range iterateZBounds(
            XYZDataset<S> dataset, boolean includeInterval) {
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        int seriesCount = dataset.getSeriesCount();

        if (includeInterval && dataset instanceof IntervalXYZDataset) {
            @SuppressWarnings("unchecked")
            IntervalXYZDataset<S> intervalDataset = (IntervalXYZDataset) dataset;
            for (int series = 0; series < seriesCount; series++) {
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    // first apply the z-value itself
                    double value = dataset.getZValue(series, item);
                    if (!Double.isNaN(value)) {
                        minimum = Math.min(minimum, value);
                        maximum = Math.max(maximum, value);
                    }

                    Number start = intervalDataset.getStartZValue(series, item);
                    if (start != null && !Double.isNaN(start.doubleValue())) {
                        minimum = Math.min(minimum, start.doubleValue());
                        maximum = Math.max(maximum, start.doubleValue());
                    }
                    Number end = intervalDataset.getEndZValue(series, item);
                    if (end != null && !Double.isNaN(end.doubleValue())) {
                        minimum = Math.min(minimum, end.doubleValue());
                        maximum = Math.max(maximum, end.doubleValue());
                    }
                }
            }
        } else {
            for (int series = 0; series < seriesCount; series++) {
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double value = dataset.getZValue(series, item);
                    if (!Double.isNaN(value)) {
                        minimum = Math.min(minimum, value);
                        maximum = Math.max(maximum, value);
                    }
                }
            }
        }

        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        } else {
            return new Range(minimum, maximum);
        }
    }

    /**
     * Returns the range of x-values in the specified dataset for the
     * data items belonging to the visible series.
     *
     * @param dataset           the dataset ({@code null} not permitted).
     * @param visibleSeriesKeys the visible series keys ({@code null} not
     *                          permitted).
     * @param includeInterval   a flag that determines whether the
     *                          y-interval for the dataset is included (this only applies if the
     *                          dataset is an instance of IntervalXYDataset).
     * @param <S>               the type for the series keys.
     * @return The x-range (possibly {@code null}).
     * @since 1.0.13
     */
    public static <S extends Comparable<S>> Range iterateToFindDomainBounds(
            XYDataset<S> dataset, List<S> visibleSeriesKeys,
            boolean includeInterval) {
        Args.nullNotPermitted(dataset, "dataset");
        Args.nullNotPermitted(visibleSeriesKeys, "visibleSeriesKeys");

        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;

        if (includeInterval && dataset instanceof IntervalXYDataset) {
            // handle special case of IntervalXYDataset
            @SuppressWarnings("unchecked")
            IntervalXYDataset<S> ixyd = (IntervalXYDataset) dataset;
            for (S seriesKey : visibleSeriesKeys) {
                int series = dataset.indexOf(seriesKey);
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double xvalue = ixyd.getXValue(series, item);
                    double lvalue = ixyd.getStartXValue(series, item);
                    double uvalue = ixyd.getEndXValue(series, item);
                    if (!Double.isNaN(xvalue)) {
                        minimum = Math.min(minimum, xvalue);
                        maximum = Math.max(maximum, xvalue);
                    }
                    if (!Double.isNaN(lvalue)) {
                        minimum = Math.min(minimum, lvalue);
                    }
                    if (!Double.isNaN(uvalue)) {
                        maximum = Math.max(maximum, uvalue);
                    }
                }
            }
        } else {
            // standard case - plain XYDataset
            for (S seriesKey : visibleSeriesKeys) {
                int series = dataset.indexOf(seriesKey);
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double x = dataset.getXValue(series, item);
                    if (!Double.isNaN(x)) {
                        minimum = Math.min(minimum, x);
                        maximum = Math.max(maximum, x);
                    }
                }
            }
        }

        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        } else {
            return new Range(minimum, maximum);
        }
    }

    /**
     * Returns the range of y-values in the specified dataset for the
     * data items belonging to the visible series and with x-values in the
     * given range.
     *
     * @param dataset           the dataset ({@code null} not permitted).
     * @param visibleSeriesKeys the visible series keys ({@code null} not
     *                          permitted).
     * @param xRange            the x-range ({@code null} not permitted).
     * @param includeInterval   a flag that determines whether the
     *                          y-interval for the dataset is included (this only applies if the
     *                          dataset is an instance of IntervalXYDataset).
     * @param <S>               the type for the series keys.
     * @return The y-range (possibly {@code null}).
     * @since 1.0.13
     */
    public static <S extends Comparable<S>> Range iterateToFindRangeBounds(
            XYDataset<S> dataset, List<S> visibleSeriesKeys, Range xRange,
            boolean includeInterval) {

        Args.nullNotPermitted(dataset, "dataset");
        Args.nullNotPermitted(visibleSeriesKeys, "visibleSeriesKeys");
        Args.nullNotPermitted(xRange, "xRange");

        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;

        // handle three cases by dataset type
        if (includeInterval && dataset instanceof OHLCDataset) {
            // handle special case of OHLCDataset
            OHLCDataset ohlc = (OHLCDataset) dataset;
            for (S seriesKey : visibleSeriesKeys) {
                int series = dataset.indexOf(seriesKey);
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double x = ohlc.getXValue(series, item);
                    if (xRange.contains(x)) {
                        double lvalue = ohlc.getLowValue(series, item);
                        double uvalue = ohlc.getHighValue(series, item);
                        if (!Double.isNaN(lvalue)) {
                            minimum = Math.min(minimum, lvalue);
                        }
                        if (!Double.isNaN(uvalue)) {
                            maximum = Math.max(maximum, uvalue);
                        }
                    }
                }
            }
        } else if (includeInterval && dataset instanceof BoxAndWhiskerXYDataset) {
            // handle special case of BoxAndWhiskerXYDataset
            @SuppressWarnings("unchecked")
            BoxAndWhiskerXYDataset<S> bx = (BoxAndWhiskerXYDataset) dataset;
            for (S seriesKey : visibleSeriesKeys) {
                int series = dataset.indexOf(seriesKey);
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double x = bx.getXValue(series, item);
                    if (xRange.contains(x)) {
                        Number lvalue = bx.getMinRegularValue(series, item);
                        Number uvalue = bx.getMaxRegularValue(series, item);
                        if (lvalue != null) {
                            minimum = Math.min(minimum, lvalue.doubleValue());
                        }
                        if (uvalue != null) {
                            maximum = Math.max(maximum, uvalue.doubleValue());
                        }
                    }
                }
            }
        } else if (includeInterval && dataset instanceof IntervalXYDataset) {
            // handle special case of IntervalXYDataset
            @SuppressWarnings("unchecked")
            IntervalXYDataset<S> ixyd = (IntervalXYDataset) dataset;
            for (S seriesKey : visibleSeriesKeys) {
                int series = dataset.indexOf(seriesKey);
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double x = ixyd.getXValue(series, item);
                    if (xRange.contains(x)) {
                        double yvalue = ixyd.getYValue(series, item);
                        double lvalue = ixyd.getStartYValue(series, item);
                        double uvalue = ixyd.getEndYValue(series, item);
                        if (!Double.isNaN(yvalue)) {
                            minimum = Math.min(minimum, yvalue);
                            maximum = Math.max(maximum, yvalue);
                        }
                        if (!Double.isNaN(lvalue)) {
                            minimum = Math.min(minimum, lvalue);
                        }
                        if (!Double.isNaN(uvalue)) {
                            maximum = Math.max(maximum, uvalue);
                        }
                    }
                }
            }
        } else {
            // standard case - plain XYDataset
            for (S seriesKey : visibleSeriesKeys) {
                int series = dataset.indexOf(seriesKey);
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double x = dataset.getXValue(series, item);
                    double y = dataset.getYValue(series, item);
                    if (xRange.contains(x)) {
                        if (!Double.isNaN(y)) {
                            minimum = Math.min(minimum, y);
                            maximum = Math.max(maximum, y);
                        }
                    }
                }
            }
        }
        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        } else {
            return new Range(minimum, maximum);
        }
    }

    /**
     * Returns the range of z-values in the specified dataset for the
     * data items belonging to the visible series and with x-values in the
     * given range.
     *
     * @param dataset           the dataset ({@code null} not permitted).
     * @param visibleSeriesKeys the visible series keys ({@code null} not
     *                          permitted).
     * @param xRange            the x-range ({@code null} not permitted).
     * @param includeInterval   a flag that determines whether the
     *                          z-interval for the dataset is included (this only applies if the
     *                          dataset has an interval, which is currently not supported).
     * @param <S>               the type for the series keys.
     * @return The y-range (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Range iterateToFindZBounds(
            XYZDataset<S> dataset, List<S> visibleSeriesKeys, Range xRange,
            boolean includeInterval) {
        Args.nullNotPermitted(dataset, "dataset");
        Args.nullNotPermitted(visibleSeriesKeys, "visibleSeriesKeys");
        Args.nullNotPermitted(xRange, "xRange");

        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;

        for (S seriesKey : visibleSeriesKeys) {
            int series = dataset.indexOf(seriesKey);
            int itemCount = dataset.getItemCount(series);
            for (int item = 0; item < itemCount; item++) {
                double x = dataset.getXValue(series, item);
                double z = dataset.getZValue(series, item);
                if (xRange.contains(x)) {
                    if (!Double.isNaN(z)) {
                        minimum = Math.min(minimum, z);
                        maximum = Math.max(maximum, z);
                    }
                }
            }
        }

        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        } else {
            return new Range(minimum, maximum);
        }
    }

    /**
     * Finds the minimum domain (or X) value for the specified dataset.  This
     * is easy if the dataset implements the {@link DomainInfo} interface (a
     * good idea if there is an efficient way to determine the minimum value).
     * Otherwise, it involves iterating over the entire data-set.
     * <p>
     * Returns {@code null} if all the data values in the dataset are
     * {@code null}.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <S>     the type for the series keys.
     * @return The minimum value (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Number findMinimumDomainValue(
            XYDataset<S> dataset) {
        Args.nullNotPermitted(dataset, "dataset");
        Number result;
        // if the dataset implements DomainInfo, life is easy
        if (dataset instanceof DomainInfo) {
            DomainInfo info = (DomainInfo) dataset;
            return info.getDomainLowerBound(true);
        } else {
            double minimum = Double.POSITIVE_INFINITY;
            int seriesCount = dataset.getSeriesCount();
            for (int series = 0; series < seriesCount; series++) {
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {

                    double value;
                    if (dataset instanceof IntervalXYDataset) {
                        @SuppressWarnings("unchecked")
                        IntervalXYDataset<S> intervalXYData
                                = (IntervalXYDataset) dataset;
                        value = intervalXYData.getStartXValue(series, item);
                    } else {
                        value = dataset.getXValue(series, item);
                    }
                    if (!Double.isNaN(value)) {
                        minimum = Math.min(minimum, value);
                    }

                }
            }
            if (minimum == Double.POSITIVE_INFINITY) {
                result = null;
            } else {
                result = minimum;
            }
        }

        return result;
    }

    /**
     * Returns the maximum domain value for the specified dataset.  This is
     * easy if the dataset implements the {@link DomainInfo} interface (a good
     * idea if there is an efficient way to determine the maximum value).
     * Otherwise, it involves iterating over the entire data-set.  Returns
     * {@code null} if all the data values in the dataset are
     * {@code null}.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <S>     the type for the series keys.
     * @return The maximum value (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Number findMaximumDomainValue(
            XYDataset<S> dataset) {
        Args.nullNotPermitted(dataset, "dataset");
        Number result;
        // if the dataset implements DomainInfo, life is easy
        if (dataset instanceof DomainInfo) {
            DomainInfo info = (DomainInfo) dataset;
            return info.getDomainUpperBound(true);
        }

        // hasn't implemented DomainInfo, so iterate...
        else {
            double maximum = Double.NEGATIVE_INFINITY;
            int seriesCount = dataset.getSeriesCount();
            for (int series = 0; series < seriesCount; series++) {
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {

                    double value;
                    if (dataset instanceof IntervalXYDataset) {
                        @SuppressWarnings("unchecked")
                        IntervalXYDataset<S> intervalXYData
                                = (IntervalXYDataset) dataset;
                        value = intervalXYData.getEndXValue(series, item);
                    } else {
                        value = dataset.getXValue(series, item);
                    }
                    if (!Double.isNaN(value)) {
                        maximum = Math.max(maximum, value);
                    }
                }
            }
            if (maximum == Double.NEGATIVE_INFINITY) {
                result = null;
            } else {
                result = maximum;
            }
        }
        return result;
    }

    /**
     * Returns the minimum range value for the specified dataset.  This is
     * easy if the dataset implements the {@link RangeInfo} interface (a good
     * idea if there is an efficient way to determine the minimum value).
     * Otherwise, it involves iterating over the entire data-set.  Returns
     * {@code null} if all the data values in the dataset are
     * {@code null}.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return The minimum value (possibly {@code null}).
     */
    public static <R extends Comparable<R>, C extends Comparable<C>> Number
    findMinimumRangeValue(CategoryDataset<R, C> dataset) {
        Args.nullNotPermitted(dataset, "dataset");
        if (dataset instanceof RangeInfo) {
            RangeInfo info = (RangeInfo) dataset;
            return info.getRangeLowerBound(true);
        }

        // hasn't implemented RangeInfo, so we'll have to iterate...
        else {
            double minimum = Double.POSITIVE_INFINITY;
            int seriesCount = dataset.getRowCount();
            int itemCount = dataset.getColumnCount();
            for (int series = 0; series < seriesCount; series++) {
                for (int item = 0; item < itemCount; item++) {
                    Number value;
                    if (dataset instanceof IntervalCategoryDataset) {
                        @SuppressWarnings("unchecked")
                        IntervalCategoryDataset<R, C> icd
                                = (IntervalCategoryDataset) dataset;
                        value = icd.getStartValue(series, item);
                    } else {
                        value = dataset.getValue(series, item);
                    }
                    if (value != null) {
                        minimum = Math.min(minimum, value.doubleValue());
                    }
                }
            }
            if (minimum == Double.POSITIVE_INFINITY) {
                return null;
            } else {
                return minimum;
            }
        }
    }

    /**
     * Returns the minimum range value for the specified dataset.  This is
     * easy if the dataset implements the {@link RangeInfo} interface (a good
     * idea if there is an efficient way to determine the minimum value).
     * Otherwise, it involves iterating over the entire data-set.  Returns
     * {@code null} if all the data values in the dataset are
     * {@code null}.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <S>     the type for the series keys.
     * @return The minimum value (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Number findMinimumRangeValue(
            XYDataset<S> dataset) {
        Args.nullNotPermitted(dataset, "dataset");

        // work out the minimum value...
        if (dataset instanceof RangeInfo) {
            RangeInfo info = (RangeInfo) dataset;
            return info.getRangeLowerBound(true);
        }

        // hasn't implemented RangeInfo, so we'll have to iterate...
        else {
            double minimum = Double.POSITIVE_INFINITY;
            int seriesCount = dataset.getSeriesCount();
            for (int series = 0; series < seriesCount; series++) {
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {

                    double value;
                    if (dataset instanceof IntervalXYDataset) {
                        @SuppressWarnings("unchecked")
                        IntervalXYDataset<S> intervalXYData
                                = (IntervalXYDataset) dataset;
                        value = intervalXYData.getStartYValue(series, item);
                    } else if (dataset instanceof OHLCDataset) {
                        OHLCDataset highLowData = (OHLCDataset) dataset;
                        value = highLowData.getLowValue(series, item);
                    } else {
                        value = dataset.getYValue(series, item);
                    }
                    if (!Double.isNaN(value)) {
                        minimum = Math.min(minimum, value);
                    }

                }
            }
            if (minimum == Double.POSITIVE_INFINITY) {
                return null;
            } else {
                return minimum;
            }
        }
    }

    /**
     * Returns the maximum range value for the specified dataset.  This is easy
     * if the dataset implements the {@link RangeInfo} interface (a good idea
     * if there is an efficient way to determine the maximum value).
     * Otherwise, it involves iterating over the entire data-set.  Returns
     * {@code null} if all the data values are {@code null}.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return The maximum value (possibly {@code null}).
     */
    public static <R extends Comparable<R>, C extends Comparable<C>>
    Number findMaximumRangeValue(CategoryDataset<R, C> dataset) {

        Args.nullNotPermitted(dataset, "dataset");

        // work out the minimum value...
        if (dataset instanceof RangeInfo) {
            RangeInfo info = (RangeInfo) dataset;
            return info.getRangeUpperBound(true);
        }

        // hasn't implemented RangeInfo, so we'll have to iterate...
        else {

            double maximum = Double.NEGATIVE_INFINITY;
            int seriesCount = dataset.getRowCount();
            int itemCount = dataset.getColumnCount();
            for (int series = 0; series < seriesCount; series++) {
                for (int item = 0; item < itemCount; item++) {
                    Number value;
                    if (dataset instanceof IntervalCategoryDataset) {
                        @SuppressWarnings("unchecked")
                        IntervalCategoryDataset<R, C> icd
                                = (IntervalCategoryDataset) dataset;
                        value = icd.getEndValue(series, item);
                    } else {
                        value = dataset.getValue(series, item);
                    }
                    if (value != null) {
                        maximum = Math.max(maximum, value.doubleValue());
                    }
                }
            }
            if (maximum == Double.NEGATIVE_INFINITY) {
                return null;
            } else {
                return maximum;
            }

        }

    }

    /**
     * Returns the maximum range value for the specified dataset.  This is
     * easy if the dataset implements the {@link RangeInfo} interface (a good
     * idea if there is an efficient way to determine the maximum value).
     * Otherwise, it involves iterating over the entire data-set.  Returns
     * {@code null} if all the data values are {@code null}.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <S>     the type for the series keys.
     * @return The maximum value (possibly {@code null}).
     */
    public static <S extends Comparable<S>> Number findMaximumRangeValue(
            XYDataset<S> dataset) {

        Args.nullNotPermitted(dataset, "dataset");

        // work out the minimum value...
        if (dataset instanceof RangeInfo) {
            RangeInfo info = (RangeInfo) dataset;
            return info.getRangeUpperBound(true);
        }

        // hasn't implemented RangeInfo, so we'll have to iterate...
        else {

            double maximum = Double.NEGATIVE_INFINITY;
            int seriesCount = dataset.getSeriesCount();
            for (int series = 0; series < seriesCount; series++) {
                int itemCount = dataset.getItemCount(series);
                for (int item = 0; item < itemCount; item++) {
                    double value;
                    if (dataset instanceof IntervalXYDataset) {
                        @SuppressWarnings("unchecked")
                        IntervalXYDataset<S> intervalXYData
                                = (IntervalXYDataset) dataset;
                        value = intervalXYData.getEndYValue(series, item);
                    } else if (dataset instanceof OHLCDataset) {
                        OHLCDataset highLowData = (OHLCDataset) dataset;
                        value = highLowData.getHighValue(series, item);
                    } else {
                        value = dataset.getYValue(series, item);
                    }
                    if (!Double.isNaN(value)) {
                        maximum = Math.max(maximum, value);
                    }
                }
            }
            if (maximum == Double.NEGATIVE_INFINITY) {
                return null;
            } else {
                return maximum;
            }
        }
    }

    /**
     * Returns the minimum and maximum values for the dataset's range
     * (y-values), assuming that the series in one category are stacked.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return The range ({@code null} if the dataset contains no values).
     */
    public static <R extends Comparable<R>, C extends Comparable<C>> Range
    findStackedRangeBounds(CategoryDataset<R, C> dataset) {
        return findStackedRangeBounds(dataset, 0.0);
    }

    /**
     * Returns the minimum and maximum values for the dataset's range
     * (y-values), assuming that the series in one category are stacked.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param base    the base value for the bars.
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return The range ({@code null} if the dataset contains no values).
     */
    public static <R extends Comparable<R>, C extends Comparable<C>> Range
    findStackedRangeBounds(CategoryDataset<R, C> dataset, double base) {
        Args.nullNotPermitted(dataset, "dataset");
        Range result = null;
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        int categoryCount = dataset.getColumnCount();
        for (int item = 0; item < categoryCount; item++) {
            double positive = base;
            double negative = base;
            int seriesCount = dataset.getRowCount();
            for (int series = 0; series < seriesCount; series++) {
                Number number = dataset.getValue(series, item);
                if (number != null) {
                    double value = number.doubleValue();
                    if (value > 0.0) {
                        positive = positive + value;
                    }
                    if (value < 0.0) {
                        negative = negative + value;
                        // '+', remember value is negative
                    }
                }
            }
            minimum = Math.min(minimum, negative);
            maximum = Math.max(maximum, positive);
        }
        if (minimum <= maximum) {
            result = new Range(minimum, maximum);
        }
        return result;

    }

    /**
     * Returns the minimum and maximum values for the dataset's range
     * (y-values), assuming that the series in one category are stacked.
     *
     * @param dataset the dataset.
     * @param map     a structure that maps series to groups.
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @param <G>     the type for the group keys.
     * @return The value range ({@code null} if the dataset contains no
     * values).
     */
    public static <R extends Comparable<R>, C extends Comparable<C>, G extends Comparable<G>>
    Range findStackedRangeBounds(CategoryDataset<R, C> dataset,
            KeyToGroupMap<R, G> map) {
        Args.nullNotPermitted(dataset, "dataset");
        boolean hasValidData = false;
        Range result = null;

        // create an array holding the group indices for each series...
        int[] groupIndex = new int[dataset.getRowCount()];
        for (int i = 0; i < dataset.getRowCount(); i++) {
            groupIndex[i] = map.getGroupIndex(map.getGroup(dataset.getRowKey(i)));
        }

        // minimum and maximum for each group...
        int groupCount = map.getGroupCount();
        double[] minimum = new double[groupCount];
        double[] maximum = new double[groupCount];

        int categoryCount = dataset.getColumnCount();
        for (int item = 0; item < categoryCount; item++) {
            double[] positive = new double[groupCount];
            double[] negative = new double[groupCount];
            int seriesCount = dataset.getRowCount();
            for (int series = 0; series < seriesCount; series++) {
                Number number = dataset.getValue(series, item);
                if (number != null) {
                    hasValidData = true;
                    double value = number.doubleValue();
                    if (value > 0.0) {
                        positive[groupIndex[series]]
                                = positive[groupIndex[series]] + value;
                    }
                    if (value < 0.0) {
                        negative[groupIndex[series]]
                                = negative[groupIndex[series]] + value;
                        // '+', remember value is negative
                    }
                }
            }
            for (int g = 0; g < groupCount; g++) {
                minimum[g] = Math.min(minimum[g], negative[g]);
                maximum[g] = Math.max(maximum[g], positive[g]);
            }
        }
        if (hasValidData) {
            for (int j = 0; j < groupCount; j++) {
                result = Range.combine(result, new Range(minimum[j],
                        maximum[j]));
            }
        }
        return result;
    }

    /**
     * Returns the minimum value in the dataset range, assuming that values in
     * each category are "stacked".
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return The minimum value.
     * @see #findMaximumStackedRangeValue(CategoryDataset)
     */
    public static <R extends Comparable<R>, C extends Comparable<C>> Number
    findMinimumStackedRangeValue(CategoryDataset<R, C> dataset) {
        Args.nullNotPermitted(dataset, "dataset");
        Number result = null;
        boolean hasValidData = false;
        double minimum = 0.0;
        int categoryCount = dataset.getColumnCount();
        for (int item = 0; item < categoryCount; item++) {
            double total = 0.0;
            int seriesCount = dataset.getRowCount();
            for (int series = 0; series < seriesCount; series++) {
                Number number = dataset.getValue(series, item);
                if (number != null) {
                    hasValidData = true;
                    double value = number.doubleValue();
                    if (value < 0.0) {
                        total = total + value;
                        // '+', remember value is negative
                    }
                }
            }
            minimum = Math.min(minimum, total);
        }
        if (hasValidData) {
            result = minimum;
        }
        return result;
    }

    /**
     * Returns the maximum value in the dataset range, assuming that values in
     * each category are "stacked".
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return The maximum value (possibly {@code null}).
     * @see #findMinimumStackedRangeValue(CategoryDataset)
     */
    public static <R extends Comparable<R>, C extends Comparable<C>> Number
    findMaximumStackedRangeValue(CategoryDataset<R, C> dataset) {
        Args.nullNotPermitted(dataset, "dataset");
        Number result = null;
        boolean hasValidData = false;
        double maximum = 0.0;
        int categoryCount = dataset.getColumnCount();
        for (int item = 0; item < categoryCount; item++) {
            double total = 0.0;
            int seriesCount = dataset.getRowCount();
            for (int series = 0; series < seriesCount; series++) {
                Number number = dataset.getValue(series, item);
                if (number != null) {
                    hasValidData = true;
                    double value = number.doubleValue();
                    if (value > 0.0) {
                        total = total + value;
                    }
                }
            }
            maximum = Math.max(maximum, total);
        }
        if (hasValidData) {
            result = maximum;
        }
        return result;
    }

    /**
     * Returns the minimum and maximum values for the dataset's range,
     * assuming that the series are stacked.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <S>     the type for the series keys.
     * @return The range ([0.0, 0.0] if the dataset contains no values).
     */
    public static <S extends Comparable<S>> Range findStackedRangeBounds(
            TableXYDataset<S> dataset) {
        return findStackedRangeBounds(dataset, 0.0);
    }

    /**
     * Returns the minimum and maximum values for the dataset's range,
     * assuming that the series are stacked, using the specified base value.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param base    the base value.
     * @param <S>     the type for the series keys.
     * @return The range ({@code null} if the dataset contains no values).
     */
    public static <S extends Comparable<S>> Range findStackedRangeBounds(
            TableXYDataset<S> dataset, double base) {
        Args.nullNotPermitted(dataset, "dataset");
        double minimum = base;
        double maximum = base;
        for (int itemNo = 0; itemNo < dataset.getItemCount(); itemNo++) {
            double positive = base;
            double negative = base;
            int seriesCount = dataset.getSeriesCount();
            for (int seriesNo = 0; seriesNo < seriesCount; seriesNo++) {
                double y = dataset.getYValue(seriesNo, itemNo);
                if (!Double.isNaN(y)) {
                    if (y > 0.0) {
                        positive += y;
                    } else {
                        negative += y;
                    }
                }
            }
            if (positive > maximum) {
                maximum = positive;
            }
            if (negative < minimum) {
                minimum = negative;
            }
        }
        if (minimum <= maximum) {
            return new Range(minimum, maximum);
        } else {
            return null;
        }
    }

    /**
     * Calculates the total for the y-values in all series for a given item
     * index.
     *
     * @param dataset the dataset.
     * @param item    the item index.
     * @param <S>     the type for the series keys.
     * @return The total.
     * @since 1.0.5
     */
    public static <S extends Comparable<S>> double calculateStackTotal(
            TableXYDataset<S> dataset, int item) {
        double total = 0.0;
        int seriesCount = dataset.getSeriesCount();
        for (int s = 0; s < seriesCount; s++) {
            double value = dataset.getYValue(s, item);
            if (!Double.isNaN(value)) {
                total = total + value;
            }
        }
        return total;
    }

    /**
     * Calculates the range of values for a dataset where each item is the
     * running total of the items for the current series.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param <R>     the type for the row keys.
     * @param <C>     the type for the column keys.
     * @return The range.
     * @see #findRangeBounds(CategoryDataset)
     */
    public static <R extends Comparable<R>, C extends Comparable<C>> Range
    findCumulativeRangeBounds(CategoryDataset<R, C> dataset) {
        Args.nullNotPermitted(dataset, "dataset");
        boolean allItemsNull = true; // we'll set this to false if there is at
        // least one non-null data item...
        double minimum = 0.0;
        double maximum = 0.0;
        for (int row = 0; row < dataset.getRowCount(); row++) {
            double runningTotal = 0.0;
            for (int column = 0; column <= dataset.getColumnCount() - 1;
                 column++) {
                Number n = dataset.getValue(row, column);
                if (n != null) {
                    allItemsNull = false;
                    double value = n.doubleValue();
                    if (!Double.isNaN(value)) {
                        runningTotal = runningTotal + value;
                        minimum = Math.min(minimum, runningTotal);
                        maximum = Math.max(maximum, runningTotal);
                    }
                }
            }
        }
        if (!allItemsNull) {
            return new Range(minimum, maximum);
        } else {
            return null;
        }
    }

    /**
     * Returns the interpolated value of y that corresponds to the specified
     * x-value in the given series.  If the x-value falls outside the range of
     * x-values for the dataset, this method returns {@code Double.NaN}.
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param series  the series index.
     * @param x       the x-value.
     * @param <S>     the type for the series keys.
     * @return The y value.
     * @since 1.0.16
     */
    public static <S extends Comparable<S>> double findYValue(
            XYDataset<S> dataset, int series, double x) {
        // delegate null check on dataset
        int[] indices = findItemIndicesForX(dataset, series, x);
        if (indices[0] == -1) {
            return Double.NaN;
        }
        if (indices[0] == indices[1]) {
            return dataset.getYValue(series, indices[0]);
        }
        double x0 = dataset.getXValue(series, indices[0]);
        double x1 = dataset.getXValue(series, indices[1]);
        double y0 = dataset.getYValue(series, indices[0]);
        double y1 = dataset.getYValue(series, indices[1]);
        return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
    }

    /**
     * Finds the indices of the the items in the dataset that span the
     * specified x-value.  There are three cases for the return value:
     * <ul>
     * <li>there is an exact match for the x-value at index i
     * (returns {@code int[] {i, i}});</li>
     * <li>the x-value falls between two (adjacent) items at index i and i+1
     * (returns {@code int[] {i, i+1}});</li>
     * <li>the x-value falls outside the domain bounds, in which case the
     *    method returns {@code int[] {-1, -1}}.</li>
     * </ul>
     *
     * @param dataset the dataset ({@code null} not permitted).
     * @param series  the series index.
     * @param x       the x-value.
     * @param <S>     the type for the series keys.
     * @return The indices of the two items that span the x-value.
     * @see #findYValue(XYDataset, int, double)
     * @since 1.0.16
     */
    public static <S extends Comparable<S>> int[] findItemIndicesForX(
            XYDataset<S> dataset, int series, double x) {
        Args.nullNotPermitted(dataset, "dataset");
        int itemCount = dataset.getItemCount(series);
        if (itemCount == 0) {
            return new int[]{-1, -1};
        }
        if (itemCount == 1) {
            if (x == dataset.getXValue(series, 0)) {
                return new int[]{0, 0};
            } else {
                return new int[]{-1, -1};
            }
        }
        if (dataset.getDomainOrder() == DomainOrder.ASCENDING) {
            int low = 0;
            int high = itemCount - 1;
            double lowValue = dataset.getXValue(series, low);
            if (lowValue > x) {
                return new int[]{-1, -1};
            }
            if (lowValue == x) {
                return new int[]{low, low};
            }
            double highValue = dataset.getXValue(series, high);
            if (highValue < x) {
                return new int[]{-1, -1};
            }
            if (highValue == x) {
                return new int[]{high, high};
            }
            int mid = (low + high) / 2;
            while (high - low > 1) {
                double midV = dataset.getXValue(series, mid);
                if (x == midV) {
                    return new int[]{mid, mid};
                }
                if (midV < x) {
                    low = mid;
                } else {
                    high = mid;
                }
                mid = (low + high) / 2;
            }
            return new int[]{low, high};
        } else if (dataset.getDomainOrder() == DomainOrder.DESCENDING) {
            int high = 0;
            int low = itemCount - 1;
            double lowValue = dataset.getXValue(series, low);
            if (lowValue > x) {
                return new int[]{-1, -1};
            }
            double highValue = dataset.getXValue(series, high);
            if (highValue < x) {
                return new int[]{-1, -1};
            }
            int mid = (low + high) / 2;
            while (high - low > 1) {
                double midV = dataset.getXValue(series, mid);
                if (x == midV) {
                    return new int[]{mid, mid};
                }
                if (midV < x) {
                    low = mid;
                } else {
                    high = mid;
                }
                mid = (low + high) / 2;
            }
            return new int[]{low, high};
        } else {
            // we don't know anything about the ordering of the x-values,
            // so we iterate until we find the first crossing of x (if any)
            // we know there are at least 2 items in the series at this point
            double prev = dataset.getXValue(series, 0);
            if (x == prev) {
                return new int[]{0, 0}; // exact match on first item
            }
            for (int i = 1; i < itemCount; i++) {
                double next = dataset.getXValue(series, i);
                if (x == next) {
                    return new int[]{i, i}; // exact match
                }
                if ((x > prev && x < next) || (x < prev && x > next)) {
                    return new int[]{i - 1, i}; // spanning match
                }
            }
            return new int[]{-1, -1}; // no crossing of x
        }
    }

}
