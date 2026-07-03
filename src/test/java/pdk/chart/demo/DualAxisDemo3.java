package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.DatasetRenderingOrder;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;

public class DualAxisDemo3 extends ApplicationFrame {
    public DualAxisDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        this.setContentPane(chartPanel);
    }

    private static Chart createChart() {
        CategoryDataset dataset1 = createDataset1();
        Chart chart = JChart.bar(dataset1, "Category", "Value", "Dual Axis Chart",
                PlotOrientation.HORIZONTAL);
        LegendTitle legend = (LegendTitle) chart.getSubtitle(0);
        legend.setPosition(RectangleEdge.LEFT);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10.0F);
        CategoryDataset dataset2 = createDataset2();
        ValueAxis axis2 = new NumberAxis("Secondary");
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        CategoryItemRenderer renderer2 = new LineAndShapeRenderer();
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static CategoryDataset createDataset1() {
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        String category6 = "Category 6";
        String category7 = "Category 7";
        String category8 = "Category 8";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 1.0F, series1, category1);
        dataset.addValue((double) 4.0F, series1, category2);
        dataset.addValue((double) 3.0F, series1, category3);
        dataset.addValue((double) 5.0F, series1, category4);
        dataset.addValue((double) 5.0F, series1, category5);
        dataset.addValue((double) 7.0F, series1, category6);
        dataset.addValue((double) 7.0F, series1, category7);
        dataset.addValue((double) 8.0F, series1, category8);
        dataset.addValue((double) 5.0F, series2, category1);
        dataset.addValue((double) 7.0F, series2, category2);
        dataset.addValue((double) 6.0F, series2, category3);
        dataset.addValue((double) 8.0F, series2, category4);
        dataset.addValue((double) 4.0F, series2, category5);
        dataset.addValue((double) 4.0F, series2, category6);
        dataset.addValue((double) 2.0F, series2, category7);
        dataset.addValue((double) 1.0F, series2, category8);
        dataset.addValue((double) 4.0F, series3, category1);
        dataset.addValue((double) 3.0F, series3, category2);
        dataset.addValue((double) 2.0F, series3, category3);
        dataset.addValue((double) 3.0F, series3, category4);
        dataset.addValue((double) 6.0F, series3, category5);
        dataset.addValue((double) 3.0F, series3, category6);
        dataset.addValue((double) 4.0F, series3, category7);
        dataset.addValue((double) 3.0F, series3, category8);
        return dataset;
    }

    private static CategoryDataset createDataset2() {
        String series1 = "Fourth";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        String category6 = "Category 6";
        String category7 = "Category 7";
        String category8 = "Category 8";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 15.0F, series1, category1);
        dataset.addValue((double) 24.0F, series1, category2);
        dataset.addValue((double) 31.0F, series1, category3);
        dataset.addValue((double) 25.0F, series1, category4);
        dataset.addValue((double) 56.0F, series1, category5);
        dataset.addValue((double) 37.0F, series1, category6);
        dataset.addValue((double) 77.0F, series1, category7);
        dataset.addValue((double) 18.0F, series1, category8);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        DualAxisDemo3 demo = new DualAxisDemo3("Chart: DualAxisDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
