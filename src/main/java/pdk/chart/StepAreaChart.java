package pdk.chart;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.XYStepAreaRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

import java.util.Objects;

/**
 * A step area chart where the area under the data points is drawn using
 * horizontal and vertical segments, creating a stepped appearance.
 * <p>
 * The renderer is an {@link XYStepAreaRenderer} configured to show both
 * the area and the data point shapes by default.  Both axes are
 * {@link NumberAxis} instances; the domain axis excludes zero from its
 * auto‑calculated range.
 * <p>
 * Crosshairs are disabled by default.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 4:45 PM
 */
public class StepAreaChart extends XYChart {

    private XYStepAreaRenderer renderer1_;

    @Override
    protected void initRenderer() {
        renderer1_ = new XYStepAreaRenderer(XYStepAreaRenderer.AREA_AND_SHAPES);
        renderer0_ = renderer1_;
    }

    @Override
    public XYStepAreaRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Full constructor.
     *
     * @param dataset     the data source ({@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     * @param urls        {@code true} to generate URLs for data points
     */
    public StepAreaChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);

        NumberAxis xAxis_ = new NumberAxis(xAxisLabel);
        xAxis_.setAutoRangeIncludesZero(false);
        NumberAxis yAxis_ = new NumberAxis(yAxisLabel);

        if (tooltips) {
            renderer1_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer1_.setURLGenerator(new StandardXYURLGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setOrientation(orientation);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);
        plot_.setDomainCrosshairVisible(false);
        plot_.setRangeCrosshairVisible(false);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a step area chart with the given parameters; URLs are
     * disabled.
     *
     * @param dataset     the data source ({@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     */
    public StepAreaChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Convenience constructor with vertical orientation, legend and
     * tooltips enabled, no URLs.
     *
     * @param dataset    the data source ({@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param yAxisLabel the range axis label ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     */
    public StepAreaChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        this(dataset, xAxisLabel, yAxisLabel, title, PlotOrientation.VERTICAL, true, true);
    }
}
