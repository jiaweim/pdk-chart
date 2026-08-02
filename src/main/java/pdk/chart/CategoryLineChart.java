package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.model.Data;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;

import java.awt.*;
import java.util.Objects;

/**
 * Line chart with category domain axis.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 2:23 PM
 */
public class CategoryLineChart extends CategoryChart {

    protected CategoryAxis xAxis_;
    protected NumberAxis yAxis_;
    protected LineAndShapeRenderer renderer1_;

    @Override
    protected void initRenderer() {
        this.renderer1_ = new LineAndShapeRenderer(true, false);
        renderer0_ = renderer1_;
    }

    protected CategoryLineChart(String title, boolean createLegend) {
        super(title, createLegend);
    }

    /**
     * Creates a line chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link LineAndShapeRenderer} as the renderer.
     *
     * @param title           the chart title ({@code null} permitted).
     * @param domainAxisLabel the label for the category axis
     *                        ({@code null} permitted).
     * @param rangeAxisLabel  the label for the value axis ({@code null}
     *                        permitted).
     * @param dataset         the dataset for the chart ({@code null} permitted).
     * @param orientation     the chart orientation (horizontal or vertical)
     *                        ({@code null} not permitted).
     * @param legend          a flag specifying whether a legend is required.
     * @param tooltips        configure chart to generate tool tips?
     * @param urls            configure chart to generate URLs?
     */
    public CategoryLineChart(CategoryDataset dataset,
            String domainAxisLabel, String rangeAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);

        this.xAxis_ = new CategoryAxis(domainAxisLabel);
        this.yAxis_ = new NumberAxis(rangeAxisLabel);

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
     * Creates a line chart with default settings.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the chart orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     */
    public CategoryLineChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a line chart with default settings.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the chart orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     */
    public CategoryLineChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, true, true);
    }

    /**
     * Creates a line chart with default settings.  The chart object returned
     * by this method uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link LineAndShapeRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     */
    public CategoryLineChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, PlotOrientation.VERTICAL);
    }

    /**
     * Creates a line chart with default settings.
     *
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     */
    public CategoryLineChart(CategoryDataset dataset,
            String categoryAxisLabel, String valueAxisLabel) {
        this(dataset, categoryAxisLabel, valueAxisLabel, null);
    }

    /**
     * Creates a line chart with default settings.
     *
     * @param dataset the dataset for the chart ({@code null} permitted).
     */
    public CategoryLineChart(CategoryDataset dataset) {
        this(dataset, null, null);
    }

    /**
     * Creates a line chart with default settings.
     *
     * @param categories categories of the dataset
     * @param values     values of the dataset
     */
    public CategoryLineChart(String[] categories, double[] values) {
        this(Data.createCategory("", categories, values), null, null, null,
                PlotOrientation.VERTICAL, false, true);
    }

    public NumberAxis getRangeAxis() {
        return yAxis_;
    }

    /**
     * Sets the default 'shapes visible' flag and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag the flag.
     */
    public void setDefaultShapesVisible(boolean flag) {
        renderer1_.setDefaultShapesVisible(flag);
    }

    /**
     * Sets the default 'shapes filled' flag and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag the flag.
     */
    public void setDefaultShapesFilled(boolean flag) {
        renderer1_.setDefaultShapesFilled(flag);
    }

    /**
     * Sets the shape used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param shape  the shape ({@code null} permitted).
     */
    public void setSeriesShape(int series, Shape shape) {
        renderer1_.setSeriesShape(series, shape);
    }

    /**
     * Sets the 'shapes visible' flag for a series.
     *
     * @param series the series index (zero-based).
     * @param flag   the flag.
     */
    public void setSeriesShapesVisible(int series, Boolean flag) {
        renderer1_.setSeriesShapesVisible(series, flag);
    }

    /**
     * Sets the 'lines visible' flag for a series.
     *
     * @param series  the series index (zero-based).
     * @param visible the flag.
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
     * Set the line width of a given series
     *
     * @param series series index
     * @param width  line width
     */
    public void setSeriesLinesWidth(int series, float width) {
        Stroke seriesStroke = renderer1_.getSeriesStroke(series);
        if (seriesStroke == null) {
            setSeriesStroke(series, new BasicStroke(width));
        } else {
            BasicStroke stroke = (BasicStroke) seriesStroke;
            setSeriesStroke(series,
                    new BasicStroke(width, stroke.getEndCap(),
                            stroke.getLineJoin(), stroke.getMiterLimit(),
                            stroke.getDashArray(), stroke.getDashPhase()));
        }
    }


    /**
     * Sets the flag that controls whether outlines are drawn for
     * shapes.
     * <p>
     * In some cases, shapes look better if they do NOT have an outline, but
     * this flag allows you to set your own preference.
     *
     * @param flag the flag.
     */
    public void setDrawOutlines(boolean flag) {
        renderer1_.setDrawOutlines(flag);
    }

    /**
     * Sets the outline stroke used for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param stroke the stroke ({@code null} permitted).
     */
    public void setSeriesOutlineStroke(int series, Stroke stroke) {
        renderer1_.setSeriesOutlineStroke(series, stroke);
    }

    /**
     * Sets the flag that controls whether the fill paint is used to fill
     * shapes.
     *
     * @param flag the flag.
     */
    public void setUseFillPaint(boolean flag) {
        renderer1_.setUseFillPaint(flag);
    }

    /**
     * Sets the flag that controls whether the series shape list is
     * automatically populated when {@link #lookupSeriesShape(int)} is called.
     *
     * @param auto the new flag value.
     */
    public void setAutoPopulateSeriesShape(boolean auto) {
        renderer1_.setAutoPopulateSeriesShape(auto);
    }

    /**
     * Sets the flag that controls whether the x-position for each
     * data item is offset within its category according to the series, and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param offset the offset.
     */
    public void setUseSeriesOffset(boolean offset) {
        renderer1_.setUseSeriesOffset(offset);
    }
}
