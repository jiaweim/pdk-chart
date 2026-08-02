package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.Chart;
import pdk.chart.data.category.DefaultCategoryDataset;
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
 * @since 24 Jul 2026, 5:53 PM
 */
public class BarChartDemo12 {

    private static Chart createChart() {
        final DefaultCategoryDataset<String, String> dataset = Data.createCategory(
                "Category",
                new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7"},
                new double[]{0, 0, 0, 0, 0, 0, 0}
        );

        Color[] colors = new Color[]{
                new Color(221, 78, 77),
                new Color(215, 131, 79),
                new Color(236, 165, 57),
                new Color(135, 170, 102),
                new Color(136, 171, 173),
                new Color(76, 179, 210),
                new Color(106, 198, 255)
        };
        CategoryBarChart chart = new CategoryBarChart(dataset, PlotOrientation.HORIZONTAL);
        chart.removeLegend();

        for (int i = 0; i < colors.length; i++) {
            chart.setItemPaint(0, i, colors[i]);
        }
        Random RND = new Random();

        Timer timer = new Timer(1000, e -> {
            int columnCount = dataset.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnKey = dataset.getColumnKey(i);
                dataset.setValue(RND.nextDouble() * 75 + 25, "Category", columnKey);
            }
        });
        timer.start();
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        Chart chart = createChart();
        chart.show();
    }
}
