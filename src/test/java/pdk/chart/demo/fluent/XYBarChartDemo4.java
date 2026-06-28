package pdk.chart.demo.fluent;

import pdk.chart.axis.NumberAxis;
import pdk.chart.fluent.Data;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;

/**
 * In this demo, the domain axis shows only integer values.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 17 Jun 2026, 9:41 AM
 */
public class XYBarChartDemo4 {

    static void main() {
        XYChart chart = XYChart.create()
                .dataset(Data.createIntervalXYDataset("Series 1",
                                new double[]{1.0, 2.0, 3.0},
                                new double[]{5.0, 70.8, 48.3},
                                0.9)
                        , XYChartType.BAR)
                .title("XYBarChartDemo4")
                .axisNames("X", "Y")
                .showLegend(true)

                .barProps(0)
                .drawBarOutline(false)
                .done();
        chart.domainAxisNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());

        chart.show(500, 300);
    }
}
