package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.Chart;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class BarChartDemo10 extends ApplicationFrame {
    public BarChartDemo10(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static DefaultCategoryDataset createDataset() {
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)31.0F, series1, category1);
        dataset.addValue((double)44.0F, series1, category2);
        dataset.addValue((double)33.0F, series1, category3);
        dataset.addValue((double)45.0F, series1, category4);
        dataset.addValue((double)35.0F, series1, category5);
        dataset.addValue((double)45.0F, series2, category1);
        dataset.addValue((double)37.0F, series2, category2);
        dataset.addValue((double)46.0F, series2, category3);
        dataset.addValue((double)38.0F, series2, category4);
        dataset.addValue((double)44.0F, series2, category5);
        dataset.addValue((double)34.0F, series3, category1);
        dataset.addValue((double)43.0F, series3, category2);
        dataset.addValue((double)32.0F, series3, category3);
        dataset.addValue((double)43.0F, series3, category4);
        dataset.addValue((double)36.0F, series3, category5);
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = ChartFactory.bar("Bar Chart Demo 10", "Category", "Value", dataset);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer)plot.getRenderer();
        renderer.setDrawBarOutline(false);
        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions((Math.PI / 6D)));
        return chart;
    }

    public static JPanel createDemoPanel() {
        DefaultCategoryDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        Animator animator = new Animator(dataset);
        animator.start();
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        BarChartDemo10 demo = new BarChartDemo10("Chart: BarChartDemo10.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
