package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.CombinedRangeCategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class CombinedCategoryPlotDemo2 extends ApplicationFrame {
    public CombinedCategoryPlotDemo2(String title) {
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
        String sector1 = "Sector 1";
        String sector2 = "Sector 2";
        String sector3 = "Sector 3";
        String sector4 = "Sector 4";
        result.addValue((double) 11.0F, series1, sector1);
        result.addValue((double) 14.0F, series1, sector2);
        result.addValue((double) 13.0F, series1, sector3);
        result.addValue((double) 15.0F, series1, sector4);
        result.addValue((double) 15.0F, series2, sector1);
        result.addValue((double) 17.0F, series2, sector2);
        result.addValue((double) 16.0F, series2, sector3);
        result.addValue((double) 18.0F, series2, sector4);
        return result;
    }

    private static Chart createChart() {
        CategoryDataset dataset1 = createDataset1();
        CategoryAxis domainAxis1 = new CategoryAxis("Class 1");
        domainAxis1.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis1.setMaximumCategoryLabelWidthRatio(5.0F);
        LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        renderer1.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot subplot1 = new CategoryPlot(dataset1, domainAxis1, (ValueAxis) null, renderer1);
        subplot1.setDomainGridlinesVisible(true);
        CategoryDataset dataset2 = createDataset2();
        CategoryAxis domainAxis2 = new CategoryAxis("Class 2");
        domainAxis2.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis2.setMaximumCategoryLabelWidthRatio(5.0F);
        BarRenderer renderer2 = new BarRenderer();
        renderer2.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot subplot2 = new CategoryPlot(dataset2, domainAxis2, (ValueAxis) null, renderer2);
        subplot2.setDomainGridlinesVisible(true);
        ValueAxis rangeAxis = new NumberAxis("Value");
        CombinedRangeCategoryPlot plot = new CombinedRangeCategoryPlot(rangeAxis);
        plot.setRangePannable(true);
        plot.add(subplot1, 3);
        plot.add(subplot2, 2);
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        Chart chart = new Chart("Combined Range Category Plot Demo", new Font("SansSerif", 1, 12), plot, true);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        String title = "Chart: CombinedCategoryPlotDemo2.java";
        CombinedCategoryPlotDemo2 demo = new CombinedCategoryPlotDemo2(title);
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
