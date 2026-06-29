package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class XYLineAndShapeRendererDemo2 extends ApplicationFrame {
    public XYLineAndShapeRendererDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart() {
        XYDataset dataset = createDataset(1, (double) 1.0F);
        Chart chart = ChartFactory.line("XYLineAndShapeRenderer Demo 2", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        TextTitle subtitle = new TextTitle("This chart shows various combinations of the useFillPaint and useOutlinePaint flags.");
        subtitle.setFont(new Font("Dialog", 0, 10));
        chart.addSubtitle(subtitle);
        ChartUtils.applyCurrentTheme(chart);
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);
        Shape shape = new Ellipse2D.Double((double) -4.0F, (double) -4.0F, (double) 8.0F, (double) 8.0F);
        XYLineAndShapeRenderer renderer1 = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer1.setDefaultShapesVisible(true);
        renderer1.setSeriesShape(0, shape);
        renderer1.setSeriesPaint(0, Color.RED);
        renderer1.setSeriesFillPaint(0, Color.YELLOW);
        renderer1.setSeriesOutlinePaint(0, Color.GRAY);
        XYDataset dataset2 = createDataset(2, (double) 2.0F);
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);
        renderer2.setSeriesShape(0, shape);
        renderer2.setSeriesPaint(0, Color.RED);
        renderer2.setSeriesFillPaint(0, Color.YELLOW);
        renderer2.setSeriesOutlinePaint(0, Color.GRAY);
        renderer2.setUseFillPaint(true);
        XYDataset dataset3 = createDataset(3, (double) 3.0F);
        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
        plot.setDataset(2, dataset3);
        plot.setRenderer(2, renderer3);
        renderer3.setSeriesShape(0, shape);
        renderer3.setSeriesPaint(0, Color.RED);
        renderer3.setSeriesFillPaint(0, Color.YELLOW);
        renderer3.setSeriesOutlinePaint(0, Color.GRAY);
        renderer3.setUseOutlinePaint(true);
        XYDataset dataset4 = createDataset(4, (double) 4.0F);
        XYLineAndShapeRenderer renderer4 = new XYLineAndShapeRenderer();
        plot.setDataset(3, dataset4);
        plot.setRenderer(3, renderer4);
        renderer4.setSeriesShape(0, shape);
        renderer4.setSeriesPaint(0, Color.RED);
        renderer4.setSeriesFillPaint(0, Color.YELLOW);
        renderer4.setSeriesOutlinePaint(0, Color.GRAY);
        renderer4.setUseOutlinePaint(true);
        renderer4.setUseFillPaint(true);
        XYDataset dataset5 = createDataset(5, (double) 5.0F);
        XYLineAndShapeRenderer renderer5 = new XYLineAndShapeRenderer();
        plot.setDataset(4, dataset5);
        plot.setRenderer(4, renderer5);
        renderer5.setSeriesShape(0, shape);
        renderer5.setSeriesPaint(0, Color.RED);
        renderer5.setSeriesFillPaint(0, Color.YELLOW);
        renderer5.setSeriesOutlinePaint(0, Color.GRAY);
        renderer5.setUseOutlinePaint(true);
        renderer5.setUseFillPaint(true);
        renderer5.setDrawOutlines(false);
        return chart;
    }

    private static XYDataset createDataset(int series, double value) {
        XYSeries series1 = new XYSeries("Series " + series);
        series1.add((double) 1.0F, value);
        series1.add((double) 2.0F, value);
        series1.add((double) 3.0F, value);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        XYLineAndShapeRendererDemo2 demo = new XYLineAndShapeRendererDemo2("Chart: XYLineAndShapeRendererDemo2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
