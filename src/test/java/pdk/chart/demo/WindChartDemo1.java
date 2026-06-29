package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.DefaultWindDataset;
import pdk.chart.data.xy.WindDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;


public class WindChartDemo1 extends ApplicationFrame {
    public WindChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static long millisForDate(int day, int month, int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day, 12, 0);
        return c.getTimeInMillis();
    }

    private static Object[] createItem(long millis, int dir, int force) {
        return new Object[]{new Date(millis), dir, force};
    }

    public static WindDataset createDataset() {
        Object[] item1 = createItem(millisForDate(3, 1, 1999), 0, 10);
        Object[] item2 = createItem(millisForDate(4, 1, 1999), 1, 8);
        Object[] item3 = createItem(millisForDate(5, 1, 1999), 2, 10);
        Object[] item4 = createItem(millisForDate(6, 1, 1999), 3, 10);
        Object[] item5 = createItem(millisForDate(7, 1, 1999), 4, 7);
        Object[] item6 = createItem(millisForDate(8, 1, 1999), 5, 10);
        Object[] item7 = createItem(millisForDate(9, 1, 1999), 6, 8);
        Object[] item8 = createItem(millisForDate(10, 1, 1999), 7, 11);
        Object[] item9 = createItem(millisForDate(11, 1, 1999), 8, 10);
        Object[] item10 = createItem(millisForDate(12, 1, 1999), 9, 11);
        Object[] item11 = createItem(millisForDate(13, 1, 1999), 10, 3);
        Object[] item12 = createItem(millisForDate(14, 1, 1999), 11, 9);
        Object[] item13 = createItem(millisForDate(15, 1, 1999), 12, 11);
        Object[] item14 = createItem(millisForDate(16, 1, 1999), 0, 0);
        Object[][] series1 = new Object[][]{item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13, item14};
        Object[][][] data = new Object[][][]{series1};
        return new DefaultWindDataset(data);
    }

    private static Chart createChart(WindDataset dataset) {
        Chart chart = JChart.createWindPlot("Wind Chart Demo", "Date", "Direction / Force", dataset, true, false, false);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    static void main() {
        WindChartDemo1 demo = new WindChartDemo1("Wind Chart Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
