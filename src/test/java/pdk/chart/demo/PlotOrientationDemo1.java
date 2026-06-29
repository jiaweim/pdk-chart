package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.annotations.XYDataImageAnnotation;
import pdk.chart.annotations.XYLineAnnotation;
import pdk.chart.annotations.XYShapeAnnotation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PlotOrientationDemo1 extends ApplicationFrame {
    private static int CHART_COUNT = 8;

    public PlotOrientationDemo1(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    private static XYDataset createDataset(int index) {
        XYSeries series1 = new XYSeries("Series " + (index + 1));
        series1.add((double) -10.0F, (double) -5.0F);
        series1.add((double) 10.0F, (double) 5.0F);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }

    private static Chart createChart(int index, XYDataset dataset) {
        Chart chart = ChartFactory.line("Chart " + (index + 1), "X", "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultShapesFilled(true);
        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        PlotOrientationDemo1 demo = new PlotOrientationDemo1("JFreeChart: PlotOrientationDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel {
        private XYDataset[] datasets;
        private Chart[] charts;
        private ChartPanel[] panels;

        public MyDemoPanel() {
            super(new GridLayout(2, 4));
            this.datasets = new XYDataset[PlotOrientationDemo1.CHART_COUNT];
            this.charts = new Chart[PlotOrientationDemo1.CHART_COUNT];
            this.panels = new ChartPanel[PlotOrientationDemo1.CHART_COUNT];

            for (int i = 0; i < PlotOrientationDemo1.CHART_COUNT; ++i) {
                this.datasets[i] = PlotOrientationDemo1.createDataset(i);
                this.charts[i] = PlotOrientationDemo1.createChart(i, this.datasets[i]);
                XYPlot plot = (XYPlot) this.charts[i].getPlot();
                plot.setDomainPannable(true);
                plot.setRangePannable(true);
                XYShapeAnnotation a1 = new XYShapeAnnotation(new Rectangle2D.Double((double) -2.0F, (double) -3.0F, (double) 1.0F, (double) 4.0F), new BasicStroke(1.0F), Color.BLUE, Color.YELLOW);
                XYLineAnnotation a2 = new XYLineAnnotation((double) 0.0F, (double) -5.0F, (double) 10.0F, (double) -5.0F);
                XYDataImageAnnotation a3 = new XYDataImageAnnotation(ChartDemo.getTestImage(), (double) 5.0F, (double) 2.0F, (double) 6.0F, (double) 4.0F, true);
                plot.addAnnotation(a1);
                plot.addAnnotation(a2);
                plot.addAnnotation(a3);
                plot.setQuadrantPaint(0, new Color(230, 230, 255));
                plot.setQuadrantPaint(1, new Color(230, 255, 230));
                plot.setQuadrantPaint(2, new Color(255, 230, 230));
                plot.setQuadrantPaint(3, new Color(255, 230, 255));
                this.addChart(this.charts[i]);
                this.panels[i] = new ChartPanel(this.charts[i], false);
                this.panels[i].setMouseWheelEnabled(true);
            }

            XYPlot plot1 = (XYPlot) this.charts[1].getPlot();
            XYPlot plot2 = (XYPlot) this.charts[2].getPlot();
            XYPlot plot3 = (XYPlot) this.charts[3].getPlot();
            XYPlot plot4 = (XYPlot) this.charts[4].getPlot();
            XYPlot plot5 = (XYPlot) this.charts[5].getPlot();
            XYPlot plot6 = (XYPlot) this.charts[6].getPlot();
            XYPlot plot7 = (XYPlot) this.charts[7].getPlot();
            plot1.getDomainAxis().setInverted(true);
            plot2.getRangeAxis().setInverted(true);
            plot3.getDomainAxis().setInverted(true);
            plot3.getRangeAxis().setInverted(true);
            plot5.getDomainAxis().setInverted(true);
            plot6.getRangeAxis().setInverted(true);
            plot4.getDomainAxis().setInverted(true);
            plot4.getRangeAxis().setInverted(true);
            plot4.setOrientation(PlotOrientation.HORIZONTAL);
            plot5.setOrientation(PlotOrientation.HORIZONTAL);
            plot6.setOrientation(PlotOrientation.HORIZONTAL);
            plot7.setOrientation(PlotOrientation.HORIZONTAL);
            this.add(this.panels[0]);
            this.add(this.panels[1]);
            this.add(this.panels[4]);
            this.add(this.panels[5]);
            this.add(this.panels[2]);
            this.add(this.panels[3]);
            this.add(this.panels[6]);
            this.add(this.panels[7]);
            this.setPreferredSize(new Dimension(800, 600));
        }
    }
}
