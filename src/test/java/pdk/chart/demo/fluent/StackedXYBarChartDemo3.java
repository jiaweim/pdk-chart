package pdk.chart.demo.fluent;

import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.data.time.TimeTableXYDataset;
import pdk.chart.data.time.Year;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.fluent.AxisType;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.title.TextTitle;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * A stacked bar chart using data from a TimeTableXYDataset, and displaying the values as percentages.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 18 Jun 2026, 9:56 AM
 */
public class StackedXYBarChartDemo3 {

    private static TableXYDataset createDataset() {
        TimeTableXYDataset dataset = new TimeTableXYDataset();
        Year[] years = new Year[]{new Year(2002), new Year(2003), new Year(2004),
                new Year(2005), new Year(2006)};
        dataset.add(years, new double[]{41287.0, 41096.0, 39603.0, 39693.0, 37227.0}, "Landfilled");
        dataset.add(years, new double[]{7717.0, 8317.0, 9493.0, 11228.0, 14941.0}, "Recycled");
        return dataset;
    }

    static void main() {
        XYChart.create(AxisType.DATE, AxisType.NUMBER)
                .dataset(createDataset(), XYChartType.BAR_STACK)
                .axisNames("Year", "Tonnes")
                .backgroundPaint(Color.WHITE)
                .plotBackgroundPaint(Color.LIGHT_GRAY)
                .domainGridlinePaint(Color.WHITE)
                .rangeGridlinePaint(Color.WHITE)
                .title("Waste Management")
                .addTitle(new TextTitle("St Albans City and District Council"))

                .domainAxisDate()
                .tickMarkPosition(DateTickMarkPosition.MIDDLE)
                .lowerMargin(0.01)
                .upperMargin(0.01)
                .doneXY()

                .rangeAxis()
                .numberFormatOverride(new DecimalFormat("0.0%"))
                .done()

                .barProps(0)
                .margin(0.3)
                .renderAsPercentages(true)
                .drawBarOutline(false)
                .defaultToolTipGenerator(new StandardXYToolTipGenerator("{0} : {1} = {2} tonnes",
                        new SimpleDateFormat("yyyy"), new DecimalFormat("#,##0")))
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, new Color(64, 0, 0), 0.0F, 0.0F, Color.RED))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, new Color(0, 64, 0), 0.0F, 0.0F, Color.GREEN))
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL))
                .done()

                .show(500, 270);
    }
}
