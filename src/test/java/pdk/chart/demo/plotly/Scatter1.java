package pdk.chart.demo.plotly;

import pdk.chart.Chart;
import pdk.chart.JChart;

/**
 * https://plotly.com/python/line-and-scatter/
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 26 Jun 2026, 21:17
 */
public class Scatter1 {
    static void main() {
        Chart chart = JChart.scatter(
                "x", new double[]{0, 1, 2, 3, 4},
                "y", new double[]{0, 1, 4, 9, 16});
        chart.show();
    }
}
