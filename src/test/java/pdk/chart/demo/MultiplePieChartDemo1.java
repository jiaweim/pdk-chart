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
        double[][] data = new double[][]{{3.0, 4.0, 3.0, 5.0},
                {5.0, 7.0, 6.0, 8.0},
                {5.0, 7.0, Double.NaN, 3.0},
                {1.0, 2.0, 3.0, 4.0},
                {2.0, 3.0, 2.0, 3.0}};
        CategoryDataset dataset = DatasetUtils.createCategoryDataset("Region ", "Sales/Q", data);
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.pieMultiple(dataset, "Multiple Pie Chart", TableOrder.BY_ROW, true, true, false);
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

    static void main() {
        MultiplePieChartDemo1 demo = new MultiplePieChartDemo1("MultiplePieChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
