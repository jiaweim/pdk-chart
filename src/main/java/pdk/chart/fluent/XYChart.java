package pdk.chart.fluent;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.Chart;
import pdk.chart.annotations.XYAnnotation;
import pdk.chart.annotations.XYPointerAnnotation;
import pdk.chart.api.Layer;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.fluent.prop.*;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.Marker;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.renderer.xy.YIntervalRenderer;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.Title;

import java.awt.*;

/**
 * Line Chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jun 2026, 10:47 AM
 */
public class XYChart extends Chart {

    public static XYChart create() {
        return new XYChart(AxisType.NUMBER, AxisType.NUMBER);
    }

    /**
     * Create an {@link XYChart}.
     *
     * @param xAxisType {@link AxisType}
     * @param yAxisType {@link AxisType}
     * @return {@link XYChart}
     */
    public static XYChart create(AxisType xAxisType, AxisType yAxisType) {
        return new XYChart(xAxisType, yAxisType);
    }

    private final ValueAxis domainAxis_;
    private final ValueAxis rangeAxis_;
    private final XYPlot plot_;

    public XYChart(AxisType xAxisType, AxisType yAxisType) {
        super(null, DEFAULT_TITLE_FONT, new XYPlot<>(), false);
        plot_ = (XYPlot) getPlot();
        this.domainAxis_ = xAxisType.createInstance();
        this.rangeAxis_ = yAxisType.createInstance();

        if (domainAxis_ instanceof NumberAxis numberAxis) {
            numberAxis.setAutoRangeIncludesZero(false);
        }

        plot_.setDomainAxis(domainAxis_);
        plot_.setRangeAxis(rangeAxis_);

        DEFAULT_THEME.apply(this);
    }


    /**
     * Return the configuration class for range axis properties.
     *
     * @return {@link CategoryNumberAxisProps}.
     */
    public NumberAxisProps rangeAxis() {
        return new NumberAxisProps(this, (NumberAxis) rangeAxis_);
    }

    /**
     * Return the configuration class for range axis properties.
     *
     * @return {@link CategoryNumberAxisProps}.
     */
    public DateAxisProps rangeAxisDate() {
        return new DateAxisProps(this, (DateAxis) rangeAxis_);
    }

    /**
     * Return the configuration class for domain axis properties.
     *
     * @return {@link CategoryNumberAxisProps}.
     */
    public NumberAxisProps domainAxis() {
        if (domainAxis_ instanceof NumberAxis nAxis) {
            return new NumberAxisProps(this, nAxis);
        } else {
            throw new IllegalStateException("The domain axis is not NumberAxis");
        }
    }

    /**
     * Return the configuration class for domain axis properties.
     *
     * @return {@link DateAxisProps}.
     */
    public DateAxisProps domainAxisDate() {
        if (domainAxis_ instanceof DateAxis da) {
            return new DateAxisProps(this, da);
        } else {
            throw new IllegalStateException("The domain axis is not DateAxis");
        }
    }

    /**
     * Return the domain axis.
     *
     * @return {@link ValueAxis}.
     */
    public ValueAxis getDomainAxis() {
        return domainAxis_;
    }

    /**
     * Set axis names.
     *
     * @param xName name for domain axis.
     * @param yName name for range axis.
     * @return this.
     */
    public XYChart axisNames(String xName, String yName) {
        domainAxis_.setLabel(xName);
        rangeAxis_.setLabel(yName);
        return this;
    }

    /**
     * Return the {@link XYRendererProps} for a given dataset.
     *
     * @param dataset index of the dataset.
     * @return {@link XYLineAndShapeProps}.
     */
    public XYLineAndShapeProps lineAndShapeProps(int dataset) {
        XYItemRenderer renderer = plot_.getRenderer(dataset);
        if (renderer instanceof XYLineAndShapeRenderer lineAndShapeRenderer) {
            return new XYLineAndShapeProps(this, lineAndShapeRenderer);
        }
        throw new IllegalStateException("The renderer corresponding to the specified dataset is not LINE or SCATTER type.");
    }

