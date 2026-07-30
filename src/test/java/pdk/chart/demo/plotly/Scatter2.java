package pdk.chart.demo.plotly;

import pdk.chart.ScatterChart;
import pdk.chart.swing.ChartPanel;

import javax.swing.*;
import java.util.HashMap;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 26 Jun 2026, 21:43
 */
public class Scatter2 {

    private static ScatterChart createChart() {
        HashMap<String, Object[]> iris = Datasets.iris();
        ScatterChart chart = new ScatterChart(
                (Double[]) iris.get("Sepal Width"),
                (Double[]) iris.get("Sepal Length"),
                "Sepal Width",
                "Sepal Length");
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        createChart().show();
    }
}
