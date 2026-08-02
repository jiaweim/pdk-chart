package pdk.chart;

import org.jspecify.annotations.NonNull;
import pdk.chart.annotations.XYAnnotation;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.labels.XYItemLabelGenerator;
import pdk.chart.labels.XYToolTipGenerator;
import pdk.chart.plot.Plot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.*;

import java.awt.*;

/**
 * Base class for charts that use an {@link XYPlot}.
 * <p>
 * Provides common methods for configuring the plot, axes, gridlines,
 * renderer, tooltips, and crosshairs. Subclasses are expected to
 * initialise the default renderer via {@link #initRenderer()}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 8:19 AM
 */
public class XYChart extends Chart {

    /**
     * Enumeration of supported chart types for which a default
     * {@link XYItemRenderer} can be created automatically.
     */
    public enum ChartType {
        /**
         * Line chart
         */
        LINE,
        /**
         * Area chart.
         */
        AREA,
        /**
         * Scatter chart.
         */
        SCATTER,
        /**
         * Bubble chart.
         */
        BUBBLE,
        /**
         * Bar chart
         */
        BAR;

        /**
         * Returns a renderer instance for the chart type.
         *
         * @return a new {@link XYItemRenderer} for this type
         * @throws UnsupportedOperationException if the type does not yet have
         *                                       a default renderer implementation
         */
        XYItemRenderer getRenderer() {
            switch (this) {
                case LINE -> {
                    return new XYLineAndShapeRenderer(true, false);
                }
                case AREA -> {
                    return new XYAreaRenderer(XYAreaRenderer.AREA);
                }
                case SCATTER -> {
                    return new XYLineAndShapeRenderer(false, true);
                }
                case BUBBLE -> {
                    return new XYBubbleRenderer(XYBubbleRenderer.SCALE_ON_RANGE_AXIS);
                }
                case BAR -> {
                    XYBarRenderer renderer = new XYBarRenderer();
                    renderer.setShadowVisible(false);
                    return renderer;
                }

                default -> throw new UnsupportedOperationException("Not supported yet " + this);
            }
        }
    }

    /**
     * The underlying XYPlot.
     */
    protected final XYPlot plot_;
    /**
     * The primary renderer (often shared by subclasses).
     */
    protected XYItemRenderer renderer0_;

    /**
     * Creates a new XY chart that uses an externally supplied plot.
     *
     * @param title        the chart title ({@code null} permitted)
     * @param plot         the plot (must be an {@link XYPlot})
     * @param createLegend whether to create a legend
     */
    public XYChart(String title, Plot plot, boolean createLegend) {
        super(title, DEFAULT_TITLE_FONT, plot, createLegend);
        plot_ = getXYPlot();
        initRenderer();
    }

    /**
     * Creates a new XY chart with the default title font and legend flag.
     * The plot is a new empty {@link XYPlot}.
     *
     * @param title        the chart title ({@code null} permitted)
     * @param createLegend whether to create a legend
     */
    public XYChart(String title, boolean createLegend) {
        this(title, new XYPlot<>(), createLegend);
    }

    /**
     * Initialises the default renderer.
     * <p>
     * Subclasses must override this to set up {@link #renderer0_}
     * (and any other renderer references) appropriately.
     */
    protected void initRenderer() {
        renderer0_ = new XYLineAndShapeRenderer();
    }

    /**
     * Sets the default tool tip generator.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public void setDefaultToolTipGenerator(XYToolTipGenerator generator) {
        renderer0_.setDefaultToolTipGenerator(generator);
    }

    /**
     * Configures whether the chart displays tool tips.
     * When enabled and no generator is set, a default
     * {@link StandardXYToolTipGenerator} is installed.
     * When disabled, the current generator is removed.
     *
     * @param showToolTips {@code true} to show tool tips
     */
    public void setShowToolTips(boolean showToolTips) {
        XYToolTipGenerator generator = renderer0_.getDefaultToolTipGenerator();
        if (showToolTips && generator == null) {
            generator = new StandardXYToolTipGenerator();
            renderer0_.setDefaultToolTipGenerator(generator);
        } else if (generator != null) {
            renderer0_.setDefaultToolTipGenerator(null);
        }
    }

