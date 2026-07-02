package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.JChart;
import pdk.chart.annotations.XYPointerAnnotation;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.NumberAxis;
import pdk.chart.block.BlockContainer;
import pdk.chart.block.BorderArrangement;
import pdk.chart.block.EmptyBlock;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.CompositeTitle;

import javax.swing.*;
import java.awt.*;

public class AnnotationDemo2 extends ApplicationFrame {
    public AnnotationDemo2(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static XYDataset createDataset1() {
        XYSeries s1 = new XYSeries("Random Data 1");
        s1.add((double) 1.0F, 181.8);
        s1.add((double) 2.0F, 167.3);
        s1.add((double) 3.0F, 153.8);
        s1.add((double) 4.0F, 167.6);
        s1.add((double) 5.0F, 158.8);
        s1.add((double) 6.0F, 148.3);
        s1.add((double) 7.0F, 153.9);
        s1.add((double) 8.0F, 142.7);
        s1.add((double) 9.0F, 123.2);
        s1.add((double) 10.0F, 131.8);
        s1.add((double) 11.0F, 139.6);
        s1.add((double) 12.0F, 142.9);
        s1.add((double) 13.0F, 138.7);
        s1.add((double) 14.0F, 137.3);
        s1.add((double) 15.0F, 143.9);
        s1.add((double) 16.0F, 139.8);
        s1.add((double) 17.0F, (double) 137.0F);
        s1.add((double) 18.0F, 132.8);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    public static XYDataset createDataset2() {
        XYSeries s2 = new XYSeries("Random Data 2");
        s2.add((double) 1.0F, 429.6);
        s2.add((double) 2.0F, 323.2);
        s2.add((double) 3.0F, 417.2);
        s2.add((double) 4.0F, 624.1);
        s2.add((double) 5.0F, 422.6);
        s2.add((double) 6.0F, 619.2);
        s2.add((double) 7.0F, (double) 416.5F);
        s2.add((double) 8.0F, 512.7);
        s2.add((double) 9.0F, (double) 501.5F);
        s2.add((double) 10.0F, 306.1);
        s2.add((double) 11.0F, 410.3);
        s2.add((double) 12.0F, 511.7);
        s2.add((double) 13.0F, (double) 611.0F);
        s2.add((double) 14.0F, 709.6);
        s2.add((double) 15.0F, 613.2);
        s2.add((double) 16.0F, 711.6);
        s2.add((double) 17.0F, 708.8);
        s2.add((double) 18.0F, 501.6);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s2);
        return dataset;
    }

    public static Chart createChart() {
        XYDataset dataset = createDataset1();
        Chart chart = JChart.line(dataset, "Date", "Price Per Unit", "Annotation Demo 2");
        chart.removeLegend();
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        NumberAxis axis1 = (NumberAxis) plot.getRangeAxis();
        axis1.setAutoRangeIncludesZero(false);
        NumberAxis axis2 = new NumberAxis("Secondary");
        axis2.setAutoRangeIncludesZero(false);
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, createDataset2());
        plot.mapDatasetToRangeAxis(1, 1);
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultShapesFilled(true);
        XYPointerAnnotation annotation1 = new XYPointerAnnotation("Annotation 1 (2.0, 167.3)", (double) 2.0F, 167.3, (-Math.PI / 4D));
        annotation1.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        annotation1.setPaint(Color.RED);
        annotation1.setArrowPaint(Color.RED);
        renderer.addAnnotation(annotation1);
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true, true);
        renderer2.setSeriesPaint(0, Color.black);
        renderer.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        XYPointerAnnotation annotation2 = new XYPointerAnnotation("Annotation 2 (15.0, 613.2)", (double) 15.0F, 613.2, (Math.PI / 2D));
        annotation2.setTextAnchor(TextAnchor.TOP_CENTER);
        renderer2.addAnnotation(annotation2);
        plot.setRenderer(1, renderer2);
        LegendTitle legend1 = new LegendTitle(renderer);
        LegendTitle legend2 = new LegendTitle(renderer2);
        BlockContainer container = new BlockContainer(new BorderArrangement());
        container.add(legend1, RectangleEdge.LEFT);
        container.add(legend2, RectangleEdge.RIGHT);
        container.add(new EmptyBlock((double) 2000.0F, (double) 0.0F));
        CompositeTitle legends = new CompositeTitle(container);
        legends.setBackgroundPaint(Color.WHITE);
        legends.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legends);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static void main(String[] args) {
        AnnotationDemo2 demo = new AnnotationDemo2("Chart: AnnotationDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
