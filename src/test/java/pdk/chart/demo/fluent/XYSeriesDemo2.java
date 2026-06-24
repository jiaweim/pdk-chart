package pdk.chart.demo.fluent;

import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jun 2026, 2:56 PM
 */
public class XYSeriesDemo2 {
    static void main() {
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        XYSeries<String> series = new XYSeries<>("Flat Data");
        series.add(
                new double[]{1.0, 5.0, 4.0, 12.5, 17.3, 21.2, 21.9, 25.6, 30.0},
                new double[]{100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0}
        );
        dataset.addSeries(series);

        XYChart.create()
                .dataset(dataset, XYChartType.LINE)
                .axisNames("X", "Y")
                .showLegend(true)
                .rangeAxis().autoRangeIncludesZero(false).done()
                .lineAndShapeProps(0)
                .addTooltips(true)
                .done()
                .show();
    }
}
