package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.labels.StandardCategorySeriesLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import javax.swing.*;
import java.awt.*;

public class BarChartDemo2 extends ApplicationFrame {
    public BarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        double[][] data = new double[][]{{1.0, 43.0F, 35.0F, 58.0F, 54.0F, 77.0F, 71.0F, 89.0F},
                {54.0F, 75.0F, 63.0F, 83.0F, 43.0F, 46.0F, 27.0F, 13.0F},
                {41.0F, 33.0F, 22.0F, 34.0F, 62.0F, 32.0F, 42.0F, 34.0F}};
        return DatasetUtils.createCategoryDataset("Series ", "Factor ", data);
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar("Bar Chart Demo 2", "Category", "Score (%)", dataset);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, 100.0);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, new Color(0, 0, 128), 0.0F, 0.0F, Color.BLUE);
        GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, new Color(0, 128, 0), 0.0F, 0.0F, Color.GREEN);
        GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, new Color(128, 0, 0), 0.0F, 0.0F, Color.RED);
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
        renderer.setDrawBarOutline(false);
        renderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        BarChartDemo2 demo = new BarChartDemo2("Chart: BarChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
