package pdk.chart.demo.fluent;

import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.internal.ShapeUtils;

import java.awt.*;

/**
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Jun 2026, 1:46 PM
 */
public class XYLineAndShapeRendererDemo2 {
    static void main() {
        XYSeriesCollection<String> dataset1 = new XYSeriesCollection<>(new XYSeries<>("Series 1", new double[]{1, 2, 3}, new double[]{1, 1, 1}));
        XYSeriesCollection<String> dataset2 = new XYSeriesCollection<>(new XYSeries<>("Series 2", new double[]{1, 2, 3}, new double[]{2, 2, 2}));
        XYSeriesCollection<String> dataset3 = new XYSeriesCollection<>(new XYSeries<>("Series 3", new double[]{1, 2, 3}, new double[]{3, 3, 3}));
        XYSeriesCollection<String> dataset4 = new XYSeriesCollection<>(new XYSeries<>("Series 4", new double[]{1, 2, 3}, new double[]{4, 4, 4}));
        XYSeriesCollection<String> dataset5 = new XYSeriesCollection<>(new XYSeries<>("Series 5", new double[]{1, 2, 3}, new double[]{5, 5, 5}));

        Shape shape = ShapeUtils.createCircle(8);
        XYChart.create()
                .title("XYLineAndShapeRendererDemo2")
                .dataset(dataset1, XYChartType.LINE)
                .addDataset(dataset2, XYChartType.LINE)
                .addDataset(dataset3, XYChartType.LINE)
                .addDataset(dataset4, XYChartType.LINE)
                .addDataset(dataset5, XYChartType.LINE)
                .axisNames("X", "Y")
                .showLegend(true)

                .lineAndShapeProps(0)
                .defaultVisible(true, true)
                .seriesShape(0, shape)
                .seriesPaint(0, Color.RED)
                .seriesFillPaint(0, Color.YELLOW)
                .seriesOutlinePaint(0, Color.GRAY)
                .addTooltips(true)
                .done()

                .lineAndShapeProps(1)
                .defaultShapesVisible(true)
                .seriesShape(0, shape)
                .seriesPaint(0, Color.RED)
                .seriesFillPaint(0, Color.YELLOW)
                .seriesOutlinePaint(0, Color.GRAY)
                .useFillPaint(true)
                .addTooltips(true)
                .done()

                .lineAndShapeProps(2)
                .defaultShapesVisible(true)
                .seriesShape(0, shape)
                .seriesPaint(0, Color.RED)
                .seriesFillPaint(0, Color.YELLOW)
                .seriesOutlinePaint(0, Color.GRAY)
                .useOutlinePaint(true)
                .addTooltips(true)
                .done()

                .lineAndShapeProps(3)
                .defaultShapesVisible(true)
                .seriesShape(0, shape)
                .seriesPaint(0, Color.RED)
                .seriesFillPaint(0, Color.YELLOW)
                .seriesOutlinePaint(0, Color.GRAY)
                .useFillPaint(true)
                .useOutlinePaint(true)
                .addTooltips(true)
                .done()

                .lineAndShapeProps(4)
                .defaultShapesVisible(true)
                .seriesShape(0, shape)
                .seriesPaint(0, Color.RED)
                .seriesFillPaint(0, Color.YELLOW)
                .seriesOutlinePaint(0, Color.GRAY)
                .useOutlinePaint(true)
                .useFillPaint(true)
                .drawOutlines(true)
                .addTooltips(true)
                .done()

                .domainAxis().autoRangeIncludesZero(false).done()
                .rangeAxis().autoRangeIncludesZero(false).done()
                .show();
    }
}
