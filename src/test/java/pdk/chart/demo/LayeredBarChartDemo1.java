package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import javax.swing.JPanel;
import pdk.chart.JChart;
import pdk.chart.Chart;
import pdk.chart.api.SortOrder;
import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.LayeredBarRenderer;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class LayeredBarChartDemo1 extends ApplicationFrame {
    public LayeredBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)1.0F, series1, category1);
        dataset.addValue((double)4.0F, series1, category2);
        dataset.addValue((double)3.0F, series1, category3);
        dataset.addValue((double)5.0F, series1, category4);
        dataset.addValue((double)5.0F, series1, category5);
        dataset.addValue((double)5.0F, series2, category1);
        dataset.addValue((double)7.0F, series2, category2);
        dataset.addValue((double)6.0F, series2, category3);
        dataset.addValue((double)8.0F, series2, category4);
        dataset.addValue((double)4.0F, series2, category5);
        dataset.addValue((double)4.0F, series3, category1);
        dataset.addValue((double)3.0F, series3, category2);
        dataset.addValue((double)2.0F, series3, category3);
        dataset.addValue((double)3.0F, series3, category4);
        dataset.addValue((double)6.0F, series3, category5);
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar("Layered Bar Chart Demo 1", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        plot.setRangePannable(true);
        plot.setRangeZeroBaselineVisible(true);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LayeredBarRenderer renderer = new LayeredBarRenderer();
        renderer.setDrawBarOutline(false);
        plot.setRenderer(renderer);
        plot.setRowRenderingOrder(SortOrder.DESCENDING);
        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        LayeredBarChartDemo1 demo = new LayeredBarChartDemo1("Chart: LayeredBarChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
