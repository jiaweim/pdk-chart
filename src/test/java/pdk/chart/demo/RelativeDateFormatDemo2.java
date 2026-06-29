package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.data.time.Day;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.fluent.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.format.RelativeDateFormat;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * A time series bar chart where the range axis displays the elapsed time
 * in hours, minutes and seconds. This uses the RelativeDateFormat class.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 18 Jun 2026, 4:35 PM
 */
public class RelativeDateFormatDemo2 extends ApplicationFrame {
    public RelativeDateFormatDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset<String> dataset) {
        Chart chart = JChart.bar("RelativeDateFormat Demo 2",
                "Date ", true,
                "Time To Complete", true,
                dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = chart.getXYPlot();
        plot.domainCrosshairVisible(true)
                .rangeCrosshairVisible(true);
        plot.getXYBarRenderer()
                .drawBarOutline(false);

        RelativeDateFormat rdf = new RelativeDateFormat();
        rdf.setShowZeroDays(false);
        rdf.setSecondFormatter(new DecimalFormat("00"));

        plot.rangeAxisDate()
                .dateFormatOverride(rdf);

        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset<String> createDataset() {
        return Data.createTime("Completion",
                new Day[]{
                        new Day(19, 1, 2007),
                        new Day(20, 1, 2007),
                        new Day(21, 1, 2007),
                        new Day(22, 1, 2007),
                        new Day(23, 1, 2007),
                        new Day(24, 1, 2007),
                        new Day(25, 1, 2007),
                        new Day(26, 1, 2007)
                }, new double[]{
                        3343000.0,
                        3420000.0,
                        3515000.0,
                        3315000.0,
                        3490000.0,
                        3556000.0,
                        3383000.0,
                        3575000.0
                });
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        RelativeDateFormatDemo2 demo = new RelativeDateFormatDemo2("Chart: RelativeDateFormatDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
