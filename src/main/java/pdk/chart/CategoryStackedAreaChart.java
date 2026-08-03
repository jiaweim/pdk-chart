package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.StackedAreaRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;

import java.util.Objects;

/**
 * A stacked area chart with a {@link CategoryAxis} as the domain axis.
 * <p>
 * Uses a {@link StackedAreaRenderer} to draw the series as filled areas
 * stacked on top of each other.  The category margin is set to zero
 * so that the area fills the entire width of each category.
 * <p>
 * This class extends {@link CategoryAreaChart} and replaces the default
 * renderer with a {@link StackedAreaRenderer}.  Tool‑tips and URL
 * generation can be enabled via constructor flags.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 3:20 PM
 */
public class CategoryStackedAreaChart extends CategoryAreaChart {

    private StackedAreaRenderer renderer2_;

    /**
     * Initializes the renderer to a {@link StackedAreaRenderer} and updates
     * the parent renderer references so that inherited methods operate on
     * the correct renderer.
     */
    @Override
    protected void initRenderer() {
        renderer2_ = new StackedAreaRenderer();
        renderer1_ = renderer2_;
        renderer0_ = renderer2_;
    }

    /**
     * Returns the stacked area renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public StackedAreaRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Full constructor – every option is exposed.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the category axis ({@code null} permitted)
     * @param valueAxisLabel    the label for the value axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not permitted)
     * @param legend            {@code true} to include a legend
     * @param tooltips          {@code true} to enable standard tool‑tips
     * @param urls              {@code true} to generate URLs for data points
     */
    public CategoryStackedAreaChart(CategoryDataset dataset, String categoryAxisLabel,
            String valueAxisLabel, String title, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);

        CategoryAxis xAxis_ = new CategoryAxis(categoryAxisLabel);
        xAxis_.setCategoryMargin(0.0);
        NumberAxis yAxis_ = new NumberAxis(valueAxisLabel);

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
     * Creates a stacked area chart with the given parameters; URLs are
     * disabled.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the category axis ({@code null} permitted)
     * @param valueAxisLabel    the label for the value axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not permitted)
     * @param legend            {@code true} to include a legend
     * @param tooltips          {@code true} to enable standard tool‑tips
     */
    public CategoryStackedAreaChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Convenience constructor with vertical orientation, legend and tooltips
     * enabled, no URLs.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the category axis ({@code null} permitted)
     * @param valueAxisLabel    the label for the value axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     */
    public CategoryStackedAreaChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, PlotOrientation.VERTICAL,
                true, true, false);
    }

}
