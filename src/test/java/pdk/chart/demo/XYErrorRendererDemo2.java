package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import pdk.chart.JChartUtils;
import pdk.chart.Chart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYErrorRenderer;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.YIntervalSeries;
import pdk.chart.data.xy.YIntervalSeriesCollection;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class XYErrorRendererDemo2 extends ApplicationFrame {
    public XYErrorRendererDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        NumberAxis yAxis = new NumberAxis("Y");
        XYErrorRenderer renderer = new XYErrorRenderer();
        renderer.setDefaultLinesVisible(true);
        renderer.setDefaultShapesVisible(false);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        Chart chart = new Chart("XYErrorRenderer Demo 2", plot);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset createDataset() {
        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        YIntervalSeries s1 = new YIntervalSeries("Series 1");
        s1.add((double)1.0F, (double)10.0F, (double)9.0F, (double)11.0F);
        s1.add((double)10.0F, 6.1, 4.34, 7.54);
        s1.add(17.8, (double)4.5F, 3.1, 5.8);
        YIntervalSeries s2 = new YIntervalSeries("Series 2");
        s2.add((double)3.0F, (double)7.0F, (double)6.0F, (double)8.0F);
        s2.add((double)13.0F, (double)13.0F, (double)11.5F, (double)14.5F);
        s2.add((double)24.0F, 16.1, 14.34, 17.54);
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        XYErrorRendererDemo2 demo = new XYErrorRendererDemo2("Chart: XYErrorRendererDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
