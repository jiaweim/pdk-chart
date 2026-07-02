package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
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
            XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
            XYSeries<String> s1 = new XYSeries<>("Series 1",
                    new double[]{2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0},
                    new double[]{56.27, 41.32, 31.45, 30.05, 24.69, 19.78, 20.94, 16.73, 14.21, 12.44});
            XYSeries<String> s2 = new XYSeries<>("Series 2",
                    new double[]{11.0, 10.0F, 9.0F, 8.0F, 7.0F, 6.0F, 5.0F, 4.0F, 3.0F, 2.0F},
                    new double[]{56.27, 41.32, 31.45, 30.05, 24.69, 19.78, 20.94, 16.73, 14.21, 12.44});
            dataset.addSeries(s1);
            dataset.addSeries(s2);
            return dataset;
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
            plot.setAxisOffset(new RectangleInsets( 4.0F, (double) 4.0F, (double) 4.0F, (double) 4.0F));
            Chart chart = new Chart("XYSplineRenderer", Chart.DEFAULT_TITLE_FONT, plot, true);
            this.addChart(chart);
            JChartUtils.applyCurrentTheme(chart);
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
            JChartUtils.applyCurrentTheme(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            return chartPanel;
        }
    }
}
