package pdk.chart.demo.fluent;

import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.fluent.CategoryXYChartType;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.title.TextTitle;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * A simple bar chart with a horizontal orientation.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 12 Jun 2026, 10:28 AM
 */
public class BarChartDemo11 {

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addSeries("S1",
                new String[]{
                        "GNU General Public License (GPL) 2.0",
                        "Apache License 2.0",
                        "GNU General Public License (GPL) 3.0",
                        "MIT License",
                        "BSD License 2.0",
                        "Artistic Licence (Perl)",
                        "GNU Lesser General Public License (LGPL) 2.1",
                        "GNU Lesser General Public License (LGPL) 3.0",
                        "Eclipse Public License",
                        "Code Project 1.02 License"
                },
                new double[]{33.0, 13.0, 12.0, 11.0, 7.0, 6.0, 6.0, 3.0, 2.0, 1.0});
        return dataset;
    }

    static void main() {
        TextTitle source = new TextTitle("Source: http://www.blackducksoftware.com/resources/data/top-20-licenses (as at 30 Aug 2013)",
                new Font("Dialog", Font.PLAIN, 9));
        source.setPosition(RectangleEdge.BOTTOM);

        CategoryXYChart.create()
                .title("Open Source Projects By License")
                .addTitle(source)
                .axisNames("License", "Percent")
                .orientation(PlotOrientation.HORIZONTAL)
                .domainGridlinesVisible(true)
                .dataset(createDataset(), CategoryXYChartType.BAR)
                .domainAxis()
                .maximumCategoryLabelWidthRatio(0.8f)
                .done()
                .rangeAxis()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .done()

                .barProps(0)
                .drawBarOutline(false)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>("{1}: {2} percent", new DecimalFormat("0")))
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(0, 0, 64)))
                .done()
                .show(500, 270);
    }
}
