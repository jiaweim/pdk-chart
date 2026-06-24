package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.statistics.BoxAndWhiskerCalculator;
import pdk.chart.data.statistics.BoxAndWhiskerXYDataset;
import pdk.chart.data.statistics.DefaultBoxAndWhiskerXYDataset;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBoxAndWhiskerRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
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
            List values = createValueList((double) 0.0F, (double) 20.0F, 20);
            result.add(t.getStart(), BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(values));
            t = t.next();
        }

        return result;
    }

    private static List<Double> createValueList(double lowerBound, double upperBound, int count) {
        List<Double> result = new ArrayList();

        for (int i = 0; i < count; ++i) {
            double v = lowerBound + Math.random() * (upperBound - lowerBound);
            result.add(v);
        }

        return result;
    }

    private static Chart createChart(BoxAndWhiskerXYDataset dataset) {
        DateAxis domainAxis = new DateAxis("Day");
        NumberAxis rangeAxis = new NumberAxis("Value");
        XYItemRenderer renderer = new XYBoxAndWhiskerRenderer();
        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        Chart chart = new Chart("Box-and-Whisker Chart Demo 2", plot);
        chart.setBackgroundPaint(Color.WHITE);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main() {
        BoxAndWhiskerChartDemo2 demo = new BoxAndWhiskerChartDemo2("Chart: BoxAndWhiskerChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
