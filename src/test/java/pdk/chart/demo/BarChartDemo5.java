package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.ChartUtils;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class BarChartDemo5 extends ApplicationFrame {
    public BarChartDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String seriesKey = "Prison Population Rates";
        dataset.addValue((double) 59.0F, seriesKey, "Norway");
        dataset.addValue((double) 69.0F, seriesKey, "Switzerland");
        dataset.addValue((double) 85.0F, seriesKey, "France");
        dataset.addValue((double) 93.0F, seriesKey, "Syria");
        dataset.addValue((double) 96.0F, seriesKey, "Germany");
        dataset.addValue((double) 111.0F, seriesKey, "China");
        dataset.addValue((double) 116.0F, seriesKey, "Australia");
        dataset.addValue((double) 121.0F, seriesKey, "Egypt");
        dataset.addValue((double) 129.0F, seriesKey, "England & Wales");
        dataset.addValue((double) 157.0F, seriesKey, "New Zealand");
        dataset.addValue((double) 205.0F, seriesKey, "Chile");
        dataset.addValue((double) 229.0F, seriesKey, "Iran");
        dataset.addValue((double) 359.0F, seriesKey, "Singapore");
        dataset.addValue((double) 404.0F, seriesKey, "South Africa");
        dataset.addValue((double) 406.0F, seriesKey, "Ukraine");
        dataset.addValue((double) 686.0F, seriesKey, "USA");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = ChartFactory.bar("Prison Population Rates - Selected Countries", "Country", "Prisoners Per 100,000 National Population", dataset, PlotOrientation.HORIZONTAL, false, true, false);
        chart.addSubtitle(new TextTitle("Source: http://www.homeoffice.gov.uk/rds/pdfs2/r188.pdf", new Font("Dialog", 2, 10)));
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.setRangePannable(true);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemLabelInsets(new RectangleInsets(9, 9, 9, 9));
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator("{0}, {1}) = {2} per 100,000", new DecimalFormat("0")));
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setCategoryMargin((double) 0.25F);
        categoryAxis.setUpperMargin(0.02);
        categoryAxis.setLowerMargin(0.02);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.1);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        BarChartDemo5 demo = new BarChartDemo5("Chart: BarChartDemo5.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
