package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class StackedBarChartDemo3 extends ApplicationFrame {
    public StackedBarChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 10.0F, "Series 1", "Jan");
        dataset.addValue((double) 12.0F, "Series 1", "Feb");
        dataset.addValue((double) 13.0F, "Series 1", "Mar");
        dataset.addValue((double) 4.0F, "Series 2", "Jan");
        dataset.addValue((double) 3.0F, "Series 2", "Feb");
        dataset.addValue((double) 2.0F, "Series 2", "Mar");
        dataset.addValue((double) 2.0F, "Series 3", "Jan");
        dataset.addValue((double) 3.0F, "Series 3", "Feb");
        dataset.addValue((double) 2.0F, "Series 3", "Mar");
        dataset.addValue((double) 2.0F, "Series 4", "Jan");
        dataset.addValue((double) 3.0F, "Series 4", "Feb");
        dataset.addValue((double) 4.0F, "Series 4", "Mar");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.createStackedBarChart("Stacked Bar Chart Demo 3", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, false, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryItemRenderer renderer = new ExtendedStackedBarRenderer();
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(renderer);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLowerMargin(0.15);
        rangeAxis.setUpperMargin(0.15);
        rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        StackedBarChartDemo3 demo = new StackedBarChartDemo3("Stacked Bar Chart Demo 3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
