package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.YIntervalSeries;
import pdk.chart.data.xy.YIntervalSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.YIntervalRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;


public class YIntervalChartDemo1 extends ApplicationFrame {
    public YIntervalChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = JChart.scatter(dataset, "X", "Y",
                "Y Interval Chart Demo 1");
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRenderer(new YIntervalRenderer());
        return chart;
    }

    private static IntervalXYDataset createDataset() {
        double y = 100.0F;
        YIntervalSeries series1 = new YIntervalSeries("Series 1");

        for (int i = 0; i < 100; ++i) {
            y += Math.random() - 0.49;
            series1.add(i, y, y - 3.0, y + 3.0);
        }

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        YIntervalChartDemo1 demo = new YIntervalChartDemo1("Chart: YIntervalChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
