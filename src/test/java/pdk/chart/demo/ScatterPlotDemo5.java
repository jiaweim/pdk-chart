package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYDotRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class ScatterPlotDemo5 extends ApplicationFrame {
    public ScatterPlotDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries s1 = new XYSeries("S1");
        XYSeries s2 = new XYSeries("S2");

        for (int i = 0; i < 100; ++i) {
            s1.add(Math.random() * (double) 50.0F, Math.random() * (double) 100.0F);
            s2.add(Math.random() * (double) 50.0F, Math.random() * (double) 100.0F);
        }

        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = ChartFactory.createScatterPlot("Scatter Plot Demo 5", "X", "Y", createDataset());
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint((Paint) null);
        plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        plot.setOutlineVisible(false);
        XYDotRenderer renderer = new XYDotRenderer();
        renderer.setDotWidth(4);
        renderer.setDotHeight(4);
        plot.setRenderer(renderer);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        AttributedString label = new AttributedString("H20");
        label.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 1, 2);
        xAxis.setAttributedLabel(label);
        xAxis.setPositiveArrowVisible(true);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        AttributedString s = new AttributedString("kg x 106");
        s.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 7, 8);
        yAxis.setAttributedLabel(s);
        yAxis.setPositiveArrowVisible(true);
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        ScatterPlotDemo5 demo = new ScatterPlotDemo5("Chart: ScatterPlotDemo5.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
