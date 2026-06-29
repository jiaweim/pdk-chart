package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.HistogramDataset;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StandardXYBarPainter;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class HistogramDemo1 extends ApplicationFrame {

    public HistogramDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static IntervalXYDataset createDataset() {
        HistogramDataset dataset = new HistogramDataset();
        double[] values = new double[1000];
        Random generator = new Random(12345678L);

        for (int i = 0; i < 1000; ++i) {
            values[i] = generator.nextGaussian() + (double) 5.0F;
        }

        dataset.addSeries("H1", values, 100, 2.0, 8.0);
        values = new double[1000];

        for (int i = 0; i < 1000; ++i) {
            values[i] = generator.nextGaussian() + 7.0;
        }

        dataset.addSeries("H2", values, 100, 4.0, 10.0);
        return dataset;
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = ChartFactory.histogram("Histogram Demo 1", null, null,
                dataset, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setForegroundAlpha(0.85F);
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setBarPainter(new StandardXYBarPainter());
        renderer.setShadowVisible(false);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() throws IOException {
        HistogramDemo1 demo = new HistogramDemo1("HistogramDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
