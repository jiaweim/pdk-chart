package pdk.chart;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.model.Data;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarPainter;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.text.TextAnchor;
import pdk.chart.urls.StandardCategoryURLGenerator;
import pdk.chart.util.GradientPaintTransformer;

import java.util.Objects;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 7:16 PM
 */
public class CategoryBarChart extends CategoryChart {

    protected CategoryAxis xAxis_;
    protected NumberAxis yAxis_;
    protected BarRenderer renderer1_;

    @Override
    protected void initRenderer() {
        renderer1_ = new BarRenderer();
        renderer0_ = renderer1_;
    }

    protected CategoryBarChart(String title, boolean createLegend) {
        super(title, createLegend);
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
    public CategoryBarChart(CategoryDataset dataset, String xAxisLabel, String yAxisLabel, String title, PlotOrientation orientation,
            boolean createLegend, boolean tooltips, boolean urls) {
        super(title, createLegend);
        Objects.requireNonNull(orientation);

        xAxis_ = new CategoryAxis(xAxisLabel);
        yAxis_ = new NumberAxis(yAxisLabel);

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
     * Creates a bar chart.  The chart object returned by this method uses a
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link NumberAxis} as the range axis, and a
     * {@link BarRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a bar chart.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, true, true);
    }

    /**
     * Creates a bar chart.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel,
            String valueAxisLabel, String title) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, PlotOrientation.VERTICAL);
    }

    /**
     * Creates a bar chart.
     *
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel) {
        this(dataset, categoryAxisLabel, valueAxisLabel, null);
    }

    /**
     * Creates a bar chart.
     *
     * @param dataset the dataset for the chart ({@code null} permitted).
     */
    public CategoryBarChart(CategoryDataset dataset, PlotOrientation orientation) {
        this(dataset, null, null, null, orientation);
    }

    /**
     * Creates a bar chart.
     *
     * @param dataset the dataset for the chart ({@code null} permitted).
     */
    public CategoryBarChart(CategoryDataset dataset) {
        this(dataset, PlotOrientation.VERTICAL);
    }

    /**
     * Creates a bar chart.
     *
     * @param categories  category values.
     * @param values      values
     * @param orientation {@link PlotOrientation}
     */
    public CategoryBarChart(String[] categories, double[] values, PlotOrientation orientation) {
        this(Data.createCategory("", categories, values), orientation);
    }

    /**
     * Creates a bar chart.
     *
     * @param categories category values.
     * @param values     values
     */
    public CategoryBarChart(String[] categories, double[] values) {
        this(Data.createCategory("", categories, values), null, null, null,
                PlotOrientation.VERTICAL, false, false);
    }

    /**
     * Sets the item margin of bars.
     * <p>
     * The value is expressed as a percentage of the
     * available width for plotting all the bars, with the resulting amount to
     * be distributed between all the bars evenly.
     *
     * @param percent the margin (where 0.10 is ten percent).
     */
    public void setItemMargin(double percent) {
        this.renderer1_.setItemMargin(percent);
    }

    /**
     * Sets the flag that controls whether bar outlines are drawn and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param draw the flag.
     */
    public void setDrawBarOutline(boolean draw) {
        this.renderer1_.setDrawBarOutline(draw);
    }

    /**
     * Sets the gradient paint transformer
     *
     * @param transformer the transformer.
     */
    public void setGradientPaintTransformer(
            @Nullable GradientPaintTransformer transformer) {
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
     * Sets the flag that controls whether shadows are
     * drawn by the renderer.
     *
     * @param visible the new flag value.
     */
    public void setShadowVisible(boolean visible) {
        renderer1_.setShadowVisible(visible);
    }

    /**
     * Sets the base value for the bars and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param base the new base value.
     */
    public void setBase(double base) {
        renderer1_.setBase(base);
    }

    /**
     * Sets the flag that controls whether the base value for the bars
     * is included in the range calculated by
     * {@link #findRangeBounds(CategoryDataset)}.  If the flag is changed,
     * a {@link RendererChangeEvent} is sent to all registered listeners.
     *
     * @param include the new value for the flag.
     */
    public void setIncludeBaseInRange(boolean include) {
        renderer1_.setIncludeBaseInRange(include);
    }

    /**
     * Sets the bar painter for this renderer and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param painter the painter ({@code null} not permitted).
     */
    public void setBarPainter(BarPainter painter) {
        renderer1_.setBarPainter(painter);
    }

    /**
     * Sets the item label insets.
     *
     * @param itemLabelInsets the insets
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
