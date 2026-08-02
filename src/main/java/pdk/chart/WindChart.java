package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.WindDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.renderer.xy.WindItemRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 4:50 PM
 */
public class WindChart extends XYChart {

    private DateAxis xAxis_;
    private NumberAxis yAxis_;
    private WindItemRenderer renderer1_;

    /**
     * Creates a wind plot with default settings.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the x-axis ({@code null} permitted).
     * @param yAxisLabel a label for the y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @param legend     a flag that controls whether a legend is created.
     * @param tooltips   configure chart to generate tool tips?
     * @param urls       configure chart to generate URLs?
     */
    public WindChart(WindDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        xAxis_ = new DateAxis(xAxisLabel);
        yAxis_ = new NumberAxis(yAxisLabel);
        yAxis_.setRange(-12.0, 12.0);

        renderer1_ = new WindItemRenderer();
        renderer0_ = renderer1_;

        if (tooltips) {
            renderer1_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }

        if (urls) {
            renderer1_.setURLGenerator(new StandardXYURLGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);

        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a wind plot with default settings.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the x-axis ({@code null} permitted).
     * @param yAxisLabel a label for the y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @param legend     a flag that controls whether a legend is created.
     * @param tooltips   configure chart to generate tool tips?
     */
    public WindChart(WindDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title, legend, tooltips, false);
    }
}
