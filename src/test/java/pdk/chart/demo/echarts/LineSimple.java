package pdk.chart.demo.echarts;

import pdk.chart.CategoryLineChart;
import pdk.chart.swing.ChartPanel;
import pdk.chart.util.ShapeUtils;

import javax.swing.*;
import java.awt.*;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=line-simple
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 25 Jun 2026, 1:58 PM
 */
public class LineSimple {

    private static CategoryLineChart createChart() {
        CategoryLineChart chart = new CategoryLineChart(
                new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"},
                new double[]{150, 230, 224, 218, 135, 147, 260});
        chart.setDefaultShapesVisible(true);
        chart.setSeriesShape(0, ShapeUtils.createCircle(10));
        chart.setSeriesStroke(0, new BasicStroke(3f));

        chart.setDrawOutlines(true);
        chart.setSeriesOutlineStroke(0, new BasicStroke(2f));

        chart.setUseFillPaint(true);
        chart.setDefaultFillPaint(Color.WHITE);

        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        createChart().show();
    }
}
