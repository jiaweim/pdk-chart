package pdk.chart.examples;

import pdk.chart.api.RectangleInsets;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.plot.PlotOrientation;

import java.awt.*;

public class BarChartDemo6 {

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(83.0, "First", "Factor 1");
        return dataset;
    }

    static void main() {
        CategoryXYChart.create()
                .orientation(PlotOrientation.HORIZONTAL)
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .plotInsets(RectangleInsets.ZERO_INSETS)
                .axisOffset(RectangleInsets.ZERO_INSETS)
                .axisNames("Category", "Score (%)")
                .backgroundPaint(Color.YELLOW)
                .rangeGridlinesVisible(false)
                .domainAxis()
                .lowerMargin(0.2)
                .upperMargin(0.2)
                .visible(false)
                .done()

                .rangeAxis()
                .range(0, 100)
                .visible(false)
                .done()

                .show(500, 270);
    }
}
