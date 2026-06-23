package pdk.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import pdk.chart.ChartFactory;
import pdk.chart.Chart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class BarChartDemo6 extends ApplicationFrame {
    public BarChartDemo6(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double)83.0F, "First", "Factor 1");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = ChartFactory.createBarChart((String)null, "Category", "Score (%)", dataset);
        chart.removeLegend();
        chart.setBackgroundPaint(Color.YELLOW);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        plot.setInsets(new RectangleInsets((double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F));
        plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        plot.setRangeGridlinesVisible(false);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.2);
        domainAxis.setUpperMargin(0.2);
        domainAxis.setVisible(false);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setRange((double)0.0F, (double)100.0F);
        rangeAxis.setVisible(false);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        BarChartDemo6 demo = new BarChartDemo6("Chart: BarChartDemo6.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
