package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
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
        XYSeries<String> series = new XYSeries<>("Random Data",
                new double[]{1.0, 5.0, 4.0, 12.5, 17.3, 21.2, 21.9, 25.6, 30.0},
                new double[]{400.2, 294.1, 100.0, 734.4, 453.2, 500.2, Double.NaN, 734.4, 453.2}
        );
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>(series);
        return dataset;
    }

    private static Chart createChart(IntervalXYDataset dataset) {
        Chart chart = JChart.bar("XY Series Demo 3", "X", false, "Y",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        IntervalMarker target = new IntervalMarker(400.0, 700.0);
        target.setLabel("Target Range");
        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
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

    static void main() {
        XYSeriesDemo3 demo = new XYSeriesDemo3("Chart: XYSeriesDemo3.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
