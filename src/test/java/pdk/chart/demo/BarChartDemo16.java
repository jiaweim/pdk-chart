package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.CategoryStackedBarChart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.color.JColorQualitative;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.swing.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Nov 2026, 10:49 AM
 */
public class BarChartDemo16 {

    public static CategoryBarChart createChart() {
        String[] months = DatasetUtils.months(7);
        Color[] colors = JColorQualitative.ChartJS();

        Random random = new Random(42);
        String[] series = {"Dataset 1", "Dataset 2", "Dataset 3"};

        DatasetUtils.CategoryDatasetBuilder<String, String> dsBuilder = DatasetUtils.category(months);
        int n = months.length;
        for (String s : series) {
            double[] values = new double[n];
            for (int i = 0; i < n; i++) {
                double value = random.nextDouble(201) - 100;
                values[i] = value;
            }
            dsBuilder.addSeries(s, values);
        }
        DefaultCategoryDataset<String, String> dataset = dsBuilder.build();

        CategoryStackedBarChart chart = new CategoryStackedBarChart(dataset);
        chart.setTitle("Stacked Bar Chart");
        chart.getLegend().setPosition(RectangleEdge.TOP);
        chart.getRenderer()
                .withSeriesPaint(0, colors[0])
                .withSeriesPaint(1, colors[1])
                .withSeriesPaint(2, colors[2]);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        createChart().show();
    }
}
