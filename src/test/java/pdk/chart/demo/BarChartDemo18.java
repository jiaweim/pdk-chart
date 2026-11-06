package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.CategoryAnchor;
import pdk.chart.color.JColorQualitative;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.swing.ChartPanel;
import pdk.chart.util.PaintUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 06 Nov 2026, 9:15 AM
 */
public class BarChartDemo18 {

    public static CategoryBarChart createChart() {
        String[] months = DatasetUtils.months(7);
        Color[] colors = JColorQualitative.ChartJS();

        Random random = new Random(42);
        String[] series = {"Dataset 1", "Dataset 2"};
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

        CategoryBarChart chart = new CategoryBarChart(dataset);
        chart.setLegendPosition(RectangleEdge.TOP);
        chart.withTitle("Vertical Bar Chart")
                .withDomainGridlinesVisible(true)
                .withDomainGridlinePosition(CategoryAnchor.BETWEEN);

        chart.getRenderer()
                .withItemMargin(0.05)
                .withSeriesPaint(0, PaintUtils.withAlpha(colors[0], 0.5f))
                .withSeriesPaint(1, PaintUtils.withAlpha(colors[1], 0.5f));
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        createChart().show();
    }
}
