package pdk.chart;

import org.jspecify.annotations.NonNull;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.statistics.HistogramDataset;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarPainter;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

/**
 * Bar chart with both X-axis and Y-axis as ValueAxis (numeric axes), implemented on XYPlot.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Aug 2026, 1:43 PM
 */
public class BarChart extends XYChart {

    protected XYBarRenderer renderer_;
    private final ValueAxis yAxis_;
    private final ValueAxis xAxis_;

    public BarChart(AxisType xAxisType, AxisType yAxisType,
            String title, boolean createLegend) {
        super(title, DEFAULT_TITLE_FONT, createLegend);
        renderer_ = new XYBarRenderer();
        setDefaultRenderer(renderer_);

        this.xAxis_ = xAxisType.createInstance();
        if (xAxis_ instanceof NumberAxis nAxis) {
            nAxis.setAutoRangeIncludesZero(false);
        }
        this.yAxis_ = yAxisType.createInstance();

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer_);

        JChartUtils.applyCurrentTheme(this);
    }

    /**
     * Creates and returns a default instance of an XY bar chart.
     * <p>
     * The chart object returned by this method uses an {@link XYPlot} instance
     * as the plot, with a {@link DateAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link XYBarRenderer} as the
     * renderer.
     *
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param xAxisType   {@link pdk.chart.AxisType} for x -axis.
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param title       the chart title ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     */
    public BarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        this(xAxisType, yAxisType, title, legend);
        xAxis_.setLabel(xAxisLabel);
        yAxis_.setLabel(yAxisLabel);
        if (tooltips) {
            if (xAxisType == AxisType.DATE) {
                renderer_.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
            } else {
                renderer_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
            }
        }
        if (urls) {
            renderer_.setURLGenerator(new StandardXYURLGenerator());
        }
        plot_.setDataset(dataset);
        plot_.setOrientation(orientation);
    }

    /**
     * Creates and returns a default instance of an XY bar chart.
     * <p>
     * The chart object returned by this method uses an {@link XYPlot} instance
     * as the plot, with a {@link DateAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link XYBarRenderer} as the
     * renderer.
     *
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param xAxisType   {@link AxisType} for x -axis.
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param yAxisType   {@link AxisType} for y-axis.
     * @param title       the chart title ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     */
    public BarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, yAxisType, title,
                orientation, legend, tooltips, false);
    }

    /**
     * Creates and returns a default instance of an XY bar chart.
     * <p>
     * The chart object returned by this method uses an {@link XYPlot} instance
     * as the plot, with a {@link DateAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link XYBarRenderer} as the
     * renderer.
     *
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param xAxisType   {@link AxisType} for x -axis.
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param title       the chart title ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     */
    public BarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, AxisType.NUMBER, title, orientation, legend, tooltips);
    }

    /**
     * Creates and returns a default instance of an XY bar chart.
     *
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param xAxisType   {@link AxisType} for x -axis.
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param yAxisType   {@link AxisType} for y-axis.
     * @param title       the chart title ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     */
    public BarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, String title,
            PlotOrientation orientation) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, yAxisType, title, orientation, true, true);
    }

    /**
     * Creates and returns a default instance of an XY bar chart.
     * <p>
     * The chart object returned by this method uses an {@link XYPlot} instance
     * as the plot, with a {@link DateAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link XYBarRenderer} as the
     * renderer.
     *
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param xAxisType  {@link pdk.chart.AxisType} for x -axis.
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param title      the chart title ({@code null} permitted).
     */
    public BarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, String title) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, AxisType.NUMBER,
                title, PlotOrientation.VERTICAL);
    }

    /**
     * Creates a histogram chart.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  the x axis label ({@code null} permitted).
     * @param yAxisLabel  the y axis label ({@code null} permitted).
     * @param dataset     the dataset ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      create a legend?
     * @param tooltips    display tooltips?
     * @param urls        generate URLs?
     */
    public BarChart(IntervalXYDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, orientation, legend, tooltips, urls);
    }

    /**
     * Creates a histogram chart.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  the x axis label ({@code null} permitted).
     * @param yAxisLabel  the y axis label ({@code null} permitted).
     * @param dataset     the dataset ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      create a legend?
     * @param tooltips    display tooltips?
     */
    public BarChart(IntervalXYDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a histogram chart.  This chart is constructed with an
     * {@link XYPlot} using an {@link XYBarRenderer}.  The domain and range
     * axes are {@link NumberAxis} instances.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel the x axis label ({@code null} permitted).
     * @param yAxisLabel the y axis label ({@code null} permitted).
     * @param dataset    the dataset ({@code null} permitted).
     */
    public BarChart(IntervalXYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, PlotOrientation.VERTICAL, true, false, false);
    }

    /**
     * Creates a histogram chart.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel the x axis label ({@code null} permitted).
     * @param yAxisLabel the y axis label ({@code null} permitted).
     * @param dataset    the dataset ({@code null} permitted).
     */
    public BarChart(HistogramDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, PlotOrientation.VERTICAL, true, false, false);
    }

    /**
     * Creates a histogram chart.
     *
     * @param xAxisLabel the x axis label ({@code null} permitted).
     * @param yAxisLabel the y axis label ({@code null} permitted).
     * @param dataset    the dataset ({@code null} permitted).
     */
    public BarChart(HistogramDataset dataset, String xAxisLabel, String yAxisLabel) {
        this(dataset, xAxisLabel, yAxisLabel, null);
    }

    /**
     * Sets the flag that controls whether bar outlines are drawn.
     *
     * @param draw the flag.
     */
    public void setDrawBarOutline(boolean draw) {
        renderer_.setDrawBarOutline(draw);
    }

    /**
     * Sets the bar painter.
     *
     * @param painter the painter.
     */
    public void setBarPainter(@NonNull XYBarPainter painter) {
        renderer_.setBarPainter(painter);
    }

    /**
     * Sets the flag that controls whether the renderer
     * draws shadows for the bars.
     *
     * @param visible the new flag value.
     */
    public void setShadowVisible(boolean visible) {
        renderer_.setShadowVisible(visible);
    }
}
