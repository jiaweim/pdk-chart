package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.axis.ExtendedCategoryAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;

public class SurveyResultsDemo2 extends ApplicationFrame {

    public SurveyResultsDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(300, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(1.32, "Results", "Sm.");
        dataset.addValue(0.4, "Results", "Med.");
        dataset.addValue(2.62, "Results", "Lg.");
        dataset.addValue(1.44, "Results", "All");
        return dataset;
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.bar(null, null, null,
                dataset, PlotOrientation.VERTICAL, false, true, false);
        chart.backgroundPaint(Color.WHITE);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.outlinePaint(null);

        plot.getRangeAxisAsNumber()
                .range(0, 5)
                .visible(false);

        TextTitle title = new TextTitle("Figure 8.5 - Case studies are available");
        title.setHorizontalAlignment(HorizontalAlignment.LEFT);
        title.setBackgroundPaint(Color.RED);
        title.setPaint(Color.WHITE);
        chart.setTitle(title);

        ExtendedCategoryAxis domainAxis = new ExtendedCategoryAxis(null);
        domainAxis.setTickLabelFont(new Font("SansSerif", 1, 12));
        domainAxis.setCategoryMargin(0.3);
        domainAxis.addSubLabel("Sm.", "(10)");
        domainAxis.addSubLabel("Med.", "(10)");
        domainAxis.addSubLabel("Lg.", "(10)");
        domainAxis.addSubLabel("All", "(10)");
        plot.setDomainAxis(domainAxis);

        plot.getBarRenderer(0)
                .seriesPaint(0, new Color(156, 164, 74))
                .drawBarOutline(false)
                .defaultItemLabelsVisible(true)
                .defaultItemLabelFont(new Font("SansSerif", Font.PLAIN, 18))
                .defaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.TOP_CENTER))
                .positiveItemLabelPositionFallback(new ItemLabelPosition());

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        SurveyResultsDemo2 demo = new SurveyResultsDemo2("Survey Results Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
