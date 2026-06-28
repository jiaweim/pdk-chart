package pdk.chart.demo.fluent;

import pdk.chart.demo.SampleXYDataset2;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Jun 2026, 12:53 PM
 */
public class ScatterPlotDemo3 {
    static void main() {
        XYChart chart = XYChart.create()
                .dataset(new SampleXYDataset2(), XYChartType.LINE)
                .title("Scatter Plot Demo 3")
                .showLegend(true)
                .axisNames("X", "Y")
                .lineAndShapeProps(0)
                .defaultShapesVisible(true)
                .defaultLinesVisible(false)
                .addTooltips(true)
                .done();
        chart.domainAxisNumber()
                .autoRangeIncludesZero(false);

        chart.show();
    }
}
