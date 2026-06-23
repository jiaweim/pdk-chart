package pdk.chart.demo.fluent;

import pdk.chart.Chart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.title.TextTitle;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Another horizontal bar chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 11 Jun 2026, 7:24 PM
 */
public class BarChartDemo5 {

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("Prison Population Rates",
                new String[]{
                        "Norway", "Switzerland", "France", "Syria", "Germany",
                        "China", "Australia", "Egypt", "England & Wales", "New Zealand",
                        "Chile", "Iran", "Singapore", "South Africa", "Ukraine",
                        "USA"
                },
                new double[]{
                        59.0, 69.0, 85.0, 93.0, 96.0,
                        111.0, 116.0, 121.0, 129.0, 157.0,
                        205.0, 229.0, 359.0, 404.0, 406.0,
                        686.0});
        return dataset;
    }

    static void main() {
        CategoryXYChart chart = CategoryXYChart.create();
        chart.orientation(PlotOrientation.HORIZONTAL)
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .title("Prison Population Rates - Selected Countries")
                .axisNames("Country", "Prisoners Per 100,000 National Population")
                .addTitle(new TextTitle("Source: http://www.homeoffice.gov.uk/rds/pdfs2/r188.pdf", new Font("Dialog", Font.ITALIC, 10)))
                .rangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT)
                .rangePannable(true)
                .barProps(0)
                .addTooltips(true)
                .defaultItemLabelsVisible(true)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .defaultToolTipGenerator(new StandardCategoryToolTipGenerator<>("{0}, {1}) = {2} per 100,000", new DecimalFormat("0")))
                .itemLabelInsets(new RectangleInsets(0.0, 9.0, 0.0, 9.0))
                .done()
                .domainAxis()
                .categoryMargin(0.25)
                .lowerMargin(0.02)
                .upperMargin(0.02)
                .done()
                .rangeAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .upperMargin(0.1);
        Chart.DEFAULT_THEME.apply(chart);
        chart.show(500, 270);
    }
}
