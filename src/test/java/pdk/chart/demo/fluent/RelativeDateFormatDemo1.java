package pdk.chart.demo.fluent;

import pdk.chart.data.time.Minute;
import pdk.chart.data.time.Second;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.fluent.AxisType;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.text.format.RelativeDateFormat;

import java.text.DecimalFormat;

/**
 * A time series chart where the time axis displays a relative date (that is, the elapsed time in hours, minutes and seconds).
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 18 Jun 2026, 10:18 AM
 */
public class RelativeDateFormatDemo1 {

    private static XYDataset createDataset() {
        TimeSeries<String> s1 = new TimeSeries<>("Heart Rate");
        s1.add(new Second[]{
                new Second(45, 6, 9, 1, 10, 2006),
                new Second(33, 8, 9, 1, 10, 2006),
                new Second(10, 10, 9, 1, 10, 2006),
                new Second(19, 12, 9, 1, 10, 2006),
                new Second(5, 15, 9, 1, 10, 2006),
                new Second(12, 16, 9, 1, 10, 2006),
                new Second(6, 18, 9, 1, 10, 2006),
                new Second(11, 20, 9, 1, 10, 2006),
        }, new double[]{143.0, 167.0, 189.0, 156.0, 176.0, 183.0, 138.0, 102.0});

        TimeSeriesCollection<String> dataset = new TimeSeriesCollection<>();
        dataset.addSeries(s1);
        return dataset;
    }

    static void main() {
        Minute base = new Minute(0, 9, 1, 10, 2006);
        RelativeDateFormat rdf = new RelativeDateFormat(base.getFirstMillisecond());
        rdf.setSecondFormatter(new DecimalFormat("00"));

        XYChart.create(AxisType.DATE, AxisType.NUMBER)
                .addDataset(createDataset(), XYChartType.LINE)
                .title("Exercise Chart")
                .axisNames("Elapsed Time", "Beats Per Minute")
                .showLegend(true)
                .domainCrosshairVisible(true)
                .rangeCrosshairVisible(true)

                .domainAxisDate()
                .dateFormatOverride(rdf)
                .doneXY()

                .rangeAxis()
                .autoRangeIncludesZero(false)
                .done()

                .lineAndShapeProps(0)
                .addTooltips(true)
                .defaultShapesVisible(true)
                .defaultShapesFilled(true)
                .done()

                .show(500, 270);
    }
}