    /**
     * Return the {@link XYBarProps} for a given dataset.
     * <p>
     * Throw an {@link IllegalStateException} if the chart type of the corresponding dataset
     * is neither {@link XYChartType#BAR} nor {@link XYChartType#HISTOGRAM}.
     *
     * @param dataset index of the dataset.
     * @return {@link XYBarProps}.
     */
    public XYBarProps barProps(int dataset) {
        XYItemRenderer renderer = plot_.getRenderer(dataset);
        if (renderer instanceof XYBarRenderer xyBarRenderer) {
            return new XYBarProps(this, xyBarRenderer);
        }
        throw new IllegalStateException("The renderer corresponding to the specified dataset is not BAR type.");
    }


    /**
     * Return the {@link YIntervalProps} for a given dataset.
     * <p>
     * Throw an {@link IllegalStateException} if the chart type of the corresponding dataset
     * is neither {@link XYChartType#PEAK}.
     *
     * @param dataset index of the dataset.
     * @return {@link YIntervalProps}.
     */
    public YIntervalProps peakProps(int dataset) {
        XYItemRenderer renderer = plot_.getRenderer(dataset);
        if (renderer instanceof YIntervalRenderer intervalRenderer) {
            return new YIntervalProps(this, intervalRenderer);
        }
        throw new IllegalStateException("The renderer corresponding to the specified dataset is not PEAK type.");
    }

    /**
     * Sets the message that is displayed when the dataset is empty or
     * {@code null}.
     *
     * @param message the message ({@code null} permitted).
     */
    public XYChart noDataMessage(String message) {
        plot_.setNoDataMessage(message);
        return this;
    }

    /**
     * Set the Chart title.
     *
     * @param title new title
     * @return this
     */
    public XYChart title(String title) {
        setTitle(title);
        return this;
    }

    public LegendTitleProps legend() {
        return new LegendTitleProps(this, (LegendTitle) getSubtitle(0));
    }

    /**
     * Whether to create and display the legend.
     *
     * @param showLegend true if add legend.
     * @return this
     */
    public XYChart showLegend(boolean showLegend) {
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
     * Adds a chart subtitle, and notifies registered listeners that the chart
     * has been modified.
     *
     * @param title the subtitle.
     */
    public XYChart addTitle(@NonNull Title title) {
        addSubtitle(title);
        return this;
    }

    /**
     * Sets the paint used to fill the chart background.
     *
     * @param paint the paint.
     * @see #getBackgroundPaint()
     */
    public XYChart backgroundPaint(@Nullable Paint paint) {
        setBackgroundPaint(paint);
        return this;
    }


    /**
     * Sets the alpha-transparency for the plot.
     *
     * @param alpha the new alpha transparency.
     */
    public XYChart foregroundAlpha(float alpha) {
        plot_.setForegroundAlpha(alpha);
        return this;
    }

    /**
     * Set the dataset to plot
     *
     * @param dataset {@link XYDataset}
     * @return this
     */
    public XYChart dataset(XYDataset dataset, XYChartType chartType) {
        return addDataset(0, dataset, chartType);
    }

    /**
     * Set the dataset to plot
     *
     * @param dataset {@link XYDataset}
     * @return this
     */
    public XYChart addDataset(XYDataset dataset, XYChartType chartType) {
        return addDataset(plot_.getDatasetCount(), dataset, chartType);
    }

    /**
     * Add a new dataset to the plot
     *
     * @param index     index of the dataset
     * @param dataset   {@link XYDataset} instance
     * @param chartType {@link XYChartType} for this dataset.
     * @return this
     */
    public XYChart addDataset(int index, XYDataset dataset, XYChartType chartType) {
        XYDataset preDataset = plot_.getDataset(index);
        if (preDataset != null) {
            throw new IllegalStateException("Dataset with index " + index + " already exists!");
        }

        plot_.setDataset(index, dataset);
        plot_.setRenderer(index, chartType.getRenderer());

        return this;
    }

    /**
     * Set the chart orientation.
     *
     * @param orientation {@link PlotOrientation}
     * @return this
     */
    public XYChart orientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
        return this;
    }

