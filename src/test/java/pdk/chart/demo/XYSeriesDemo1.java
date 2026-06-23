package pdk.chart.demo;

import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Jun 2026, 1:40 PM
 */
public class XYSeriesDemo1 {
    static void main() {
        XYSeries<String> series1 = new XYSeries<>("Random data",
                new double[]{1.0, 5.0, 4.0, 12.5, 17.3, 21.2, 21.9, 25.6, 30.0},
                new double[]{500.2, 694.1, 100.0, 734.4, 453.2, 500.2, Double.NaN, 734.4, 453.2});
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>(series1);

        XYChart.create()
                .title("XY Series Demo")
                .axisNames("X", "Y")
                .showLegend(true)
                .dataset(dataset, XYChartType.LINE)
                .lineAndShapeProps(0).addTooltips(true).done()
                .show();
    }
}
