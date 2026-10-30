package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.AxisLocation;
import pdk.chart.color.JColorQualitative;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ChartPanel;
import pdk.chart.util.PaintUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BarChartDemo15 {

    public static CategoryBarChart createChart() {

        String[] months = DatasetUtils.months(7);
        int n = months.length;
        Random rand = new Random(42);
        double[] values1 = new double[n];
        double[] values2 = new double[n];
        for (int i = 0; i < n; i++) {
            values1[i] = rand.nextInt(201) - 100;
            values2[i] = rand.nextInt(201) - 100;
        }

        DefaultCategoryDataset<String, String> dataset = DatasetUtils.<String, String>category(months)
                .addSeries("Dataset 1", values1)
                .addSeries("Dataset 2", values2)
                .build();

        Color[] colors = JColorQualitative.ChartJS();
        Color fillRed = PaintUtils.withAlpha(colors[0], 0.5f);
        Color fillBlue = PaintUtils.withAlpha(colors[1], 0.5f);

        CategoryBarChart chart = new CategoryBarChart(dataset, PlotOrientation.HORIZONTAL);
        chart.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        chart.setLegendPosition(RectangleEdge.RIGHT);
        chart.getRenderer()
                .withSeriesPaint(0, fillRed)
                .withSeriesOutlinePaint(0, colors[0])
                .withSeriesOutlineStroke(0, new BasicStroke(2f))
                .withDrawBarOutline(true)
                .withSeriesPaint(1, fillBlue)
                .withSeriesOutlinePaint(1, colors[1])
                .withSeriesOutlineStroke(1, new BasicStroke(2f))
                .withDrawBarOutline(true);
        chart.setTitle("Horizontal Bar Chart");

        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        createChart().show();
    }
}