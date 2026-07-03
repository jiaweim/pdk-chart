package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.MinMaxCategoryRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class MinMaxCategoryPlotDemo1 extends ApplicationFrame {
    public MinMaxCategoryPlotDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0F, "First", "C1");
        dataset.addValue(4.0F, "First", "C2");
        dataset.addValue(3.0F, "First", "C3");
        dataset.addValue(5.0F, "First", "C4");
        dataset.addValue(5.0F, "First", "C5");
        dataset.addValue(7.0F, "First", "C6");
        dataset.addValue(7.0F, "First", "C7");
        dataset.addValue(8.0F, "First", "C8");
        dataset.addValue(5.0F, "Second", "C1");
        dataset.addValue(7.0F, "Second", "C2");
        dataset.addValue(6.0F, "Second", "C3");
        dataset.addValue(8.0F, "Second", "C4");
        dataset.addValue(4.0F, "Second", "C5");
        dataset.addValue(4.0F, "Second", "C6");
        dataset.addValue(2.0F, "Second", "C7");
        dataset.addValue(1.0F, "Second", "C8");
        dataset.addValue(4.0F, "Third", "C1");
        dataset.addValue(3.0F, "Third", "C2");
        dataset.addValue(2.0F, "Third", "C3");
        dataset.addValue(3.0F, "Third", "C4");
        dataset.addValue(6.0F, "Third", "C5");
        dataset.addValue(3.0F, "Third", "C6");
        dataset.addValue(4.0F, "Third", "C7");
        dataset.addValue(3.0F, "Third", "C8");
        return dataset;
    }

    public static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar(dataset, "Category", "Value", "Min/Max Category Plot");
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        MinMaxCategoryRenderer renderer = new MinMaxCategoryRenderer();
        renderer.setDrawLines(false);
        plot.setRenderer(renderer);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        MinMaxCategoryPlotDemo1 demo = new MinMaxCategoryPlotDemo1("Chart: MinMaxCategoryPlotDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
