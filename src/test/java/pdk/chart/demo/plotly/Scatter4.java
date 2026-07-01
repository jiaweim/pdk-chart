package pdk.chart.demo.plotly;

import pdk.chart.Chart;
import pdk.chart.JChart;
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

        Chart chart = JChart.scatter((Double[]) iris.get("Sepal Width"),
                (Double[]) iris.get("Sepal Length"),
                (Double[]) iris.get("Petal Length"),
                "x",
                "y",
                "Petal Length"
        );
        PaintScaleLegend subtitle = (PaintScaleLegend) chart.getSubtitle(0);
        subtitle.setPadding(10,0,50,0);
        chart.show();
    }
}
