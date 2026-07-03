package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
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

public class PeriodAxisDemo2 extends ApplicationFrame {
    public PeriodAxisDemo2(String title) {
        super(title);
        XYDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setMouseZoomable(true, true);
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.timeLine(dataset, "Date", "Price Per Unit",
                "Legal & General Unit Trust Prices");
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
        domainAxis.setAutoRangeTimePeriodClass(Day.class);
        PeriodAxisLabelInfo[] info = new PeriodAxisLabelInfo[3];
        info[0] = new PeriodAxisLabelInfo(Day.class, new SimpleDateFormat("d"));
        info[1] = new PeriodAxisLabelInfo(Month.class, new SimpleDateFormat("MMM"),
                new RectangleInsets(2.0F, 2.0F, 2.0F, 2.0F),
                new Font("SansSerif", Font.BOLD, 10), Color.BLUE, false, new BasicStroke(0.0F), Color.LIGHT_GRAY);
        info[2] = new PeriodAxisLabelInfo(Year.class, new SimpleDateFormat("yyyy"));
        domainAxis.setLabelInfo(info);
        plot.setDomainAxis(domainAxis);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("L&G European Index Trust");
        s1.add(new Day(24, 1, 2004), 181.8);
        s1.add(new Day(25, 1, 2004), 167.3);
        s1.add(new Day(26, 1, 2004), 153.8);
        s1.add(new Day(27, 1, 2004), 167.6);
        s1.add(new Day(28, 1, 2004), 158.8);
        s1.add(new Day(29, 1, 2004), 148.3);
        s1.add(new Day(30, 1, 2004), 153.9);
        s1.add(new Day(31, 1, 2004), 142.7);
        s1.add(new Day(1, 2, 2004), 123.2);
        s1.add(new Day(2, 2, 2004), 131.8);
        s1.add(new Day(3, 2, 2004), 139.6);
        s1.add(new Day(4, 2, 2004), 142.9);
        s1.add(new Day(5, 2, 2004), 138.7);
        s1.add(new Day(6, 2, 2004), 137.3);
        s1.add(new Day(7, 2, 2004), 143.9);
        s1.add(new Day(8, 2, 2004), 139.8);
        s1.add(new Day(9, 2, 2004), 137.0);
        s1.add(new Day(10, 2, 2004), 132.8);
        TimeZone zone = TimeZone.getTimeZone("Pacific/Auckland");
        TimeSeriesCollection dataset = new TimeSeriesCollection(zone);
        dataset.addSeries(s1);
        dataset.setXPosition(TimePeriodAnchor.MIDDLE);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        PeriodAxisDemo2 demo = new PeriodAxisDemo2("PeriodAxisDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
