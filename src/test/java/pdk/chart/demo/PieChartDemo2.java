package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.labels.StandardPieSectionLabelGenerator;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class PieChartDemo2 extends ApplicationFrame {
    public PieChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static PieDataset createDataset() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset();
        dataset.setValue("One", 43.2);
        dataset.setValue("Two", (double) 10.0F);
        dataset.setValue("Three", (double) 27.5F);
        dataset.setValue("Four", (double) 17.5F);
        dataset.setValue("Five", (double) 11.0F);
        dataset.setValue("Six", 19.4);
        return dataset;
    }

    private static Chart createChart(PieDataset dataset) {
        Chart chart = JChart.pie("Pie Chart Demo 2", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("One", new Color(160, 160, 255));
        plot.setSectionPaint("Two", new Color(128, 128, 223));
        plot.setSectionPaint("Three", new Color(96, 96, 191));
        plot.setSectionPaint("Four", new Color(64, 64, 159));
        plot.setSectionPaint("Five", new Color(32, 32, 127));
        plot.setSectionPaint("Six", new Color(0, 0, 111));
        plot.setNoDataMessage("No data available");
        plot.setExplodePercent("Two", 0.2);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2} percent)"));
        plot.setLabelBackgroundPaint(new Color(220, 220, 220));
        plot.setLegendLabelToolTipGenerator(new StandardPieSectionLabelGenerator("Tooltip for legend item {0}"));
        plot.setSimpleLabels(true);
        plot.setInteriorGap((double) 0.0F);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        PieChartDemo2 demo = new PieChartDemo2("JFreeChart: PieChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
