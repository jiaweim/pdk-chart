package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.labels.XYToolTipGenerator;
import pdk.chart.model.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.urls.StandardXYURLGenerator;
import pdk.chart.urls.XYURLGenerator;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * A specialized chart for scatter plots, built on top of {@link Chart}.
 * Provides convenient methods to configure common scatter chart properties
 * such as point shape, color, transparency, and axis ranges.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 30 Jul 2026, 9:41 AM
 */
public class ScatterChart extends XYChart {
    /**
     * Reference to the renderer for direct manipulation.
     */
    private XYLineAndShapeRenderer renderer1_;

    @Override
    protected void initRenderer() {
        super.initRenderer();
        renderer1_ = new XYLineAndShapeRenderer(false, true);
        renderer0_ = renderer1_;
    }

    /**
     * Returns the internal {@link XYLineAndShapeRenderer} for advanced customization.
     *
     * @return the renderer
     */
    @Override
    public XYLineAndShapeRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Constructs a new scatter chart with the specified dataset, axis labels, title,
     * orientation, and optional legend, tooltips, and URLs.
     * <p>
     * The X-axis is automatically adapted to the dataset type: if a
     * {@link TimeSeriesCollection} is supplied, a
     * {@link DateAxis} is used; otherwise a
     * {@link NumberAxis} is created with
     * <code>setAutoRangeIncludesZero(false)</code>.
     * The Y-axis is always a {@link NumberAxis} with
     * auto-range-includes-zero disabled.
     * <p>
     * The default renderer draws data point shapes but does not draw connecting
     * lines. Tooltip and URL generators are installed according to the
     * {@code tooltips} and {@code urls} flags.
     *
     * @param dataset     the dataset containing the data points (must not be {@code null})
     * @param xAxisName   the label for the X (domain) axis
     * @param yAxisName   the label for the Y (range) axis
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation (horizontal or vertical)
     * @param legend      if {@code true}, a legend will be included
     * @param tooltips    if {@code true}, standard tooltips will be generated for data points
     * @param urls        if {@code true}, URLs will be generated for data points
     */
    public ScatterChart(XYDataset dataset, String xAxisName, String yAxisName,
            String title, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);

