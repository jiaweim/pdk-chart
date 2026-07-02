package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.DefaultDrawingSupplier;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.StackedBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class ItemLabelDemo5 extends ApplicationFrame {
    public ItemLabelDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static CategoryDataset createDataset() {
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        categoryDataset.addValue(52.83, "Germany", "Western EU");
        categoryDataset.addValue(20.83, "France", "Western EU");
        categoryDataset.addValue(10.83, "Great Britain", "Western EU");
        categoryDataset.addValue(7.33, "Netherlands", "Western EU");
        categoryDataset.addValue(4.66, "Belgium", "Western EU");
        categoryDataset.addValue(57.14, "Spain", "Southern EU");
        categoryDataset.addValue(14.28, "Greece", "Southern EU");
        categoryDataset.addValue(14.28, "Italy", "Southern EU");
        categoryDataset.addValue(14.28, "Portugal", "Southern EU");
        categoryDataset.addValue((double) 100.0F, "Czech Republic", "Eastern EU");
        categoryDataset.addValue(66.66, "Denmark", "Scandinavia");
        categoryDataset.addValue(33.33, "Finland", "Scandinavia");
        categoryDataset.addValue((double) 0.0F, "", "Africa");
        categoryDataset.addValue((double) 100.0F, "Israel", "Asia");
        return categoryDataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.barStacked("Item Label Demo 5", (String) null, (String) null, dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        MyStackedBarRenderer renderer = new MyStackedBarRenderer();
        plot.setRenderer(renderer);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, (double) 0.0F);
        renderer.setPositiveItemLabelPositionFallback(position);
        renderer.setNegativeItemLabelPositionFallback(position);
        StandardCategoryItemLabelGenerator scilg = new StandardCategoryItemLabelGenerator("{0}", NumberFormat.getInstance());
        renderer.setDefaultItemLabelGenerator(scilg);
        renderer.setDefaultItemLabelsVisible(true);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setUpperBound((double) 100.0F);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        ItemLabelDemo5 demo = new ItemLabelDemo5("Chart: ItemLabelDemo5.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    private static class MyStackedBarRenderer extends StackedBarRenderer {
        int oldColumn;
        int count;
        Paint[] list;

        private MyStackedBarRenderer() {
            this.oldColumn = -99;
            this.count = 0;
            this.list = DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE;
        }

        public Paint getItemPaint(int row, int column) {
            if (this.oldColumn != column) {
                this.count = 0;
                this.oldColumn = column;
            } else {
                ++this.count;
            }

            return this.list[this.count];
        }
    }
}
