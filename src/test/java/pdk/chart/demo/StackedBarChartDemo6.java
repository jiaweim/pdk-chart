package pdk.chart.demo;

import pdk.chart.CategoryStackedBarChart;
import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class StackedBarChartDemo6 extends ApplicationFrame {
    public StackedBarChartDemo6(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        long day = 86400000L;
        dataset.addValue((double) (3L * day), "Series 1", "Category 1");
        dataset.addValue((double) (1L * day), "Series 2", "Category 1");
        dataset.addValue((double) (2L * day), "Series 3", "Category 1");
        dataset.addValue((double) (4L * day), "Series 1", "Category 2");
        dataset.addValue((double) (5L * day), "Series 2", "Category 2");
        dataset.addValue((double) (1L * day), "Series 3", "Category 2");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        CategoryStackedBarChart chart = new CategoryStackedBarChart(dataset, "Stacked Bar Chart Demo 6", "Category", "Value",
                PlotOrientation.HORIZONTAL, true, true);
        chart.setDrawBarOutline(false);
        long millis = System.currentTimeMillis();
        chart.setBase((double) millis);

        DateAxis rangeAxis = new DateAxis("Date");
        rangeAxis.setLowerMargin(0.0F);
        chart.setRangeAxis(rangeAxis);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        StackedBarChartDemo6 demo = new StackedBarChartDemo6("Stacked Bar Chart Demo 6");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
