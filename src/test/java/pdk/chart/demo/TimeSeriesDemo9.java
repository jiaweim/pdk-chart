package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class TimeSeriesDemo9 extends ApplicationFrame {
    public TimeSeriesDemo9(String title) {
        super(title);
        XYDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.createTimeSeriesChart("Time Series Demo 9", "Date", "Price Per Unit", dataset, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultShapesVisible(true);
            renderer.setDefaultShapesFilled(true);
            renderer.setSeriesShape(0, new Ellipse2D.Double((double) -3.0F, (double) -3.0F, (double) 6.0F, (double) 6.0F));
            renderer.setSeriesShape(1, new Rectangle2D.Double((double) -3.0F, (double) -3.0F, (double) 6.0F, (double) 6.0F));
            GeneralPath s2 = new GeneralPath();
            s2.moveTo(0.0F, -3.0F);
            s2.lineTo(3.0F, 3.0F);
            s2.lineTo(-3.0F, 3.0F);
            s2.closePath();
            renderer.setSeriesShape(2, s2);
            GeneralPath s3 = new GeneralPath();
            s3.moveTo(-1.0F, -3.0F);
            s3.lineTo(1.0F, -3.0F);
            s3.lineTo(1.0F, -1.0F);
            s3.lineTo(3.0F, -1.0F);
            s3.lineTo(3.0F, 1.0F);
            s3.lineTo(1.0F, 1.0F);
            s3.lineTo(1.0F, 3.0F);
            s3.lineTo(-1.0F, 3.0F);
            s3.lineTo(-1.0F, 1.0F);
            s3.lineTo(-3.0F, 1.0F);
            s3.lineTo(-3.0F, -1.0F);
            s3.lineTo(-1.0F, -1.0F);
            s3.closePath();
            renderer.setSeriesShape(3, s3);
        }

        plot.getDomainAxis().setVisible(false);
        plot.getRangeAxis().setVisible(false);
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();

        for (int i = 0; i < 4; ++i) {
            dataset.addSeries(createTimeSeries(i, 10));
        }

        return dataset;
    }

    private static TimeSeries createTimeSeries(int series, int count) {
        TimeSeries result = new TimeSeries("Series " + series);
        Day start = new Day();

        for (int i = 0; i < count; ++i) {
            result.add(start, Math.random());
            start = (Day) start.next();
        }

        return result;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        TimeSeriesDemo9 demo = new TimeSeriesDemo9("Time Series Demo 9");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
