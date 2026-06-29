package pdk.chart.demo.fluent;

import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.demo.Animator;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryChartType;

import java.awt.*;

/**
 * A simple bar chart with animation.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 12 Jun 2026, 10:23 AM
 */
public class BarChartDemo10 {

    private static DefaultCategoryDataset<String, String> createDataset() {
        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4", "Category 5"};
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("First", categories, new double[]{31.0, 44.0, 33.0, 45.0, 35.0});
        dataset.addSeries("Second", categories, new double[]{45.0, 37.0, 46.0, 38.0, 44.0});
        dataset.addSeries("Third", categories, new double[]{34.0, 43.0, 32.0, 43.0, 36.0});

        return dataset;
    }

    static void main() {
        DefaultCategoryDataset<String, String> dataset = createDataset();
        CategoryXYChart chart = CategoryXYChart.create()
                .title("Bar Chart Demo 10")
                .axisNames("Category", "Value")
                .dataset(dataset, CategoryChartType.BAR)
                .domainGridlinesVisible(true)

                .barProps(0)
                .drawBarOutline(false)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64)))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0)))
                .seriesPaint(2, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0)))
                .done()

                .domainAxis()
                .categoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6))
                .done();


        chart.rangeAxisNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());

        Animator animator = new Animator(dataset);
        animator.start();

        chart.show(500, 270);
    }
}
