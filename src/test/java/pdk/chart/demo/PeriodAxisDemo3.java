package pdk.chart.demo;

import pdk.chart.AxisType;
import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.PeriodAxis;
import pdk.chart.axis.PeriodAxisLabelInfo;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.Month;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class PeriodAxisDemo3 extends ApplicationFrame {
    public PeriodAxisDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = JChart.bar(dataset, "Day", AxisType.DATE, "Temperature", "Maximum Temperature");
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        PeriodAxis domainAxis = new PeriodAxis("Day");
        domainAxis.setAutoRangeTimePeriodClass(Day.class);
        PeriodAxisLabelInfo[] info = new PeriodAxisLabelInfo[3];
        info[0] = new PeriodAxisLabelInfo(Day.class, new SimpleDateFormat("d"));
        info[1] = new PeriodAxisLabelInfo(Day.class, new SimpleDateFormat("E"),
                new RectangleInsets(2.0, 2.0, 2.0, 2.0), new Font("SansSerif", Font.BOLD, 10),
                Color.BLUE, false, new BasicStroke(0.0F), Color.LIGHT_GRAY);
        info[2] = new PeriodAxisLabelInfo(Month.class, new SimpleDateFormat("MMM"));
        domainAxis.setLabelInfo(info);
        plot.setDomainAxis(domainAxis);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("Temperature");
        s1.add(new Day(1, 4, 2006), 14.5F);
        s1.add(new Day(2, 4, 2006), 11.5F);
        s1.add(new Day(3, 4, 2006), 13.7);
        s1.add(new Day(4, 4, 2006), 10.5F);
        s1.add(new Day(5, 4, 2006), 14.9);
        s1.add(new Day(6, 4, 2006), 15.7);
        s1.add(new Day(7, 4, 2006), 11.5F);
        s1.add(new Day(8, 4, 2006), 9.5F);
        s1.add(new Day(9, 4, 2006), 10.9);
        s1.add(new Day(10, 4, 2006), 14.1);
        s1.add(new Day(11, 4, 2006), 12.3);
        s1.add(new Day(12, 4, 2006), 14.3);
        s1.add(new Day(13, 4, 2006), 19.0F);
        s1.add(new Day(14, 4, 2006), 17.9);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        PeriodAxisDemo3 demo = new PeriodAxisDemo3("PeriodAxisDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
