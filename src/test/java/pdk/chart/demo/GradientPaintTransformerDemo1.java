package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.StandardBarPainter;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import javax.swing.*;
import java.awt.*;

public class GradientPaintTransformerDemo1 extends ApplicationFrame {
    public GradientPaintTransformerDemo1(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    private static Chart createChart(String title, CategoryDataset dataset) {
        Chart chart = JChart.bar(dataset, (String) null, "Value", title);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer r = (BarRenderer) plot.getRenderer();
        r.setItemMargin(0.02);
        return chart;
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 7.0F, "Series 1", "Category 1");
        dataset.addValue((double) 5.0F, "Series 2", "Category 1");
        return dataset;
    }

    public static JPanel createDemoPanel() {
        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        panel.setPreferredSize(new Dimension(800, 600));
        CategoryDataset dataset = createDataset();
        Chart chart1 = createChart("Type: VERTICAL", dataset);
        CategoryPlot plot1 = (CategoryPlot) chart1.getPlot();
        BarRenderer renderer1 = (BarRenderer) plot1.getRenderer();
        renderer1.setBarPainter(new StandardBarPainter());
        renderer1.setDrawBarOutline(false);
        renderer1.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW));
        renderer1.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN));
        renderer1.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
        ChartPanel chartPanel1 = new ChartPanel(chart1, false);
        panel.add(chartPanel1);
        Chart chart2 = createChart("Type: HORIZONTAL", dataset);
        CategoryPlot plot2 = (CategoryPlot) chart2.getPlot();
        BarRenderer renderer2 = (BarRenderer) plot2.getRenderer();
        renderer2.setBarPainter(new StandardBarPainter());
        renderer2.setDrawBarOutline(false);
        renderer2.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW));
        renderer2.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN));
        renderer2.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
        ChartPanel chartPanel2 = new ChartPanel(chart2, false);
        panel.add(chartPanel2);
        Chart chart3 = createChart("Type: CENTER_VERTICAL", dataset);
        CategoryPlot plot3 = (CategoryPlot) chart3.getPlot();
        BarRenderer renderer3 = (BarRenderer) plot3.getRenderer();
        renderer3.setBarPainter(new StandardBarPainter());
        renderer3.setDrawBarOutline(false);
        renderer3.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW));
        renderer3.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN));
        renderer3.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
        ChartPanel chartPanel3 = new ChartPanel(chart3, false);
        panel.add(chartPanel3);
        Chart chart4 = createChart("Type: CENTER_HORIZONTAL", dataset);
        CategoryPlot plot4 = (CategoryPlot) chart4.getPlot();
        BarRenderer renderer4 = (BarRenderer) plot4.getRenderer();
        renderer4.setBarPainter(new StandardBarPainter());
        renderer4.setDrawBarOutline(false);
        renderer4.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW));
        renderer4.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN));
        renderer4.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
        ChartPanel chartPanel4 = new ChartPanel(chart4, false);
        panel.add(chartPanel4);
        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);
        return panel;
    }

    public static void main(String[] args) {
        GradientPaintTransformerDemo1 demo = new GradientPaintTransformerDemo1("Chart: GradientPaintTransformerDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
