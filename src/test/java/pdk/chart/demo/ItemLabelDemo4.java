package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class ItemLabelDemo4 extends ApplicationFrame {
    public ItemLabelDemo4(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 212.0F, "Classes", "JDK 1.0");
        dataset.addValue((double) 504.0F, "Classes", "JDK 1.1");
        dataset.addValue((double) 1520.0F, "Classes", "JDK 1.2");
        dataset.addValue((double) 1842.0F, "Classes", "JDK 1.3");
        dataset.addValue((double) 2991.0F, "Classes", "JDK 1.4");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.line(dataset, "Release", "Class Count", "Java Standard Class Library",
                PlotOrientation.VERTICAL, false, true);
        chart.addSubtitle(new TextTitle("Number of Classes By Release"));
        TextTitle source = new TextTitle("Source: Java In A Nutshell (4th Edition) by David Flanagan (O'Reilly)");
        source.setFont(new Font("SansSerif", 0, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        JChartUtils.applyCurrentTheme(chart);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setUpperMargin(0.15);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setDefaultFillPaint(Color.WHITE);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("$#,##0.00")));
        renderer.setDefaultItemLabelsVisible(true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        ItemLabelDemo4 demo = new ItemLabelDemo4("Chart: ItemLabelDemo4.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
