package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.general.SeriesException;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.SamplingXYLineRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;


public class TimeSeriesDemo5 extends ApplicationFrame {
    public TimeSeriesDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        TimeSeries series = new TimeSeries("Random Data");
        Day current = new Day(1, 1, 1990);
        double value = 100.0;

        for (int i = 0; i < 100000; ++i) {
            try {
                value = value + Math.random() - 0.5;
                series.add(current, value);
                current = (Day) current.next();
            } catch (SeriesException var6) {
                System.err.println("Error adding to series");
            }
        }

        return new TimeSeriesCollection(series);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.timeLine(dataset, "Day", "Value", "Test", false, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        SamplingXYLineRenderer renderer = new SamplingXYLineRenderer();
        plot.setRenderer(renderer);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        String title = "₢₢₢₣₤₥₦₧₨₩₪";
        TimeSeriesDemo5 demo = new TimeSeriesDemo5(title);
        demo.pack();
        UIUtils.positionFrameRandomly(demo);
        demo.setVisible(true);
    }
}
