package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.Layer;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryMarker;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;

public class CategoryMarkerDemo2 extends ApplicationFrame {
    public CategoryMarkerDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 21.0F, "Series 1", "Category 1");
        dataset.addValue((double) 50.0F, "Series 1", "Category 2");
        dataset.addValue((double) 152.0F, "Series 1", "Category 3");
        dataset.addValue((double) 184.0F, "Series 1", "Category 4");
        dataset.addValue((double) 299.0F, "Series 1", "Category 5");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.line("Category Marker Demo 2", "Category", "Count", dataset);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setDefaultFillPaint(Color.WHITE);
        CategoryMarker marker = new CategoryMarker("Category 4", new Color(0, 0, 255, 25), new BasicStroke(1.0F));
        marker.setDrawAsLine(false);
        marker.setAlpha(1.0F);
        marker.setLabel("Marker Label");
        marker.setLabelFont(new Font("Dialog", 0, 11));
        marker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        marker.setLabelOffset(new RectangleInsets((double) 2.0F, (double) 5.0F, (double) 2.0F, (double) 5.0F));
        plot.addDomainMarker(marker, Layer.BACKGROUND);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        CategoryMarkerDemo2 demo = new CategoryMarkerDemo2("Chart: CategoryMarkerDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
