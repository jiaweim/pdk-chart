package pdk.chart.demo.fluent;

import pdk.chart.data.category.DefaultIntervalCategoryDataset;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;

import java.text.DecimalFormat;

/**
 * A bar chart that displays intervals.
 * <p>
 * This uses the DefaultIntervalCategoryDataset and IntervalBarRenderer classes.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 15 Jun 2026, 2:27 PM
 */
public class IntervalBarChartDemo1 {

    private static IntervalCategoryDataset createDataset() {
        double[] starts_S1 = new double[]{0.1, 0.2, 0.3};
        double[] starts_S2 = new double[]{0.3, 0.4, 0.5};
        double[] ends_S1 = new double[]{0.5, 0.6, 0.7};
        double[] ends_S2 = new double[]{0.7, 0.8, 0.9};
        double[][] starts = new double[][]{starts_S1, starts_S2};
        double[][] ends = new double[][]{ends_S1, ends_S2};
        DefaultIntervalCategoryDataset dataset = new DefaultIntervalCategoryDataset(starts, ends);
        return dataset;
    }

    static void main() {
        CategoryXYChart chart = CategoryXYChart.create()
                .title("IntervalBarChartDemo1")
                .dataset(createDataset(), CategoryXYChartType.BAR_INTERVAL)
                .axisNames("Category", "Percentage")
                .showLegend(true)
                .domainGridlinesVisible(true)
                .rangePannable(true)
                .rangeAxis()
                .numberFormatOverride(new DecimalFormat("0.00%"))
                .done();
        chart.show(500, 270);
    }
}