    /**
     * Appends a dataset to the plot using a renderer created from the
     * given {@link ChartType}.
     *
     * @param dataset   the dataset to add
     * @param chartType the chart type specifying which renderer to use
     */
    public void addDataset(XYDataset dataset, ChartType chartType) {
        int datasetCount = plot_.getDatasetCount();
        plot_.setDataset(datasetCount, dataset);
        plot_.setRenderer(datasetCount, chartType.getRenderer());
    }

    /**
     * Sets a dataset for the plot and sends a change event to all registered
     * listeners.
     *
     * @param index   the dataset index (must be &gt;= 0).
     * @param dataset the dataset ({@code null} permitted).
     */
    public void setDataset(int index, XYDataset dataset) {
        plot_.setDataset(index, dataset);
    }

    /**
     * Maps a dataset to a particular range axis.  All data will be plotted
     * against axis zero by default, no mapping is required for this case.
     *
     * @param index     the dataset index (zero-based).
     * @param axisIndex the axis index.
     */
    public void mapDatasetToRangeAxis(int index, int axisIndex) {
        plot_.mapDatasetToRangeAxis(index, axisIndex);
    }

    /**
     * Appends a dataset to the plot with the specified renderer.
     *
     * @param dataset  the dataset to add
     * @param renderer the renderer to use for the dataset
     */
    public void addDataset(XYDataset dataset, XYItemRenderer renderer) {
        int datasetCount = plot_.getDatasetCount();
        plot_.setDataset(datasetCount, dataset);
        plot_.setRenderer(datasetCount, renderer);
    }

