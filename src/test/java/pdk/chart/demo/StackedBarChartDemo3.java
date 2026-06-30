package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.Data;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
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

    private static CategoryDataset<String, String> createDataset() {
        String[] categories = new String[]{"Jan", "Feb", "Mar"};
        return Data.<String, String>category()
                .addSeries("Series 1", categories,
                        new double[]{10.0, 12.0, 13.0})
                .addSeries("Series 2", categories,
                        new double[]{4.0, 3.0, 2.0})
                .addSeries("Series 3", categories,
                        new double[]{2.0, 3.0, 2.0})
                .addSeries("Series 4", categories,
                        new double[]{2.0, 3.0, 4.0})
                .build();
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.barStacked("Stacked Bar Chart Demo 3", "Category", "Value",
                dataset, PlotOrientation.VERTICAL, true, false, false);

        CategoryPlot plot = chart.getCategoryPlot();
        ExtendedStackedBarRenderer renderer = new ExtendedStackedBarRenderer();
        renderer.defaultItemLabelsVisible(true)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .defaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        plot.setRenderer(renderer);

        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .lowerMargin(0.15)
                .upperMargin(0.15)
                .numberFormatOverride(NumberFormat.getPercentInstance());

        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        StackedBarChartDemo3 demo = new StackedBarChartDemo3("Stacked Bar Chart Demo 3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
