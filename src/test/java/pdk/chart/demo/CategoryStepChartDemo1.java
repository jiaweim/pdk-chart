package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.renderer.category.CategoryStepRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class CategoryStepChartDemo1 extends ApplicationFrame {
    public CategoryStepChartDemo1(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        double[][] data = new double[][]{{(double) 1.0F, (double) 4.0F, (double) 3.0F, (double) 5.0F, (double) 5.0F, (double) 7.0F, (double) 7.0F, (double) 8.0F}, {(double) 5.0F, (double) 7.0F, (double) 6.0F, (double) 8.0F, (double) 4.0F, (double) 4.0F, (double) 2.0F, (double) 1.0F}, {(double) 4.0F, (double) 3.0F, (double) 2.0F, (double) 3.0F, (double) 6.0F, (double) 3.0F, (double) 4.0F, (double) 3.0F}};
        CategoryDataset dataset = DatasetUtils.createCategoryDataset("Series ", "Type ", data);
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        CategoryItemRenderer renderer = new CategoryStepRenderer(true);
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryAxis domainAxis = new CategoryAxis("Category");
        NumberAxis rangeAxis = new NumberAxis("Value");
        CategoryPlot plot = new CategoryPlot(dataset, domainAxis, rangeAxis, renderer);
        plot.setRangePannable(true);
        Chart chart = new Chart("Category Step Chart", plot);
        plot.setAxisOffset(new RectangleInsets((double) 5.0F, (double) 5.0F, (double) 5.0F, (double) 5.0F));
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setLowerMargin((double) 0.0F);
        domainAxis.setUpperMargin((double) 0.0F);
        domainAxis.addCategoryLabelToolTip("Type 1", "The first type.");
        domainAxis.addCategoryLabelToolTip("Type 2", "The second type.");
        domainAxis.addCategoryLabelToolTip("Type 3", "The third type.");
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelAngle((double) 0.0F);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        CategoryStepChartDemo1 demo = new CategoryStepChartDemo1("Chart : CategoryStepChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