    /**
     * Sets the orientation for the plot.
     *
     * @param orientation the orientation ({@code null} not allowed).
     */
    public void setOrientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
    }

    /**
     * Enables or disables panning of the plot along the domain axes.
     *
     * @param pannable the new flag value.
     */
    public void setDomainPannable(boolean pannable) {
        plot_.setDomainPannable(pannable);
    }

    /**
     * Enables or disables panning of the plot along the range axis/axes.
     *
     * @param pannable the new flag value.
     */
    public void setRangePannable(boolean pannable) {
        plot_.setRangePannable(pannable);
    }

    /**
     * Simultaneously sets the domain and range pannable flags.
     *
     * @param domainPannable the new flag for domain panning
     * @param rangePannable  the new flag for range panning
     */
    public void setPannable(boolean domainPannable, boolean rangePannable) {
        plot_.setDomainPannable(domainPannable);
        plot_.setRangePannable(rangePannable);
    }

    /**
     * Sets the lower and upper margin for the domain axis.
     * Margins are expressed as a fraction of the axis range and
     * are applied only when the axis range is auto‑calculated.
     *
     * @param lowerMargin the lower margin (e.g. 0.05 = 5%)
     * @param upperMargin the upper margin (e.g. 0.05 = 5%)
     */
    public void setDomainAxisMargin(double lowerMargin, double upperMargin) {
        ValueAxis domainAxis = plot_.getDomainAxis();
        if (domainAxis != null) {
            domainAxis.setLowerMargin(lowerMargin);
            domainAxis.setUpperMargin(upperMargin);
        }
    }

    /**
     * Sets the lower and upper margin for the range axis.
     * Margins are expressed as a fraction of the axis range and
     * are applied only when the axis range is auto‑calculated.
     *
     * @param lowerMargin the lower margin (e.g. 0.05 = 5%)
     * @param upperMargin the upper margin (e.g. 0.05 = 5%)
     */
    public void setRangeAxisMargin(double lowerMargin, double upperMargin) {
        ValueAxis rangeAxis = plot_.getRangeAxis();
        if (rangeAxis != null) {
            rangeAxis.setLowerMargin(lowerMargin);
            rangeAxis.setUpperMargin(upperMargin);
        }
    }

    /**
     * Sets the label for the domain (X) axis.
     *
     * @param label the label text ({@code null} permitted)
     */
    public void setDomainAxisLabel(String label) {
        ValueAxis xAxis = plot_.getDomainAxis();
        if (xAxis != null) {
            xAxis.setLabel(label);
        }
    }

    /**
     * Sets the label for the range (Y) axis.
     *
     * @param label the label text ({@code null} permitted)
     */
    public void setRangeAxisLabel(String label) {
        ValueAxis yAxis = plot_.getRangeAxis();
        if (yAxis != null) {
            yAxis.setLabel(label);
        }
    }

    /**
     * Sets the lower and upper bounds of the domain (X) axis.
     *
     * @param lower the lower bound
     * @param upper the upper bound
     */
    public void setDomainAxisRange(double lower, double upper) {
        ValueAxis xAxis = plot_.getDomainAxis();
        if (xAxis != null) {
            xAxis.setRange(lower, upper);
        }
    }

    /**
     * Sets the lower and upper bounds of the range (Y) axis.
     *
     * @param lower the lower bound
     * @param upper the upper bound
     */
    public void setRangeAxisRange(double lower, double upper) {
        ValueAxis rangeAxis = plot_.getRangeAxis();
        if (rangeAxis != null) {
            rangeAxis.setRange(lower, upper);
        }
    }

    /**
     * Sets the paint for the grid lines plotted against the domain axis.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public void setDomainGridlinePaint(Paint paint) {
        plot_.setDomainGridlinePaint(paint);
    }

    /**
     * Sets the paint for the grid lines plotted against the range axis.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public void setRangeGridlinePaint(Paint paint) {
        plot_.setRangeGridlinePaint(paint);
    }

    /**
     * Controls whether the domain grid-lines are visible.
     *
     * @param visible the new value of the flag.
     */
    public void setDomainGridlinesVisible(boolean visible) {
        plot_.setDomainGridlinesVisible(visible);
    }

    /**
     * Sets whether the range axis grid lines are visible.
     *
     * @param visible the new value of the flag.
     */
    public void setRangeGridlinesVisible(boolean visible) {
        plot_.setRangeGridlinesVisible(visible);
    }

    /**
     * Sets whether the domain minor grid-lines are visible.
     *
     * @param visible the new value of the flag.
     */
    public void setDomainMinorGridlinesVisible(boolean visible) {
        plot_.setDomainMinorGridlinesVisible(visible);
    }

    /**
     * Sets whether the range axis minor grid lines are visible.
     *
     * @param visible the new value of the flag.
     */
    public void setRangeMinorGridlinesVisible(boolean visible) {
        plot_.setRangeMinorGridlinesVisible(visible);
    }

    /**
     * Returns the domain (X) axis.
     *
     * @return the domain axis, or {@code null} if none is set
     */
    public ValueAxis getDomainAxis() {
        return plot_.getDomainAxis();
    }

    /**
     * Returns the range (Y) axis.
     *
     * @return the range axis, or {@code null} if none is set
     */
    public ValueAxis getRangeAxis() {
        return plot_.getRangeAxis();
    }

    /**
     * Returns the domain axis as a {@link NumberAxis}, if applicable.
     *
     * @return the domain axis cast to {@link NumberAxis}, or {@code null}
     * if the axis is not a number axis
     */
    public NumberAxis getDomainAxisAsNumber() {
        ValueAxis domainAxis = plot_.getDomainAxis();
        if (domainAxis instanceof NumberAxis nAxis) {
            return nAxis;
        }
        return null;
    }

    /**
     * Returns the domain axis as a {@link DateAxis}, if applicable.
     *
     * @return the domain axis cast to {@link DateAxis}, or {@code null}
     * if the axis is not a date axis
     */
    public DateAxis getDomainAxisAsDate() {
        ValueAxis domainAxis = plot_.getDomainAxis();
        if (domainAxis instanceof DateAxis dateAxis) {
            return dateAxis;
        }
        return null;
    }

    /**
     * Returns the range axis as a {@link NumberAxis}, if applicable.
     *
     * @return the range axis cast to {@link NumberAxis}, or {@code null}
     * if the axis is not a number axis
     */
    public NumberAxis getRangeAxisAsNumber() {
        ValueAxis rangeAxis = plot_.getRangeAxis();
        if (rangeAxis instanceof NumberAxis nAxis) {
            return nAxis;
        }
        return null;
    }

    /**
     * Returns the range axis as a {@link DateAxis}, if applicable.
     *
     * @return the range axis cast to {@link DateAxis}, or {@code null}
     * if the axis is not a date axis
     */
    public DateAxis getRangeAxisAsDate() {
        ValueAxis rangeAxis = plot_.getRangeAxis();
        if (rangeAxis instanceof DateAxis dateAxis) {
            return dateAxis;
        }
        return null;
    }

    /**
     * Sets the labels for both axes in one call.
     *
     * @param xLabel the label for the domain (X) axis
     * @param yLabel the label for the range (Y) axis
     */
    public void setAxisLabels(String xLabel, String yLabel) {
        ValueAxis xAxis = getDomainAxis();
        if (xAxis != null) {
            xAxis.setLabel(xLabel);
        }
        ValueAxis yAxis = getRangeAxis();
        if (yAxis != null) {
            yAxis.setLabel(yLabel);
        }
    }

    /**
     * Adds an annotation to the plot.
     *
     * @param annotation the annotation ({@code null} not permitted).
     */
    public void addAnnotation(XYAnnotation annotation) {
        plot_.addAnnotation(annotation);
    }

    /**
     * Controls whether the zero baseline is drawn for the domain axis.
     *
     * @param visible {@code true} to make the baseline visible
     */
    public void setDomainZeroBaselineVisible(boolean visible) {
        plot_.setDomainZeroBaselineVisible(visible);
    }

    /**
     * Controls whether the zero baseline is drawn for the range axis.
     *
     * @param visible {@code true} to make the baseline visible
     */
    public void setRangeZeroBaselineVisible(boolean visible) {
        plot_.setRangeZeroBaselineVisible(visible);
    }

    /**
     * Sets the stroke for the domain gridlines.
     *
     * @param stroke the stroke ({@code null} not permitted)
     */
    public void setDomainGridlineStroke(Stroke stroke) {
        plot_.setDomainGridlineStroke(stroke);
    }

    /**
     * Sets the stroke for the range gridlines.
     *
     * @param stroke the stroke ({@code null} not permitted)
     */
    public void setRangeGridlineStroke(Stroke stroke) {
        plot_.setRangeGridlineStroke(stroke);
    }

    /**
     * Sets the stroke for the domain minor gridlines.
     *
     * @param stroke the stroke ({@code null} not permitted)
     */
    public void setDomainMinorGridlineStroke(Stroke stroke) {
        plot_.setDomainMinorGridlineStroke(stroke);
    }

    /**
     * Sets the stroke for the range minor gridlines.
     *
     * @param stroke the stroke ({@code null} not permitted)
     */
    public void setRangeMinorGridlineStroke(Stroke stroke) {
        plot_.setRangeMinorGridlineStroke(stroke);
    }

    /**
     * Sets the visibility of the domain crosshair.
     *
     * @param flag the new visibility flag
     */
    public void setDomainCrosshairVisible(boolean flag) {
        plot_.setDomainCrosshairVisible(flag);
    }

    /**
     * Sets the visibility of the range crosshair.
     *
     * @param flag the new visibility flag
     */
    public void setRangeCrosshairVisible(boolean flag) {
        plot_.setRangeCrosshairVisible(flag);
    }

    /**
     * Sets the axis offsets (gap between the data area and the axes) and sends
     * a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param offset the offset ({@code null} not permitted).
     */
    public void setAxisOffset(RectangleInsets offset) {
        plot_.setAxisOffset(offset);
    }

    /**
     * Enables auto‑range on both the domain and range axes, causing
     * them to recalculate their bounds to fit the data.
     */
    public void autoAdjustRange() {
        ValueAxis xAxis = getDomainAxis();
        if (xAxis != null) {
            xAxis.setAutoRange(true);
        }
        ValueAxis yAxis = getRangeAxis();
        if (yAxis != null) {
            yAxis.setAutoRange(true);
        }
    }

    /**
     * Sets the stroke used for a series.
     *
     * @param series the series index (zero-based).
     * @param stroke the stroke ({@code null} permitted).
     */
    public void setSeriesStroke(int series, Stroke stroke) {
        renderer0_.setSeriesStroke(series, stroke);
    }

    /**
     * Set the stroke width for a series.
     *
     * @param series the series index (zero-based).
     * @param width  stroke width.
     */
    public void setSeriesStrokeWidth(int series, float width) {
        BasicStroke stroke = (BasicStroke) renderer0_.getSeriesStroke(series);
        if (stroke == null) {
            renderer0_.setSeriesStroke(series, new BasicStroke(width));
        } else {
            renderer0_.setSeriesStroke(series, new BasicStroke(width, stroke.getEndCap(),
                    stroke.getLineJoin(), stroke.getMiterLimit(), stroke.getDashArray(), stroke.getDashPhase()));
        }
    }

    /**
     * Sets the outline paint for a series.
     *
     * @param series the series index (zero-based)
     * @param paint  the paint ({@code null} permitted)
     */
    public void setSeriesOutlinePaint(int series, Paint paint) {
        renderer0_.setSeriesOutlinePaint(series, paint);
    }

    /**
     * Sets the default visibility for item labels.
     *
     * @param visible {@code true} to show item labels by default
     */
    public void setDefaultItemLabelsVisible(boolean visible) {
        renderer0_.setDefaultItemLabelsVisible(visible);
    }

    /**
     * Sets the default item label generator.
     *
     * @param generator the generator ({@code null} permitted)
     */
    public void setDefaultItemLabelGenerator(XYItemLabelGenerator generator) {
        renderer0_.setDefaultItemLabelGenerator(generator);
    }

    /**
     * Sets the default positive item label position.
     *
     * @param position the position ({@code null} not permitted).
     */
    public void setDefaultPositiveItemLabelPosition(ItemLabelPosition position) {
        renderer0_.setDefaultPositiveItemLabelPosition(position);
    }

    /**
     * Sets the paint used for a series.
     *
     * @param series the series index.
     * @param paint  the paint ({@code null} permitted).
     */
    public void setSeriesPaint(int series, Paint paint) {
        renderer0_.setSeriesPaint(series, paint);
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
     * Sets the paint used for a series fill and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     */
    public void setSeriesFillPaint(int series, Paint paint) {
        renderer0_.setSeriesFillPaint(series, paint);
    }

    /**
     * Sets the renderer for the primary dataset.
     * <p>
     * If the renderer is set to {@code null}, no data will be displayed.
     *
     * @param renderer the renderer ({@code null} permitted).
     */
    public void setRenderer(XYItemRenderer renderer) {
        plot_.setRenderer(renderer);
    }

    /**
     * Sets the renderer for the dataset with the specified index and sends a
     * change event to all registered listeners.  Note that each dataset should
     * have its own renderer, you should not use one renderer for multiple
     * datasets.
     *
     * @param index    the index (must be &gt;= 0).
     * @param renderer the renderer.
     */
    public void setRenderer(int index, XYItemRenderer renderer) {
        plot_.setRenderer(index, renderer);
    }

    /**
     * Returns the renderer with the specified index, or {@code null}.
     *
     * @param index the renderer index (must be &gt;= 0).
     * @return The renderer (possibly {@code null}).
     * @see #setRenderer(int, XYItemRenderer)
     */
    public XYItemRenderer getRenderer(int index) {
        return plot_.getRenderer(index);
    }

    /**
     * Sets a range axis and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param axis the axis ({@code null} permitted).
     */
    public void addRangeAxis(ValueAxis axis) {
        plot_.addRangeAxis(axis);
    }


    /**
     * Adds an annotation and sends a {@link RendererChangeEvent} to all
     * registered listeners.  The annotation is added to the foreground
     * layer.
     *
     * <p>Annotations added directly to an {@link XYPlot} are drawn once for the entire plot,
     * while annotations added to an {@link XYItemRenderer} are drawn only when that renderer
     * is painted, and support {@link Layer} (background / foreground) placement.
     *
     * @param annotation the annotation.
     */
    public void addRendererAnnotation(@NonNull XYAnnotation annotation) {
        renderer0_.addAnnotation(annotation);
    }

}
