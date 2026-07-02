package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.*;
import pdk.chart.data.time.Minute;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYAreaRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class CompassFormatDemo1 extends ApplicationFrame {
    public CompassFormatDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset createDirectionDataset(int count) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries("Wind Direction");
        RegularTimePeriod start = new Minute();
        double direction = (double) 180.0F;

        for (int i = 0; i < count; ++i) {
            s1.add(start, direction);
            start = start.next();
            direction += (Math.random() - (double) 0.5F) * (double) 15.0F;
            if (direction < (double) 0.0F) {
                direction += (double) 360.0F;
            } else if (direction > (double) 360.0F) {
                direction -= (double) 360.0F;
            }
        }

        dataset.addSeries(s1);
        return dataset;
    }

    private static XYDataset createForceDataset(int count) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries("Wind Force");
        RegularTimePeriod start = new Minute();
        double force = (double) 3.0F;

        for (int i = 0; i < count; ++i) {
            s1.add(start, force);
            start = start.next();
            force = Math.max((double) 0.5F, force + (Math.random() - (double) 0.5F) * (double) 0.5F);
        }

        dataset.addSeries(s1);
        return dataset;
    }

    private static Chart createChart() {
        XYDataset direction = createDirectionDataset(600);
        Chart chart = JChart.timeLine("Time", "Date", "Direction", direction, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.getDomainAxis().setLowerMargin((double) 0.0F);
        plot.getDomainAxis().setUpperMargin((double) 0.0F);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false);
        TickUnits units = new TickUnits();
        units.add(new NumberTickUnit((double) 180.0F, new CompassFormat()));
        units.add(new NumberTickUnit((double) 90.0F, new CompassFormat()));
        units.add(new NumberTickUnit((double) 45.0F, new CompassFormat()));
        units.add(new NumberTickUnit((double) 22.5F, new CompassFormat()));
        rangeAxis.setStandardTickUnits(units);
        plot.setRangeAxis(rangeAxis);
        XYItemRenderer renderer2 = new XYAreaRenderer();
        ValueAxis axis2 = new NumberAxis("Force");
        axis2.setRange((double) 0.0F, (double) 12.0F);
        renderer2.setSeriesPaint(0, new Color(0, 0, 255, 128));
        plot.setDataset(1, createForceDataset(600));
        plot.setRenderer(1, renderer2);
        plot.setRangeAxis(1, axis2);
        plot.mapDatasetToRangeAxis(1, 1);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main(String[] args) {
        CompassFormatDemo1 demo = new CompassFormatDemo1("Chart: CompassFormatDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
