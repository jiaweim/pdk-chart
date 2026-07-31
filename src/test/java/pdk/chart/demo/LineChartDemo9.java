package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.LineChart;
import pdk.chart.swing.ChartPanel;

import javax.swing.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 27 Jul 2026, 10:45 AM
 */
public class LineChartDemo9 {

    private static Chart createChart() {
        double[] xData = new double[]{0.0, 1.0, 2.0};
        double[] yData = new double[]{2.0, 1.0, 0.0};

        LineChart chart = new LineChart(xData, yData);
        chart.setAxisLabels("x", "y");
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        createChart().show();
    }
}
