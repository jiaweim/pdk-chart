package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.DatasetRenderingOrder;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.renderer.category.LevelRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class OverlaidBarChartDemo2 extends ApplicationFrame {
    public OverlaidBarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart() {
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        dataset1.addValue((double) 1.0F, "S1", "Category 1");
        dataset1.addValue((double) 4.0F, "S1", "Category 2");
        dataset1.addValue((double) 3.0F, "S1", "Category 3");
        dataset1.addValue((double) 5.0F, "S1", "Category 4");
        dataset1.addValue((double) 5.0F, "S1", "Category 5");
        dataset1.addValue((double) 5.0F, "S2", "Category 1");
        dataset1.addValue((double) 7.0F, "S2", "Category 2");
        dataset1.addValue((double) 6.0F, "S2", "Category 3");
        dataset1.addValue((double) 8.0F, "S2", "Category 4");
        dataset1.addValue((double) 4.0F, "S2", "Category 5");
        CategoryItemRenderer renderer = new BarRenderer();
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);
        plot.setDomainAxis(new CategoryAxis("Category"));
        plot.setRangeAxis(new NumberAxis("Value"));
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        plot.setRangePannable(true);
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        dataset2.addValue((double) 6.0F, "Prior 1", "Category 1");
        dataset2.addValue((double) 7.0F, "Prior 1", "Category 2");
        dataset2.addValue((double) 2.0F, "Prior 1", "Category 3");
        dataset2.addValue((double) 6.0F, "Prior 1", "Category 4");
        dataset2.addValue((double) 6.0F, "Prior 1", "Category 5");
        dataset2.addValue((double) 4.0F, "Prior 2", "Category 1");
        dataset2.addValue((double) 2.0F, "Prior 2", "Category 2");
        dataset2.addValue((double) 1.0F, "Prior 2", "Category 3");
        dataset2.addValue((double) 3.0F, "Prior 2", "Category 4");
        dataset2.addValue((double) 2.0F, "Prior 2", "Category 5");
        CategoryItemRenderer renderer2 = new LevelRenderer();
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        Chart chart = new Chart(plot);
        chart.setTitle("OverlaidBarChartDemo2");
        ChartUtils.applyCurrentTheme(chart);
        renderer2.setSeriesStroke(0, new BasicStroke(2.0F));
        renderer2.setSeriesStroke(1, new BasicStroke(2.0F));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        OverlaidBarChartDemo2 demo = new OverlaidBarChartDemo2("JFreeChart: OverlaidBarChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
