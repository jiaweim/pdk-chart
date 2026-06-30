package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategorySeriesLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * A demo that shows the use of the maximum bar width setting to cap the width of bars.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 11 Jun 2026, 4:41 PM
 */
public class BarChartDemo4 extends ApplicationFrame {
    public BarChartDemo4(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(1.0, "First", "Category 1");
        dataset.addValue(5.0, "Second", "Category 1");
        return dataset;
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.bar("Bar Chart Demo 4", null, "Value", dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        plot.getBarRenderer(0)
                .drawBarOutline(false)
                .maximumBarWidth(0.1)
                .legendItemLabelGenerator(new StandardCategorySeriesLabelGenerator("{0} series"));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        BarChartDemo4 demo = new BarChartDemo4("BarChartDemo4.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
