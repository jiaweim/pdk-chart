//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pdk.chart.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.axis.CompassFormat;
import pdk.chart.axis.ModuloAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.NumberTickUnit;
import pdk.chart.axis.TickUnits;
import pdk.chart.axis.ValueAxis;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYAreaRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.data.Range;
import pdk.chart.data.time.Minute;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class CompassFormatDemo2 extends ApplicationFrame {
    public CompassFormatDemo2(String title) {
        super(title);
        this.setContentPane(new MyDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        CompassFormatDemo2 demo = new CompassFormatDemo2("Chart: CompassFormatDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    private static class MyDemoPanel extends DemoPanel implements ChangeListener {
        private JSlider directionSlider;
        private JSlider fieldSlider;
        private ModuloAxis rangeAxis;
        private double direction = (double)0.0F;
        private double degrees = (double)45.0F;

        public MyDemoPanel() {
            super(new BorderLayout());
            JPanel controlPanel = new JPanel(new GridLayout(1, 2));
            this.fieldSlider = new JSlider(1, 10, 180, 45);
            this.fieldSlider.setPaintLabels(true);
            this.fieldSlider.setPaintTicks(true);
            this.fieldSlider.setMajorTickSpacing(10);
            this.fieldSlider.setMinorTickSpacing(5);
            this.fieldSlider.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            this.fieldSlider.addChangeListener(this);
            this.directionSlider = new JSlider(1, 0, 360, 0);
            this.directionSlider.setMajorTickSpacing(30);
            this.directionSlider.setMinorTickSpacing(5);
            this.directionSlider.setPaintLabels(true);
            this.directionSlider.setPaintTicks(true);
            this.directionSlider.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            this.directionSlider.setPaintTrack(true);
            this.directionSlider.addChangeListener(this);
            controlPanel.add(this.fieldSlider);
            controlPanel.add(this.directionSlider);
            Chart chart = this.createChart();
            this.addChart(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(500, 270));
            this.add(controlPanel, "West");
            this.add(chartPanel);
        }

        private XYDataset createDirectionDataset(int count) {
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries s1 = new TimeSeries("Wind Direction");
            RegularTimePeriod start = new Minute();
            double direction = (double)0.0F;

            for(int i = 0; i < count; ++i) {
                s1.add(start, direction);
                start = start.next();
                direction += (Math.random() - (double)0.5F) * (double)15.0F;
                if (direction < (double)0.0F) {
                    direction += (double)360.0F;
                } else if (direction > (double)360.0F) {
                    direction -= (double)360.0F;
                }
            }

            dataset.addSeries(s1);
            return dataset;
        }

        private XYDataset createForceDataset(int count) {
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries s1 = new TimeSeries("Wind Force");
            RegularTimePeriod start = new Minute();
            double force = (double)3.0F;

            for(int i = 0; i < count; ++i) {
                s1.add(start, force);
                start = start.next();
                force = Math.max((double)0.5F, force + (Math.random() - (double)0.5F) * (double)0.5F);
            }

            dataset.addSeries(s1);
            return dataset;
        }

        private Chart createChart() {
            XYDataset direction = this.createDirectionDataset(100);
            Chart chart = ChartFactory.createTimeSeriesChart("Time", "Date", "Direction", direction, true, true, false);
            XYPlot plot = (XYPlot)chart.getPlot();
            plot.getDomainAxis().setLowerMargin((double)0.0F);
            plot.getDomainAxis().setUpperMargin((double)0.0F);
            this.rangeAxis = new ModuloAxis("Direction", new Range((double)0.0F, (double)360.0F));
            TickUnits units = new TickUnits();
            units.add(new NumberTickUnit((double)180.0F, new CompassFormat()));
            units.add(new NumberTickUnit((double)90.0F, new CompassFormat()));
            units.add(new NumberTickUnit((double)45.0F, new CompassFormat()));
            units.add(new NumberTickUnit((double)22.5F, new CompassFormat()));
            this.rangeAxis.setStandardTickUnits(units);
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            renderer.setDefaultLinesVisible(false);
            renderer.setDefaultShapesVisible(true);
            plot.setRenderer(renderer);
            plot.setRangeAxis(this.rangeAxis);
            this.rangeAxis.setDisplayRange((double)-45.0F, (double)45.0F);
            XYItemRenderer renderer2 = new XYAreaRenderer();
            ValueAxis axis2 = new NumberAxis("Force");
            axis2.setRange((double)0.0F, (double)12.0F);
            renderer2.setSeriesPaint(0, new Color(0, 0, 255, 128));
            plot.setDataset(1, this.createForceDataset(100));
            plot.setRenderer(1, renderer2);
            plot.setRangeAxis(1, axis2);
            plot.mapDatasetToRangeAxis(1, 1);
            ChartUtils.applyCurrentTheme(chart);
            return chart;
        }

        public void stateChanged(ChangeEvent event) {
            if (event.getSource() == this.directionSlider) {
                this.direction = (double)this.directionSlider.getValue();
                this.rangeAxis.setDisplayRange(this.direction - this.degrees, this.direction + this.degrees);
            } else if (event.getSource() == this.fieldSlider) {
                this.degrees = (double)this.fieldSlider.getValue();
                this.rangeAxis.setDisplayRange(this.direction - this.degrees, this.direction + this.degrees);
            }

        }
    }
}
