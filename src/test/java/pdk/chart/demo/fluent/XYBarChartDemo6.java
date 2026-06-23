package pdk.chart.demo.fluent;

import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.fluent.Data;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.plot.PlotOrientation;

/**
 * In this demo, the XYBarRenderer is configured to use the y-interval
 * from the dataset (rather than using zero for the base of the bars).
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 17 Jun 2026, 10:33 AM
 */
public class XYBarChartDemo6 {

    static void main() {
        IntervalXYDataset<String> dataset = Data.createIntervalXYDataset("Series 1",
                new double[]{1.0, 2.0, 3.0, 4.0},
                new double[]{0.9, 1.8, 2.7, 3.6},
                new double[]{1.1, 2.2, 3.3, 4.4},
                new double[]{1.0, 2.0, 3.0, 4.0},
                new double[]{0.9, 1.8, 2.7, 3.6},
                new double[]{1.1, 2.2, 3.3, 4.4}
        );

        XYChart.create()
                .orientation(PlotOrientation.HORIZONTAL)
                .dataset(dataset, XYChartType.BAR)
                .axisNames("X", "Y")
                .domainPannable(true)
                .rangePannable(true)

                .barProps(0)
                .useYInterval(true)
                .done()

                .show(500, 300, "XYBarChartDemo6");
    }
}
