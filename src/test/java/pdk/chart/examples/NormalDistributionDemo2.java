package pdk.chart.examples;

import pdk.chart.Chart;
import pdk.chart.annotations.XYPointerAnnotation;
import pdk.chart.data.function.NormalDistributionFunction2D;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;

/**
 * A line chart displaying several normal distribution functions.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jul 2025, 1:27 PM
 */
public class NormalDistributionDemo2 extends ApplicationFrame {

    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public NormalDistributionDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static Chart createChart(XYDataset dataset) {
        XYChart chart = XYChart.create()
                .dataset(dataset, XYChartType.LINE)
                .title("Normal Distributions")
                .axisNames("X", "Y")
                .showLegend(true)
                .zeroBaselineVisible(true, true)
                .pannable(true, true)
                .lineAndShapeProps(0)
                .addTooltips(true)
                .seriesStroke(0, new BasicStroke(1.5F))
                .seriesStroke(1, new BasicStroke(2.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0F, new float[]{6.0F, 4.0F}, 0.0F))
                .seriesStroke(2, new BasicStroke(2.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0F, new float[]{6.0F, 4.0F, 3.0F, 3.0F}, 0.0F))
                .seriesStroke(3, new BasicStroke(2.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0F, new float[]{4.0F, 4.0F}, 0.0F))
                .drawSeriesLineAsPath(true)
                .done()
                .domainAxis()
                .lowerMargin(0)
                .upperMargin(0)
                .doneXY();

        XYPointerAnnotation a1 = new XYPointerAnnotation("μ = -2.0, σ² = 0.5", -2.0D, 0.564D, 3.9269908169872414D);
        a1.setLabelOffset(4.0D);
        a1.setTextAnchor(TextAnchor.BOTTOM_RIGHT);
        a1.setBackgroundPaint(Color.YELLOW);
        chart.addAnnotation(a1);

        XYPointerAnnotation a2 = new XYPointerAnnotation("μ = 0.0, σ²= 0.2", 0.225D, 0.8D, 0.0D);
        a2.setLabelOffset(4.0D);
        a2.setTextAnchor(TextAnchor.CENTER_LEFT);
        a2.setBackgroundPaint(new Color(0, 0, 255, 63));
        chart.addAnnotation(a2);
        XYPointerAnnotation a3 = new XYPointerAnnotation("μ = 0.0, σ² = 1.0", 0.75D, 0.3D, 5.497787143782138D);
        a3.setLabelOffset(4.0D);
        a3.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        a3.setBackgroundPaint(new Color(255, 0, 0, 63));
        chart.addAnnotation(a3);
        XYPointerAnnotation a4 = new XYPointerAnnotation("μ = 0.0, σ² = 5.0", 3.0D, 0.075D, 4.71238898038469D);
        a4.setLabelOffset(4.0D);
        a4.setTextAnchor(TextAnchor.BOTTOM_CENTER);
        a4.setBackgroundPaint(new Color(0, 255, 0, 63));
        chart.addAnnotation(a4);

        return chart;
    }

    public static XYDataset<String> createDataset() {
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();

        NormalDistributionFunction2D f1 = new NormalDistributionFunction2D(0.0, 1.0);
        XYSeries<String> s1 = f1.sampleSeries(-5.1, 5.1, 121, "N1");
        dataset.addSeries(s1);

        NormalDistributionFunction2D f2 = new NormalDistributionFunction2D(0.0, Math.sqrt(0.2));
        XYSeries<String> s2 = f2.sampleSeries(-5.1, 5.1, 121, "N2");
        dataset.addSeries(s2);

        NormalDistributionFunction2D f3 = new NormalDistributionFunction2D(0.0, Math.sqrt(5.0));
        XYSeries<String> s3 = f3.sampleSeries(-5.1, 5.1, 121, "N3");
        dataset.addSeries(s3);

        NormalDistributionFunction2D f4 = new NormalDistributionFunction2D(-2.0, Math.sqrt(0.5));
        XYSeries<String> s4 = f4.sampleSeries(-5.1, 5.1, 121, "N4");
        dataset.addSeries(s4);

        return dataset;
    }

    static void main(String[] args) {
        NormalDistributionDemo2 demo = new NormalDistributionDemo2("JFreeChart: NormalDistributionDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
