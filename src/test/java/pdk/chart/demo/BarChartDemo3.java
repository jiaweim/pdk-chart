package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.Chart;
import pdk.chart.api.Layer;
import pdk.chart.api.LengthAdjustmentType;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryMarker;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

public class BarChartDemo3 extends ApplicationFrame {
    public BarChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        double[][] data = new double[][]{{(double)4.0F, (double)3.0F, (double)-2.0F, (double)3.0F, (double)6.0F}};
        return DatasetUtils.createCategoryDataset("Series ", "Category ", data);
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = ChartFactory.createBarChart("Bar Chart Demo 3", "Category", "Value", dataset);
        chart.removeLegend();
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setNoDataMessage("NO DATA!");
        plot.setRangePannable(true);
        Paint[] customColours = new Paint[]{new Color(196, 215, 216), new Color(78, 137, 139), new Color(138, 177, 178), new Color(19, 97, 100)};
        CategoryItemRenderer renderer = new CustomRenderer(customColours);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);
        ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, (double)0.0F);
        renderer.setDefaultPositiveItemLabelPosition(p);
        plot.setRenderer(renderer);
        CategoryMarker marker = new CategoryMarker("Category 3");
        marker.setLabel("Special");
        marker.setPaint(new Color(221, 255, 221, 128));
        marker.setAlpha(0.5F);
        marker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        marker.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        marker.setLabelOffsetType(LengthAdjustmentType.CONTRACT);
        plot.addDomainMarker(marker, Layer.BACKGROUND);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLowerMargin(0.15);
        rangeAxis.setUpperMargin(0.15);
        NumberAxis rangeAxis2 = new NumberAxis((String)null);
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis2.setLowerMargin(0.15);
        rangeAxis2.setUpperMargin(0.15);
        plot.setRangeAxis(1, rangeAxis2);
        CategoryAxis domainAxis2 = new CategoryAxis((String)null);
        plot.setDomainAxis(1, domainAxis2);
        List<Integer> axisIndices = Arrays.asList(0, 1);
        plot.mapDatasetToDomainAxes(0, axisIndices);
        plot.mapDatasetToRangeAxes(0, axisIndices);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        BarChartDemo3 demo = new BarChartDemo3("Chart: BarChartDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
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
