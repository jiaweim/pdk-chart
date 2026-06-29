package pdk.chart.demo.fluent;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryChartType;

import java.awt.*;
import java.util.List;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 10:18 AM
 */
public class BoxAndWhiskerChartDemo1 {
    static void main() {
        int SERIES_COUNT = 3;
        int CATEGORY_COUNT = 5;
        int VALUE_COUNT = 20;
        DefaultBoxAndWhiskerCategoryDataset<String, String> dataset = new DefaultBoxAndWhiskerCategoryDataset<>();
        for (int s = 0; s < SERIES_COUNT; s++) {
            for (int c = 0; c < CATEGORY_COUNT; c++) {
                List<Double> values = new java.util.ArrayList<>();
                for (int i = 0; i < VALUE_COUNT; i++) {
                    double v = 0 + (Math.random() * (20.0 - 0.0));
                    values.add(v);
                }

                dataset.add(values, "Series " + s, "Category " + c);
            }
        }

        CategoryXYChart chart = CategoryXYChart.create()
                .title("Box and Whisker Chart Demo 1")
                .axisNames("Category", "Value")
                .showLegend(true)
                .backgroundPaint(Color.WHITE)
                .plotBackgroundPaint(Color.LIGHT_GRAY)
                .addDataset(dataset, CategoryChartType.BoxWhisker)
                .domainGridlinePaint(Color.WHITE)
                .domainGridlinesVisible(true)
                .rangeGridlinePaint(Color.WHITE);
        chart.rangeAxisNumber()
                .standardTickUnits(NumberAxis.createStandardTickUnits());
        chart.show();
    }
}
