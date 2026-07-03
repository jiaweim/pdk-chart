package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.annotations.XYAnnotation;
import pdk.chart.annotations.XYDrawableAnnotation;
import pdk.chart.annotations.XYPointerAnnotation;
import pdk.chart.api.LengthAdjustmentType;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.api.RectangleEdge;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.Hour;
import pdk.chart.data.time.Minute;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.Marker;
import pdk.chart.plot.ValueMarker;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;

public class MarkerDemo1 extends ApplicationFrame {

    public MarkerDemo1(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset<String> data) {
        Chart chart = JChart.scatter(data, "Time", "Y", "Marker Demo 1");
        chart.getLegend(0).position(RectangleEdge.RIGHT);

        XYPlot plot = chart.getXYPlot();
        plot.domainPannable(true)
                .rangePannable(true);

        plot.getDomainAxisAsDate()
                .upperMargin(0.5);
        plot.getRangeAxisAsNumber()
                .lowerMargin(0.5)
                .upperMargin(0.3);

        Marker start = new ValueMarker(200.0);
        start.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        start.setPaint(Color.GREEN);
        start.setLabel("Bid Start Price");
        start.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        start.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(start);

        Marker target = new ValueMarker(175.0);
        target.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        target.setPaint(Color.RED);
        target.setLabel("Target Price");
        target.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        target.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);
        plot.addRangeMarker(target);

        Hour hour = new Hour(2, new Day(22, 5, 2003));
        double millis = (double) hour.getFirstMillisecond();
        Marker originalEnd = new ValueMarker(millis);
        originalEnd.setPaint(Color.orange);
        originalEnd.setLabel("Original Close (02:00)");
        originalEnd.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        originalEnd.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addDomainMarker(originalEnd);

        Minute min = new Minute(15, hour);
        millis = (double) min.getFirstMillisecond();
        Marker currentEnd = new ValueMarker(millis);
        currentEnd.setPaint(Color.RED);
        currentEnd.setLabel("Close Date (02:15)");
        currentEnd.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        currentEnd.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        plot.addDomainMarker(currentEnd);
        Hour h = new Hour(2, new Day(22, 5, 2003));
        Minute m = new Minute(10, h);
        millis = (double) m.getFirstMillisecond();
        CircleDrawer cd = new CircleDrawer(Color.RED, new BasicStroke(1.0F), null);
        XYAnnotation bestBid = new XYDrawableAnnotation(millis, 163.0, 11.0, 11.0, cd);
        plot.addAnnotation(bestBid);

        XYPointerAnnotation pointer = new XYPointerAnnotation("Best Bid", millis, 163.0, 2.356194490192345);
        pointer.setBaseRadius(35.0);
        pointer.setTipRadius(10.0);
        pointer.setFont(new Font("SansSerif", Font.PLAIN, 9));
        pointer.setPaint(Color.BLUE);
        pointer.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
        plot.addAnnotation(pointer);

        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset<String> createDataset() {
        return Data.<String>time()
                .addSeries(createSupplier1Bids())
                .addSeries(createSupplier2Bids()).build();
    }

    private static TimeSeries<String> createSupplier1Bids() {
        Hour hour = new Hour(1, new Day(22, 5, 2003));
        TimeSeries<String> series1 = new TimeSeries<>("Supplier 1");
        series1.add(new Minute(13, hour), 200.0);
        series1.add(new Minute(14, hour), 195.0);
        series1.add(new Minute(45, hour), 190.0);
        series1.add(new Minute(46, hour), 188.0);
        series1.add(new Minute(47, hour), 185.0);
        series1.add(new Minute(52, hour), 180.0);
        return series1;
    }

    private static TimeSeries<String> createSupplier2Bids() {
        Hour hour1 = new Hour(1, new Day(22, 5, 2003));
        Hour hour2 = (Hour) hour1.next();
        TimeSeries<String> series2 = new TimeSeries<>("Supplier 2");
        series2.add(new Minute(25, hour1), 185.0);
        series2.add(new Minute(0, hour2), 175.0);
        series2.add(new Minute(5, hour2), 170.0);
        series2.add(new Minute(6, hour2), 168.0);
        series2.add(new Minute(9, hour2), 165.0);
        series2.add(new Minute(10, hour2), 163.0);
        return series2;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        MarkerDemo1 demo = new MarkerDemo1("MarkerDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
