package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import javax.swing.JPanel;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.StatisticalBarRenderer;

import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.statistics.DefaultStatisticalCategoryDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

public class StatisticalBarChartDemo1 extends ApplicationFrame {
    private static final long serialVersionUID = 1L;

    public static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.line("Statistical Bar Chart Demo 1", "Type", "Value", dataset);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);
        StatisticalBarRenderer renderer = new StatisticalBarRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setErrorIndicatorPaint(Color.black);
        renderer.setIncludeBaseInRange(false);
        plot.setRenderer(renderer);
        ChartUtils.applyCurrentTheme(chart);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelPaint(Color.YELLOW);
        renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE6, TextAnchor.BOTTOM_CENTER));
        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        return chart;
    }

    public static CategoryDataset createDataset() {
        DefaultStatisticalCategoryDataset dataset = new DefaultStatisticalCategoryDataset();
        dataset.add((double)10.0F, 2.4, "Row 1", "Column 1");
        dataset.add((double)15.0F, 4.4, "Row 1", "Column 2");
        dataset.add((double)13.0F, 2.1, "Row 1", "Column 3");
        dataset.add((double)7.0F, 1.3, "Row 1", "Column 4");
        dataset.add((double)22.0F, 2.4, "Row 2", "Column 1");
        dataset.add((double)18.0F, 4.4, "Row 2", "Column 2");
        dataset.add((double)28.0F, 2.1, "Row 2", "Column 3");
        dataset.add((double)17.0F, 1.3, "Row 2", "Column 4");
        return dataset;
    }

    public StatisticalBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        CategoryDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        StatisticalBarChartDemo1 demo = new StatisticalBarChartDemo1("Chart: StatisticalBarChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
