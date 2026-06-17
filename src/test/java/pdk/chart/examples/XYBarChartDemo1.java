package pdk.chart.examples;

import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.fluent.AxisType;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.title.TextTitle;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * A bar chart created using data from a TimeSeriesCollection. The chart uses a subtitle to cite the data source.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 2:10 PM
 */
public class XYBarChartDemo1 {

    private static IntervalXYDataset<String> createDataset() {
        TimeSeries<String> t1 = new TimeSeries<>("Executions");
        try {
            t1.add(new Year(1976), 0.0);
            t1.add(new Year(1977), 1.0);
            t1.add(new Year(1978), 0.0);
            t1.add(new Year(1979), 2.0);
            t1.add(new Year(1980), 0.0);
            t1.add(new Year(1981), 1.0);
            t1.add(new Year(1982), 2.0);
            t1.add(new Year(1983), 5.0);
            t1.add(new Year(1984), 21.0);
            t1.add(new Year(1985), 18.0);
            t1.add(new Year(1986), 18.0);
            t1.add(new Year(1987), 25.0);
            t1.add(new Year(1988), 11.0);
            t1.add(new Year(1989), 16.0);
            t1.add(new Year(1990), 23.0);
            t1.add(new Year(1991), 14.0);
            t1.add(new Year(1992), 31.0);
            t1.add(new Year(1993), 38.0);
            t1.add(new Year(1994), 31.0);
            t1.add(new Year(1995), 56.0);
            t1.add(new Year(1996), 45.0);
            t1.add(new Year(1997), 74.0);
            t1.add(new Year(1998), 68.0);
            t1.add(new Year(1999), 98.0);
            t1.add(new Year(2000), 85.0);
            t1.add(new Year(2001), 66.0);
            t1.add(new Year(2002), 71.0);
            t1.add(new Year(2003), 65.0);
            t1.add(new Year(2004), 59.0);
            t1.add(new Year(2005), 60.0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        TimeSeriesCollection<String> tsc = new TimeSeriesCollection<>(t1);
        return tsc;
    }

    static void main() {
        XYChart.create(AxisType.DATE, AxisType.NUMBER)
                .title("State Executions - USA")
                .axisNames("Year", "Number of People")
                .dataset(createDataset(), XYChartType.BAR)
                .showLegend(true)
                .addTitle(new TextTitle("Source: http://www.amnestyusa.org/abolish/listbyyear.do",
                        new Font("Dialog", Font.ITALIC, 10)))

                .barRenderer(0)
                .defaultToolTipGenerator(new StandardXYToolTipGenerator("{1} = {2}",
                        new SimpleDateFormat("yyyy"), new DecimalFormat("0")))
                .margin(0.1)
                .done()

                .domainAxisDate()
                .tickMarkPosition(DateTickMarkPosition.MIDDLE)
                .lowerMargin(0.01)
                .upperMargin(0.01)
                .doneXY()

                .show(500, 270);
    }
}
