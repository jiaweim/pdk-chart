package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.SymbolAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.internal.ShapeUtils;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class LineChartDemo8 extends ApplicationFrame {
    public LineChartDemo8(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 0.0F, "Series 1", "Category 1");
        dataset.addValue((double) 2.0F, "Series 1", "Category 2");
        dataset.addValue((double) 1.0F, "Series 1", "Category 3");
        dataset.addValue((double) 3.0F, "Series 1", "Category 4");
        dataset.addValue((double) 5.0F, "Series 1", "Category 5");
        dataset.addValue((double) 2.0F, "Series 2", "Category 1");
        dataset.addValue((double) 4.0F, "Series 2", "Category 2");
        dataset.addValue((double) 4.0F, "Series 2", "Category 3");
        dataset.addValue((double) 5.0F, "Series 2", "Category 4");
        dataset.addValue((double) 2.0F, "Series 2", "Category 5");
        dataset.addValue((double) 1.0F, "Series 3", "Category 1");
        dataset.addValue((double) 3.0F, "Series 3", "Category 2");
        dataset.addValue((double) 5.0F, "Series 3", "Category 3");
        dataset.addValue((double) 2.0F, "Series 3", "Category 4");
        dataset.addValue((double) 0.0F, "Series 3", "Category 5");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.line("Line Chart Demo 8", "Category", "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        SymbolAxis rangeAxis = new SymbolAxis("Group", new String[]{"A", "B", "C", "D", "E", "F"});
        plot.setRangeAxis(rangeAxis);
        JChartUtils.applyCurrentTheme(chart);
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
        LineChartDemo8 demo = new LineChartDemo8("Chart: LineChartDemo8.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
