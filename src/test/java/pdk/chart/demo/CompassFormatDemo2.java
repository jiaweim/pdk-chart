package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.*;
import pdk.chart.data.Range;
import pdk.chart.data.time.Minute;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYAreaRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class CompassFormatDemo2 extends ApplicationFrame {
    public CompassFormatDemo2(String title) {
        super(title);
        this.setContentPane(new MyDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    static void main() {
        CompassFormatDemo2 demo = new CompassFormatDemo2("CompassFormatDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    private static class MyDemoPanel extends DemoPanel implements ChangeListener {
        private JSlider directionSlider;
        private JSlider fieldSlider;
        private ModuloAxis rangeAxis;
        private double direction = 0.0;
        private double degrees = 45.0;

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
            double direction = 0.0;
            for (int i = 0; i < count; ++i) {
                s1.add(start, direction);
                start = start.next();
                direction += (Math.random() - 0.5) * 15.0;
                if (direction < 0.0) {
                    direction += 360.0;
                } else if (direction > (double) 360.0F) {
                    direction -= 360.0;
                }
            }

            dataset.addSeries(s1);
            return dataset;
        }

        private XYDataset createForceDataset(int count) {
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            TimeSeries s1 = new TimeSeries("Wind Force");
            RegularTimePeriod start = new Minute();
            double force = 3.0;

            for (int i = 0; i < count; ++i) {
                s1.add(start, force);
                start = start.next();
                force = Math.max(0.5, force + (Math.random() - 0.5) * 0.5);
            }

            dataset.addSeries(s1);
            return dataset;
        }

        private Chart createChart() {
            XYDataset direction = this.createDirectionDataset(100);
            Chart chart = JChart.timeLine(direction, "Date", "Direction", "Time");
            XYPlot plot = (XYPlot) chart.getPlot();
            plot.getDomainAxis().setLowerMargin(0.0);
            plot.getDomainAxis().setUpperMargin(0.0);
            this.rangeAxis = new ModuloAxis("Direction", new Range(0.0, 360.0));
            TickUnits units = new TickUnits();
            units.add(new NumberTickUnit(180.0, new CompassFormat()));
            units.add(new NumberTickUnit(90.0, new CompassFormat()));
            units.add(new NumberTickUnit(45.0, new CompassFormat()));
            units.add(new NumberTickUnit(22.5, new CompassFormat()));
            this.rangeAxis.setStandardTickUnits(units);
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            renderer.setDefaultLinesVisible(false);
            renderer.setDefaultShapesVisible(true);
            plot.setRenderer(renderer);
            plot.setRangeAxis(this.rangeAxis);
            this.rangeAxis.setDisplayRange(-45.0, 45.0);
            XYItemRenderer renderer2 = new XYAreaRenderer();
            ValueAxis axis2 = new NumberAxis("Force");
            axis2.setRange(0.0, 12.0);
            renderer2.setSeriesPaint(0, new Color(0, 0, 255, 128));
            plot.setDataset(1, this.createForceDataset(100));
            plot.setRenderer(1, renderer2);
            plot.setRangeAxis(1, axis2);
            plot.mapDatasetToRangeAxis(1, 1);
            JChartUtils.applyCurrentTheme(chart);
            return chart;
        }

        public void stateChanged(ChangeEvent event) {
            if (event.getSource() == this.directionSlider) {
                this.direction = this.directionSlider.getValue();
                this.rangeAxis.setDisplayRange(this.direction - this.degrees, this.direction + this.degrees);
            } else if (event.getSource() == this.fieldSlider) {
                this.degrees = this.fieldSlider.getValue();
                this.rangeAxis.setDisplayRange(this.direction - this.degrees, this.direction + this.degrees);
            }
        }
    }
}
