package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StandardXYItemRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;
import pdk.chart.data.time.Minute;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;

public class MultipleAxisDemo1 extends ApplicationFrame {
    public MultipleAxisDemo1(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(600, 270));
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        this.setContentPane(chartPanel);
    }

    private static Chart createChart() {
        XYDataset dataset1 = createDataset("Series 1", (double) 100.0F, new Minute(), 200);
        Chart chart = JChart.timeLine("Multiple Axis Demo 1", "Time of Day", "Primary Range Axis", dataset1);
        chart.addSubtitle(new TextTitle("Four datasets and four range axes."));
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        NumberAxis axis2 = new NumberAxis("Range Axis 2");
        axis2.setAutoRangeIncludesZero(false);
        plot.setRangeAxis(1, axis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);
        XYDataset dataset2 = createDataset("Series 2", (double) 1000.0F, new Minute(), 170);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        plot.setRenderer(1, renderer2);
        NumberAxis axis3 = new NumberAxis("Range Axis 3");
        plot.setRangeAxis(2, axis3);
        XYDataset dataset3 = createDataset("Series 3", (double) 10000.0F, new Minute(), 170);
        plot.setDataset(2, dataset3);
        plot.mapDatasetToRangeAxis(2, 2);
        XYItemRenderer renderer3 = new StandardXYItemRenderer();
        plot.setRenderer(2, renderer3);
        NumberAxis axis4 = new NumberAxis("Range Axis 4");
        plot.setRangeAxis(3, axis4);
        XYDataset dataset4 = createDataset("Series 4", (double) 25.0F, new Minute(), 200);
        plot.setDataset(3, dataset4);
        plot.mapDatasetToRangeAxis(3, 3);
        XYItemRenderer renderer4 = new StandardXYItemRenderer();
        plot.setRenderer(3, renderer4);
        ChartUtils.applyCurrentTheme(chart);
        plot.getRenderer().setSeriesPaint(0, Color.black);
        renderer2.setSeriesPaint(0, Color.RED);
        axis2.setLabelPaint(Color.RED);
        axis2.setTickLabelPaint(Color.RED);
        renderer3.setSeriesPaint(0, Color.BLUE);
        axis3.setLabelPaint(Color.BLUE);
        axis3.setTickLabelPaint(Color.BLUE);
        renderer4.setSeriesPaint(0, Color.GREEN);
        axis4.setLabelPaint(Color.GREEN);
        axis4.setTickLabelPaint(Color.GREEN);
        return chart;
    }

    private static XYDataset createDataset(String name, double base, RegularTimePeriod start, int count) {
        TimeSeries series = new TimeSeries(name);
        RegularTimePeriod period = start;
        double value = base;

        for (int i = 0; i < count; ++i) {
            series.add(period, value);
            period = period.next();
            value *= (double) 1.0F + (Math.random() - 0.495) / (double) 10.0F;
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        MultipleAxisDemo1 demo = new MultipleAxisDemo1("Chart: MultipleAxisDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
