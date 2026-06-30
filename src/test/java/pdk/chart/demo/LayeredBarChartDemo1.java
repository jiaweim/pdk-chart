package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.SortOrder;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * A layered bar chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 15 Jun 2026, 2:28 PM
 */
public class LayeredBarChartDemo1 extends ApplicationFrame {
    public LayeredBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4", "Category 5"};
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("First", categories, new double[]{1.0, 4.0, 3.0, 5.0, 5.0});
        dataset.addSeries("Second", categories, new double[]{5.0, 7.0, 6.0, 8.0, 4.0});
        dataset.addSeries("Third", categories, new double[]{4.0, 3.0, 2.0, 3.0, 6.0});

        return dataset;
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.barLayered("Layered Bar Chart Demo 1", "Category", "Value", dataset,
                PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.domainGridlinesVisible(true)
                .rangePannable(true)
                .rangeZeroBaselineVisible(true)
                .rowRenderingOrder(SortOrder.DESCENDING);
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        plot.getBarRenderer(0)
                .drawBarOutline(false)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64)))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0)))
                .seriesPaint(2, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0)));

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        LayeredBarChartDemo1 demo = new LayeredBarChartDemo1("LayeredBarChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
