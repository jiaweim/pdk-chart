package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.DefaultStatisticalCategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.StatisticalLineAndShapeRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;

import java.util.Objects;

/**
 * A statistical line chart that displays a mean trend line together with
 * a shaded band representing the standard deviation (or other statistical
 * spread) around the mean.
 * <p>
 * The chart uses a {@link StatisticalLineAndShapeRenderer} to draw the
 * mean line and the spread band.  The domain axis is a
 * {@link CategoryAxis} and the range axis is a {@link NumberAxis}.
 * <p>
 * The dataset must be a {@link DefaultStatisticalCategoryDataset} that
 * provides the mean and standard deviation for each category.  Tool‑tips
 * and URLs can be enabled via constructor flags.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 3:53 PM
 */
public class CategoryStatisticalLineChart extends CategoryLineChart {

    private StatisticalLineAndShapeRenderer renderer2_;

    @Override
    protected void initRenderer() {
        this.renderer2_ = new StatisticalLineAndShapeRenderer(true, false);
        this.renderer0_ = renderer2_;
        this.renderer1_ = renderer2_;
    }

    @Override
    public StatisticalLineAndShapeRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Full constructor – every option is exposed.
     *
     * @param dataset         the statistical dataset ({@code null} permitted)
     * @param domainAxisLabel the label for the category axis ({@code null} permitted)
     * @param rangeAxisLabel  the label for the value axis ({@code null} permitted)
     * @param title           the chart title ({@code null} permitted)
     * @param orientation     the plot orientation ({@code null} not permitted)
     * @param legend          {@code true} to include a legend
     * @param tooltips        {@code true} to enable standard tool‑tips
     * @param urls            {@code true} to generate URLs for data points
     */
    public CategoryStatisticalLineChart(DefaultStatisticalCategoryDataset dataset,
            String domainAxisLabel, String rangeAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);
        CategoryAxis xAxis_ = new CategoryAxis(domainAxisLabel);
        NumberAxis yAxis_ = new NumberAxis(rangeAxisLabel);

        if (tooltips) {
            renderer1_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer1_.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }
        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer1_);
        plot_.setOrientation(orientation);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Convenience constructor with vertical orientation, legend and
     * tool‑tips enabled, no URLs.
     *
     * @param dataset           the statistical dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the category axis ({@code null} permitted)
     * @param valueAxisLabel    the label for the value axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     */
    public CategoryStatisticalLineChart(DefaultStatisticalCategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, PlotOrientation.VERTICAL, true, true, false);
    }

}
