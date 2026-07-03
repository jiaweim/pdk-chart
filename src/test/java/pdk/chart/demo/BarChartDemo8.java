package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * A bar chart with items labels displayed for just one series.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 12 Jun 2026, 9:58 AM
 */
public class BarChartDemo8 extends ApplicationFrame {

    public BarChartDemo8(String title) {
        super(title);
        CategoryDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
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
        Chart chart = JChart.bar(dataset, "Category", "Value", "Bar Chart Demo 8");
        chart.removeLegend();
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getDomainAxis()
                .categoryLabelPositions(CategoryLabelPositions.UP_45);
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .upperMargin(0.15);
        plot.getBarRenderer(0)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .seriesItemLabelsVisible(0, true);

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        BarChartDemo8 demo = new BarChartDemo8("BarChartDemo8.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
