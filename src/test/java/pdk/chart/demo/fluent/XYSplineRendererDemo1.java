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
 * @since 04 Jun 2026, 1:59 PM
 */
public class XYSplineRendererDemo1 {
    static void main() {
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        XYSeries<String> s1 = new XYSeries<>("Series 1",
                new double[]{2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0},
                new double[]{56.27, 41.32, 31.45, 30.05, 24.69, 19.78, 20.94, 16.73, 14.21, 12.44});
        XYSeries<String> s2 = new XYSeries<>("Series 2",
                new double[]{11.0, 10.0F, 9.0F, 8.0F, 7.0F, 6.0F, 5.0F, 4.0F, 3.0F, 2.0F},
                new double[]{56.27, 41.32, 31.45, 30.05, 24.69, 19.78, 20.94, 16.73, 14.21, 12.44});
        dataset.addSeries(s1);
        dataset.addSeries(s2);

        XYChart.create()
                .dataset(dataset, XYChartType.SPLINE)
                .showLegend(true)
                .show();
    }
}
