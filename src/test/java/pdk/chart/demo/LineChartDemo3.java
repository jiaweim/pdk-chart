package pdk.chart.demo;

import java.awt.Dimension;
import javax.swing.JPanel;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

public class LineChartDemo3 extends ApplicationFrame {
    public LineChartDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    private static XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for(int i = 0; i < 10; ++i) {
            XYSeries series = new XYSeries("S" + i);

            for(int j = 0; j < 10; ++j) {
                series.add((double)j, Math.random() * (double)100.0F);
            }

            dataset.addSeries(series);
        }

        return dataset;
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.line("Line Chart Demo 3", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultShapesFilled(true);
        renderer.setDrawOutlines(true);
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    public static void main(String[] args) {
        LineChartDemo3 demo = new LineChartDemo3("Chart: LineChartDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
