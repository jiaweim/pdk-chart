package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.YIntervalSeries;
import pdk.chart.data.xy.YIntervalSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.DeviationRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class DeviationRendererDemo1 extends ApplicationFrame {
    public static XYDataset createDataset() {
        YIntervalSeries series1 = new YIntervalSeries("Series 1");
        YIntervalSeries series2 = new YIntervalSeries("Series 2");
        double y1 = (double) 100.0F;
        double y2 = (double) 100.0F;

        for (int i = 0; i <= 100; ++i) {
            y1 = y1 + Math.random() - 0.48;
            double dev1 = 0.05 * (double) i;
            series1.add((double) i, y1, y1 - dev1, y1 + dev1);
            y2 = y2 + Math.random() - (double) 0.5F;
            double dev2 = 0.07 * (double) i;
            series2.add((double) i, y2, y2 - dev2, y2 + dev2);
        }

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    public static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.line("DeviationRendererDemo1", "X", "Y", dataset);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        DeviationRenderer renderer = new DeviationRenderer(true, false);
        renderer.setSeriesStroke(0, new BasicStroke(3.0F, 1, 1));
        renderer.setSeriesStroke(0, new BasicStroke(3.0F, 1, 1));
        renderer.setSeriesStroke(1, new BasicStroke(3.0F, 1, 1));
        renderer.setSeriesFillPaint(0, new Color(255, 200, 200));
        renderer.setSeriesFillPaint(1, new Color(200, 200, 255));
        plot.setRenderer(renderer);
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
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

    public static void main(String[] args) {
        DeviationRendererDemo1 demo = new DeviationRendererDemo1("Chart : DeviationRendererDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
