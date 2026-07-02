package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.JChart;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.YIntervalSeries;
import pdk.chart.data.xy.YIntervalSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYErrorRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;


public class YIntervalChartDemo2 extends ApplicationFrame {
    public YIntervalChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static void add(YIntervalSeries s, int y, int m, int d, double v, double std) {
        Calendar cal = Calendar.getInstance();
        cal.set(y, m, d);
        s.add((double) cal.getTime().getTime(), v, v - std, v + std);
    }

    private static IntervalXYDataset createDataset() {
        YIntervalSeries<String> s1 = new YIntervalSeries<>("Series 1");
        add(s1, 2005, 4, 16, 1309.0, 13.0);
        add(s1, 2005, 9, 18, 1312.0, 12.0);
        add(s1, 2005, 10, 7, 1309.0, 12.0);
        add(s1, 2006, 0, 12, 1311.0, 12.0);
        add(s1, 2006, 1, 7, 1311.0, 13.0);
        add(s1, 2006, 3, 3, 1309.0, 13.0);
        add(s1, 2006, 3, 4, 1307.0, 13.0);
        add(s1, 2006, 3, 6, 1305.0, 13.0);
        add(s1, 2006, 3, 13, 1303.0, 13.0);
        add(s1, 2006, 3, 25, 1308.0, 13.0);
        add(s1, 2006, 3, 28, 1311.0, 13.0);
        add(s1, 2006, 4, 2, 1306.0, 13.0);
        add(s1, 2006, 4, 15, 1303.0, 13.0);
        add(s1, 2006, 4, 18, 1311.0, 13.0);
        add(s1, 2006, 10, 16, 1301.0, 13.0);
        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = JChart.line(dataset, "Date", "Value", "YIntervalChartDemo2");
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainAxis(new DateAxis("Date"));
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);
        XYErrorRenderer renderer = new XYErrorRenderer();
        renderer.setDefaultLinesVisible(true);
        renderer.setUseFillPaint(true);
        renderer.setDefaultFillPaint(Color.WHITE);
        plot.setRenderer(renderer);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        YIntervalChartDemo2 demo = new YIntervalChartDemo2("YIntervalChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
