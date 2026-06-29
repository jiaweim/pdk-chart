package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class StackedAreaChartDemo1 extends ApplicationFrame {
    public StackedAreaChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 1.0F, "S1", "C1");
        dataset.addValue((double) 2.0F, "S1", "C2");
        dataset.addValue((double) 3.0F, "S1", "C3");
        dataset.addValue((double) 4.0F, "S1", "C4");
        dataset.addValue((double) 5.0F, "S1", "C5");
        dataset.addValue((double) 6.0F, "S1", "C6");
        dataset.addValue((double) 7.0F, "S1", "C7");
        dataset.addValue((double) 8.0F, "S1", "C8");
        dataset.addValue((double) 6.0F, "S2", "C1");
        dataset.addValue((double) 3.0F, "S2", "C2");
        dataset.addValue((double) 4.0F, "S2", "C3");
        dataset.addValue((double) 3.0F, "S2", "C4");
        dataset.addValue((double) 9.0F, "S2", "C5");
        dataset.addValue((double) 7.0F, "S2", "C6");
        dataset.addValue((double) 2.0F, "S2", "C7");
        dataset.addValue((double) 3.0F, "S2", "C8");
        dataset.addValue((double) 1.0F, "S3", "C1");
        dataset.addValue((double) 7.0F, "S3", "C2");
        dataset.addValue((double) 6.0F, "S3", "C3");
        dataset.addValue((double) 7.0F, "S3", "C4");
        dataset.addValue((double) 4.0F, "S3", "C5");
        dataset.addValue((double) 5.0F, "S3", "C6");
        dataset.addValue((double) 3.0F, "S3", "C7");
        dataset.addValue((double) 1.0F, "S3", "C8");
        return dataset;
    }

    public static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.stackedArea("Stacked Area Chart", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.85F);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin((double) 0.0F);
        domainAxis.setUpperMargin((double) 0.0F);
        domainAxis.setCategoryMargin((double) 0.0F);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setDefaultItemLabelsVisible(true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        StackedAreaChartDemo1 demo = new StackedAreaChartDemo1("Chart: StackedAreaChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
