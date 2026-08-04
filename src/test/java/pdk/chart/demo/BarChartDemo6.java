package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.Chart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class BarChartDemo6 extends ApplicationFrame {

    public BarChartDemo6(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(83.0F, "First", "Factor 1");
        return dataset;
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        CategoryBarChart chart = new CategoryBarChart(dataset, "Category", "Score (%)", null);
        chart.removeLegend();

        chart.withBackgroundPaint(Color.YELLOW)
                .withOrientation(PlotOrientation.HORIZONTAL)
                .withPlotInsets(RectangleInsets.ZERO_INSETS)
                .withAxisOffset(RectangleInsets.ZERO_INSETS)
                .withRangeGridlinesVisible(false);

        chart.getDomainAxis()
                .withLowerMargin(0.2)
                .withUpperMargin(0.2)
                .withVisible(false);
        chart.getRangeAxisAsNumber()
                .withRange(0, 100)
                .withVisible(false);

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        BarChartDemo6 demo = new BarChartDemo6("Chart: BarChartDemo6.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
