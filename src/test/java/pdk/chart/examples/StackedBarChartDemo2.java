package pdk.chart.examples;

import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
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
        dataset.addValue((double) 81.0F, "Against all torture", "Italy");
        dataset.addValue((double) 72.0F, "Against all torture", "Great Britain");
        dataset.addValue((double) 58.0F, "Against all torture", "USA");
        dataset.addValue((double) 48.0F, "Against all torture", "Israel");
        dataset.addValue((double) 43.0F, "Against all torture", "Russia");
        dataset.addValue((double) 23.0F, "Against all torture", "India");
        dataset.addValue((double) 59.0F, "Against all torture", "Average (*)");
        dataset.addValue((double) 5.0F, "-", "Italy");
        dataset.addValue((double) 4.0F, "-", "Great Britain");
        dataset.addValue((double) 6.0F, "-", "USA");
        dataset.addValue((double) 9.0F, "-", "Israel");
        dataset.addValue((double) 20.0F, "-", "Russia");
        dataset.addValue((double) 45.0F, "-", "India");
        dataset.addValue((double) 12.0F, "-", "Average (*)");
        dataset.addValue((double) 14.0F, "Some degree permissible", "Italy");
        dataset.addValue((double) 24.0F, "Some degree permissible", "Great Britain");
        dataset.addValue((double) 36.0F, "Some degree permissible", "USA");
        dataset.addValue((double) 43.0F, "Some degree permissible", "Israel");
        dataset.addValue((double) 37.0F, "Some degree permissible", "Russia");
        dataset.addValue((double) 32.0F, "Some degree permissible", "India");
        dataset.addValue((double) 29.0F, "Some degree permissible", "Average (*)");
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
                .addDataset(createDataset(), CategoryXYChartType.BAR_STACK)

                .showLegend(true)
                .legendProps()
                .position(RectangleEdge.BOTTOM)
                .doneCateogry()

                .title("Public Opinion : Torture of Prisoners")
                .addTitle(tt)
                .addTitle(t)
                .axisNames("Country", "%")
                .fixedLegendItems(items)
                .plotInsets(new RectangleInsets(5.0, 5.0, 5.0, 20.0))
                .domainGridlinesVisible(true)

                .rangeAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .upperMargin(0.0)
                .done()

                .barProps(0)
                .addTooltips(true)
                .drawBarOutline(false)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0)))
                .seriesPaint(1, new Color(0, 0, 0, 0))
                .seriesPaint(2, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, new Color(64, 0, 0)))
                .done();

        chart.getTitle().setMargin(2.0, 0.0, 0.0, 0.0);

        chart.show(500, 270);
    }
}

