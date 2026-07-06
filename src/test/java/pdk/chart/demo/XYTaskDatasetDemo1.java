package pdk.chart.demo;

import pdk.chart.AxisType;
import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.SymbolAxis;
import pdk.chart.data.gantt.Task;
import pdk.chart.data.gantt.TaskSeries;
import pdk.chart.data.gantt.TaskSeriesCollection;
import pdk.chart.data.gantt.XYTaskDataset;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.Hour;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class XYTaskDatasetDemo1 extends ApplicationFrame {
    public XYTaskDatasetDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = JChart.bar(dataset, "Resource", AxisType.SYMBOL,
                "Timing", AxisType.DATE,
                "XYTaskDatasetDemo1",
                PlotOrientation.HORIZONTAL, true, false);

        chart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRangePannable(true);

        SymbolAxis xAxis = (SymbolAxis) plot.getDomainAxis();
        xAxis.setSymbols(new String[]{"Team A", "Team B", "Team C", "Team D"});
        xAxis.setLabel("Series");
        xAxis.setGridBandsVisible(false);
        xAxis.configure();

        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setUseYInterval(true);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    private static IntervalXYDataset createDataset() {
        return new XYTaskDataset(createTasks());
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
        dataset.add(s1);
        dataset.add(s2);
        dataset.add(s3);
        dataset.add(s4);
        return dataset;
    }

    static void main() {
        XYTaskDatasetDemo1 demo = new XYTaskDatasetDemo1("XYTaskDatasetDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
