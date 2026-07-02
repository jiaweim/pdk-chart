package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.JChart;
import pdk.chart.api.Layer;
import pdk.chart.api.LengthAdjustmentType;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.axis.NumberTickUnitSource;
import pdk.chart.axis.PeriodAxis;
import pdk.chart.axis.PeriodAxisLabelInfo;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.Hour;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.IntervalMarker;
import pdk.chart.plot.Marker;
import pdk.chart.plot.ValueMarker;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class MarkerDemo2 extends ApplicationFrame {
    public MarkerDemo2(String title) {
        super(title);
        XYDataset data = createDataset();
        Chart chart = createChart(data);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset data) {
        Chart chart = JChart.line(data, "X", "Temperature", "Marker Demo 2");
        XYPlot plot = (XYPlot) chart.getPlot();
        PeriodAxis domainAxis = new PeriodAxis(null, new Hour(0, 30, 6, 2005), new Hour(23, 30, 6, 2005));
        PeriodAxisLabelInfo[] info = new PeriodAxisLabelInfo[2];
        info[0] = new PeriodAxisLabelInfo(Hour.class, new SimpleDateFormat("HH"));
        info[1] = new PeriodAxisLabelInfo(Day.class, new SimpleDateFormat("dd-MMM"));
        domainAxis.setLabelInfo(info);
        plot.setDomainAxis(domainAxis);
        ChartUtils.applyCurrentTheme(chart);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlineStroke(new BasicStroke(1.0F));
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlineStroke(new BasicStroke(1.0F));
        plot.setRangeTickBandPaint(new Color(240, 240, 240));
        ValueAxis yAxis = plot.getRangeAxis();
        NumberTickUnitSource tickUnitSource = new NumberTickUnitSource();
        yAxis.setStandardTickUnits(tickUnitSource);
        yAxis.setRange(0.0, 100.0);
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2.0F));
        Marker threshold = new ValueMarker(80.0F);
        threshold.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        threshold.setPaint(Color.RED);
        threshold.setStroke(new BasicStroke(2.0F));
        threshold.setLabel("Temperature Threshold");
        threshold.setLabelFont(new Font("SansSerif", 0, 11));
        threshold.setLabelPaint(Color.RED);
        threshold.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        threshold.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
        plot.addRangeMarker(threshold);
        Hour hour1 = new Hour(18, 30, 6, 2005);
        Hour hour2 = new Hour(20, 30, 6, 2005);
        double millis1 = (double) hour1.getFirstMillisecond();
        double millis2 = (double) hour2.getFirstMillisecond();
        Marker cooling = new IntervalMarker(millis1, millis2);
        cooling.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        cooling.setPaint(new Color(150, 150, 255));
        cooling.setLabel("Automatic Cooling");
        cooling.setLabelFont(new Font("SansSerif", 0, 11));
        cooling.setLabelPaint(Color.BLUE);
        cooling.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        cooling.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addDomainMarker(cooling, Layer.BACKGROUND);
        Marker coolingStart = new ValueMarker(millis1, Color.BLUE, new BasicStroke(2.0F));
        Marker coolingEnd = new ValueMarker(millis2, Color.BLUE, new BasicStroke(2.0F));
        plot.addDomainMarker(coolingStart, Layer.BACKGROUND);
        plot.addDomainMarker(coolingEnd, Layer.BACKGROUND);
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeriesCollection result = new TimeSeriesCollection();
        TimeSeries series1 = new TimeSeries("Temperature");
        series1.add(new Hour(0, 30, 6, 2005), 45.3);
        series1.add(new Hour(1, 30, 6, 2005), 48.9);
        series1.add(new Hour(2, 30, 6, 2005), 52.1);
        series1.add(new Hour(3, 30, 6, 2005), 44.8);
        series1.add(new Hour(4, 30, 6, 2005), 49.9);
        series1.add(new Hour(5, 30, 6, 2005), (double) 55.5F);
        series1.add(new Hour(6, 30, 6, 2005), 58.2);
        series1.add(new Hour(7, 30, 6, 2005), 58.1);
        series1.add(new Hour(8, 30, 6, 2005), 63.7);
        series1.add(new Hour(9, 30, 6, 2005), 66.3);
        series1.add(new Hour(10, 30, 6, 2005), 69.8);
        series1.add(new Hour(11, 30, 6, 2005), 70.1);
        series1.add(new Hour(12, 30, 6, 2005), 72.4);
        series1.add(new Hour(13, 30, 6, 2005), 69.7);
        series1.add(new Hour(14, 30, 6, 2005), 68.6);
        series1.add(new Hour(15, 30, 6, 2005), 70.9);
        series1.add(new Hour(16, 30, 6, 2005), 73.4);
        series1.add(new Hour(17, 30, 6, 2005), (double) 77.5F);
        series1.add(new Hour(18, 30, 6, 2005), 82.9);
        series1.add(new Hour(19, 30, 6, 2005), 62.1);
        series1.add(new Hour(20, 30, 6, 2005), 37.3);
        series1.add(new Hour(21, 30, 6, 2005), 40.7);
        series1.add(new Hour(22, 30, 6, 2005), 44.2);
        series1.add(new Hour(23, 30, 6, 2005), 49.8);
        result.addSeries(series1);
        return result;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        MarkerDemo2 demo = new MarkerDemo2("Chart: MarkerDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
