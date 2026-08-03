package pdk.chart.demo;

import pdk.chart.AreaChart;
import pdk.chart.Chart;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.renderer.xy.XYAreaRenderer2;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;


public class XYAreaRenderer2Demo1 extends ApplicationFrame {
    public XYAreaRenderer2Demo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static XYDataset<String> createDataset() {
        XYSeries<String> series1 = new XYSeries<>("Random 1");
        series1.add(1.0, 500.2);
        series1.add(2.0, 694.1);
        series1.add(3.0, -734.4);
        series1.add(4.0, 453.2);
        series1.add(5.0, 500.2);
        series1.add(6.0, 300.7);
        series1.add(7.0, 734.4);
        series1.add(8.0, 453.2);
        XYSeries<String> series2 = new XYSeries<>("Random 2");
        series2.add(1.0, 700.2);
        series2.add(2.0, 534.1);
        series2.add(3.0, 323.4);
        series2.add(4.0, 125.2);
        series2.add(5.0, 653.2);
        series2.add(6.0, 432.7);
        series2.add(7.0, 564.4);
        series2.add(8.0, 322.2);
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.setIntervalWidth(0.0F);
        return dataset;
    }

    private static Chart createChart(XYDataset dataset) {
        AreaChart chart = new AreaChart(dataset, "Domain (X)", "Range (Y)",
                "XYAreaRenderer2Demo1");
        chart.setPlotForegroundAlpha(0.65F);
        chart.setRenderer(new XYAreaRenderer2());

        ValueAxis xAxis = chart.getDomainAxis();
        xAxis.setTickMarkPaint(Color.BLACK);
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);

        ValueAxis yAxis = chart.getRangeAxis();
        yAxis.setTickMarkPaint(Color.BLACK);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    static void main() {
        XYAreaRenderer2Demo1 demo = new XYAreaRenderer2Demo1("XYAreaRenderer2Demo1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
