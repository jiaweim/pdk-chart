package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.ScatterChart;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.swing.ChartPanel;
import pdk.chart.util.ShapeUtils;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 27 Jul 2026, 10:55 AM
 */
public class ScatterPlotDemo6 {

    static final Random random = new Random();

    private static List<Double> getGaussian(int number, double mean, double std) {
        List<Double> seriesData = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            seriesData.add(mean + std * random.nextGaussian());
        }

        return seriesData;
    }

    private static Chart createChart() {
        XYSeriesCollection<String> dataset = Data.<String>xy()
                .addSeries("Gaussian Blob 1", getGaussian(1000, 1, 10),
                        getGaussian(1000, 1, 10))
                .addSeries("Gaussian Blob 2", getGaussian(1000, 1, 10),
                        getGaussian(1000, 0, 5))
                .build();
        ScatterChart chart = new ScatterChart(dataset, "X", "Y");
        chart.setSeriesShape(0, ShapeUtils.createCircle(6));
        chart.setSeriesShape(1, ShapeUtils.createDiamond(6));
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        createChart().show();
    }
}
