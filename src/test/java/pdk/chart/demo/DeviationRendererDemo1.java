package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.DeviationChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.YIntervalSeries;
import pdk.chart.data.xy.YIntervalSeriesCollection;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class DeviationRendererDemo1 extends ApplicationFrame {
    public static XYDataset createDataset() {
        YIntervalSeries series1 = new YIntervalSeries("Series 1");
        YIntervalSeries series2 = new YIntervalSeries("Series 2");
        double y1 = 100.0;
        double y2 = 100.0;

        for (int i = 0; i <= 100; ++i) {
            y1 = y1 + Math.random() - 0.48;
            double dev1 = 0.05 * i;
            series1.add(i, y1, y1 - dev1, y1 + dev1);
            y2 = y2 + Math.random() - 0.5;
            double dev2 = 0.07 * i;
            series2.add(i, y2, y2 - dev2, y2 + dev2);
        }

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    public static Chart createChart(XYDataset dataset) {
        DeviationChart chart = new DeviationChart(dataset, "X", "Y", "DeviationRendererDemo1");
        chart.setDomainPannable(true);
        chart.setSeriesStroke(0, new BasicStroke(3.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        chart.setSeriesStroke(0, new BasicStroke(3.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        chart.setSeriesStroke(1, new BasicStroke(3.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        chart.setSeriesFillPaint(0, new Color(255, 200, 200));
        chart.setSeriesFillPaint(1, new Color(200, 200, 255));
        NumberAxis yAxis = (NumberAxis) chart.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    public DeviationRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        XYDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        DeviationRendererDemo1 demo = new DeviationRendererDemo1("DeviationRendererDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
