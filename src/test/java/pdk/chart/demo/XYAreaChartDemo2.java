package pdk.chart.demo;

import pdk.chart.AreaChart;
import pdk.chart.AxisType;
import pdk.chart.Chart;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class XYAreaChartDemo2 extends ApplicationFrame {
    public XYAreaChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset<String> createDataset() {
        TimeSeries<String> series1 = new TimeSeries<>("Random 1");
        double value = 0.0;
        Day day = new Day();

        for (int i = 0; i < 200; ++i) {
            value = value + Math.random() - (double) 0.5F;
            series1.add(day, value);
            day = (Day) day.next();
        }

        TimeSeriesCollection<String> dataset = new TimeSeriesCollection<>(series1);
        return dataset;
    }

    private static Chart createChart(XYDataset<String> dataset) {
        AreaChart chart = new AreaChart(dataset, "Time", AxisType.DATE,
                "Value", "XY Area Chart Demo 2");
        chart.setDomainPannable(true);
        ValueAxis xAxis = chart.getDomainAxis();
        xAxis.setLowerMargin(0);
        xAxis.setUpperMargin(0);
        chart.setForegroundAlpha(0.5f);
        chart.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})",
                new SimpleDateFormat("d-MMM-yyyy"),
                new DecimalFormat("#,##0.00")));
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    static void main() {
        XYAreaChartDemo2 demo = new XYAreaChartDemo2("XY Area Chart Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
