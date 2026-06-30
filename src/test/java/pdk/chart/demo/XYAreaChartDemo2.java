package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class XYAreaChartDemo2 extends ApplicationFrame {
    public XYAreaChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Random 1");
        double value = (double) 0.0F;
        Day day = new Day();

        for (int i = 0; i < 200; ++i) {
            value = value + Math.random() - (double) 0.5F;
            series1.add(day, value);
            day = (Day) day.next();
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series1);
        return dataset;
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.areaXY("XY Area Chart Demo 2", "Time", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        ValueAxis domainAxis = new DateAxis("Time");
        domainAxis.setLowerMargin((double) 0.0F);
        domainAxis.setUpperMargin((double) 0.0F);
        plot.setDomainAxis(domainAxis);
        plot.setForegroundAlpha(0.5F);
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("#,##0.00")));
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        XYAreaChartDemo2 demo = new XYAreaChartDemo2("XY Area Chart Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
