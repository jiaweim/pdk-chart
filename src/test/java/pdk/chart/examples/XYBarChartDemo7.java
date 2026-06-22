package pdk.chart.examples;

import pdk.chart.Chart;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.xy.XYIntervalSeries;
import pdk.chart.data.xy.XYIntervalSeriesCollection;
import pdk.chart.fluent.AxisType;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.plot.PlotOrientation;

import java.awt.*;

/**
 * In this demo, the XYBarRenderer displays some date intervals along the x-axis.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 17 Jun 2026, 10:34 AM
 */
public class XYBarChartDemo7 {

    private static void addItem(XYIntervalSeries<String> s, RegularTimePeriod p0, RegularTimePeriod p1, int index) {
        s.add(index, index - 0.45, index + 0.45, p0.getFirstMillisecond(), p0.getFirstMillisecond(), p1.getLastMillisecond());
    }

    static void main() {
        Day d0 = new Day(12, 6, 2007);
        Day d1 = new Day(13, 6, 2007);
        Day d2 = new Day(14, 6, 2007);
        Day d3 = new Day(15, 6, 2007);
        Day d4 = new Day(16, 6, 2007);
        Day d5 = new Day(17, 6, 2007);

        XYIntervalSeries<String> s1 = new XYIntervalSeries<>("S1");
        XYIntervalSeries<String> s2 = new XYIntervalSeries<>("S2");
        XYIntervalSeries<String> s3 = new XYIntervalSeries<>("S3");

        addItem(s1, d0, d1, 0);
        addItem(s1, d3, d3, 0);
        addItem(s2, d0, d5, 1);
        addItem(s3, d2, d4, 2);

        XYIntervalSeriesCollection<String> dataset = new XYIntervalSeriesCollection<>();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        dataset.addSeries(s3);

        XYChart chart = XYChart.create(AxisType.SYMBOL, AxisType.DATE)
                .title("XYBarChartDemo7")
                .axisNames("Series", "Date")
                .orientation(PlotOrientation.HORIZONTAL)

                .domainAxis()
                .symbols(new String[]{"S1", "S2", "S3"})
                .gridBandsVisible(false)
                .done()

                .dataset(dataset, XYChartType.BAR)
                .showLegend(true)
                .rangePannable(true)
                .plotBackgroundPaint(Color.LIGHT_GRAY)
                .domainGridlinePaint(Color.LIGHT_GRAY)
                .domainGridlinePaint(Color.WHITE)
                .rangeGridlinePaint(Color.WHITE)

                .barProps(0)
                .useYInterval(true)
                .done();

        Chart.DEFAULT_THEME.apply(chart);
        chart.show(500, 300);
    }
}
