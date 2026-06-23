package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.data.xy.DefaultXYZDataset;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HideSeriesDemo3 extends ApplicationFrame {
    public HideSeriesDemo3(String title) {
        super(title);
        this.setContentPane(new MyDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        HideSeriesDemo3 demo = new HideSeriesDemo3("Chart: HideSeriesDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel implements ActionListener {
        private XYItemRenderer renderer;

        public MyDemoPanel() {
            super(new BorderLayout());
            XYZDataset dataset = this.createSampleDataset();
            Chart chart = this.createChart(dataset);
            this.addChart(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setMouseWheelEnabled(true);
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

        private XYZDataset createSampleDataset() {
            DefaultXYZDataset dataset = new DefaultXYZDataset();
            double[] x = new double[]{2.1, 2.3, 2.3};
            double[] y = new double[]{14.1, 11.1, (double) 10.0F};
            double[] z = new double[]{2.4, 2.7, 2.7};
            double[][] series = new double[][]{x, y, z};
            dataset.addSeries("Series 1", series);
            x = new double[]{2.2, 2.2, 1.8};
            y = new double[]{14.1, 11.1, (double) 10.0F};
            z = new double[]{2.2, 2.2, 2.2};
            series = new double[][]{x, y, z};
            dataset.addSeries("Series 2", series);
            x = new double[]{1.8, 1.9, 2.3, 3.8};
            y = new double[]{5.4, 4.1, 4.1, (double) 25.0F};
            z = new double[]{2.1, 2.2, 1.6, (double) 4.0F};
            series = new double[][]{x, y, z};
            dataset.addSeries("Series 3", series);
            return dataset;
        }

        private Chart createChart(XYZDataset dataset) {
            Chart result = ChartFactory.createBubbleChart("Hide Series Demo 3", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
            XYPlot plot = (XYPlot) result.getPlot();
            plot.setDomainPannable(true);
            plot.setRangePannable(true);
            this.renderer = plot.getRenderer(0);
            return result;
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
