package pdk.chart.fluent;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.Chart;
import pdk.chart.annotations.CategoryAnnotation;
import pdk.chart.api.Layer;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.api.SortOrder;
import pdk.chart.axis.*;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.fluent.prop.CategoryAxisProps;
import pdk.chart.fluent.prop.CategoryBarProps;
import pdk.chart.fluent.prop.CategoryLineProps;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.legend.LegendItemCollection;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.*;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;
import pdk.chart.title.Title;

import java.awt.*;
import java.util.List;

/**
 * XYChart with category domain axis.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 10:10 AM
 */
public class CategoryXYChart extends Chart {

    /**
     * Create a {@link CategoryXYChart}
     *
     * @return {@link CategoryXYChart}.
     */
    public static CategoryXYChart create() {
        return new CategoryXYChart();
    }

    /**
     * Create a {@link CategoryXYChart}
     *
     * @param dataset   {@link CategoryDataset}
     * @param chartType {@link CategoryXYChartType}
     * @return {@link CategoryXYChart} instance.
     */
    public static CategoryXYChart create(CategoryDataset dataset, CategoryXYChartType chartType) {
        CategoryXYChart chart = new CategoryXYChart();
        chart.addDataset(dataset, chartType);
        return chart;
    }

    private final NumberAxis rangeAxis_ = new NumberAxis();
    private CategoryAxis domainAxis_ = new CategoryAxis();
    private final CategoryPlot plot_;

    public CategoryXYChart() {
        super(null, DEFAULT_TITLE_FONT, new CategoryPlot<>(), false);
        plot_ = getCategoryPlot();

        plot_.setDomainAxis(domainAxis_);
        plot_.setRangeAxis(rangeAxis_);

        DEFAULT_THEME.apply(this);
    }

