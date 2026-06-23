package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.axis.NumberAxis;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.data.time.Month;
import pdk.chart.data.time.TimePeriodAnchor;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class CompareToPreviousYearDemo extends ApplicationFrame {
    public CompareToPreviousYearDemo(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel)createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setMouseZoomable(true, true);
        this.setContentPane(chartPanel);
    }

    private static Chart createChart() {
        XYDataset sales2006 = createDataset2006();
        XYDataset sales2007 = createDataset2007();
        DateAxis xAxis = new DateAxis("Date");
        Month jan2007 = new Month(1, 2007);
        Month dec2007 = new Month(12, 2007);
        xAxis.setRange((double)jan2007.getFirstMillisecond(), (double)dec2007.getLastMillisecond());
        xAxis.setDateFormatOverride(new SimpleDateFormat("MMM"));
        xAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setUseFillPaint(true);
        renderer.setDefaultFillPaint(Color.WHITE);
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{1}: {2}", new SimpleDateFormat("MMM-yyyy"), new DecimalFormat("0.00")));
        XYPlot plot = new XYPlot(sales2007, xAxis, new NumberAxis("Sales"), renderer);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        DateAxis hiddenAxis = new DateAxis();
        hiddenAxis.setVisible(false);
        plot.setDomainAxis(1, hiddenAxis);
        plot.setDataset(1, sales2006);
        plot.mapDatasetToDomainAxis(1, 1);
        XYLineAndShapeRenderer renderer2006 = new XYLineAndShapeRenderer();
        renderer2006.setSeriesPaint(0, Color.BLUE);
        renderer2006.setUseFillPaint(true);
        renderer2006.setDefaultFillPaint(Color.WHITE);
        renderer2006.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{1}: {2}", new SimpleDateFormat("MMM-yyyy"), new DecimalFormat("0.00")));
        plot.setRenderer(1, renderer2006);
        Chart chart = new Chart("Sales Comparison Chart", plot);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        DateAxis axis = (DateAxis)plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset createDataset2006() {
        TimeSeries s1 = new TimeSeries("Sales 2006");
        s1.add(new Month(1, 2006), (double)100.0F);
        s1.add(new Month(2, 2006), 102.3);
        s1.add(new Month(3, 2006), 105.7);
        s1.add(new Month(4, 2006), 104.2);
        s1.add(new Month(5, 2006), 114.7);
        s1.add(new Month(6, 2006), 121.7);
        s1.add(new Month(7, 2006), 155.6);
        s1.add(new Month(8, 2006), 143.2);
        s1.add(new Month(9, 2006), 131.9);
        s1.add(new Month(10, 2006), (double)120.0F);
        s1.add(new Month(11, 2006), 109.9);
        s1.add(new Month(12, 2006), 99.6);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.setXPosition(TimePeriodAnchor.MIDDLE);
        return dataset;
    }

    private static XYDataset createDataset2007() {
        TimeSeries s = new TimeSeries("Sales 2007");
        s.add(new Month(1, 2007), 163.9);
        s.add(new Month(2, 2007), 163.8);
        s.add(new Month(3, 2007), (double)162.0F);
        s.add(new Month(4, 2007), 167.1);
        s.add(new Month(5, 2007), (double)170.0F);
        s.add(new Month(6, 2007), 175.7);
        s.add(new Month(7, 2007), 171.9);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s);
        dataset.setXPosition(TimePeriodAnchor.MIDDLE);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        CompareToPreviousYearDemo demo = new CompareToPreviousYearDemo("Chart: CompareToPreviousYearDemo.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
