package pdk.chart.demo;

import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryChartType;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 9:45 AM
 */
public class BarChartCategoryDemo {
    static void main() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();

        String series1 = "Males";
        String series2 = "Females";

        String category1 = "18 to 39";
        String category2 = "40 - 59";
        String category3 = "60 and over";

        dataset.addValue(5.5, series1, category1);
        dataset.addValue(10.3, series2, category1);
        dataset.addValue(8.4, series1, category2);
        dataset.addValue(20.1, series2, category2);
        dataset.addValue(12.8, series1, category3);
        dataset.addValue(24.3, series2, category3);


        CategoryXYChart chart = CategoryXYChart.create()
                .dataset(dataset, CategoryChartType.BAR)
                .title("Antidepressant Medication Usage")
                .axisNames("Age Category", "Percent")
                .domainGridlinesVisible(true)
                .rangeGridlinesVisible(true)
                .barProps(0).itemMargin(0.0).done()
                .domainAxis().categoryMargin(0.5).done();

        chart.show();
    }
}
