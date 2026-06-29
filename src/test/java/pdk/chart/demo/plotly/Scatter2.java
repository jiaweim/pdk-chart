package pdk.chart.demo.plotly;

import pdk.chart.Chart;
import pdk.chart.JChart;

import java.util.HashMap;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 26 Jun 2026, 21:43
 */
public class Scatter2 {
    static void main() {
        HashMap<String, Object[]> iris = Datasets.iris();
        Chart chart = JChart.scatter(
                "Sepal Width", (Double[]) iris.get("Sepal Width"),
                "Sepal Length", (Double[]) iris.get("Sepal Length"));

        chart.show();
    }
}
