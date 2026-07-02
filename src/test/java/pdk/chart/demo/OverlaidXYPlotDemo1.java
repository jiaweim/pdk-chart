package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickMarkPosition;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.DatasetRenderingOrder;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StandardXYItemRenderer;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class OverlaidXYPlotDemo1 extends ApplicationFrame {
    public OverlaidXYPlotDemo1(String title) {
        super(title);
        JPanel panel = createDemoPanel();
        panel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(panel);
    }

    private static Chart createChart() {
        IntervalXYDataset data1 = createDataset1();
        XYItemRenderer renderer1 = new XYBarRenderer(0.2);
        renderer1.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
        DateAxis domainAxis = new DateAxis("Date");
        domainAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        ValueAxis rangeAxis = new NumberAxis("Value");
        XYPlot plot = new XYPlot(data1, domainAxis, rangeAxis, renderer1);
        XYDataset data2 = createDataset2();
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
        plot.setDataset(1, data2);
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        Chart chart = new Chart("Overlaid XYPlot Demo 1", Chart.DEFAULT_TITLE_FONT, plot, true);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset createDataset1() {
        TimeSeries series1 = new TimeSeries("Series 1");
        series1.add(new Day(1, 3, 2002), 12353.3);
        series1.add(new Day(2, 3, 2002), 13734.4);
        series1.add(new Day(3, 3, 2002), 14525.3);
        series1.add(new Day(4, 3, 2002), 13984.3);
        series1.add(new Day(5, 3, 2002), 12999.4);
        series1.add(new Day(6, 3, 2002), 14274.3);
        series1.add(new Day(7, 3, 2002), (double) 15943.5F);
        series1.add(new Day(8, 3, 2002), 14845.3);
        series1.add(new Day(9, 3, 2002), 14645.4);
        series1.add(new Day(10, 3, 2002), 16234.6);
        series1.add(new Day(11, 3, 2002), 17232.3);
        series1.add(new Day(12, 3, 2002), 14232.2);
        series1.add(new Day(13, 3, 2002), 13102.2);
        series1.add(new Day(14, 3, 2002), 14230.2);
        series1.add(new Day(15, 3, 2002), 11235.2);
        return new TimeSeriesCollection(series1);
    }

    private static XYDataset createDataset2() {
        TimeSeries series2 = new TimeSeries("Series 2");
        series2.add(new Day(3, 3, 2002), 16853.2);
        series2.add(new Day(4, 3, 2002), 19642.3);
        series2.add(new Day(5, 3, 2002), (double) 18253.5F);
        series2.add(new Day(6, 3, 2002), 15352.3);
        series2.add(new Day(7, 3, 2002), (double) 13532.0F);
        series2.add(new Day(8, 3, 2002), 12635.3);
        series2.add(new Day(9, 3, 2002), 13998.2);
        series2.add(new Day(10, 3, 2002), 11943.2);
        series2.add(new Day(11, 3, 2002), 16943.9);
        series2.add(new Day(12, 3, 2002), 17843.2);
        series2.add(new Day(13, 3, 2002), 16495.3);
        series2.add(new Day(14, 3, 2002), 17943.6);
        series2.add(new Day(15, 3, 2002), 18500.7);
        series2.add(new Day(16, 3, 2002), 19595.9);
        TimeSeriesCollection tsc = new TimeSeriesCollection(series2);
        return tsc;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }

    static void main(String[] args) {
        OverlaidXYPlotDemo1 demo = new OverlaidXYPlotDemo1("JFreeChart: OverlaidXYPlotDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
