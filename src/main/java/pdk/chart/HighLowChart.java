package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.OHLCDataset;
import pdk.chart.labels.HighLowItemLabelGenerator;
import pdk.chart.renderer.xy.HighLowRenderer;

/**
 * High low open close chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 2:49 PM
 */
public class HighLowChart extends XYChart {

    private DateAxis xAxis_;
    private NumberAxis yAxis_;
    private HighLowRenderer renderer1_;

    /**
     * Creates and returns a default instance of a high-low-open-close chart.
     *
     * @param title          the chart title ({@code null} permitted).
     * @param timeAxisLabel  a label for the time axis ({@code null}
     *                       permitted).
     * @param valueAxisLabel a label for the value axis ({@code null}
     *                       permitted).
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @param legend         a flag specifying whether a legend is required.
     */
    public HighLowChart(OHLCDataset dataset, String timeAxisLabel, String valueAxisLabel,
            String title, boolean legend, boolean tooltips) {
        super(title, legend);
        xAxis_ = new DateAxis(timeAxisLabel);
        yAxis_ = new NumberAxis(valueAxisLabel);
        renderer1_ = new HighLowRenderer();
        renderer0_ = renderer1_;

        if (tooltips) {
            renderer1_.setDefaultToolTipGenerator(new HighLowItemLabelGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);

        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates and returns a default instance of a high-low-open-close chart.
     *
     * @param title          the chart title ({@code null} permitted).
     * @param timeAxisLabel  a label for the time axis ({@code null}
     *                       permitted).
     * @param valueAxisLabel a label for the value axis ({@code null}
     *                       permitted).
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @param legend         a flag specifying whether a legend is required.
     */
    public HighLowChart(OHLCDataset dataset, String timeAxisLabel, String valueAxisLabel,
            String title, boolean legend) {
        this(dataset, timeAxisLabel, valueAxisLabel, title, legend, false);
    }
}
