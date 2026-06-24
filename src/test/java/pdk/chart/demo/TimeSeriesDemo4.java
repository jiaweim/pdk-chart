package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
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
        series.add(new Hour(8, today), (Number)null);
        series.add(new Hour(12, today), 734.4);
        series.add(new Hour(16, today), 453.2);
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        return dataset;
    }

    private static Chart createChart(XYDataset dataset) {
        String chartTitle = "₢₢₣₤₥₦₧₨₩₪";
        Chart chart = ChartFactory.createTimeSeriesChart(chartTitle, "Time", "Value", dataset, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setInsets(new RectangleInsets(0.0F, (double)0.0F, (double)0.0F, (double)20.0F));
        Marker marker = new ValueMarker(700.0F);
        marker.setPaint(Color.BLUE);
        marker.setAlpha(0.8F);
        plot.addRangeMarker(marker);
        plot.setBackgroundPaint((Paint)null);
        plot.setBackgroundImage(ChartDemo.getTestImage());
        plot.getDomainAxis().setLowerMargin((double)0.0F);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        TimeSeriesDemo4 demo = new TimeSeriesDemo4("Time Series Demo 4");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
