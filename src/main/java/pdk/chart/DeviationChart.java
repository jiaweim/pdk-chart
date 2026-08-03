package pdk.chart;

import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.DeviationRenderer;

/**
 * A chart that displays a line with a filled deviation band, typically used
 * to show a confidence interval or standard deviation around a mean value.
 * <p>
 * The renderer is a {@link DeviationRenderer} initialised with lines visible
 * and shapes hidden by default.  The deviation (upper and lower bounds) is
 * determined by additional Y values in the dataset, usually provided via an
 * {@link pdk.chart.data.xy.YIntervalSeriesCollection} or similar dataset that
 * supplies multiple y-values per item.
 * <p>
 * This class extends {@link LineChart}; all axis and tooltip configuration
 * is inherited.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 8:40 PM
 */
public class DeviationChart extends LineChart {

    private DeviationRenderer renderer2_;

    @Override
    protected void initRenderer() {
        renderer2_ = new DeviationRenderer(true, false);
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
    }

    @Override
    public DeviationRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Full constructor – every option is exposed.
     *
     * @param dataset     the dataset (should provide multiple y-values per
     *                    item for deviation bands; {@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param xAxisType   the type of the domain axis ({@code null} not permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param yAxisType   the type of the range axis ({@code null} not permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     * @param urls        {@code true} to generate URLs for data points
     */
    public DeviationChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(dataset, xAxisLabel, xAxisType, yAxisLabel, yAxisType,
                title, orientation, legend, tooltips, urls);
    }

    /**
     * Convenience constructor with vertical orientation, legend and tooltips
     * enabled, numeric axes, no URLs.
     *
     * @param dataset    the dataset ({@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param yAxisLabel the range axis label ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     */
    public DeviationChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        super(dataset, xAxisLabel, yAxisLabel, title);
    }
}
