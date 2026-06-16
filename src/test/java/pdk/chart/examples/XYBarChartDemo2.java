package pdk.chart.examples;

import pdk.chart.Chart;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;

/**
 * A bar chart created using the ClusteredXYBarRenderer class.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 2:22 PM
 */
public class XYBarChartDemo2 {

    private static IntervalXYDataset<String> createDataset() {
        TimeSeries<String> series1 = new TimeSeries<>("Series 1");
        series1.add(new Day(1, 1, 2003), 54.3);
        series1.add(new Day(2, 1, 2003), 20.3);
        series1.add(new Day(3, 1, 2003), 43.4);
        series1.add(new Day(4, 1, 2003), -12.0);
        TimeSeries<String> series2 = new TimeSeries<>("Series 2");
        series2.add(new Day(1, 1, 2003), 8.0);
        series2.add(new Day(2, 1, 2003), 16.0);
        series2.add(new Day(3, 1, 2003), 21.0);
        series2.add(new Day(4, 1, 2003), 5.0);
        TimeSeriesCollection<String> dataset = new TimeSeriesCollection<>();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    static void main() {
        XYChart chart = XYChart.create(true)
                .title("XY Bar Chart Demo 2")
                .axisNames("Date", "Y")
                .dataset(createDataset(), XYChartType.BAR_CLUSTER)
                .showLegend(true)
                .domainPannable(true)
                .rangePannable(true)

                .barRenderer(0)
                .addTooltips(true)
                .drawBarOutline(false)
                .done();

        Chart.DEFAULT_THEME.apply(chart);
        chart.show(500, 300);
    }
}
