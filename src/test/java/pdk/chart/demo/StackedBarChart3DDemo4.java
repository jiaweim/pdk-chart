package pdk.chart.demo;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.StackedBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

public class StackedBarChart3DDemo4 extends ApplicationFrame {
    public StackedBarChart3DDemo4(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)10.0F, "Series 1", "C1");
        dataset.addValue((double)5.0F, "Series 1", "C2");
        dataset.addValue((double)6.0F, "Series 1", "C3");
        dataset.addValue((double)7.0F, "Series 1", "C4");
        dataset.addValue((double)8.0F, "Series 1", "C5");
        dataset.addValue((double)9.0F, "Series 1", "C6");
        dataset.addValue((double)10.0F, "Series 1", "C7");
        dataset.addValue((double)11.0F, "Series 1", "C8");
        dataset.addValue((double)3.0F, "Series 1", "C9");
        dataset.addValue((double)4.0F, "Series 2", "C1");
        dataset.addValue((double)7.0F, "Series 2", "C2");
        dataset.addValue((double)17.0F, "Series 2", "C3");
        dataset.addValue((double)15.0F, "Series 2", "C4");
        dataset.addValue((double)6.0F, "Series 2", "C5");
        dataset.addValue((double)8.0F, "Series 2", "C6");
        dataset.addValue((double)9.0F, "Series 2", "C7");
        dataset.addValue((double)13.0F, "Series 2", "C8");
        dataset.addValue((double)7.0F, "Series 2", "C9");
        dataset.addValue((double)15.0F, "Series 3", "C1");
        dataset.addValue((double)14.0F, "Series 3", "C2");
        dataset.addValue((double)12.0F, "Series 3", "C3");
        dataset.addValue((double)11.0F, "Series 3", "C4");
        dataset.addValue((double)10.0F, "Series 3", "C5");
        dataset.addValue((double)0.0F, "Series 3", "C6");
        dataset.addValue((double)7.0F, "Series 3", "C7");
        dataset.addValue((double)9.0F, "Series 3", "C8");
        dataset.addValue((double)11.0F, "Series 3", "C9");
        dataset.addValue((double)14.0F, "Series 4", "C1");
        dataset.addValue((double)3.0F, "Series 4", "C2");
        dataset.addValue((double)7.0F, "Series 4", "C3");
        dataset.addValue((double)0.0F, "Series 4", "C4");
        dataset.addValue((double)9.0F, "Series 4", "C5");
        dataset.addValue((double)6.0F, "Series 4", "C6");
        dataset.addValue((double)7.0F, "Series 4", "C7");
        dataset.addValue((double)9.0F, "Series 4", "C8");
        dataset.addValue((double)10.0F, "Series 4", "C9");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.createStackedBarChart("Stacked Bar Chart 3D Demo 4", "Category", "Value", dataset, PlotOrientation.HORIZONTAL, true, true, false);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        NumberAxis yAxis = (NumberAxis)plot.getRangeAxis();
        yAxis.setNumberFormatOverride(new DecimalFormat("0%"));
        StackedBarRenderer renderer = (StackedBarRenderer)plot.getRenderer();
        renderer.setRenderAsPercentages(true);
        renderer.setDrawBarOutline(false);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{3}", NumberFormat.getIntegerInstance(), new DecimalFormat("0.0%")));
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        renderer.setDefaultNegativeItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        StackedBarChart3DDemo4 demo = new StackedBarChart3DDemo4("Stacked Bar Chart 3D Demo 4");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
