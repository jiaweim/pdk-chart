package pdk.chart.demo.plotly;

import pdk.chart.ScatterChart;
import pdk.chart.swing.ChartPanel;

import javax.swing.*;

/**
 * https://plotly.com/python/line-and-scatter/
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 26 Jun 2026, 21:17
 */
public class Scatter1 {

    private static ScatterChart createChart() {
        ScatterChart chart = new ScatterChart(
                new double[]{0, 1, 2, 3, 4},
                new double[]{0, 1, 4, 9, 16},
                "x",
                "y");
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        createChart().show();
    }
}
