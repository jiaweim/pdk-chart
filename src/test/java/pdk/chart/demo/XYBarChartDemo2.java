package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * A bar chart created using the ClusteredXYBarRenderer class.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 2:22 PM
 */
public class XYBarChartDemo2 extends ApplicationFrame {

    public XYBarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static IntervalXYDataset<String> createDataset() {
        TimeSeries<String> series1 = new TimeSeries<>("Series 1");
        series1.add(new Day(1, 1, 2003), 54.3);
        series1.add(new Day(2, 1, 2003), 20.3);
        series1.add(new Day(3, 1, 2003), 43.4);
        series1.add(new Day(4, 1, 2003), -12.0);
        TimeSeries<String> series2 = new TimeSeries<>("Series 2");
        series2.add(new Day(1, 1, 2003), 8.0);
        series2.add(new Day(2, 1, 2003), 16.0);
        series2.add(new Day(3, 1, 2003), 21.0);
        series2.add(new Day(4, 1, 2003), 5.0);
        TimeSeriesCollection<String> dataset = new TimeSeriesCollection<>();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    private static Chart createChart(IntervalXYDataset<String> dataset) {
        Chart chart = JChart.barCluster(dataset,
                "Date", "Y", "XY Bar Chart Demo 2", true);

        XYPlot plot = chart.getXYPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        plot.getBarRenderer()
                .margin(0)
                .drawBarOutline(false);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    static void main() {
        XYBarChartDemo2 demo = new XYBarChartDemo2("XYBarChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
