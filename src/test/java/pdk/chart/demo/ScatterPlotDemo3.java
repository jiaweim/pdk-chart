package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartRenderingInfo;
import pdk.chart.JChart;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 30 Jun 2026, 2:25 PM
 */
public class ScatterPlotDemo3 extends ApplicationFrame {

    public ScatterPlotDemo3(String title) {
        super(title);
        JPanel demoPanel = createDemoPanel();
        demoPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(demoPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.scatter("Scatter Plot Demo 3", "X", "Y",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = chart.getXYPlot();
        plot.domainCrosshairVisible(true)
                .domainCrosshairLockedOnData(true)
                .rangeCrosshairVisible(true)
                .rangeCrosshairLockedOnData(true)
                .domainZeroBaselineVisible(true)
                .rangeZeroBaselineVisible(true)
                .domainPannable(true)
                .rangePannable(true);
        plot.getDomainAxisAsNumber()
                .autoRangeIncludesZero(false);

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(new SampleXYDataset2());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        panel.addChartMouseListener(new MyChartMouseListener(panel));
        return panel;
    }

    static void main() {
        ScatterPlotDemo3 demo = new ScatterPlotDemo3("Chart: ScatterPlotDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyChartMouseListener implements ChartMouseListener {

        ChartPanel panel;

        public MyChartMouseListener(ChartPanel panel) {
            this.panel = panel;
        }

        public void chartMouseClicked(ChartMouseEvent event) {
            int x = event.getTrigger().getX();
            int y = event.getTrigger().getY();
            Point2D p = this.panel.translateScreenToJava2D(new Point(x, y));
            XYPlot plot = (XYPlot) this.panel.getChart().getPlot();
            ChartRenderingInfo info = this.panel.getChartRenderingInfo();
            Rectangle2D dataArea = info.getPlotInfo().getDataArea();
            double xx = plot.getDomainAxis().java2DToValue(p.getX(), dataArea, plot.getDomainAxisEdge());
            double yy = plot.getRangeAxis().java2DToValue(p.getY(), dataArea, plot.getRangeAxisEdge());
            ValueAxis domainAxis = plot.getDomainAxis();
            ValueAxis rangeAxis = plot.getRangeAxis();
            double xxx = domainAxis.valueToJava2D(xx, dataArea, plot.getDomainAxisEdge());
            double yyy = rangeAxis.valueToJava2D(yy, dataArea, plot.getRangeAxisEdge());
            Point2D p2 = this.panel.translateJava2DToScreen(new Point2D.Double(xxx, yyy));
            System.out.println("Mouse coordinates are (" + x + ", " + y + "), in data space = (" + xx + ", " + yy + ").");
            System.out.println("--> (" + p2.getX() + ", " + p2.getY() + ")");
        }

        public void chartMouseMoved(ChartMouseEvent event) {
        }
    }
}
