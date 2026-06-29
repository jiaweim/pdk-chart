package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.ChartUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class XYSeriesDemo1 extends ApplicationFrame {
    public XYSeriesDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.line("XY Series Demo 1", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        NumberAxis xAxis2 = new NumberAxis((String) null);
        plot.setDomainAxis(1, xAxis2);
        NumberAxis yAxis2 = new NumberAxis((String) null);
        plot.setRangeAxis(1, yAxis2);
        List<Integer> axisIndices = Arrays.asList(0, 1);
        plot.mapDatasetToDomainAxes(0, axisIndices);
        plot.mapDatasetToRangeAxes(0, axisIndices);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset createDataset() {
        XYSeries series = new XYSeries("Random Data");
        series.add((double) 1.0F, 500.2);
        series.add((double) 5.0F, 694.1);
        series.add((double) 4.0F, (double) 100.0F);
        series.add((double) 12.5F, 734.4);
        series.add(17.3, 453.2);
        series.add(21.2, 500.2);
        series.add(21.9, (Number) null);
        series.add(25.6, 734.4);
        series.add((double) 30.0F, 453.2);
        return new XYSeriesCollection(series);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        XYSeriesDemo1 demo = new XYSeriesDemo1("Chart: XYSeriesDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
