package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.BoxAndWhiskerCalculator;
import pdk.chart.data.statistics.BoxAndWhiskerXYDataset;
import pdk.chart.data.statistics.DefaultBoxAndWhiskerXYDataset;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoxAndWhiskerChartDemo2 extends ApplicationFrame {
    public BoxAndWhiskerChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static BoxAndWhiskerXYDataset createDataset() {
        int VALUE_COUNT = 20;
        DefaultBoxAndWhiskerXYDataset result = new DefaultBoxAndWhiskerXYDataset("Series Name");
        RegularTimePeriod t = new Day();

        for (int i = 0; i < 10; ++i) {
            List values = createValueList(0.0, 20.0, VALUE_COUNT);
            result.add(t.getStart(), BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(values));
            t = t.next();
        }

        return result;
    }

    private static List<Double> createValueList(double lowerBound, double upperBound, int count) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            double v = lowerBound + Math.random() * (upperBound - lowerBound);
            result.add(v);
        }

        return result;
    }

    private static Chart createChart(BoxAndWhiskerXYDataset dataset) {
        Chart chart = JChart.boxAndWhisker("Box-and-Whisker Chart Demo 2", "Day", "Value", dataset, false);
        chart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = chart.getXYPlot();

        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        plot.getRangeAxisAsNumber().standardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        BoxAndWhiskerChartDemo2 demo = new BoxAndWhiskerChartDemo2("BoxAndWhiskerChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
