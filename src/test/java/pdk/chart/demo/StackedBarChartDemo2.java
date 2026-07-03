package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
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

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * A stacked bar chart with a horizontal orientation.
 * <p>
 * A completely transparent color is used for the middle series, so that it leaves a gap in the chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 22 Jun 2026, 10:40 AM
 */
public class StackedBarChartDemo2 extends ApplicationFrame {
    public StackedBarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        String[] categories = {"Italy", "Great Britain", "USA", "Israel", "Russia", "India", "Average (*)"};
        return Data.<String, String>category()
                .addSeries("Against all torture", categories,
                        new double[]{81.0, 72.0, 58.0, 48.0, 43.0, 23.0, 59.0})
                .addSeries("-", categories,
                        new double[]{5.0, 4.0, 6.0, 9.0, 20.0, 45.0, 12.0})
                .addSeries("Some degree permissible", categories,
                        new double[]{14.0, 24.0, 36.0, 43.0, 37.0, 32.0, 29.0,})
                .build();
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.barStacked(dataset, "Country", "%",
                "Public Opinion : Torture of Prisoners",
                PlotOrientation.HORIZONTAL, false, true);

        chart.getTitle().setMargin(2.0, 0.0, 0.0, 0.0);

        TextTitle tt = new TextTitle("Source: http://news.bbc.co.uk/1/hi/world/6063386.stm", new Font("Dialog", Font.PLAIN, 11));
        tt.setPosition(RectangleEdge.BOTTOM);
        tt.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        tt.setMargin(0.0, 0.0, 4.0, 4.0);
        chart.addSubtitle(tt);

        TextTitle t = new TextTitle("(*) Across 27,000 respondents in 25 countries", new Font("Dialog", Font.PLAIN, 11));
        t.setPosition(RectangleEdge.BOTTOM);
        t.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        t.setMargin(4.0, 0.0, 2.0, 4.0);
        chart.addSubtitle(t);

        CategoryPlot plot = chart.getCategoryPlot();
        LegendItemCollection items = new LegendItemCollection();
        items.add(new LegendItem("Against all torture", null, null, null,
                new Rectangle2D.Double(-6.0, -3.0, 12.0, 6.0), Color.GREEN));
        items.add(new LegendItem("Some degree permissible", null, null, null,
                new Rectangle2D.Double(-6.0, -3.0, 12.0, 6.0), Color.RED));
        plot.setFixedLegendItems(items);

        plot.setInsets(new RectangleInsets(5.0, 5.0, 5.0, 20.0));
        LegendTitle legend = new LegendTitle(plot);
        legend.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legend);

        plot.setDomainGridlinesVisible(true);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.0F);

        plot.getBarRenderer(0)
                .drawBarOutline(false);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        JChartUtils.applyCurrentTheme(chart);

        plot.getBarRenderer(0)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0)))
                .seriesPaint(1, new Color(0, 0, 0, 0)) // 完全透明
                .seriesPaint(2, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0)));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        StackedBarChartDemo2 demo = new StackedBarChartDemo2("StackedBarChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
