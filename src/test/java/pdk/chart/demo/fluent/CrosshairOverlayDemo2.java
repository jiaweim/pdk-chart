package pdk.chart.demo.fluent;

import pdk.chart.api.RectangleAnchor;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.plot.Crosshair;
import pdk.chart.plot.XYPlot;
import pdk.chart.swing.*;

import java.awt.*;

/**
 * In this demo we use the overlay feature on the ChartPanel
 * in conjunction with the ChartMouseListener to update crosshairs
 * in real time as the mouse moves over the chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 04 Jun 2026, 10:27 AM
 */
public class CrosshairOverlayDemo2 {

    static void main() {
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        for (int i = 0; i < 4; ++i) {
            XYSeries<String> series = new XYSeries<>("S" + i);
            for (int x = 0; x < 10; ++x) {
                series.add(x, x + Math.random() * 4.0);
            }
            dataset.addSeries(series);
        }

        XYChart chart = XYChart.create()
                .title("CrosshairOverlayDemo2")
                .dataset(dataset, XYChartType.LINE)
                .showLegend(true)
                .axisNames("X", "Y");

        CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
        Crosshair xCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0.5f));
        xCrosshair.setLabelVisible(true);
        crosshairOverlay.addDomainCrosshair(xCrosshair);

        for (int i = 0; i < 4; i++) {
            Crosshair yCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0.5f));
            yCrosshair.setLabelVisible(true);
            if (i % 2 != 0) {
                yCrosshair.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
            }
            crosshairOverlay.addRangeCrosshair(yCrosshair);
        }

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.addOverlay(crosshairOverlay);
        chartPanel.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent event) {}

            @Override
            public void chartMouseMoved(ChartMouseEvent event) {
                XYPlot xyPlot = chart.getXYPlot();
                ValueAxis xAxis = xyPlot.getDomainAxis();
                double x = xAxis.java2DToValue(event.getTrigger().getX(),
                        chartPanel.getScreenDataArea(), RectangleEdge.BOTTOM);
                crosshairOverlay.getDomainCrosshairs().getFirst().setValue(x);

                for (int i = 0; i < 4; i++) {
                    double y = DatasetUtils.findYValue(xyPlot.getDataset(), i, x);
                    crosshairOverlay.getRangeCrosshairs().get(i).setValue(y);
                }
            }
        });
        ApplicationFrame frame = new ApplicationFrame("");
        frame.setContentPane(chartPanel);
        frame.pack();
        UIUtils.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }
}
