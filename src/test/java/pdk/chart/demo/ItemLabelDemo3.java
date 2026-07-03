package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.CategoryItemLabelGenerator;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class ItemLabelDemo3 extends ApplicationFrame {
    public ItemLabelDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 51.0F, "Series 1", "Apples");
        dataset.addValue(44.3, "Series 1", "Bananas");
        dataset.addValue((double) 93.0F, "Series 1", "Oranges");
        dataset.addValue(35.6, "Series 1", "Pears");
        dataset.addValue(75.1, "Series 1", "Plums");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar(dataset, "Category", "Value", "Item Label Demo 3",
                PlotOrientation.VERTICAL, false, true);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        plot.setRangeZeroBaselineVisible(true);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setVisible(false);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setUpperMargin(0.15);
        CategoryItemRenderer renderer = plot.getRenderer();
        CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator("{1}", NumberFormat.getInstance());
        renderer.setDefaultItemLabelGenerator(generator);
        renderer.setDefaultItemLabelFont(new Font("SansSerif", 0, 12));
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, (-Math.PI / 2D)));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        ItemLabelDemo3 demo = new ItemLabelDemo3("Chart: ItemLabelDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
