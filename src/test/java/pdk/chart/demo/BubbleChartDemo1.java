package pdk.chart.demo;

import pdk.chart.BubbleChart;
import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class BubbleChartDemo1 extends ApplicationFrame {
    public BubbleChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYZDataset dataset) {
        BubbleChart chart = new BubbleChart(dataset, "X", "Y", "Bubble Chart Demo 1");
        chart.setForegroundAlpha(0.65f);
        chart.setPannable(true, true);
        chart.setSeriesPaint(0, Color.BLUE);
        chart.setDomainAxisMargin(0.15, 0.15);
        chart.setRangeAxisMargin(0.15, 0.15);
        return chart;
    }

    public static XYZDataset<String> createDataset() {
        return Data
                .<String>xyz()
                .addSeries("Series 1",
                        new double[]{2.1, 2.3, 2.3, 2.2, 2.2, 1.8, 1.8, 1.9, 2.3, 3.8},
                        new double[]{14.1, 11.1, 10.0, 8.8, 8.7, 8.4, 5.4, 4.1, 4.1, 25.0},
                        new double[]{2.4, 2.7, 2.7, 2.2, 2.2, 2.2, 2.1, 2.2, 1.6, 4.0}
                ).build();
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        return chartPanel;
    }

    static void main() {
        BubbleChartDemo1 demo = new BubbleChartDemo1("BubbleChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
