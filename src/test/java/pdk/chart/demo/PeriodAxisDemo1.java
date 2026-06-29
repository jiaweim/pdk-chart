package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.PeriodAxis;
import pdk.chart.axis.PeriodAxisLabelInfo;
import pdk.chart.data.time.*;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class PeriodAxisDemo1 extends ApplicationFrame {
    public PeriodAxisDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.timeLine("Legal & General Unit Trust Prices", "Date", "Price Per Unit", dataset, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer rr = (XYLineAndShapeRenderer) renderer;
            rr.setDefaultShapesVisible(true);
            rr.setDefaultShapesFilled(true);
            rr.setDefaultItemLabelsVisible(true);
        }

        PeriodAxis domainAxis = new PeriodAxis("Date");
        domainAxis.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));
        domainAxis.setAutoRangeTimePeriodClass(Month.class);
        domainAxis.setMajorTickTimePeriodClass(Month.class);
        PeriodAxisLabelInfo[] info = new PeriodAxisLabelInfo[2];
        info[0] = new PeriodAxisLabelInfo(Month.class, new SimpleDateFormat("MMM"), new RectangleInsets((double) 2.0F, (double) 2.0F, (double) 2.0F, (double) 2.0F), new Font("SansSerif", 1, 10), Color.BLUE, false, new BasicStroke(0.0F), Color.LIGHT_GRAY);
        info[1] = new PeriodAxisLabelInfo(Year.class, new SimpleDateFormat("yyyy"));
        domainAxis.setLabelInfo(info);
        plot.setDomainAxis(domainAxis);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("L&G European Index Trust");
        s1.add(new Month(2, 2001), 181.8);
        s1.add(new Month(3, 2001), 167.3);
        s1.add(new Month(4, 2001), 153.8);
        s1.add(new Month(5, 2001), 167.6);
        s1.add(new Month(6, 2001), 158.8);
        s1.add(new Month(7, 2001), 148.3);
        s1.add(new Month(8, 2001), 153.9);
        s1.add(new Month(9, 2001), 142.7);
        s1.add(new Month(10, 2001), 123.2);
        s1.add(new Month(11, 2001), 131.8);
        s1.add(new Month(12, 2001), 139.6);
        s1.add(new Month(1, 2002), 142.9);
        s1.add(new Month(2, 2002), 138.7);
        s1.add(new Month(3, 2002), 137.3);
        s1.add(new Month(4, 2002), 143.9);
        s1.add(new Month(5, 2002), 139.8);
        s1.add(new Month(6, 2002), (double) 137.0F);
        s1.add(new Month(7, 2002), 132.8);
        TimeSeries s2 = new TimeSeries("L&G UK Index Trust");
        s2.add(new Month(2, 2001), 129.6);
        s2.add(new Month(3, 2001), 123.2);
        s2.add(new Month(4, 2001), 117.2);
        s2.add(new Month(5, 2001), 124.1);
        s2.add(new Month(6, 2001), 122.6);
        s2.add(new Month(7, 2001), 119.2);
        s2.add(new Month(8, 2001), (double) 116.5F);
        s2.add(new Month(9, 2001), 112.7);
        s2.add(new Month(10, 2001), (double) 101.5F);
        s2.add(new Month(11, 2001), 106.1);
        s2.add(new Month(12, 2001), 110.3);
        s2.add(new Month(1, 2002), 111.7);
        s2.add(new Month(2, 2002), (double) 111.0F);
        s2.add(new Month(3, 2002), 109.6);
        s2.add(new Month(4, 2002), 113.2);
        s2.add(new Month(5, 2002), 111.6);
        s2.add(new Month(6, 2002), 108.8);
        s2.add(new Month(7, 2002), 101.6);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        dataset.setXPosition(TimePeriodAnchor.MIDDLE);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        PeriodAxisDemo1 demo = new PeriodAxisDemo1("Chart: PeriodAxisDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
