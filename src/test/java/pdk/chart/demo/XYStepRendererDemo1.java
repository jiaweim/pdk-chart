package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYStepRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class XYStepRendererDemo1 extends ApplicationFrame {
    public XYStepRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.line("XYStepRenderer Demo 1", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYStepRenderer renderer = new XYStepRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setSeriesStroke(0, new BasicStroke(2.0F));
        renderer.setSeriesStroke(1, new BasicStroke(2.0F));
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        renderer.setDefaultEntityRadius(6);
        plot.setRenderer(renderer);
        return chart;
    }

    private static XYDataset createDataset() {
        XYSeries series1 = new XYSeries("Series 1");
        series1.add((double) 1.0F, (double) 3.0F);
        series1.add((double) 2.0F, (double) 4.0F);
        series1.add((double) 3.0F, (double) 2.0F);
        series1.add((double) 6.0F, (double) 3.0F);
        XYSeries series2 = new XYSeries("Series 2");
        series2.add((double) 1.0F, (double) 7.0F);
        series2.add((double) 2.0F, (double) 6.0F);
        series2.add((double) 3.0F, (double) 9.0F);
        series2.add((double) 4.0F, (double) 5.0F);
        series2.add((double) 6.0F, (double) 4.0F);
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
        XYStepRendererDemo1 demo = new XYStepRendererDemo1("Chart: XYStepRendererDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
