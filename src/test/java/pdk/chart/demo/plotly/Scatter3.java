package pdk.chart.demo.plotly;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;

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

        Chart chart = ChartFactory.bubble(
                "x", (Double[]) iris.get("Sepal Width"),
                "y", (Double[]) iris.get("Sepal Length"),
                (Double[]) iris.get("Petal Length"),
                (String[]) iris.get("Class"));
        chart.show();
    }
}
