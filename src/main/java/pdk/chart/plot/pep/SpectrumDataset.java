package pdk.chart.plot.pep;

import pdk.chart.data.xy.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * dataset for {@link PSMPlot}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 5:15 PM
 */
public class SpectrumDataset extends YIntervalSeriesCollection<SeriesType> {

    private final Map<SeriesType, String[]> labelMap = new HashMap<>();
    private final XYSeriesCollection<SeriesType> mzErrorDataset = new XYSeriesCollection<>();


    public SpectrumDataset() {
        super();
    }

    /**
     * Sort the series to ensure the rendering order.
     */
    public void sort() {
        data.sort((o1, o2) -> o2.getKey().compareTo(o1.getKey()));
    }

    /**
     * Add a series of peaks.
     *
     * @param seriesType {@link SeriesType} of the peaks.
     * @param x          mz values.
     * @param y          intensity values.
     */
    public void addSeries(SeriesType seriesType, double[] x, double[] y) {
        addSeries(seriesType, x, y, null);
    }

    /**
     * Add a series of peaks.
     *
     * @param seriesType {@link SeriesType} of the peaks.
     * @param x          mz values
     * @param y          intensity values.
     * @param labels     peak annotation labels.
     */
    public void addSeries(SeriesType seriesType, double[] x, double[] y, String[] labels) {
        addSeries(seriesType, x, y, null, labels);
    }

    /**
     * Add a series of peaks.
     *
     * @param seriesType {@link SeriesType} of the peaks.
     * @param x          mz values
     * @param y          intensity values.
     * @param labels     peak annotation labels.
     */
    public void addSeries(SeriesType seriesType, double[] x, double[] y, double[] mzError, String[] labels) {
        Objects.requireNonNull(seriesType);
        Objects.requireNonNull(x);
        Objects.requireNonNull(y);

        if (x.length != y.length) {
            throw new IllegalArgumentException("x and y arrays must have same length");
        }

        if (mzError != null) {
            if (x.length != mzError.length) {
                throw new IllegalArgumentException("x and mzError arrays must have same length");
            }
            XYSeries<SeriesType> series = new XYSeries<>(seriesType);
            for (int i = 0; i < x.length; i++) {
                series.add(x[i], mzError[i]);
            }
            mzErrorDataset.addSeries(series);
        }

        YIntervalSeries<SeriesType> series = new YIntervalSeries<>(seriesType);
        for (int i = 0; i < x.length; i++) {
            series.add(x[i], y[i], 0, y[i]);
        }
        addSeries(series);
        labelMap.put(seriesType, labels);
    }

    public XYDataset<SeriesType> getMZErrorDataset() {
        return mzErrorDataset;
    }

    /**
     * Return the fragment size for a given {@link SeriesType}.
     *
     * @param seriesType {@link SeriesType}.
     * @return fragment size of a given series.
     */
    public String[] getLabels(SeriesType seriesType) {
        return labelMap.get(seriesType);
    }

}
