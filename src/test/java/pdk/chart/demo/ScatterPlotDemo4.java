package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYDotRenderer;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class ScatterPlotDemo4 extends ApplicationFrame {
    public ScatterPlotDemo4(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        XYDataset dataset = new SampleXYDataset2();
        Chart chart = JChart.scatter("Scatter Plot Demo 4", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setRangeTickBandPaint(new Color(200, 200, 100, 100));
        XYDotRenderer renderer = new XYDotRenderer();
        renderer.setDotWidth(4);
        renderer.setDotHeight(4);
        plot.setRenderer(renderer);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        NumberAxis domainAxis = (NumberAxis)plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(false);
        plot.getRangeAxis().setInverted(true);
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        ScatterPlotDemo4 demo = new ScatterPlotDemo4("Chart: ScatterPlotDemo4.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
