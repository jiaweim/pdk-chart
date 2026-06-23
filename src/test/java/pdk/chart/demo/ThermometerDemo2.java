package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.api.UnitType;
import pdk.chart.data.general.DefaultValueDataset;
import pdk.chart.fluent.ThermometerChart;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

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
        ThermometerChart chart = new ThermometerChart();
        chart.title("ThermometerDemo2")
                .dataset(dataset)
                .plotBackgroundPaint(Color.LIGHT_GRAY);
        chart.padding(new RectangleInsets(UnitType.RELATIVE,0.1, 0.1, 0.1, 0.1))
                .bulbRadius(80);
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