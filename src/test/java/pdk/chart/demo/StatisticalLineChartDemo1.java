package pdk.chart.demo;

import pdk.chart.CategoryStatisticalLineChart;
import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.DefaultStatisticalCategoryDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class StatisticalLineChartDemo1 extends ApplicationFrame {
    public StatisticalLineChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static DefaultStatisticalCategoryDataset createDataset() {
        DefaultStatisticalCategoryDataset dataset = new DefaultStatisticalCategoryDataset();
        dataset.add(10.0, 2.4, "Row 1", "Column 1");
        dataset.add(15.0, 4.4, "Row 1", "Column 2");
        dataset.add(13.0, 2.1, "Row 1", "Column 3");
        dataset.add(7.0, 1.3, "Row 1", "Column 4");
        dataset.add(22.0, 2.4, "Row 2", "Column 1");
        dataset.add(18.0, 4.4, "Row 2", "Column 2");
        dataset.add(28.0, 2.1, "Row 2", "Column 3");
        dataset.add(17.0, 1.3, "Row 2", "Column 4");
        return dataset;
    }

    private static Chart createChart(DefaultStatisticalCategoryDataset dataset) {
        CategoryStatisticalLineChart chart = new CategoryStatisticalLineChart(dataset, "Type", "Value", "Statistical Line Chart Demo 1");
        chart.setRangePannable(true);
        chart.setUseSeriesOffset(true);

        chart.getDomainAxis()
                .withLowerMargin(0.0)
                .withUpperMargin(0.0);

        NumberAxis rangeAxis = chart.getRangeAxisAsNumber();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        StatisticalLineChartDemo1 demo = new StatisticalLineChartDemo1("StatisticalLineChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
