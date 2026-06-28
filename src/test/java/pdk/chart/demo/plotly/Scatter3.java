package pdk.chart.demo.plotly;

import pdk.chart.fluent.JChart;
import pdk.chart.fluent.XYChart;

import java.util.HashMap;

/**
 * https://plotly.com/python/line-and-scatter/
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 28 Jun 2026, 10:47
 */
public class Scatter3 {

    static void main() {
        HashMap<String, Object[]> iris = Datasets.iris();

        XYChart chart = JChart.scatter(
                (Double[]) iris.get("Sepal Width"),
                (Double[]) iris.get("Sepal Length"),
                (Double[]) iris.get("Petal Length"),
                (String[]) iris.get("Class")
        );
        chart.show();
    }
}