    /**
     * Whether to create and display the legend.
     *
     * @param showLegend true if add legend.
     * @return this
     */
    public CategoryXYChart showLegend(boolean showLegend) {
        if (showLegend) {
            LegendTitle legend = new LegendTitle(this.plot_);
            legend.setMargin(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
            legend.setBackgroundPaint(Color.WHITE);
            legend.setPosition(RectangleEdge.BOTTOM);

            addSubtitle(legend);
        }
        return this;
    }

    /**
     * Sets the fixed legend items for the plot.  Leave this set to
     * {@code null} if you prefer the legend items to be created
     * automatically.
     *
     * @param items the legend items ({@code null} permitted).
     */
    public CategoryXYChart fixedLegendItems(@Nullable LegendItemCollection items) {
        plot_.setFixedLegendItems(items);
        return this;
    }

    /**
     * Sets the chart title.
     * <p>
     * This is a convenience method that ends up calling
     * the {@link #setTitle(TextTitle)} method.  If there is an existing title,
     * its text is updated, otherwise a new title using the default font is
     * added to the chart.  If {@code text} is {@code null} the chart
     * title is set to {@code null}.
     *
     * @param title the title text.
     */
    public CategoryXYChart title(@Nullable String title) {
        setTitle(title);
        return this;
    }

    /**
     * Sets the main title for the chart.
     * <p>
     * If you do not want a title for the chart, set it to {@code null}.
     * <p>
     * If you want more than one title on a chart, use the {@link #addSubtitle(Title)} method.
     *
     * @param title the title ({@code null} permitted).
     * @see #getTitle()
     */
    public CategoryXYChart title(TextTitle title) {
        setTitle(title);
        return this;
    }

    public CategoryXYChart addTitle(Title title) {
        addSubtitle(title);
        return this;
    }

    /**
     * Set the axis labels.
     *
     * @param xName name for domain axis.
     * @param yName name for range axis.
     * @return this.
     */
    public CategoryXYChart axisNames(@Nullable String xName, @Nullable String yName) {
        domainAxis_.setLabel(xName);
        rangeAxis_.setLabel(yName);
        return this;
    }

    /**
     * Return the range axis as {@link NumberAxis} instance.
     *
     * @return {@link NumberAxis}
     */
    public NumberAxis rangeAxisNumber() {
        return rangeAxis_;
    }

    /**
     * Set the domain axis.
     *
     * @param domainAxis {@link CategoryAxis} instance.
     * @return this.
     */
    public CategoryXYChart setDomainAxis(CategoryAxis domainAxis) {
        plot_.setDomainAxis(domainAxis);
        this.domainAxis_ = domainAxis;
        return this;
    }

    public CategoryAxisProps domainAxis() {
        return new CategoryAxisProps(this, domainAxis_);
    }

    /**
     * Sets the location of the range axis and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param location the location ({@code null} not permitted).
     */
    public CategoryXYChart rangeAxisLocation(AxisLocation location) {
        plot_.setRangeAxisLocation(location);
        return this;
    }

    /**
     * Add a new {@link CategoryDataset} to show.
     *
     * @param dataset   {@link CategoryDataset} to show.
     * @param chartType {@link CategoryXYChartType} to render the dataset.
     * @return this.
     */
    public CategoryXYChart dataset(CategoryDataset dataset, CategoryXYChartType chartType) {
        return addDataset(0, dataset, chartType);
    }

    /**
     * Add a new {@link CategoryDataset} to show.
     *
     * @param dataset   {@link CategoryDataset} to show.
     * @param chartType {@link CategoryXYChartType} to render the dataset.
     * @return this.
     */
    public CategoryXYChart addDataset(CategoryDataset dataset, CategoryXYChartType chartType) {
        int datasetCount = plot_.getDatasetCount();
        if (datasetCount == 1 && plot_.getDataset(0) == null) {
            datasetCount = 0;
        }
        return addDataset(datasetCount, dataset, chartType);
    }

    /**
     * Add a new {@link CategoryDataset} to show.
     *
     * @param dataset   {@link CategoryDataset} to show.
     * @param chartType {@link CategoryXYChartType} to render the dataset.
     * @return this.
     */
    public CategoryXYChart addDataset(int index, CategoryDataset dataset, CategoryXYChartType chartType) {
        CategoryDataset exitingDataset = plot_.getDataset(index);
        if (exitingDataset != null) {
            throw new IllegalStateException("Dataset with index " + index + " already exists!");
        }

        CategoryItemRenderer renderer = chartType.getRenderer();
        if (chartType == CategoryXYChartType.BAR) {
            PlotOrientation orientation = plot_.getOrientation();
            if (orientation == PlotOrientation.HORIZONTAL) {
                ItemLabelPosition position1 = new ItemLabelPosition(
                        ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
                renderer.setDefaultPositiveItemLabelPosition(position1);
                ItemLabelPosition position2 = new ItemLabelPosition(
                        ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
                renderer.setDefaultNegativeItemLabelPosition(position2);
            } else if (orientation == PlotOrientation.VERTICAL) {
                ItemLabelPosition position1 = new ItemLabelPosition(
                        ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
                renderer.setDefaultPositiveItemLabelPosition(position1);
                ItemLabelPosition position2 = new ItemLabelPosition(
                        ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
                renderer.setDefaultNegativeItemLabelPosition(position2);
            }
        } else if (chartType == CategoryXYChartType.BAR_WATERFALL) {
            PlotOrientation orientation = plot_.getOrientation();
            if (orientation == PlotOrientation.HORIZONTAL) {
                ItemLabelPosition position = new ItemLabelPosition(
                        ItemLabelAnchor.CENTER, TextAnchor.CENTER,
                        TextAnchor.CENTER, Math.PI / 2.0);
                renderer.setDefaultPositiveItemLabelPosition(position);
                renderer.setDefaultNegativeItemLabelPosition(position);
            } else if (orientation == PlotOrientation.VERTICAL) {
                ItemLabelPosition position = new ItemLabelPosition(
                        ItemLabelAnchor.CENTER, TextAnchor.CENTER,
                        TextAnchor.CENTER, 0.0);
                renderer.setDefaultPositiveItemLabelPosition(position);
                renderer.setDefaultNegativeItemLabelPosition(position);
            }
            plot_.clearRangeMarkers();
            Marker baseline = new ValueMarker(0.0);
            baseline.setPaint(Color.BLACK);
            plot_.addRangeMarker(baseline, Layer.FOREGROUND);
        }

        plot_.setDataset(index, dataset);
        plot_.setRenderer(index, renderer);
        return this;
    }

    /**
     * Add a new {@link CategoryDataset} to show.
     *
     * @param dataset  {@link CategoryDataset} to show.
     * @param renderer {@link CategoryItemRenderer} to render the dataset.
     * @return this.
     */
    public CategoryXYChart addDataset(CategoryDataset dataset, CategoryItemRenderer renderer) {
        int datasetCount = plot_.getDatasetCount();
        if (datasetCount == 1 && plot_.getDataset(0) == null) {
            datasetCount = 0;
        }
        return addDataset(datasetCount, dataset, renderer);
    }

    /**
     * Add a new {@link CategoryDataset} to show.
     *
     * @param dataset  {@link CategoryDataset} to show.
     * @param renderer {@link CategoryItemRenderer} to render the dataset.
     * @return this.
     */
    public CategoryXYChart addDataset(int index, CategoryDataset dataset, CategoryItemRenderer renderer) {
        CategoryDataset exitingDataset = plot_.getDataset(index);
        if (exitingDataset != null) {
            throw new IllegalStateException("Dataset with index " + index + " already exists!");
        }

        plot_.setDataset(index, dataset);
        plot_.setRenderer(index, renderer);
        return this;
    }

    /**
     * Set properties for the {@link CategoryXYChartType#BAR}.
     * <p>
     * Throw an {@link IllegalStateException} if the renderer type of the specified dataset is not BAR.
     *
     * @param index index of the dataset.
     * @return {@link CategoryBarProps}.
     */
    public CategoryBarProps barProps(int index) {
        CategoryItemRenderer renderer = plot_.getRenderer(index);
        if (renderer instanceof BarRenderer barRenderer) {
            return new CategoryBarProps(this, barRenderer);
        }
        throw new IllegalStateException("The specified chart type is not bar.");
    }

    /**
     * Set properties for the {@link CategoryXYChartType#LINE}.
     * <p>
     * Throw an {@link IllegalStateException} if the renderer type of the specified dataset is not LINE.
     *
     * @param index index of the dataset.
     * @return {@link CategoryBarProps}.
     */
    public CategoryLineProps lineRenderer(int index) {
        CategoryItemRenderer renderer = plot_.getRenderer(index);
        if (renderer instanceof LineAndShapeRenderer lineAndShapeRenderer) {
            return new CategoryLineProps(this, lineAndShapeRenderer);
        }
        throw new IllegalStateException("The specified chart type is not line.");
    }

    /**
     * Set the renderer for a given dataset.
     *
     * @param index    dataset index.
     * @param renderer {@link CategoryItemRenderer}.
     * @return this.
     */
    public CategoryXYChart setRenderer(int index, CategoryItemRenderer renderer) {
        plot_.setRenderer(index, renderer);
        return this;
    }

    /**
     * Set the chart orientation.
     *
     * @param orientation {@link PlotOrientation}
     * @return this
     */
    public CategoryXYChart orientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
        return this;
    }


    /**
     * Sets the paint used to fill the chart background.
     *
     * @param paint the paint.
     */
    public CategoryXYChart backgroundPaint(@Nullable Paint paint) {
        setBackgroundPaint(paint);
        return this;
    }

    /**
     * Sets the background color of the plot area.
     *
     * @param paint the paint.
     */
    public CategoryXYChart plotBackgroundPaint(@Nullable Paint paint) {
        plot_.setBackgroundPaint(paint);
        return this;
    }

    /**
     * Sets the insets for the plot.
     *
     * @param insets the new insets ({@code null} not permitted).
     */
    public CategoryXYChart plotInsets(@NonNull RectangleInsets insets) {
        plot_.setInsets(insets);
        return this;
    }

    /**
     * Sets the alpha-transparency for the plot.
     *
     * @param alpha the new alpha transparency.
     */
    public CategoryXYChart foregroundAlpha(float alpha) {
        plot_.setForegroundAlpha(alpha);
        return this;
    }

    /**
     * Sets the axis offsets (gap between the data area and the axes).
     *
     * @param offset the offset ({@code null} not permitted).
     */
    public CategoryXYChart axisOffset(RectangleInsets offset) {
        plot_.setAxisOffset(offset);
        return this;
    }

    /**
     * Sets the paint used to draw the grid-lines (if any) against the domain
     * axis.
     *
     * @param paint the paint.
     */
    public CategoryXYChart domainGridlinePaint(@NonNull Paint paint) {
        plot_.setDomainGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the paint used to draw the grid lines against the range axis.
     *
     * @param paint the paint.
     */
    public CategoryXYChart rangeGridlinePaint(@NonNull Paint paint) {
        plot_.setRangeGridlinePaint(paint);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the domain axis.
     *
     * @param showGridlines true if show grid lines
     * @return this
     */
    public CategoryXYChart domainGridlinesVisible(boolean showGridlines) {
        plot_.setDomainGridlinesVisible(showGridlines);
        return this;
    }

    /**
     * Sets the position used for the domain gridlines.
     *
     * @param position the position.
     */
    public CategoryXYChart domainGridlinePosition(@NonNull CategoryAnchor position) {
        plot_.setDomainGridlinePosition(position);
        return this;
    }

    /**
     * Sets the stroke used to draw grid-lines against the domain axis.
     *
     * @param stroke the stroke.
     */
    public CategoryXYChart domainGridlineStroke(@NonNull Stroke stroke) {
        plot_.setDomainGridlineStroke(stroke);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the range axis.
     *
     * @param showGridlines true if show grid lines
     * @return this
     */
    public CategoryXYChart rangeGridlinesVisible(boolean showGridlines) {
        plot_.setRangeGridlinesVisible(showGridlines);
        return this;
    }

    /**
     * Sets the flag that controls whether the zero baseline is
     * displayed for the range axis, and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param visible the flag.
     */
    public CategoryXYChart rangeZeroBaselineVisible(boolean visible) {
        plot_.setRangeZeroBaselineVisible(visible);
        return this;
    }

    /**
     * Whether the range crosshair is visible.
     *
     * @param flag the new value of the flag.
     */
    public CategoryXYChart rangeCrosshairVisible(boolean flag) {
        plot_.setRangeCrosshairVisible(flag);
        return this;
    }

    /**
     * Sets the paint used to draw the range crosshair (if visible).
     *
     * @param paint the paint.
     */
    public CategoryXYChart rangeCrosshairPaint(@NonNull Paint paint) {
        plot_.setRangeCrosshairPaint(paint);
        return this;
    }

    /**
     * Sets the message that is displayed when the dataset is empty or
     * {@code null}, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param message the message.
     */
    public CategoryXYChart setNoDataMessage(@Nullable String message) {
        plot_.setNoDataMessage(message);
        return this;
    }

    /**
     * Sets the paint used to draw the outline of the plot area.
     * <p>
     * If set this attribute to {@code null}, no outline will be drawn.
     *
     * @param paint the paint.
     */
    public CategoryXYChart plotOutlinePaint(@Nullable Paint paint) {
        plot_.setOutlinePaint(paint);
        return this;
    }

    /**
     * Sets the stroke used to draw the grid-lines against the range axis.
     *
     * @param stroke the stroke.
     */
    public CategoryXYChart rangeGridlineStroke(@NonNull Stroke stroke) {
        plot_.setRangeGridlineStroke(stroke);
        return this;
    }


    /**
     * Enables or disables panning of the plot along the range axes.
     *
     * @param pannable the new flag value.
     */
    public CategoryXYChart rangePannable(boolean pannable) {
        plot_.setRangePannable(pannable);
        return this;
    }

    /**
     * Adds a marker for display against the range axis.
     * <p>
     * Typically a marker will be drawn by the renderer as a line perpendicular to the range axis,
     * however this is entirely up to the renderer.
     *
     * @param marker the marker.
     * @param layer  the layer (foreground or background).
     */
    public CategoryXYChart addRangeMarker(@NonNull Marker marker, @NonNull Layer layer) {
        plot_.addRangeMarker(marker, layer);
        return this;
    }

    /**
     * Clears all the range markers for the plot.
     */
    public CategoryXYChart clearRangeMarkers() {
        plot_.clearRangeMarkers();
        return this;
    }

    /**
     * Adds a marker for display against the domain axis and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * <p>
     * Typically a marker will be drawn by the renderer as a line perpendicular to the domain
     * axis, however this is entirely up to the renderer.
     *
     * @param marker the marker.
     * @param layer  the layer (foreground or background).
     */
    public CategoryXYChart addDomainMarker(@NonNull CategoryMarker marker, @NonNull Layer layer) {
        plot_.addDomainMarker(0, marker, layer);
        return this;
    }

    /**
     * Adds an annotation to the plot.
     *
     * @param annotation the annotation.
     */
    public CategoryXYChart addAnnotation(@NonNull CategoryAnnotation annotation) {
        plot_.addAnnotation(annotation, false);
        return this;
    }

    /**
     * Sets a range axis and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param index the axis index.
     * @param axis  the axis.
     */
    public CategoryXYChart addRangeAxis(int index, ValueAxis axis) {
        plot_.setRangeAxis(index, axis);
        return this;
    }

    /**
     * Sets a range axis and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param index the axis index.
     * @param axis  the axis.
     */
    public CategoryXYChart addDomainAxis(int index, CategoryAxis axis) {
        plot_.setDomainAxis(index, axis);
        return this;
    }

    /**
     * Maps the specified dataset to the axes in the list.  Note that the
     * conversion of data values into Java2D space is always performed using
     * the first axis in the list.
     *
     * @param index       the dataset index (zero-based).
     * @param axisIndices the axis indices ({@code null} permitted).
     */
    public CategoryXYChart mapDatasetToDomainAxes(int index, List<Integer> axisIndices) {
        plot_.mapDatasetToDomainAxes(index, axisIndices);
        return this;
    }


    /**
     * Maps the specified dataset to the axes in the list.  Note that the
     * conversion of data values into Java2D space is always performed using
     * the first axis in the list.
     *
     * @param index       the dataset index (zero-based).
     * @param axisIndices the axis indices ({@code null} permitted).
     */
    public CategoryXYChart mapDatasetToRangeAxes(int index, List<Integer> axisIndices) {
        plot_.mapDatasetToRangeAxes(index, axisIndices);
        return this;
    }

    /**
     * Sets the row order in which the items in each dataset should be
     * rendered.
     * <p>
     * Note that this affects the order in which items are drawn,
     * NOT their position in the chart.
     *
     * @param order the order ({@code null} not permitted).
     */
    public CategoryXYChart rowRenderingOrder(SortOrder order) {
        plot_.setRowRenderingOrder(order);
        return this;
    }
}
