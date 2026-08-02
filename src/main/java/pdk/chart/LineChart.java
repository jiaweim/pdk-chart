package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.model.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

import java.util.Objects;

/**
 * XY line chart implementation.
 *
 * <p>
 * By default:
 * <ul>
 *     <li>lines are visible</li>
 *     <li>shapes are hidden</li>
 * </ul>
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 2:23 PM
 */
public class LineChart extends XYChart {

    protected XYLineAndShapeRenderer renderer1_;
    protected ValueAxis xAxis_;
    protected ValueAxis yAxis_;

    public LineChart(String title, boolean createLegend) {
        super(title, createLegend);
    }

    @Override
    protected void initRenderer() {
        renderer1_ = new XYLineAndShapeRenderer(true, false);
        renderer0_ = renderer1_;
    }

    /**
     * Creates a line chart for displaying an {@link XYDataset}.
     *
     * <p>
     * Lines are enabled by default and point shapes are disabled.
     * The domain and range axes are automatically configured according
     * to the supplied {@link AxisType}.
     *
     * @param dataset     the data source ({@code null} permitted)
     * @param xAxisLabel  the label for the domain axis
     * @param xAxisType   the domain axis type ({@code null} not permitted)
     * @param yAxisLabel  the label for the range axis
     * @param yAxisType   the range axis type ({@code null} not permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      whether to display a legend
     * @param tooltips    whether to enable item tooltips
     * @param urls        whether to enable item URLs
     */
    public LineChart(XYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, String title, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);
        Objects.requireNonNull(xAxisType);
        Objects.requireNonNull(yAxisType);

        xAxis_ = xAxisType.createInstance(xAxisLabel);
        if (xAxis_ instanceof NumberAxis nAxis) {
            nAxis.setAutoRangeIncludesZero(false);
        }
        yAxis_ = yAxisType.createInstance(yAxisLabel);

        if (tooltips) {
            if (xAxis_ instanceof NumberAxis) {
                renderer1_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
            } else if (xAxis_ instanceof DateAxis) {
                renderer1_.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
            } else {
                throw new IllegalArgumentException("Unknown axis type: " + xAxisType);
            }
        }
        if (urls) {
            renderer1_.setURLGenerator(new StandardXYURLGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setOrientation(orientation);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     */
    public LineChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, AxisType.NUMBER, title,
                orientation, legend, tooltips, false);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     */
    public LineChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, String title) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, title,
                PlotOrientation.VERTICAL, true, false);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     */
    public LineChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, null);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     */
    public LineChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, orientation, legend, tooltips, urls);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     */
    public LineChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title, orientation,
                legend, tooltips, false);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title      the chart title.
     * @param xAxisLabel a label for the X-axis.
     * @param yAxisLabel a label for the Y-axis.
     * @param dataset    the dataset for the chart.
     */
    public LineChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        this(dataset, xAxisLabel, yAxisLabel, title, PlotOrientation.VERTICAL,
                true, true);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     */
    public LineChart(XYDataset dataset, String xAxisLabel, String yAxisLabel) {
        this(dataset, xAxisLabel, yAxisLabel, null);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param dataset the dataset for the chart ({@code null} permitted).
     */
    public LineChart(XYDataset dataset) {
        this(dataset, null, null);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param x x values.
     * @param y y values.
     */
    public LineChart(double[] x, double[] y) {
        this(Data.createXY("", x, y), null, null, null,
                PlotOrientation.VERTICAL, false, true);
    }

    /**
     * Sets the flag that controls whether each series is drawn as a
     * single path and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param flag the flag.
     */
    public void setDrawSeriesLineAsPath(boolean flag) {
        renderer1_.setDrawSeriesLineAsPath(flag);
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

    /**
     * Sets the default 'lines visible' flag and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag the flag.
     */
    public void setDefaultLinesVisible(boolean flag) {
        renderer1_.setDefaultLinesVisible(flag);
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
     * Sets the 'shapes visible' flag for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param visible the flag.
     */
    public void setSeriesShapesVisible(int series, boolean visible) {
        renderer1_.setSeriesShapesVisible(series, visible);
    }

    /**
     * Sets the 'shapes filled' flag for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param flag   the flag.
     */
    public void setSeriesShapesFilled(int series, Boolean flag) {
        renderer1_.setSeriesShapesFilled(series, flag);
    }

    /**
     * Sets the flag that controls whether the fill paint is used to fill
     * shapes, and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     *
     * @param flag the flag.
     */
    public void setUseFillPaint(boolean flag) {
        renderer1_.setUseFillPaint(flag);
    }

    /**
     * Sets the flag that controls whether the outline paint is used to draw
     * shape outlines, and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     * <p>
     * Refer to {@code XYLineAndShapeRendererDemo2.java} to see the
     * effect of this flag.
     *
     * @param flag the flag.
     */
    public void setUseOutlinePaint(boolean flag) {
        renderer1_.setUseOutlinePaint(flag);
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
}
