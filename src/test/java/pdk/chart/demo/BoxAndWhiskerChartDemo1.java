package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.BoxAndWhiskerCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 10:18 AM
 */
public class BoxAndWhiskerChartDemo1 extends ApplicationFrame {

    public BoxAndWhiskerChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static BoxAndWhiskerCategoryDataset<String, String> createDataset() {
        int SERIES_COUNT = 3;
        int CATEGORY_COUNT = 5;
        int VALUE_COUNT = 20;
        Data.BoxAndWhiskerDatasetBuilder<String, String> bwBuilder = Data.boxAndWhisker();
        for (int s = 0; s < SERIES_COUNT; ++s) {
            for (int c = 0; c < CATEGORY_COUNT; ++c) {
                List<Double> values = createValueList(0.0, 20.0, VALUE_COUNT);
                bwBuilder.add(values, "Series " + s, "Category " + c);
            }
        }
        return bwBuilder.build();
    }

    private static List<Double> createValueList(double lowerBound, double upperBound, int count) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            double v = lowerBound + Math.random() * (upperBound - lowerBound);
            result.add(v);
        }
        return result;
    }

    private static Chart createChart(BoxAndWhiskerCategoryDataset<String, String> dataset) {
        Chart chart = JChart.boxAndWhisker(dataset,
                "Category", "Value", "Box and Whisker Chart Demo 1", true);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.domainGridlinesVisible(true)
                .rangePannable(true);
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        BoxAndWhiskerChartDemo1 demo = new BoxAndWhiskerChartDemo1("BoxAndWhiskerChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
