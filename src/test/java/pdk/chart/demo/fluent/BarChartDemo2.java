package pdk.chart.demo.fluent;

import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.StandardCategorySeriesLabelGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import java.awt.*;

/**
 * A bar chart with a horizontal orientation.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 11 Jun 2026, 10:15 AM
 */
public class BarChartDemo2 {

    private static CategoryDataset<String, String> createDataset() {
        double[][] data = new double[][]{
                {1.0, 43.0, 35.0, 58.0, 54.0, 77.0, 71.0, 89.0},
                {54.0, 75.0, 63.0, 83.0, 43.0, 46.0, 27.0, 13.0},
                {41.0, 33.0, 22.0, 34.0, 62.0, 32.0, 42.0, 34.0}
        };
        return DatasetUtils.createCategoryDataset("Series ", "Factor ", data);
    }

    static void main() {
        CategoryXYChart chart = CategoryXYChart.create()
                .title("Bar Chart Demo 2")
                .showLegend(true)
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .axisNames("Category", "Score (%)")
                .orientation(PlotOrientation.HORIZONTAL)
                .rangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT)
                .rangeAxis()
                .range(0, 100)
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .done()

                .barProps(0)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, new Color(0, 0, 128), 0.0F, 0.0F, Color.BLUE))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, new Color(0, 128, 0), 0.0F, 0.0F, Color.GREEN))
                .seriesPaint(2, new GradientPaint(0.0F, 0.0F, new Color(128, 0, 0), 0.0F, 0.0F, Color.RED))
                .drawBarOutline(false)
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL))
                .legendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"))
                .done();
        chart.show(500, 270);
    }
}
