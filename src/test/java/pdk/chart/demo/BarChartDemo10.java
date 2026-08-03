package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.Chart;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class BarChartDemo10 extends ApplicationFrame {

    public BarChartDemo10(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static DefaultCategoryDataset<String, String> createDataset() {
        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4", "Category 5"};
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("First", categories, new double[]{31.0, 44.0, 33.0, 45.0, 35.0});
        dataset.addSeries("Second", categories, new double[]{45.0, 37.0, 46.0, 38.0, 44.0});
        dataset.addSeries("Third", categories, new double[]{34.0, 43.0, 32.0, 43.0, 36.0});

        return dataset;
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        CategoryBarChart chart = new CategoryBarChart(dataset, "Category", "Value", "Bar Chart Demo 10");
        chart.setDomainGridlinesVisible(true);

        chart.getDomainAxis()
                .withCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions((Math.PI / 6)));
        chart.getRangeAxisAsNumber()
                .withStandardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.getRenderer()
                .withDrawBarOutline(false)
                .withSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64)))
                .withSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0)))
                .withSeriesPaint(2, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0)));

        return chart;
    }

    public static JPanel createDemoPanel() {
        DefaultCategoryDataset<String, String> dataset = createDataset();
        Chart chart = createChart(dataset);
        Animator animator = new Animator(dataset);
        animator.start();
        return new ChartPanel(chart);
    }

    static void main() {
        BarChartDemo10 demo = new BarChartDemo10("BarChartDemo10.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
