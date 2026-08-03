package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.KeyToGroupMap;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.StackedBarRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;

/**
 * A stacked bar chart with a {@link CategoryAxis} as the domain axis.
 * <p>
 * Uses a {@link StackedBarRenderer} to draw the bars, stacking series on top
 * of each other.  The category axis margins are inherited from the parent
 * {@link CategoryBarChart} and can be adjusted with its setter methods.
 * <p>
 * Tool‑tips and URLs can be enabled via constructor flags.  For finer
 * control (e.g. rendering as percentages, grouping series), use the
 * provided setter methods.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Aug 2026, 4:49 PM
 */
public class CategoryStackedBarChart extends CategoryBarChart {

    protected StackedBarRenderer renderer2_;

    /**
     * Initializes the renderer to a {@link StackedBarRenderer} and
     * updates the parent renderer references.
     */
    @Override
    protected void initRenderer() {
        renderer2_ = new StackedBarRenderer();
        renderer1_ = renderer2_;
        renderer0_ = renderer2_;
    }

    /**
     * Returns the stacked bar renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public StackedBarRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Full constructor – every option is exposed.
     *
     * @param dataset     the dataset ({@code null} permitted)
     * @param xAxisLabel  the label for the category axis ({@code null} permitted)
     * @param yAxisLabel  the label for the value axis ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     * @param urls        {@code true} to generate URLs for data points
     */
    public CategoryStackedBarChart(CategoryDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);

        CategoryAxis xAxis_ = new CategoryAxis(xAxisLabel);
        NumberAxis yAxis_ = new NumberAxis(yAxisLabel);

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
     * Creates a stacked bar chart with the given parameters; URLs are
     * disabled.
     *
     * @param dataset         the dataset ({@code null} permitted)
     * @param domainAxisLabel the label for the category axis ({@code null} permitted)
     * @param rangeAxisLabel  the label for the value axis ({@code null} permitted)
     * @param title           the chart title ({@code null} permitted)
     * @param orientation     the plot orientation ({@code null} not permitted)
     * @param legend          {@code true} to include a legend
     * @param tooltips        {@code true} to enable standard tool‑tips
     */
    public CategoryStackedBarChart(CategoryDataset dataset, String domainAxisLabel, String rangeAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, domainAxisLabel, rangeAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Convenience constructor with vertical orientation, legend and tooltips
     * enabled, no URLs.
     *
     * @param dataset         the dataset ({@code null} permitted)
     * @param domainAxisLabel the label for the category axis ({@code null} permitted)
     * @param rangeAxisLabel  the label for the value axis ({@code null} permitted)
     * @param title           the chart title ({@code null} permitted)
     */
    public CategoryStackedBarChart(CategoryDataset dataset, String domainAxisLabel, String rangeAxisLabel,
            String title) {
        this(dataset, domainAxisLabel, rangeAxisLabel, title, PlotOrientation.VERTICAL, true, true);
    }

    /**
     * Sets the map used to assign each series to a group, allowing stacked
     * bars to be grouped independently of the series structure.
     *
     * @param map the key‑to‑group map ({@code null} not permitted)
     */
    public void setSeriesToGroupMap(KeyToGroupMap map) {
        renderer2_.setSeriesToGroupMap(map);
    }

    /**
     * Controls whether the stacked bars are rendered as percentages,
     * i.e. the bars for each category add up to 100%.
     *
     * @param asPercentages {@code true} to render as percentages
     */
    public void setRenderAsPercentages(boolean asPercentages) {
        renderer2_.setRenderAsPercentages(asPercentages);
    }

}
