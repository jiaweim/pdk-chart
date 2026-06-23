package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.plot.Crosshair;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ChartMouseEvent;
import pdk.chart.swing.ChartMouseListener;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.CrosshairOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CrosshairOverlayDemo1 extends JFrame {
    public static Chart createChart(XYDataset dataset) {
        Chart chart = ChartFactory.createXYLineChart("CrosshairOverlayDemo1", "X", "Y", dataset);
        return chart;
    }

    public static XYDataset createDataset() {
        XYSeries series = new XYSeries("S1");

        for (int x = 0; x < 10; ++x) {
            series.add((double) x, (double) x + Math.random() * (double) 4.0F);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    public CrosshairOverlayDemo1(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CrosshairOverlayDemo1 app = new CrosshairOverlayDemo1("Chart: CrosshairOverlayDemo1.java");
                app.pack();
                app.setVisible(true);
            }
        });
    }

    static class MyDemoPanel extends JPanel implements ChartMouseListener {
        private final ChartPanel chartPanel;
        private final Crosshair xCrosshair;
        private final Crosshair yCrosshair;

        public MyDemoPanel() {
            super(new BorderLayout());
            Chart chart = CrosshairOverlayDemo1.createChart(CrosshairOverlayDemo1.createDataset());
            this.chartPanel = new ChartPanel(chart);
            this.chartPanel.addChartMouseListener(this);
            CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
            this.xCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0.5F));
            this.xCrosshair.setLabelVisible(true);
            this.yCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0.5F));
            this.yCrosshair.setLabelVisible(true);
            crosshairOverlay.addDomainCrosshair(this.xCrosshair);
            crosshairOverlay.addRangeCrosshair(this.yCrosshair);
            this.chartPanel.addOverlay(crosshairOverlay);
            this.add(this.chartPanel);
        }

        public void chartMouseClicked(ChartMouseEvent event) {
        }

        public void chartMouseMoved(ChartMouseEvent event) {
            Rectangle2D dataArea = this.chartPanel.getScreenDataArea();
            Chart chart = event.getChart();
            XYPlot plot = (XYPlot) chart.getPlot();
            ValueAxis xAxis = plot.getDomainAxis();
            double x = xAxis.java2DToValue((double) event.getTrigger().getX(), dataArea, RectangleEdge.BOTTOM);
            if (!xAxis.getRange().contains(x)) {
                x = Double.NaN;
            }

            double y = DatasetUtils.findYValue(plot.getDataset(), 0, x);
            this.xCrosshair.setValue(x);
            this.yCrosshair.setValue(y);
        }
    }
}
