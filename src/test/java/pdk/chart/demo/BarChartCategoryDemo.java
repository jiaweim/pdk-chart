package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.Data;
import pdk.chart.plot.CategoryPlot;

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

        Chart chart = JChart.bar("Antidepressant Medication Usage",
                "Age Category", "Percent",
                dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.domainGridlinesVisible(true)
                .rangeGridlinesVisible(true);
        plot.getBarRenderer(0)
                .itemMargin(0.0);
        plot.getDomainAxis()
                .categoryMargin(0.5);

        chart.show();
    }
}
