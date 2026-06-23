package pdk.chart.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.DateAxis;
import pdk.chart.data.Range;
import pdk.chart.data.general.DatasetChangeEvent;
import pdk.chart.data.general.DatasetChangeListener;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.data.time.Minute;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.data.xy.AbstractXYDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;


public class TranslateDemo1 extends ApplicationFrame {
    public TranslateDemo1(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        TranslateDemo1 demo = new TranslateDemo1("Translate Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    private static class MyDemoPanel extends DemoPanel implements ChangeListener {
        private TimeSeries series;
        private ChartPanel chartPanel;
        private Chart chart = this.createChart();
        private JSlider slider;
        private TranslatingXYDataset dataset;

        public MyDemoPanel() {
            super(new BorderLayout());
            this.addChart(this.chart);
            this.chartPanel = new ChartPanel(this.chart);
            this.chartPanel.setPreferredSize(new Dimension(600, 270));
            this.chartPanel.setDomainZoomable(true);
            this.chartPanel.setRangeZoomable(true);
            Border border = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createEtchedBorder());
            this.chartPanel.setBorder(border);
            this.add(this.chartPanel);
            JPanel dashboard = new JPanel(new BorderLayout());
            dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
            this.slider = new JSlider(-200, 200, 0);
            this.slider.setPaintLabels(true);
            this.slider.setMajorTickSpacing(50);
            this.slider.setPaintTicks(true);
            this.slider.addChangeListener(this);
            dashboard.add(this.slider);
            this.add(dashboard, "South");
        }

        private Chart createChart() {
            XYDataset dataset1 = this.createDataset("Random 1", (double)100.0F, new Minute(), 200);
            Chart chart1 = ChartFactory.createTimeSeriesChart("Translate Demo 1", "Time of Day", "Value", dataset1);
            XYPlot plot = (XYPlot)chart1.getPlot();
            plot.setOrientation(PlotOrientation.VERTICAL);
            plot.setDomainCrosshairVisible(true);
            plot.setDomainCrosshairLockedOnData(false);
            plot.setRangeCrosshairVisible(false);
            DateAxis axis = (DateAxis)plot.getDomainAxis();
            Range range = DatasetUtils.findDomainBounds(this.dataset);
            axis.setRange(range);
            return chart1;
        }

        private XYDataset createDataset(String name, double base, RegularTimePeriod start, int count) {
            this.series = new TimeSeries(name);
            RegularTimePeriod period = start;
            double value = base;

            for(int i = 0; i < count; ++i) {
                this.series.add(period, value);
                period = period.next();
                value *= (double)1.0F + (Math.random() - 0.495) / (double)10.0F;
            }

            TimeSeriesCollection tsc = new TimeSeriesCollection();
            tsc.addSeries(this.series);
            this.dataset = new TranslatingXYDataset(tsc);
            return this.dataset;
        }

        public void stateChanged(ChangeEvent event) {
            int value = this.slider.getValue();
            this.dataset.setTranslate((double)(value * 60) * (double)1000.0F);
        }

        static class TranslatingXYDataset extends AbstractXYDataset implements XYDataset, DatasetChangeListener {
            private XYDataset underlying;
            private double translate;

            public TranslatingXYDataset(XYDataset underlying) {
                this.underlying = underlying;
                this.underlying.addChangeListener(this);
                this.translate = (double)0.0F;
            }

            public double getTranslate() {
                return this.translate;
            }

            public void setTranslate(double t) {
                this.translate = t;
                this.fireDatasetChanged();
            }

            public int getItemCount(int series) {
                return this.underlying.getItemCount(series);
            }

            public double getXValue(int series, int item) {
                return this.underlying.getXValue(series, item) + this.translate;
            }

            public Number getX(int series, int item) {
                return this.getXValue(series, item);
            }

            public Number getY(int series, int item) {
                return this.getYValue(series, item);
            }

            public double getYValue(int series, int item) {
                return this.underlying.getYValue(series, item);
            }

            public int getSeriesCount() {
                return this.underlying.getSeriesCount();
            }

            public Comparable getSeriesKey(int series) {
                return this.underlying.getSeriesKey(series);
            }

            public void datasetChanged(DatasetChangeEvent event) {
                this.fireDatasetChanged();
            }
        }
    }
}
