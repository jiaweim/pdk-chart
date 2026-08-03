package pdk.chart.demo;

import pdk.chart.CategoryLineChart;
import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.util.ShapeUtils;

import javax.swing.*;
import java.awt.*;

public class LineChartDemo7 extends ApplicationFrame {
    public LineChartDemo7(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(21.0, "Series 1", "Category 1");
        dataset.addValue(50.0, "Series 1", "Category 2");
        dataset.addValue(152.0, "Series 1", "Category 3");
        dataset.addValue(184.0, "Series 1", "Category 4");
        dataset.addValue(299.0, "Series 1", "Category 5");
        dataset.addValue(275.0, "Series 2", "Category 1");
        dataset.addValue(121.0, "Series 2", "Category 2");
        dataset.addValue(98.0, "Series 2", "Category 3");
        dataset.addValue(103.0, "Series 2", "Category 4");
        dataset.addValue(210.0, "Series 2", "Category 5");
        dataset.addValue(198.0, "Series 3", "Category 1");
        dataset.addValue(165.0, "Series 3", "Category 2");
        dataset.addValue(55.0, "Series 3", "Category 3");
        dataset.addValue(34.0, "Series 3", "Category 4");
        dataset.addValue(77.0, "Series 3", "Category 5");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        CategoryLineChart chart = new CategoryLineChart(dataset, "Category", "Count", "Line Chart Demo 7");
        NumberAxis rangeAxis = chart.getRangeAxisAsNumber();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        chart.setSeriesShapesVisible(0, true);
        chart.setSeriesShapesVisible(1, false);
        chart.setSeriesShapesVisible(2, true);

        chart.setSeriesLinesVisible(2, false);

        chart.setSeriesShape(2, ShapeUtils.createDiamond(4.0F));

        chart.setDrawOutlines(true);
        chart.setUseFillPaint(true);
        chart.setDefaultFillPaint(Color.WHITE);

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        LineChartDemo7 demo = new LineChartDemo7("LineChartDemo7.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
