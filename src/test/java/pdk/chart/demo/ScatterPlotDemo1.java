package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ScatterChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
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
        ScatterChart chart = new ScatterChart(dataset, "X", "Y", "Scatter Plot Demo 1");
        chart.setNoDataMessage("No data");
        chart.setPannable(true, true);
        chart.setDomainZeroBaselineVisible(true);
        chart.setRangeZeroBaselineVisible(true);

        chart.setDomainGridlineStroke(new BasicStroke(0f));
        chart.setDomainMinorGridlineStroke(new BasicStroke(0f));
        chart.setDomainGridlinePaint(Color.BLUE);

        chart.setRangeGridlineStroke(new BasicStroke(0f));
        chart.setRangeMinorGridlineStroke(new BasicStroke(0f));
        chart.setRangeGridlinePaint(Color.BLUE);

        chart.setDomainMinorGridlinesVisible(true);
        chart.setRangeMinorGridlinesVisible(true);

        chart.setSeriesOutlinePaint(0, Color.BLACK);
        chart.setUseOutlinePaint(true);

        NumberAxis domainAxis = chart.getDomainAxisAsNumber();
        domainAxis.setAutoRangeIncludesZero(false);
        domainAxis.setTickMarkInsideLength(2f);
        domainAxis.setTickMarkOutsideLength(2f);
        domainAxis.setMinorTickCount(2);
        domainAxis.setMinorTickMarksVisible(true);

        NumberAxis rangeAxis = chart.getRangeAxisAsNumber();
        rangeAxis.setTickMarkInsideLength(2f);
        rangeAxis.setTickMarkOutsideLength(2f);
        rangeAxis.setMinorTickCount(2);
        rangeAxis.setMinorTickMarksVisible(true);
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
