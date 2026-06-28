package pdk.chart.demo.fluent;

import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.axis.CategoryLabelPosition;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.ExtendedCategoryAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.text.TextAnchor;
import pdk.chart.text.TextBlockAnchor;
import pdk.chart.title.TextTitle;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * A bar chart with item labels.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 9:58 AM
 */
public class SurveyResultsDemo3 {

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(2.61, "Results", "Sm.");
        dataset.addValue(2.7, "Results", "Med.");
        dataset.addValue(2.9, "Results", "Lg.");
        dataset.addValue(2.74, "Results", "All");
        return dataset;
    }

    static void main() {
        TextTitle title = new TextTitle("Figure 6 | Overall SEO Rating");
        title.setHorizontalAlignment(HorizontalAlignment.LEFT);
        title.setBackgroundPaint(Color.RED);
        title.setPaint(Color.WHITE);

        ExtendedCategoryAxis domainAxis = new ExtendedCategoryAxis(null);
        domainAxis.setTickLabelFont(new Font("SansSerif", Font.BOLD, 12));
        domainAxis.setCategoryMargin(0.3);
        domainAxis.addSubLabel("Sm.", "(10)");
        domainAxis.addSubLabel("Med.", "(10)");
        domainAxis.addSubLabel("Lg.", "(10)");
        domainAxis.addSubLabel("All", "(10)");

        CategoryLabelPositions p = domainAxis.getCategoryLabelPositions();
        CategoryLabelPosition left = new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.replaceLeftPosition(p, left));

        CategoryXYChart chart = CategoryXYChart.create()
                .orientation(PlotOrientation.HORIZONTAL)
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .title(title)
                .backgroundPaint(Color.WHITE)
                .plotOutlinePaint(null)

                .barProps(0)
                .addTooltips(true)
                .seriesPaint(0, new Color(156, 164, 74))
                .drawBarOutline(false)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>("{2}", new DecimalFormat("0.00")))
                .defaultItemLabelsVisible(true)
                .defaultItemLabelFont(new Font("SansSerif", Font.PLAIN, 18))
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE3, TextAnchor.CENTER_RIGHT))
                .positiveItemLabelPositionFallback(new ItemLabelPosition())
                .done()

                .setDomainAxis(domainAxis);

        chart.rangeAxisNumber()
                .range(0, 4)
                .visible(false);

        chart.show(300, 270);
    }
}
