package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.model.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.AreaRendererEndType;
import pdk.chart.renderer.category.AreaRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;

import java.util.Objects;

/**
 * An area chart with a {@link CategoryAxis} as the domain axis.
 * <p>
 * The chart uses an {@link AreaRenderer} with {@link AreaRendererEndType#LEVEL}
 * as the default end type.  The category margin of the domain axis is set to
 * {@code 0.0} so that the area fills the entire width of each category.
 * <p>
 * Tool‑tips and URL generation can be enabled via constructor flags.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 6:26 PM
 */
public class CategoryAreaChart extends CategoryChart {

    protected AreaRenderer renderer1_;

    /**
     * Initializes the renderer to an {@link AreaRenderer} with
     * {@link AreaRendererEndType#LEVEL} and assigns it to the parent’s
     * renderer reference.
     */
    @Override
    protected void initRenderer() {
        renderer1_ = new AreaRenderer();
        renderer1_.setEndType(AreaRendererEndType.LEVEL);
        renderer0_ = renderer1_;
    }

    /**
     * Returns the area renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public AreaRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Creates a new empty area chart with the given title and legend flag.
     *
     * @param title        the chart title ({@code null} permitted)
     * @param createLegend whether to include a legend
     */
    protected CategoryAreaChart(String title, boolean createLegend) {
        super(title, createLegend);
    }

    /**
     * Full constructor.
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
    public CategoryAreaChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);

        CategoryAxis xAxis_ = new CategoryAxis(categoryAxisLabel);
        xAxis_.setCategoryMargin(0.0);
        NumberAxis yAxis_ = new NumberAxis(valueAxisLabel);

        if (tooltips) {
            renderer1_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer1_.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setOrientation(orientation);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates an area chart with the given parameters; URLs are disabled.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the category axis ({@code null} permitted)
     * @param valueAxisLabel    the label for the value axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not permitted)
     * @param legend            {@code true} to include a legend
     * @param tooltips          {@code true} to enable standard tool‑tips
     */
    public CategoryAreaChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
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
    public CategoryAreaChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, PlotOrientation.VERTICAL,
                true, true, false);
    }

    /**
     * Creates an area chart from a dataset with no axis labels or title.
     *
     * @param dataset the dataset ({@code null} permitted)
     */
    public CategoryAreaChart(CategoryDataset dataset) {
        this(dataset, null, null, null);
    }

    /**
     * Creates an area chart from two arrays representing categories and values.
     * <p>
     * The chart has vertical orientation, no legend, tooltips enabled,
     * and no axis labels or title.
     *
     * @param categories the category names (must not be {@code null})
     * @param values     the values for each category (must not be {@code null}
     *                   and must have the same length as {@code categories})
     */
    public CategoryAreaChart(String[] categories, double[] values) {
        this(Data.createCategory("", categories, values),
                null, null, null, PlotOrientation.VERTICAL, false, true);
    }

    /**
     * Sets the end type for the area renderer, which controls how the area
     * is drawn at the beginning and end of the data sequence.
     *
     * @param type the end type ({@code null} not permitted)
     */
    public void setEndType(AreaRendererEndType type) {
        renderer1_.setEndType(type);
    }

}
