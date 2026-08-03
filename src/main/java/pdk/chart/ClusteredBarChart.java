package pdk.chart;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.ClusteredXYBarRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

/**
 * A clustered bar chart, where multiple series are displayed side‑by‑side
 * for each category.
 * <p>
 * This chart uses a {@link ClusteredXYBarRenderer} with shadows disabled
 * by default. The domain and range axes are created from the supplied
 * {@link AxisType} values, allowing numeric or date axes.
 * <p>
 * <b>Note:</b> For proper bar widths the dataset should implement
 * {@link IntervalXYDataset}.  Using a plain {@link XYDataset} will work,
 * but the renderer may fall back to default bar widths if interval
 * information is missing.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Aug 2026, 2:13 PM
 */
public class ClusteredBarChart extends BarChart {

    private ClusteredXYBarRenderer renderer2_;

    /**
     * Initializes the renderer to a {@link ClusteredXYBarRenderer} with
     * shadows disabled.  Both the {@code renderer0_} and {@code renderer1_}
     * fields inherited from the parent are updated to point to this renderer.
     */
    @Override
    protected void initRenderer() {
        renderer2_ = new ClusteredXYBarRenderer();
        renderer2_.setShadowVisible(false);

        this.renderer0_ = renderer2_;
        this.renderer1_ = renderer2_;
    }

    @Override
    public ClusteredXYBarRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Full constructor – all options are exposed.
     *
     * @param dataset     the dataset (preferably an {@link IntervalXYDataset};
     *                    {@code null} permitted)
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
    public ClusteredBarChart(XYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType,
            @Nullable String title, @NonNull PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        ValueAxis xAxis_ = xAxisType.createInstance(xAxisLabel);
        ValueAxis yAxis_ = yAxisType.createInstance(yAxisLabel);

        if (tooltips) {
            if (xAxisType == AxisType.DATE) {
                renderer2_.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
            } else {
                renderer2_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
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
     * Creates a clustered bar chart with the given flags; URLs are disabled.
     *
     * @param dataset     the dataset (preferably an {@link IntervalXYDataset})
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param xAxisType   the type of the domain axis
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param yAxisType   the type of the range axis
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     */
    public ClusteredBarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType,
            @Nullable String title, @NonNull PlotOrientation orientation,
            boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, yAxisType, title,
                orientation, legend, tooltips, false);
    }

    /**
     * Convenience constructor with vertical orientation, legend and tooltips
     * enabled, no URLs.
     *
     * @param dataset    the dataset (preferably an {@link IntervalXYDataset})
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param xAxisType  the type of the domain axis
     * @param yAxisLabel the range axis label ({@code null} permitted)
     * @param yAxisType  the type of the range axis
     * @param title      the chart title ({@code null} permitted)
     */
    public ClusteredBarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType,
            @Nullable String title) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, yAxisType, title,
                PlotOrientation.VERTICAL, true, true, false);
    }

}
