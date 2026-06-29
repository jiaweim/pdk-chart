package pdk.chart.demo;

import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.NumberAxis;
import pdk.chart.labels.AbstractCategoryItemLabelGenerator;
import pdk.chart.labels.CategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class ItemLabelDemo1 extends ApplicationFrame {
    public ItemLabelDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)11.0F, "S1", "C1");
        dataset.addValue(44.3, "S1", "C2");
        dataset.addValue((double)93.0F, "S1", "C3");
        dataset.addValue(35.6, "S1", "C4");
        dataset.addValue(75.1, "S1", "C5");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = ChartFactory.bar("Item Label Demo 1", "Category", "Value", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setRangePannable(true);
        plot.setRangeZeroBaselineVisible(true);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setUpperMargin(0.15);
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setDefaultItemLabelGenerator(new LabelGenerator((double)50.0F));
        renderer.setDefaultItemLabelFont(new Font("Serif", 0, 20));
        renderer.setDefaultItemLabelsVisible(true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        ItemLabelDemo1 demo = new ItemLabelDemo1("Chart: ItemLabelDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class LabelGenerator extends AbstractCategoryItemLabelGenerator implements CategoryItemLabelGenerator {
        private double threshold;

        public LabelGenerator(double threshold) {
            super("", new DecimalFormat("$#,##0.00"));
            this.threshold = threshold;
        }

        public String generateLabel(CategoryDataset dataset, int series, int category) {
            String result = null;
            Number value = dataset.getValue(series, category);
            if (value != null) {
                double v = value.doubleValue();
                if (v > this.threshold) {
                    result = value.toString();
                }
            }

            return result;
        }
    }
}
