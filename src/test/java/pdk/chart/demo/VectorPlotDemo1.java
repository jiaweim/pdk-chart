package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.VectorSeries;
import pdk.chart.data.xy.VectorSeriesCollection;
import pdk.chart.data.xy.VectorXYDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.VectorRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class VectorPlotDemo1 extends ApplicationFrame {
    public VectorPlotDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(VectorXYDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setLowerMargin(0.01);
        xAxis.setUpperMargin(0.01);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setLowerMargin(0.01);
        yAxis.setUpperMargin(0.01);
        yAxis.setAutoRangeIncludesZero(false);
        VectorRenderer renderer = new VectorRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setDefaultToolTipGenerator(new VectorToolTipGenerator());
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setAxisOffset(new RectangleInsets((double) 5.0F, (double) 5.0F, (double) 5.0F, (double) 5.0F));
        plot.setOutlinePaint(Color.black);
        Chart chart = new Chart("Vector Plot Demo 1", plot);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static VectorXYDataset createDataset() {
        VectorSeries s1 = new VectorSeries("Series 1");

        for (double r = (double) 0.0F; r < (double) 20.0F; ++r) {
            for (double c = (double) 0.0F; c < (double) 20.0F; ++c) {
                s1.add(r + (double) 10.0F, c + (double) 10.0F, Math.sin(r / (double) 5.0F) / (double) 2.0F, Math.cos(c / (double) 5.0F) / (double) 2.0F);
            }
        }

        VectorSeriesCollection dataset = new VectorSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        VectorPlotDemo1 demo = new VectorPlotDemo1("Chart : Vector Plot Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class VectorToolTipGenerator extends StandardXYToolTipGenerator {
        public VectorToolTipGenerator() {
        }

        public String generateToolTip(XYDataset dataset, int series, int item) {
            if (dataset instanceof VectorXYDataset) {
                VectorXYDataset d = (VectorXYDataset) dataset;
                double x = d.getXValue(series, item);
                double dx = d.getVectorXValue(series, item);
                double y = d.getYValue(series, item);
                double dy = d.getVectorYValue(series, item);
                NumberFormat xf = this.getXFormat();
                NumberFormat yf = this.getYFormat();
                return xf.format(x) + " (" + xf.format(dx) + "), " + yf.format(y) + " (" + yf.format(dy) + ")";
            } else {
                return super.generateToolTip(dataset, series, item);
            }
        }
    }
}
