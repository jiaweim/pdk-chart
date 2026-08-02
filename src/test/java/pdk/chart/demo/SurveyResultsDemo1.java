package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.Chart;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.axis.CategoryAnchor;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.model.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;

/**
 * A bar chart with special formatting to display survey results.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 9:42 AM
 */
public class SurveyResultsDemo1 extends ApplicationFrame {
    public SurveyResultsDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(700, 600));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        return Data.createCategory("Results",
                new String[]{
                        "Category 1", "Category 2", "Category 3", "Category 4", "Category 5",
                        "Category 6", "Category 7", "Category 8", "Category 9", "Category 10",
                        "Category 11", "Category 12", "Category 13", "Category 14", "Overall"},
                new double[]{
                        2.01, 2.02, 2.0, 1.97, 1.44,
                        1.49, 1.49, 1.48, 4.26, 4.08,
                        4.03, 3.92, 3.99, 2.23, 2.6});
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        CategoryBarChart chart = new CategoryBarChart(dataset, null, null, null,
                PlotOrientation.HORIZONTAL, false, true);
        chart.setBackgroundPaint(Color.WHITE);

        TextTitle title = new TextTitle("Figure 7 | I. Resources - The site offers users relevant, informative and educational resources");
        title.setHorizontalAlignment(HorizontalAlignment.LEFT);
        title.setBackgroundPaint(Color.RED);
        title.setPaint(Color.WHITE);
        chart.setTitle(title);
        chart.setPlotOutlinePaint(null);
        chart.setDomainGridlinesVisible(true);
        chart.setDomainGridlinePosition(CategoryAnchor.END);
        chart.setDomainGridlineStroke(new BasicStroke(0.5f));
        chart.setDomainGridlinePaint(Color.BLACK);
        chart.setRangeGridlinesVisible(false);

        chart.clearRangeMarkers();

        CategoryAxis xAxis = chart.getDomainAxis();
        xAxis.setVisible(false);
        xAxis.setCategoryMargin(0.5);

        chart.getRangeAxisAsNumber().setVisible(false);

        chart.setSeriesPaint(0, new Color(156, 164, 74));
        chart.setDrawBarOutline(false);
        chart.setDefaultItemLabelsVisible(true);
        chart.setDefaultItemLabelFont(new Font("SansSerif", Font.BOLD, 10));
        chart.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE3, TextAnchor.CENTER_RIGHT));

        Font boldFont = new Font("SansSerif", Font.BOLD, 12);
        Font plainFont = new Font("SansSerif", Font.PLAIN, 12);

        chart.addAnnotation("1. White papers are available.", "Category 1", 0.0,
                boldFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("2. White papers enhance users understanding of the firm and its expertise.", "Category 2", 0.0,
                plainFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("3. White papers are relevant to the firm's prospects and clients.", "Category 3", 0.0,
                plainFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("4. White papers are relevant to the firm's positioning.", "Category 4", 0.0,
                plainFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("5. Case studies are available.", "Category 5", 0.0,
                boldFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("6. Case studies enhance users understanding of the firm and its expertise.", "Category 6", 0.0,
                plainFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("7. Case studies are relevant to the firm's prospects and clients.", "Category 7", 0.0,
                plainFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("8. White papers are relevant to the firm's positioning.", "Category 8", 0.0,
                plainFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("9. Case studies are available.", "Category 9", 0.0,
                boldFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("10. Case studies enhance users understanding of the firm and its expertise.", "Category 10", 0.0,
                plainFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("11. Case studies are relevant to the firm's prospects and clients.", "Category 11", 0.0,
                plainFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("12. White papers are relevant to the firm's positioning.", "Category 12", 0.0,
                plainFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("13. Users can easily access resources based on viewer interest.", "Category 13", 0.0,
                boldFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("14. Access to additional hyperlinks enhances users's ability to find relevant information.", "Category 14", 0.0,
                boldFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);
        chart.addAnnotation("15. OVERALL EFFECTIVENESS.", "Overall", 0.0,
                boldFont, TextAnchor.BOTTOM_LEFT, CategoryAnchor.START);

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        SurveyResultsDemo1 demo = new SurveyResultsDemo1("Survey Results Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
