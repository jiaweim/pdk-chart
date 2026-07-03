package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.SimpleHistogramBin;
import pdk.chart.data.statistics.SimpleHistogramDataset;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 30 Jun 2026, 1:14 PM
 */
public class HistogramDemo2 extends ApplicationFrame {
    public HistogramDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static IntervalXYDataset<String> createDataset() {
        SimpleHistogramDataset<String> dataset = new SimpleHistogramDataset<>("Series 1");
        SimpleHistogramBin bin1 = new SimpleHistogramBin(0.0, 1.0, true, false);
        SimpleHistogramBin bin2 = new SimpleHistogramBin(1.0, 2.0, true, false);
        SimpleHistogramBin bin3 = new SimpleHistogramBin(2.0, 3.0, true, false);
        SimpleHistogramBin bin4 = new SimpleHistogramBin(3.0, 4.0, true, true);
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

    private static Chart createChart(IntervalXYDataset<String> dataset) {
        Chart chart = JChart.histogram(dataset, null, null, "HistogramDemo2",
                PlotOrientation.VERTICAL, true, true);

        XYPlot plot = chart.getXYPlot();
        plot.foregroundAlpha(0.85f)
                .domainPannable(true)
                .rangePannable(true);
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        plot.getBarRenderer()
                .drawBarOutline(false);

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        HistogramDemo2 demo = new HistogramDemo2("HistogramDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
