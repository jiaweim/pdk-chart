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
 * @since 04 Jun 2026, 1:42 PM
 */
public class XYLineAndShapeRendererDemo1 {
    static void main() {
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        dataset.addSeries(new XYSeries<>("Series 1", new double[]{1, 2, 3}, new double[]{3.3, 4.4, 1.7}));
        dataset.addSeries(new XYSeries<>("Series 2", new double[]{1, 2, 3, 4}, new double[]{7.3, 6.8, 9.6, 5.6}));

        XYChart.create()
                .dataset(dataset, XYChartType.LINE)
                .axisNames("X", "Y")
                .title("XYLineAndShapeRenderer Demo 1")
                .showLegend(true)
                .lineAndShapeProps(0)
                .addTooltips(true)
                .seriesVisible(0, true, false)
                .seriesVisible(1, false, true)
                .done()
                .show();
    }
}
