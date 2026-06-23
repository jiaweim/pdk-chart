package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.data.gantt.Task;
import pdk.chart.data.gantt.TaskSeries;
import pdk.chart.data.gantt.TaskSeriesCollection;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class GanttDemo2 extends ApplicationFrame {
    public GanttDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalCategoryDataset dataset) {
        Chart chart = ChartFactory.createGanttChart("Gantt Chart Demo", "Task", "Date", dataset, true, true, false);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setRangePannable(true);
        plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10.0F);
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        return chart;
    }

    private static IntervalCategoryDataset createDataset() {
        TaskSeries s1 = new TaskSeries("Scheduled");
        Task t1 = new Task("Write Proposal", date(1, 3, 2001), date(5, 3, 2001));
        t1.setPercentComplete((double)1.0F);
        s1.add(t1);
        Task t2 = new Task("Obtain Approval", date(9, 3, 2001), date(9, 3, 2001));
        t2.setPercentComplete((double)1.0F);
        s1.add(t2);
        Task t3 = new Task("Requirements Analysis", date(10, 3, 2001), date(5, 4, 2001));
        Task st31 = new Task("Requirements 1", date(10, 3, 2001), date(25, 3, 2001));
        st31.setPercentComplete((double)1.0F);
        Task st32 = new Task("Requirements 2", date(1, 4, 2001), date(5, 4, 2001));
        st32.setPercentComplete((double)1.0F);
        t3.addSubtask(st31);
        t3.addSubtask(st32);
        s1.add(t3);
        Task t4 = new Task("Design Phase", date(6, 4, 2001), date(30, 4, 2001));
        Task st41 = new Task("Design 1", date(6, 4, 2001), date(10, 4, 2001));
        st41.setPercentComplete((double)1.0F);
        Task st42 = new Task("Design 2", date(15, 4, 2001), date(20, 4, 2001));
        st42.setPercentComplete((double)1.0F);
        Task st43 = new Task("Design 3", date(23, 4, 2001), date(30, 4, 2001));
        st43.setPercentComplete((double)0.5F);
        t4.addSubtask(st41);
        t4.addSubtask(st42);
        t4.addSubtask(st43);
        s1.add(t4);
        Task t5 = new Task("Design Signoff", date(2, 5, 2001), date(2, 5, 2001));
        s1.add(t5);
        Task t6 = new Task("Alpha Implementation", date(3, 5, 2001), date(31, 6, 2001));
        t6.setPercentComplete(0.6);
        s1.add(t6);
        Task t7 = new Task("Design Review", date(1, 7, 2001), date(8, 7, 2001));
        t7.setPercentComplete((double)0.0F);
        s1.add(t7);
        Task t8 = new Task("Revised Design Signoff", date(10, 7, 2001), date(10, 7, 2001));
        t8.setPercentComplete((double)0.0F);
        s1.add(t8);
        Task t9 = new Task("Beta Implementation", date(12, 7, 2001), date(12, 8, 2001));
        t9.setPercentComplete((double)0.0F);
        s1.add(t9);
        Task t10 = new Task("Testing", date(13, 8, 2001), date(31, 9, 2001));
        t10.setPercentComplete((double)0.0F);
        s1.add(t10);
        Task t11 = new Task("Final Implementation", date(1, 10, 2001), date(15, 10, 2001));
        t11.setPercentComplete((double)0.0F);
        s1.add(t11);
        Task t12 = new Task("Signoff", date(28, 10, 2001), date(30, 10, 2001));
        t12.setPercentComplete((double)0.0F);
        s1.add(t12);
        TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        return collection;
    }

    private static Date date(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date result = calendar.getTime();
        return result;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        GanttDemo2 demo = new GanttDemo2("Chart: GanttDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
