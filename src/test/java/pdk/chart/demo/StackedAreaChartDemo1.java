package pdk.chart.demo;

import pdk.chart.CategoryStackedAreaChart;
import pdk.chart.Chart;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
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
        dataset.addValue(1.0, "S1", "C1");
        dataset.addValue(2.0, "S1", "C2");
        dataset.addValue(3.0, "S1", "C3");
        dataset.addValue(4.0, "S1", "C4");
        dataset.addValue(5.0, "S1", "C5");
        dataset.addValue(6.0, "S1", "C6");
        dataset.addValue(7.0, "S1", "C7");
        dataset.addValue(8.0, "S1", "C8");
        dataset.addValue(6.0, "S2", "C1");
        dataset.addValue(3.0, "S2", "C2");
        dataset.addValue(4.0, "S2", "C3");
        dataset.addValue(3.0, "S2", "C4");
        dataset.addValue(9.0, "S2", "C5");
        dataset.addValue(7.0, "S2", "C6");
        dataset.addValue(2.0, "S2", "C7");
        dataset.addValue(3.0, "S2", "C8");
        dataset.addValue(1.0, "S3", "C1");
        dataset.addValue(7.0, "S3", "C2");
        dataset.addValue(6.0, "S3", "C3");
        dataset.addValue(7.0, "S3", "C4");
        dataset.addValue(4.0, "S3", "C5");
        dataset.addValue(5.0, "S3", "C6");
        dataset.addValue(3.0, "S3", "C7");
        dataset.addValue(1.0, "S3", "C8");
        return dataset;
    }

    public static Chart createChart(CategoryDataset dataset) {
        CategoryStackedAreaChart chart = new CategoryStackedAreaChart(dataset,
                "Category", "Value", "Stacked Area Chart");
        chart.setPlotForegroundAlpha(0.85F);
        CategoryAxis domainAxis = chart.getDomainAxis();
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        domainAxis.setCategoryMargin(0.0);

        NumberAxis rangeAxis = chart.getRangeAxisAsNumber();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.setDefaultItemLabelsVisible(true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        StackedAreaChartDemo1 demo = new StackedAreaChartDemo1("StackedAreaChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
