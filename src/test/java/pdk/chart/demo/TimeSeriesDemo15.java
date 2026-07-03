package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.DateAxis;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class TimeSeriesDemo15 extends ApplicationFrame {
    public TimeSeriesDemo15(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.timeLine(dataset, "Date", "Evaluation ID",
                "Bug Report Submissions for Java");
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultShapesVisible(true);
            renderer.setDefaultShapesFilled(true);
            renderer.setUseFillPaint(true);
            renderer.setDefaultFillPaint(Color.WHITE);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("Bugs");
        s1.add(new Day(27, 6, 2005), 478474.0);
        s1.add(new Day(24, 1, 2006), 633804.0);
        s1.add(new Day(28, 4, 2006), 694096.0);
        s1.add(new Day(12, 5, 2006), 704680.0);
        s1.add(new Day(16, 5, 2006), 709599.0);
        s1.add(new Day(21, 6, 2006), 734754.0);
        s1.add(new Day(27, 7, 2006), 760008.0);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        TimeSeriesDemo15 demo = new TimeSeriesDemo15("Time Series Demo 15");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
