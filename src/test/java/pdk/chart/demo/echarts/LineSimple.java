package pdk.chart.demo.echarts;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.internal.ShapeUtils;

import java.awt.*;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=line-simple
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 25 Jun 2026, 1:58 PM
 */
public class LineSimple {
    static void main() {
        Chart chart = JChart.line(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new double[]{150, 230, 224, 218, 135, 147, 260});
        chart.getCategoryPlot()
                .getLineAndShapeRenderer(0)
                .defaultShapesVisible(true)
                .seriesShape(0, ShapeUtils.createCircle(10))

                .seriesStroke(0, new BasicStroke(3f))
                .drawOutlines(true)
                .seriesOutlineStroke(0, new BasicStroke(2f))

                .useFillPaint(true)
                .defaultFillPaint(Color.WHITE);

        chart.show();
    }
}
