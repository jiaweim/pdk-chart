package pdk.chart.examples;

import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.StandardCategorySeriesLabelGenerator;
import pdk.chart.renderer.category.StandardBarPainter;
import pdk.chart.title.TextTitle;

import java.awt.*;

/**
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 11 Jun 2026, 9:14 AM
 */
public class BarChartDemo1 {
    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        String[] categories = {"18 to 39", "40 - 59", "60 and over"};
        dataset.addSeries("Males", categories, new double[]{5.5, 8.4, 12.8});
        dataset.addSeries("Females", categories, new double[]{10.3, 20.1, 24.3});

        return dataset;
    }

    static void main() {
        CategoryXYChart chart = CategoryXYChart.create()
                .title("Antidepressant Medication Usage")
                .axisNames("Age Category", "Percent")
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .addTitle(new TextTitle("Percentage of adults aged 18 and over who used antidepressant medication over past 30 days, by age and sex: United States, 2015-2018"))
                .domainGridlinesVisible(true)
                .rangeCrosshairVisible(true)
                .rangeCrosshairPaint(Color.BLUE)
                .barProps(0)
                .drawBarOutline(false)
                .barPainter(new StandardBarPainter())
                .itemMargin(0.06)
                .legendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"))
                .done()
                .domainAxis()
                .categoryMargin(0.2)
                .done()
                .rangeAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .doneCategory();


        TextTitle source = new TextTitle("Source: https://www.cdc.gov/nchs/products/databriefs/db377.htm");
        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        chart.showLegend(true);
        chart.show(720, 480);
    }
}
