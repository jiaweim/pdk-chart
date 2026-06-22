package pdk.chart.examples;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.BoxAndWhiskerCalculator;
import pdk.chart.data.statistics.BoxAndWhiskerXYDataset;
import pdk.chart.data.statistics.DefaultBoxAndWhiskerXYDataset;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.fluent.AxisType;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoxAndWhiskerChartDemo2 {

    private static BoxAndWhiskerXYDataset createDataset() {
        int VALUE_COUNT = 20;
        DefaultBoxAndWhiskerXYDataset result = new DefaultBoxAndWhiskerXYDataset("Series Name");
        RegularTimePeriod t = new Day();
        for (int i = 0; i < 10; ++i) {
            List values = createValueList(0.0, 20.0, 20);
            result.add(t.getStart(), BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(values));
            t = t.next();
        }

        return result;
    }

    private static List<Double> createValueList(double lowerBound, double upperBound, int count) {
        List<Double> result = new ArrayList();

        for (int i = 0; i < count; ++i) {
            double v = lowerBound + Math.random() * (upperBound - lowerBound);
            result.add(v);
        }

        return result;
    }

    static void main() {
        XYChart.create(AxisType.DATE, AxisType.NUMBER)
                .dataset(createDataset(), XYChartType.BOX_WHISKER)
                .title("Box-and-Whisker Chart Demo 2")
                .axisNames("Day", "Value")
                .backgroundPaint(Color.WHITE)
                .plotBackgroundPaint(Color.LIGHT_GRAY)
                .domainGridlinePaint(Color.WHITE)
                .domainGridlinesVisible(true)
                .rangeGridlinePaint(Color.WHITE)
                .domainPannable(true)
                .rangePannable(true)

                .rangeAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .done()

                .show(500, 270);
    }
}
