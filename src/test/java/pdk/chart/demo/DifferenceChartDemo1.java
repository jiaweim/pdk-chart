package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYDifferenceRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class DifferenceChartDemo1 extends ApplicationFrame {
    public static XYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Random 1");
        TimeSeries series2 = new TimeSeries("Random 2");
        double value1 = (double) 0.0F;
        double value2 = (double) 0.0F;
        Day day = new Day();

        for (int i = 0; i < 200; ++i) {
            value1 = value1 + Math.random() - (double) 0.5F;
            value2 = value2 + Math.random() - (double) 0.5F;
            series1.add(day, value1);
            series2.add(day, value2);
            day = (Day) day.next();
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    public static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.createTimeSeriesChart("Difference Chart Demo 1", "Time", "Value", dataset);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        XYDifferenceRenderer r = new XYDifferenceRenderer(Color.GREEN, Color.RED, false);
        r.setRoundXCoordinates(true);
        plot.setDomainCrosshairLockedOnData(true);
        plot.setRangeCrosshairLockedOnData(true);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.setRenderer(r);
        ValueAxis domainAxis = new DateAxis("Time");
        domainAxis.setLowerMargin((double) 0.0F);
        domainAxis.setUpperMargin((double) 0.0F);
        plot.setDomainAxis(domainAxis);
        plot.setForegroundAlpha(0.5F);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public DifferenceChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        DifferenceChartDemo1 demo = new DifferenceChartDemo1("Chart: DifferenceChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
