package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.function.Function2D;
import pdk.chart.data.function.LineFunction2D;
import pdk.chart.data.function.PowerFunction2D;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.data.statistics.Regression;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class RegressionDemo1 extends ApplicationFrame {
    public RegressionDemo1(String title) {
        super(title);
        JPanel content = createDemoPanel();
        this.getContentPane().add(content);
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        RegressionDemo1 appFrame = new RegressionDemo1("Chart: Regression Demo 1");
        appFrame.pack();
        UIUtils.centerFrameOnScreen(appFrame);
        appFrame.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel {
        private XYDataset data1 = this.createSampleData1();

        public MyDemoPanel() {
            super(new BorderLayout());
            this.add(this.createContent());
        }

        private XYDataset createSampleData1() {
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
            XYDataset result = new XYSeriesCollection(series);
            return result;
        }

        private JTabbedPane createContent() {
            JTabbedPane tabs = new JTabbedPane();
            tabs.add("Linear", this.createChartPanel1());
            tabs.add("Power", this.createChartPanel2());
            return tabs;
        }

        private ChartPanel createChartPanel1() {
            NumberAxis xAxis = new NumberAxis("X");
            xAxis.setAutoRangeIncludesZero(false);
            NumberAxis yAxis = new NumberAxis("Y");
            yAxis.setAutoRangeIncludesZero(false);
            XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false, true);
            XYPlot plot = new XYPlot(this.data1, xAxis, yAxis, renderer1);
            double[] coefficients = Regression.getOLSRegression(this.data1, 0);
            Function2D curve = new LineFunction2D(coefficients[0], coefficients[1]);
            XYDataset regressionData = curve.sample(2.0F, (double) 11.0F, 100, "Fitted Regression Line");
            plot.setDataset(1, regressionData);
            XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true, false);
            renderer2.setSeriesPaint(0, Color.BLUE);
            plot.setRenderer(1, renderer2);
            Chart chart = new Chart("Linear Regression", Chart.DEFAULT_TITLE_FONT, plot, true);
            ChartUtils.applyCurrentTheme(chart);
            this.addChart(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            return chartPanel;
        }

        private ChartPanel createChartPanel2() {
            NumberAxis xAxis = new NumberAxis("X");
            xAxis.setAutoRangeIncludesZero(false);
            NumberAxis yAxis = new NumberAxis("Y");
            yAxis.setAutoRangeIncludesZero(false);
            XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false, true);
            XYPlot plot = new XYPlot(this.data1, xAxis, yAxis, renderer1);
            double[] coefficients = Regression.getPowerRegression(this.data1, 0);
            Function2D curve = new PowerFunction2D(coefficients[0], coefficients[1]);
            XYDataset regressionData = curve.sample( 2.0F, (double) 11.0F, 100, "Fitted Regression Line");
            XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true, false);
            renderer2.setSeriesPaint(0, Color.BLUE);
            plot.setDataset(1, regressionData);
            plot.setRenderer(1, renderer2);
            Chart chart = new Chart("Power Regression", Chart.DEFAULT_TITLE_FONT, plot, true);
            ChartUtils.applyCurrentTheme(chart);
            this.addChart(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            return chartPanel;
        }
    }
}
