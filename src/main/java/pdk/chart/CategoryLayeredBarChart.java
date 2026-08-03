package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.LayeredBarRenderer;
import pdk.chart.text.TextAnchor;
import pdk.chart.urls.StandardCategoryURLGenerator;

/**
 * A layered bar chart where multiple series are drawn as stacked
 * bars using a {@link LayeredBarRenderer}.
 * <p>
 * The renderer places bars from different series on top of each other
 * (layered) rather than side‑by‑side, making it useful for showing
 * overlapping ranges or contributions.  Item label positions are
 * automatically configured based on the plot orientation.
 * <p>
 * Tool‑tips and URLs can be enabled via constructor flags.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Aug 2026, 4:41 PM
 */
public class CategoryLayeredBarChart extends CategoryBarChart {

    private LayeredBarRenderer renderer2_;

    /**
     * Initializes the renderer to a {@link LayeredBarRenderer} and
     * updates the parent renderer references so that inherited methods
     * operate on the correct renderer.
     */
    @Override
    protected void initRenderer() {
        renderer2_ = new LayeredBarRenderer();
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
    }

    @Override
    public LayeredBarRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Full constructor.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the category axis label ({@code null} permitted)
     * @param valueAxisLabel    the value axis label ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not permitted)
     * @param legend            {@code true} to include a legend
     * @param tooltips          {@code true} to enable standard tool‑tips
     * @param urls              {@code true} to generate URLs for data points
     */
    public CategoryLayeredBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        CategoryAxis xAxis_ = new CategoryAxis(categoryAxisLabel);
        NumberAxis yAxis_ = new NumberAxis(valueAxisLabel);

        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
            renderer2_.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
            renderer2_.setDefaultNegativeItemLabelPosition(position2);
        } else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
            renderer2_.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
            renderer2_.setDefaultNegativeItemLabelPosition(position2);
        }

        if (tooltips) {
            renderer1_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer1_.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }
        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);
        plot_.setOrientation(orientation);
        JChart.applyCurrentTheme(this);
    }


    /**
     * Creates a layered bar chart with the given parameters; URLs are disabled.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the category axis label ({@code null} permitted)
     * @param valueAxisLabel    the value axis label ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not permitted)
     * @param legend            {@code true} to include a legend
     * @param tooltips          {@code true} to enable standard tool‑tips
     */
    public CategoryLayeredBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Convenience constructor with legend and tooltips enabled, no URLs.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the category axis label ({@code null} permitted)
     * @param valueAxisLabel    the value axis label ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not permitted)
     */
    public CategoryLayeredBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, true, true, false);
    }

}
