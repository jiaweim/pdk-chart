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
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;

public class PieChartDemo6 extends ApplicationFrame {
    public PieChartDemo6(String title) {
        super(title);
        JPanel panel = createDemoPanel();
        panel.setPreferredSize(new Dimension(800, 600));
        this.setContentPane(panel);
    }

    private static PieDataset<String> createDataset() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset();
        dataset.setValue("S1", (double) 7.0F);
        dataset.setValue("S2", (Number) null);
        dataset.setValue("S3", (double) 0.0F);
        dataset.setValue("S4", (double) 3.0F);
        dataset.setValue("S5", (double) -1.0F);
        return dataset;
    }

    private static Chart createChart(String title, PieDataset<String> dataset) {
        Chart chart = ChartFactory.createPieChart(title, dataset, true, true, false);
        PiePlot<String> plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {1}"));
        return chart;
    }

    public static JPanel createDemoPanel() {
        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        Chart chart1 = createChart("Pie Chart 1", createDataset());
        Font font = new Font("Dialog", 0, 12);
        chart1.addSubtitle(new TextTitle("Ignore nulls: false; Ignore zeros: false;", font));
        Chart chart2 = createChart("Pie Chart 2", createDataset());
        chart2.addSubtitle(new TextTitle("Ignore nulls: true; Ignore zeros: false;", font));
        PiePlot<String> plot2 = (PiePlot) chart2.getPlot();
        plot2.setIgnoreNullValues(true);
        plot2.setIgnoreZeroValues(false);
        Chart chart3 = createChart("Pie Chart 3", createDataset());
        chart3.addSubtitle(new TextTitle("Ignore nulls: false; Ignore zeros: true;", font));
        PiePlot<String> plot3 = (PiePlot) chart3.getPlot();
        plot3.setIgnoreNullValues(false);
        plot3.setIgnoreZeroValues(true);
        Chart chart4 = createChart("Pie Chart 4", createDataset());
        chart4.addSubtitle(new TextTitle("Ignore nulls: true; Ignore zeros: true;", font));
        PiePlot plot4 = (PiePlot) chart4.getPlot();
        plot4.setIgnoreNullValues(true);
        plot4.setIgnoreZeroValues(true);
        panel.add(new ChartPanel(chart1, false));
        panel.add(new ChartPanel(chart2, false));
        panel.add(new ChartPanel(chart3, false));
        panel.add(new ChartPanel(chart4, false));
        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);
        return panel;
    }

    public static void main(String[] args) {
        PieChartDemo6 demo = new PieChartDemo6("JFreeChart: PieChartDemo6.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
