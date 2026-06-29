package pdk.chart.demo.fluent;

import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryChartType;
import pdk.chart.legend.LegendItem;
import pdk.chart.legend.LegendItemCollection;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.title.TextTitle;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * A stacked bar chart with a horizontal orientation.
 * <p>
 * A completely transparent color is used for the middle series, so that it leaves a gap in the chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 22 Jun 2026, 10:40 AM
 */
public class StackedBarChartDemo2 {

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(81.0, "Against all torture", "Italy");
        dataset.addValue(72.0, "Against all torture", "Great Britain");
        dataset.addValue(58.0, "Against all torture", "USA");
        dataset.addValue(48.0, "Against all torture", "Israel");
        dataset.addValue(43.0, "Against all torture", "Russia");
        dataset.addValue(23.0, "Against all torture", "India");
        dataset.addValue(59.0, "Against all torture", "Average (*)");
        dataset.addValue(5.0, "-", "Italy");
        dataset.addValue(4.0, "-", "Great Britain");
        dataset.addValue(6.0, "-", "USA");
        dataset.addValue(9.0, "-", "Israel");
        dataset.addValue(20.0, "-", "Russia");
        dataset.addValue(45.0, "-", "India");
        dataset.addValue(12.0, "-", "Average (*)");
        dataset.addValue(14.0, "Some degree permissible", "Italy");
        dataset.addValue(24.0, "Some degree permissible", "Great Britain");
        dataset.addValue(36.0, "Some degree permissible", "USA");
        dataset.addValue(43.0, "Some degree permissible", "Israel");
        dataset.addValue(37.0, "Some degree permissible", "Russia");
        dataset.addValue(32.0, "Some degree permissible", "India");
        dataset.addValue(29.0, "Some degree permissible", "Average (*)");
        return dataset;
    }

    static void main() {
        TextTitle tt = new TextTitle("Source: http://news.bbc.co.uk/1/hi/world/6063386.stm",
                new Font("Dialog", Font.PLAIN, 11));
        tt.setPosition(RectangleEdge.BOTTOM);
        tt.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        tt.setMargin(0.0, 0.0, 4.0, 4.0);

        TextTitle t = new TextTitle("(*) Across 27,000 respondents in 25 countries",
                new Font("Dialog", Font.PLAIN, 11));
        t.setPosition(RectangleEdge.BOTTOM);
        t.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        t.setMargin(4.0, 0.0, 2.0, 4.0);

        LegendItemCollection items = new LegendItemCollection();
        items.add(new LegendItem("Against all torture", null, null, null,
                new Rectangle2D.Double(-6.0, -3.0, 12.0, 6.0), Color.GREEN));
        items.add(new LegendItem("Some degree permissible", null, null, null,
                new Rectangle2D.Double(-6.0, -3.0, 12.0, 6.0), Color.RED));

        CategoryXYChart chart = CategoryXYChart.create()
                .orientation(PlotOrientation.HORIZONTAL)
                .addDataset(createDataset(), CategoryChartType.BAR_STACK)
                .showLegend(true)

                .title("Public Opinion : Torture of Prisoners")
                .addTitle(tt)
                .addTitle(t)
                .axisNames("Country", "%")
                .fixedLegendItems(items)
                .plotInsets(new RectangleInsets(5.0, 5.0, 5.0, 20.0))
                .domainGridlinesVisible(true)

                .barProps(0)
                .addTooltips(true)
                .drawBarOutline(false)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0)))
                .seriesPaint(1, new Color(0, 0, 0, 0))
                .seriesPaint(2, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0)))
                .done();

        chart.getLegend(0)
                .position(RectangleEdge.BOTTOM);

        chart.rangeAxisNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .upperMargin(0.0);
        chart.getTitle().setMargin(2.0, 0.0, 0.0, 0.0);

        chart.show(500, 270);
    }
}

