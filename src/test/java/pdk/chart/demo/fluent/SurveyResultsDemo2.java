package pdk.chart.demo.fluent;

import pdk.chart.api.HorizontalAlignment;
import pdk.chart.axis.ExtendedCategoryAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import java.awt.*;

/**
 * A bar chart with sublabels for the categories.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 9:56 AM
 */
public class SurveyResultsDemo2 {

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(1.32, "Results", "Sm.");
        dataset.addValue(0.4, "Results", "Med.");
        dataset.addValue(2.62, "Results", "Lg.");
        dataset.addValue(1.44, "Results", "All");
        return dataset;
    }

    static void main() {
        TextTitle title = new TextTitle("Figure 8.5 - Case studies are available");
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

        CategoryXYChart.create()
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .title(title)
                .backgroundPaint(Color.WHITE)

                .plotOutlinePaint(null)

                .barProps(0)
                .addTooltips(true)
                .seriesPaint(0, new Color(156, 164, 74))
                .drawBarOutline(false)
                .defaultItemLabelsVisible(true)
                .defaultItemLabelFont(new Font("SansSerif", Font.PLAIN, 18))
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.TOP_CENTER))
                .positiveItemLabelPositionFallback(new ItemLabelPosition())
                .done()

                .rangeAxis()
                .range(0, 5)
                .visible(false)
                .done()

                .setDomainAxis(domainAxis)

                .show(300, 270);
    }
}
