package pdk.chart.examples;

import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.fluent.AxisType;
import pdk.chart.fluent.Data;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.title.TextTitle;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Another demo for the XYBarRenderer class.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 17 Jun 2026, 10:17 AM
 */
public class XYBarChartDemo5 {

    static void main() {
        TimeSeries<String> timeSeries = new TimeSeries<>("Budget");
        timeSeries.add(
                new Year[]{
                        new Year(1980), new Year(1981), new Year(1982), new Year(1983), new Year(1984),
                        new Year(1985), new Year(1986), new Year(1987), new Year(1988), new Year(1989),
                        new Year(1990), new Year(1991), new Year(1992), new Year(1993), new Year(1994),
                        new Year(1995), new Year(1996), new Year(1997), new Year(1998), new Year(1999),
                        new Year(2000), new Year(2001), new Year(2002), new Year(2003), new Year(2004)
                }, new double[]{
                        -74.0, -79.0, -128.0, -208.0, -185.0,
                        -212.0, -221.0, -150.0, -155.0, -153.0,
                        -221.0, -269.0, -290.0, -255.0, -203.0,
                        -164.0, -107.0, -22.0, 69.0, 126.0,
                        236.0, 128.0, -158.0, -378.0, -412.0
                }
        );
        IntervalXYDataset<String> dataset = Data.createIntervalXYDataset(timeSeries);

        TextTitle source = new TextTitle("Source: http://www.cbo.gov/showdoc.cfm?index=1821&sequence=0#table12");
        source.setFont(new Font("Dialog", Font.PLAIN, 8));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);

        XYChart.create(AxisType.DATE, AxisType.NUMBER)
                .title("US Budget Deficit")
                .addTitle(source)
                .axisNames("Year", "$ Billion")
                .dataset(dataset, XYChartType.BAR)

                .barRenderer(0)
                .drawBarOutline(true)
                .seriesOutlinePaint(0, Color.RED)
                .defaultToolTipGenerator(new StandardXYToolTipGenerator("{1} = {2}",
                        new SimpleDateFormat("yyyy"), new DecimalFormat("0")))
                .done()

                .domainAxisDate()
                .tickMarkPosition(DateTickMarkPosition.MIDDLE)
                .lowerMargin(0.01)
                .upperMargin(0.01)
                .doneXY()

                .show(500, 300, "US Budget Deficit");

    }
}
