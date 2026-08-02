package pdk.chart;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.XYDataset;
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

    private ClusteredXYBarRenderer renderer2_;

    @Override
    protected void initRenderer() {
        renderer2_ = new ClusteredXYBarRenderer();
        renderer2_.setShadowVisible(false);

        this.renderer0_ = renderer2_;
        this.renderer1_ = renderer2_;
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
    public ClusteredBarChart(XYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType,
            @Nullable String title, @NonNull PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        this.xAxis_ = xAxisType.createInstance(xAxisLabel);
        this.yAxis_ = yAxisType.createInstance(yAxisLabel);

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
