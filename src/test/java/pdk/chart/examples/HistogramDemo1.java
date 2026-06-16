package pdk.chart.examples;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.HistogramDataset;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.renderer.xy.StandardXYBarPainter;

import java.util.Random;

/**
 * A simple histogram illustrating the use of the HistogramDataset class.
 * <p>
 * Mouse-wheel zooming has been enabled for this chart, as well as panning (via CTRL-mouse-drag).
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 2:38 PM
 */
public class HistogramDemo1 {

    private static IntervalXYDataset createDataset() {
        HistogramDataset dataset = new HistogramDataset();

        double[] values = new double[1000];
        Random generator = new Random(12345678L);
        for (int i = 0; i < 1000; ++i) {
            values[i] = generator.nextGaussian() + 5.0;
        }
        dataset.addSeries("H1", values, 100, 2.0, 8.0);

        values = new double[1000];
        for (int i = 0; i < 1000; ++i) {
            values[i] = generator.nextGaussian() + (double) 7.0F;
        }

        dataset.addSeries("H2", values, 100, 4.0, 10.0);
        return dataset;
    }

    static void main() {
        XYChart.create()
                .title("Histogram Demo 1")
                .dataset(createDataset(), XYChartType.HISTOGRAM)
                .showLegend(true)
                .domainPannable(true)
                .rangePannable(true)
                .foregroundAlpha(0.85f)

                .rangeAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .doneXY()

                .barRenderer(0)
                .addTooltips(true)
                .drawBarOutline(false)
                .barPainter(new StandardXYBarPainter())
                .shadowVisible(false)
                .done()

                .show(500, 270);
    }
}
