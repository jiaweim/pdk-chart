package pdk.chart;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.annotations.CategoryAnnotation;
import pdk.chart.api.Layer;
import pdk.chart.api.RectangleInsets;
import pdk.chart.api.SortOrder;
import pdk.chart.axis.*;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.event.ChartChangeEvent;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.CategoryItemLabelGenerator;
import pdk.chart.labels.CategoryToolTipGenerator;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.legend.LegendItemCollection;
import pdk.chart.plot.*;
import pdk.chart.renderer.AreaRendererEndType;
import pdk.chart.renderer.category.*;
import pdk.chart.text.TextAnchor;

import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 1:42 PM
 */
public class CategoryChart extends Chart {

    public enum Type {
        LINE,
        STAT_LINE,
        AREA,
        STACKED_AREA,
        BAR,
        STACKED_BAR,
        INTERVAL_BAR,
        LAYERED_BAR,
        STAT_BAR,
        WATERFALL_BAR,
        BOX,
        GANTT;

        public CategoryItemRenderer getRenderer() {
            switch (this) {
                case LINE -> {
                    return new LineAndShapeRenderer(true, false);
                }
                case STAT_LINE -> {
                    return new StatisticalLineAndShapeRenderer(true, false);
                }
                case AREA -> {
                    AreaRenderer renderer = new AreaRenderer();
                    renderer.setEndType(AreaRendererEndType.LEVEL);
                    return renderer;
                }
                case STACKED_AREA -> {
                    return new StackedAreaRenderer();
                }
                case BAR -> {
                    BarRenderer barRenderer = new BarRenderer();
                    barRenderer.setShadowVisible(false);
                    return barRenderer;
                }
                case INTERVAL_BAR -> {
                    return new IntervalBarRenderer();
                }
                case LAYERED_BAR -> {
                    return new LayeredBarRenderer();
                }
                case STACKED_BAR -> {
                    return new StackedBarRenderer();
                }
                case STAT_BAR -> {
                    return new StatisticalBarRenderer();
                }
                case WATERFALL_BAR -> {
                    return new WaterfallBarRenderer();
                }
                case BOX -> {
                    return new BoxAndWhiskerRenderer();
                }
                case GANTT -> {
                    return new GanttRenderer();
                }
                default -> {
                    throw new IllegalStateException("Unexpected value: " + this);
                }
            }
        }
    }

    protected final CategoryPlot plot_;
    protected CategoryItemRenderer renderer0_;

    /**
     * Initial the default renderer.
     */
    protected void initRenderer() {
        renderer0_ = new LineAndShapeRenderer(true, false);
    }

    public CategoryChart(String title, boolean createLegend) {
        super(title, DEFAULT_TITLE_FONT, new CategoryPlot<>(), createLegend);
        this.plot_ = getCategoryPlot();
        initRenderer();
    }


