package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.annotations.XYAnnotation;
import pdk.chart.annotations.XYDrawableAnnotation;
import pdk.chart.annotations.XYPointerAnnotation;
import pdk.chart.api.LengthAdjustmentType;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.time.*;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.legend.LegendTitle;
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

    private static Chart createChart(XYDataset data) {
        Chart chart = ChartFactory.createScatterPlot("Marker Demo 1", "X", "Y", data);
        LegendTitle legend = (LegendTitle) chart.getSubtitle(0);
        legend.setPosition(RectangleEdge.RIGHT);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.getRenderer().setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        DateAxis domainAxis = new DateAxis("Time");
        domainAxis.setUpperMargin((double) 0.5F);
        plot.setDomainAxis(domainAxis);
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setUpperMargin(0.3);
        rangeAxis.setLowerMargin((double) 0.5F);
        Marker start = new ValueMarker((double) 200.0F);
        start.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        start.setPaint(Color.GREEN);
        start.setLabel("Bid Start Price");
        start.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        start.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(start);
        Marker target = new ValueMarker((double) 175.0F);
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
        CircleDrawer cd = new CircleDrawer(Color.RED, new BasicStroke(1.0F), (Paint) null);
        XYAnnotation bestBid = new XYDrawableAnnotation(millis, (double) 163.0F, (double) 11.0F, (double) 11.0F, cd);
        plot.addAnnotation(bestBid);
        XYPointerAnnotation pointer = new XYPointerAnnotation("Best Bid", millis, (double) 163.0F, 2.356194490192345);
        pointer.setBaseRadius((double) 35.0F);
        pointer.setTipRadius((double) 10.0F);
        pointer.setFont(new Font("SansSerif", 0, 9));
        pointer.setPaint(Color.BLUE);
        pointer.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
        plot.addAnnotation(pointer);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeriesCollection result = new TimeSeriesCollection();
        result.addSeries(createSupplier1Bids());
        result.addSeries(createSupplier2Bids());
        return result;
    }

    private static TimeSeries createSupplier1Bids() {
        Hour hour = new Hour(1, new Day(22, 5, 2003));
        TimeSeries series1 = new TimeSeries("Supplier 1");
        series1.add(new Minute(13, hour), (double) 200.0F);
        series1.add(new Minute(14, hour), (double) 195.0F);
        series1.add(new Minute(45, hour), (double) 190.0F);
        series1.add(new Minute(46, hour), (double) 188.0F);
        series1.add(new Minute(47, hour), (double) 185.0F);
        series1.add(new Minute(52, hour), (double) 180.0F);
        return series1;
    }

    private static TimeSeries createSupplier2Bids() {
        Hour hour1 = new Hour(1, new Day(22, 5, 2003));
        Hour hour2 = (Hour) hour1.next();
        TimeSeries series2 = new TimeSeries("Supplier 2");
        series2.add(new Minute(25, hour1), (double) 185.0F);
        series2.add(new Minute(0, hour2), (double) 175.0F);
        series2.add(new Minute(5, hour2), (double) 170.0F);
        series2.add(new Minute(6, hour2), (double) 168.0F);
        series2.add(new Minute(9, hour2), (double) 165.0F);
        series2.add(new Minute(10, hour2), (double) 163.0F);
        return series2;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        MarkerDemo1 demo = new MarkerDemo1("JFreeChart: MarkerDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
