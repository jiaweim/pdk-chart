package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickUnit;
import pdk.chart.axis.DateTickUnitType;
import pdk.chart.data.time.Month;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYSeriesLabelGenerator;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;


public class TimeSeriesDemo3 extends ApplicationFrame {
    public TimeSeriesDemo3(String title) {
        super(title);
        XYDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Series 1");
        series1.add(new Month(1, 2002), 500.2);
        series1.add(new Month(2, 2002), 694.1);
        series1.add(new Month(3, 2002), 734.4);
        series1.add(new Month(4, 2002), 453.2);
        series1.add(new Month(5, 2002), 500.2);
        series1.add(new Month(6, 2002), 345.6);
        series1.add(new Month(7, 2002), 500.2);
        series1.add(new Month(8, 2002), 694.1);
        series1.add(new Month(9, 2002), 734.4);
        series1.add(new Month(10, 2002), 453.2);
        series1.add(new Month(11, 2002), 500.2);
        series1.add(new Month(12, 2002), 345.6);
        TimeSeries series2 = new TimeSeries("Series 2");
        series2.add(new Month(1, 2002), 234.1);
        series2.add(new Month(2, 2002), 623.7);
        series2.add(new Month(3, 2002), 642.5);
        series2.add(new Month(4, 2002), 651.4);
        series2.add(new Month(5, 2002), 643.5);
        series2.add(new Month(6, 2002), 785.6);
        series2.add(new Month(7, 2002), 234.1);
        series2.add(new Month(8, 2002), 623.7);
        series2.add(new Month(9, 2002), 642.5);
        series2.add(new Month(10, 2002), 651.4);
        series2.add(new Month(11, 2002), 643.5);
        series2.add(new Month(12, 2002), 785.6);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.timeLine(dataset, "Time", "Value", "Time Series Demo 3");
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 1, new SimpleDateFormat("MMM-yyyy")));
        axis.setVerticalTickLabels(true);
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setSeriesFillPaint(0, Color.RED);
        renderer.setSeriesFillPaint(1, Color.WHITE);
        renderer.setUseFillPaint(true);
        renderer.setLegendItemToolTipGenerator(new StandardXYSeriesLabelGenerator("Tooltip {0}"));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        TimeSeriesDemo3 demo = new TimeSeriesDemo3("Time Series Demo 3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
