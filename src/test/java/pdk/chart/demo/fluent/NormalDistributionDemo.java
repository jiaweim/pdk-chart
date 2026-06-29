package pdk.chart.demo.fluent;

import pdk.chart.data.function.NormalDistributionFunction2D;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 12:40 PM
 */
public class NormalDistributionDemo {
    static void main() {
        NormalDistributionFunction2D func = new NormalDistributionFunction2D(20.6, 1.62);
        XYSeries<String> lineSeries = func.sampleSeries(20.6 - 10, 20.6 + 10, 500, "Line");
        XYSeries<String> areaSeries = func.sampleSeries(10.6, 18, 100, "Area");

        XYSeriesCollection<String> dataset1 = new XYSeriesCollection<>(lineSeries);
        XYSeriesCollection<String> dataset2 = new XYSeriesCollection<>(areaSeries);

        XYChart.create()
                .axisNames("X", "Probability Density")
                .dataset(dataset1, XYChartType.LINE)
                .addDataset(dataset2, XYChartType.AREA)
                .show();
    }
}
