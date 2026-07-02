package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.JChart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.Data;
import pdk.chart.labels.StandardCategoryItemLabelGenerator;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
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

    private static CategoryDataset<String, String> createDataset() {
        return Data.createCategory("Prison Population Rates",
                new String[]{
                        "Norway", "Switzerland", "France", "Syria", "Germany",
                        "China", "Australia", "Egypt", "England & Wales", "New Zealand",
                        "Chile", "Iran", "Singapore", "South Africa", "Ukraine",
                        "USA"
                },
                new double[]{
                        59.0, 69.0, 85.0, 93.0, 96.0,
                        111.0, 116.0, 121.0, 129.0, 157.0,
                        205.0, 229.0, 359.0, 404.0, 406.0,
                        686.0});
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.bar("Prison Population Rates - Selected Countries",
                "Country", "Prisoners Per 100,000 National Population",
                dataset, PlotOrientation.HORIZONTAL, false, true, false);

        chart.addSubtitle(new TextTitle("Source: http://www.homeoffice.gov.uk/rds/pdfs2/r188.pdf", new Font("Dialog", 2, 10)));
        CategoryPlot plot = chart.getCategoryPlot();
        plot.rangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT)
                .rangePannable(true);
        plot.getBarRenderer(0)
                .itemLabelInsets(new RectangleInsets(9, 9, 9, 9))
                .defaultItemLabelsVisible(true)
                .defaultItemLabelGenerator(new StandardCategoryItemLabelGenerator<>())
                .defaultToolTipGenerator(new StandardCategoryToolTipGenerator<>("{0}, {1}) = {2} per 100,000",
                        new DecimalFormat("0")));
        plot.getDomainAxis()
                .categoryMargin(0.25)
                .lowerMargin(0.02)
                .upperMargin(0.02);
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .upperMargin(0.1);

        JChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        BarChartDemo5 demo = new BarChartDemo5("BarChartDemo5.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
