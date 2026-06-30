package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.SortOrder;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.plot.pie.PieLabelLinkStyle;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PieChartDemo4 extends ApplicationFrame {
    public PieChartDemo4(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Section A", 43.2);
        dataset.setValue("Section B", (double) 10.0F);
        dataset.setValue("Section C", (double) 27.5F);
        dataset.setValue("Section D", (double) 17.5F);
        dataset.setValue("Section E", (double) 11.0F);
        dataset.setValue("Section F", 19.4);
        return dataset;
    }

    private static Chart createChart(PieDataset dataset) {
        Chart chart = JChart.pie("Pie Chart Demo 4", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        plot.setExplodePercent("Section D", (double) 0.5F);
        plot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel(createDataset());
    }

    public static void main(String[] args) {
        PieChartDemo4 demo = new PieChartDemo4("JFreeChart: PieChartDemo4.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    private static class MyDemoPanel extends DemoPanel implements ActionListener {
        Chart chart;
        DefaultPieDataset dataset;
        boolean ascendingByKey = false;
        boolean ascendingByValue = false;

        public MyDemoPanel(DefaultPieDataset dataset) {
            super(new BorderLayout());
            this.dataset = dataset;
            this.chart = PieChartDemo4.createChart(dataset);
            this.addChart(this.chart);
            ChartPanel chartPanel = new ChartPanel(this.chart, false);
            chartPanel.setMouseWheelEnabled(true);
            this.add(chartPanel);
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JButton button1 = new JButton("By Key");
            button1.setActionCommand("BY_KEY");
            button1.addActionListener(this);
            JButton button2 = new JButton("By Value");
            button2.setActionCommand("BY_VALUE");
            button2.addActionListener(this);
            JButton button3 = new JButton("Random");
            button3.setActionCommand("RANDOM");
            button3.addActionListener(this);
            JCheckBox checkBox = new JCheckBox("Simple Labels");
            checkBox.setActionCommand("LABELS");
            checkBox.addActionListener(this);
            buttonPanel.add(button1);
            buttonPanel.add(button2);
            buttonPanel.add(button3);
            buttonPanel.add(checkBox);
            this.add(buttonPanel, "South");
        }

        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if ("BY_KEY".equals(cmd)) {
                if (!this.ascendingByKey) {
                    this.dataset.sortByKeys(SortOrder.ASCENDING);
                    this.ascendingByKey = true;
                } else {
                    this.dataset.sortByKeys(SortOrder.DESCENDING);
                    this.ascendingByKey = false;
                }
            } else if ("BY_VALUE".equals(cmd)) {
                if (!this.ascendingByValue) {
                    this.dataset.sortByValues(SortOrder.ASCENDING);
                    this.ascendingByValue = true;
                } else {
                    this.dataset.sortByValues(SortOrder.DESCENDING);
                    this.ascendingByValue = false;
                }
            } else if ("RANDOM".equals(cmd)) {
                List<Comparable> keys = new ArrayList(this.dataset.getKeys());
                Collections.shuffle(keys);
                DefaultPieDataset pd = new DefaultPieDataset();

                for (Comparable key : keys) {
                    pd.setValue(key, this.dataset.getValue(key));
                }

                PiePlot plot = (PiePlot) this.chart.getPlot();
                plot.setDataset(pd);
                this.dataset = pd;
            } else if ("LABELS".equals(cmd)) {
                PiePlot plot = (PiePlot) this.chart.getPlot();
                boolean simple = plot.getSimpleLabels();
                if (simple) {
                    plot.setInteriorGap(0.05);
                    plot.setSimpleLabels(false);
                } else {
                    plot.setInteriorGap(0.01);
                    plot.setSimpleLabels(true);
                }
            }
        }
    }
}
