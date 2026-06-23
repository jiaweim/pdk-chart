package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.renderer.xy.XYSplineRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class XYSplineRendererDemo1 extends ApplicationFrame {
    public XYSplineRendererDemo1(String title) {
        super(title);
        JPanel content = createDemoPanel();
        this.getContentPane().add(content);
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        XYSplineRendererDemo1 appFrame = new XYSplineRendererDemo1("Chart: XYSplineRendererDemo1.java");
        appFrame.pack();
        UIUtils.centerFrameOnScreen(appFrame);
        appFrame.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel {
        private final XYDataset data1 = this.createSampleData();

        public MyDemoPanel() {
            super(new BorderLayout());
            this.add(this.createContent());
        }

        private XYDataset createSampleData() {
            XYSeries series = new XYSeries("Series 1");
            series.add((double) 2.0F, 56.27);
            series.add((double) 3.0F, 41.32);
            series.add((double) 4.0F, 31.45);
            series.add((double) 5.0F, 30.05);
            series.add((double) 6.0F, 24.69);
            series.add((double) 7.0F, 19.78);
            series.add((double) 8.0F, 20.94);
            series.add((double) 9.0F, 16.73);
            series.add((double) 10.0F, 14.21);
            series.add((double) 11.0F, 12.44);
            XYSeriesCollection result = new XYSeriesCollection(series);
            XYSeries series2 = new XYSeries("Series 2");
            series2.add((double) 11.0F, 56.27);
            series2.add((double) 10.0F, 41.32);
            series2.add((double) 9.0F, 31.45);
            series2.add((double) 8.0F, 30.05);
            series2.add((double) 7.0F, 24.69);
            series2.add((double) 6.0F, 19.78);
            series2.add((double) 5.0F, 20.94);
            series2.add((double) 4.0F, 16.73);
            series2.add((double) 3.0F, 14.21);
            series2.add((double) 2.0F, 12.44);
            result.addSeries(series2);
            return result;
        }

        private JTabbedPane createContent() {
            JTabbedPane tabs = new JTabbedPane();
            tabs.add("Splines:", this.createChartPanel1());
            tabs.add("Lines:", this.createChartPanel2());
            return tabs;
        }

        private ChartPanel createChartPanel1() {
            NumberAxis xAxis = new NumberAxis("X");
            xAxis.setAutoRangeIncludesZero(false);
            NumberAxis yAxis = new NumberAxis("Y");
            yAxis.setAutoRangeIncludesZero(false);
            XYSplineRenderer renderer1 = new XYSplineRenderer();
            XYPlot plot = new XYPlot(this.data1, xAxis, yAxis, renderer1);
            plot.setBackgroundPaint(Color.LIGHT_GRAY);
            plot.setDomainGridlinePaint(Color.WHITE);
            plot.setRangeGridlinePaint(Color.WHITE);
            plot.setAxisOffset(new RectangleInsets((double) 4.0F, (double) 4.0F, (double) 4.0F, (double) 4.0F));
            Chart chart = new Chart("XYSplineRenderer", Chart.DEFAULT_TITLE_FONT, plot, true);
            this.addChart(chart);
            ChartUtils.applyCurrentTheme(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            return chartPanel;
        }

        private ChartPanel createChartPanel2() {
            NumberAxis xAxis = new NumberAxis("X");
            xAxis.setAutoRangeIncludesZero(false);
            NumberAxis yAxis = new NumberAxis("Y");
            yAxis.setAutoRangeIncludesZero(false);
            XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
            XYPlot plot = new XYPlot(this.data1, xAxis, yAxis, renderer1);
            plot.setBackgroundPaint(Color.LIGHT_GRAY);
            plot.setDomainGridlinePaint(Color.WHITE);
            plot.setRangeGridlinePaint(Color.WHITE);
            plot.setAxisOffset(new RectangleInsets((double) 4.0F, (double) 4.0F, (double) 4.0F, (double) 4.0F));
            Chart chart = new Chart("XYLineAndShapeRenderer", Chart.DEFAULT_TITLE_FONT, plot, true);
            this.addChart(chart);
            ChartUtils.applyCurrentTheme(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            return chartPanel;
        }
    }
}
