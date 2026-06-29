package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.NumberAxis;
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

public class LineChartDemo6 extends ApplicationFrame {
    public LineChartDemo6(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        XYSeries series1 = new XYSeries("First");
        series1.add((double) 1.0F, (double) 1.0F);
        series1.add((double) 2.0F, (double) 4.0F);
        series1.add((double) 3.0F, (double) 3.0F);
        series1.add((double) 4.0F, (double) 5.0F);
        series1.add((double) 5.0F, (double) 5.0F);
        series1.add((double) 6.0F, (double) 7.0F);
        series1.add((double) 7.0F, (double) 7.0F);
        series1.add((double) 8.0F, (double) 8.0F);
        XYSeries series2 = new XYSeries("Second");
        series2.add((double) 1.0F, (double) 5.0F);
        series2.add((double) 2.0F, (double) 7.0F);
        series2.add((double) 3.0F, (double) 6.0F);
        series2.add((double) 4.0F, (double) 8.0F);
        series2.add((double) 5.0F, (double) 4.0F);
        series2.add((double) 6.0F, (double) 4.0F);
        series2.add((double) 7.0F, (double) 2.0F);
        series2.add((double) 8.0F, (double) 1.0F);
        XYSeries series3 = new XYSeries("Third");
        series3.add((double) 3.0F, (double) 4.0F);
        series3.add((double) 4.0F, (double) 3.0F);
        series3.add((double) 5.0F, (double) 2.0F);
        series3.add((double) 6.0F, (double) 3.0F);
        series3.add((double) 7.0F, (double) 6.0F);
        series3.add((double) 8.0F, (double) 3.0F);
        series3.add((double) 9.0F, (double) 4.0F);
        series3.add((double) 10.0F, (double) 3.0F);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        return dataset;
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.line("Line Chart Demo 6", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        plot.setRenderer(renderer);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        LineChartDemo6 demo = new LineChartDemo6("Chart: LineChartDemo6.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
