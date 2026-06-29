package pdk.chart.demo.fluent;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.fluent.Data;
import pdk.chart.fluent.XYChartType;
import pdk.chart.internal.ShapeUtils;
import pdk.chart.plot.XYPlot;

import java.awt.*;

/**
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Jun 2026, 1:46 PM
 */
public class XYLineAndShapeRendererDemo2 {
    static void main() {
        XYDataset<String> dataset1 = Data.createXY("Series 1", new double[]{1, 2, 3}, new double[]{1, 1, 1});
        XYDataset<String> dataset2 = Data.createXY("Series 2", new double[]{1, 2, 3}, new double[]{2, 2, 2});
        XYDataset<String> dataset3 = Data.createXY("Series 3", new double[]{1, 2, 3}, new double[]{3, 3, 3});
        XYDataset<String> dataset4 = Data.createXY("Series 4", new double[]{1, 2, 3}, new double[]{4, 4, 4});
        XYDataset<String> dataset5 = Data.createXY("Series 5", new double[]{1, 2, 3}, new double[]{5, 5, 5});

        Shape shape = ShapeUtils.createCircle(8);
        Chart line = JChart.line("XYLineAndShapeRendererDemo2", "x", "y",
                dataset1);
        XYPlot plot = line.getXYPlot();
        plot.addDataset(dataset2, XYChartType.LINE);
        plot.addDataset(dataset3, XYChartType.LINE);
        plot.addDataset(dataset4, XYChartType.LINE);
        plot.addDataset(dataset5, XYChartType.LINE);

        plot.getLineAndShapeRenderer(0)
                .defaultVisible(true, true)
                .seriesShape(0, shape)
                .seriesPaint(0, Color.RED)
                .seriesFillPaint(0, Color.YELLOW)
                .seriesOutlinePaint(0, Color.GRAY)
                .showTooltips(true);
        plot.getLineAndShapeRenderer(1)
                .defaultShapesVisible(true)
                .seriesShape(0, shape)
                .seriesPaint(0, Color.RED)
                .seriesFillPaint(0, Color.YELLOW)
                .seriesOutlinePaint(0, Color.GRAY)
                .useFillPaint(true)
                .showTooltips(true);
        plot.getLineAndShapeRenderer(2)
                .defaultShapesVisible(true)
                .seriesShape(0, shape)
                .seriesPaint(0, Color.RED)
                .seriesFillPaint(0, Color.YELLOW)
                .seriesOutlinePaint(0, Color.GRAY)
                .useOutlinePaint(true)
                .showTooltips(true);

        plot.getLineAndShapeRenderer(3)
                .defaultShapesVisible(true)
                .seriesShape(0, shape)
                .seriesPaint(0, Color.RED)
                .seriesFillPaint(0, Color.YELLOW)
                .seriesOutlinePaint(0, Color.GRAY)
                .useFillPaint(true)
                .useOutlinePaint(true)
                .showTooltips(true);

        plot.getLineAndShapeRenderer(4)
                .defaultShapesVisible(true)
                .seriesShape(0, shape)
                .seriesPaint(0, Color.RED)
                .seriesFillPaint(0, Color.YELLOW)
                .seriesOutlinePaint(0, Color.GRAY)
                .useOutlinePaint(true)
                .useFillPaint(true)
                .drawOutlines(true)
                .showTooltips(true);

        plot.rangeAxisNumber()
                .autoRangeIncludesZero(false);
        line.show();
    }
}
