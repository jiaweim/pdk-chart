package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.SubCategoryAxis;
import pdk.chart.data.KeyToGroupMap;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.GroupedStackedBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;

public class StackedBarChartDemo5 extends ApplicationFrame {
    public StackedBarChartDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        result.addValue((double)3396.0F, "S1", "C1");
        result.addValue((double)1580.0F, "S2", "C1");
        result.addValue((double)76.0F, "S3", "C1");
        result.addValue((double)10100.0F, "S4", "C1");
        result.addValue((double)3429.0F, "S1", "C2");
        result.addValue((double)1562.0F, "S2", "C2");
        result.addValue((double)61.0F, "S3", "C2");
        result.addValue((double)-10100.0F, "S4", "C2");
        return result;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.barStacked("Stacked Bar Chart Demo 5", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
        KeyToGroupMap map = new KeyToGroupMap("G1");
        map.mapKeyToGroup("S1", "G1");
        map.mapKeyToGroup("S2", "G1");
        map.mapKeyToGroup("S3", "G2");
        map.mapKeyToGroup("S4", "G3");
        renderer.setSeriesToGroupMap(map);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setPositiveItemLabelPositionFallback(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
        renderer.setItemMargin(0.1);
        SubCategoryAxis domainAxis = new SubCategoryAxis("Category / Group");
        domainAxis.setCategoryMargin(0.05);
        domainAxis.addSubCategory("G1");
        domainAxis.addSubCategory("G2");
        domainAxis.addSubCategory("G3");
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setDomainAxis(domainAxis);
        plot.setRenderer(renderer);
        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        StackedBarChartDemo5 demo = new StackedBarChartDemo5("Stacked Bar Chart Demo 5");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
