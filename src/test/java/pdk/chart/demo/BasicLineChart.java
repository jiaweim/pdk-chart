package pdk.chart.demo;

import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryChartType;
import pdk.chart.internal.ShapeUtils;

import java.awt.*;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=line-simple
 * <p>
 * 不是完全实现，无法避免直线从圆中穿过。
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 11:05 AM
 */
public class BasicLineChart {

    static void main() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("Category",
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new double[]{150, 230, 224, 218, 135, 147, 260});

        CategoryXYChart.create()
                .dataset(dataset, CategoryChartType.LINE)
                .showLegend(true)
                .lineRenderer(0)
                .seriesShape(0, ShapeUtils.createCircle(6))
                .seriesLinesWidth(0, 2f)
                .seriesShapesFilled(0, false)
                .seriesOutlineStroke(0, new BasicStroke(2f))
                .done()
                .show();
    }
}
