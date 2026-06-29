package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.annotations.XYPolygonAnnotation;
import pdk.chart.api.Layer;
import pdk.chart.data.xy.DefaultXYDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class XYPolygonAnnotationDemo1 extends ApplicationFrame {
    public XYPolygonAnnotationDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static XYDataset createDataset() {
        DefaultXYDataset d = new DefaultXYDataset();
        double[] x1 = new double[]{1.7, 2.2, 2.7, (double) 3.0F, 3.2};
        double[] y1 = new double[]{(double) 4.0F, (double) 3.0F, (double) 6.0F, (double) 1.0F, (double) 9.0F};
        double[][] data1 = new double[][]{x1, y1};
        d.addSeries("Series 1", data1);
        double[] x2 = new double[]{2.1, 2.2, 2.4, 2.7, 3.2};
        double[] y2 = new double[]{(double) 4.5F, (double) 6.0F, (double) 4.0F, (double) 8.0F, 5.1};
        double[][] data2 = new double[][]{x2, y2};
        d.addSeries("Series 2", data2);
        return d;
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.scatter("XYPolygonAnnotationDemo1", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        XYPolygonAnnotation a = new XYPolygonAnnotation(new double[]{(double) 2.0F, (double) 5.0F, (double) 2.5F, (double) 8.0F, (double) 3.0F, (double) 5.0F, (double) 2.5F, (double) 2.0F}, (Stroke) null, (Paint) null, new Color(200, 200, 255, 100));
        a.setToolTipText("Target Zone");
        renderer.addAnnotation(a, Layer.BACKGROUND);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        XYPolygonAnnotationDemo1 demo = new XYPolygonAnnotationDemo1("XYPolygonAnnotationDemo1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
