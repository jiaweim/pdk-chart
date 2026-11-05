package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.CategoryStackedBarChart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.color.JColorQualitative;
import pdk.chart.data.KeyToGroupMap;
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
 * @since 05 Nov 2026, 9:32 AM
 */
public class BarChartDemo17 {

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
                values[i] = random.nextDouble(201) - 100;
            }
            dsBuilder.addSeries(s, values);
        }
        DefaultCategoryDataset<String, String> dataset = dsBuilder.build();

        KeyToGroupMap<String, String> groupMap = new KeyToGroupMap<>("Stack 0");
        groupMap.mapKeyToGroup("Dataset 1", "Stack 0");
        groupMap.mapKeyToGroup("Dataset 2", "Stack 0");
        groupMap.mapKeyToGroup("Dataset 3", "Stack 1");

        CategoryStackedBarChart chart = new CategoryStackedBarChart(dataset);
        chart.setTitle("Stacked Bar Chart");
        chart.getLegend().setPosition(RectangleEdge.TOP);
        chart.getRenderer()
                .withSeriesToGroupMap(groupMap)
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
