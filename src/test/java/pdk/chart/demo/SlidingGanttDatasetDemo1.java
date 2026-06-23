package pdk.chart.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.api.Layer;
import pdk.chart.axis.DateAxis;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.IntervalMarker;
import pdk.chart.plot.Marker;
import pdk.chart.renderer.category.GanttRenderer;
import pdk.chart.data.gantt.GanttCategoryDataset;
import pdk.chart.data.gantt.SlidingGanttCategoryDataset;
import pdk.chart.data.gantt.Task;
import pdk.chart.data.gantt.TaskSeries;
import pdk.chart.data.gantt.TaskSeriesCollection;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.Hour;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class SlidingGanttDatasetDemo1 extends ApplicationFrame {
    public SlidingGanttDatasetDemo1(String title) {
        super(title);
        this.setDefaultCloseOperation(3);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new DemoPanel();
    }

    public static void main(String[] args) {
        SlidingGanttDatasetDemo1 demo = new SlidingGanttDatasetDemo1("Chart: SlidingGanttDatasetDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class DemoPanel extends JPanel implements ChangeListener {
        JScrollBar scroller;
        SlidingGanttCategoryDataset dataset = new SlidingGanttCategoryDataset(createDataset(), 0, 15);

        public DemoPanel() {
            super(new BorderLayout());
            Chart chart = createChart(this.dataset);
            ChartPanel cp1 = new ChartPanel(chart);
            cp1.setPreferredSize(new Dimension(400, 400));
            this.scroller = new JScrollBar(1, 0, 15, 0, 50);
            this.add(cp1);
            this.scroller.getModel().addChangeListener(this);
            JPanel scrollPanel = new JPanel(new BorderLayout());
            scrollPanel.add(this.scroller);
            scrollPanel.setBorder(BorderFactory.createEmptyBorder(66, 2, 2, 2));
            this.add(scrollPanel, "East");
        }

        public static GanttCategoryDataset createDataset() {
            TaskSeries s1 = new TaskSeries("Scheduled");
            Day t0 = new Day();
            Day t1 = new Day();

            for(int i = 0; i < 50; ++i) {
                int days = (int)(Math.random() * (double)10.0F) + 1;

                for(int j = 0; j < days; ++j) {
                    t1 = (Day)t1.next();
                }

                s1.add(new Task("Task " + i, new Date(t0.getMiddleMillisecond()), new Date(t1.getMiddleMillisecond())));
                t0 = (Day)t1.next();
                t1 = (Day)t1.next();
            }

            TaskSeriesCollection collection = new TaskSeriesCollection();
            collection.add(s1);
            return collection;
        }

        private static Chart createChart(SlidingGanttCategoryDataset dataset) {
            Chart chart = ChartFactory.createGanttChart("Gantt Chart Demo", "Task", "Date", dataset, true, true, false);
            CategoryPlot plot = (CategoryPlot)chart.getPlot();
            Hour h = new Hour(1, 14, 5, 2008);

            for(int i = 0; i < 12; ++i) {
                Marker marker = new IntervalMarker((double)h.getFirstMillisecond(), (double)h.getLastMillisecond(), Color.LIGHT_GRAY);
                plot.addRangeMarker(marker, Layer.BACKGROUND);
                h = (Hour)h.next().next();
            }

            plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10.0F);
            DateAxis rangeAxis = (DateAxis)plot.getRangeAxis();
            rangeAxis.setRange(DatasetUtils.findRangeBounds(dataset.getUnderlyingDataset(), true));
            GanttRenderer renderer = (GanttRenderer)plot.getRenderer();
            renderer.setDrawBarOutline(false);
            renderer.setShadowVisible(false);
            return chart;
        }

        public void stateChanged(ChangeEvent e) {
            this.dataset.setFirstCategoryIndex(this.scroller.getValue());
        }
    }
}
