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

public class MultiplePieChartDemo1 extends ApplicationFrame {
    public MultiplePieChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(600, 380));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        double[][] data = new double[][]{{(double) 3.0F, (double) 4.0F, (double) 3.0F, (double) 5.0F}, {(double) 5.0F, (double) 7.0F, (double) 6.0F, (double) 8.0F}, {(double) 5.0F, (double) 7.0F, Double.NaN, (double) 3.0F}, {(double) 1.0F, (double) 2.0F, (double) 3.0F, (double) 4.0F}, {(double) 2.0F, (double) 3.0F, (double) 2.0F, (double) 3.0F}};
        CategoryDataset dataset = DatasetUtils.createCategoryDataset("Region ", "Sales/Q", data);
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.createMultiplePieChart("Multiple Pie Chart", dataset, TableOrder.BY_ROW, true, true, false);
        MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
        Chart subchart = plot.getPieChart();
        PiePlot p = (PiePlot) subchart.getPlot();
        p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}"));
        p.setLabelFont(new Font("SansSerif", 0, 8));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        MultiplePieChartDemo1 demo = new MultiplePieChartDemo1("JFreeChart: MultiplePieChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
