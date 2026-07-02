package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYStepAreaRenderer;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;

import javax.swing.*;
import java.awt.*;

public class XYStepAreaRendererDemo1 extends ApplicationFrame {
    public XYStepAreaRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        this.setContentPane(chartPanel);
    }

    private static Chart createChart(XYDataset dataset) {
        Chart chart = JChart.line(dataset, "X", "Y",
                "XYStepAreaRenderer Demo 1");
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        XYStepAreaRenderer renderer = new XYStepAreaRenderer(2);
        renderer.setDataBoundsIncludesVisibleSeriesOnly(false);
        renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        renderer.setDefaultEntityRadius(6);
        plot.setRenderer(renderer);
        return chart;
    }

    private static XYDataset createDataset() {
        XYSeries series1 = new XYSeries("Series 1");
        series1.add(1.0, 3.0);
        series1.add(2.0, 4.0);
        series1.add(3.0, 2.0);
        series1.add(6.0, 3.0);
        XYSeries series2 = new XYSeries("Series 2");
        series2.add(1.0, 7.0);
        series2.add(2.0, 6.0);
        series2.add(3.0, 9.0);
        series2.add(4.0, 5.0);
        series2.add(6.0, 4.0);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    static void main() {
        XYStepAreaRendererDemo1 demo = new XYStepAreaRendererDemo1("XYStepAreaRenderer Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
