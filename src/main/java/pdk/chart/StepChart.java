package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYStepRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

import java.util.Objects;

/**
 * A step chart where data points are connected by horizontal and vertical
 * line segments, producing a staircase appearance.
 * <p>
 * The renderer is an {@link XYStepRenderer} (lines visible, shapes hidden
 * by default).  The domain axis is a {@link DateAxis} and the range axis a
 * {@link NumberAxis} with integer tick units.
 * <p>
 * <b>Note:</b> The overridden {@link #getRenderer(int)} always returns the
 * step renderer regardless of the index.  If you add multiple datasets with
 * different renderers, use {@link XYPlot#getRenderer(int)} directly.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 4:35 PM
 */
public class StepChart extends LineChart {

    private XYStepRenderer renderer2_;

    /**
     * Initializes the renderer to an {@link XYStepRenderer} and updates
     * the parent renderer references.
     */
    @Override
    protected void initRenderer() {
        renderer2_ = new XYStepRenderer();
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
    }

    /**
     * Returns the step renderer (ignoring the index parameter).
     * <p>
     * <b>Warning:</b> This implementation always returns the same
     * renderer.  For correct behavior with multiple datasets, access
     * the renderer through the underlying {@link XYPlot}.
     *
     * @return the step renderer
     */
    @Override
    public XYStepRenderer getRenderer() {
        return renderer2_;
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
    public StepChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);

        xAxis_ = new DateAxis(xAxisLabel);
        yAxis_ = new NumberAxis(yAxisLabel);
        yAxis_.setStandardTickUnits(NumberAxis.createIntegerTickUnits());


        if (tooltips) {
            renderer2_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer2_.setURLGenerator(new StandardXYURLGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setOrientation(orientation);
        plot_.setRenderer(renderer2_);
        plot_.setDataset(dataset);
        plot_.setDomainCrosshairVisible(false);
        plot_.setRangeCrosshairVisible(false);

        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a step chart with the given parameters; URLs are disabled.
     *
     * @param dataset     the data source ({@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     */
    public StepChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
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
    public StepChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        this(dataset, xAxisLabel, yAxisLabel, title, PlotOrientation.VERTICAL, true, true);
    }
}
