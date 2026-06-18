package pdk.chart.examples;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.StandardCategorySeriesLabelGenerator;

/**
 * A demo that shows the use of the maximum bar width setting to cap the width of bars.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 11 Jun 2026, 4:41 PM
 */
public class BarChartDemo4 {

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(1.0, "First", "Category 1");
        dataset.addValue(5.0, "Second", "Category 1");
        return dataset;
    }

    static void main() {
        CategoryXYChart chart = CategoryXYChart.create();
        chart.dataset(createDataset(), CategoryXYChartType.BAR)
                .title("Bar Chart Demo 4")
                .axisNames(null, "Value")
                .showLegend(true)
                .rangeAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .doneCategory()

                .barProps(0)
                .drawBarOutline(false)
                .maximumBarWidth(0.5)
                .legendItemLabelGenerator(new StandardCategorySeriesLabelGenerator("{0} series"));

        chart.show(500, 270);
    }
}
