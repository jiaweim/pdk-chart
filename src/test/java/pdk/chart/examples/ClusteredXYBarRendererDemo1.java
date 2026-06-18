package pdk.chart.examples;

import pdk.chart.data.time.Day;
import pdk.chart.data.time.TimeSeries;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.fluent.AxisType;
import pdk.chart.fluent.Data;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import java.awt.*;

/**
 * A simple demo for the ClusteredXYBarRenderer class.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 17 Jun 2026, 12:56 PM
 */
public class ClusteredXYBarRendererDemo1 {

    static void main() {
        TimeSeries<String> s1 = new TimeSeries<>("Series 1");
        s1.add(new Day[]{
                        new Day(1, 1, 2003),
                        new Day(2, 1, 2003),
                        new Day(3, 1, 2003),
                        new Day(4, 1, 2003)
                },
                new double[]{54.3, 20.3, 43.4, -12.0});

        TimeSeries<String> s2 = new TimeSeries<>("Series 2");
        s2.add(new Day[]{
                        new Day(1, 1, 2003),
                        new Day(2, 1, 2003),
                        new Day(3, 1, 2003),
                        new Day(4, 1, 2003)
                },
                new double[]{8.0, 16.0, 21.0, 5.0,});

        IntervalXYDataset<String> dataset = Data.createIntervalXYDataset(s1, s2);

        DemoPanel panel = new DemoPanel(new GridLayout(2, 2));
        panel.setPreferredSize(new Dimension(800, 600));

        XYChart chart1 = XYChart.create(AxisType.DATE, AxisType.NUMBER)
                .title("Vertical")
                .dataset(dataset, XYChartType.BAR_CLUSTER)
                .axisNames(null, "Y")

                .barProps(0)
                .drawBarOutline(false)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN))
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL))
                .margin(0.2)
                .done();
        XYChart chart2 = XYChart.create(AxisType.DATE, AxisType.NUMBER)
                .title("Vertical / Inverted Axis")
                .dataset(dataset, XYChartType.BAR_CLUSTER)

                .domainAxisDate()
                .inverted(true)
                .doneXY()

                .barProps(0)
                .drawBarOutline(false)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN))
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL))
                .margin(0.2)
                .done();

        XYChart chart3 = XYChart.create(AxisType.DATE, AxisType.NUMBER)
                .orientation(PlotOrientation.HORIZONTAL)
                .title("Horizontal")
                .dataset(dataset, XYChartType.BAR_CLUSTER)

                .barProps(0)
                .margin(0.2)
                .drawBarOutline(false)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN))
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL))
                .done();

        XYChart chart4 = XYChart.create(AxisType.DATE, AxisType.NUMBER)
                .orientation(PlotOrientation.HORIZONTAL)
                .title("Horizontal / Inverted Axis")
                .dataset(dataset, XYChartType.BAR_CLUSTER)

                .domainAxisDate()
                .inverted(true)
                .doneXY()

                .barProps(0)
                .drawBarOutline(false)
                .seriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.RED, 0.0F, 0.0F, Color.YELLOW))
                .seriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, Color.GREEN))
                .gradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL))
                .done();


        ChartPanel panel1 = new ChartPanel(chart1);
        ChartPanel panel2 = new ChartPanel(chart2);
        ChartPanel panel3 = new ChartPanel(chart3);
        ChartPanel panel4 = new ChartPanel(chart4);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);

        panel.addChart(chart1);
        panel.addChart(chart2);
        panel.addChart(chart3);
        panel.addChart(chart4);

        ApplicationFrame frame = new ApplicationFrame("");
        frame.setContentPane(panel);
        frame.pack();
        UIUtils.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }
}