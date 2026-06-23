package pdk.chart.demo.fluent;

import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;

/**
 * A bar chart with items labels displayed for just one series.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 12 Jun 2026, 9:58 AM
 */
public class BarChartDemo8 {

    private static CategoryDataset<String, String> createDataset() {
        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4", "Category 5"};
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("First", categories, new double[]{1.0, 4.0, 3.0, 5.0, 5.0});
        dataset.addSeries("Second", categories, new double[]{5.0, 7.0, 6.0, 8.0, 4.0});
        dataset.addSeries("Third", categories, new double[]{4.0, 3.0, 2.0, 3.0, 6.0});
        return dataset;
    }

    static void main() {
        CategoryXYChart.create()
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .title("Bar Chart Demo 8")
                .axisNames("Category", "Value")

                .rangeAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .upperMargin(0.15)
                .done()

                .barProps(0)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .seriesItemLabelsVisible(0, Boolean.TRUE)
                .done()

                .domainAxis()
                .categoryLabelPositions(CategoryLabelPositions.UP_45)
                .done()

                .show(500, 270);

    }
}
