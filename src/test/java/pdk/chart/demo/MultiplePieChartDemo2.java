package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.TableOrder;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.labels.StandardPieSectionLabelGenerator;
import pdk.chart.plot.pie.MultiplePiePlot;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class MultiplePieChartDemo2 extends ApplicationFrame {
    public MultiplePieChartDemo2(String title) {
        super(title);
        CategoryDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false, true);
        chartPanel.setPreferredSize(new Dimension(600, 380));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        double[][] data = new double[][]{{(double) 3.0F, (double) 4.0F, (double) 3.0F, (double) 5.0F}, {(double) 5.0F, (double) 7.0F, (double) 6.0F, (double) 8.0F}, {(double) 5.0F, (double) 7.0F, (double) 3.0F, (double) 8.0F}, {(double) 1.0F, (double) 2.0F, (double) 3.0F, (double) 4.0F}, {(double) 2.0F, (double) 3.0F, (double) 2.0F, (double) 3.0F}};
        CategoryDataset dataset = DatasetUtils.createCategoryDataset("Region ", "Sales/Q", data);
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.createMultiplePieChart("Multiple Pie Chart", dataset, TableOrder.BY_COLUMN, true, true, false);
        MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineStroke(new BasicStroke(1.0F));
        Chart subchart = plot.getPieChart();
        PiePlot p = (PiePlot) subchart.getPlot();
        p.setBackgroundPaint((Paint) null);
        p.setOutlineStroke((Stroke) null);
        p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));
        p.setMaximumLabelWidth(0.2);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main(String[] args) {
        MultiplePieChartDemo2 demo = new MultiplePieChartDemo2("JFreeChart: MultiplePieChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}