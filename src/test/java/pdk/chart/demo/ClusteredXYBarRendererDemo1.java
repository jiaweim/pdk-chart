package pdk.chart.demo;

import pdk.chart.AxisType;
import pdk.chart.ClusteredBarChart;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.time.Day;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.model.Data;
import pdk.chart.plot.PlotOrientation;
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

    private static ClusteredBarChart createChart(String title, IntervalXYDataset dataset) {
        ClusteredBarChart chart = new ClusteredBarChart(dataset, null, AxisType.DATE,
                "Y", AxisType.NUMBER, title);
        chart.setBarMargin(0.2);
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

        ClusteredBarChart chart1 = createChart("Vertical", dataset);
        chart1.setDrawBarOutline(false);
        chart1.setSeriesPaint(0, gp0);
        chart1.setSeriesPaint(1, gp1);
        chart1.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));

        ChartPanel chartPanel1 = new ChartPanel(chart1);
        panel.add(chartPanel1);

        ClusteredBarChart chart2 = createChart("Vertical / Inverted Axis", dataset);
        chart2.setDrawBarOutline(false);
        chart2.setSeriesPaint(0, gp0);
        chart2.setSeriesPaint(1, gp1);
        chart2.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
        ValueAxis xAxis = chart2.getDomainAxis();
        xAxis.setInverted(true);

        ChartPanel chartPanel2 = new ChartPanel(chart2);
        panel.add(chartPanel2);

        ClusteredBarChart chart3 = createChart("Horizontal", dataset);
        chart3.setOrientation(PlotOrientation.HORIZONTAL);
        chart3.setDrawBarOutline(false);
        chart3.setSeriesPaint(0, gp0);
        chart3.setSeriesPaint(1, gp1);
        chart3.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));

        ChartPanel chartPanel3 = new ChartPanel(chart3);
        panel.add(chartPanel3);

        ClusteredBarChart chart4 = createChart("Horizontal / Inverted Axis", dataset);
        chart4.setOrientation(PlotOrientation.HORIZONTAL);
        chart4.setDrawBarOutline(false);
        chart4.setSeriesPaint(0, gp0);
        chart4.setSeriesPaint(1, gp1);
        chart4.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
        chart4.getDomainAxis().setInverted(true);

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
