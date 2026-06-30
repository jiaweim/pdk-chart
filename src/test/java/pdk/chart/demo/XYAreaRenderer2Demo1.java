package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
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

    private static XYDataset createDataset() {
        XYSeries series1 = new XYSeries("Random 1");
        series1.add((double) 1.0F, 500.2);
        series1.add((double) 2.0F, 694.1);
        series1.add((double) 3.0F, -734.4);
        series1.add((double) 4.0F, 453.2);
        series1.add((double) 5.0F, 500.2);
        series1.add((double) 6.0F, 300.7);
        series1.add((double) 7.0F, 734.4);
        series1.add((double) 8.0F, 453.2);
        XYSeries series2 = new XYSeries("Random 2");
        series2.add((double) 1.0F, 700.2);
        series2.add((double) 2.0F, 534.1);
        series2.add((double) 3.0F, 323.4);
        series2.add((double) 4.0F, 125.2);
        series2.add((double) 5.0F, 653.2);
        series2.add((double) 6.0F, 432.7);
        series2.add((double) 7.0F, 564.4);
        series2.add((double) 8.0F, 322.2);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.setIntervalWidth((double) 0.0F);
        return dataset;
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.areaXY("XYAreaRenderer2Demo1", "Domain (X)", "Range (Y)", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRenderer(new XYAreaRenderer2());
        plot.setForegroundAlpha(0.65F);
        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickMarkPaint(Color.black);
        domainAxis.setLowerMargin((double) 0.0F);
        domainAxis.setUpperMargin((double) 0.0F);
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickMarkPaint(Color.black);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        XYAreaRenderer2Demo1 demo = new XYAreaRenderer2Demo1("XYAreaRenderer2Demo1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
