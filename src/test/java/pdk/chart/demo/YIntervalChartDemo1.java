package pdk.chart.demo;

import java.awt.Dimension;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;

import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.YIntervalSeries;
import pdk.chart.data.xy.YIntervalSeriesCollection;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.YIntervalRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;


public class YIntervalChartDemo1 extends ApplicationFrame {
    public YIntervalChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = ChartFactory.scatter("Y Interval Chart Demo 1", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRenderer(new YIntervalRenderer());
        return chart;
    }

    private static IntervalXYDataset createDataset() {
        double y = (double)100.0F;
        YIntervalSeries series1 = new YIntervalSeries("Series 1");

        for(int i = 0; i < 100; ++i) {
            y += Math.random() - 0.49;
            series1.add((double)i, y, y - (double)3.0F, y + (double)3.0F);
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
