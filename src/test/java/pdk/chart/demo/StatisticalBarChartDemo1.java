package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.statistics.DefaultStatisticalCategoryDataset;
import pdk.chart.CategoryChartType;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;

public class StatisticalBarChartDemo1 extends ApplicationFrame {

    public static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.create(dataset, CategoryChartType.BAR_STATISTICS,
                "Statistical Bar Chart Demo 1", "Type", "Value",
                PlotOrientation.VERTICAL, true, true);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .autoRangeIncludesZero(false);
        ChartUtils.applyCurrentTheme(chart);
        plot.getBarRenderer(0)
                .drawBarOutline(false)
                .errorIndicatorPaint(Color.BLACK)
                .includeBaseInRange(false)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .defaultItemLabelsVisible(true)
                .defaultItemLabelPaint(Color.YELLOW)
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE6, TextAnchor.BOTTOM_CENTER))
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64)))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0)));

        return chart;
    }

    public static CategoryDataset<String, String> createDataset() {
        DefaultStatisticalCategoryDataset<String, String> dataset = new DefaultStatisticalCategoryDataset<>();
        dataset.add(10.0, 2.4, "Row 1", "Column 1");
        dataset.add(15.0, 4.4, "Row 1", "Column 2");
        dataset.add(13.0, 2.1, "Row 1", "Column 3");
        dataset.add(7.0, 1.3, "Row 1", "Column 4");
        dataset.add(22.0, 2.4, "Row 2", "Column 1");
        dataset.add(18.0, 4.4, "Row 2", "Column 2");
        dataset.add(28.0, 2.1, "Row 2", "Column 3");
        dataset.add(17.0, 1.3, "Row 2", "Column 4");
        return dataset;
    }

    public StatisticalBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        CategoryDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        return new ChartPanel(chart);
    }

    static void main() {
        StatisticalBarChartDemo1 demo = new StatisticalBarChartDemo1("Chart: StatisticalBarChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
