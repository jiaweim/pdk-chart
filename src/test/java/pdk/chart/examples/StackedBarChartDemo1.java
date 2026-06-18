package pdk.chart.examples;

import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.renderer.category.StandardBarPainter;
import pdk.chart.title.TextTitle;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class StackedBarChartDemo1 {

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 197.0F, "Agricultural", "Brazil");
        dataset.addValue((double) 64.0F, "Domestic", "Brazil");
        dataset.addValue((double) 57.0F, "Industrial", "Brazil");
        dataset.addValue((double) 339.0F, "Agricultural", "Indonesia");
        dataset.addValue((double) 30.0F, "Domestic", "Indonesia");
        dataset.addValue((double) 4.0F, "Industrial", "Indonesia");
        dataset.addValue((double) 279.0F, "Agricultural", "China");
        dataset.addValue((double) 27.0F, "Domestic", "China");
        dataset.addValue((double) 107.0F, "Industrial", "China");
        dataset.addValue((double) 92.0F, "Agricultural", "Germany");
        dataset.addValue((double) 55.0F, "Domestic", "Germany");
        dataset.addValue((double) 313.0F, "Industrial", "Germany");
        dataset.addValue((double) 96.0F, "Agricultural", "Russia");
        dataset.addValue((double) 102.0F, "Domestic", "Russia");
        dataset.addValue((double) 337.0F, "Industrial", "Russia");
        dataset.addValue((double) 403.0F, "Agricultural", "Turkey");
        dataset.addValue((double) 82.0F, "Domestic", "Turkey");
        dataset.addValue((double) 60.0F, "Industrial", "Turkey");
        dataset.addValue((double) 536.0F, "Agricultural", "Bangladesh");
        dataset.addValue((double) 17.0F, "Domestic", "Bangladesh");
        dataset.addValue((double) 6.0F, "Industrial", "Bangladesh");
        dataset.addValue((double) 508.0F, "Agricultural", "India");
        dataset.addValue((double) 47.0F, "Domestic", "India");
        dataset.addValue((double) 30.0F, "Industrial", "India");
        dataset.addValue((double) 428.0F, "Agricultural", "Japan");
        dataset.addValue((double) 138.0F, "Domestic", "Japan");
        dataset.addValue((double) 124.0F, "Industrial", "Japan");
        dataset.addValue((double) 325.0F, "Agricultural", "Italy");
        dataset.addValue((double) 130.0F, "Domestic", "Italy");
        dataset.addValue((double) 268.0F, "Industrial", "Italy");
        dataset.addValue((double) 569.0F, "Agricultural", "Mexico");
        dataset.addValue((double) 126.0F, "Domestic", "Mexico");
        dataset.addValue((double) 37.0F, "Industrial", "Mexico");
        dataset.addValue((double) 576.0F, "Agricultural", "Vietnam");
        dataset.addValue((double) 68.0F, "Domestic", "Vietnam");
        dataset.addValue((double) 203.0F, "Industrial", "Vietnam");
        dataset.addValue((double) 794.0F, "Agricultural", "Egypt");
        dataset.addValue((double) 74.0F, "Domestic", "Egypt");
        dataset.addValue((double) 55.0F, "Industrial", "Egypt");
        dataset.addValue((double) 954.0F, "Agricultural", "Iran");
        dataset.addValue((double) 21.0F, "Domestic", "Iran");
        dataset.addValue((double) 73.0F, "Industrial", "Iran");
        dataset.addValue((double) 1029.0F, "Agricultural", "Pakistan");
        dataset.addValue((double) 21.0F, "Domestic", "Pakistan");
        dataset.addValue((double) 21.0F, "Industrial", "Pakistan");
        dataset.addValue((double) 1236.0F, "Agricultural", "Thailand");
        dataset.addValue((double) 26.0F, "Domestic", "Thailand");
        dataset.addValue((double) 26.0F, "Industrial", "Thailand");
        dataset.addValue((double) 165.0F, "Agricultural", "Canada");
        dataset.addValue((double) 274.0F, "Domestic", "Canada");
        dataset.addValue((double) 947.0F, "Industrial", "Canada");
        dataset.addValue((double) 1363.0F, "Agricultural", "Iraq");
        dataset.addValue((double) 44.0F, "Domestic", "Iraq");
        dataset.addValue((double) 74.0F, "Industrial", "Iraq");
        dataset.addValue((double) 656.0F, "Agricultural", "US");
        dataset.addValue((double) 208.0F, "Domestic", "US");
        dataset.addValue((double) 736.0F, "Industrial", "US");
        dataset.addValue((double) 2040.0F, "Agricultural", "Uzbekistan");
        dataset.addValue((double) 110.0F, "Domestic", "Uzbekistan");
        dataset.addValue((double) 44.0F, "Industrial", "Uzbekistan");
        return dataset;
    }

    static void main() {
        AttributedString yLabel = new AttributedString("m3/person/year");
        yLabel.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_ULTRABOLD);
        yLabel.addAttribute(TextAttribute.SIZE, 14);
        yLabel.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 1, 2);

        CategoryXYChart.create()
                .addDataset(createDataset(), CategoryXYChartType.BAR_STACK)
                .title("Freshwater Usage By Country")
                .axisNames("Country", "Value")
                .addTitle(new TextTitle("Source: http://en.wikipedia.org/wiki/Peak_water#Water_supply"))

                .domainAxis()
                .lowerMargin(0.01)
                .upperMargin(0.01)
                .categoryLabelPositions(CategoryLabelPositions.UP_90)
                .done()

                .rangeAxis()
                .attributedLabel(yLabel)
                .doneCategory()

                .barProps(0)
                .drawBarOutline(false)
                .barPainter(new StandardBarPainter())
                .defaultItemLabelsVisible(true)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .defaultItemLabelPaint(Color.WHITE)
                .seriesPaint(0, new Color(0, 55, 122))
                .seriesPaint(1, new Color(24, 123, 58))
                .seriesPaint(2, Color.RED)
                .done()

                .show(500, 270);
    }
}
