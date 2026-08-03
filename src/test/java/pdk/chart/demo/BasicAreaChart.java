package pdk.chart.demo;

import pdk.chart.CategoryAreaChart;
import pdk.chart.CategoryChart;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.model.Data;
import pdk.chart.plot.DatasetRenderingOrder;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.util.ShapeUtils;

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

        CategoryAreaChart chart = new CategoryAreaChart(data);
        chart.removeLegend();
        chart.setDataset(1, data, CategoryChart.Type.LINE);

        CategoryAxis xAxis = chart.getDomainAxis();
        xAxis.setLowerMargin(0);
        xAxis.setUpperMargin(0);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) chart.getRenderer(1);
        renderer.withUseFillPaint(true)
                .withSeriesOutlineStroke(0, new BasicStroke(2f))
                .withDefaultFillPaint(Color.WHITE)
                .withSeriesShape(0, ShapeUtils.createCircle(6));

        chart.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        chart.show();
    }
}
