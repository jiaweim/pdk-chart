package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.axis.CategoryLabelPosition;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.ExtendedCategoryAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.Data;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.text.TextBlockAnchor;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class SurveyResultsDemo3 extends ApplicationFrame {

    public SurveyResultsDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(300, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        return Data.createCategory("Results",
                new String[]{"Sm.", "Med.", "Lg.", "All"},
                new double[]{2.61, 2.7, 2.9, 2.74});
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar(null, null, null,
                dataset, PlotOrientation.HORIZONTAL, false, true, false);
        chart.setBackgroundPaint(Color.WHITE);

        TextTitle title = new TextTitle("Figure 6 | Overall SEO Rating");
        title.setHorizontalAlignment(HorizontalAlignment.LEFT);
        title.setBackgroundPaint(Color.RED);
        title.setPaint(Color.WHITE);
        chart.setTitle(title);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.outlinePaint(null);

        plot.getRangeAxisAsNumber()
                .range(0, 4)
                .visible(false);

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
        plot.setDomainAxis(domainAxis);

        plot.getBarRenderer(0)
                .seriesPaint(0, new Color(156, 164, 74))
                .drawBarOutline(false)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>("{2}", new DecimalFormat("0.00")))
                .defaultItemLabelsVisible(true)
                .defaultItemLabelFont(new Font("SansSerif", Font.PLAIN, 18))
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE3, TextAnchor.CENTER_RIGHT))
                .positiveItemLabelPositionFallback(new ItemLabelPosition());
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        SurveyResultsDemo3 demo = new SurveyResultsDemo3("Survey Results Demo 3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
