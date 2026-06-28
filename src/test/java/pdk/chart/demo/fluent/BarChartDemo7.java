package pdk.chart.demo.fluent;

import pdk.chart.api.Layer;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.IntervalMarker;
import pdk.chart.text.TextAnchor;

import java.awt.*;

/**
 * A bar chart with the item label position customised.
 * <p>
 * A range marker has been added to the chart to indicate a target range for the data values.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 12 Jun 2026, 9:47 AM
 */
public class BarChartDemo7 {
    private static CategoryDataset<String, String> createDataset() {
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";

        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4", "Category 5"};
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries(series1, categories, new double[]{1.0, 4.0, 3.0, 5.0, 5.0});
        dataset.addSeries(series2, categories, new double[]{5.0, 7.0, 6.0, 8.0, 4.0});
        dataset.addSeries(series3, categories, new double[]{4.0, 3.0, 0.0, 3.0, 6.0});
        return dataset;
    }

    static void main() {
        IntervalMarker target = new IntervalMarker(4.5, 7.5);
        target.setLabel("Target Range");
        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target.setLabelAnchor(RectangleAnchor.LEFT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setPaint(new Color(222, 222, 255, 128));

        CategoryXYChart chart = CategoryXYChart.create()
                .title("Bar Chart Demo 7")
                .axisNames("Category", "Value")
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .rangePannable(true)
                .addRangeMarker(target, Layer.BACKGROUND)

                .barProps(0)
                .drawBarOutline(false)
                .itemMargin(0.1)
                .defaultItemLabelGenerator(new LabelGenerator())
                .defaultItemLabelsVisible(true)
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.CENTER_RIGHT,
                        TextAnchor.CENTER_RIGHT, (-Math.PI / 2)))
                .positiveItemLabelPositionFallback(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT,
                        TextAnchor.CENTER_LEFT, (-Math.PI / 2)))
                .done()
                .domainAxis()
                .categoryLabelPositions(CategoryLabelPositions.UP_90)
                .done();
        chart.rangeAxisNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());

        chart.show(500, 270);
    }

    static class LabelGenerator extends StandardCategoryItemLabelGenerator {
        public String generateLabel(CategoryDataset dataset, int series, int category) {
            return dataset.getRowKey(series).toString();
        }
    }
}
