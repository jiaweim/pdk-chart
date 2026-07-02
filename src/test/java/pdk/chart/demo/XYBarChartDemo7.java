package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.Chart;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.SymbolAxis;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.XYIntervalSeries;
import pdk.chart.data.xy.XYIntervalSeriesCollection;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class XYBarChartDemo7 extends ApplicationFrame {
    public XYBarChartDemo7(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = JChart.bar("XYBarChartDemo7", "Date", true, "Y", dataset, PlotOrientation.HORIZONTAL, true, false, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setRangePannable(true);
        plot.setRangeAxis(new DateAxis("Date"));
        SymbolAxis xAxis = new SymbolAxis("Series", new String[]{"S1", "S2", "S3"});
        xAxis.setGridBandsVisible(false);
        plot.setDomainAxis(xAxis);
        XYBarRenderer renderer = (XYBarRenderer)plot.getRenderer();
        renderer.setUseYInterval(true);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset createDataset() {
        Day d0 = new Day(12, 6, 2007);
        Day d1 = new Day(13, 6, 2007);
        Day d2 = new Day(14, 6, 2007);
        Day d3 = new Day(15, 6, 2007);
        Day d4 = new Day(16, 6, 2007);
        Day d5 = new Day(17, 6, 2007);
        XYIntervalSeriesCollection dataset = new XYIntervalSeriesCollection();
        XYIntervalSeries s1 = new XYIntervalSeries("S1");
        XYIntervalSeries s2 = new XYIntervalSeries("S2");
        XYIntervalSeries s3 = new XYIntervalSeries("S3");
        addItem(s1, d0, d1, 0);
        addItem(s1, d3, d3, 0);
        addItem(s2, d0, d5, 1);
        addItem(s3, d2, d4, 2);
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        dataset.addSeries(s3);
        return dataset;
    }

    private static void addItem(XYIntervalSeries s, RegularTimePeriod p0, RegularTimePeriod p1, int index) {
        s.add((double)index, (double)index - 0.45, (double)index + 0.45, (double)p0.getFirstMillisecond(), (double)p0.getFirstMillisecond(), (double)p1.getLastMillisecond());
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        XYBarChartDemo7 demo = new XYBarChartDemo7("Chart : XYBarChartDemo7.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
