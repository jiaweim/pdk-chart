package pdk.chart.demo;

import pdk.chart.CategoryChart;
import pdk.chart.CategoryStackedBarChart;
import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.KeyToGroupMap;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.legend.LegendItemCollection;
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
        dataset.addValue(1.0, series1, category1);
        dataset.addValue(4.0, series1, category2);
        dataset.addValue(3.0, series1, category3);
        dataset.addValue(5.0, series1, category4);
        dataset.addValue(3.0, series2, category1);
        dataset.addValue(6.0, series2, category2);
        dataset.addValue(1.0, series2, category3);
        dataset.addValue(5.0, series2, category4);
        return dataset;
    }

    private static CategoryDataset createDataset2() {
        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4"};
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addSeries("Dummy 2", categories, new Number[]{null, null, null, null});
        dataset.addSeries("Series 2", categories, new double[]{75.0, 87.0, 96.0, 68.0});
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset1, CategoryDataset dataset2) {
        KeyToGroupMap<String, String> map = new KeyToGroupMap<>("G1");
        map.mapKeyToGroup("Series 1A", "G1");
        map.mapKeyToGroup("Series 1B", "G1");
        map.mapKeyToGroup("NOTHING", "G2");

        CategoryStackedBarChart chart = new CategoryStackedBarChart(dataset1,
                "Category", "Value", "Dual Axis Bar Chart");
        chart.setSeriesToGroupMap(map);
        chart.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        chart.setDataset(1, dataset2, CategoryChart.ChartType.BAR);
        chart.setRangeAxis(1, new NumberAxis("Secondary"), AxisLocation.BOTTOM_OR_RIGHT);
        chart.mapDatasetToRangeAxis(1, 1);

//        LegendItemCollection result = new LegendItemCollection();
//        LegendItemCollection legendItems = chart.getRenderer().getLegendItems();
//        for(int i = 0; i< legendItems.getItemCount(); i++) {
//            System.out.println(legendItems.get(i).getLabel());
//        }
//        result.addAll(chart.getRenderer().getLegendItems());
//        result.add(chart.getRenderer(1).getLegendItem(1, 1));
//        chart.setFixedLegendItems(result);

        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset1(), createDataset2());
        return new ChartPanel(chart);
    }

    static void main() {
        DualAxisDemo6 demo = new DualAxisDemo6("DualAxisDemo6.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
