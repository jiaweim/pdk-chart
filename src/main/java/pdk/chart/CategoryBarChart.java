package pdk.chart;

import org.jspecify.annotations.NonNull;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.model.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarPainter;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.text.TextAnchor;
import pdk.chart.urls.StandardCategoryURLGenerator;
import pdk.chart.util.GradientPaintTransformer;

import java.util.Objects;

/**
 * A bar chart with a {@link CategoryAxis} as the domain axis.
 * <p>
 * Uses a {@link BarRenderer} to draw the bars.  The item label positions
 * are automatically adjusted based on the plot orientation.  Tool‑tips
 * and URLs can be enabled via constructor flags.
 * <p>
 * For customization beyond the defaults, use the setter methods inherited
 * from {@link CategoryChart} or directly access the renderer through
 * {@link #getRenderer()}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 7:16 PM
 */
public class CategoryBarChart extends CategoryChart {

    protected BarRenderer renderer1_;

    /**
     * Initializes the renderer to a {@link BarRenderer} and assigns it
     * to the parent’s renderer reference.
     */
    @Override
    protected void initRenderer() {
        renderer1_ = new BarRenderer();
        renderer0_ = renderer1_;
    }

    /**
     * Returns the bar renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public BarRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Constructor for subclass use.  Does not attach any dataset or axes;
     * these must be configured separately.
     *
     * @param title        the chart title ({@code null} permitted)
     * @param createLegend whether a legend should be created
     */
    protected CategoryBarChart(String title, boolean createLegend) {
        super(title, createLegend);
    }