        ValueAxis xAxis;
        if (dataset instanceof TimeSeriesCollection<?>) {
            xAxis = new DateAxis(xAxisName);
        } else {
            xAxis = new NumberAxis(xAxisName);
            ((NumberAxis) xAxis).setAutoRangeIncludesZero(false);
        }
        NumberAxis yAxis = new NumberAxis(yAxisName);
        yAxis.setAutoRangeIncludesZero(false);

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            if (xAxis instanceof DateAxis) {
                toolTipGenerator = StandardXYToolTipGenerator.getTimeSeriesInstance();
            } else {
                toolTipGenerator = new StandardXYToolTipGenerator();
            }
        }

        renderer1_.setDefaultToolTipGenerator(toolTipGenerator);
        renderer1_.setURLGenerator(urlGenerator);

        plot_.setDataset(dataset);
        plot_.setDomainAxis(xAxis);
        plot_.setRangeAxis(yAxis);
        plot_.setOrientation(orientation);
        plot_.setRenderer(renderer1_);

        JChart.applyCurrentTheme(this);
    }

    /**
     * Convenience constructor that creates a minimal scatter chart from two arrays
     * of equal length, representing the x and y coordinates of data points.
     * <p>
     * The chart is built with no title, no axis labels, vertical orientation,
     * no legend, standard tooltips enabled, and no URLs.
     *
     * @param x the array of x-coordinates (must not be {@code null})
     * @param y the array of y-coordinates (must not be {@code null} and must have
     *          the same length as {@code x})
     * @throws IllegalArgumentException if {@code x} and {@code y} have different lengths
     */
    public ScatterChart(double[] x, double[] y) {
        this(Data.createXY("", x, y), null, null, null,
                PlotOrientation.VERTICAL, false, true, false);
    }

    /**
     * Constructs a scatter chart from two {@code double} arrays of equal length.
     * <p>
     * The chart is created with vertical orientation, no legend, tooltips
     * enabled, no title, and no URLs. Axis labels are taken from the
     * supplied parameters.
     *
     * @param x         the array of x-coordinates (must not be {@code null})
     * @param y         the array of y-coordinates (must not be {@code null}
     *                  and must have the same length as {@code x})
     * @param xAxisName the label for the X axis ({@code null} permitted)
     * @param yAxisName the label for the Y axis ({@code null} permitted)
     * @throws IllegalArgumentException if {@code x} and {@code y} have
     *                                  different lengths
     */
    public ScatterChart(double[] x, double[] y, String xAxisName, String yAxisName) {
        this(Data.createXY("", x, y), xAxisName, yAxisName, null,
                PlotOrientation.VERTICAL, false, true, false);
    }

    /**
     * Constructs a scatter chart from two arrays of {@code Double} values
     * representing the x and y coordinates.
     * <p>
     * The chart is created with vertical orientation, no legend, tooltips
     * enabled, no title, and no URLs. The x‑axis and y‑axis labels are
     * taken from the provided parameters.
     *
     * @param x         the array of x-coordinates (must not be {@code null})
     * @param y         the array of y-coordinates (must not be {@code null}
     *                  and must have the same length as {@code x})
     * @param xAxisName the label for the X axis ({@code null} permitted)
     * @param yAxisName the label for the Y axis ({@code null} permitted)
     * @throws IllegalArgumentException if {@code x} and {@code y} have
     *                                  different lengths
     */
    public ScatterChart(Double[] x, Double[] y, String xAxisName, String yAxisName) {
        this(Data.createXY("", x, y), xAxisName, yAxisName,
                PlotOrientation.VERTICAL, false, true);
    }

    /**
     * Constructs a new scatter chart with the specified dataset, axis labels, title,
     * orientation, and optional legend, tooltips, and URLs.
     * <p>
     * The X-axis is automatically adapted to the dataset type: if a
     * {@link TimeSeriesCollection} is supplied, a
     * {@link DateAxis} is used; otherwise a
     * {@link NumberAxis} is created with
     * <code>setAutoRangeIncludesZero(false)</code>.
     * The Y-axis is always a {@link NumberAxis} with
     * auto-range-includes-zero disabled.
     * <p>
     * The default renderer draws data point shapes but does not draw connecting
     * lines. Tooltip are installed according to the {@code tooltips} flag.
     *
     * @param dataset   the dataset containing the data points (must not be {@code null})
     * @param xAxisName the label for the X (domain) axis
     * @param yAxisName the label for the Y (range) axis
     */
    public ScatterChart(XYDataset dataset, String xAxisName, String yAxisName) {
        this(dataset, xAxisName, yAxisName, PlotOrientation.VERTICAL,
                dataset.getSeriesCount() > 1, true);
    }

    /**
     * Constructs a new scatter chart with the specified dataset.
     *
     * @param dataset the dataset containing the data points (must not be {@code null})
     */
    public ScatterChart(XYDataset dataset) {
        this(dataset, null, null);
    }

    /**
     * Constructs a new scatter chart with the specified dataset, axis labels, title,
     * orientation, and optional legend, tooltips, and URLs.
     * <p>
     * The X-axis is automatically adapted to the dataset type: if a
     * {@link TimeSeriesCollection} is supplied, a
     * {@link DateAxis} is used; otherwise a
     * {@link NumberAxis} is created with
     * <code>setAutoRangeIncludesZero(false)</code>.
     * The Y-axis is always a {@link NumberAxis} with
     * auto-range-includes-zero disabled.
     * <p>
     * The default renderer draws data point shapes but does not draw connecting
     * lines. Tooltip are installed according to the {@code tooltips} flag.
     *
     * @param dataset   the dataset containing the data points (must not be {@code null})
     * @param xAxisName the label for the X (domain) axis
     * @param yAxisName the label for the Y (range) axis
     */
    public ScatterChart(XYDataset dataset, String xAxisName, String yAxisName,
            String title) {
        this(dataset, xAxisName, yAxisName, title, PlotOrientation.VERTICAL,
                dataset.getSeriesCount() > 1, true);
    }

    /**
     * Constructs a new scatter chart with the specified dataset, axis labels, title,
     * orientation, and optional legend, tooltips, and URLs.
     * <p>
     * The X-axis is automatically adapted to the dataset type: if a
     * {@link TimeSeriesCollection} is supplied, a
     * {@link DateAxis} is used; otherwise a
     * {@link NumberAxis} is created with
     * <code>setAutoRangeIncludesZero(false)</code>.
     * The Y-axis is always a {@link NumberAxis} with
     * auto-range-includes-zero disabled.
     * <p>
     * The default renderer draws data point shapes but does not draw connecting
     * lines. Tooltip are installed according to the {@code tooltips} flag.
     *
     * @param dataset     the dataset containing the data points (must not be {@code null})
     * @param xAxisName   the label for the X (domain) axis
     * @param yAxisName   the label for the Y (range) axis
     * @param orientation the plot orientation (horizontal or vertical)
     * @param legend      if {@code true}, a legend will be included
     * @param tooltips    if {@code true}, standard tooltips will be generated for data points
     */
    public ScatterChart(XYDataset dataset, String xAxisName, String yAxisName,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisName, yAxisName, null, orientation, legend, tooltips, false);
    }

    /**
     * Constructs a new scatter chart with the specified dataset, axis labels, title,
     * orientation, and optional legend, tooltips, and URLs.
     * <p>
     * The X-axis is automatically adapted to the dataset type: if a
     * {@link TimeSeriesCollection} is supplied, a
     * {@link DateAxis} is used; otherwise a
     * {@link NumberAxis} is created with
     * <code>setAutoRangeIncludesZero(false)</code>.
     * The Y-axis is always a {@link NumberAxis} with
     * auto-range-includes-zero disabled.
     * <p>
     * The default renderer draws data point shapes but does not draw connecting
     * lines. Tooltip are installed according to the {@code tooltips} flag.
     *
     * @param dataset     the dataset containing the data points (must not be {@code null})
     * @param xAxisName   the label for the X (domain) axis
     * @param yAxisName   the label for the Y (range) axis
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation (horizontal or vertical)
     * @param legend      if {@code true}, a legend will be included
     * @param tooltips    if {@code true}, standard tooltips will be generated for data points
     */
    public ScatterChart(XYDataset dataset, String xAxisName, String yAxisName,
            String title, PlotOrientation orientation,
            boolean legend, boolean tooltips) {
        this(dataset, xAxisName, yAxisName, title, orientation, legend, tooltips, false);
    }


    /**
     * Sets the default shape for all data points.
     *
     * @param shape the {@link Shape} to use (centered on (0,0))
     */
    public void setShape(Shape shape) {
        renderer1_.setDefaultShape(shape);
        renderer1_.setAutoPopulateSeriesShape(false);
    }

    /**
     * Sets the shape for a specific series.
     *
     * @param seriesIndex the series index (0-based)
     * @param shape       the shape to use for that series
     */
    public void setSeriesShape(int seriesIndex, Shape shape) {
        renderer1_.setSeriesShape(seriesIndex, shape);
    }

    /**
     * Conveniently sets the default shape to a circle with the given diameter.
     *
     * @param diameter the circle diameter in pixels
     */
    public void setCircleShape(double diameter) {
        double r = diameter / 2.0;
        setShape(new Ellipse2D.Double(-r, -r, diameter, diameter));
    }

    /**
     * Sets the default fill color for all data points.
     *
     * @param color the color to apply
     */
    public void setColor(Color color) {
        renderer1_.setDefaultPaint(color);
        renderer1_.setAutoPopulateSeriesPaint(false);
    }

    /**
     * Sets the fill color for a specific series.
     *
     * @param seriesIndex the series index (0-based)
     * @param color       the color for that series
     */
    public void setSeriesColor(int seriesIndex, Color color) {
        renderer1_.setSeriesPaint(seriesIndex, color);
    }

    /**
     * Sets the transparency of a given series.
     *
     * @param alpha transparency value between 0.0 (fully transparent) and 1.0 (opaque)
     */
    public void setSeriesAlpha(int seriesIndex, float alpha) {
        Color color = (Color) renderer1_.getSeriesPaint(seriesIndex);
        if (color != null) {
            int a = Math.round(alpha * 255);
            renderer1_.setSeriesPaint(seriesIndex,
                    new Color(color.getRed(), color.getGreen(), color.getBlue(), a));
        }
    }

    /**
     * Sets the transparency of the default point color.
     *
     * @param alpha transparency value between 0.0 (fully transparent) and 1.0 (opaque)
     */
    public void setTransparency(float alpha) {
        Color base = (Color) renderer1_.getDefaultPaint();
        if (base != null) {
            int a = Math.round(alpha * 255);
            renderer1_.setDefaultPaint(new Color(base.getRed(), base.getGreen(), base.getBlue(), a));
        }
    }

    /**
     * Controls whether connecting lines are drawn between data points.
     *
     * @param visible {@code true} to show lines, {@code false} to hide them
     */
    public void setLinesVisible(boolean visible) {
        renderer1_.setDefaultLinesVisible(visible);
    }

    /**
     * Controls connecting lines for a specific series.
     *
     * @param seriesIndex the series index
     * @param visible     {@code true} to show lines for that series
     */
    public void setSeriesLinesVisible(int seriesIndex, boolean visible) {
        renderer1_.setSeriesLinesVisible(seriesIndex, visible);
    }

    /**
     * Controls whether data point shapes are visible.
     *
     * @param visible {@code true} to show shapes, {@code false} to hide them
     */
    public void setShapesVisible(boolean visible) {
        renderer1_.setDefaultShapesVisible(visible);
    }

    /**
     * Controls shape visibility for a specific series.
     *
     * @param seriesIndex the series index
     * @param visible     {@code true} to show shapes for that series
     */
    public void setSeriesShapesVisible(int seriesIndex, boolean visible) {
        renderer1_.setSeriesShapesVisible(seriesIndex, visible);
    }

    /**
     * Sets the flag that controls whether the outline paint is used to draw
     * shape outlines, and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     *
     * @param flag the flag.
     */
    public void setUseOutlinePaint(boolean flag) {
        renderer1_.setUseOutlinePaint(flag);
    }

    /**
     * Sets the flag that controls whether outlines are drawn for
     * shapes, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * <p>
     * In some cases, shapes look better if they do NOT have an outline, but
     * this flag allows you to set your own preference.
     *
     * @param flag the flag.
     */
    public void setDrawOutlines(boolean flag) {
        renderer1_.setDrawOutlines(flag);
    }

}
