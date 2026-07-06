package pdk.chart.demo;

import pdk.chart.AxisType;
import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * A demo that shows varying bar widths on an XYPlot.
 * <p>
 * The underlying dataset is an IntervalXYDataset which places no limitation on the interval for either the x-values or the y-values.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 16 Jun 2026, 2:25 PM
 */
public class XYBarChartDemo3 extends ApplicationFrame {

    public XYBarChartDemo3(String title) {
        super(title);
        IntervalXYDataset dataset = new SimpleIntervalXYDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        return JChart.bar(dataset, "X", AxisType.NUMBER, "Y", "Sample");
    }

    public static JPanel createDemoPanel() {
        IntervalXYDataset dataset = new SimpleIntervalXYDataset();
        return new ChartPanel(createChart(dataset), false);
    }

    static void main() {
        XYBarChartDemo3 demo = new XYBarChartDemo3("XY Bar Chart Demo 3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
