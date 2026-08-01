package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.urls.StandardXYURLGenerator;
import pdk.chart.util.Args;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 2:23 PM
 */
public class LineChart extends XYChart {

    private final XYLineAndShapeRenderer renderer;
    private ValueAxis xAxis_;
    private NumberAxis yAxis_;

    public LineChart(AxisType xAxisType, String title, boolean createLegend) {
        super(title, DEFAULT_TITLE_FONT, createLegend);
        renderer = new XYLineAndShapeRenderer(true, false);
        setDefaultRenderer(renderer);

        xAxis_ = xAxisType.createInstance();
        if (xAxis_ instanceof NumberAxis nAxis) {
            nAxis.setAutoRangeIncludesZero(false);
        }
        yAxis_ = new NumberAxis();
        yAxis_.setAutoRangeIncludesZero(false);

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer);
        JChartUtils.applyCurrentTheme(this);
    }

    public LineChart(String title, boolean createLegend) {
        this(AxisType.NUMBER, title, createLegend);
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
    public LineChart(XYDataset dataset, String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        this(xAxisType, title, legend);
        Args.nullNotPermitted(orientation, "PlotOrientation");
        xAxis_.setLabel(xAxisLabel);
        yAxis_.setLabel(yAxisLabel);
        plot_.setDataset(dataset);
        plot_.setOrientation(orientation);
        if (tooltips) {
            if (xAxis_ instanceof NumberAxis) {
                renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
            } else if (xAxis_ instanceof DateAxis) {
                renderer.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
            }
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }
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
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, title,
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
                PlotOrientation.VERTICAL, true, false, false);
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
        this(title, legend);
        Args.nullNotPermitted(orientation, "PlotOrientation");
        xAxis_.setLabel(xAxisLabel);
        yAxis_.setLabel(yAxisLabel);
        plot_.setDataset(dataset);
        plot_.setOrientation(orientation);
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }
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
    public LineChart(XYDataset dataset,
            String xAxisLabel, String yAxisLabel, String title) {
        this(dataset, xAxisLabel, yAxisLabel, title, PlotOrientation.VERTICAL, true, true);
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
}
