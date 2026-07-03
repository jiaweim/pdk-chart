package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class GridBandDemo1 extends ApplicationFrame {
    public GridBandDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.scatter(dataset, "X", "Y", "Grid Band Demo 1");
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA");
        plot.setRangeZeroBaselineVisible(true);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainTickBandPaint(new Color(0, 100, 0, 50));
        plot.setRangeTickBandPaint(new Color(0, 100, 0, 50));
        return chart;
    }

    public static JPanel createDemoPanel() {
        XYSeries series = new XYSeries("Random Data");

        for (int i = 0; i < 100; ++i) {
            series.add(Math.random() + (double) 1.0F, Math.random() + (double) 1.0F);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        return chartPanel;
    }

    public static void main(String[] args) {
        GridBandDemo1 demo = new GridBandDemo1("Chart: GridBandDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