    /**
     * Sets the paint used to fill the chart background and sends a
     * {@link ChartChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} permitted).
     * @see #getBackgroundPaint()
     */
    public CategoryChart withBackgroundPaint(Paint paint) {
        setBackgroundPaint(paint);
        return this;
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
     * Sets the row order in which the items in each dataset should be
     * rendered and sends a {@link PlotChangeEvent} to all registered
     * listeners.  Note that this affects the order in which items are drawn,
     * NOT their position in the chart.
     *
     * @param order the order ({@code null} not permitted).
     */
    public CategoryChart withRowRenderingOrder(SortOrder order) {
        plot_.setRowRenderingOrder(order);
        return this;
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
     * Sets a domain axis and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param index the axis index.
     * @param axis  the axis ({@code null} permitted).
     */
    public void setDomainAxis(int index, CategoryAxis axis) {
        plot_.setDomainAxis(index, axis);
    }

    /**
     * Maps the specified dataset to the axes in the list.  Note that the
     * conversion of data values into Java2D space is always performed using
     * the first axis in the list.
     *
     * @param index       the dataset index (zero-based).
     * @param axisIndices the axis indices ({@code null} permitted).
     */
    public void mapDatasetToDomainAxes(int index, List<Integer> axisIndices) {
        plot_.mapDatasetToDomainAxes(index, axisIndices);
    }

    /**
     * Maps the specified dataset to the axes in the list.  Note that the
     * conversion of data values into Java2D space is always performed using
     * the first axis in the list.
     *
     * @param index       the dataset index (zero-based).
     * @param axisIndices the axis indices ({@code null} permitted).
     */
    public void mapDatasetToRangeAxes(int index, List<Integer> axisIndices) {
        plot_.mapDatasetToRangeAxes(index, axisIndices);
    }

    /**
     * Sets the domain axis for the plot and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param axis the axis ({@code null} permitted).
     * @see #getDomainAxis()
     */
    public CategoryChart withDomainAxis(CategoryAxis axis) {
        plot_.setDomainAxis(axis);
        return this;
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
     * Sets the flag that enables or disables panning of the plot along
     * the range axes.
     *
     * @param pannable the new flag value.
     */
    public CategoryChart withRangePannable(boolean pannable) {
        plot_.setRangePannable(pannable);
        return this;
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
     * Sets the orientation for the plot.
     *
     * @param orientation the orientation ({@code null} not permitted).
     */
    public CategoryChart withOrientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
        return this;
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
     * Add a new annotation to the plot.
     *
     * @param text           annotation text.
     * @param category       category to annotate
     * @param value          value to annotate
     * @param font           {@link Font} for annotation
     * @param anchor         {@link TextAnchor}
     * @param categoryAnchor {@link CategoryAnchor}
     */
    public void addAnnotation(String text, Comparable category, double value,
            Font font, TextAnchor anchor, CategoryAnchor categoryAnchor) {
        plot_.addAnnotation(text, category, value, font, anchor, categoryAnchor);
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

    public void setDataset(int index, CategoryDataset dataset, Type type) {
        Objects.requireNonNull(type);
        plot_.setDataset(index, dataset);
        CategoryItemRenderer renderer = type.getRenderer();
        plot_.setRenderer(index, renderer);
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
        plot_.setRenderer(index, renderer);
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
     * Sets the flag that controls whether grid-lines are drawn against
     * the domain axis. That is, whether to draw grid lines perpendicular to the domain axis.
     * <p>
     * If the flag value changes, a {@link PlotChangeEvent} is sent to all
     * registered listeners.
     *
     * @param visible the new value of the flag.
     */
    public CategoryChart withDomainGridlinesVisible(boolean visible) {
        plot_.setDomainGridlinesVisible(visible);
        return this;
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
     * Sets the paint used to draw the grid lines against the range axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the paint.
     */
    public CategoryChart withRangeGridlinePaint(@NonNull Paint paint) {
        plot_.setRangeGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the stroke used to draw the grid-lines against the range axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke the stroke.
     */
    public void setRangeGridlineStroke(@NonNull Stroke stroke) {
        plot_.setRangeGridlineStroke(stroke);
    }

    /**
     * Sets the stroke used to draw the grid-lines against the range axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke the stroke.
     */
    public CategoryChart withRangeGridlineStroke(@NonNull Stroke stroke) {
        plot_.setRangeGridlineStroke(stroke);
        return this;
    }

    /**
     * Sets the flag that controls whether grid-lines are drawn against
     * the range axis.  If the flag changes value, a {@link PlotChangeEvent} is
     * sent to all registered listeners.
     *
     * @param visible the new value of the flag.
     */
    public void setRangeGridlinesVisible(boolean visible) {
        plot_.setRangeGridlinesVisible(visible);
    }

    /**
     * Sets the flag that controls whether grid-lines are drawn against
     * the range axis.  If the flag changes value, a {@link PlotChangeEvent} is
     * sent to all registered listeners.
     *
     * @param visible the new value of the flag.
     */
    public CategoryChart withRangeGridlinesVisible(boolean visible) {
        plot_.setRangeGridlinesVisible(visible);
        return this;
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
     * Sets the flag that controls whether the zero baseline is
     * displayed for the range axis, and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param visible the flag.
     */
    public CategoryChart withRangeZeroBaselineVisible(boolean visible) {
        plot_.setRangeZeroBaselineVisible(visible);
        return this;
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
     * Clears all the range markers for the plot and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     */
    public void clearRangeMarkers() {
        plot_.clearRangeMarkers();
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
     * Sets the flag indicating whether the range crosshair is visible.
     *
     * @param flag the new value of the flag.
     */
    public CategoryChart withRangeCrosshairVisible(boolean flag) {
        plot_.setRangeCrosshairVisible(flag);
        return this;
    }

    /**
     * Sets the paint used to draw the range crosshair (if visible) and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public void setRangeCrosshairPaint(Paint paint) {
        plot_.setRangeCrosshairPaint(paint);
    }

    /**
     * Sets the paint used to draw the range crosshair (if visible) and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public CategoryChart withRangeCrosshairPaint(Paint paint) {
        plot_.setRangeCrosshairPaint(paint);
        return this;
    }

    /**
     * Sets the position used for the domain gridlines and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param position the position ({@code null} not permitted).
     */
    public void setDomainGridlinePosition(CategoryAnchor position) {
        plot_.setDomainGridlinePosition(position);
    }

    /**
     * Sets the stroke used to draw grid-lines against the domain axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke the stroke ({@code null} not permitted).
     */
    public void setDomainGridlineStroke(Stroke stroke) {
        plot_.setDomainGridlineStroke(stroke);
    }

    /**
     * Sets the axis offsets (gap between the data area and the axes).
     *
     * @param offset the offset ({@code null} not permitted).
     */
    public void setAxisOffset(RectangleInsets offset) {
        plot_.setAxisOffset(offset);
    }

    /**
     * Sets the axis offsets (gap between the data area and the axes).
     *
     * @param offset the offset ({@code null} not permitted).
     */
    public CategoryChart withAxisOffset(RectangleInsets offset) {
        plot_.setAxisOffset(offset);
        return this;
    }

    /**
     * Sets the rendering order.
     * <p>
     * By default, the plot renders the primary dataset
     * last (so that the primary dataset overlays the secondary datasets).  You
     * can reverse this if you want to.
     *
     * @param order the rendering order ({@code null} not permitted).
     */
    public void setDatasetRenderingOrder(DatasetRenderingOrder order) {
        plot_.setDatasetRenderingOrder(order);
    }

    /**
     * Sets the location of the range axis and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param location the location ({@code null} not permitted).
     * @see #setDomainAxisLocation(AxisLocation)
     */
    public void setRangeAxisLocation(AxisLocation location) {
        plot_.setRangeAxisLocation(location);
    }

    /**
     * Sets the location of the range axis and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param location the location ({@code null} not permitted).
     * @see #setDomainAxisLocation(AxisLocation)
     */
    public CategoryChart withRangeAxisLocation(AxisLocation location) {
        plot_.setRangeAxisLocation(location);
        return this;
    }

    /**
     * Sets the background color of the plot area and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} permitted).
     * @see #getBackgroundPaint()
     */
    public CategoryChart withPlotBackgroundPaint(Paint paint) {
        setPlotBackgroundPaint(paint);
        return this;
    }

    /**
     * Sets the insets for the plot.
     * <p>
     * Used to control the padding between the chart's plot area and its outer border,
     * i.e., to add or remove blank space around the plot area.
     *
     * @param insets the new insets ({@code null} not permitted).
     */
    public CategoryChart withPlotInsets(RectangleInsets insets) {
        plot_.setInsets(insets);
        return this;
    }

    /**
     * Sets the paint used to draw the outline of the plot area and sends a
     * {@link PlotChangeEvent} to all registered listeners.  If you set this
     * attribute to {@code null}, no outline will be drawn.
     *
     * @param paint the paint ({@code null} permitted).
     */
    public CategoryChart withPlotOutlinePaint(Paint paint) {
        plot_.setOutlinePaint(paint);
        return this;
    }

    /**
     * Sets the message that is displayed when the dataset is empty or
     * {@code null}, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param message the message ({@code null} permitted).
     */
    public CategoryChart withNoDataMessage(String message) {
        setNoDataMessage(message);
        return this;
    }

    /**
     * Sets the alpha-transparency for the plot and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param alpha the new alpha transparency.
     */
    public CategoryChart withPlotForegroundAlpha(float alpha) {
        plot_.setForegroundAlpha(alpha);
        return this;
    }

    /**
     * Sets the paint used to draw the outline of the plot area and sends a
     * {@link PlotChangeEvent} to all registered listeners.  If you set this
     * attribute to {@code null}, no outline will be drawn.
     *
     * @param paint the paint ({@code null} permitted).
     * @see #getOutlinePaint()
     */
    public void setPlotOutlinePaint(Paint paint) {
        plot_.setOutlinePaint(paint);
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

    /**
     * Set the paint used to color data items as they are drawn.
     *
     * @param row    the row (or series) index (zero-based).
     * @param column the column (or category) index (zero-based).
     * @param paint  the paint.
     */
    public void setItemPaint(int row, int column, Paint paint) {
        renderer0_.setItemPaint(row, column, paint);
    }

    /**
     * Sets the default tool tip generator and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param generator the generator.
     */
    public void setDefaultToolTipGenerator(@Nullable CategoryToolTipGenerator generator) {
        renderer0_.setDefaultToolTipGenerator(generator);
    }

    /**
     * Sets the default item label font and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param font the font.
     */
    public void setDefaultItemLabelFont(@NonNull Font font) {
        renderer0_.setDefaultItemLabelFont(font);
    }

}
