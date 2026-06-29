package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import pdk.chart.JChart;
import pdk.chart.Chart;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.axis.CategoryLabelPosition;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.ExtendedCategoryAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.text.TextBlockAnchor;
import pdk.chart.title.TextTitle;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;

public class SurveyResultsDemo3 extends ApplicationFrame {
    public SurveyResultsDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(300, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(2.61, "Results", "Sm.");
        dataset.addValue(2.7, "Results", "Med.");
        dataset.addValue(2.9, "Results", "Lg.");
        dataset.addValue(2.74, "Results", "All");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar((String)null, (String)null, (String)null, dataset, PlotOrientation.HORIZONTAL, false, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setOutlinePaint((Paint)null);
        TextTitle title = new TextTitle("Figure 6 | Overall SEO Rating");
        title.setHorizontalAlignment(HorizontalAlignment.LEFT);
        title.setBackgroundPaint(Color.RED);
        title.setPaint(Color.WHITE);
        chart.setTitle(title);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setRange((double)0.0F, (double)4.0F);
        rangeAxis.setVisible(false);
        ExtendedCategoryAxis domainAxis = new ExtendedCategoryAxis((String)null);
        domainAxis.setTickLabelFont(new Font("SansSerif", 1, 12));
        domainAxis.setCategoryMargin(0.3);
        domainAxis.addSubLabel("Sm.", "(10)");
        domainAxis.addSubLabel("Med.", "(10)");
        domainAxis.addSubLabel("Lg.", "(10)");
        domainAxis.addSubLabel("All", "(10)");
        CategoryLabelPositions p = domainAxis.getCategoryLabelPositions();
        CategoryLabelPosition left = new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.replaceLeftPosition(p, left));
        plot.setDomainAxis(domainAxis);
        BarRenderer renderer = (BarRenderer)plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(156, 164, 74));
        renderer.setDrawBarOutline(false);
        StandardCategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("0.00"));
        renderer.setDefaultItemLabelGenerator(generator);
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(new Font("SansSerif", 0, 18));
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.INSIDE3, TextAnchor.CENTER_RIGHT);
        renderer.setDefaultPositiveItemLabelPosition(position);
        renderer.setPositiveItemLabelPositionFallback(new ItemLabelPosition());
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        SurveyResultsDemo3 demo = new SurveyResultsDemo3("Survey Results Demo 3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
