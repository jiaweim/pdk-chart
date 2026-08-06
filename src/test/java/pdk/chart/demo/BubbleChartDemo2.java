package pdk.chart.demo;

import pdk.chart.BubbleChart;
import pdk.chart.Chart;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.labels.BubbleXYItemLabelGenerator;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.renderer.xy.XYBubbleRenderer;
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
        BubbleChart chart = new BubbleChart(XYBubbleRenderer.ScaleType.SCALE_ON_BOTH_AXES, dataset,
                "X", "Y", "Bubble Chart Demo 2");

        chart.withPlotForegroundAlpha(0.65f)
                .withPannable(true, true);

        chart.getRenderer()
                .withSeriesPaint(0, Color.BLUE)
                .withDefaultItemLabelGenerator(new BubbleXYItemLabelGenerator())
                .withDefaultItemLabelsVisible(true)
                .withDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));

        chart.getDomainAxisAsNumber().withRange(0.0, 10.0);
        chart.getRangeAxisAsNumber().withRange(0.0, 10.0);
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
