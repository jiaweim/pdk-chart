package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.time.Minute;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class MultipleAxisDemo2 extends ApplicationFrame {
    public MultipleAxisDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(600, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart() {
        XYDataset dataset1 = createDataset("Series 1", (double) 100.0F, new Minute(), 200);
        Chart chart = ChartFactory.timeLine("Multiple Axis Demo 2", "Time of Day", "Primary Range Axis", dataset1, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setOrientation(PlotOrientation.VERTICAL);
        NumberAxis xAxis2 = new NumberAxis("Domain Axis 2");
        xAxis2.setAutoRangeIncludesZero(false);
        plot.setDomainAxis(1, xAxis2);
        NumberAxis yAxis2 = new NumberAxis("Range Axis 2");
        plot.setRangeAxis(1, yAxis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        XYDataset dataset2 = createDataset("Series 2", (double) 1000.0F, new Minute(), 170);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToDomainAxis(1, 1);
        plot.mapDatasetToRangeAxis(1, 1);
        plot.setRenderer(1, new XYLineAndShapeRenderer(true, false));
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset createDataset(String name, double base, RegularTimePeriod start, int count) {
        TimeSeries series = new TimeSeries(name);
        RegularTimePeriod period = start;
        double value = base;

        for (int i = 0; i < count; ++i) {
            series.add(period, value);
            period = period.next();
            value *= (double) 1.0F + (Math.random() - 0.495) / (double) 10.0F;
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        MultipleAxisDemo2 demo = new MultipleAxisDemo2("Chart: MultipleAxisDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
