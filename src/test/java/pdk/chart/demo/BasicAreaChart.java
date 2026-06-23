package pdk.chart.demo;

import pdk.chart.api.RectangleInsets;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=area-basic
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 10:43 AM
 */
public class BasicAreaChart {
    static void main() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("",
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new double[]{820, 932, 901, 934, 1290, 1330, 1320});

        CategoryXYChart.create()
                .dataset(dataset, CategoryXYChartType.AREA)
                .axisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0))
                .domainAxis()
                .lowerMargin(0.0)
                .upperMargin(0.0)
                .categoryMargin(0.0)
                .chart()
                .show();
    }
}
