package pdk.chart.examples;

import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.fluent.AxisType;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.text.format.RelativeDateFormat;

import java.text.DecimalFormat;

public class RelativeDateFormatDemo2 {

    private static IntervalXYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("Completion");
        s1.add(new Day(19, 1, 2007), 3343000.0);
        s1.add(new Day(20, 1, 2007), 3420000.0);
        s1.add(new Day(21, 1, 2007), 3515000.0);
        s1.add(new Day(22, 1, 2007), 3315000.0);
        s1.add(new Day(23, 1, 2007), 3490000.0);
        s1.add(new Day(24, 1, 2007), 3556000.0);
        s1.add(new Day(25, 1, 2007), 3383000.0);
        s1.add(new Day(26, 1, 2007), 3575000.0);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    static void main() {
        RelativeDateFormat rdf = new RelativeDateFormat();
        rdf.setShowZeroDays(false);
        rdf.setSecondFormatter(new DecimalFormat("00"));

        XYChart.create(AxisType.DATE, AxisType.DATE)
                .title("RelativeDateFormat Demo 2")
                .addDataset(createDataset(), XYChartType.BAR)
                .axisNames("Date", "Time To Complete")
                .showLegend(true)
                .domainCrosshairVisible(true)
                .rangeCrosshairVisible(true)

                .barProps(0)
                .addTooltips(true)
                .drawBarOutline(false)
                .done()

                .rangeAxisDate()
                .dateFormatOverride(rdf)
                .doneXY()

                .show(500, 270);
    }
}
