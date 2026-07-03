package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.data.gantt.Task;
import pdk.chart.data.gantt.TaskSeries;
import pdk.chart.data.gantt.TaskSeriesCollection;
import pdk.chart.data.time.SimpleTimePeriod;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.GanttRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class GanttDemo1 extends ApplicationFrame {
    public GanttDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static IntervalCategoryDataset createDataset() {
        TaskSeries s1 = new TaskSeries("Scheduled");
        s1.add(new Task("Write Proposal", new SimpleTimePeriod(date(1, 3, 2001), date(5, 3, 2001))));
        s1.add(new Task("Obtain Approval", new SimpleTimePeriod(date(9, 3, 2001), date(9, 3, 2001))));
        s1.add(new Task("Requirements Analysis", new SimpleTimePeriod(date(10, 3, 2001), date(5, 4, 2001))));
        s1.add(new Task("Design Phase", new SimpleTimePeriod(date(6, 4, 2001), date(30, 4, 2001))));
        s1.add(new Task("Design Signoff", new SimpleTimePeriod(date(2, 5, 2001), date(2, 5, 2001))));
        s1.add(new Task("Alpha Implementation", new SimpleTimePeriod(date(3, 5, 2001), date(31, 6, 2001))));
        s1.add(new Task("Design Review", new SimpleTimePeriod(date(1, 7, 2001), date(8, 7, 2001))));
        s1.add(new Task("Revised Design Signoff", new SimpleTimePeriod(date(10, 7, 2001), date(10, 7, 2001))));
        s1.add(new Task("Beta Implementation", new SimpleTimePeriod(date(12, 7, 2001), date(12, 8, 2001))));
        s1.add(new Task("Testing", new SimpleTimePeriod(date(13, 8, 2001), date(31, 9, 2001))));
        s1.add(new Task("Final Implementation", new SimpleTimePeriod(date(1, 10, 2001), date(15, 10, 2001))));
        s1.add(new Task("Signoff", new SimpleTimePeriod(date(28, 10, 2001), date(30, 10, 2001))));
        TaskSeries s2 = new TaskSeries("Actual");
        s2.add(new Task("Write Proposal", new SimpleTimePeriod(date(1, 3, 2001), date(5, 3, 2001))));
        s2.add(new Task("Obtain Approval", new SimpleTimePeriod(date(9, 3, 2001), date(9, 3, 2001))));
        s2.add(new Task("Requirements Analysis", new SimpleTimePeriod(date(10, 3, 2001), date(15, 4, 2001))));
        s2.add(new Task("Design Phase", new SimpleTimePeriod(date(15, 4, 2001), date(17, 5, 2001))));
        s2.add(new Task("Design Signoff", new SimpleTimePeriod(date(30, 5, 2001), date(30, 5, 2001))));
        s2.add(new Task("Alpha Implementation", new SimpleTimePeriod(date(1, 6, 2001), date(12, 8, 2001))));
        s2.add(new Task("Design Review", new SimpleTimePeriod(date(12, 8, 2001), date(22, 8, 2001))));
        s2.add(new Task("Revised Design Signoff", new SimpleTimePeriod(date(25, 8, 2001), date(27, 8, 2001))));
        s2.add(new Task("Beta Implementation", new SimpleTimePeriod(date(27, 8, 2001), date(30, 9, 2001))));
        s2.add(new Task("Testing", new SimpleTimePeriod(date(31, 9, 2001), date(17, 10, 2001))));
        s2.add(new Task("Final Implementation", new SimpleTimePeriod(date(18, 10, 2001), date(5, 11, 2001))));
        s2.add(new Task("Signoff", new SimpleTimePeriod(date(10, 11, 2001), date(11, 11, 2001))));
        TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        collection.add(s2);
        return collection;
    }

    private static Date date(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date result = calendar.getTime();
        return result;
    }

    private static Chart createChart(IntervalCategoryDataset dataset) {
        Chart chart = JChart.gantt(dataset, "Task", "Date", "Gantt Chart Demo",
                true, true);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10.0F);
        plot.setRangeCrosshairVisible(true);
        GanttRenderer renderer = (GanttRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        GanttDemo1 demo = new GanttDemo1("Chart: GanttDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
