package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.labels.BubbleXYItemLabelGenerator;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardXYZToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBubbleRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;

public class BubbleChartDemo2 extends ApplicationFrame {

    public BubbleChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYZDataset dataset) {
        Chart chart = ChartFactory.bubble("Bubble Chart Demo 2", "X", "Y",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRenderer(new XYBubbleRenderer(0));
        plot.setForegroundAlpha(0.65F);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setDefaultItemLabelGenerator(new BubbleXYItemLabelGenerator());
        renderer.setDefaultToolTipGenerator(new StandardXYZToolTipGenerator());
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setRange(0.0, 10.0);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, 10.0);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(new SampleXYZDataset2());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        BubbleChartDemo2 demo = new BubbleChartDemo2("BubbleChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
