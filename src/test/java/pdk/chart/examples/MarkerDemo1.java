package pdk.chart.examples;

import pdk.chart.annotations.XYAnnotation;
import pdk.chart.annotations.XYDrawableAnnotation;
import pdk.chart.annotations.XYPointerAnnotation;
import pdk.chart.api.LengthAdjustmentType;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.api.RectangleEdge;
import pdk.chart.data.time.*;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.Marker;
import pdk.chart.plot.ValueMarker;
import pdk.chart.text.TextAnchor;

import java.awt.*;

public class MarkerDemo1 {

    private static XYDataset createDataset() {
        TimeSeriesCollection result = new TimeSeriesCollection();
        result.addSeries(createSupplier1Bids());
        result.addSeries(createSupplier2Bids());
        return result;
    }

    private static TimeSeries createSupplier1Bids() {
        Hour hour = new Hour(1, new Day(22, 5, 2003));
        TimeSeries series1 = new TimeSeries("Supplier 1");
        series1.add(new Minute(13, hour), 200.0);
        series1.add(new Minute(14, hour), 195.0);
        series1.add(new Minute(45, hour), 190.0);
        series1.add(new Minute(46, hour), 188.0);
        series1.add(new Minute(47, hour), 185.0);
        series1.add(new Minute(52, hour), 180.0);
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

    static void main() {

        Marker start = new ValueMarker(200.0);
        start.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        start.setPaint(Color.GREEN);
        start.setLabel("Bid Start Price");
        start.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        start.setLabelTextAnchor(TextAnchor.TOP_RIGHT);

        Marker target = new ValueMarker(175.0);
        target.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        target.setPaint(Color.RED);
        target.setLabel("Target Price");
        target.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        target.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);

        Hour hour = new Hour(2, new Day(22, 5, 2003));
        double millis = (double) hour.getFirstMillisecond();
        Marker originalEnd = new ValueMarker(millis);
        originalEnd.setPaint(Color.orange);
        originalEnd.setLabel("Original Close (02:00)");
        originalEnd.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        originalEnd.setLabelTextAnchor(TextAnchor.TOP_RIGHT);

        Minute min = new Minute(15, hour);
        millis = (double) min.getFirstMillisecond();
        Marker currentEnd = new ValueMarker(millis);
        currentEnd.setPaint(Color.RED);
        currentEnd.setLabel("Close Date (02:15)");
        currentEnd.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        currentEnd.setLabelTextAnchor(TextAnchor.TOP_LEFT);

        Hour h = new Hour(2, new Day(22, 5, 2003));
        Minute m = new Minute(10, h);
        millis = (double) m.getFirstMillisecond();
        CircleDrawer cd = new CircleDrawer(Color.RED, new BasicStroke(1.0F), null);
        XYAnnotation bestBid = new XYDrawableAnnotation(millis, 163.0F, 11.0F, 11.0F, cd);

        XYPointerAnnotation pointer = new XYPointerAnnotation("Best Bid", millis, 163.0, 2.356194490192345);
        pointer.setBaseRadius(35.0);
        pointer.setTipRadius(10.0);
        pointer.setFont(new Font("SansSerif", Font.PLAIN, 9));
        pointer.setPaint(Color.BLUE);
        pointer.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);

        XYChart.create(true)
                .dataset(createDataset(), XYChartType.SCATTER)
                .title("Marker Demo 1")
                .showLegend(true)
                .axisNames("Time", "Y")
                .domainPannable(true)
                .rangePannable(true)
                .addRangeMarker(start)
                .addRangeMarker(target)
                .addDomainMarker(originalEnd)
                .addDomainMarker(currentEnd)
                .addAnnotation(bestBid)
                .addAnnotation(pointer)

                .legend()
                .position(RectangleEdge.RIGHT)
                .doneXY()

                .lineAndShapeRenderer(0)
                .defaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance())
                .done()

                .dateDomainAxis()
                .upperMargin(0.5)
                .doneXY()

                .rangeAxis()
                .lowerMargin(0.5)
                .upperMargin(0.3)
                .doneXY()

                .show(500, 270);
    }
}
