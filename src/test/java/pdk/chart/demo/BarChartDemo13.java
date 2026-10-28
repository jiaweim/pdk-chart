package pdk.chart.demo;

import pdk.chart.CategoryBarChart;
import pdk.chart.api.RectangleEdge;
import pdk.chart.color.JColorQualitative;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.renderer.category.RoundedBarPainter;
import pdk.chart.swing.ChartPanel;
import pdk.chart.util.PaintUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 27 Oct 2026, 9:40 AM
 */
public class BarChartDemo13 {

    private static CategoryBarChart createChart() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July"};
        Random rnd = new Random(42);

        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        for (String month : months) {
            dataset.addValue(rnd.nextDouble(201) - 100, "Series 1", month);
            dataset.addValue(rnd.nextDouble(201) - 100, "Series 2", month);
        }

        CategoryBarChart chart = new CategoryBarChart(dataset);
        chart.getLegend().setPosition(RectangleEdge.TOP);
        chart.withDomainGridlinesVisible(true)
                .withRangeGridlinesVisible(true);

        Color[] colors = JColorQualitative.ChartJS();
        final Color RED = colors[0];
        final Color BLUE = colors[1];

        chart.getRenderer()
                .withBarPainter(new RoundedBarPainter(true))
                .withItemMargin(0.05)
                .withShadowVisible(false)
                .withDrawBarOutline(true)
                .withSeriesPaint(0, PaintUtils.withAlpha(RED, 0.5f))
                .withSeriesOutlinePaint(0, RED)
                .withSeriesOutlineStroke(0, new BasicStroke(2f))
                .withSeriesPaint(1, PaintUtils.withAlpha(BLUE, 0.5f))
                .withSeriesOutlinePaint(1, BLUE)
                .withSeriesOutlineStroke(1, new BasicStroke(2f))
                .withDefaultLegendShape(new Rectangle2D.Double(-8, -5, 25, 12));

        return chart;
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart());
    }

    static void main() {
        createChart().show();
    }
}
