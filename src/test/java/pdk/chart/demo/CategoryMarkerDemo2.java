package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.Layer;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.Data;
import pdk.chart.plot.CategoryMarker;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;

/**
 * This demo shows a CategoryMarker added to a simple line plot.
 * The marker is displayed as a rectangle in the background of the category.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 3:56 PM
 */
public class CategoryMarkerDemo2 extends ApplicationFrame {

    public CategoryMarkerDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        return Data.createCategory("Series 1",
                new String[]{"Category 1", "Category 2", "Category 3", "Category 4", "Category 5"},
                new double[]{21.0, 50.0, 152.0, 184.0, 299.0});
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.line("Category Marker Demo 2",
                "Category", "Count", dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        plot.getLineAndShapeRenderer()
                .seriesShapesVisible(0, true)
                .drawOutlines(true)
                .useFillPaint(true)
                .defaultFillPaint(Color.WHITE);

        CategoryMarker marker = new CategoryMarker("Category 4",
                new Color(0, 0, 255, 25), new BasicStroke(1.0F));
        marker.setDrawAsLine(false);
        marker.setAlpha(1.0F);
        marker.setLabel("Marker Label");
        marker.setLabelFont(new Font("Dialog", Font.PLAIN, 11));
        marker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        marker.setLabelOffset(new RectangleInsets(2.0, 5.0, 2.0, 5.0));

        plot.addDomainMarker(marker, Layer.BACKGROUND);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        CategoryMarkerDemo2 demo = new CategoryMarkerDemo2("CategoryMarkerDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
