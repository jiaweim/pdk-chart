package pdk.chart.demo;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.DefaultDrawingSupplier;
import pdk.chart.plot.DrawingSupplier;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class LineChartDemo5 extends ApplicationFrame {
    public LineChartDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";
        String type1 = "Type 1";
        String type2 = "Type 2";
        String type3 = "Type 3";
        String type4 = "Type 4";
        String type5 = "Type 5";
        String type6 = "Type 6";
        String type7 = "Type 7";
        String type8 = "Type 8";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)1.0F, series1, type1);
        dataset.addValue((double)4.0F, series1, type2);
        dataset.addValue((double)3.0F, series1, type3);
        dataset.addValue((double)5.0F, series1, type4);
        dataset.addValue((double)5.0F, series1, type5);
        dataset.addValue((double)7.0F, series1, type6);
        dataset.addValue((double)7.0F, series1, type7);
        dataset.addValue((double)8.0F, series1, type8);
        dataset.addValue((double)5.0F, series2, type1);
        dataset.addValue((double)7.0F, series2, type2);
        dataset.addValue((double)6.0F, series2, type3);
        dataset.addValue((double)8.0F, series2, type4);
        dataset.addValue((double)4.0F, series2, type5);
        dataset.addValue((double)4.0F, series2, type6);
        dataset.addValue((double)2.0F, series2, type7);
        dataset.addValue((double)1.0F, series2, type8);
        dataset.addValue((double)4.0F, series3, type1);
        dataset.addValue((double)3.0F, series3, type2);
        dataset.addValue((double)2.0F, series3, type3);
        dataset.addValue((double)3.0F, series3, type4);
        dataset.addValue((double)6.0F, series3, type5);
        dataset.addValue((double)3.0F, series3, type6);
        dataset.addValue((double)4.0F, series3, type7);
        dataset.addValue((double)3.0F, series3, type8);
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = ChartFactory.createLineChart("Line Chart Demo 5", "Type", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        Shape[] shapes = new Shape[3];
        int[] xpoints = new int[]{-3, 3, -3};
        int[] ypoints = new int[]{-3, 0, 3};
        shapes[0] = new Polygon(xpoints, ypoints, 3);
        shapes[1] = new Rectangle2D.Double((double)-2.0F, (double)-3.0F, (double)3.0F, (double)6.0F);
        xpoints = new int[]{-3, 3, 3};
        ypoints = new int[]{0, -3, 3};
        shapes[2] = new Polygon(xpoints, ypoints, 3);
        DrawingSupplier supplier = new DefaultDrawingSupplier(DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE, DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE, shapes);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        plot.setDrawingSupplier(supplier);
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[]{10.0F, 6.0F}, 0.0F));
        plot.getRenderer().setSeriesStroke(1, new BasicStroke(2.0F, 1, 1, 1.0F, new float[]{6.0F, 6.0F}, 0.0F));
        plot.getRenderer().setSeriesStroke(2, new BasicStroke(2.0F, 1, 1, 1.0F, new float[]{2.0F, 6.0F}, 0.0F));
        LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);
        rangeAxis.setUpperMargin(0.12);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        LineChartDemo5 demo = new LineChartDemo5("Chart: LineChartDemo5.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
