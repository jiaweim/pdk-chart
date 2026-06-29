package pdk.chart.demo.fluent;

import pdk.chart.api.RectangleEdge;
import pdk.chart.api.RectangleInsets;
import pdk.chart.api.UnitType;
import pdk.chart.api.VerticalAlignment;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryChartType;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.AreaRendererEndType;
import pdk.chart.title.TextTitle;

import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 10:30 AM
 */
public class AreaChartDemo1 {
    static void main() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        String[] rowKeys = new String[]{"Series 1", "Series 2", "Series 3"};
        String[] columnKeys = new String[]{"Type 1", "Type 2", "Type 3", "Type 4", "Type 5", "Type 6", "Type 7", "Type 8"};
        dataset.addSeries(rowKeys[0], columnKeys,
                new double[]{1.0, 4.0, 3.0, 5.0, 5.0, 7.0, 7.0, 8.0});
        dataset.addSeries(rowKeys[1], columnKeys,
                new double[]{5.0, 7.0, 6.0, 8.0, 4.0, 4.0, 2.0, 1.0});
        dataset.addSeries(rowKeys[2], columnKeys,
                new double[]{4.0, 3.0, 2.0, 3.0, 6.0, 3.0, 4.0, 3.0});

        TextTitle title = new TextTitle("An area chart demonstration.  We use this subtitle as an "
                + "example of what happens when you get a really long title or subtitle.");
        title.setFont(new Font("SansSerif", Font.PLAIN, 12));
        title.setPosition(RectangleEdge.TOP);
        title.setPadding(new RectangleInsets(UnitType.RELATIVE, 0.05, 0.05, 0.05, 0.05));
        title.setVerticalAlignment(VerticalAlignment.BOTTOM);

        CategoryXYChart chart = CategoryXYChart.create()
                .dataset(dataset, CategoryChartType.AREA)
                .title("Area Chart")
                .axisNames("Category", "Value")
                .orientation(PlotOrientation.VERTICAL)
                .showLegend(true)
                .backgroundPaint(Color.WHITE)
                .foregroundAlpha(0.5f)
                .addTitle(title)
                .domainAxis().categoryMargin(0.0).chart();

        chart.getCategoryPlot()
                .getAreaRenderer(0)
                .showTooltips(true)
                .endType(AreaRendererEndType.LEVEL);

        chart.show();
    }
}
