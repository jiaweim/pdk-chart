package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.api.Layer;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.IntervalMarker;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ApplicationFrame;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.UIUtils;
import pdk.chart.text.TextAnchor;

import javax.swing.*;
import java.awt.*;

public class XYSeriesDemo3 extends ApplicationFrame {
    public XYSeriesDemo3(String title) {
        super(title);
        IntervalXYDataset dataset = createDataset();
        Chart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static IntervalXYDataset createDataset() {
        XYSeries series = new XYSeries("Random Data");
        series.add((double) 1.0F, 400.2);
        series.add((double) 5.0F, 294.1);
        series.add((double) 4.0F, (double) 100.0F);
        series.add((double) 12.5F, 734.4);
        series.add(17.3, 453.2);
        series.add(21.2, 500.2);
        series.add(21.9, (Number) null);
        series.add(25.6, 734.4);
        series.add((double) 30.0F, 453.2);
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = ChartFactory.bar("XY Series Demo 3", "X", false, "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        IntervalMarker target = new IntervalMarker((double) 400.0F, (double) 700.0F);
        target.setLabel("Target Range");
        target.setLabelFont(new Font("SansSerif", 2, 11));
        target.setLabelAnchor(RectangleAnchor.LEFT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setPaint(new Color(222, 222, 255, 128));
        plot.addRangeMarker(target, Layer.BACKGROUND);
        return chart;
    }

    public static JPanel createDemoPanel() {
        Chart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        XYSeriesDemo3 demo = new XYSeriesDemo3("Chart: XYSeriesDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
