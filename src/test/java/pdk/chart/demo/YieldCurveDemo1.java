package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.AxisState;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTick;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class YieldCurveDemo1 extends ApplicationFrame {
    public static XYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("US$ Treasury Yields");
        Day m1 = new Day(24, 4, 2016);
        Day m3 = new Day(24, 6, 2016);
        Day m6 = new Day(24, 10, 2016);
        Day y1 = new Day(24, 3, 2017);
        Day y2 = new Day(24, 3, 2018);
        Day y3 = new Day(24, 3, 2019);
        Day y5 = new Day(24, 3, 2021);
        Day y7 = new Day(24, 3, 2023);
        Day y10 = new Day(24, 3, 2026);
        Day y20 = new Day(24, 3, 2036);
        s1.add(m1, 0.24);
        s1.add(m3, 0.3);
        s1.add(m6, 0.46);
        s1.add(y1, 0.63);
        s1.add(y2, 0.89);
        s1.add(y3, 1.05);
        s1.add(y5, 1.39);
        s1.add(y7, 1.7);
        s1.add(y10, 1.91);
        s1.add(y20, 2.28);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    public static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.timeLine(dataset, "Date", "Yield", "US$ Treasury Yields");
        chart.removeLegend();
        XYPlot plot = (XYPlot) chart.getPlot();
        GregorianCalendar cal = new GregorianCalendar(2016, 2, 23);
        plot.setDomainAxis(new CustomDateAxis("Date", cal.getTime()));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultShapesVisible(true);
            renderer.setDefaultShapesFilled(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        chart.addSubtitle(new TextTitle("March 2016"));
        TextTitle source = new TextTitle("Source: https://www.treasury.gov/resource-center/data-chart-center/interest-rates/");
        JChartUtils.applyCurrentTheme(chart);
        source.setFont(new Font("Dialog", Font.PLAIN, 9));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        return chart;
    }

    public YieldCurveDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        YieldCurveDemo1 demo = new YieldCurveDemo1("Chart: YieldCurveDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class CustomDateAxis extends DateAxis {
        private final Date base;

        public CustomDateAxis(String label, Date base) {
            super(label);
            this.base = base;
        }

        public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
            List<DateTick> result = new ArrayList();
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(this.base);
            cal.add(2, 1);
            result.add(new DateTick(cal.getTime(), "1M", TextAnchor.TOP_CENTER, TextAnchor.CENTER, (double) 0.0F));
            cal.add(2, 11);
            result.add(new DateTick(cal.getTime(), "1Y", TextAnchor.TOP_CENTER, TextAnchor.CENTER, (double) 0.0F));
            cal.add(1, 1);
            result.add(new DateTick(cal.getTime(), "2Y", TextAnchor.TOP_CENTER, TextAnchor.CENTER, (double) 0.0F));
            cal.add(1, 1);
            result.add(new DateTick(cal.getTime(), "3Y", TextAnchor.TOP_CENTER, TextAnchor.CENTER, (double) 0.0F));
            cal.add(1, 2);
            result.add(new DateTick(cal.getTime(), "5Y", TextAnchor.TOP_CENTER, TextAnchor.CENTER, (double) 0.0F));
            cal.add(1, 5);
            result.add(new DateTick(cal.getTime(), "10Y", TextAnchor.TOP_CENTER, TextAnchor.CENTER, (double) 0.0F));
            cal.add(1, 10);
            result.add(new DateTick(cal.getTime(), "20Y", TextAnchor.TOP_CENTER, TextAnchor.CENTER, (double) 0.0F));
            return result;
        }
    }
}
