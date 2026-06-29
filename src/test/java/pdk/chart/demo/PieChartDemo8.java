package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.labels.PieSectionLabelGenerator;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class PieChartDemo8 extends ApplicationFrame {
    public PieChartDemo8(String title) {
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
        Chart chart = JChart.createPieChart("Pie Chart Demo 8", dataset, false, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new CustomLabelGenerator());
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        PieChartDemo8 demo = new PieChartDemo8("JFreeChart: PieChartDemo8.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class CustomLabelGenerator implements PieSectionLabelGenerator {
        public String generateSectionLabel(PieDataset dataset, Comparable key) {
            String result = null;
            if (dataset != null && !key.equals("Two")) {
                result = key.toString();
            }

            return result;
        }

        public AttributedString generateAttributedSectionLabel(PieDataset dataset, Comparable key) {
            String keyStr = key.toString();
            String text = keyStr + " : " + dataset.getValue(key);
            AttributedString result = new AttributedString(text);
            result.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, 0, keyStr.length() - 1);
            return result;
        }
    }
}
