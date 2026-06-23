package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.Chart;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.legend.LegendItem;
import pdk.chart.legend.LegendItemCollection;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;

public class StackedBarChartDemo2 extends ApplicationFrame {
    public StackedBarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)81.0F, "Against all torture", "Italy");
        dataset.addValue((double)72.0F, "Against all torture", "Great Britain");
        dataset.addValue((double)58.0F, "Against all torture", "USA");
        dataset.addValue((double)48.0F, "Against all torture", "Israel");
        dataset.addValue((double)43.0F, "Against all torture", "Russia");
        dataset.addValue((double)23.0F, "Against all torture", "India");
        dataset.addValue((double)59.0F, "Against all torture", "Average (*)");
        dataset.addValue((double)5.0F, "-", "Italy");
        dataset.addValue((double)4.0F, "-", "Great Britain");
        dataset.addValue((double)6.0F, "-", "USA");
        dataset.addValue((double)9.0F, "-", "Israel");
        dataset.addValue((double)20.0F, "-", "Russia");
        dataset.addValue((double)45.0F, "-", "India");
        dataset.addValue((double)12.0F, "-", "Average (*)");
        dataset.addValue((double)14.0F, "Some degree permissible", "Italy");
        dataset.addValue((double)24.0F, "Some degree permissible", "Great Britain");
        dataset.addValue((double)36.0F, "Some degree permissible", "USA");
        dataset.addValue((double)43.0F, "Some degree permissible", "Israel");
        dataset.addValue((double)37.0F, "Some degree permissible", "Russia");
        dataset.addValue((double)32.0F, "Some degree permissible", "India");
        dataset.addValue((double)29.0F, "Some degree permissible", "Average (*)");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = ChartFactory.createStackedBarChart("Public Opinion : Torture of Prisoners", "Country", "%", dataset, PlotOrientation.HORIZONTAL, false, true, false);
        chart.getTitle().setMargin((double)2.0F, (double)0.0F, (double)0.0F, (double)0.0F);
        TextTitle tt = new TextTitle("Source: http://news.bbc.co.uk/1/hi/world/6063386.stm", new Font("Dialog", 0, 11));
        tt.setPosition(RectangleEdge.BOTTOM);
        tt.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        tt.setMargin((double)0.0F, (double)0.0F, (double)4.0F, (double)4.0F);
        chart.addSubtitle(tt);
        TextTitle t = new TextTitle("(*) Across 27,000 respondents in 25 countries", new Font("Dialog", 0, 11));
        t.setPosition(RectangleEdge.BOTTOM);
        t.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        t.setMargin((double)4.0F, (double)0.0F, (double)2.0F, (double)4.0F);
        chart.addSubtitle(t);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        LegendItemCollection items = new LegendItemCollection();
        items.add(new LegendItem("Against all torture", (String)null, (String)null, (String)null, new Rectangle2D.Double((double)-6.0F, (double)-3.0F, (double)12.0F, (double)6.0F), Color.GREEN));
        items.add(new LegendItem("Some degree permissible", (String)null, (String)null, (String)null, new Rectangle2D.Double((double)-6.0F, (double)-3.0F, (double)12.0F, (double)6.0F), Color.RED));
        plot.setFixedLegendItems(items);
        plot.setInsets(new RectangleInsets((double)5.0F, (double)5.0F, (double)5.0F, (double)20.0F));
        LegendTitle legend = new LegendTitle(plot);
        legend.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legend);
        plot.setDomainGridlinesVisible(true);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin((double)0.0F);
        BarRenderer renderer = (BarRenderer)plot.getRenderer();
        renderer.setDrawBarOutline(false);
        ChartUtils.applyCurrentTheme(chart);
        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0));
        Paint gp1 = new Color(0, 0, 0, 0);
        GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        StackedBarChartDemo2 demo = new StackedBarChartDemo2("Chart: StackedBarChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