    /**
     * Adds a marker for the range axis in the specified layer.
     * <p>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the range axis, however this is entirely up to the renderer.
     *
     * @param marker the marker ({@code null} not permitted).
     * @param layer  the layer (foreground or background).
     */
    public XYChart addRangeMarker(Marker marker, Layer layer) {
        plot_.addRangeMarker(marker, layer);
        return this;
    }

    /**
     * Adds a marker for the range axis in the specified layer.
     * <p>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the range axis, however this is entirely up to the renderer.
     *
     * @param marker the marker ({@code null} not permitted).
     */
    public XYChart addRangeMarker(Marker marker) {
        plot_.addRangeMarker(marker, Layer.FOREGROUND);
        return this;
    }

    /**
     * Adds a marker for the domain axis.
     * <p>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the domain axis, however this is entirely up to the renderer.
     *
     * @param marker the marker ({@code null} not permitted).
     */
    public XYChart addDomainMarker(Marker marker) {
        plot_.addDomainMarker(marker, Layer.FOREGROUND);
        return this;
    }

    /**
     * Add a XYPointer Annotation
     *
     * @param label           annotation text
     * @param x               the x-coordinate (measured against the chart's domain axis).
     * @param y               the y-coordinate (measured against the chart's range axis).
     * @param angle           the angle of the arrow's line (in radians).
     * @param labelOffset     the label offset (distance between arrows and annotation text)
     * @param textAnchor      the text anchor (the point on the text bounding rectangle that is aligned to the (x, y) coordinate of the annotation)
     * @param backgroundColor the background paint for the annotation
     * @return this
     */
    public XYChart addPointerAnnotation(String label, double x, double y,
            double angle, double labelOffset,
            TextAnchor textAnchor, Color backgroundColor) {

        XYPointerAnnotation annotation = new XYPointerAnnotation(label, x, y, angle);
        annotation.setLabelOffset(labelOffset);
        annotation.setTextAnchor(textAnchor);
        annotation.setBackgroundPaint(backgroundColor);
        plot_.addAnnotation(annotation);
        return this;
    }

    /**
     * Adds an annotation to the plot.
     *
     * @param annotation the annotation.
     */
    public XYChart addAnnotation(@NonNull XYAnnotation annotation) {
        plot_.addAnnotation(annotation, false);
        return this;
    }

    /**
     * Enables or disables panning of the plot along the domain axes.
     *
     * @param pannable the new flag value.
     */
    public XYChart domainPannable(boolean pannable) {
        plot_.setDomainPannable(pannable);
        return this;
    }

    /**
     * Enables or disables panning of the plot along the range axis/axes.
     *
     * @param pannable the new flag value.
     */
    public XYChart rangePannable(boolean pannable) {
        plot_.setRangePannable(pannable);
        return this;
    }

    /**
     * Enables or disables panning of the plot along the range axis/axes.
     *
     * @param domainPannable Enables or disables panning of the plot along the domain axes.
     * @param rangePannable  Enables or disables panning of the plot along the range axes.
     * @return this.
     */
    public XYChart pannable(boolean domainPannable, boolean rangePannable) {
        plot_.setDomainPannable(domainPannable);
        plot_.setRangePannable(rangePannable);
        return this;
    }

