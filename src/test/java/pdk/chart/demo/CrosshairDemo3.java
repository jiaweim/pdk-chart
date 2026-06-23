package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.Range;
import pdk.chart.data.time.Month;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.text.SimpleDateFormat;

public class CrosshairDemo3 extends ApplicationFrame {
    public CrosshairDemo3(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        CrosshairDemo3 demo = new CrosshairDemo3("Chart: CrosshairDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel implements ChangeListener {
        private Chart chart;
        private JSlider slider;

        public MyDemoPanel() {
            super(new BorderLayout());
            XYDataset dataset = this.createDataset();
            this.chart = this.createChart(dataset);
            this.addChart(this.chart);
            ChartPanel chartPanel = new ChartPanel(this.chart);
            chartPanel.setPreferredSize(new Dimension(500, 270));
            chartPanel.setMouseZoomable(true);
            JPanel controls = new JPanel(new BorderLayout());
            this.slider = new JSlider(0, 100, 50);
            this.slider.addChangeListener(this);
            controls.add(this.slider);
            this.add(chartPanel);
            this.add(controls, "South");
        }

        private Chart createChart(XYDataset dataset) {
            Chart c = ChartFactory.createTimeSeriesChart("Legal & General Unit Trust Prices", "Date", "Price Per Unit", dataset, true, true, false);
            XYPlot plot = (XYPlot) c.getPlot();
            plot.setDomainCrosshairVisible(true);
            plot.setDomainCrosshairLockedOnData(false);
            plot.setRangeCrosshairVisible(false);
            XYItemRenderer renderer = plot.getRenderer();
            if (renderer instanceof XYLineAndShapeRenderer) {
                XYLineAndShapeRenderer rr = (XYLineAndShapeRenderer) renderer;
                rr.setDefaultShapesVisible(true);
                rr.setDefaultShapesFilled(true);
            }

            DateAxis axis = (DateAxis) plot.getDomainAxis();
            axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
            return c;
        }

        private XYDataset createDataset() {
            TimeSeries s1 = new TimeSeries("L&G European Index Trust");
            s1.add(new Month(2, 2001), 181.8);
            s1.add(new Month(3, 2001), 167.3);
            s1.add(new Month(4, 2001), 153.8);
            s1.add(new Month(5, 2001), 167.6);
            s1.add(new Month(6, 2001), 158.8);
            s1.add(new Month(7, 2001), 148.3);
            s1.add(new Month(8, 2001), 153.9);
            s1.add(new Month(9, 2001), 142.7);
            s1.add(new Month(10, 2001), 123.2);
            s1.add(new Month(11, 2001), 131.8);
            s1.add(new Month(12, 2001), 139.6);
            s1.add(new Month(1, 2002), 142.9);
            s1.add(new Month(2, 2002), 138.7);
            s1.add(new Month(3, 2002), 137.3);
            s1.add(new Month(4, 2002), 143.9);
            s1.add(new Month(5, 2002), 139.8);
            s1.add(new Month(6, 2002), (double) 137.0F);
            s1.add(new Month(7, 2002), 132.8);
            TimeSeries s2 = new TimeSeries("L&G UK Index Trust");
            s2.add(new Month(2, 2001), 129.6);
            s2.add(new Month(3, 2001), 123.2);
            s2.add(new Month(4, 2001), 117.2);
            s2.add(new Month(5, 2001), 124.1);
            s2.add(new Month(6, 2001), 122.6);
            s2.add(new Month(7, 2001), 119.2);
            s2.add(new Month(8, 2001), (double) 116.5F);
            s2.add(new Month(9, 2001), 112.7);
            s2.add(new Month(10, 2001), (double) 101.5F);
            s2.add(new Month(11, 2001), 106.1);
            s2.add(new Month(12, 2001), 110.3);
            s2.add(new Month(1, 2002), 111.7);
            s2.add(new Month(2, 2002), (double) 111.0F);
            s2.add(new Month(3, 2002), 109.6);
            s2.add(new Month(4, 2002), 113.2);
            s2.add(new Month(5, 2002), 111.6);
            s2.add(new Month(6, 2002), 108.8);
            s2.add(new Month(7, 2002), 101.6);
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(s1);
            dataset.addSeries(s2);
            return dataset;
        }

        public void stateChanged(ChangeEvent event) {
            int value = this.slider.getValue();
            XYPlot plot = (XYPlot) this.chart.getPlot();
            ValueAxis domainAxis = plot.getDomainAxis();
            Range range = domainAxis.getRange();
            double c = domainAxis.getLowerBound() + (double) value / (double) 100.0F * range.getLength();
            plot.setDomainCrosshairValue(c);
        }
    }
}
