package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.IntervalBarRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 2:23 PM
 */
public class CategoryIntervalBarChart extends CategoryBarChart {

    private IntervalBarRenderer renderer2_;

    @Override
    protected void initRenderer() {
        renderer2_ = new IntervalBarRenderer();
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
    }

    /**
     * Creates a bar chart.
     *
     * @param title        the chart title ({@code null} permitted).
     * @param xAxisLabel   the label for the category axis
     *                     ({@code null} permitted).
     * @param yAxisLabel   the label for the value axis
     *                     ({@code null} permitted).
     * @param dataset      the dataset for the chart ({@code null} permitted).
     * @param orientation  the plot orientation (horizontal or vertical)
     *                     ({@code null} not permitted).
     * @param createLegend a flag specifying whether a legend is required.
     * @param tooltips     configure chart to generate tool tips?
     * @param urls         configure chart to generate URLs?
     */
    public CategoryIntervalBarChart(CategoryDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean createLegend, boolean tooltips, boolean urls) {
        super(title, createLegend);

        xAxis_ = new CategoryAxis(xAxisLabel);
        yAxis_ = new NumberAxis(yAxisLabel);
        if (tooltips) {
            renderer2_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer2_.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }
        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setOrientation(orientation);
        plot_.setRenderer(renderer2_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a bar chart.
     *
     * @param title        the chart title ({@code null} permitted).
     * @param xAxisLabel   the label for the category axis
     *                     ({@code null} permitted).
     * @param yAxisLabel   the label for the value axis
     *                     ({@code null} permitted).
     * @param dataset      the dataset for the chart ({@code null} permitted).
     * @param orientation  the plot orientation (horizontal or vertical)
     *                     ({@code null} not permitted).
     * @param createLegend a flag specifying whether a legend is required.
     * @param tooltips     configure chart to generate tool tips?
     */
    public CategoryIntervalBarChart(CategoryDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean createLegend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title, orientation, createLegend, tooltips, false);
    }
}
