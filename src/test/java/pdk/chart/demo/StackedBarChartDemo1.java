package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.Data;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.StandardBarPainter;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.swing.*;
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
public class StackedBarChartDemo1 extends ApplicationFrame {
    public StackedBarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        String[] categories = new String[]{
                "Brazil", "Indonesia", "China", "Germany", "Russia",
                "Turkey", "Bangladesh", "India", "Japan", "Italy",
                "Mexico", "Vietnam", "Egypt", "Iran", "Pakistan",
                "Thailand", "Canada", "Iraq", "US", "Uzbekistan"
        };
        return Data.<String, String>category()
                .addSeries("Agricultural", categories, new double[]{
                        197.0, 339.0, 279.0, 92.0, 96.0,
                        403.0, 536.0, 508.0, 428.0, 325.0,
                        569.0, 576.0, 794.0, 954.0, 1029.0,
                        1236.0, 165.0, 1363.0, 656.0, 2040.0
                })
                .addSeries("Domestic", categories, new double[]{
                        64.0, 30.0, 27.0, 55.0, 102.0,
                        82.0, 17.0, 47.0, 138.0, 130.0,
                        126.0, 68.0, 74.0, 21.0, 21.0,
                        26.0, 274.0, 44.0, 208.0, 110.0
                })
                .addSeries("Industrial", categories, new double[]{
                        57.0, 4.0, 107.0, 313.0, 337.0,
                        60.0, 6.0, 30.0, 124.0, 268.0,
                        37.0, 203.0, 55.0, 73.0, 21.0,
                        26.0, 947.0, 74.0, 736.0, 44.0
                })
                .build();
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.barStacked("Freshwater Usage By Country", "Country", "Value", dataset);
        chart.addSubtitle(new TextTitle("Source: http://en.wikipedia.org/wiki/Peak_water#Water_supply"));
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getDomainAxis()
                .lowerMargin(0.01)
                .upperMargin(0.01)
                .categoryLabelPositions(CategoryLabelPositions.UP_90);

        AttributedString yLabel = new AttributedString("m3/person/year");
        yLabel.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_ULTRABOLD);
        yLabel.addAttribute(TextAttribute.SIZE, 14);
        yLabel.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 1, 2);
        plot.getRangeAxisAsNumber().attributedLabel(yLabel);

        plot.getBarRenderer(0)
                .drawBarOutline(false)
                .barPainter(new StandardBarPainter())
                .defaultItemLabelsVisible(true)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .defaultItemLabelPaint(Color.WHITE)
                .seriesPaint(0, new Color(0, 55, 122))
                .seriesPaint(1, new Color(24, 123, 58))
                .seriesPaint(2, Color.RED);

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        StackedBarChartDemo1 demo = new StackedBarChartDemo1("StackedBarChartDemo1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
