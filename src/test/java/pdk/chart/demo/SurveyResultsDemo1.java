package pdk.chart.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.Chart;
import pdk.chart.annotations.CategoryTextAnnotation;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.axis.CategoryAnchor;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;

public class SurveyResultsDemo1 extends ApplicationFrame {
    public SurveyResultsDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(700, 600));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(2.01, "Results", "Category 1");
        dataset.addValue(2.02, "Results", "Category 2");
        dataset.addValue((double)2.0F, "Results", "Category 3");
        dataset.addValue(1.97, "Results", "Category 4");
        dataset.addValue(1.44, "Results", "Category 5");
        dataset.addValue(1.49, "Results", "Category 6");
        dataset.addValue(1.49, "Results", "Category 7");
        dataset.addValue(1.48, "Results", "Category 8");
        dataset.addValue(4.26, "Results", "Category 9");
        dataset.addValue(4.08, "Results", "Category 10");
        dataset.addValue(4.03, "Results", "Category 11");
        dataset.addValue(3.92, "Results", "Category 12");
        dataset.addValue(3.99, "Results", "Category 13");
        dataset.addValue(2.23, "Results", "Category 14");
        dataset.addValue(2.6, "Results", "Overall");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = ChartFactory.bar((String)null, (String)null, (String)null, dataset, PlotOrientation.HORIZONTAL, false, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        TextTitle title = new TextTitle("Figure 7 | I. Resources - The site offers users relevant, informative and educational resources");
        title.setHorizontalAlignment(HorizontalAlignment.LEFT);
        title.setBackgroundPaint(Color.RED);
        title.setPaint(Color.WHITE);
        chart.setTitle(title);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setOutlinePaint((Paint)null);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePosition(CategoryAnchor.END);
        plot.setDomainGridlineStroke(new BasicStroke(0.5F));
        plot.setDomainGridlinePaint(Color.black);
        plot.setRangeGridlinesVisible(false);
        plot.clearRangeMarkers();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setVisible(false);
        domainAxis.setCategoryMargin((double)0.5F);
        plot.getRangeAxis().setVisible(false);
        BarRenderer renderer = (BarRenderer)plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(156, 164, 74));
        renderer.setDrawBarOutline(false);
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(new Font("SansSerif", 1, 10));
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.INSIDE3, TextAnchor.CENTER_RIGHT);
        renderer.setDefaultPositiveItemLabelPosition(position);
        CategoryTextAnnotation a1 = new CategoryTextAnnotation("1. White papers are available.", "Category 1", (double)0.0F);
        a1.setFont(new Font("SansSerif", 1, 12));
        a1.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a1.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a1);
        CategoryTextAnnotation a2 = new CategoryTextAnnotation("2. White papers enhance users understanding of the firm and its expertise.", "Category 2", (double)0.0F);
        a2.setFont(new Font("SansSerif", 0, 12));
        a2.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a2.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a2);
        CategoryTextAnnotation a3 = new CategoryTextAnnotation("3. White papers are relevant to the firm's prospects and clients.", "Category 3", (double)0.0F);
        a3.setFont(new Font("SansSerif", 0, 12));
        a3.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a3.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a3);
        CategoryTextAnnotation a4 = new CategoryTextAnnotation("4. White papers are relevant to the firm's positioning.", "Category 4", (double)0.0F);
        a4.setFont(new Font("SansSerif", 0, 12));
        a4.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a4.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a4);
        CategoryTextAnnotation a5 = new CategoryTextAnnotation("5. Case studies are available.", "Category 5", (double)0.0F);
        a5.setFont(new Font("SansSerif", 1, 12));
        a5.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a5.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a5);
        CategoryTextAnnotation a6 = new CategoryTextAnnotation("6. Case studies enhance users understanding of the firm and its expertise.", "Category 6", (double)0.0F);
        a6.setFont(new Font("SansSerif", 0, 12));
        a6.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a6.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a6);
        CategoryTextAnnotation a7 = new CategoryTextAnnotation("7. Case studies are relevant to the firm's prospects and clients.", "Category 7", (double)0.0F);
        a7.setFont(new Font("SansSerif", 0, 12));
        a7.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a7.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a7);
        CategoryTextAnnotation a8 = new CategoryTextAnnotation("8. White papers are relevant to the firm's positioning.", "Category 8", (double)0.0F);
        a8.setFont(new Font("SansSerif", 0, 12));
        a8.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a8.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a8);
        CategoryTextAnnotation a9 = new CategoryTextAnnotation("9. Case studies are available.", "Category 9", (double)0.0F);
        a9.setFont(new Font("SansSerif", 1, 12));
        a9.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a9.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a9);
        CategoryTextAnnotation a10 = new CategoryTextAnnotation("10. Case studies enhance users understanding of the firm and its expertise.", "Category 10", (double)0.0F);
        a10.setFont(new Font("SansSerif", 0, 12));
        a10.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a10.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a10);
        CategoryTextAnnotation a11 = new CategoryTextAnnotation("11. Case studies are relevant to the firm's prospects and clients.", "Category 11", (double)0.0F);
        a11.setFont(new Font("SansSerif", 0, 12));
        a11.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a11.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a11);
        CategoryTextAnnotation a12 = new CategoryTextAnnotation("12. White papers are relevant to the firm's positioning.", "Category 12", (double)0.0F);
        a12.setFont(new Font("SansSerif", 0, 12));
        a12.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a12.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a12);
        CategoryTextAnnotation a13 = new CategoryTextAnnotation("13. Users can easily access resources based on viewer interest.", "Category 13", (double)0.0F);
        a13.setFont(new Font("SansSerif", 1, 12));
        a13.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a13.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a13);
        CategoryTextAnnotation a14 = new CategoryTextAnnotation("14. Access to additional hyperlinks enhances users's ability to find relevant information.", "Category 14", (double)0.0F);
        a14.setFont(new Font("SansSerif", 1, 12));
        a14.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a14.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a14);
        CategoryTextAnnotation a15 = new CategoryTextAnnotation("15. OVERALL EFFECTIVENESS.", "Overall", (double)0.0F);
        a15.setFont(new Font("SansSerif", 1, 12));
        a15.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        a15.setCategoryAnchor(CategoryAnchor.START);
        plot.addAnnotation(a15);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        SurveyResultsDemo1 demo = new SurveyResultsDemo1("Survey Results Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
