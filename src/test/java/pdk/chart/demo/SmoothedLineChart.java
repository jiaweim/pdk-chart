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
 * @since 04 Jun 2026, 2:02 PM
 */
public class SmoothedLineChart {
    static void main() {
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        dataset.addSeries(new XYSeries<>("line",
                new double[]{1, 2, 3, 4, 5, 6, 7},
                new double[]{820, 932, 901, 934, 1290, 1330, 1320}));
        XYChart.create()
                .dataset(dataset, XYChartType.SPLINE)
                .domainGridlinesVisible(false)
                .show();
    }
}
