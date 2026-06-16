package pdk.chart.examples;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.SimpleHistogramBin;
import pdk.chart.data.statistics.SimpleHistogramDataset;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;

public class HistogramDemo2 {

    private static IntervalXYDataset createDataset() {
        SimpleHistogramDataset dataset = new SimpleHistogramDataset("Series 1");
        SimpleHistogramBin bin1 = new SimpleHistogramBin(0.0, 1.0, true, false);
        SimpleHistogramBin bin2 = new SimpleHistogramBin(1.0, 2.0, true, false);
        SimpleHistogramBin bin3 = new SimpleHistogramBin(2.0, 3.0, true, false);
        SimpleHistogramBin bin4 = new SimpleHistogramBin(3.0, 4.0, true, true);
        bin1.setItemCount(1);
        bin2.setItemCount(10);
        bin3.setItemCount(15);
        bin4.setItemCount(20);
        dataset.addBin(bin1);
        dataset.addBin(bin2);
        dataset.addBin(bin3);
        dataset.addBin(bin4);
        return dataset;
    }

    static void main() {
        XYChart.create()
                .dataset(createDataset(), XYChartType.BAR)
                .title("HistogramDemo2")
                .showLegend(true)
                .foregroundAlpha(0.85f)
                .domainPannable(true)
                .rangePannable(true)

                .rangeAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .doneXY()

                .barRenderer(0)
                .addTooltips(true)
                .drawBarOutline(false)
                .done()
                .show(500, 270);

    }
}
