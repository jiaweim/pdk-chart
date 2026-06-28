package pdk.chart.demo.plotly;

import pdk.chart.fluent.JChart;
import pdk.chart.fluent.XYChart;

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
        XYChart chart = JChart.scatter((Double[]) iris.get("Sepal Width"), (Double[]) iris.get("Sepal Length"));
        chart.axisNames("Sepal Width", "Sepal Length");
        chart.rangeAxisNumber()
                .autoRangeIncludesZero(false);
        chart.getLineAndShape(0)
                .showTooltips(true);
        chart.show();
    }
}
