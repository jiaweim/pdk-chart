package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
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
        Chart chart = JChart.bar(null, "Category", "Score (%)", dataset);
        chart.removeLegend();
        chart.setBackgroundPaint(Color.YELLOW);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.orientation(PlotOrientation.HORIZONTAL)
                .insets(RectangleInsets.ZERO_INSETS)
                .axisOffset(RectangleInsets.ZERO_INSETS)
                .rangeGridlinesVisible(false);
        plot.getDomainAxis()
                .lowerMargin(0.2)
                .upperMargin(0.2)
                .visible(false);
        plot.getRangeAxisAsNumber()
                .range(0, 100)
                .visible(false);
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
