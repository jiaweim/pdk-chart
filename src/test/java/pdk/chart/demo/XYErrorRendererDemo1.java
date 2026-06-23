package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.XYIntervalSeries;
import pdk.chart.data.xy.XYIntervalSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYErrorRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class XYErrorRendererDemo1 extends ApplicationFrame {
    public XYErrorRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        NumberAxis yAxis = new NumberAxis("Y");
        XYErrorRenderer renderer = new XYErrorRenderer();
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        Chart chart = new Chart("XYErrorRenderer Demo 1", plot);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset createDataset() {
        XYIntervalSeriesCollection dataset = new XYIntervalSeriesCollection();
        XYIntervalSeries s1 = new XYIntervalSeries("Series 1");
        s1.add((double) 1.0F, (double) 0.5F, (double) 1.5F, (double) 10.0F, (double) 9.0F, (double) 11.0F);
        s1.add((double) 10.0F, 8.7, 11.21, 6.1, 4.34, 7.54);
        s1.add(17.8, (double) 16.0F, 18.9, (double) 4.5F, 3.1, 5.8);
        XYIntervalSeries s2 = new XYIntervalSeries("Series 2");
        s2.add((double) 3.0F, (double) 2.5F, (double) 3.5F, (double) 7.0F, (double) 6.0F, (double) 8.0F);
        s2.add((double) 13.0F, (double) 11.5F, (double) 14.5F, (double) 13.0F, (double) 11.5F, (double) 14.5F);
        s2.add((double) 24.0F, 22.7, 25.21, 16.1, 14.34, 17.54);
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        XYErrorRendererDemo1 demo = new XYErrorRendererDemo1("Chart: XYErrorRendererDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
