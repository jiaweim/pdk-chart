package pdk.chart.demo.fluent;

import pdk.chart.api.SortOrder;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryChartType;
import pdk.chart.plot.PlotOrientation;

import java.awt.*;

/**
 * A layered bar chart (horizontal orientation).
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 15 Jun 2026, 2:58 PM
 */
public class LayeredBarChartDemo2 {

    private static CategoryDataset<String, String> createDataset() {
        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4", "Category 5"};
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("First", categories, new double[]{1.0, 4.0, 3.0, 5.0, 5.0});
        dataset.addSeries("Second", categories, new double[]{5.0, 7.0, 6.0, 8.0, 4.0});
        dataset.addSeries("Third", categories, new double[]{4.0, 3.0, 2.0, 3.0, 6.0});

        return dataset;
    }

    static void main() {
        CategoryXYChart chart = CategoryXYChart.create()
                .title("Layered Bar Chart Demo 2")
                .orientation(PlotOrientation.HORIZONTAL)
                .showLegend(true)
                .dataset(createDataset(), CategoryChartType.BAR_LAYER)
                .domainGridlinesVisible(true)
                .rangeZeroBaselineVisible(true)
                .rangePannable(true)
                .rangeZeroBaselineVisible(true)
                .rowRenderingOrder(SortOrder.DESCENDING)

                .barProps(0)
                .drawBarOutline(false)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64)))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0)))
                .seriesPaint(2, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0)))
                .done();

        chart.rangeAxisNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.show(500, 270);

    }
}
