package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.CombinedDomainCategoryPlot;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class CombinedCategoryPlotDemo1 extends ApplicationFrame {
    public CombinedCategoryPlotDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static CategoryDataset createDataset1() {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String series1 = "First";
        String series2 = "Second";
        String type1 = "Type 1";
        String type2 = "Type 2";
        String type3 = "Type 3";
        String type4 = "Type 4";
        String type5 = "Type 5";
        String type6 = "Type 6";
        String type7 = "Type 7";
        String type8 = "Type 8";
        result.addValue((double) 1.0F, series1, type1);
        result.addValue((double) 4.0F, series1, type2);
        result.addValue((double) 3.0F, series1, type3);
        result.addValue((double) 5.0F, series1, type4);
        result.addValue((double) 5.0F, series1, type5);
        result.addValue((double) 7.0F, series1, type6);
        result.addValue((double) 7.0F, series1, type7);
        result.addValue((double) 8.0F, series1, type8);
        result.addValue((double) 5.0F, series2, type1);
        result.addValue((double) 7.0F, series2, type2);
        result.addValue((double) 6.0F, series2, type3);
        result.addValue((double) 8.0F, series2, type4);
        result.addValue((double) 4.0F, series2, type5);
        result.addValue((double) 4.0F, series2, type6);
        result.addValue((double) 2.0F, series2, type7);
        result.addValue((double) 1.0F, series2, type8);
        return result;
    }

    public static CategoryDataset createDataset2() {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String series1 = "Third";
        String series2 = "Fourth";
        String type1 = "Type 1";
        String type2 = "Type 2";
        String type3 = "Type 3";
        String type4 = "Type 4";
        String type5 = "Type 5";
        String type6 = "Type 6";
        String type7 = "Type 7";
        String type8 = "Type 8";
        result.addValue((double) 11.0F, series1, type1);
        result.addValue((double) 14.0F, series1, type2);
        result.addValue((double) 13.0F, series1, type3);
        result.addValue((double) 15.0F, series1, type4);
        result.addValue((double) 15.0F, series1, type5);
        result.addValue((double) 17.0F, series1, type6);
        result.addValue((double) 17.0F, series1, type7);
        result.addValue((double) 18.0F, series1, type8);
        result.addValue((double) 15.0F, series2, type1);
        result.addValue((double) 17.0F, series2, type2);
        result.addValue((double) 16.0F, series2, type3);
        result.addValue((double) 18.0F, series2, type4);
        result.addValue((double) 14.0F, series2, type5);
        result.addValue((double) 14.0F, series2, type6);
        result.addValue((double) 12.0F, series2, type7);
        result.addValue((double) 11.0F, series2, type8);
        return result;
    }

    private static Chart createChart() {
        CategoryDataset dataset1 = createDataset1();
        NumberAxis rangeAxis1 = new NumberAxis("Value");
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        renderer1.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot subplot1 = new CategoryPlot(dataset1, (CategoryAxis) null, rangeAxis1, renderer1);
        subplot1.setDomainGridlinesVisible(true);
        CategoryDataset dataset2 = createDataset2();
        NumberAxis rangeAxis2 = new NumberAxis("Value");
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer2 = new BarRenderer();
        renderer2.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot subplot2 = new CategoryPlot(dataset2, (CategoryAxis) null, rangeAxis2, renderer2);
        subplot2.setDomainGridlinesVisible(true);
        CategoryAxis domainAxis = new CategoryAxis("Category");
        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
        plot.add(subplot1, 2);
        plot.add(subplot2, 1);
        Chart chart = new Chart("Combined Domain Category Plot Demo", new Font("SansSerif", 1, 12), plot, true);
        JChartUtils.applyCurrentTheme(chart);
        subplot1.setAxisOffset(RectangleInsets.ZERO_INSETS);
        subplot2.setAxisOffset(RectangleInsets.ZERO_INSETS);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        String title = "Chart: CombinedCategoryPlotDemo1.java";
        CombinedCategoryPlotDemo1 demo = new CombinedCategoryPlotDemo1(title);
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
