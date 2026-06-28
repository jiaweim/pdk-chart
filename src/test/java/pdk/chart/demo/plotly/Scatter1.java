package pdk.chart.demo.plotly;

import pdk.chart.fluent.JChart;
import pdk.chart.fluent.XYChart;

/**
 * https://plotly.com/python/line-and-scatter/
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 26 Jun 2026, 21:17
 */
public class Scatter1 {
    static void main() {
        XYChart chart = JChart.scatter(
                        new double[]{0, 1, 2, 3, 4},
                        new double[]{0, 1, 4, 9, 16})
                .axisNames("x", "y");
        chart.show();
    }
}
