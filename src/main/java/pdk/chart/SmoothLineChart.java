package pdk.chart;

import org.jspecify.annotations.Nullable;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.XYSplineRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

import java.util.Objects;

/**
 * A line chart that uses spline interpolation to draw smooth curves
 * between data points.
 * <p>
 * The renderer is an {@link XYSplineRenderer} (lines visible, shapes
 * hidden by default).  All other configuration – axes, tooltips, URLs,
 * orientation – is handled identically to {@link LineChart}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @see XYSplineRenderer
 * @see LineChart
 * @since 02 Aug 2026, 3:36 PM
 */
public class SmoothLineChart extends LineChart {

    private XYSplineRenderer renderer2_;

    /**
     * Initializes the renderer to an {@link XYSplineRenderer} and
     * updates the parent’s renderer references so that inherited methods
     * operate on the correct renderer.
     */
    @Override
    protected void initRenderer() {
        renderer2_ = new XYSplineRenderer();
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
    }

    /**
     * Returns the spline renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public XYSplineRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Full constructor – every option is exposed.
     *
     * @param dataset     the data source ({@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param xAxisType   the domain axis type (must not be {@code null})
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param yAxisType   the range axis type (must not be {@code null})
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation (must not be {@code null})
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     * @param urls        {@code true} to generate URLs for data points
     */
    public SmoothLineChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, @Nullable String title,
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
        if (yAxis_ instanceof NumberAxis nAxis) {
            nAxis.setAutoRangeIncludesZero(false);
        }
        if (tooltips) {
            if (xAxisType == AxisType.NUMBER) {
                renderer2_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
            } else if (xAxisType == AxisType.DATE) {
                renderer2_.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
            }
        }
        if (urls) {
            renderer2_.setURLGenerator(new StandardXYURLGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setOrientation(orientation);
        plot_.setRenderer(renderer2_);
        plot_.setDataset(dataset);

        JChart.applyCurrentTheme(this);
    }

    /**
     * Convenience constructor that assumes both axes are
     * {@link NumberAxis} instances and disables URLs.
     *
     * @param dataset     the data source ({@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation (must not be {@code null})
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     */
    public SmoothLineChart(XYDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a smooth line chart with vertical orientation,
     * legend and tooltips enabled, numeric axes, and no title.
     *
     * @param dataset the data source ({@code null} permitted)
     */
    public SmoothLineChart(XYDataset dataset) {
        this(dataset, null, null, null, PlotOrientation.VERTICAL,
                true, true);
    }

}
