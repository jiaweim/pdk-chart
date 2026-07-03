package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.time.Day;
import pdk.chart.data.time.RegularTimePeriod;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.StandardXYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MultipleDatasetDemo1 extends ApplicationFrame {
    public MultipleDatasetDemo1(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    static void main() {
        MultipleDatasetDemo1 demo = new MultipleDatasetDemo1("MultipleDatasetDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel implements ActionListener {
        private XYPlot plot;
        private int datasetIndex = 0;

        public MyDemoPanel() {
            super(new BorderLayout());
            TimeSeriesCollection dataset1 = this.createRandomDataset("Series 1");
            Chart chart = JChart.timeLine(dataset1, "Time", "Value",
                    "Multiple Dataset Demo 1");
            chart.setBackgroundPaint((Paint) null);
            this.addChart(chart);
            this.plot = (XYPlot) chart.getPlot();
            ValueAxis axis = this.plot.getDomainAxis();
            axis.setAutoRange(true);
            NumberAxis rangeAxis2 = new NumberAxis("Range Axis 2");
            rangeAxis2.setAutoRangeIncludesZero(false);
            JChartUtils.applyCurrentTheme(chart);
            JPanel content = new JPanel(new BorderLayout());
            ChartPanel chartPanel = new ChartPanel(chart);
            content.add(chartPanel);
            JButton button1 = new JButton("Add Dataset");
            button1.setActionCommand("ADD_DATASET");
            button1.addActionListener(this);
            JButton button2 = new JButton("Remove Dataset");
            button2.setActionCommand("REMOVE_DATASET");
            button2.addActionListener(this);
            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(button1);
            buttonPanel.add(button2);
            content.add(buttonPanel, "South");
            chartPanel.setPreferredSize(new Dimension(500, 270));
            this.add(content);
        }

        private TimeSeriesCollection createRandomDataset(String name) {
            TimeSeries series = new TimeSeries(name);
            double value = 100.0;
            RegularTimePeriod t = new Day();
            for (int i = 0; i < 50; ++i) {
                series.add(t, value);
                t = t.next();
                value *= 1.0 + Math.random() / 100.0;
            }

            return new TimeSeriesCollection(series);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("ADD_DATASET")) {
                if (this.datasetIndex < 20) {
                    ++this.datasetIndex;
                    this.plot.setDataset(this.datasetIndex, this.createRandomDataset("S" + this.datasetIndex));
                    this.plot.setRenderer(this.datasetIndex, new StandardXYItemRenderer());
                }
            } else if (e.getActionCommand().equals("REMOVE_DATASET") && this.datasetIndex >= 1) {
                this.plot.setDataset(this.datasetIndex, null);
                this.plot.setRenderer(this.datasetIndex, null);
                --this.datasetIndex;
            }

        }
    }
}
