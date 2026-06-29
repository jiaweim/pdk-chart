package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.api.RectangleInsets;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.fluent.Data;
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

    private static CategoryDataset<String, String> createDataset() {
        return Data.createCategoryDataset("Network Traffic",
                new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"},
                new double[]{410.0, 680.0, 530.0, 570.0, 330.0}
        );
    }

    private static Chart createChart(CategoryDataset<String, String> dataset) {
        Chart chart = ChartFactory.bar("Bar Chart Demo 9", null, "Value", dataset,
                PlotOrientation.VERTICAL, false, true, false);
        TextTitle title = chart.getTitle();
        title.setBorder(0.0, 0.0, 1.0, 0.0);
        title.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.RED, 350.0F, 0.0F, Color.WHITE, true));
        title.setExpandToFitSpace(true);

        chart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.YELLOW, 350.0F, 0.0F, Color.WHITE, true));
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA!");
        plot.setBackgroundPaint(null);
        plot.setInsets(new RectangleInsets(10.0F, (double) 5.0F, (double) 5.0F, (double) 5.0F));
        plot.setOutlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setRangeGridlineStroke(new BasicStroke(1.0F));
        Paint[] colors = createPaint();
        CustomBarRenderer renderer = new CustomBarRenderer(colors);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDrawBarOutline(true);
        renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
        plot.setRenderer(renderer);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setRange((double) 0.0F, (double) 800.0F);
        rangeAxis.setTickMarkPaint(Color.black);
        return chart;
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

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        BarChartDemo9 demo = new BarChartDemo9("Chart: BarChartDemo9.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class CustomBarRenderer extends BarRenderer {
        private Paint[] colors;

        public CustomBarRenderer(Paint[] colors) {
            this.colors = colors;
        }

        public Paint getItemPaint(int row, int column) {
            return this.colors[column % this.colors.length];
        }
    }
}
