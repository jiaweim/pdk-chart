package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.BoxAndWhiskerCategoryDataset;
import pdk.chart.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoxAndWhiskerChartDemo1 extends ApplicationFrame {
    public BoxAndWhiskerChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static BoxAndWhiskerCategoryDataset createDataset() {
        int SERIES_COUNT = 3;
        int CATEGORY_COUNT = 5;
        int VALUE_COUNT = 20;
        DefaultBoxAndWhiskerCategoryDataset result = new DefaultBoxAndWhiskerCategoryDataset();

        for (int s = 0; s < SERIES_COUNT; ++s) {
            for (int c = 0; c < CATEGORY_COUNT; ++c) {
                List<Double> values = createValueList((double) 0.0F, (double) 20.0F, VALUE_COUNT);
                result.add(values, "Series " + s, "Category " + c);
            }
        }

        return result;
    }

    private static List<Double> createValueList(double lowerBound, double upperBound, int count) {
        List<Double> result = new ArrayList();

        for (int i = 0; i < count; ++i) {
            double v = lowerBound + Math.random() * (upperBound - lowerBound);
            result.add(v);
        }

        return result;
    }

    private static Chart createChart(BoxAndWhiskerCategoryDataset dataset) {
        Chart chart = JChart.boxAndWhisker("Box and Whisker Chart Demo 1", "Category", "Value", dataset, true);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        plot.setRangePannable(true);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        BoxAndWhiskerChartDemo1 demo = new BoxAndWhiskerChartDemo1("Chart: BoxAndWhiskerChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
