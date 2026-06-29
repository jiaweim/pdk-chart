package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.SimpleHistogramBin;
import pdk.chart.data.statistics.SimpleHistogramDataset;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class HistogramDemo2 extends ApplicationFrame {
    public HistogramDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static IntervalXYDataset createDataset() {
        SimpleHistogramDataset dataset = new SimpleHistogramDataset("Series 1");
        SimpleHistogramBin bin1 = new SimpleHistogramBin((double) 0.0F, (double) 1.0F, true, false);
        SimpleHistogramBin bin2 = new SimpleHistogramBin((double) 1.0F, (double) 2.0F, true, false);
        SimpleHistogramBin bin3 = new SimpleHistogramBin((double) 2.0F, (double) 3.0F, true, false);
        SimpleHistogramBin bin4 = new SimpleHistogramBin((double) 3.0F, (double) 4.0F, true, true);
        bin1.setItemCount(1);
        bin2.setItemCount(10);
        bin3.setItemCount(15);
        bin4.setItemCount(20);
        dataset.addBin(bin1);
        dataset.addBin(bin2);
        dataset.addBin(bin3);
        dataset.addBin(bin4);
        return dataset;
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = ChartFactory.histogram("HistogramDemo2", (String) null, (String) null, dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setForegroundAlpha(0.85F);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        HistogramDemo2 demo = new HistogramDemo2("Chart: HistogramDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
