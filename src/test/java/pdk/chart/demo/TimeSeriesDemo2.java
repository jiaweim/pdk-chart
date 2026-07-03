package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.time.Quarter;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.ValueMarker;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;


public class TimeSeriesDemo2 extends ApplicationFrame {
    public TimeSeriesDemo2(String title) {
        super(title);
        XYDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        TimeSeries series = new TimeSeries("Quarterly Data");
        series.add(new Quarter(1, 2001), 500.2);
        series.add(new Quarter(2, 2001), 694.1);
        series.add(new Quarter(3, 2001), 734.4);
        series.add(new Quarter(4, 2001), 453.2);
        series.add(new Quarter(1, 2002), 500.2);
        series.add(new Quarter(2, 2002), null);
        series.add(new Quarter(3, 2002), 734.4);
        series.add(new Quarter(4, 2002), 453.2);
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        return dataset;
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.timeLine(dataset, "Time", "Value", "Time Series Demo 2");
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.addRangeMarker(new ValueMarker(550.0));
        Quarter q = new Quarter(2, 2002);
        plot.addDomainMarker(new ValueMarker(q.getMiddleMillisecond()));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        TimeSeriesDemo2 demo = new TimeSeriesDemo2("Time Series Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
