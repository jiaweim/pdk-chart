package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class AxisOffsetsDemo1 extends ApplicationFrame {
    public AxisOffsetsDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        String[] categories = new String[]{"C1", "C2", "C3", "C4", "C5"};
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("S1", categories, new double[]{1.0, 4.0, 3.0, 5.0, 5.0});
        dataset.addSeries("S2", categories, new double[]{5.0, 7.0, 6.0, 8.0, 4.0});
        dataset.addSeries("S3", categories, new double[]{4.0, 3.0, 2.0, 3.0, 6.0});

        return dataset;
    }

    private static CategoryBarChart createChart(String title, CategoryDataset dataset) {
        CategoryBarChart chart = new CategoryBarChart(dataset, "Category", "Value", title);
        chart.removeLegend();
        chart.setDomainGridlinesVisible(true);

        chart.getRangeAxisAsNumber()
                .withStandardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.getRenderer().withDrawBarOutline(false)
                .withSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64)))
                .withSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0)))
                .withSeriesPaint(2, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0)));

        return chart;
    }

    public static JPanel createDemoPanel() {
        CategoryBarChart chart1 = createChart("Axis Offsets: 0", createDataset());
        chart1.setAxisOffset(RectangleInsets.ZERO_INSETS);
        ChartPanel panel1 = new ChartPanel(chart1);
        panel1.setMinimumDrawWidth(0);
        panel1.setMinimumDrawHeight(0);

        CategoryBarChart chart2 = createChart("Axis Offsets: 5", createDataset());
        ChartPanel panel2 = new ChartPanel(chart2);
        panel2.setMinimumDrawWidth(0);
        panel2.setMinimumDrawHeight(0);
        chart2.setAxisOffset(new RectangleInsets(5.0));

        DemoPanel demoPanel = new DemoPanel(new GridLayout(2, 1));
        demoPanel.add(panel1);
        demoPanel.add(panel2);
        demoPanel.addChart(chart1);
        demoPanel.addChart(chart2);
        return demoPanel;
    }

    static void main() {
        AxisOffsetsDemo1 demo = new AxisOffsetsDemo1("AxisOffsetsDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
