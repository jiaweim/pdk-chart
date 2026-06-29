package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.DateAxis;
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
import java.text.SimpleDateFormat;

public class TimeSeriesDemo15 extends ApplicationFrame {
    public TimeSeriesDemo15(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.timeLine("Bug Report Submissions for Java", "Date", "Evaluation ID", dataset, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultShapesVisible(true);
            renderer.setDefaultShapesFilled(true);
            renderer.setUseFillPaint(true);
            renderer.setDefaultFillPaint(Color.WHITE);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("Bugs");
        s1.add(new Day(27, 6, 2005), (double) 478474.0F);
        s1.add(new Day(24, 1, 2006), (double) 633804.0F);
        s1.add(new Day(28, 4, 2006), (double) 694096.0F);
        s1.add(new Day(12, 5, 2006), (double) 704680.0F);
        s1.add(new Day(16, 5, 2006), (double) 709599.0F);
        s1.add(new Day(21, 6, 2006), (double) 734754.0F);
        s1.add(new Day(27, 7, 2006), (double) 760008.0F);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        TimeSeriesDemo15 demo = new TimeSeriesDemo15("Time Series Demo 15");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
