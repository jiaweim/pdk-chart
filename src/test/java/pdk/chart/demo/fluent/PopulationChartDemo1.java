package pdk.chart.demo.fluent;

import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DefaultKeyedValues2DDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryChartType;
import pdk.chart.plot.PlotOrientation;

/**
 * A population pyramid chart. This is constructed using a stacked bar chart...
 * but it would be better to create a dedicated plot type. That's on the TODO list (since forever).
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 18 Jun 2026, 4:36 PM
 */
public class PopulationChartDemo1 {

    public static CategoryDataset createDataset() {
        DefaultKeyedValues2DDataset data = new DefaultKeyedValues2DDataset();
        data.addValue(-6.0, "Male", "70+");
        data.addValue(-8.0, "Male", "60-69");
        data.addValue(-11.0, "Male", "50-59");
        data.addValue(-13.0, "Male", "40-49");
        data.addValue(-14.0, "Male", "30-39");
        data.addValue(-15.0, "Male", "20-29");
        data.addValue(-19.0, "Male", "10-19");
        data.addValue(-21.0, "Male", "0-9");
        data.addValue(10.0, "Female", "70+");
        data.addValue(12.0, "Female", "60-69");
        data.addValue(13.0, "Female", "50-59");
        data.addValue(14.0, "Female", "40-49");
        data.addValue(15.0, "Female", "30-39");
        data.addValue(17.0, "Female", "20-29");
        data.addValue(19.0, "Female", "10-19");
        data.addValue(20.0, "Female", "0-9");
        return data;
    }

    static void main() {
        CategoryXYChart.create()
                .orientation(PlotOrientation.HORIZONTAL)
                .addDataset(createDataset(), CategoryChartType.BAR_STACK)
                .title("Population Chart Demo 1")
                .axisNames("Age Group", "Population (millions)")
                .showLegend(true)
                .barProps(0)
                .addTooltips(true)
                .done()
                .show(500, 270);
    }
}
