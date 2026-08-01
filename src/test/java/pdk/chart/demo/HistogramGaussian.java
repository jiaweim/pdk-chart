package pdk.chart.demo;

import pdk.chart.BarChart;
import pdk.chart.Data;
import pdk.chart.data.statistics.HistogramDataset;

import java.util.Random;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 10:52 AM
 */
public class HistogramGaussian {
    static void main() {
        Data.HistogramDatasetBuilder his = Data.his();
        double[] values = new double[1000];
        Random random = new Random(12345678L);
        for (int i = 0; i < 1000; i++) {
            values[i] = random.nextGaussian() + 5;
        }
        his.addSeries("H1", values, 100, 2.0, 8.0);

        values = new double[1000];
        for (int i = 0; i < 1000; i++) {
            values[i] = random.nextGaussian() + 7;
        }
        his.addSeries("H2", values, 100, 4.0, 10.0);

        HistogramDataset dataset = his.build();

        BarChart chart = new BarChart(dataset, null, null, "Histogram Gaussian");
        chart.setForegroundAlpha(0.85f);
        chart.show();
    }
}
