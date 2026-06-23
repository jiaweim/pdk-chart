package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.BarRenderer;
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

    private static CategoryDataset createDataset() {
        String series1 = "S1";
        String series2 = "S2";
        String series3 = "S3";
        String category1 = "C1";
        String category2 = "C2";
        String category3 = "C3";
        String category4 = "C4";
        String category5 = "C5";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 1.0F, series1, category1);
        dataset.addValue((double) 4.0F, series1, category2);
        dataset.addValue((double) 3.0F, series1, category3);
        dataset.addValue((double) 5.0F, series1, category4);
        dataset.addValue((double) 5.0F, series1, category5);
        dataset.addValue((double) 5.0F, series2, category1);
        dataset.addValue((double) 7.0F, series2, category2);
        dataset.addValue((double) 6.0F, series2, category3);
        dataset.addValue((double) 8.0F, series2, category4);
        dataset.addValue((double) 4.0F, series2, category5);
        dataset.addValue((double) 4.0F, series3, category1);
        dataset.addValue((double) 3.0F, series3, category2);
        dataset.addValue((double) 2.0F, series3, category3);
        dataset.addValue((double) 3.0F, series3, category4);
        dataset.addValue((double) 6.0F, series3, category5);
        return dataset;
    }

    private static Chart createChart(String title, CategoryDataset dataset) {
        Chart chart = ChartFactory.createBarChart(title, "Category", "Value", dataset);
        chart.removeLegend();
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart1 = createChart("Axis Offsets: 0", createDataset());
        CategoryPlot plot1 = (CategoryPlot) chart1.getPlot();
        plot1.setAxisOffset(RectangleInsets.ZERO_INSETS);
        ChartPanel panel1 = new ChartPanel(chart1);
        panel1.setMinimumDrawWidth(0);
        panel1.setMinimumDrawHeight(0);
        Chart chart2 = createChart("Axis Offsets: 5", createDataset());
        ChartPanel panel2 = new ChartPanel(chart2);
        panel2.setMinimumDrawWidth(0);
        panel2.setMinimumDrawHeight(0);
        CategoryPlot plot2 = (CategoryPlot) chart2.getPlot();
        plot2.setAxisOffset(new RectangleInsets((double) 5.0F, (double) 5.0F, (double) 5.0F, (double) 5.0F));
        DemoPanel demoPanel = new DemoPanel(new GridLayout(2, 1));
        demoPanel.add(panel1);
        demoPanel.add(panel2);
        demoPanel.addChart(chart1);
        demoPanel.addChart(chart2);
        return demoPanel;
    }

    public static void main(String[] args) {
        AxisOffsetsDemo1 demo = new AxisOffsetsDemo1("Chart: AxisOffsetsDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