    /**
     * Sets the background color of the plot area.
     *
     * @param paint the paint.
     */
    public XYChart plotBackgroundPaint(@Nullable Paint paint) {
        plot_.setBackgroundPaint(paint);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the domain axis.
     *
     * @param showDomainGridlines true if show grid lines
     * @return this
     */
    public XYChart domainGridlinesVisible(boolean showDomainGridlines) {
        plot_.setDomainGridlinesVisible(showDomainGridlines);
        return this;
    }

    /**
     * Sets the stroke for the grid lines plotted against the domain axis.
     *
     * @param stroke the stroke.
     */
    public XYChart domainGridlinesStroke(@NonNull Stroke stroke) {
        plot_.setDomainGridlineStroke(stroke);
        return this;
    }

    /**
     * Sets the paint used to draw the grid-lines (if any) against the domain
     * axis.
     *
     * @param paint the paint.
     */
    public XYChart domainGridlinePaint(@NonNull Paint paint) {
        plot_.setDomainGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the stroke for the minor grid lines plotted against the domain axis.
     *
     * @param stroke the stroke.
     */
    public XYChart domainMinorGridlineStroke(@NonNull Stroke stroke) {
        plot_.setDomainMinorGridlineStroke(stroke);
        return this;
    }

    /**
     * Whether grid-lines are drawn against the range axis.
     *
     * @param showRangeGridlines true if show grid lines
     * @return this
     */
    public XYChart rangeGridlinesVisible(boolean showRangeGridlines) {
        plot_.setRangeGridlinesVisible(showRangeGridlines);
        return this;
    }

    /**
     * Sets the stroke for the grid lines plotted against the range axis.
     *
     * @param stroke the stroke ({@code null} not permitted).
     */
    public XYChart rangeGridlinesStroke(@NonNull Stroke stroke) {
        plot_.setRangeGridlineStroke(stroke);
        return this;
    }

    /**
     * Sets the stroke for the minor grid lines plotted against the range axis.
     *
     * @param stroke the stroke.
     */
    public XYChart rangeMinorGridlineStroke(@NonNull Stroke stroke) {
        plot_.setRangeMinorGridlineStroke(stroke);
        return this;
    }

    /**
     * Sets the paint used to draw the grid lines against the range axis.
     *
     * @param paint the paint.
     */
    public XYChart rangeGridlinePaint(@NonNull Paint paint) {
        plot_.setRangeGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the paint for the minor grid lines plotted against the range axis.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public XYChart rangeMinorGridlinePaint(Paint paint) {
        plot_.setRangeMinorGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the paint for the minor grid lines plotted against the domain axis.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public XYChart domainMinorGridlinePaint(Paint paint) {
        plot_.setDomainMinorGridlinePaint(paint);
        return this;
    }

    /**
     * Sets the flag that controls whether the domain minor grid-lines
     * are visible.
     *
     * @param visible the new value of the flag.
     */
    public XYChart domainMinorGridlinesVisible(boolean visible) {
        plot_.setDomainMinorGridlinesVisible(visible);
        return this;
    }

    /**
     * Sets the flag that controls whether the range axis minor grid
     * lines are visible.
     *
     * @param visible the new value of the flag.
     */
    public XYChart rangeMinorGridlinesVisible(boolean visible) {
        plot_.setRangeMinorGridlinesVisible(visible);
        return this;
    }

    /**
     * Sets the flag that controls whether the zero baseline is
     * displayed for the domain axis.
     *
     * @param visible the flag.
     */
    public XYChart domainZeroBaselineVisible(boolean visible) {
        plot_.setDomainZeroBaselineVisible(visible);
        return this;
    }

    /**
     * Sets the flag that controls whether the zero baseline is
     * displayed for the range axis.
     *
     * @param visible the flag.
     */
    public XYChart rangeZeroBaselineVisible(boolean visible) {
        plot_.setRangeZeroBaselineVisible(visible);
        return this;
    }

    /**
     * Set whether the zero baseline is displayed for the domain axis and range axis.
     *
     * @param domainVisible whether display zero baseline for the domain axis.
     * @param rangeVisible  whether display zero baseline for the range axis.
     * @return this.
     */
    public XYChart zeroBaselineVisible(boolean domainVisible, boolean rangeVisible) {
        plot_.setDomainZeroBaselineVisible(domainVisible);
        plot_.setRangeZeroBaselineVisible(rangeVisible);
        return this;
    }

    /**
     * Sets whether the domain crosshair is visible.
     *
     * @param flag the new value of the flag.
     */
    public XYChart domainCrosshairVisible(boolean flag) {
        plot_.setDomainCrosshairVisible(flag);
        return this;
    }

    /**
     * Set whether the range crosshair is visible.
     *
     * @param flag the new value of the flag.
     */
    public XYChart rangeCrosshairVisible(boolean flag) {
        plot_.setRangeCrosshairVisible(flag);
        return this;
    }

}
