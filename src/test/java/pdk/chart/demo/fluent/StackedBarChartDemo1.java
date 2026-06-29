package pdk.chart.demo.fluent;

import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryChartType;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.renderer.category.StandardBarPainter;
import pdk.chart.title.TextTitle;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

/**
 * A regular stacked bar chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 22 Jun 2026, 10:39 AM
 */
public class StackedBarChartDemo1 {

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(197.0F, "Agricultural", "Brazil");
        dataset.addValue(64.0F, "Domestic", "Brazil");
        dataset.addValue(57.0F, "Industrial", "Brazil");
        dataset.addValue(339.0F, "Agricultural", "Indonesia");
        dataset.addValue(30.0F, "Domestic", "Indonesia");
        dataset.addValue(4.0F, "Industrial", "Indonesia");
        dataset.addValue(279.0F, "Agricultural", "China");
        dataset.addValue(27.0F, "Domestic", "China");
        dataset.addValue(107.0F, "Industrial", "China");
        dataset.addValue(92.0F, "Agricultural", "Germany");
        dataset.addValue(55.0F, "Domestic", "Germany");
        dataset.addValue(313.0F, "Industrial", "Germany");
        dataset.addValue(96.0F, "Agricultural", "Russia");
        dataset.addValue(102.0F, "Domestic", "Russia");
        dataset.addValue(337.0F, "Industrial", "Russia");
        dataset.addValue(403.0F, "Agricultural", "Turkey");
        dataset.addValue(82.0F, "Domestic", "Turkey");
        dataset.addValue(60.0F, "Industrial", "Turkey");
        dataset.addValue(536.0F, "Agricultural", "Bangladesh");
        dataset.addValue(17.0F, "Domestic", "Bangladesh");
        dataset.addValue(6.0F, "Industrial", "Bangladesh");
        dataset.addValue(508.0F, "Agricultural", "India");
        dataset.addValue(47.0F, "Domestic", "India");
        dataset.addValue(30.0F, "Industrial", "India");
        dataset.addValue(428.0F, "Agricultural", "Japan");
        dataset.addValue(138.0F, "Domestic", "Japan");
        dataset.addValue(124.0F, "Industrial", "Japan");
        dataset.addValue(325.0F, "Agricultural", "Italy");
        dataset.addValue(130.0F, "Domestic", "Italy");
        dataset.addValue(268.0F, "Industrial", "Italy");
        dataset.addValue(569.0F, "Agricultural", "Mexico");
        dataset.addValue(126.0F, "Domestic", "Mexico");
        dataset.addValue(37.0F, "Industrial", "Mexico");
        dataset.addValue(576.0F, "Agricultural", "Vietnam");
        dataset.addValue(68.0F, "Domestic", "Vietnam");
        dataset.addValue(203.0F, "Industrial", "Vietnam");
        dataset.addValue(794.0F, "Agricultural", "Egypt");
        dataset.addValue(74.0F, "Domestic", "Egypt");
        dataset.addValue(55.0F, "Industrial", "Egypt");
        dataset.addValue(954.0F, "Agricultural", "Iran");
        dataset.addValue(21.0F, "Domestic", "Iran");
        dataset.addValue(73.0F, "Industrial", "Iran");
        dataset.addValue(1029.0F, "Agricultural", "Pakistan");
        dataset.addValue(21.0F, "Domestic", "Pakistan");
        dataset.addValue(21.0F, "Industrial", "Pakistan");
        dataset.addValue(1236.0F, "Agricultural", "Thailand");
        dataset.addValue(26.0F, "Domestic", "Thailand");
        dataset.addValue(26.0F, "Industrial", "Thailand");
        dataset.addValue(165.0F, "Agricultural", "Canada");
        dataset.addValue(274.0F, "Domestic", "Canada");
        dataset.addValue(947.0F, "Industrial", "Canada");
        dataset.addValue(1363.0F, "Agricultural", "Iraq");
        dataset.addValue(44.0F, "Domestic", "Iraq");
        dataset.addValue(74.0F, "Industrial", "Iraq");
        dataset.addValue(656.0F, "Agricultural", "US");
        dataset.addValue(208.0F, "Domestic", "US");
        dataset.addValue(736.0F, "Industrial", "US");
        dataset.addValue(2040.0F, "Agricultural", "Uzbekistan");
        dataset.addValue(110.0F, "Domestic", "Uzbekistan");
        dataset.addValue(44.0F, "Industrial", "Uzbekistan");
        return dataset;
    }

    static void main() {
        AttributedString yLabel = new AttributedString("m3/person/year");
        yLabel.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_ULTRABOLD);
        yLabel.addAttribute(TextAttribute.SIZE, 14);
        yLabel.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 1, 2);

        CategoryXYChart chart = CategoryXYChart.create()
                .addDataset(createDataset(), CategoryChartType.BAR_STACK)
                .title("Freshwater Usage By Country")
                .axisNames("Country", "Value")
                .addTitle(new TextTitle("Source: http://en.wikipedia.org/wiki/Peak_water#Water_supply"))
                .showLegend(true)

                .domainAxis()
                .lowerMargin(0.01)
                .upperMargin(0.01)
                .categoryLabelPositions(CategoryLabelPositions.UP_90)
                .done()

                .barProps(0)
                .drawBarOutline(false)
                .barPainter(new StandardBarPainter())
                .defaultItemLabelsVisible(true)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .defaultItemLabelPaint(Color.WHITE)
                .seriesPaint(0, new Color(0, 55, 122))
                .seriesPaint(1, new Color(24, 123, 58))
                .seriesPaint(2, Color.RED)
                .done();

        chart.rangeAxisNumber()
                .attributedLabel(yLabel);
        chart.show(500, 270);
    }
}
