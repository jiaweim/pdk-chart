package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.data.category.DefaultIntervalCategoryDataset;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.DatasetRenderingOrder;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.IntervalBarRenderer;
import pdk.chart.renderer.category.LevelRenderer;
import pdk.chart.renderer.category.StandardBarPainter;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class OverlaidChartDemo1 extends ApplicationFrame {
    public OverlaidChartDemo1(String title) {
        super(title);
        JPanel panel = createDemoPanel();
        this.setContentPane(panel);
    }

    public static JPanel createDemoPanel() {
        CategoryDataset dataset = createDataset1();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(600, 480));
        return chartPanel;
    }

    private static IntervalCategoryDataset createDataset1() {
        double[] start = new double[]{-4.1, -2.2, -2.1, -1.2, -0.4, -0.2, -0.1, -0.15, (double) 0.0F, -0.1, -0.1, (double) 0.0F, (double) 0.0F, (double) 0.0F, (double) 0.0F};
        double[] end = new double[]{2.9, 3.6, 3.3, (double) 2.5F, 2.8, (double) 2.0F, 2.1, (double) 2.0F, (double) 2.0F, 1.8, 1.7, 1.8, 1.6, 1.2, 1.2};
        DefaultIntervalCategoryDataset dataset = new DefaultIntervalCategoryDataset(new double[][]{start}, new double[][]{end});
        dataset.setCategoryKeys(new String[]{"Dedicated Short Bias", "Managed Futures", "S&P 500", "Long/Short Equity", "Emerging Markets", "Convertible Arbitrage", "Event Driven - Multi Strategy", "Event Driven", "DJ/Credit Suisse Hedge Fund Index", "Event Driven - Distressed", "Multi-Strategy", "Global Macro", "Fixed Income Arbitrage", "Event Driven - Risk Arbitrage", "Equity Market Neutral"});
        return dataset;
    }

    private static CategoryDataset createDataset2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(-1.3, "S1", "Dedicated Short Bias");
        dataset.addValue(0.7, "S1", "Managed Futures");
        dataset.addValue(1.2, "S1", "S&P 500");
        dataset.addValue(1.1, "S1", "Long/Short Equity");
        dataset.addValue(1.4, "S1", "Emerging Markets");
        dataset.addValue(1.05, "S1", "Convertible Arbitrage");
        dataset.addValue(1.2, "S1", "Event Driven - Multi Strategy");
        dataset.addValue(1.1, "S1", "Event Driven");
        dataset.addValue(0.7, "S1", "DJ/Credit Suisse Hedge Fund Index");
        dataset.addValue((double) 1.0F, "S1", "Event Driven - Distressed");
        dataset.addValue(0.8, "S1", "Multi-Strategy");
        dataset.addValue(1.2, "S1", "Global Macro");
        dataset.addValue(0.8, "S1", "Fixed Income Arbitrage");
        dataset.addValue((double) 0.5F, "S1", "Event Driven - Risk Arbitrage");
        dataset.addValue(0.6, "S1", "Equity Market Neutral");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        CategoryAxis xAxis = new CategoryAxis((String) null);
        ValueAxis yAxis = new NumberAxis("Monthly Return (%)");
        IntervalBarRenderer renderer = new IntervalBarRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setSeriesPaint(0, new Color(10, 12, 118));
        renderer.setShadowVisible(false);
        CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.setBackgroundPaint(new Color(230, 230, 230));
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setRangeGridlineStroke(new BasicStroke(0.5F));
        plot.setDataset(1, createDataset2());
        LevelRenderer renderer2 = new LevelRenderer();
        renderer2.setSeriesStroke(0, new BasicStroke(4.0F, 1, 1));
        renderer2.setSeriesPaint(0, Color.RED);
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        Chart chart = new Chart("Interquartile Range and Median: Monthly Returns", plot);
        chart.removeLegend();
        chart.setBackgroundPaint(Color.WHITE);
        return chart;
    }

    public static void main(String[] args) {
        OverlaidChartDemo1 demo = new OverlaidChartDemo1("JFreeChart: OverlaidChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
