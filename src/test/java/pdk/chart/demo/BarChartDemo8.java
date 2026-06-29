package pdk.chart.demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import pdk.chart.JChart;
import pdk.chart.Chart;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class BarChartDemo8 extends ApplicationFrame {
    public BarChartDemo8(String title) {
        super(title);
        CategoryDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)1.0F, series1, category1);
        dataset.addValue((double)4.0F, series1, category2);
        dataset.addValue((double)3.0F, series1, category3);
        dataset.addValue((double)5.0F, series1, category4);
        dataset.addValue((double)5.0F, series1, category5);
        dataset.addValue((double)5.0F, series2, category1);
        dataset.addValue((double)7.0F, series2, category2);
        dataset.addValue((double)6.0F, series2, category3);
        dataset.addValue((double)8.0F, series2, category4);
        dataset.addValue((double)4.0F, series2, category5);
        dataset.addValue((double)4.0F, series3, category1);
        dataset.addValue((double)3.0F, series3, category2);
        dataset.addValue((double)2.0F, series3, category3);
        dataset.addValue((double)3.0F, series3, category4);
        dataset.addValue((double)6.0F, series3, category5);
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar("Bar Chart Demo 8", "Category", "Value", dataset);
        chart.removeLegend();
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.15);
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        BarChartDemo8 demo = new BarChartDemo8("Chart: BarChartDemo8.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
