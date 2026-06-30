package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.data.general.DefaultValueDataset;
import pdk.chart.plot.ThermometerPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;

/**
 * A simple demo of the ThermometerPlot class.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 09 Jun 2026, 10:22 AM
 */
public class ThermometerDemo2 extends ApplicationFrame {

    public ThermometerDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        this.setContentPane(chartPanel);
    }

    private static Chart createChart() {
        DefaultValueDataset dataset = new DefaultValueDataset(37.2);
        ThermometerPlot plot = new ThermometerPlot(dataset);
        Chart chart = new Chart("ThermometerDemo2", plot);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }

    static void main() {
        ThermometerDemo2 demo = new ThermometerDemo2("JFreeChart: ThermometerDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}