package pdk.chart.demo.echarts;

import pdk.chart.Chart;
import pdk.chart.ScatterChart;
import pdk.chart.swing.ChartPanel;

import javax.swing.*;
import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 30 Jul 2026, 9:29 AM
 */
public class BasicScatterChart {

    private static Chart createChart() {
        ScatterChart chart = new ScatterChart(
                new double[]{
                        10.0, 8.07, 13.0, 9.05, 11.0,
                        14.0, 13.4, 10.0, 14.0, 12.5,
                        9.15, 11.5, 3.03, 12.2, 2.02,
                        1.05, 4.05, 6.03, 12.0, 12.0,
                        7.08, 5.02},
                new double[]{
                        8.04, 6.95, 7.58, 8.81, 8.33,
                        7.66, 6.81, 6.33, 8.96, 6.82,
                        7.2, 7.2, 4.23, 7.83, 4.47,
                        3.33, 4.96, 7.24, 6.26, 8.84,
                        5.82, 5.68}
        );
        chart.setCircleShape(20);
        chart.setColor(Color.BLUE);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }


    static void main() {
        createChart().show();
    }
}
