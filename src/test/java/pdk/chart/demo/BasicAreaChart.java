package pdk.chart.demo;

import pdk.chart.CategoryChartType;
import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.util.ShapeUtils;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.DatasetRenderingOrder;

import java.awt.*;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=area-basic
 *
 * @author Jiawei Mao
 * @version 1.0.0⭐
 * @since 05 Jun 2026, 10:43 AM
 */
public class BasicAreaChart {
    static void main() {

        CategoryDataset<String, String> data = Data.createCategory("",
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new double[]{820, 932, 901, 934, 1290, 1330, 1320}
        );

        Chart chart = JChart.area(data);
        chart.removeLegend();
        CategoryPlot plot = chart.getCategoryPlot();
        plot.addDataset(data, CategoryChartType.LINE);
        plot.getDomainAxis()
                .lowerMargin(0)
                .upperMargin(0);
        plot.getLineAndShapeRenderer(1)
                .useFillPaint(true)
                .seriesOutlineStroke(0, new BasicStroke(2f))
                .defaultFillPaint(Color.WHITE)
                .seriesShape(0, ShapeUtils.createCircle(6));
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        chart.show();
    }
}
