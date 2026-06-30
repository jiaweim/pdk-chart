package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;

public class PieChartDemo5 extends ApplicationFrame {
    public PieChartDemo5(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        DefaultPieDataset<String> dataset = new DefaultPieDataset();
        dataset.setValue("Section 1", 23.3);
        dataset.setValue("Section 2", 56.5F);
        dataset.setValue("Section 3", 43.3);
        dataset.setValue("Section 4", 11.1);
        Chart chart1 = JChart.pie("Chart 1", dataset, false, false, false);
        chart1.addSubtitle(new TextTitle("setCircular(true);", new Font("Dialog", 0, 12)));
        PiePlot plot1 = (PiePlot)chart1.getPlot();
        plot1.setCircular(true);
        plot1.setInteriorGap(0.04);
        plot1.setMaximumLabelWidth(0.2);
        Chart chart2 = JChart.pie("Chart 2", dataset, false, false, false);
        chart2.addSubtitle(new TextTitle("setCircular(false);", new Font("Dialog", 0, 12)));
        PiePlot plot2 = (PiePlot)chart2.getPlot();
        plot2.setCircular(false);
        plot2.setInteriorGap(0.04);
        plot2.setMaximumLabelWidth(0.2);

        panel.add(new ChartPanel(chart1, false));
        panel.add(new ChartPanel(chart2, false));
        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.setPreferredSize(new Dimension(800, 600));
        return panel;
    }

    static void main(String[] args) {
        PieChartDemo5 demo = new PieChartDemo5("JFreeChart: PieChartDemo5.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
