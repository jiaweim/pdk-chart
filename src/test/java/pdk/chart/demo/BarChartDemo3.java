package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.api.Layer;
import pdk.chart.api.LengthAdjustmentType;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryMarker;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
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
public class BarChartDemo3 extends ApplicationFrame {

    public BarChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        double[][] data = new double[][]{{4.0, 3.0, -2.0, 3.0, 6.0}};
        return DatasetUtils.createCategoryDataset("Series ", "Category ", data);
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.bar(dataset, "Category", "Value", "Bar Chart Demo 3");
        chart.removeLegend();
        CategoryPlot<String, String> plot = chart.getCategoryPlot();
        plot.setNoDataMessage("NO DATA!");
        plot.setRangePannable(true);
        Paint[] customColours = new Paint[]{
                new Color(196, 215, 216),
                new Color(78, 137, 139),
                new Color(138, 177, 178),
                new Color(19, 97, 100)
        };
        CustomRenderer renderer = new CustomRenderer(customColours);
        renderer.defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .defaultItemLabelsVisible(true)
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER,
                        TextAnchor.CENTER, TextAnchor.CENTER, 0.0));
        plot.setRenderer(renderer);

        CategoryMarker marker = new CategoryMarker("Category 3");
        marker.setLabel("Special");
        marker.setPaint(new Color(221, 255, 221, 128));
        marker.setAlpha(0.5F);
        marker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        marker.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        marker.setLabelOffsetType(LengthAdjustmentType.CONTRACT);
        plot.addDomainMarker(marker, Layer.BACKGROUND);

        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .lowerMargin(0.15)
                .upperMargin(0.15);

        NumberAxis rangeAxis2 = new NumberAxis(null);
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis2.setLowerMargin(0.15);
        rangeAxis2.setUpperMargin(0.15);
        plot.setRangeAxis(1, rangeAxis2);

        CategoryAxis domainAxis2 = new CategoryAxis(null);
        plot.setDomainAxis(1, domainAxis2);

        List<Integer> axisIndices = Arrays.asList(0, 1);
        plot.mapDatasetToDomainAxes(0, axisIndices);
        plot.mapDatasetToRangeAxes(0, axisIndices);

        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        BarChartDemo3 demo = new BarChartDemo3("BarChartDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class CustomRenderer extends BarRenderer {
        private final Paint[] colors;

        public CustomRenderer(Paint[] colors) {
            this.colors = colors;
        }

        public Paint getItemPaint(int row, int column) {
            return this.colors[column % this.colors.length];
        }
    }
}
