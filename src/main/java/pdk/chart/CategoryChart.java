package pdk.chart;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.annotations.CategoryAnnotation;
import pdk.chart.api.Layer;
import pdk.chart.api.SortOrder;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.CategoryItemLabelGenerator;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.legend.LegendItemCollection;
import pdk.chart.plot.CategoryMarker;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.Marker;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.CategoryItemRenderer;

import java.awt.*;
import java.util.Objects;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 1:42 PM
 */
public class CategoryChart extends Chart {

    public enum ChartType {
        LINE,
        AREA,
        BAR,
        BOX,
    }

    protected final CategoryPlot plot_;
    protected CategoryItemRenderer renderer0_;

    public CategoryChart(String title, boolean createLegend) {
        super(title, DEFAULT_TITLE_FONT, new CategoryPlot<>(), createLegend);
        this.plot_ = getCategoryPlot();
    }

    /**
     * Sets the row order in which the items in each dataset should be
     * rendered and sends a {@link PlotChangeEvent} to all registered
     * listeners.  Note that this affects the order in which items are drawn,
     * NOT their position in the chart.
     *
     * @param order the order ({@code null} not permitted).
     */
    public void setRowRenderingOrder(SortOrder order) {
        plot_.setRowRenderingOrder(order);
    }

    /**
     * Returns the domain axis for the plot.  If the domain axis for this plot
     * is {@code null}, then the method will return the parent plot's
     * domain axis (if there is a parent plot).
     *
     * @return The domain axis ({@code null} permitted).
     */
    public CategoryAxis getDomainAxis() {
        return this.plot_.getDomainAxis();
    }

    /**
     * Sets the domain axis for the plot and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param axis the axis ({@code null} permitted).
     * @see #getDomainAxis()
     */
    public void setDomainAxis(CategoryAxis axis) {
        plot_.setDomainAxis(axis);
    }

    /**
     * Returns the range axis for the plot.  If the range axis for this plot is
     * null, then the method will return the parent plot's range axis (if there
     * is a parent plot).
     *
     * @return The range axis (possibly {@code null}).
     */
    public ValueAxis getRangeAxis() {
        return plot_.getRangeAxis(0);
    }

    public NumberAxis getRangeAxisAsNumber() {
        ValueAxis yAxis = plot_.getRangeAxis();
        if (yAxis instanceof NumberAxis nAxis) {
            return nAxis;
        }
        return null;
    }

    /**
     * Sets the range axis for the plot and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param axis the axis ({@code null} permitted).
     */
    public void setRangeAxis(ValueAxis axis) {
        plot_.setRangeAxis(axis);
    }

    /**
     * Sets the flag that enables or disables panning of the plot along
     * the range axes.
     *
     * @param pannable the new flag value.
     */
    public void setRangePannable(boolean pannable) {
        plot_.setRangePannable(pannable);
    }

