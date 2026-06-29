package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.DatasetRenderingOrder;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class DualAxisDemo4 extends ApplicationFrame {
    public DualAxisDemo4(String title) {
        super(title);
        JPanel panel = createDemoPanel();
        panel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(panel);
    }

    private static Chart createChart() {
        CategoryDataset dataset1 = createDataset1();
        Chart chart = ChartFactory.bar("Dual Axis Chart", "Category", "Value", dataset1, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
        CategoryItemRenderer renderer1 = plot.getRenderer();
        renderer1.setSeriesPaint(0, Color.RED);
        renderer1.setSeriesPaint(1, Color.YELLOW);
        renderer1.setSeriesPaint(2, Color.GREEN);
        CategoryDataset dataset2 = createDataset2();
        ValueAxis axis2 = new NumberAxis("Secondary");
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);
        CategoryItemRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static CategoryDataset createDataset1() {
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 1.0F, series1, category1);
        dataset.addValue((double) 4.0F, series1, category2);
        dataset.addValue((double) 3.0F, series1, category3);
        dataset.addValue((double) 5.0F, series1, category4);
        dataset.addValue((double) 5.0F, series1, category5);
        dataset.addValue((double) 5.0F, series2, category1);
        dataset.addValue((double) 7.0F, series2, category2);
        dataset.addValue((double) 6.0F, series2, category3);
        dataset.addValue((double) 8.0F, series2, category4);
        dataset.addValue((double) 4.0F, series2, category5);
        dataset.addValue((double) 4.0F, series3, category1);
        dataset.addValue((double) 3.0F, series3, category2);
        dataset.addValue((double) 2.0F, series3, category3);
        dataset.addValue((double) 3.0F, series3, category4);
        dataset.addValue((double) 6.0F, series3, category5);
        return dataset;
    }

    private static CategoryDataset createDataset2() {
        String series1 = "Fourth";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 15.0F, series1, category1);
        dataset.addValue((double) 24.0F, series1, category2);
        dataset.addValue((double) 31.0F, series1, category3);
        dataset.addValue((double) 25.0F, series1, category4);
        dataset.addValue((double) 56.0F, series1, category5);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        DualAxisDemo4 demo = new DualAxisDemo4("Chart: DualAxisDemo4.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
