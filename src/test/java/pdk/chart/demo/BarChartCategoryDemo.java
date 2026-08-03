package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.model.Data;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 9:45 AM
 */
public class BarChartCategoryDemo {
    static void main() {
        String[] categories = {"18 to 39", "40 - 59", "60 and over",};
        DefaultCategoryDataset<String, String> dataset = Data.<String, String>category()
                .addSeries("Males", categories, new double[]{5.5, 8.4, 12.8})
                .addSeries("Females", categories, new double[]{10.3, 20.1, 24.3}).build();

        CategoryBarChart chart = new CategoryBarChart(dataset,
                "Age Category", "Percent", "Antidepressant Medication Usage");
        chart.withDomainGridlinesVisible(true)
                .withRangeGridlinesVisible(true);

        chart.getRenderer()
                .withItemMargin(0.0);
        chart.getDomainAxis()
                .withCategoryMargin(0.5);

        chart.show();
    }
}
