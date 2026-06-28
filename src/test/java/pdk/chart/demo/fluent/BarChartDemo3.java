package pdk.chart.demo.fluent;

import pdk.chart.Chart;
import pdk.chart.api.Layer;
import pdk.chart.api.LengthAdjustmentType;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryMarker;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.text.TextAnchor;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * A bar chart with item labels displayed. The item labels for positive data values are displayed inside the bars,
 * while the item label for the negative data value is displayed underneath the bar (this is configurable).
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 11 Jun 2026, 10:18 AM
 */
public class BarChartDemo3 {

    private static CategoryDataset<String, String> createDataset() {
        double[][] data = new double[][]{{4.0, 3.0, -2.0, 3.0, 6.0}};
        return DatasetUtils.createCategoryDataset("Series ", "Category ", data);
    }

    static void main() {
        Paint[] customColours = new Paint[]{
                new Color(196, 215, 216),
                new Color(78, 137, 139),
                new Color(138, 177, 178),
                new Color(19, 97, 100)
        };

        CategoryMarker marker = new CategoryMarker("Category 3");
        marker.setLabel("Special");
        marker.setPaint(new Color(221, 255, 221, 128));
        marker.setAlpha(0.5F);
        marker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        marker.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        marker.setLabelOffsetType(LengthAdjustmentType.CONTRACT);

        NumberAxis rangeAxis2 = new NumberAxis(null);
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis2.setLowerMargin(0.15);
        rangeAxis2.setUpperMargin(0.15);

        CategoryAxis domainAxis2 = new CategoryAxis(null);

        List<Integer> axisIndices = Arrays.asList(0, 1);
        CategoryXYChart chart = CategoryXYChart.create()
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .setRenderer(0, new CustomRenderer(customColours))
                .setNoDataMessage("NO DATA!")
                .axisNames("Category", "Value")
                .addDomainMarker(marker, Layer.BACKGROUND)
                .barProps(0)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .defaultItemLabelsVisible(true)
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 0.0))
                .done()

                .addRangeAxis(1, rangeAxis2)
                .addDomainAxis(1, domainAxis2)
                .mapDatasetToDomainAxes(0, axisIndices)
                .mapDatasetToRangeAxes(0, axisIndices);

        chart.rangeAxisNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .lowerMargin(0.15)
                .upperMargin(0.15);

        Chart.DEFAULT_THEME.apply(chart);
        chart.show(500, 270);
    }

    static class CustomRenderer extends BarRenderer {
        private Paint[] colors;

        public CustomRenderer(Paint[] colors) {
            this.colors = colors;
        }

        public Paint getItemPaint(int row, int column) {
            return this.colors[column % this.colors.length];
        }
    }
}
