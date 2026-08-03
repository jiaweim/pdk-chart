package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.model.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

import java.util.Objects;

/**
 * An XY line chart implementation.
 * <p>
 * By default:
 * <ul>
 *   <li>lines are visible</li>
 *   <li>shapes are hidden</li>
 * </ul>
 * <p>
 * The domain and range axes can be configured via {@link AxisType}
 * to support numeric, date, or other value axes.
 * Tool‑tips and URL generation are optional.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @see XYLineAndShapeRenderer
 * @see XYChart
 * @since 31 Jul 2026, 2:23 PM
 */
public class LineChart extends XYChart {

    protected XYLineAndShapeRenderer renderer1_;
    protected ValueAxis xAxis_;
    protected ValueAxis yAxis_;

    /**
     * Creates a new empty line chart with the given title and legend flag.
     * No dataset or axes are attached initially.
     *
     * @param title        the chart title ({@code null} permitted)
     * @param createLegend whether to display a legend
     */
    public LineChart(String title, boolean createLegend) {
        super(title, createLegend);
    }

    @Override
    protected void initRenderer() {
        renderer1_ = new XYLineAndShapeRenderer(true, false);
        renderer0_ = renderer1_;
    }

    @Override
    public XYLineAndShapeRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Fully parameterised constructor.
     *
     * @param dataset     the data source ({@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param xAxisType   the type of domain axis (must not be {@code null})
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param yAxisType   the type of range axis (must not be {@code null})
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation (must not be {@code null})
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     * @param urls        {@code true} to generate URLs for data points
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
     * Creates a line chart with the given axis types, but uses the
     * default {@link AxisType#NUMBER} for the range axis.  URLs are
     * disabled.
     *
     * @param dataset     the data source ({@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param xAxisType   the domain axis type (must not be {@code null})
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation (must not be {@code null})
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     */
    public LineChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, AxisType.NUMBER, title,
                orientation, legend, tooltips, false);
    }

    /**
     * Creates a line chart with vertical orientation, legend enabled,
     * tool‑tips disabled, and numeric range axis.
     *
     * @param dataset    the data source ({@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param xAxisType  the domain axis type (must not be {@code null})
     * @param yAxisLabel the range axis label ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     */
    public LineChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, String title) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, title,
                PlotOrientation.VERTICAL, true, false);
    }

    /**
     * Creates a line chart with the given axis type, no title, and
     * default settings (vertical orientation, legend on, tool‑tips off).
     *
     * @param dataset    the data source ({@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param xAxisType  the domain axis type (must not be {@code null})
     * @param yAxisLabel the range axis label ({@code null} permitted)
     */
    public LineChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, null);
    }

    /**
     * Creates a line chart with numeric axes.
     *
     * @param dataset     the data source ({@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation (must not be {@code null})
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     * @param urls        {@code true} to generate URLs for data points
     */
    public LineChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, orientation, legend, tooltips, urls);
    }

    /**
     * Creates a line chart with numeric axes, tool‑tips, and no URLs.
     *
     * @param dataset     the data source ({@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation (must not be {@code null})
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     */
    public LineChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title, orientation,
                legend, tooltips, false);
    }

    /**
     * Creates a line chart with numeric axes, vertical orientation,
     * legend and tool‑tips enabled, no URLs.
     *
     * @param dataset    the data source ({@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param yAxisLabel the range axis label ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     */
    public LineChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        this(dataset, xAxisLabel, yAxisLabel, title, PlotOrientation.VERTICAL,
                true, true);
    }

    /**
     * Creates a line chart with numeric axes, vertical orientation,
     * legend and tool‑tips enabled, no title.
     *
     * @param dataset    the data source ({@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param yAxisLabel the range axis label ({@code null} permitted)
     */
    public LineChart(XYDataset dataset, String xAxisLabel, String yAxisLabel) {
        this(dataset, xAxisLabel, yAxisLabel, null);
    }

    /**
     * Creates a line chart from an {@link XYDataset} with no axis
     * labels and no title.  Vertical orientation, legend and tool‑tips
     * are enabled.
     *
     * @param dataset the data source ({@code null} permitted)
     */
    public LineChart(XYDataset dataset) {
        this(dataset, null, null);
    }

    /**
     * Creates a line chart from two arrays of {@code double} values,
     * with no axis labels or title.  Vertical orientation, legend off,
     * tool‑tips on.
     *
     * @param x the x-coordinates
     * @param y the y-coordinates (must have the same length as {@code x})
     * @throws IllegalArgumentException if {@code x} and {@code y} have
     *                                  different lengths
     */
    public LineChart(double[] x, double[] y) {
        this(Data.createXY("", x, y), null, null, null,
                PlotOrientation.VERTICAL, false, true);
    }

    /**
     * Sets whether each series is drawn as a single continuous path.
     * When enabled, line rendering may be faster and smoother.
     *
     * @param flag {@code true} to draw as a single path
     */
    public void setDrawSeriesLineAsPath(boolean flag) {
        renderer1_.setDrawSeriesLineAsPath(flag);
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
     * Sets the default visibility for lines.
     *
     * @param flag {@code true} to show lines by default
     */
    public void setDefaultLinesVisible(boolean flag) {
        renderer1_.setDefaultLinesVisible(flag);
    }

    /**
     * Sets the default visibility for point shapes.
     *
     * @param flag {@code true} to show shapes by default
     */
    public void setDefaultShapesVisible(boolean flag) {
        renderer1_.setDefaultShapesVisible(flag);
    }

    /**
     * Sets the shapes visibility for a specific series.
     *
     * @param series  the series index (zero‑based)
     * @param visible {@code true} to show shapes for the series
     */
    public void setSeriesShapesVisible(int series, boolean visible) {
        renderer1_.setSeriesShapesVisible(series, visible);
    }

    /**
     * Sets whether shapes for a series are filled.
     *
     * @param series the series index (zero‑based)
     * @param flag   {@code true} to fill shapes, {@code false} for outline
     *               only, or {@code null} to use the default
     */
    public void setSeriesShapesFilled(int series, Boolean flag) {
        renderer1_.setSeriesShapesFilled(series, flag);
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
     * Sets whether the outline paint is used to draw shape outlines.
     *
     * @param flag {@code true} to use the outline paint
     */
    public void setUseOutlinePaint(boolean flag) {
        renderer1_.setUseOutlinePaint(flag);
    }

    /**
     * Sets the default shapes‑filled flag.
     *
     * @param flag {@code true} to fill shapes by default
     */
    public void setDefaultShapesFilled(boolean flag) {
        renderer1_.setDefaultShapesFilled(flag);
    }
}
