package pdk.chart.demo;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import pdk.chart.ChartFactory;
import pdk.chart.Chart;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.ValueAxis;
import pdk.chart.plot.Crosshair;
import pdk.chart.plot.XYPlot;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.swing.ChartMouseEvent;
import pdk.chart.swing.ChartMouseListener;
import pdk.chart.swing.ChartPanel;
import pdk.chart.swing.CrosshairOverlay;

public class CrosshairOverlayDemo2 extends JFrame {
    public CrosshairOverlayDemo2(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CrosshairOverlayDemo2 app = new CrosshairOverlayDemo2("Chart: CrosshairOverlayDemo2.java");
                app.pack();
                app.setVisible(true);
            }
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

            for(int i = 0; i < 4; ++i) {
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

        private Chart createChart(XYDataset dataset) {
            Chart chart = ChartFactory.line("CrosshairOverlayDemo2", "X", "Y", dataset);
            return chart;
        }

        private XYDataset createDataset() {
            XYSeriesCollection dataset = new XYSeriesCollection();

            for(int i = 0; i < 4; ++i) {
                XYSeries series = new XYSeries("S" + i);

                for(int x = 0; x < 10; ++x) {
                    series.add((double)x, (double)x + Math.random() * (double)4.0F);
                }

                dataset.addSeries(series);
            }

            return dataset;
        }

        public void chartMouseClicked(ChartMouseEvent event) {
        }

        public void chartMouseMoved(ChartMouseEvent event) {
            Rectangle2D dataArea = this.chartPanel.getScreenDataArea();
            Chart chart = event.getChart();
            XYPlot plot = (XYPlot)chart.getPlot();
            ValueAxis xAxis = plot.getDomainAxis();
            double x = xAxis.java2DToValue((double)event.getTrigger().getX(), dataArea, RectangleEdge.BOTTOM);
            this.xCrosshair.setValue(x);

            for(int i = 0; i < 4; ++i) {
                double y = DatasetUtils.findYValue(plot.getDataset(), i, x);
                this.yCrosshairs[i].setValue(y);
            }

        }
    }
}
