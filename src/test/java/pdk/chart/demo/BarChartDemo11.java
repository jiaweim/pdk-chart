package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.Data;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * A simple bar chart with a horizontal orientation.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 12 Jun 2026, 10:28 AM
 */
public class BarChartDemo11 extends ApplicationFrame {

    public BarChartDemo11(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        return Data.createCategory("S1", new String[]{
                        "GNU General Public License (GPL) 2.0",
                        "Apache License 2.0",
                        "GNU General Public License (GPL) 3.0",
                        "MIT License",
                        "BSD License 2.0",
                        "Artistic Licence (Perl)",
                        "GNU Lesser General Public License (LGPL) 2.1",
                        "GNU Lesser General Public License (LGPL) 3.0",
                        "Eclipse Public License",
                        "Code Project 1.02 License"
                },
                new double[]{33.0, 13.0, 12.0, 11.0, 7.0, 6.0, 6.0, 3.0, 2.0, 1.0});
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.bar("Open Source Projects By License", "License", "Percent", dataset);
        chart.removeLegend();

        TextTitle source = new TextTitle("Source: http://www.blackducksoftware.com/resources/data/top-20-licenses (as at 30 Aug 2013)",
                new Font("Dialog", Font.PLAIN, 9));
        source.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(source);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.orientation(PlotOrientation.HORIZONTAL)
                .domainGridlinesVisible(true);

        plot.getDomainAxis()
                .maximumCategoryLabelWidthRatio(0.8f);
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        plot.getBarRenderer(0)
                .drawBarOutline(false)
                .defaultToolTipGenerator(new StandardCategoryToolTipGenerator<>("{1}: {2} percent",
                        new DecimalFormat("0")))
                .seriesPaint(0,
                        new GradientPaint(0.0F, 0.0F, Color.BLUE,
                                0.0F, 0.0F, new Color(0, 0, 64)));

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        BarChartDemo11 demo = new BarChartDemo11("BarChartDemo11.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
