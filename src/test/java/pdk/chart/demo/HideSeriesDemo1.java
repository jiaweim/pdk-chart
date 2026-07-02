package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HideSeriesDemo1 extends ApplicationFrame {
    public HideSeriesDemo1(String title) {
        super(title);
        this.setContentPane(new MyDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        HideSeriesDemo1 demo = new HideSeriesDemo1("Chart: HideSeriesDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel implements ActionListener {
        private XYItemRenderer renderer;

        public MyDemoPanel() {
            super(new BorderLayout());
            XYDataset dataset = this.createSampleDataset();
            Chart chart = this.createChart(dataset);
            this.addChart(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            JPanel boxPanel = new JPanel();
            JCheckBox box1 = new JCheckBox("Series 1");
            box1.setActionCommand("S1");
            box1.addActionListener(this);
            box1.setSelected(true);
            JCheckBox box2 = new JCheckBox("Series 2");
            box2.setActionCommand("S2");
            box2.addActionListener(this);
            box2.setSelected(true);
            JCheckBox box3 = new JCheckBox("Series 3");
            box3.setActionCommand("S3");
            box3.addActionListener(this);
            box3.setSelected(true);
            boxPanel.add(box1);
            boxPanel.add(box2);
            boxPanel.add(box3);
            this.add(chartPanel);
            this.add(boxPanel, "South");
            chartPanel.setPreferredSize(new Dimension(500, 270));
        }

        private XYDataset createSampleDataset() {
            XYSeries series1 = new XYSeries("Series 1");
            series1.add((double) 1.0F, 3.3);
            series1.add((double) 2.0F, 4.4);
            series1.add((double) 3.0F, 1.7);
            XYSeries series2 = new XYSeries("Series 2");
            series2.add((double) 1.0F, 7.3);
            series2.add((double) 2.0F, 6.8);
            series2.add((double) 3.0F, 9.6);
            series2.add((double) 4.0F, 5.6);
            XYSeries series3 = new XYSeries("Series 3");
            series3.add(1.0, 17.3);
            series3.add(2.0, 16.8);
            series3.add(3.0, 19.6);
            series3.add(4.0, 15.6);
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            dataset.addSeries(series3);
            return dataset;
        }

        private Chart createChart(XYDataset dataset) {
            Chart chart = JChart.line(dataset, "X", "Y", "Hide Series Demo 1");
            XYPlot plot = (XYPlot) chart.getPlot();
            this.renderer = plot.getRenderer();
            return chart;
        }

        public void actionPerformed(ActionEvent e) {
            int series = -1;
            if (e.getActionCommand().equals("S1")) {
                series = 0;
            } else if (e.getActionCommand().equals("S2")) {
                series = 1;
            } else if (e.getActionCommand().equals("S3")) {
                series = 2;
            }

            if (series >= 0) {
                boolean visible = this.renderer.getItemVisible(series, 0);
                this.renderer.setSeriesVisible(series, !visible);
            }

        }
    }
}