    /**
     * Sets the orientation for the plot.
     *
     * @param orientation the orientation ({@code null} not permitted).
     */
    public void setOrientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
    }

    /**
     * Sets the fixed legend items for the plot.  Leave this set to
     * {@code null} if you prefer the legend items to be created
     * automatically.
     *
     * @param items the legend items.
     */
    public void setFixedLegendItems(@Nullable LegendItemCollection items) {
        plot_.setFixedLegendItems(items);
    }

    /**
     * Sets the default shape.
     *
     * @param shape the shape ({@code null} not permitted).
     */
    public void setDefaultShape(Shape shape) {
        renderer0_.setDefaultShape(shape);
    }

    /**
     * Sets the default fill paint and, if requested.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public void setDefaultFillPaint(Paint paint) {
        renderer0_.setDefaultFillPaint(paint);
    }

    /**
     * Sets the default item label generator for this category chart.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public void setDefaultItemLabelGenerator(CategoryItemLabelGenerator generator) {
        renderer0_.setDefaultItemLabelGenerator(generator);
    }

    /**
     * Sets whether item labels are visible.
     *
     * @param visible the flag.
     */
    public void setDefaultItemLabelsVisible(boolean visible) {
        renderer0_.setDefaultItemLabelsVisible(visible);
    }

    /**
     * Sets the paint used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     */
    public void setSeriesPaint(int series, Paint paint) {
        if (renderer0_ != null) {
            renderer0_.setSeriesPaint(series, paint);
        }
    }

    /**
     * Sets the default item label paint and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public void setDefaultItemLabelPaint(Paint paint) {
        if (renderer0_ != null) {
            renderer0_.setDefaultItemLabelPaint(paint);
        }
    }

    /**
     * Adds a marker for display against the domain axis and sends a
     * {@link PlotChangeEvent} to all registered listeners.  Typically a marker
     * will be drawn by the renderer as a line perpendicular to the domain
     * axis, however this is entirely up to the renderer.
     *
     * @param marker the marker ({@code null} not permitted).
     * @param layer  the layer (foreground or background) ({@code null}
     *               not permitted).
     */
    public void addDomainMarker(CategoryMarker marker, Layer layer) {
        plot_.addDomainMarker(marker, layer);
    }

    /**
     * Adds an annotation to the plot and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param annotation the annotation.
     */
    public void addAnnotation(@NonNull CategoryAnnotation annotation) {
        plot_.addAnnotation(annotation);
    }

    /**
     * Sets the location of the domain axis and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param location the axis location ({@code null} not permitted).
     */
    public void setDomainAxisLocation(AxisLocation location) {
        plot_.setDomainAxisLocation(location);
    }

    /**
     * Sets the location for a range axis and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param index    the axis index.
     * @param location the location.
     */
    public void setRangeAxisLocation(int index, AxisLocation location) {
        plot_.setRangeAxisLocation(index, location, true);
    }

    /**
     * Sets a dataset for the plot and sends a change notification to all
     * registered listeners.
     *
     * @param index   the dataset index (must be &gt;= 0).
     * @param dataset the dataset ({@code null} permitted).
     */
    public void setDataset(int index, CategoryDataset dataset) {
        plot_.setDataset(index, dataset);
    }

    public void setDataset(int index, CategoryDataset dataset, ChartType chartType) {
        Objects.requireNonNull(chartType);
        plot_.setDataset(index, dataset);
        if (chartType == ChartType.BAR) {
            plot_.setRenderer(index, new BarRenderer());
        }
    }

    /**
     * Sets the renderer at index 0 (sometimes referred to as the "primary"
     * renderer) and sends a change event to all registered listeners.
     *
     * @param renderer the renderer ({@code null} permitted.
     * @see #getRenderer()
     */
    public void setRenderer(CategoryItemRenderer renderer) {
        plot_.setRenderer(renderer);
    }


    /**
     * Sets the renderer to use for the dataset with the specified index and
     * sends a change event to all registered listeners.  Note that each
     * dataset should have its own renderer, you should not use one renderer
     * for multiple datasets.
     *
     * @param index    the index.
     * @param renderer the renderer ({@code null} permitted).
     */
    public void setRenderer(int index, CategoryItemRenderer renderer) {
        plot_.setRenderer(index, renderer, true);
    }

    /**
     * Maps a dataset to a particular range axis.
     *
     * @param index     the dataset index (zero-based).
     * @param axisIndex the axis index (zero-based).
     */
    public void mapDatasetToRangeAxis(int index, int axisIndex) {
        plot_.mapDatasetToRangeAxis(index, axisIndex);
    }

    /**
     * Sets a range axis and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param index the axis index.
     * @param axis  the axis.
     */
    public void setRangeAxis(int index, ValueAxis axis) {
        plot_.setRangeAxis(index, axis);
    }

    /**
     * Sets a range axis and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param index the axis index.
     * @param axis  the axis.
     */
    public void setRangeAxis(int index, ValueAxis axis, AxisLocation location) {
        plot_.setRangeAxis(index, axis);
        plot_.setRangeAxisLocation(index, location);
    }

    /**
     * Returns a reference to the renderer for the plot.
     *
     * @return The renderer.
     */
    public CategoryItemRenderer getRenderer() {
        return plot_.getRenderer();
    }

    /**
     * Returns the renderer at the given index.
     *
     * @param index the renderer index.
     * @return The renderer (possibly {@code null}).
     * @see #setRenderer(int, CategoryItemRenderer)
     */
    public CategoryItemRenderer getRenderer(int index) {
        return plot_.getRenderer(index);
    }

    /**
     * Returns the dataset with the given index, or {@code null} if there is
     * no dataset.
     *
     * @param index the dataset index (must be &gt;= 0).
     * @return The dataset (possibly {@code null}).
     * @see #setDataset(int, CategoryDataset)
     */
    public CategoryDataset getDataset(int index) {
        return plot_.getDataset(index);
    }

    /**
     * Sets the paint used to draw the grid-lines (if any) against the domain
     * axis and sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public void setDomainGridlinePaint(Paint paint) {
        plot_.setDomainGridlinePaint(paint);
    }

    /**
     * Sets the flag that controls whether grid-lines are drawn against
     * the domain axis. That is, whether to draw grid lines perpendicular to the domain axis.
     * <p>
     * If the flag value changes, a {@link PlotChangeEvent} is sent to all
     * registered listeners.
     *
     * @param visible the new value of the flag.
     */
    public void setDomainGridlinesVisible(boolean visible) {
        plot_.setDomainGridlinesVisible(visible);
    }

    /**
     * Sets the paint used to draw the grid lines against the range axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the paint.
     */
    public void setRangeGridlinePaint(@NonNull Paint paint) {
        plot_.setRangeGridlinePaint(paint);
    }

    /**
     * Sets the flag that controls whether the zero baseline is
     * displayed for the range axis, and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param visible the flag.
     */
    public void setRangeZeroBaselineVisible(boolean visible) {
        plot_.setRangeZeroBaselineVisible(visible);
    }

    /**
     * Adds a marker for display against the range axis and sends a
     * {@link PlotChangeEvent} to all registered listeners.  Typically a marker
     * will be drawn by the renderer as a line perpendicular to the range axis,
     * however this is entirely up to the renderer.
     *
     * @param marker the marker ({@code null} not permitted).
     * @param layer  the layer (foreground or background) ({@code null}
     *               not permitted).
     */
    public void addRangeMarker(Marker marker, Layer layer) {
        plot_.addRangeMarker(marker, layer);
    }


    /**
     * Sets the flag indicating whether the range crosshair is visible.
     *
     * @param flag the new value of the flag.
     */
    public void setRangeCrosshairVisible(boolean flag) {
        plot_.setRangeCrosshairVisible(flag);
    }

    /**
     * Sets the default positive item label position.
     *
     * @param position the position ({@code null} not permitted).
     */
    public void setDefaultPositiveItemLabelPosition(
            ItemLabelPosition position) {
        renderer0_.setDefaultPositiveItemLabelPosition(position);
    }

    /**
     * Sets the default item label position for negative values and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param position the position ({@code null} not permitted).
     */
    public void setDefaultNegativeItemLabelPosition(
            ItemLabelPosition position) {
        renderer0_.setDefaultNegativeItemLabelPosition(position);
    }
}
