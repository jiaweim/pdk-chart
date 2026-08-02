package pdk.chart.demo;

import pdk.chart.CategoryIntervalBarChart;
import pdk.chart.Chart;
import pdk.chart.model.Data;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * A bar chart that displays intervals.
 * <p>
 * This uses the DefaultIntervalCategoryDataset and IntervalBarRenderer classes.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 15 Jun 2026, 2:27 PM
 */
public class IntervalBarChartDemo1 extends ApplicationFrame {

    public IntervalBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static IntervalCategoryDataset<String, String> createDataset() {
        return Data.<String, String>intervalCategory()
                .addSeries(new double[]{0.1, 0.2, 0.3}, new double[]{0.5, 0.6, 0.7})
                .addSeries(new double[]{0.3, 0.4, 0.5}, new double[]{0.7, 0.8, 0.9})
                .build();
    }

    private static Chart createChart(IntervalCategoryDataset<String, String> dataset) {
        CategoryIntervalBarChart chart = new CategoryIntervalBarChart(dataset, "Category", "Percentage",
                "IntervalBarChartDemo1", PlotOrientation.VERTICAL, true, true);
        NumberAxis yAxis = chart.getRangeAxisAsNumber();
        yAxis.setNumberFormatOverride(new DecimalFormat("0.00%"));

        chart.setDomainGridlinesVisible(true);
        chart.setRangePannable(true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        JFrame demo = new IntervalBarChartDemo1("IntervalBarChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
