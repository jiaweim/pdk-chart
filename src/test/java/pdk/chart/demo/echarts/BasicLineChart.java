package pdk.chart.demo.echarts;

import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ChartPanel;
import pdk.chart.util.ShapeUtils;

import javax.swing.*;
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

    private static Chart createChart() {
        CategoryDataset<String, String> dataset = Data.createCategory(
                "Category",
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new double[]{150, 230, 224, 218, 135, 147, 260}
        );

        Chart chart = JChart.line(dataset);

        LineAndShapeRenderer renderer = chart.getCategoryPlot().getLineAndShapeRenderer();
        renderer.setAutoPopulateSeriesShape(false);

        renderer.defaultShapesVisible(true)
                .drawOutlines(true)
                .useFillPaint(true)
                .defaultFillPaint(Color.WHITE)
                .defaultShape(ShapeUtils.createCircle(6))
                .defaultShapesVisible(true)
                .defaultShapesFilled(true)

                .seriesLinesWidth(0, 2f)
                .seriesOutlineStroke(0, new BasicStroke(2f));
        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        Chart chart = createChart();
        chart.show();
    }
}
