package pdk.chart.demo;

import pdk.chart.CategoryLayeredBarChart;
import pdk.chart.Chart;
import pdk.chart.model.Data;
import pdk.chart.api.SortOrder;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * A layered bar chart (horizontal orientation).
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 15 Jun 2026, 2:58 PM
 */
public class LayeredBarChartDemo2 extends ApplicationFrame {

    public LayeredBarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4", "Category 5"};
        return Data.<String, String>category()
                .addSeries("First", categories, new double[]{1.0, 4.0, 3.0, 5.0, 5.0})
                .addSeries("Second", categories, new double[]{5.0, 7.0, 6.0, 8.0, 4.0})
                .addSeries("Third", categories, new double[]{4.0, 3.0, 2.0, 3.0, 6.0}).build();
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        CategoryLayeredBarChart chart = new CategoryLayeredBarChart(dataset, "Category", "Value",
                "Layered Bar Chart Demo 2", PlotOrientation.HORIZONTAL);

        chart.setDomainGridlinesVisible(true);
        chart.setRangePannable(true);
        chart.setRangeZeroBaselineVisible(true);

        NumberAxis yAxis = chart.getRangeAxisAsNumber();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0));

        chart.setDrawBarOutline(false);
        chart.setSeriesPaint(0, gp0);
        chart.setSeriesPaint(1, gp1);
        chart.setSeriesPaint(2, gp2);

        chart.setRowRenderingOrder(SortOrder.DESCENDING);

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        LayeredBarChartDemo2 demo = new LayeredBarChartDemo2("LayeredBarChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
