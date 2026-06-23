package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.YIntervalSeries;
import pdk.chart.data.xy.YIntervalSeriesCollection;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYErrorRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;


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
        s.add((double)cal.getTime().getTime(), v, v - std, v + std);
    }

    private static IntervalXYDataset createDataset() {
        YIntervalSeries s1 = new YIntervalSeries("Series 1");
        add(s1, 2005, 4, 16, (double)1309.0F, (double)13.0F);
        add(s1, 2005, 9, 18, (double)1312.0F, (double)12.0F);
        add(s1, 2005, 10, 7, (double)1309.0F, (double)12.0F);
        add(s1, 2006, 0, 12, (double)1311.0F, (double)12.0F);
        add(s1, 2006, 1, 7, (double)1311.0F, (double)13.0F);
        add(s1, 2006, 3, 3, (double)1309.0F, (double)13.0F);
        add(s1, 2006, 3, 4, (double)1307.0F, (double)13.0F);
        add(s1, 2006, 3, 6, (double)1305.0F, (double)13.0F);
        add(s1, 2006, 3, 13, (double)1303.0F, (double)13.0F);
        add(s1, 2006, 3, 25, (double)1308.0F, (double)13.0F);
        add(s1, 2006, 3, 28, (double)1311.0F, (double)13.0F);
        add(s1, 2006, 4, 2, (double)1306.0F, (double)13.0F);
        add(s1, 2006, 4, 15, (double)1303.0F, (double)13.0F);
        add(s1, 2006, 4, 18, (double)1311.0F, (double)13.0F);
        add(s1, 2006, 10, 16, (double)1301.0F, (double)13.0F);
        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = ChartFactory.createXYLineChart("YIntervalChartDemo2", "Date", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainAxis(new DateAxis("Date"));
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
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

    public static void main(String[] args) {
        YIntervalChartDemo2 demo = new YIntervalChartDemo2("Chart: YIntervalChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
