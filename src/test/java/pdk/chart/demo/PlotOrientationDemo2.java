package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.annotations.XYLineAnnotation;
import pdk.chart.annotations.XYShapeAnnotation;
import pdk.chart.api.Layer;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.IntervalMarker;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PlotOrientationDemo2 extends ApplicationFrame {
    private static final int CHART_COUNT = 8;

    public PlotOrientationDemo2(String title) {
        super(title);
        this.setContentPane(new MyDemoPanel());
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
        Chart chart = JChart.line("Chart " + (index + 1), "X", "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
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
        PlotOrientationDemo2 demo = new PlotOrientationDemo2("JFreeChart: PlotOrientationDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel {
        private XYDataset[] datasets = new XYDataset[8];
        private Chart[] charts = new Chart[8];
        private ChartPanel[] panels = new ChartPanel[8];

        public MyDemoPanel() {
            super(new GridLayout(2, 4));

            for (int i = 0; i < 8; ++i) {
                this.datasets[i] = PlotOrientationDemo2.createDataset(i);
                this.charts[i] = PlotOrientationDemo2.createChart(i, this.datasets[i]);
                XYPlot plot = (XYPlot) this.charts[i].getPlot();
                plot.setDomainPannable(true);
                plot.setRangePannable(true);
                XYShapeAnnotation a1 = new XYShapeAnnotation(new Rectangle2D.Double((double) 1.0F, (double) 2.0F, (double) 2.0F, (double) 3.0F), new BasicStroke(1.0F), Color.BLUE);
                XYLineAnnotation a2 = new XYLineAnnotation((double) 0.0F, (double) -5.0F, (double) 10.0F, (double) -5.0F);
                plot.addAnnotation(a1);
                plot.addAnnotation(a2);
                plot.addDomainMarker(new IntervalMarker((double) 5.0F, (double) 10.0F), Layer.BACKGROUND);
                plot.addRangeMarker(new IntervalMarker((double) -2.0F, (double) 0.0F), Layer.BACKGROUND);
                this.addChart(this.charts[i]);
                this.panels[i] = new ChartPanel(this.charts[i], false);
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
