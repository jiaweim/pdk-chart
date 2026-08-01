package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.DefaultStatisticalCategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.renderer.category.StatisticalLineAndShapeRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;
import pdk.chart.util.Args;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 3:53 PM
 */
public class StatisticalCategoryLineChart extends CategoryLineChart {

    private StatisticalLineAndShapeRenderer localRenderer_;

    /**
     * Creates a line chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link LineAndShapeRenderer} as the renderer.
     *
     * @param title           the chart title ({@code null} permitted).
     * @param domainAxisLabel the label for the category axis
     *                        ({@code null} permitted).
     * @param rangeAxisLabel  the label for the value axis ({@code null}
     *                        permitted).
     * @param dataset         the dataset for the chart ({@code null} permitted).
     * @param orientation     the chart orientation (horizontal or vertical)
     *                        ({@code null} not permitted).
     * @param legend          a flag specifying whether a legend is required.
     * @param tooltips        configure chart to generate tool tips?
     * @param urls            configure chart to generate URLs?
     */
    public StatisticalCategoryLineChart(DefaultStatisticalCategoryDataset dataset,
            String domainAxisLabel, String rangeAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Args.nullNotPermitted(orientation, "orientation");
        this.xAxis_ = new CategoryAxis(domainAxisLabel);
        this.yAxis_ = new NumberAxis(rangeAxisLabel);

        this.localRenderer_ = new StatisticalLineAndShapeRenderer(true, false);
        this.renderer_ = localRenderer_;
        setDefaultRenderer(renderer_);

        if (tooltips) {
            renderer_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer_.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }
        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer_);
        plot_.setOrientation(orientation);
        plot_.setDataset(dataset);
        JChartUtils.applyCurrentTheme(this);
    }

    public StatisticalCategoryLineChart(DefaultStatisticalCategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, PlotOrientation.VERTICAL, true, true, false);
    }

}
