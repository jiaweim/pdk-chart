package pdk.chart.demo;

import pdk.chart.CategoryLineChart;
import pdk.chart.Chart;
import pdk.chart.annotations.CategoryPointerAnnotation;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;
import pdk.chart.title.TextTitle;

import javax.swing.*;
import java.awt.*;

public class CategoryPointerAnnotationDemo1 extends ApplicationFrame {
    public CategoryPointerAnnotationDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(212.0, "Classes", "JDK 1.0");
        dataset.addValue(504.0, "Classes", "JDK 1.1");
        dataset.addValue(1520.0, "Classes", "JDK 1.2");
        dataset.addValue(1842.0, "Classes", "JDK 1.3");
        dataset.addValue(2991.0, "Classes", "JDK 1.4");
        return dataset;
    }

    private static Chart createChart(CategoryDataset dataset) {
        CategoryLineChart chart = new CategoryLineChart(dataset, "Release", "Class Count", "Java Standard Class Library",
                PlotOrientation.VERTICAL, false, true);
        chart.addSubtitle(new TextTitle("Number of Classes By Release"));
        TextTitle source = new TextTitle("Source: Java In A Nutshell (4th Edition) by David Flanagan (O'Reilly)");
        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);

        chart.getRangeAxisAsNumber()
                .setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        chart.getRenderer()
                .withDefaultShapesVisible(true)
                .withDrawOutlines(true)
                .withUseFillPaint(true)
                .withDefaultFillPaint(Color.WHITE);

        CategoryPointerAnnotation cpa = new CategoryPointerAnnotation("Released 4-Dec-1998", "JDK 1.2", (double) 1530.0F, -2.356194490192345);
        cpa.setTextAnchor(TextAnchor.BOTTOM_RIGHT);
        chart.addAnnotation(cpa);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        CategoryPointerAnnotationDemo1 demo = new CategoryPointerAnnotationDemo1("CategoryPointerAnnotationDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
