package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.KeyToGroupMap;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.StackedBarRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Aug 2026, 4:49 PM
 */
public class CategoryStackedBarChart extends CategoryBarChart {

    protected StackedBarRenderer renderer2_;

    @Override
    protected void initRenderer() {
        renderer2_ = new StackedBarRenderer();
        renderer1_ = renderer2_;
        renderer0_ = renderer2_;
    }

    /**
     * Creates a stacked bar chart with default settings.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedBarRenderer}
     * as the renderer.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  the label for the category axis
     *                    ({@code null} permitted).
     * @param yAxisLabel  the label for the value axis
     *                    ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the orientation of the chart (horizontal or
     *                    vertical) ({@code null} not permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     */
    public CategoryStackedBarChart(CategoryDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);

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
        plot_.setRenderer(renderer2_);
        plot_.setDataset(dataset);
        plot_.setOrientation(orientation);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a stacked bar chart with default settings.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedBarRenderer}
     * as the renderer.
     *
     * @param title           the chart title ({@code null} permitted).
     * @param domainAxisLabel the label for the category axis
     *                        ({@code null} permitted).
     * @param rangeAxisLabel  the label for the value axis
     *                        ({@code null} permitted).
     * @param dataset         the dataset for the chart ({@code null} permitted).
     * @param orientation     the orientation of the chart (horizontal or
     *                        vertical) ({@code null} not permitted).
     * @param legend          a flag specifying whether a legend is required.
     * @param tooltips        configure chart to generate tool tips?
     */
    public CategoryStackedBarChart(CategoryDataset dataset, String domainAxisLabel, String rangeAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, domainAxisLabel, rangeAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a stacked bar chart with default settings.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedBarRenderer}
     * as the renderer.
     *
     * @param title           the chart title ({@code null} permitted).
     * @param domainAxisLabel the label for the category axis
     *                        ({@code null} permitted).
     * @param rangeAxisLabel  the label for the value axis
     *                        ({@code null} permitted).
     * @param dataset         the dataset for the chart ({@code null} permitted).
     */
    public CategoryStackedBarChart(CategoryDataset dataset, String domainAxisLabel, String rangeAxisLabel,
            String title) {
        this(dataset, domainAxisLabel, rangeAxisLabel, title, PlotOrientation.VERTICAL, true, true);
    }

    /**
     * Updates the map used to assign each series to a group, and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param map the map ({@code null} not permitted).
     */
    public void setSeriesToGroupMap(KeyToGroupMap map) {
        renderer2_.setSeriesToGroupMap(map);
    }

    /**
     * Sets the flag that controls whether the renderer displays each item
     * value as a percentage (so that the stacked bars add to 100%), and sends
     * a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param asPercentages the flag.
     */
    public void setRenderAsPercentages(boolean asPercentages) {
        renderer2_.setRenderAsPercentages(asPercentages);
    }

}
