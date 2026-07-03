package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.internal.ShapeUtils;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

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
        dataset.addValue((double) 21.0F, "Series 1", "Category 1");
        dataset.addValue((double) 50.0F, "Series 1", "Category 2");
        dataset.addValue((double) 152.0F, "Series 1", "Category 3");
        dataset.addValue((double) 184.0F, "Series 1", "Category 4");
        dataset.addValue((double) 299.0F, "Series 1", "Category 5");
        dataset.addValue((double) 275.0F, "Series 2", "Category 1");
        dataset.addValue((double) 121.0F, "Series 2", "Category 2");
        dataset.addValue((double) 98.0F, "Series 2", "Category 3");
        dataset.addValue((double) 103.0F, "Series 2", "Category 4");
        dataset.addValue((double) 210.0F, "Series 2", "Category 5");
        dataset.addValue((double) 198.0F, "Series 3", "Category 1");
        dataset.addValue((double) 165.0F, "Series 3", "Category 2");
        dataset.addValue((double) 55.0F, "Series 3", "Category 3");
        dataset.addValue((double) 34.0F, "Series 3", "Category 4");
        dataset.addValue((double) 77.0F, "Series 3", "Category 5");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.line(dataset, "Category", "Count", "Line Chart Demo 7");
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(2, true);
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShape(2, ShapeUtils.createDiamond(4.0F));
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setDefaultFillPaint(Color.WHITE);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        LineChartDemo7 demo = new LineChartDemo7("Chart: LineChartDemo7.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
