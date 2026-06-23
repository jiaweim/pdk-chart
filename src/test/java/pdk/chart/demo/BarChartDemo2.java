package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.Chart;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.labels.StandardCategorySeriesLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

public class BarChartDemo2 extends ApplicationFrame {
    public BarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        double[][] data = new double[][]{{(double)1.0F, (double)43.0F, (double)35.0F, (double)58.0F, (double)54.0F, (double)77.0F, (double)71.0F, (double)89.0F}, {(double)54.0F, (double)75.0F, (double)63.0F, (double)83.0F, (double)43.0F, (double)46.0F, (double)27.0F, (double)13.0F}, {(double)41.0F, (double)33.0F, (double)22.0F, (double)34.0F, (double)62.0F, (double)32.0F, (double)42.0F, (double)34.0F}};
        return DatasetUtils.createCategoryDataset("Series ", "Factor ", data);
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = ChartFactory.createBarChart("Bar Chart Demo 2", "Category", "Score (%)", dataset);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setRange((double)0.0F, (double)100.0F);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer)plot.getRenderer();
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
