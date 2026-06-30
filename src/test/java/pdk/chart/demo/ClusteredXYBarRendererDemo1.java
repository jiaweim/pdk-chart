package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.time.Day;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import javax.swing.*;
import java.awt.*;

public class ClusteredXYBarRendererDemo1 extends ApplicationFrame {

    public ClusteredXYBarRendererDemo1(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    private static Chart createChart(String title, IntervalXYDataset dataset) {
        Chart chart = JChart.barCluster(title, null, true, "Y", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = chart.getXYPlot();
        plot.getClusteredXYBarRenderer(0)
                .margin(0.2);
        return chart;
    }

    private static IntervalXYDataset<String> createDataset() {
        return Data.<String>time()
                .addSeries("Series 1",
                        new Day[]{
                                new Day(1, 1, 2003),
                                new Day(2, 1, 2003),
                                new Day(3, 1, 2003),
                                new Day(4, 1, 2003)
                        },
                        new double[]{54.3, 20.3, 43.4, -12.0}
                )
                .addSeries("Series 2",
                        new Day[]{
                                new Day(1, 1, 2003),
                                new Day(2, 1, 2003),
                                new Day(3, 1, 2003),
                                new Day(4, 1, 2003)
                        },
                        new double[]{8.0, 16.0, 21.0, 5.0}
                ).build();
    }

    public static JPanel createDemoPanel() {
        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        panel.setPreferredSize(new Dimension(800, 600));
        IntervalXYDataset dataset = createDataset();

        GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW);
        GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN);

        Chart chart1 = createChart("Vertical", dataset);
        chart1.getXYPlot()
                .getClusteredXYBarRenderer(0)
                .drawBarOutline(false)
                .seriesPaint(0, gp0)
                .seriesPaint(1, gp1)
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));

        ChartPanel chartPanel1 = new ChartPanel(chart1);
        panel.add(chartPanel1);

        Chart chart2 = createChart("Vertical / Inverted Axis", dataset);
        chart2.getXYPlot()
                .getBarRenderer(0)
                .drawBarOutline(false)
                .seriesPaint(0, gp0)
                .seriesPaint(1, gp1)
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
        chart2.getXYPlot().getDomainAxisAsDate()
                .inverted(true);

        ChartPanel chartPanel2 = new ChartPanel(chart2);
        panel.add(chartPanel2);

        Chart chart3 = createChart("Horizontal", dataset);
        XYPlot plot3 = chart3.getXYPlot();
        plot3.setOrientation(PlotOrientation.HORIZONTAL);
        plot3.getBarRenderer()
                .drawBarOutline(false)
                .seriesPaint(0, gp0)
                .seriesPaint(1, gp1)
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
        ChartPanel chartPanel3 = new ChartPanel(chart3);
        panel.add(chartPanel3);

        Chart chart4 = createChart("Horizontal / Inverted Axis", dataset);
        XYPlot plot4 = (XYPlot) chart4.getPlot();
        plot4.setOrientation(PlotOrientation.HORIZONTAL);
        plot4.getBarRenderer()
                .drawBarOutline(false)
                .seriesPaint(0, gp0)
                .seriesPaint(1, gp1)
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
        plot4.getDomainAxisAsDate().inverted(true);

        ChartPanel chartPanel4 = new ChartPanel(chart4);
        panel.add(chartPanel4);
        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);
        return panel;
    }

    static void main() {
        ClusteredXYBarRendererDemo1 demo = new ClusteredXYBarRendererDemo1("ClusteredXYBarRendererDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
