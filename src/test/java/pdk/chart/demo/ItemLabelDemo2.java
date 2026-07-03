package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.AbstractCategoryItemLabelGenerator;
import pdk.chart.labels.CategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class ItemLabelDemo2 extends ApplicationFrame {
    public ItemLabelDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100.0F, "S1", "C1");
        dataset.addValue(44.3, "S1", "C2");
        dataset.addValue((double) 93.0F, "S1", "C3");
        dataset.addValue((double) 80.0F, "S2", "C1");
        dataset.addValue(75.1, "S2", "C2");
        dataset.addValue(15.1, "S2", "C3");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar(dataset, "Category", "Value", "Item Label Demo 2",
                PlotOrientation.HORIZONTAL);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.setRangePannable(true);
        plot.setRangeZeroBaselineVisible(true);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setUpperMargin(0.25F);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setItemLabelInsets(new RectangleInsets(7, 7, 7, 7));
        renderer.setDefaultItemLabelGenerator(new LabelGenerator((Integer) null));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        ItemLabelDemo2 demo = new ItemLabelDemo2("Chart: ItemLabelDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class LabelGenerator extends AbstractCategoryItemLabelGenerator implements CategoryItemLabelGenerator {
        private Integer category;
        private final NumberFormat formatter;

        public LabelGenerator(Integer category) {
            super("", NumberFormat.getInstance());
            this.formatter = NumberFormat.getPercentInstance();
            this.category = category;
        }

        public String generateLabel(CategoryDataset dataset, int series, int category) {
            String result = null;
            double base;
            if (this.category != null) {
                Number b = dataset.getValue(series, this.category.intValue());
                base = b.doubleValue();
            } else {
                base = this.calculateSeriesTotal(dataset, series);
            }

            Number value = dataset.getValue(series, category);
            if (value != null) {
                double v = value.doubleValue();
                result = value.toString() + " (" + this.formatter.format(v / base) + ")";
            }

            return result;
        }

        private double calculateSeriesTotal(CategoryDataset dataset, int series) {
            double result = (double) 0.0F;

            for (int i = 0; i < dataset.getColumnCount(); ++i) {
                Number value = dataset.getValue(series, i);
                if (value != null) {
                    result += value.doubleValue();
                }
            }

            return result;
        }
    }
}
