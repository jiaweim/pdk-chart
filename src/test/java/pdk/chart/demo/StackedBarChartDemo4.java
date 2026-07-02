package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.JChart;
import pdk.chart.axis.SubCategoryAxis;
import pdk.chart.data.KeyToGroupMap;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.Data;
import pdk.chart.legend.LegendItem;
import pdk.chart.legend.LegendItemCollection;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.Plot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.GroupedStackedBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import javax.swing.*;
import java.awt.*;

public class StackedBarChartDemo4 extends ApplicationFrame {
    public StackedBarChartDemo4(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(590, 350));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        String[] categories = new String[]{"Jan 04", "Feb 04", "Mar 04"};
        return Data.<String, String>category()
                .addSeries("Product 1 (US)", categories,
                        new double[]{20.3, 27.2, 19.7})
                .addSeries("Product 1 (Europe)", categories,
                        new double[]{19.4, 10.9, 18.4})
                .addSeries("Product 1 (Asia)", categories,
                        new double[]{16.5, 15.9, 16.1})
                .addSeries("Product 1 (Middle East)", categories,
                        new double[]{13.2, 14.4, 13.7})
                .addSeries("Product 2 (US)", categories,
                        new double[]{23.3, 16.2, 28.7})
                .addSeries("Product 2 (Europe)", categories,
                        new double[]{12.7, 17.9, 12.6})
                .addSeries("Product 2 (Asia)", categories,
                        new double[]{15.4, 21.0, 11.1})
                .addSeries("Product 2 (Middle East)", categories,
                        new double[]{23.8, 23.4, 19.3})
                .addSeries("Product 3 (US)", categories,
                        new double[]{11.9, 31.0, 22.7})
                .addSeries("Product 3 (Europe)", categories,
                        new double[]{15.3, 14.4, 25.3})
                .addSeries("Product 3 (Asia)", categories,
                        new double[]{23.9, 19.0, 10.1})
                .addSeries("Product 3 (Middle East)", categories,
                        new double[]{13.2, 15.5, 10.1})
                .build();
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.barStacked("Stacked Bar Chart Demo 4", "Category", "Value",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
        KeyToGroupMap<String, String> map = new KeyToGroupMap<>("G1");
        map.mapKeyToGroup("Product 1 (US)", "G1");
        map.mapKeyToGroup("Product 1 (Europe)", "G1");
        map.mapKeyToGroup("Product 1 (Asia)", "G1");
        map.mapKeyToGroup("Product 1 (Middle East)", "G1");
        map.mapKeyToGroup("Product 2 (US)", "G2");
        map.mapKeyToGroup("Product 2 (Europe)", "G2");
        map.mapKeyToGroup("Product 2 (Asia)", "G2");
        map.mapKeyToGroup("Product 2 (Middle East)", "G2");
        map.mapKeyToGroup("Product 3 (US)", "G3");
        map.mapKeyToGroup("Product 3 (Europe)", "G3");
        map.mapKeyToGroup("Product 3 (Asia)", "G3");
        map.mapKeyToGroup("Product 3 (Middle East)", "G3");
        renderer.setSeriesToGroupMap(map);
        renderer.setItemMargin(0.1);
        renderer.setDrawBarOutline(false);
        SubCategoryAxis domainAxis = new SubCategoryAxis("Product / Month");
        domainAxis.setCategoryMargin(0.05);
        domainAxis.addSubCategory("Product 1");
        domainAxis.addSubCategory("Product 2");
        domainAxis.addSubCategory("Product 3");
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setDomainAxis(domainAxis);
        plot.setRenderer(renderer);
        plot.setFixedLegendItems(createLegendItems());
        JChartUtils.applyCurrentTheme(chart);
        domainAxis.setSubLabelFont(new Font("Tahoma", Font.ITALIC, 10));
        Paint p1 = new GradientPaint(0.0F, 0.0F, new Color(34, 34, 255), 0.0F, 0.0F, new Color(136, 136, 255));
        renderer.setSeriesPaint(0, p1);
        renderer.setSeriesPaint(4, p1);
        renderer.setSeriesPaint(8, p1);
        Paint p2 = new GradientPaint(0.0F, 0.0F, new Color(34, 255, 34), 0.0F, 0.0F, new Color(136, 255, 136));
        renderer.setSeriesPaint(1, p2);
        renderer.setSeriesPaint(5, p2);
        renderer.setSeriesPaint(9, p2);
        Paint p3 = new GradientPaint(0.0F, 0.0F, new Color(255, 34, 34), 0.0F, 0.0F, new Color(255, 136, 136));
        renderer.setSeriesPaint(2, p3);
        renderer.setSeriesPaint(6, p3);
        renderer.setSeriesPaint(10, p3);
        Paint p4 = new GradientPaint(0.0F, 0.0F, new Color(255, 255, 34), 0.0F, 0.0F, new Color(255, 255, 136));
        renderer.setSeriesPaint(3, p4);
        renderer.setSeriesPaint(7, p4);
        renderer.setSeriesPaint(11, p4);
        renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
        return chart;
    }

    private static LegendItemCollection createLegendItems() {
        LegendItemCollection result = new LegendItemCollection();
        LegendItem item1 = new LegendItem("US", "-", null, null, Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(34, 34, 255));
        LegendItem item2 = new LegendItem("Europe", "-", null, null, Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(34, 255, 34));
        LegendItem item3 = new LegendItem("Asia", "-", null, null, Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(255, 34, 34));
        LegendItem item4 = new LegendItem("Middle East", "-", null, null, Plot.DEFAULT_LEGEND_ITEM_BOX, new Color(255, 255, 34));
        result.add(item1);
        result.add(item2);
        result.add(item3);
        result.add(item4);
        return result;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        StackedBarChartDemo4 demo = new StackedBarChartDemo4("Stacked Bar Chart Demo 4");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
