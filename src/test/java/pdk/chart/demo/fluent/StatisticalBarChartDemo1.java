package pdk.chart.demo.fluent;

import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.statistics.DefaultStatisticalCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryChartType;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.text.TextAnchor;

import java.awt.*;

public class StatisticalBarChartDemo1 {

    public static CategoryDataset<String, String> createDataset() {
        DefaultStatisticalCategoryDataset<String, String> dataset = new DefaultStatisticalCategoryDataset<>();
        dataset.add(10.0, 2.4, "Row 1", "Column 1");
        dataset.add(15.0, 4.4, "Row 1", "Column 2");
        dataset.add(13.0, 2.1, "Row 1", "Column 3");
        dataset.add(7.0, 1.3, "Row 1", "Column 4");
        dataset.add(22.0, 2.4, "Row 2", "Column 1");
        dataset.add(18.0, 4.4, "Row 2", "Column 2");
        dataset.add(28.0, 2.1, "Row 2", "Column 3");
        dataset.add(17.0, 1.3, "Row 2", "Column 4");
        return dataset;
    }

    static void main() {
        CategoryXYChart chart = CategoryXYChart.create()
                .title("Statistical Bar Chart Demo 1")
                .axisNames("Type", "Value")
                .dataset(createDataset(), CategoryChartType.BAR_STATISTICS)
                .showLegend(true);

        Chart.DEFAULT_THEME.apply(chart);

        chart.rangeAxisNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .autoRangeIncludesZero(false);
        chart.barProps(0)
                .drawBarOutline(false)
                .errorIndicatorPaint(Color.BLACK)
                .includeBaseInRange(false)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .defaultItemLabelsVisible(true)
                .defaultItemLabelPaint(Color.YELLOW)
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE6, TextAnchor.BOTTOM_CENTER))
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64)))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0)))
                .done();
        chart.show(500, 270);
    }
}
