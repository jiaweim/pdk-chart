package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.labels.StandardCategorySeriesLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import javax.swing.*;
import java.awt.*;

/**
 * A bar chart with a horizontal orientation.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 11 Jun 2026, 10:15 AM
 */
public class BarChartDemo2 extends ApplicationFrame {

    public BarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        double[][] data = new double[][]{{1.0, 43.0F, 35.0F, 58.0F, 54.0F, 77.0F, 71.0F, 89.0F},
                {54.0F, 75.0F, 63.0F, 83.0F, 43.0F, 46.0F, 27.0F, 13.0F},
                {41.0F, 33.0F, 22.0F, 34.0F, 62.0F, 32.0F, 42.0F, 34.0F}};
        return DatasetUtils.createCategoryDataset("Series ", "Factor ", data);
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.bar(dataset, "Category", "Score (%)", "Bar Chart Demo 2");
        CategoryPlot plot = chart.getCategoryPlot();
        plot.orientation(PlotOrientation.HORIZONTAL)
                .rangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.getRangeAxisAsNumber()
                .range(0, 100)
                .standardTickUnits(NumberAxis.createIntegerTickUnits());

        plot.getBarRenderer(0)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, new Color(0, 0, 128), 0.0F, 0.0F, Color.BLUE))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, new Color(0, 128, 0), 0.0F, 0.0F, Color.GREEN))
                .seriesPaint(2, new GradientPaint(0.0F, 0.0F, new Color(128, 0, 0), 0.0F, 0.0F, Color.RED))
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL))
                .drawBarOutline(false)
                .legendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        BarChartDemo2 demo = new BarChartDemo2("BarChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
