package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.Hour;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.Marker;
import pdk.chart.plot.ValueMarker;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;


public class TimeSeriesDemo4 extends ApplicationFrame {
    public TimeSeriesDemo4(String title) {
        super(title);
        XYDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setMouseZoomable(true);
        this.setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        TimeSeries series = new TimeSeries("Random Data");
        Day today = new Day();
        series.add(new Hour(0, today), 500.2);
        series.add(new Hour(2, today), 694.1);
        series.add(new Hour(3, today), 734.4);
        series.add(new Hour(4, today), 453.2);
        series.add(new Hour(7, today), 500.2);
        series.add(new Hour(8, today), null);
        series.add(new Hour(12, today), 734.4);
        series.add(new Hour(16, today), 453.2);
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        return dataset;
    }

    private static Chart createChart(XYDataset dataset) {
        String chartTitle = "₢₢₣₤₥₦₧₨₩₪";
        Chart chart = JChart.timeLine(dataset, "Time", "Value", chartTitle);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setInsets(new RectangleInsets(0.0, 0.0, 0.0, 20.0));
        Marker marker = new ValueMarker(700.0F);
        marker.setPaint(Color.BLUE);
        marker.setAlpha(0.8F);
        plot.addRangeMarker(marker);
        plot.setBackgroundPaint(null);
        plot.setBackgroundImage(ChartDemo.getTestImage());
        plot.getDomainAxis().setLowerMargin(0.0);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        TimeSeriesDemo4 demo = new TimeSeriesDemo4("Time Series Demo 4");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
