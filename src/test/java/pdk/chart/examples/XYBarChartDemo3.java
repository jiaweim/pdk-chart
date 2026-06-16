package pdk.chart.examples;

import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;

/**
 * A demo that shows varying bar widths on an XYPlot.
 * <p>
 * The underlying dataset is an IntervalXYDataset which places no limitation on the interval for either the x-values or the y-values.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 2:25 PM
 */
public class XYBarChartDemo3 {

    static void main() {
        XYChart.create()
                .dataset(new SimpleIntervalXYDataset(), XYChartType.BAR)
                .title("Sample")
                .axisNames("X", "Y")
                .show(500, 300);
    }
}
