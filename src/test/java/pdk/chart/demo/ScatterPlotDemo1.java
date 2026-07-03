package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class ScatterPlotDemo1 extends ApplicationFrame {

    public ScatterPlotDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.scatter(dataset, "X", "Y", "Scatter Plot Demo 1");
        XYPlot plot = chart.getXYPlot();
        plot.noDataMessage("NO DATA")
                .domainPannable(true)
                .rangePannable(true)
                .domainZeroBaselineVisible(true)
                .rangeZeroBaselineVisible(true)
                .domainGridlineStroke(new BasicStroke(0f))
                .domainMinorGridlineStroke(new BasicStroke(0f))
                .domainGridlinePaint(Color.BLUE)
                .rangeGridlineStroke(new BasicStroke(0f))
                .rangeMinorGridlineStroke(new BasicStroke(0f))
                .rangeGridlinePaint(Color.BLUE)
                .domainMinorGridlinesVisible(true)
                .rangeMinorGridlinesVisible(true);

        plot.getLineAndShapeRenderer()
                .seriesOutlinePaint(0, Color.BLACK)
                .useOutlinePaint(true);
        plot.getDomainAxisAsNumber()
                .autoRangeIncludesZero(false)
                .tickMarkInsideLength(2.0f)
                .tickMarkOutsideLength(2.0f)
                .minorTickCount(2)
                .minorTickMarksVisible(true);
        plot.getRangeAxisAsNumber()
                .tickMarkInsideLength(2.0f)
                .tickMarkOutsideLength(2.0f)
                .minorTickCount(2)
                .minorTickMarksVisible(true);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(new SampleXYDataset2());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        return chartPanel;
    }

    static void main() {
        ScatterPlotDemo1 demo = new ScatterPlotDemo1("ScatterPlotDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
