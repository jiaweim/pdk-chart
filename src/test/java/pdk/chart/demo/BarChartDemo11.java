package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import pdk.chart.JChart;
import pdk.chart.Chart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.NumberAxis;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;

public class BarChartDemo11 extends ApplicationFrame {
    public BarChartDemo11(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)33.0F, "S1", "GNU General Public License (GPL) 2.0");
        dataset.addValue((double)13.0F, "S1", "Apache License 2.0");
        dataset.addValue((double)12.0F, "S1", "GNU General Public License (GPL) 3.0");
        dataset.addValue((double)11.0F, "S1", "MIT License");
        dataset.addValue((double)7.0F, "S1", "BSD License 2.0");
        dataset.addValue((double)6.0F, "S1", "Artistic Licence (Perl)");
        dataset.addValue((double)6.0F, "S1", "GNU Lesser General Public License (LGPL) 2.1");
        dataset.addValue((double)3.0F, "S1", "GNU Lesser General Public License (LGPL) 3.0");
        dataset.addValue((double)2.0F, "S1", "Eclipse Public License");
        dataset.addValue((double)1.0F, "S1", "Code Project 1.02 License");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar("Open Source Projects By License", "License", "Percent", dataset);
        chart.removeLegend();
        TextTitle source = new TextTitle("Source: http://www.blackducksoftware.com/resources/data/top-20-licenses (as at 30 Aug 2013)", new Font("Dialog", 0, 9));
        source.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(source);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        plot.setDomainGridlinesVisible(true);
        plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(0.8F);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer)plot.getRenderer();
        renderer.setDrawBarOutline(false);
        StandardCategoryToolTipGenerator tt = new StandardCategoryToolTipGenerator("{1}: {2} percent", new DecimalFormat("0"));
        renderer.setDefaultToolTipGenerator(tt);
        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64));
        renderer.setSeriesPaint(0, gp0);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        BarChartDemo11 demo = new BarChartDemo11("Chart: BarChartDemo11.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
