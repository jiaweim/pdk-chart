package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.KeyToGroupMap;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.legend.LegendItem;
import pdk.chart.legend.LegendItemCollection;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.renderer.category.GroupedStackedBarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class DualAxisDemo6 extends ApplicationFrame {
    public DualAxisDemo6(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset1() {
        String series1 = "Series 1A";
        String series2 = "Series 1B";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 1.0F, series1, category1);
        dataset.addValue((double) 4.0F, series1, category2);
        dataset.addValue((double) 3.0F, series1, category3);
        dataset.addValue((double) 5.0F, series1, category4);
        dataset.addValue((double) 3.0F, series2, category1);
        dataset.addValue((double) 6.0F, series2, category2);
        dataset.addValue((double) 1.0F, series2, category3);
        dataset.addValue((double) 5.0F, series2, category4);
        return dataset;
    }

    private static CategoryDataset createDataset2() {
        String series1 = "Dummy 2";
        String series2 = "Series 2";
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((Number) null, series1, category1);
        dataset.addValue((Number) null, series1, category2);
        dataset.addValue((Number) null, series1, category3);
        dataset.addValue((Number) null, series1, category4);
        dataset.addValue((double) 75.0F, series2, category1);
        dataset.addValue((double) 87.0F, series2, category2);
        dataset.addValue((double) 96.0F, series2, category3);
        dataset.addValue((double) 68.0F, series2, category4);
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset1, CategoryDataset dataset2) {
        CategoryAxis domainAxis = new CategoryAxis("Category");
        NumberAxis rangeAxis = new NumberAxis("Value");
        GroupedStackedBarRenderer renderer1 = new GroupedStackedBarRenderer();
        KeyToGroupMap map = new KeyToGroupMap("G1");
        map.mapKeyToGroup("Series 1A", "G1");
        map.mapKeyToGroup("Series 1B", "G1");
        map.mapKeyToGroup("NOTHING", "G2");
        renderer1.setSeriesToGroupMap(map);
        CategoryPlot plot = new CategoryPlot(dataset1, domainAxis, rangeAxis, renderer1) {
            public LegendItemCollection getLegendItems() {
                LegendItemCollection result = new LegendItemCollection();
                result.addAll(this.getRenderer().getLegendItems());
                CategoryDataset dset2 = this.getDataset(1);
                if (dset2 != null) {
                    CategoryItemRenderer renderer2 = this.getRenderer(1);
                    if (renderer2 != null) {
                        LegendItem item = renderer2.getLegendItem(1, 1);
                        result.add(item);
                    }
                }

                return result;
            }
        };
        Chart chart = new Chart("Dual Axis Bar Chart", plot);
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);
        ValueAxis axis2 = new NumberAxis("Secondary");
        plot.setRangeAxis(1, axis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        BarRenderer renderer2 = new BarRenderer();
        plot.setRenderer(1, renderer2);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset1(), createDataset2());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        DualAxisDemo6 demo = new DualAxisDemo6("Chart: DualAxisDemo6.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
