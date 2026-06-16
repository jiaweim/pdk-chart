package pdk.chart.examples;

import pdk.chart.annotations.CategoryTextAnnotation;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.axis.CategoryAnchor;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import java.awt.*;

/**
 * A bar chart with special formatting to display survey results.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 9:42 AM
 */
public class SurveyResultsDemo1 {

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(2.01, "Results", "Category 1");
        dataset.addValue(2.02, "Results", "Category 2");
        dataset.addValue((double) 2.0F, "Results", "Category 3");
        dataset.addValue(1.97, "Results", "Category 4");
        dataset.addValue(1.44, "Results", "Category 5");
        dataset.addValue(1.49, "Results", "Category 6");
        dataset.addValue(1.49, "Results", "Category 7");
        dataset.addValue(1.48, "Results", "Category 8");
        dataset.addValue(4.26, "Results", "Category 9");
        dataset.addValue(4.08, "Results", "Category 10");
        dataset.addValue(4.03, "Results", "Category 11");
        dataset.addValue(3.92, "Results", "Category 12");
        dataset.addValue(3.99, "Results", "Category 13");
        dataset.addValue(2.23, "Results", "Category 14");
        dataset.addValue(2.6, "Results", "Overall");
        return dataset;
    }

    static void main() {
        TextTitle title = new TextTitle("Figure 7 | I. Resources - The site offers users relevant, informative and educational resources");
        title.setHorizontalAlignment(HorizontalAlignment.LEFT);
        title.setBackgroundPaint(Color.RED);
        title.setPaint(Color.WHITE);

        CategoryTextAnnotation a1 = new CategoryTextAnnotation("1. White papers are available.", "Category 1", 0.0);
        a1.setFont(new Font("SansSerif", Font.BOLD, 12));
        a1.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a1.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a2 = new CategoryTextAnnotation("2. White papers enhance users understanding of the firm and its expertise.", "Category 2", 0.0);
        a2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a2.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a2.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a3 = new CategoryTextAnnotation("3. White papers are relevant to the firm's prospects and clients.", "Category 3", 0.0);
        a3.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a3.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a3.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a4 = new CategoryTextAnnotation("4. White papers are relevant to the firm's positioning.", "Category 4", 0.0);
        a4.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a4.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a4.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a5 = new CategoryTextAnnotation("5. Case studies are available.", "Category 5", 0.0);
        a5.setFont(new Font("SansSerif", Font.BOLD, 12));
        a5.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a5.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a6 = new CategoryTextAnnotation("6. Case studies enhance users understanding of the firm and its expertise.", "Category 6", 0.0);
        a6.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a6.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a6.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a7 = new CategoryTextAnnotation("7. Case studies are relevant to the firm's prospects and clients.", "Category 7", 0.0);
        a7.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a7.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a7.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a8 = new CategoryTextAnnotation("8. White papers are relevant to the firm's positioning.", "Category 8", 0.0);
        a8.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a8.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a8.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a9 = new CategoryTextAnnotation("9. Case studies are available.", "Category 9", 0.0);
        a9.setFont(new Font("SansSerif", Font.BOLD, 12));
        a9.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a9.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a10 = new CategoryTextAnnotation("10. Case studies enhance users understanding of the firm and its expertise.", "Category 10", 0.0);
        a10.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a10.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a10.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a11 = new CategoryTextAnnotation("11. Case studies are relevant to the firm's prospects and clients.", "Category 11", 0.0);
        a11.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a11.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a11.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a12 = new CategoryTextAnnotation("12. White papers are relevant to the firm's positioning.", "Category 12", 0.0);
        a12.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a12.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a12.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a13 = new CategoryTextAnnotation("13. Users can easily access resources based on viewer interest.", "Category 13", 0.0);
        a13.setFont(new Font("SansSerif", Font.BOLD, 12));
        a13.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a13.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a14 = new CategoryTextAnnotation("14. Access to additional hyperlinks enhances users's ability to find relevant information.", "Category 14", 0.0);
        a14.setFont(new Font("SansSerif", Font.BOLD, 12));
        a14.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a14.setCategoryAnchor(CategoryAnchor.START);

        CategoryTextAnnotation a15 = new CategoryTextAnnotation("15. OVERALL EFFECTIVENESS.", "Overall", 0.0);
        a15.setFont(new Font("SansSerif", Font.BOLD, 12));
        a15.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a15.setCategoryAnchor(CategoryAnchor.START);

        CategoryXYChart.create()
                .orientation(PlotOrientation.HORIZONTAL)
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .backgroundPaint(Color.WHITE)
                .title(title)
                .plotOutlinePaint(null)
                .domainGridlinesVisible(true)
                .domainGridlinePosition(CategoryAnchor.END)
                .domainGridlineStroke(new BasicStroke(0.5f))
                .domainGridlinePaint(Color.BLACK)
                .rangeGridlinesVisible(false)
                .clearRangeMarkers()

                .barRenderer(0)
                .addTooltips(true)
                .seriesPaint(0, new Color(156, 164, 74))
                .drawBarOutline(false)
                .defaultItemLabelsVisible(true)
                .defaultItemLabelFont(new Font("SansSerif", Font.BOLD, 10))
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE3, TextAnchor.CENTER_RIGHT))
                .done()

                .domainAxis()
                .visible(false)
                .categoryMargin(0.5)
                .done()

                .rangeAxis()
                .visible(false)
                .doneCategory()

                .addAnnotation(a1)
                .addAnnotation(a2)
                .addAnnotation(a3)
                .addAnnotation(a4)
                .addAnnotation(a5)
                .addAnnotation(a6)
                .addAnnotation(a7)
                .addAnnotation(a8)
                .addAnnotation(a9)
                .addAnnotation(a10)
                .addAnnotation(a11)
                .addAnnotation(a12)
                .addAnnotation(a13)
                .addAnnotation(a14)
                .addAnnotation(a15)

                .show(700, 600);
    }
}
