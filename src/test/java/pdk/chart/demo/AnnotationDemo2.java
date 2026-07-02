package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.annotations.XYPointerAnnotation;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.NumberAxis;
import pdk.chart.block.BlockContainer;
import pdk.chart.block.BorderArrangement;
import pdk.chart.block.EmptyBlock;
import pdk.chart.data.xy.XYDataset;
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

    public static XYDataset<String> createDataset1() {
        return Data.createXY("Random Data 1",
                new double[]{
                        1.0, 2.0, 3.0, 4.0, 5.0, 6.0,
                        7.0, 8.0, 9.0, 10.0, 11.0, 12.0,
                        13.0, 14.0, 15.0, 16.0, 17.0, 18.0
                },
                new double[]{
                        181.8, 167.3, 153.8, 167.6, 158.8, 148.3,
                        153.9, 142.7, 123.2, 131.8, 139.6, 142.9,
                        138.7, 137.3, 143.9, 139.8, 137.0, 132.8
                }
        );
    }

    public static XYDataset<String> createDataset2() {
        return Data.createXY("Random Data 2",
                new double[]{
                        1.0, 2.0, 3.0, 4.0, 5.0, 6.0,
                        7.0, 8.0, 9.0, 10.0, 11.0, 12.0,
                        13.0, 14.0, 15.0, 16.0, 17.0, 18.0},
                new double[]{
                        429.6, 323.2, 417.2, 624.1, 422.6, 619.2,
                        416.5, 512.7, 501.5, 306.1, 410.3, 511.7,
                        611.0, 709.6, 613.2, 711.6, 708.8, 501.6
                });
    }

    public static Chart createChart() {
        XYDataset<String> dataset = createDataset1();
        Chart chart = JChart.line(dataset, "Date", "Price Per Unit", "Annotation Demo 2");
        chart.removeLegend();
        XYPlot plot = chart.getXYPlot();
        plot.pannable(true, true);

        NumberAxis axis1 = (NumberAxis) plot.getRangeAxis();
        axis1.setAutoRangeIncludesZero(false);

        NumberAxis axis2 = new NumberAxis("Secondary");
        axis2.setAutoRangeIncludesZero(false);
        plot.addRangeAxis(axis2);
        plot.setDataset(1, createDataset2());
        plot.mapDatasetToRangeAxis(1, 1);

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultShapesFilled(true);

        XYPointerAnnotation annotation1 = new XYPointerAnnotation("Annotation 1 (2.0, 167.3)",
                2.0, 167.3, (-Math.PI / 4));
        annotation1.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        annotation1.setPaint(Color.RED);
        annotation1.setArrowPaint(Color.RED);
        renderer.addAnnotation(annotation1);

        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true, true);
        renderer2.setSeriesPaint(0, Color.black);
        renderer.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        XYPointerAnnotation annotation2 = new XYPointerAnnotation("Annotation 2 (15.0, 613.2)",
                15.0, 613.2, (Math.PI / 2D));
        annotation2.setTextAnchor(TextAnchor.TOP_CENTER);
        renderer2.addAnnotation(annotation2);

        plot.setRenderer(1, renderer2);
        LegendTitle legend1 = new LegendTitle(renderer);
        LegendTitle legend2 = new LegendTitle(renderer2);
        BlockContainer container = new BlockContainer(new BorderArrangement());
        container.add(legend1, RectangleEdge.LEFT);
        container.add(legend2, RectangleEdge.RIGHT);
        container.add(new EmptyBlock(2000.0, 0.0));
        CompositeTitle legends = new CompositeTitle(container);
        legends.setBackgroundPaint(Color.WHITE);
        legends.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legends);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    static void main() {
        AnnotationDemo2 demo = new AnnotationDemo2("AnnotationDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
