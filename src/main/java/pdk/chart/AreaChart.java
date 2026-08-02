package pdk.chart;

import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.labels.XYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.XYAreaRenderer;
import pdk.chart.urls.StandardXYURLGenerator;
import pdk.chart.urls.XYURLGenerator;

import java.util.Objects;

/**
 * An area chart implementation based on an {@link XYDataset}.
 * <p>
 * This chart uses an {@link XYAreaRenderer} to fill the area
 * beneath the data points with a semi‑transparent color.
 * The default foreground alpha is set to {@code 0.65f}, giving
 * the chart a typical translucent look.
 * <p>
 * The domain and range axes are created from the supplied
 * {@link AxisType} values, allowing both numeric and date axes.
 * <p>
 * Tool‑tips and URL generation can be enabled via the constructor flags.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @see XYAreaRenderer
 * @see XYChart
 * @since 31 Jul 2026
 */
public class AreaChart extends XYChart {

    private ValueAxis xAxis_;
    private ValueAxis yAxis_;
    private XYAreaRenderer renderer_;

    /**
     * Initializes the renderer to an {@link XYAreaRenderer} and assigns it
     * to the parent’s {@code renderer0_} reference.
     */
    @Override
    protected void initRenderer() {
        renderer_ = new XYAreaRenderer();
        renderer0_ = renderer_;
    }

    /**
     * Full constructor – every option is exposed.
     *
     * @param dataset     the data source ({@code null} permitted)
     * @param xAxisLabel  the label for the domain axis ({@code null} permitted)
     * @param xAxisType   the type of the domain axis ({@code null} not permitted)
     * @param yAxisLabel  the label for the range axis ({@code null} permitted)
     * @param yAxisType   the type of the range axis ({@code null} not permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     * @param urls        {@code true} to attach URLs to data points
     */
    public AreaChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);
        Objects.requireNonNull(xAxisType);
        Objects.requireNonNull(yAxisType);

        xAxis_ = xAxisType.createInstance(xAxisLabel);
        if (xAxis_ instanceof NumberAxis nAxis) {
            nAxis.setAutoRangeIncludesZero(false);
        }
        yAxis_ = yAxisType.createInstance(yAxisLabel);

        if (tooltips) {
            XYToolTipGenerator tipGenerator = new StandardXYToolTipGenerator();
            renderer_.setDefaultToolTipGenerator(tipGenerator);
        }

        if (urls) {
            XYURLGenerator urlGenerator = new StandardXYURLGenerator();
            renderer_.setURLGenerator(urlGenerator);
        }

        plot_.setOrientation(orientation);
        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer_);
        plot_.setForegroundAlpha(0.65f);
        plot_.setDataset(dataset);

        JChart.applyCurrentTheme(this);
    }

    /**
     * Convenience constructor with a vertical orientation, legend,
     * tooltips disabled, and no URLs. The range axis is a
     * {@link NumberAxis}.
     *
     * @param dataset    the data source ({@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param xAxisType  the type of the domain axis ({@code null} not permitted)
     * @param yAxisLabel the range axis label ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     */
    public AreaChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, String title) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, AxisType.NUMBER, title,
                PlotOrientation.VERTICAL, true, false, false);
    }

    /**
     * Creates an area chart with the specified dataset, axis labels,
     * title, orientation, and legend/tooltip/URL flags.
     *
     * @param dataset     the dataset for the chart ({@code null} permitted)
     * @param xAxisLabel  the label for the domain (X) axis ({@code null} permitted)
     * @param yAxisLabel  the label for the range (Y) axis ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      if {@code true}, a legend is displayed
     * @param tooltips    if {@code true}, standard tool‑tips are enabled
     * @param urls        if {@code true}, URLs are generated for data points
     */
    public AreaChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, orientation, legend, tooltips, urls);
    }

    /**
     * Creates an area chart with the specified dataset, axis labels,
     * title, orientation, legend, and tooltip flag.
     * URL generation is disabled.
     *
     * @param dataset     the dataset for the chart ({@code null} permitted)
     * @param xAxisLabel  the label for the domain (X) axis ({@code null} permitted)
     * @param yAxisLabel  the label for the range (Y) axis ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      if {@code true}, a legend is displayed
     * @param tooltips    if {@code true}, standard tool‑tips are enabled
     */
    public AreaChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title,
                orientation, legend, tooltips, false);
    }

    /**
     * Creates an area chart with vertical orientation, legend and tooltips
     * enabled, and no URLs.
     *
     * @param dataset    the dataset for the chart ({@code null} permitted)
     * @param xAxisLabel the label for the domain (X) axis ({@code null} permitted)
     * @param yAxisLabel the label for the range (Y) axis ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     */
    public AreaChart(XYDataset dataset, String xAxisLabel, String yAxisLabel,
            String title) {
        this(dataset, xAxisLabel, yAxisLabel, title, PlotOrientation.VERTICAL,
                true, true);
    }
}
