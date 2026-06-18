package pdk.chart.examples;

import pdk.chart.Chart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.axis.NumberAxis;
import pdk.chart.block.BlockBorder;
import pdk.chart.data.time.TimeTableXYDataset;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.fluent.AxisType;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardXYItemLabelGenerator;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * A stacked bar chart using data from a TimeTableXYDataset.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 18 Jun 2026, 9:31 AM
 */
public class StackedXYBarChartDemo2 {

    private static TableXYDataset createDataset() {
        Year[] years = new Year[]{
                new Year(1983), new Year(1984), new Year(1985), new Year(1986), new Year(1987),
                new Year(1988), new Year(1989), new Year(1990), new Year(1991), new Year(1992),
                new Year(1993), new Year(1994), new Year(1995), new Year(1996), new Year(1997),
                new Year(1998), new Year(1999), new Year(2000), new Year(2001), new Year(2002),
                new Year(2003)
        };


        TimeTableXYDataset dataset = new TimeTableXYDataset();
        dataset.add(years, new double[]{
                0.0, 2.0, 1.0, 1.0, 2.0,
                2.0, 1.0, 5.0, 5.0, 2.0,
                4.0, 3.0, 2.0, 1.0, 2.0,
                1.0, 4.0, 6.0, 5.0, 4.0,
                2.0
        }, "Albatrosses");
        dataset.add(years, new double[]{
                21.0, 24.0, 32.0, 20.0, 28.0,
                17.0, 31.0, 32.0, 29.0, 31.0,
                25.0, 44.0, 35.0, 40.0, 32.0,
                32.0, 30.0, 29.0, 28.0, 39.0,
                32.0
        }, "Aces");

        return dataset;
    }

    static void main() {
        XYChart chart = XYChart.create(AxisType.DATE, AxisType.NUMBER)
                .addDataset(createDataset(), XYChartType.BAR_STACK)
                .axisNames("Date", "Count")
                .title("Holes-In-One / Double Eagles")
                .addTitle(new TextTitle("PGA Tour, 1983 to 2003"))
                .addTitle(new TextTitle("http://www.golfdigest.com/majors/masters/index.ssf?/majors/masters/gw20040402albatross.html",
                        new Font("Dialog", 0, 8)))

                .domainAxisDate()
                .tickMarkPosition(DateTickMarkPosition.MIDDLE)
                .lowerMargin(0.01)
                .upperMargin(0.01)
                .doneXY()

                .rangeAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .upperMargin(0.1)
                .doneXY()

                .barProps(0)
                .margin(0.15)
                .drawBarOutline(false)
                .defaultItemLabelsVisible(true)
                .defaultItemLabelGenerator(new StandardXYItemLabelGenerator())
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER))
                .defaultToolTipGenerator(new StandardXYToolTipGenerator("{0} : {1} = {2}",
                        new SimpleDateFormat("yyyy"), new DecimalFormat("0")))
                .done();

        LegendTitle legend = new LegendTitle(chart.getPlot());
        legend.setBackgroundPaint(Color.WHITE);
        legend.setFrame(new BlockBorder());
        legend.setPosition(RectangleEdge.BOTTOM);
        chart.addTitle(legend);

        Chart.DEFAULT_THEME.apply(chart);

        chart.show(500, 270);
    }
}
