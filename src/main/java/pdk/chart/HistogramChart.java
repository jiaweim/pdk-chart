package pdk.chart;

import pdk.chart.axis.NumberAxis;

import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Aug 2026, 3:32 PM
 */
public class HistogramChart extends XYChart {


    private NumberAxis xAxis_;
    private NumberAxis yAxis_;

    public HistogramChart(String title, Font titleFont, boolean createLegend) {
        super(title, titleFont, createLegend);
        xAxis_ = new NumberAxis();
        yAxis_ = new NumberAxis();
    }


}
