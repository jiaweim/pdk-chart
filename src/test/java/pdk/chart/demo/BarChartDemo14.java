package pdk.chart.demo;

import pdk.chart.CategoryIntervalBarChart;
import pdk.chart.Chart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.color.JColorQualitative;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.model.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 28 Oct 2026, 6:58 PM
 */
public class BarChartDemo14 {

    private static Chart createChart() {
        String[] labels = DatasetUtils.months(7);
        int n = labels.length;

        Random rand = new Random(42);
        double[] starts1 = new double[n];
        double[] ends1 = new double[n];
        double[] starts2 = new double[n];
        double[] ends2 = new double[n];

        for (int i = 0; i < n; i++) {
            double a = rand.nextInt(201) - 100;
            double b = rand.nextInt(201) - 100;

            starts1[i] = Math.min(a, b);
            ends1[i] = Math.max(a, b);

            a = rand.nextInt(201) - 100;
            b = rand.nextInt(201) - 100;
            starts2[i] = Math.min(a, b);
            ends2[i] = Math.max(a, b);
        }

        IntervalCategoryDataset<String, String> dataset =
                Data.<String, String>intervalCategory()
                        .addSeries("Dataset 1", starts1, ends1)
                        .addSeries("Dataset 2", starts2, ends2)
                        .categories(labels)
                        .build();

        CategoryIntervalBarChart chart = new CategoryIntervalBarChart(dataset,
                "Month", "Value", "Floating Bar Chart",
                PlotOrientation.VERTICAL, true, true, false);

        Color[] colors = JColorQualitative.ChartJS();

        chart.setSeriesPaint(0, colors[0]);
        chart.setSeriesPaint(1, colors[1]);
        chart.getLegend().setPosition(RectangleEdge.TOP);

        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        createChart().show();
    }
}
