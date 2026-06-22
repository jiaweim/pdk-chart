package pdk.chart.examples;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.renderer.category.CategoryItemRenderer;

import java.text.NumberFormat;

public class StackedBarChartDemo3 {

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10.0F, "Series 1", "Jan");
        dataset.addValue(12.0F, "Series 1", "Feb");
        dataset.addValue(13.0F, "Series 1", "Mar");
        dataset.addValue(4.0F, "Series 2", "Jan");
        dataset.addValue(3.0F, "Series 2", "Feb");
        dataset.addValue(2.0F, "Series 2", "Mar");
        dataset.addValue(2.0F, "Series 3", "Jan");
        dataset.addValue(3.0F, "Series 3", "Feb");
        dataset.addValue(2.0F, "Series 3", "Mar");
        dataset.addValue(2.0F, "Series 4", "Jan");
        dataset.addValue(3.0F, "Series 4", "Feb");
        dataset.addValue(4.0F, "Series 4", "Mar");
        return dataset;
    }

    static void main() {
        CategoryItemRenderer renderer = new ExtendedStackedBarRenderer();
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());

        CategoryXYChart.create()
                .addDataset(createDataset(), renderer)
                .title("Stacked Bar Chart Demo 3")
                .axisNames("Category", "Value")
                .showLegend(true)

                .rangeAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .lowerMargin(0.15)
                .upperMargin(0.15)
                .numberFormatOverride(NumberFormat.getPercentInstance())
                .done()

                .show(500, 270);
    }
}
