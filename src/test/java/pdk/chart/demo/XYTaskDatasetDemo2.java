package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.SymbolAxis;
import pdk.chart.data.gantt.Task;
import pdk.chart.data.gantt.TaskSeries;
import pdk.chart.data.gantt.TaskSeriesCollection;
import pdk.chart.data.gantt.XYTaskDataset;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.Hour;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.CombinedDomainXYPlot;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;


public class XYTaskDatasetDemo2 extends ApplicationFrame {
    public XYTaskDatasetDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static XYPlot createSubplot1(XYDataset dataset) {
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setUseFillPaint(true);
        renderer.setDefaultFillPaint(Color.WHITE);
        renderer.setDefaultShape(new Ellipse2D.Double((double)-4.0F, (double)-4.0F, (double)8.0F, (double)8.0F));
        renderer.setAutoPopulateSeriesShape(false);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setLowerMargin(0.1);
        yAxis.setUpperMargin(0.1);
        XYPlot plot = new XYPlot(dataset, new DateAxis("Time"), yAxis, renderer);
        return plot;
    }

    private static XYPlot createSubplot2(IntervalXYDataset dataset) {
        DateAxis xAxis = new DateAxis("Date/Time");
        SymbolAxis yAxis = new SymbolAxis("Resources", new String[]{"Team A", "Team B", "Team C", "Team D", "Team E"});
        yAxis.setGridBandsVisible(false);
        XYBarRenderer renderer = new XYBarRenderer();
        renderer.setUseYInterval(true);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        return plot;
    }

    private static Chart createChart() {
        CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis("Date/Time"));
        plot.setDomainPannable(true);
        plot.add(createSubplot1(createDataset1()));
        plot.add(createSubplot2(createDataset2()));
        Chart chart = new Chart("XYTaskDatasetDemo2", plot);
        chart.setBackgroundPaint(Color.WHITE);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(), false);
    }

    private static XYDataset createDataset1() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries("Time Series 1");
        s1.add(new Hour(0, new Day()), (double)20214.5F);
        s1.add(new Hour(4, new Day()), (double)73346.5F);
        s1.add(new Hour(8, new Day()), 54643.6);
        s1.add(new Hour(12, new Day()), 92683.8);
        s1.add(new Hour(16, new Day()), 110235.4);
        s1.add(new Hour(20, new Day()), (double)120742.5F);
        s1.add(new Hour(24, new Day()), (double)90654.5F);
        dataset.addSeries(s1);
        return dataset;
    }

    private static IntervalXYDataset createDataset2() {
        XYTaskDataset dataset = new XYTaskDataset(createTasks());
        dataset.setTransposed(true);
        dataset.setSeriesWidth(0.6);
        return dataset;
    }

    private static TaskSeriesCollection createTasks() {
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        TaskSeries s1 = new TaskSeries("Team A");
        s1.add(new Task("T1a", new Hour(11, new Day())));
        s1.add(new Task("T1b", new Hour(14, new Day())));
        s1.add(new Task("T1c", new Hour(16, new Day())));
        TaskSeries s2 = new TaskSeries("Team B");
        s2.add(new Task("T2a", new Hour(13, new Day())));
        s2.add(new Task("T2b", new Hour(19, new Day())));
        s2.add(new Task("T2c", new Hour(21, new Day())));
        TaskSeries s3 = new TaskSeries("Team C");
        s3.add(new Task("T3a", new Hour(13, new Day())));
        s3.add(new Task("T3b", new Hour(19, new Day())));
        s3.add(new Task("T3c", new Hour(21, new Day())));
        TaskSeries s4 = new TaskSeries("Team D");
        s4.add(new Task("T4a", new Day()));
        TaskSeries s5 = new TaskSeries("Team E");
        s5.add(new Task("T5a", new Day()));
        dataset.add(s1);
        dataset.add(s2);
        dataset.add(s3);
        dataset.add(s4);
        dataset.add(s5);
        return dataset;
    }

    static void main() {
        XYTaskDatasetDemo2 demo = new XYTaskDatasetDemo2("Chart : XYTaskDatasetDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