    /**
     * Full constructor – every option is exposed.
     *
     * @param dataset      the dataset ({@code null} permitted)
     * @param xAxisLabel   the label for the category axis ({@code null} permitted)
     * @param yAxisLabel   the label for the value axis ({@code null} permitted)
     * @param title        the chart title ({@code null} permitted)
     * @param orientation  the plot orientation ({@code null} not permitted)
     * @param createLegend {@code true} to include a legend
     * @param tooltips     {@code true} to enable standard tool‑tips
     * @param urls         {@code true} to generate URLs for data points
     */
    public CategoryBarChart(CategoryDataset dataset, String xAxisLabel, String yAxisLabel, String title, PlotOrientation orientation,
            boolean createLegend, boolean tooltips, boolean urls) {
        super(title, createLegend);
        Objects.requireNonNull(orientation);

        CategoryAxis xAxis_ = new CategoryAxis(xAxisLabel);
        NumberAxis yAxis_ = new NumberAxis(yAxisLabel);

        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
            renderer1_.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
            renderer1_.setDefaultNegativeItemLabelPosition(position2);
        } else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
            renderer1_.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
            renderer1_.setDefaultNegativeItemLabelPosition(position2);
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
     * Creates a bar chart with the given parameters; URLs are disabled.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the category axis ({@code null} permitted)
     * @param valueAxisLabel    the label for the value axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not permitted)
     * @param legend            {@code true} to include a legend
     * @param tooltips          {@code true} to enable standard tool‑tips
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a bar chart with legend and tooltips enabled, no URLs.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the category axis ({@code null} permitted)
     * @param valueAxisLabel    the label for the value axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not permitted)
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title,
                orientation, true, true);
    }

    /**
     * Creates a vertical bar chart with legend and tooltips enabled.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the category axis ({@code null} permitted)
     * @param valueAxisLabel    the label for the value axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel,
            String valueAxisLabel, String title) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, PlotOrientation.VERTICAL);
    }

    /**
     * Creates a vertical bar chart with no title, legend and tooltips enabled.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the category axis ({@code null} permitted)
     * @param valueAxisLabel    the label for the value axis ({@code null} permitted)
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel) {
        this(dataset, categoryAxisLabel, valueAxisLabel, null);
    }

    /**
     * Creates a bar chart with the given orientation, no axis labels, no title,
     * legend and tooltips enabled.
     *
     * @param dataset     the dataset ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     */
    public CategoryBarChart(CategoryDataset dataset, PlotOrientation orientation) {
        this(dataset, null, null, null, orientation);
    }

    /**
     * Creates a vertical bar chart with no axis labels, no title,
     * legend and tooltips enabled.
     *
     * @param dataset the dataset ({@code null} permitted)
     */
    public CategoryBarChart(CategoryDataset dataset) {
        this(dataset, PlotOrientation.VERTICAL);
    }

    /**
     * Creates a bar chart from two arrays with the given orientation,
     * no axis labels, no title, legend and tooltips enabled.
     *
     * @param categories  the category names (must not be {@code null})
     * @param values      the values (must not be {@code null} and same length
     *                    as {@code categories})
     * @param orientation the plot orientation ({@code null} not permitted)
     */
    public CategoryBarChart(String[] categories, double[] values, PlotOrientation orientation) {
        this(Data.createCategory("", categories, values), orientation);
    }

    /**
     * Creates a vertical bar chart from two arrays, no axis labels, no title,
     * no legend, tooltips disabled.
     *
     * @param categories the category names (must not be {@code null})
     * @param values     the values (must not be {@code null} and same length
     *                   as {@code categories})
     */
    public CategoryBarChart(String[] categories, double[] values) {
        this(Data.createCategory("", categories, values), null, null, null,
                PlotOrientation.VERTICAL, false, false);
    }

    /**
     * Sets the item margin for the bars.
     * <p>
     * The margin is expressed as a percentage of the available width for
     * all bars and is distributed evenly between them.
     *
     * @param percent the margin (e.g. 0.10 = 10%)
     */
    public void setItemMargin(double percent) {
        this.renderer1_.setItemMargin(percent);
    }

    /**
     * Sets whether bar outlines are drawn.
     *
     * @param draw {@code true} to draw outlines, {@code false} otherwise
     */
    public void setDrawBarOutline(boolean draw) {
        this.renderer1_.setDrawBarOutline(draw);
    }

    /**
     * Sets the gradient paint transformer.
     *
     * @param transformer the transformer ({@code null} permitted)
     */
    public void setGradientPaintTransformer(GradientPaintTransformer transformer) {
        this.renderer1_.setGradientPaintTransformer(transformer);
    }

    /**
     * Sets the fallback position for positive item labels that don't fit
     * within a bar.
     *
     * @param position the position ({@code null} permitted).
     */
    public void setPositiveItemLabelPositionFallback(ItemLabelPosition position) {
        renderer1_.setPositiveItemLabelPositionFallback(position);
    }

    /**
     * Controls whether shadows are drawn under the bars.
     *
     * @param visible {@code true} to display shadows
     */
    public void setShadowVisible(boolean visible) {
        renderer1_.setShadowVisible(visible);
    }

    /**
     * Sets the base value for the bars (the baseline for bar lengths).
     *
     * @param base the new base value.
     */
    public void setBase(double base) {
        renderer1_.setBase(base);
    }

    /**
     * Controls whether the base value is included in the auto‑calculated
     * axis range.
     *
     * @param include {@code true} to include the base value in the range
     */
    public void setIncludeBaseInRange(boolean include) {
        renderer1_.setIncludeBaseInRange(include);
    }

    /**
     * Sets the bar painter used to fill the bars.
     *
     * @param painter the painter ({@code null} not permitted)
     */
    public void setBarPainter(BarPainter painter) {
        renderer1_.setBarPainter(painter);
    }

    /**
     * Sets the insets for item labels.
     *
     * @param itemLabelInsets the insets (not {@code null})
     */
    public void setItemLabelInsets(@NonNull RectangleInsets itemLabelInsets) {
        renderer1_.setItemLabelInsets(itemLabelInsets);
    }

    /**
     * Sets the maximum bar width, which is specified as a percentage of the
     * available space for all bars, and sends a {@link RendererChangeEvent} to
     * all registered listeners.
     *
     * @param percent the percent (where 0.05 is five percent).
     */
    public void setMaximumBarWidth(double percent) {
        renderer1_.setMaximumBarWidth(percent);
    }
}
