package pdk.chart.examples;

import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import java.awt.*;

public class ScatterPlotDemo1 {

    static void main() {
        SampleXYDataset2 dataset = new SampleXYDataset2();
        XYChart chart = XYChart.create()
                .dataset(dataset, XYChartType.LINE)
                .noDataMessage("NO DATA")
                .orientation(PlotOrientation.VERTICAL)
                .title("Scatter Plot Demo 1")
                .showLegend(true)
                .axisNames("X", "Y")
                .pannable(true, true)
                .zeroBaselineVisible(true, true)

                .domainGridlinesStroke(new BasicStroke(0.0f))
                .domainGridlinePaint(Color.BLUE)

                .domainMinorGridlineStroke(new BasicStroke(0.0F))
                .domainMinorGridlinePaint(Color.GRAY)
                .domainMinorGridlinesVisible(true)
                .rangeGridlinesStroke(new BasicStroke(0.0F))
                .rangeGridlinePaint(Color.BLUE)
                .rangeMinorGridlineStroke(new BasicStroke(0.0F))
                .rangeMinorGridlinePaint(Color.GRAY)
                .rangeMinorGridlinesVisible(true)

                .lineAndShapeProps(0)
                .addTooltips(true)
                .seriesOutlinePaint(0, Color.BLACK)
                .useOutlinePaint(true)
                .defaultShapesVisible(true)
                .defaultLinesVisible(false)
                .done()
                .domainAxis()
                .autoRangeIncludesZero(false)
                .tickMarkInsideLength(2.0f)
                .tickMarkOutsideLength(2.0f)
                .minorTickCount(2)
                .minorTickMarksVisible(true)
                .doneXY()
                .rangeAxis().tickMarkInsideLength(2.0f)
                .tickMarkOutsideLength(2.0f)
                .minorTickCount(2)
                .minorTickMarksVisible(true)
                .doneXY();

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setMouseWheelEnabled(true);
        ApplicationFrame frame = new ApplicationFrame("ScatterPlotDemo1.java");
        frame.setContentPane(chartPanel);
        frame.pack();
        UIUtils.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }
}
