package pdk.chart.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.DefaultXYItemRenderer;

import pdk.chart.data.time.Millisecond;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.time.TimeSeriesCollection;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class DynamicDataDemo2 extends ApplicationFrame {
    public DynamicDataDemo2(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        DynamicDataDemo2 demo = new DynamicDataDemo2("Chart: DynamicDataDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel implements ActionListener {
        private final TimeSeries series1 = new TimeSeries("Random 1");
        private final TimeSeries series2 = new TimeSeries("Random 2");
        private double lastValue1 = (double) 100.0F;
        private double lastValue2 = (double) 500.0F;

        public MyDemoPanel() {
            super(new BorderLayout());
            TimeSeriesCollection dataset1 = new TimeSeriesCollection(this.series1);
            TimeSeriesCollection dataset2 = new TimeSeriesCollection(this.series2);
            Chart chart = JChart.timeLine("Dynamic Data Demo 2", "Time", "Value", dataset1);
            this.addChart(chart);
            XYPlot plot = (XYPlot) chart.getPlot();
            ValueAxis axis = plot.getDomainAxis();
            axis.setAutoRange(true);
            axis.setFixedAutoRange((double) 10000.0F);
            plot.setDataset(1, dataset2);
            NumberAxis rangeAxis2 = new NumberAxis("Range Axis 2");
            rangeAxis2.setAutoRangeIncludesZero(false);
            plot.setRenderer(1, new DefaultXYItemRenderer());
            plot.setRangeAxis(1, rangeAxis2);
            plot.mapDatasetToRangeAxis(1, 1);
            ChartUtils.applyCurrentTheme(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            this.add(chartPanel);
            JButton button1 = new JButton("Add To Series 1");
            button1.setActionCommand("ADD_DATA_1");
            button1.addActionListener(this);
            JButton button2 = new JButton("Add To Series 2");
            button2.setActionCommand("ADD_DATA_2");
            button2.addActionListener(this);
            JButton button3 = new JButton("Add To Both");
            button3.setActionCommand("ADD_BOTH");
            button3.addActionListener(this);
            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.setBackground(Color.WHITE);
            buttonPanel.add(button1);
            buttonPanel.add(button2);
            buttonPanel.add(button3);
            this.add(buttonPanel, "South");
            chartPanel.setPreferredSize(new Dimension(500, 270));
        }

        public void actionPerformed(ActionEvent e) {
            boolean add1 = false;
            boolean add2 = false;
            if (e.getActionCommand().equals("ADD_DATA_1")) {
                add1 = true;
            } else if (e.getActionCommand().equals("ADD_DATA_2")) {
                add2 = true;
            } else if (e.getActionCommand().equals("ADD_BOTH")) {
                add1 = true;
                add2 = true;
            }

            if (add1) {
                double factor = 0.9 + 0.2 * Math.random();
                this.lastValue1 *= factor;
                Millisecond now = new Millisecond();
                System.out.println("Now = " + now.toString());
                this.series1.add(new Millisecond(), this.lastValue1);
            }

            if (add2) {
                double factor = 0.9 + 0.2 * Math.random();
                this.lastValue2 *= factor;
                Millisecond now = new Millisecond();
                System.out.println("Now = " + now.toString());
                this.series2.add(new Millisecond(), this.lastValue2);
            }

        }
    }
}
