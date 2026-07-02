package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartUtils;
import pdk.chart.JChart;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
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

    private static Chart createChart(XYDataset<String> dataset) {
        Chart chart = JChart.line(dataset, "X", "Y", "XY Series Demo 1");
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        NumberAxis xAxis2 = new NumberAxis(null);
        plot.setDomainAxis(1, xAxis2);
        NumberAxis yAxis2 = new NumberAxis(null);
        plot.setRangeAxis(1, yAxis2);
        List<Integer> axisIndices = Arrays.asList(0, 1);
        plot.mapDatasetToDomainAxes(0, axisIndices);
        plot.mapDatasetToRangeAxes(0, axisIndices);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static XYDataset<String> createDataset() {
        XYSeries<String> series1 = new XYSeries<>("Random data",
                new double[]{1.0, 5.0, 4.0, 12.5, 17.3, 21.2, 21.9, 25.6, 30.0},
                new double[]{500.2, 694.1, 100.0, 734.4, 453.2, 500.2, Double.NaN, 734.4, 453.2});
        return new XYSeriesCollection<>(series1);
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    static void main() {
        XYSeriesDemo1 demo = new XYSeriesDemo1("XYSeriesDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
