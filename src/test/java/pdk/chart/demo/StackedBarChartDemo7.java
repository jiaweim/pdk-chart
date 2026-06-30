package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.StackedBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class StackedBarChartDemo7 extends ApplicationFrame {
    public StackedBarChartDemo7(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(32.4, "Series 1", "Category 1");
        dataset.addValue(17.8, "Series 2", "Category 1");
        dataset.addValue(27.7, "Series 3", "Category 1");
        dataset.addValue(43.2, "Series 1", "Category 2");
        dataset.addValue(15.6, "Series 2", "Category 2");
        dataset.addValue(18.3, "Series 3", "Category 2");
        dataset.addValue((double) 23.0F, "Series 1", "Category 3");
        dataset.addValue(111.3, "Series 2", "Category 3");
        dataset.addValue((double) 25.5F, "Series 3", "Category 3");
        dataset.addValue((double) 13.0F, "Series 1", "Category 4");
        dataset.addValue(11.8, "Series 2", "Category 4");
        dataset.addValue((double) 29.5F, "Series 3", "Category 4");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.barStacked("Stacked Bar Chart Demo 7", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
        StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
        renderer.setRenderAsPercentages(true);
        renderer.setDrawBarOutline(false);
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        StackedBarChartDemo7 demo = new StackedBarChartDemo7("Stacked Bar Chart Demo 7");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
