package pdk.chart.examples;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.fluent.Data;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;

public class StackedXYBarChartDemo1 {

    static void main() {
        XYSeries<String> s1 = new XYSeries<>("Series 1", true, false);
        s1.add(new double[]{1.0, 2.0, 3.0, 4.0},
                new double[]{5.0, 15.5, 9.5, 7.5});
        XYSeries<String> s2 = new XYSeries<>("Series 2", true, false);
        s2.add(new double[]{1.0, 2.0, 3.0, 4.0},
                new double[]{5.0, 15.5, 9.5, 3.5});

        TableXYDataset<String> dataset = Data.createTableXYDataset(s1, s2);

        XYChart.create()
                .title("Stacked XY Bar Chart Demo 1")
                .dataset(dataset, XYChartType.BAR_STACK)
                .axisNames("X", "Y")

                .domainAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .doneXY()

                .barRenderer(0)
                .drawBarOutline(false)
                .margin(0.1)
                .done()

                .show(500, 270);
    }
}
