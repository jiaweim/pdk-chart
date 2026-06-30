package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.JChart;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.CategoryChartType;
import pdk.chart.Data;
import pdk.chart.plot.CategoryPlot;
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
        Chart chart = JChart.create(dataset, CategoryChartType.BAR_INTERVAL, "IntervalBarChartDemo1",
                "Category", "Percentage", PlotOrientation.VERTICAL,
                true, true);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRangeAxisAsNumber()
                .numberFormatOverride(new DecimalFormat("0.00%"));
        plot.domainGridlinesVisible(true)
                .rangePannable(true);

        ChartUtils.applyCurrentTheme(chart);
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
