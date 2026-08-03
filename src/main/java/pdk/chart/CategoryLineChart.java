package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.model.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;

import java.awt.*;
import java.util.Objects;

/**
 * A line chart with a {@link CategoryAxis} as the domain axis.
 * <p>
 * The default renderer is a {@link LineAndShapeRenderer} with lines visible
 * and shapes hidden.  Lines, shapes, strokes, tool‑tips and URLs can be
 * configured through the provided setter methods.
 * <p>
 * For a value‑axis‑based line chart, see {@link LineChart}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 2:23 PM
 */
public class CategoryLineChart extends CategoryChart {

    protected LineAndShapeRenderer renderer1_;

    @Override
    protected void initRenderer() {
        this.renderer1_ = new LineAndShapeRenderer(true, false);
        renderer0_ = renderer1_;
    }

    @Override
    public LineAndShapeRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Creates a new empty line chart with the given title and legend flag.
     * No dataset or axes are attached initially.
     *
     * @param title        the chart title ({@code null} permitted)
     * @param createLegend whether to include a legend
     */
    protected CategoryLineChart(String title, boolean createLegend) {
        super(title, createLegend);
    }

    /**
     * Full constructor – every option is exposed.
     *
     * @param dataset         the dataset ({@code null} permitted)
     * @param domainAxisLabel the category axis label ({@code null} permitted)
     * @param rangeAxisLabel  the value axis label ({@code null} permitted)
     * @param title           the chart title ({@code null} permitted)
     * @param orientation     the plot orientation ({@code null} not permitted)
     * @param legend          {@code true} to include a legend
     * @param tooltips        {@code true} to enable standard tool‑tips
     * @param urls            {@code true} to generate URLs for data points
     */
    public CategoryLineChart(CategoryDataset dataset,
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
     * Creates a line chart with the given parameters; URLs are disabled.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the category axis label ({@code null} permitted)
     * @param valueAxisLabel    the value axis label ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not permitted)
     * @param legend            {@code true} to include a legend
     * @param tooltips          {@code true} to enable standard tool‑tips
     */
    public CategoryLineChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
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
    public CategoryLineChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, true, true);
    }

    /**
     * Creates a vertical line chart with legend and tooltips enabled, no URLs.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the category axis label ({@code null} permitted)
     * @param valueAxisLabel    the value axis label ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     */
    public CategoryLineChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, PlotOrientation.VERTICAL);
    }

    /**
     * Creates a vertical line chart with no title, legend and tooltips enabled.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisLabel the category axis label ({@code null} permitted)
     * @param valueAxisLabel    the value axis label ({@code null} permitted)
     */
    public CategoryLineChart(CategoryDataset dataset,
            String categoryAxisLabel, String valueAxisLabel) {
        this(dataset, categoryAxisLabel, valueAxisLabel, null);
    }

    /**
     * Creates a vertical line chart with no axis labels, no title,
     * legend and tooltips enabled.
     *
     * @param dataset the dataset ({@code null} permitted)
     */
    public CategoryLineChart(CategoryDataset dataset) {
        this(dataset, null, null);
    }

    /**
     * Creates a line chart from two arrays representing categories and
     * values.  Vertical orientation, no legend, tooltips enabled.
     *
     * @param categories the category names (must not be {@code null})
     * @param values     the values for each category (must not be {@code null}
     *                   and same length as {@code categories})
     */
    public CategoryLineChart(String[] categories, double[] values) {
        this(Data.createCategory("", categories, values), null, null, null,
                PlotOrientation.VERTICAL, false, true);
    }

    /**
     * Sets the default shapes visible flag.
     *
     * @param flag {@code true} to show shapes by default
     */
    public void setDefaultShapesVisible(boolean flag) {
        renderer1_.setDefaultShapesVisible(flag);
    }

    /**
     * Sets the default shapes filled flag.
     *
     * @param flag {@code true} to fill shapes by default
     */
    public void setDefaultShapesFilled(boolean flag) {
        renderer1_.setDefaultShapesFilled(flag);
    }

    /**
     * Sets the shape for a specific series.
     *
     * @param series the series index (zero‑based)
     * @param shape  the shape ({@code null} permitted)
     */
    public void setSeriesShape(int series, Shape shape) {
        renderer1_.setSeriesShape(series, shape);
    }

    /**
     * Sets the shapes visible flag for a series.
     *
     * @param series the series index (zero‑based)
     * @param flag   {@code true} to show shapes for the series,
     *               {@code null} to use the default
     */
    public void setSeriesShapesVisible(int series, Boolean flag) {
        renderer1_.setSeriesShapesVisible(series, flag);
    }

    /**
     * Sets the lines visible flag for a series.
     *
     * @param series  the series index (zero‑based)
     * @param visible {@code true} to show lines for the series
     */
    public void setSeriesLinesVisible(int series, boolean visible) {
        renderer1_.setSeriesLinesVisible(series, visible);
    }

    /**
     * Sets the stroke used for a series.
     *
     * @param series the series index (zero-based).
     * @param stroke the stroke ({@code null} permitted).
     */
    public void setSeriesStroke(int series, Stroke stroke) {
        renderer1_.setSeriesStroke(series, stroke);
    }

    /**
     * Sets the line width for a series, preserving the existing stroke’s
     * other attributes (cap, join, dash pattern, etc.).
     *
     * @param series the series index (zero‑based)
     * @param width  the new line width
     */
    public void setSeriesStrokeWidth(int series, float width) {
        renderer1_.withSeriesStrokeWidth(series, width);
    }

    /**
     * Sets whether shape outlines are drawn.
     * <p>
     * In many cases shapes look better without an outline; this flag
     * allows you to override the default.
     *
     * @param flag {@code true} to draw outlines
     */
    public void setDrawOutlines(boolean flag) {
        renderer1_.setDrawOutlines(flag);
    }

    /**
     * Sets the outline stroke for a series.
     *
     * @param series the series index (zero‑based)
     * @param stroke the stroke ({@code null} permitted)
     */
    public void setSeriesOutlineStroke(int series, Stroke stroke) {
        renderer1_.setSeriesOutlineStroke(series, stroke);
    }

    /**
     * Sets whether the fill paint is used to fill shapes.
     *
     * @param flag {@code true} to use the fill paint
     */
    public void setUseFillPaint(boolean flag) {
        renderer1_.setUseFillPaint(flag);
    }

    /**
     * Sets whether the series shape list is automatically populated when
     * {@link #lookupSeriesShape(int)} is called.
     *
     * @param auto {@code true} to auto‑populate
     */
    public void setAutoPopulateSeriesShape(boolean auto) {
        renderer1_.setAutoPopulateSeriesShape(auto);
    }

    /**
     * Sets whether each series is offset within its category (to avoid
     * overlapping lines).
     *
     * @param offset {@code true} to use series offset
     */
    public void setUseSeriesOffset(boolean offset) {
        renderer1_.setUseSeriesOffset(offset);
    }
}
