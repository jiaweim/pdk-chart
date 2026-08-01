package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.labels.StandardCategorySeriesLabelGenerator;
import pdk.chart.legend.LegendTitle;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.renderer.category.StandardBarPainter;
import pdk.chart.swing.ChartPanel;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;

public class BarChartDemo1 {

    private static Chart createChart() {
        String[] categories = new String[]{"18 to 39", "40 - 59", "60 and over"};
        DefaultCategoryDataset<String, String> dataset = Data.<String, String>category()
                .addSeries("Males", categories, new double[]{5.5, 8.4, 12.8})
                .addSeries("Females", categories, new double[]{10.3, 20.1, 24.3})
                .build();

        CategoryBarChart chart = new CategoryBarChart(dataset, "Age Category", "Percent", "Antidepressant Medication Usage");
        LegendTitle legend = chart.getLegend();
        chart.removeLegend();
        chart.addSubtitle(new TextTitle("Percentage of adults aged 18 and over who used antidepressant medication over past 30 days, by age and sex: United States, 2015-2018"));

        TextTitle source = new TextTitle("Source: https://www.cdc.gov/nchs/products/databriefs/db377.htm");
        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);

        chart.addSubtitle(legend);
        CategoryPlot<String, String> plot = chart.getCategoryPlot();
        plot.domainGridlinesVisible(true)
                .rangeCrosshairVisible(true)
                .rangeCrosshairPaint(Color.BLUE);
        plot.getDomainAxis()
                .categoryMargin(0.2);
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        plot.getBarRenderer(0)
                .drawBarOutline(false)
                .barPainter(new StandardBarPainter())
                .itemMargin(0.06)
                .legendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart();
        return new ChartPanel(chart);
    }

    static void main() {
        createChart().show();
    }
}
