package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class XYLineAndShapeRendererDemo1 extends ApplicationFrame {
    public XYLineAndShapeRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.line("XYLineAndShapeRenderer Demo 1", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesVisible(1, true);
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        renderer.setDefaultEntityRadius(6);
        plot.setRenderer(renderer);
        return chart;
    }

    private static XYDataset createDataset() {
        XYSeries series1 = new XYSeries("Series 1");
        series1.add((double) 1.0F, 3.3);
        series1.add((double) 2.0F, 4.4);
        series1.add((double) 3.0F, 1.7);
        XYSeries series2 = new XYSeries("Series 2");
        series2.add((double) 1.0F, 7.3);
        series2.add((double) 2.0F, (double) 0.0F);
        series2.add((double) 3.0F, 9.6);
        series2.add((double) 4.0F, 5.6);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        XYLineAndShapeRendererDemo1 demo = new XYLineAndShapeRendererDemo1("Chart: XYLineAndShapeRendererDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
