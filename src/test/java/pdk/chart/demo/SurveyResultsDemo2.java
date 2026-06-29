package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import javax.swing.JPanel;
import pdk.chart.JChart;
import pdk.chart.Chart;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.axis.ExtendedCategoryAxis;
import pdk.chart.axis.ValueAxis;
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

public class SurveyResultsDemo2 extends ApplicationFrame {
    public SurveyResultsDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(300, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.32, "Results", "Sm.");
        dataset.addValue(0.4, "Results", "Med.");
        dataset.addValue(2.62, "Results", "Lg.");
        dataset.addValue(1.44, "Results", "All");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.bar((String)null, (String)null, (String)null, dataset, PlotOrientation.VERTICAL, false, true, false);
        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setOutlinePaint((Paint)null);
        TextTitle title = new TextTitle("Figure 8.5 - Case studies are available");
        title.setHorizontalAlignment(HorizontalAlignment.LEFT);
        title.setBackgroundPaint(Color.RED);
        title.setPaint(Color.WHITE);
        chart.setTitle(title);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setRange((double)0.0F, (double)5.0F);
        rangeAxis.setVisible(false);
        ExtendedCategoryAxis domainAxis = new ExtendedCategoryAxis((String)null);
        domainAxis.setTickLabelFont(new Font("SansSerif", 1, 12));
        domainAxis.setCategoryMargin(0.3);
        domainAxis.addSubLabel("Sm.", "(10)");
        domainAxis.addSubLabel("Med.", "(10)");
        domainAxis.addSubLabel("Lg.", "(10)");
        domainAxis.addSubLabel("All", "(10)");
        plot.setDomainAxis(domainAxis);
        BarRenderer renderer = (BarRenderer)plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(156, 164, 74));
        renderer.setDrawBarOutline(false);
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(new Font("SansSerif", Font.PLAIN, 18));
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.TOP_CENTER);
        renderer.setDefaultPositiveItemLabelPosition(position);
        renderer.setPositiveItemLabelPositionFallback(new ItemLabelPosition());
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        SurveyResultsDemo2 demo = new SurveyResultsDemo2("Survey Results Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
