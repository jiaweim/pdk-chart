package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.CategoryLabelPositions;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.LineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class LineChartDemo1 extends ApplicationFrame {
    public LineChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(768, 512));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((double) 212.0F, "Classes", "JDK 1.0");
        dataset.addValue((double) 504.0F, "Classes", "JDK 1.1");
        dataset.addValue((double) 1520.0F, "Classes", "JDK 1.2");
        dataset.addValue((double) 1842.0F, "Classes", "JDK 1.3");
        dataset.addValue((double) 2991.0F, "Classes", "JDK 1.4");
        dataset.addValue((double) 3500.0F, "Classes", "JDK 1.5");
        dataset.addValue((double) 3793.0F, "Classes", "JDK 1.6");
        dataset.addValue((double) 4024.0F, "Classes", "JDK 1.7");
        dataset.addValue((double) 4240.0F, "Classes", "JDK 8");
        dataset.addValue((double) 6005.0F, "Classes", "JDK 9");
        dataset.addValue((double) 6002.0F, "Classes", "JDK 10");
        dataset.addValue((double) 4411.0F, "Classes", "JDK 11");
        dataset.addValue((double) 4433.0F, "Classes", "JDK 12");
        dataset.addValue((double) 4545.0F, "Classes", "JDK 13");
        dataset.addValue((double) 4569.0F, "Classes", "JDK 14");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        Chart chart = JChart.line("Java Standard Class Library", (String) null, "Class Count", dataset, PlotOrientation.VERTICAL, false, true, false);
        chart.addSubtitle(new TextTitle("Number of Classes By Release"));
        TextTitle source = new TextTitle("Source: https://stackoverflow.com/q/3112882 and Java In A Nutshell (5th Edition) by David Flanagan (O'Reilly)");
        source.setFont(new Font("SansSerif", 0, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        plot.setRangeGridlinesVisible(false);

        try {
            Image image = ImageIO.read(LineChartDemo1.class.getResourceAsStream("OnBridge11small.png"));
            chart.setBackgroundImage(image);
            plot.setBackgroundPaint((Paint) null);
        } catch (Exception var7) {
            System.err.println("Error loading background image.");
        }

        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartUtils.applyCurrentTheme(chart);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setDefaultFillPaint(Color.WHITE);
        renderer.setSeriesStroke(0, new BasicStroke(3.0F));
        renderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
        renderer.setSeriesShape(0, new Ellipse2D.Double((double) -5.0F, (double) -5.0F, (double) 10.0F, (double) 10.0F));
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        LineChartDemo1 demo = new LineChartDemo1("Chart: LineChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
