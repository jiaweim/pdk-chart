package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.OHLCDataset;
import pdk.chart.renderer.xy.CandlestickRenderer;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 1:28 PM
 */
public class CandleStickChart extends XYChart {

    private DateAxis xAxis_;
    private NumberAxis yAxis_;
    private CandlestickRenderer renderer1_;

    /**
     * Creates and returns a default instance of a candlesticks chart.
     *
     * @param title          the chart title ({@code null} permitted).
     * @param timeAxisLabel  a label for the time axis ({@code null}
     *                       permitted).
     * @param valueAxisLabel a label for the value axis ({@code null}
     *                       permitted).
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @param legend         a flag specifying whether a legend is required.
     */
    public CandleStickChart(OHLCDataset dataset, String timeAxisLabel, String valueAxisLabel,
            String title, boolean legend) {
        super(title, legend);

        xAxis_ = new DateAxis(timeAxisLabel);
        yAxis_ = new NumberAxis(valueAxisLabel);

        renderer1_ = new CandlestickRenderer();
        renderer0_ = renderer1_;

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);

        JChart.applyCurrentTheme(this);
    }
}
