package pdk.chart.demo.plotly;

import pdk.chart.ScatterChartV2;
import pdk.chart.legend.PaintScaleLegend;

import java.util.HashMap;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Jul 2026, 10:29 AM
 */
public class Scatter4 {
    static void main() {
        HashMap<String, Object[]> iris = Datasets.iris();

        ScatterChartV2 chart = new ScatterChartV2(
                (Double[]) iris.get("Sepal Width"),
                (Double[]) iris.get("Sepal Length"),
                (Double[]) iris.get("Petal Length"),
                "x",
                "y",
                "Petal Length"
        );
        PaintScaleLegend subtitle = (PaintScaleLegend) chart.getSubtitle(0);
        subtitle.setPadding(10, 0, 50, 0);
        chart.getRangeAxisAsNumber()
                .withAutoRangeIncludesZero(false);
        chart.show();
    }
}
