package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.labels.StandardPieSectionLabelGenerator;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class PieChartDemo7 extends ApplicationFrame {
    public PieChartDemo7(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    private static PieDataset createDataset(int sections) {
        DefaultPieDataset<String> result = new DefaultPieDataset();

        for (int i = 0; i < sections; ++i) {
            double value = (double) 100.0F * Math.random();
            result.setValue("Section " + i, value);
        }

        return result;
    }

    public static JPanel createDemoPanel() {
        PieDataset dataset = createDataset(14);
        Chart chart = ChartFactory.createPieChart("Pie Chart Demo 7", dataset, false, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setCircular(true);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {2}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));
        plot.setNoDataMessage("No data available");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        Rotator rotator = new Rotator(plot);
        rotator.start();
        return chartPanel;
    }

    public static void main(String[] args) {
        PieChartDemo7 demo = new PieChartDemo7("JFreeChart: PieChartDemo7.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class Rotator extends Timer implements ActionListener {
        private PiePlot plot;
        private int angle = 270;

        Rotator(PiePlot plot) {
            super(50, (ActionListener) null);
            this.plot = plot;
            this.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event) {
            this.plot.setStartAngle((double) this.angle);
            ++this.angle;
            if (this.angle == 360) {
                this.angle = 0;
            }

        }
    }
}
