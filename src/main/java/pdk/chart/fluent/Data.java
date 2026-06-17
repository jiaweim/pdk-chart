package pdk.chart.fluent;

import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.*;
import pdk.chart.internal.Args;

/**
 * Utilities for build dataset.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 17 Jun 2026, 9:35 AM
 */
public interface Data {


    public static class IntervalXYDatasetBuilder<S extends Comparable<S>> {

        private DefaultIntervalXYDataset<S> dataset = new DefaultIntervalXYDataset<>();

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
