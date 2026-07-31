package pdk.chart.demo.echarts;

import pdk.chart.CategoryLineChart;
import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.swing.ChartPanel;
import pdk.chart.util.ShapeUtils;

import javax.swing.*;
import java.awt.*;

/**
 * https://echarts.apache.org/examples/en/editor.html?c=line-simple
 * <p>
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

        CategoryLineChart chart = new CategoryLineChart(dataset);

        // set shape
        chart.setAutoPopulateSeriesShape(false);
        chart.setDefaultShapesVisible(true);
        chart.setDefaultShape(ShapeUtils.createCircle(6));

        // shape outline
        chart.setDrawOutlines(true);
        chart.setSeriesOutlineStroke(0, new BasicStroke(2f));

        // shape fill
        chart.setUseFillPaint(true);
        chart.setDefaultFillPaint(Color.WHITE);
        chart.setDefaultShapesFilled(true);

        // set line
        chart.setSeriesLinesWidth(0, 2f);

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
