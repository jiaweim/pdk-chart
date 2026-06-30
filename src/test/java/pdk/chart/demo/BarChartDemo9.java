package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.Data;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.renderer.category.StandardBarPainter;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.title.TextTitle;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import javax.swing.*;
import java.awt.*;

public class BarChartDemo9 extends ApplicationFrame {

    public BarChartDemo9(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Paint[] createPaint() {
        Paint[] colors = new Paint[5];
        colors[0] = new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.WHITE);
        colors[1] = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, Color.WHITE);
        colors[2] = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.WHITE);
        colors[3] = new GradientPaint(0.0F, 0.0F, Color.orange, 0.0F, 0.0F, Color.WHITE);
        colors[4] = new GradientPaint(0.0F, 0.0F, Color.magenta, 0.0F, 0.0F, Color.WHITE);
        return colors;
    }

    private static CategoryDataset<String, String> createDataset() {
        return Data.createCategory("Network Traffic",
                new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"},
                new double[]{410.0, 680.0, 530.0, 570.0, 330.0}
        );
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = JChart.bar("Bar Chart Demo 9", null, "Value", dataset,
                PlotOrientation.VERTICAL, false, true, false);

        TextTitle title = chart.getTitle();
        title.setBorder(0.0, 0.0, 1.0, 0.0);
        title.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.RED, 350.0F, 0.0F, Color.WHITE, true));
        title.setExpandToFitSpace(true);

        chart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.YELLOW, 350.0F, 0.0F, Color.WHITE, true));
        CategoryPlot plot = chart.getCategoryPlot();
        plot.noDataMessage("NO DATA!")
                .backgroundPaint(null)
                .insets(new RectangleInsets(10.0, 5.0, 5.0, 5.0))
                .outlinePaint(Color.BLACK)
                .rangeGridlinePaint(Color.GRAY)
                .rangeGridlineStroke(new BasicStroke(1.0f));

        Paint[] colors = createPaint();
        CustomBarRenderer renderer = new CustomBarRenderer(colors);
        renderer.barPainter(new StandardBarPainter())
                .drawBarOutline(true)
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
        plot.setRenderer(renderer);

        plot.getRangeAxisAsNumber()
                .standardTickUnits(NumberAxis.createIntegerTickUnits())
                .range(0, 800)
                .tickMarkPaint(Color.BLACK);

        return chart;
    }


    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        BarChartDemo9 demo = new BarChartDemo9("BarChartDemo9.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class CustomBarRenderer extends BarRenderer {
        private final Paint[] colors;

        public CustomBarRenderer(Paint[] colors) {
            this.colors = colors;
        }

        public Paint getItemPaint(int row, int column) {
            return this.colors[column % this.colors.length];
        }
    }
}
