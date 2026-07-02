package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.Data;
import pdk.chart.JChart;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.plot.Crosshair;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.ChartMouseEvent;
import pdk.chart.swing.ChartMouseListener;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.CrosshairOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * In this demo we use the overlay feature on the ChartPanel
 * in conjunction with the ChartMouseListener to update crosshairs
 * in real time as the mouse moves over the chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Jun 2026, 10:27 AM
 */
public class CrosshairOverlayDemo2 extends JFrame {
    public CrosshairOverlayDemo2(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            CrosshairOverlayDemo2 app = new CrosshairOverlayDemo2("CrosshairOverlayDemo2.java");
            app.pack();
            app.setVisible(true);
        });
    }

    static class MyDemoPanel extends JPanel implements ChartMouseListener {

        private static final int SERIES_COUNT = 4;
        private ChartPanel chartPanel;
        private Crosshair xCrosshair;
        private Crosshair[] yCrosshairs;

        public MyDemoPanel() {
            super(new BorderLayout());
            Chart chart = this.createChart(this.createDataset());
            this.chartPanel = new ChartPanel(chart);
            this.chartPanel.addChartMouseListener(this);
            CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
            this.xCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0.5F));
            this.xCrosshair.setLabelVisible(true);
            crosshairOverlay.addDomainCrosshair(this.xCrosshair);
            this.yCrosshairs = new Crosshair[4];

            for (int i = 0; i < SERIES_COUNT; ++i) {
                this.yCrosshairs[i] = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0.5F));
                this.yCrosshairs[i].setLabelVisible(true);
                if (i % 2 != 0) {
                    this.yCrosshairs[i].setLabelAnchor(RectangleAnchor.TOP_RIGHT);
                }

                crosshairOverlay.addRangeCrosshair(this.yCrosshairs[i]);
            }

            this.chartPanel.addOverlay(crosshairOverlay);
            this.add(this.chartPanel);
        }

        private Chart createChart(XYDataset<String> dataset) {
            return JChart.line(dataset, "X", "Y", "CrosshairOverlayDemo2");
        }

        private XYDataset<String> createDataset() {
            Data.XYDatasetBuilder<String> xy = Data.xy();
            for (int i = 0; i < 4; ++i) {
                XYSeries<String> series = new XYSeries<>("S" + i);
                for (int x = 0; x < 10; ++x) {
                    series.add(x, x + Math.random() * 4.0);
                }
                xy.addSeries(series);
            }

            return xy.build();
        }

        public void chartMouseClicked(ChartMouseEvent event) {
        }

        public void chartMouseMoved(ChartMouseEvent event) {
            Rectangle2D dataArea = this.chartPanel.getScreenDataArea();
            Chart chart = event.getChart();
            XYPlot plot = chart.getXYPlot();
            ValueAxis xAxis = plot.getDomainAxis();
            double x = xAxis.java2DToValue(event.getTrigger().getX(), dataArea, RectangleEdge.BOTTOM);
            this.xCrosshair.setValue(x);

            for (int i = 0; i < 4; ++i) {
                double y = DatasetUtils.findYValue(plot.getDataset(), i, x);
                this.yCrosshairs[i].setValue(y);
            }
        }
    }
}
