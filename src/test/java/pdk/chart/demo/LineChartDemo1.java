package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChartUtils;
import pdk.chart.JChart;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.Data;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 30 Jun 2026, 1:47 PM
 */
public class LineChartDemo1 extends ApplicationFrame {

    public LineChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(768, 512));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset<String, String> createDataset() {
        return Data.createCategory("Classes",
                new String[]{
                        "JDK 1.0", "JDK 1.1", "JDK 1.2", "JDK 1.3", "JDK 1.4",
                        "JDK 1.5", "JDK 1.6", "JDK 1.7", "JDK 8", "JDK 9",
                        "JDK 10", "JDK 11", "JDK 12", "JDK 13", "JDK 14"
                },
                new double[]{
                        212.0, 504.0, 1520.0, 1842.0, 2991.0,
                        3500.0, 3793.0, 4024.0, 4240.0, 6005.0,
                        6002.0, 4411.0, 4433.0, 4545.0, 4569.0
                }
        );
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.line("Java Standard Class Library", null,
                "Class Count", dataset, PlotOrientation.VERTICAL, false, true, false);
        chart.addSubtitle(new TextTitle("Number of Classes By Release"));
        TextTitle source = new TextTitle("Source: https://stackoverflow.com/q/3112882 and Java In A Nutshell (5th Edition) by David Flanagan (O'Reilly)");
        source.setFont(new Font("SansSerif", 0, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.rangePannable(true)
                .rangeGridlinesVisible(false);

        try {
            Image image = ImageIO.read(LineChartDemo1.class.getResourceAsStream("OnBridge11small.png"));
            chart.setBackgroundImage(image);
            plot.setBackgroundPaint(null);
        } catch (Exception var7) {
            System.err.println("Error loading background image.");
        }

        plot.getDomainAxis()
                .categoryLabelPositions(CategoryLabelPositions.UP_90);
        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits());
        JChartUtils.applyCurrentTheme(chart);

        plot.getLineAndShapeRenderer()
                .defaultShapesVisible(true)
                .drawOutlines(true)
                .useFillPaint(true)
                .defaultFillPaint(Color.WHITE)
                .seriesStroke(0, new BasicStroke(3.0F))
                .seriesOutlineStroke(0, new BasicStroke(2.0F))
                .seriesShape(0, new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));

        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        LineChartDemo1 demo = new LineChartDemo1("Chart: LineChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
