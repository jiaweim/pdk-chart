package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.DateAxis;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.date.SerialDate;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;


public class TimeSeriesDemo11 extends ApplicationFrame {
    public TimeSeriesDemo11(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    private static Chart createChart(String title, XYDataset dataset) {
        Chart chart = JChart.timeLine(dataset, "Date", "Price", title);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setOrientation(PlotOrientation.VERTICAL);
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        return chart;
    }

    private static XYDataset createDataset(String name, double base, RegularTimePeriod start, int count) {
        TimeSeries series = new TimeSeries(name);
        RegularTimePeriod period = start;
        double value = base;

        for (int i = 0; i < count; ++i) {
            series.add(period, value);
            period = period.previous();
            value *= (double) 1.0F + (Math.random() - 0.495) / (double) 10.0F;
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        panel.setPreferredSize(new Dimension(800, 600));
        Day today = new Day();
        XYDataset dataset = createDataset("Series 1", (double) 100.0F, today, 365);
        Chart chart1 = createChart("Chart 1 : 1 Year", dataset);
        ChartPanel chartPanel1 = new ChartPanel(chart1, false);
        panel.add(chartPanel1);
        Chart chart2 = createChart("Chart 2 : 6 Months", dataset);
        SerialDate t = today.getSerialDate();
        SerialDate t6m = SerialDate.addMonths(-6, t);
        Day sixMonthsAgo = new Day(t6m);
        XYPlot plot2 = (XYPlot) chart2.getPlot();
        DateAxis axis2 = (DateAxis) plot2.getDomainAxis();
        axis2.setRange(sixMonthsAgo.getStart(), today.getEnd());
        ChartPanel chartPanel2 = new ChartPanel(chart2, false);
        panel.add(chartPanel2);
        Chart chart3 = createChart("Chart 3 : 3 Months", dataset);
        SerialDate t3m = SerialDate.addMonths(-3, t);
        Day threeMonthsAgo = new Day(t3m);
        XYPlot plot3 = (XYPlot) chart3.getPlot();
        DateAxis axis3 = (DateAxis) plot3.getDomainAxis();
        axis3.setRange(threeMonthsAgo.getStart(), today.getEnd());
        ChartPanel chartPanel3 = new ChartPanel(chart3, false);
        panel.add(chartPanel3);
        Chart chart4 = createChart("Chart 4 : 1 Month", dataset);
        SerialDate t1m = SerialDate.addMonths(-1, t);
        Day oneMonthsAgo = new Day(t1m);
        XYPlot plot4 = (XYPlot) chart4.getPlot();
        DateAxis axis4 = (DateAxis) plot4.getDomainAxis();
        axis4.setRange(oneMonthsAgo.getStart(), today.getEnd());
        ChartPanel chartPanel4 = new ChartPanel(chart4, false);
        panel.add(chartPanel4);
        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);
        return panel;
    }

    public static void main(String[] args) {
        TimeSeriesDemo11 demo = new TimeSeriesDemo11("Time Series Demo 11");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
