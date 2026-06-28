package pdk.chart.demo.fluent;

import pdk.chart.api.Layer;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.plot.CategoryMarker;
import pdk.chart.text.TextAnchor;

import java.awt.*;

/**
 * This demo shows a CategoryMarker added to a simple line plot.
 * The marker is displayed as a line in the centre of the category.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 3:52 PM
 */
public class CategoryMarkerDemo1 {

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(21.0, "Series 1", "Category 1");
        dataset.addValue(50.0, "Series 1", "Category 2");
        dataset.addValue(152.0, "Series 1", "Category 3");
        dataset.addValue(184.0, "Series 1", "Category 4");
        dataset.addValue(299.0, "Series 1", "Category 5");
        return dataset;
    }

    static void main() {
        CategoryMarker marker = new CategoryMarker("Category 4", Color.BLUE, new BasicStroke(1.0F));
        marker.setDrawAsLine(true);
        marker.setLabel("Marker Label");
        marker.setLabelFont(new Font("Dialog", Font.PLAIN, 11));
        marker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        marker.setLabelOffset(new RectangleInsets((double) 2.0F, (double) 5.0F, (double) 2.0F, (double) 5.0F));

        CategoryXYChart chart = CategoryXYChart.create()
                .dataset(createDataset(), CategoryXYChartType.LINE)
                .title("Category Marker Demo 1")
                .axisNames("Category", "Count")
                .showLegend(true)
                .addDomainMarker(marker, Layer.BACKGROUND)

                .lineRenderer(0)
                .addTooltips(true)
                .seriesShapesVisible(0, true)
                .drawOutlines(true)
                .useFillPaint(true)
                .defaultFillPaint(Color.WHITE)
                .done();

        chart.rangeAxisNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.show(500, 270);

    }
}
