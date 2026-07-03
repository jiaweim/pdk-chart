package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.DateTickUnit;
import pdk.chart.axis.DateTickUnitType;
import pdk.chart.axis.TickUnits;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.time.Week;
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


public class TimeSeriesDemo13 extends ApplicationFrame {
    public TimeSeriesDemo13(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.timeLine(dataset, "Date", "Value", "Weekly Data");
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer rr = (XYLineAndShapeRenderer) renderer;
            rr.setDefaultShapesVisible(true);
            rr.setDefaultShapesFilled(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        TickUnits standardUnits = new TickUnits();
        standardUnits.add(new DateTickUnit(DateTickUnitType.DAY, 1, new SimpleDateFormat("MMM dd ''yy")));
        standardUnits.add(new DateTickUnit(DateTickUnitType.DAY, 7, new SimpleDateFormat("MMM dd ''yy")));
        standardUnits.add(new DateTickUnit(DateTickUnitType.MONTH, 1, new SimpleDateFormat("MMM ''yy")));
        axis.setStandardTickUnits(standardUnits);
        return chart;
    }

    private static XYDataset createDataset(int items) {
        TimeSeries s1 = new TimeSeries("Random Data");
        RegularTimePeriod t = new Week();
        double v = 100.0;

        for (int i = 0; i < items; ++i) {
            s1.add(t, v);
            v *= 1.0 + (Math.random() - 0.499) / 100.0;
            t = t.next();
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(s1);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        XYDataset dataset1 = createDataset(26);
        Chart chart1 = createChart(dataset1);
        ChartPanel chartPanel1 = new ChartPanel(chart1, false);
        XYDataset dataset2 = createDataset(1);
        Chart chart2 = createChart(dataset2);
        ChartPanel chartPanel2 = new ChartPanel(chart2, false);
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Chart 1", chartPanel1);
        tabs.add("Chart 2", chartPanel2);
        JPanel content = new JPanel(new BorderLayout());
        content.setPreferredSize(new Dimension(500, 270));
        content.add(tabs);
        return content;
    }

    static void main() {
        TimeSeriesDemo13 demo = new TimeSeriesDemo13("Time Series Demo 13");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
