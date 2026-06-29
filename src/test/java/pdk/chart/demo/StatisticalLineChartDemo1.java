package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.statistics.DefaultStatisticalCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.StatisticalLineAndShapeRenderer;
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

    private static CategoryDataset createDataset() {
        DefaultStatisticalCategoryDataset dataset = new DefaultStatisticalCategoryDataset();
        dataset.add((double) 10.0F, 2.4, "Row 1", "Column 1");
        dataset.add((double) 15.0F, 4.4, "Row 1", "Column 2");
        dataset.add((double) 13.0F, 2.1, "Row 1", "Column 3");
        dataset.add((double) 7.0F, 1.3, "Row 1", "Column 4");
        dataset.add((double) 22.0F, 2.4, "Row 2", "Column 1");
        dataset.add((double) 18.0F, 4.4, "Row 2", "Column 2");
        dataset.add((double) 28.0F, 2.1, "Row 2", "Column 3");
        dataset.add((double) 17.0F, 1.3, "Row 2", "Column 4");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.line("Statistical Line Chart Demo 1", "Type", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setUpperMargin((double) 0.0F);
        domainAxis.setLowerMargin((double) 0.0F);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        StatisticalLineAndShapeRenderer renderer = new StatisticalLineAndShapeRenderer(true, false);
        renderer.setUseSeriesOffset(true);
        plot.setRenderer(renderer);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        StatisticalLineChartDemo1 demo = new StatisticalLineChartDemo1("Chart: StatisticalLineChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
