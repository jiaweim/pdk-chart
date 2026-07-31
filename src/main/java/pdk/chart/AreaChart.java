package pdk.chart;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.labels.XYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.XYAreaRenderer;
import pdk.chart.urls.StandardXYURLGenerator;
import pdk.chart.urls.XYURLGenerator;

import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 6:48 PM
 */
public class AreaChart extends XYChart {

    private NumberAxis xAxis_;
    private NumberAxis yAxis_;
    private XYAreaRenderer renderer_;

    public AreaChart(String title, Font titleFont, boolean createLegend) {
        super(title, titleFont, createLegend);
        xAxis_ = new NumberAxis();
        xAxis_.setAutoRangeIncludesZero(false);

        yAxis_ = new NumberAxis();
        renderer_ = new XYAreaRenderer();

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer_);
        plot_.setForegroundAlpha(0.5f);
        JChartUtils.applyCurrentTheme(this);
    }

    /**
     * Creates an area chart using an {@link XYDataset}.
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
    public AreaChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        this(title, DEFAULT_TITLE_FONT, legend);

        xAxis_.setLabel(xAxisLabel);
        yAxis_.setLabel(yAxisLabel);

        plot_.setDataset(dataset);
        plot_.setOrientation(orientation);

        if (tooltips) {
            XYToolTipGenerator tipGenerator = new StandardXYToolTipGenerator();
            renderer_.setDefaultToolTipGenerator(tipGenerator);
        }

        if (urls) {
            XYURLGenerator urlGenerator = new StandardXYURLGenerator();
            renderer_.setURLGenerator(urlGenerator);
        }
    }

    /**
     * Creates an area chart using an {@link XYDataset}.
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
    public AreaChart(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title,
                orientation, legend, tooltips, false);
    }

    /**
     * Creates an area chart using an {@link XYDataset}.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     */
    public AreaChart(XYDataset dataset, String xAxisLabel, String yAxisLabel,
            String title) {
        this(dataset, xAxisLabel, yAxisLabel, title, PlotOrientation.VERTICAL,
                true, true);
    }
}
