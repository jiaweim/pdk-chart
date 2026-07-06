package pdk.chart.demo;

import pdk.chart.AxisType;
import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * In this demo, the domain axis shows only integer values.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 17 Jun 2026, 9:41 AM
 */
public class XYBarChartDemo4 extends ApplicationFrame {
    public XYBarChartDemo4(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset<String> dataset) {
        Chart chart = JChart.bar(dataset, "X", AxisType.NUMBER,
                "Y", "XYBarChartDemo4",
                PlotOrientation.VERTICAL, true, false);
        XYPlot plot = chart.getXYPlot();
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }

    private static IntervalXYDataset<String> createDataset() {
        return Data.createIntervalXY("Series 1",
                new double[]{1.0, 2.0, 3.0},
                new double[]{5.0, 70.8, 48.3},
                0.9);
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    static void main() {
        XYBarChartDemo4 demo = new XYBarChartDemo4("XY Bar Chart Demo 4");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
