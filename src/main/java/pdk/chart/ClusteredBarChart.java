package pdk.chart;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.ClusteredXYBarRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Aug 2026, 2:13 PM
 */
public class ClusteredBarChart extends BarChart {

    private ClusteredXYBarRenderer localRenderer_;

    public ClusteredBarChart(AxisType xAxisType, AxisType yAxisType, String title, boolean createLegend) {
        super(xAxisType, yAxisType, title, createLegend);
        this.localRenderer_ = new ClusteredXYBarRenderer();
        localRenderer_.setShadowVisible(false);

        renderer_ = localRenderer_;
        setDefaultRenderer(renderer_);
        plot_.setRenderer(renderer_);
        JChartUtils.applyCurrentTheme(this);
    }

    /**
     * Create a clustered bar chart, returns a default instance of XY bar chart.
     *
     * @param title       chart title.
     * @param xAxisLabel  x-axis name.
     * @param yAxisLabel  y-axis name.
     * @param dataset     the dataset for the chart.
     * @param orientation {@link PlotOrientation}
     * @param legend      whether show legend.
     * @param tooltips    whether create tool tips.
     * @param urls        whether create urls.
     */
    public ClusteredBarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType,
            @Nullable String title, @NonNull PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        this(xAxisType, yAxisType, title, legend);

        setAxisLabels(xAxisLabel, yAxisLabel);
        plot_.setDataset(dataset);
        plot_.setOrientation(orientation);
        if (tooltips) {
            if (xAxisType == AxisType.DATE) {
                renderer_.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
            } else {
                renderer_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
            }
        }
        if (urls) {
            renderer_.setURLGenerator(new StandardXYURLGenerator());
        }
    }

    /**
     * Create a clustered bar chart, returns a default instance of XY bar chart.
     *
     * @param title       chart title.
     * @param xAxisLabel  x-axis name.
     * @param yAxisLabel  y-axis name.
     * @param dataset     the dataset for the chart.
     * @param orientation {@link PlotOrientation}
     * @param legend      whether show legend.
     * @param tooltips    whether create tool tips.
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
     * Create a clustered bar chart, returns a default instance of XY bar chart.
     *
     * @param title      chart title.
     * @param xAxisLabel x-axis name.
     * @param yAxisLabel y-axis name.
     * @param dataset    the dataset for the chart.
     */
    public ClusteredBarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType,
            @Nullable String title) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, yAxisType, title,
                PlotOrientation.VERTICAL, true, true, false);
    }

}
