package pdk.chart.demo.fluent;

import pdk.chart.api.Layer;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.fluent.XYChart;
import pdk.chart.fluent.XYChartType;
import pdk.chart.plot.IntervalMarker;
import pdk.chart.plot.Marker;
import pdk.chart.text.TextAnchor;

import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 05 Jun 2026, 9:20 AM
 */
public class XYSeriesDemo3 {
    static void main() {
        XYSeries<String> series = new XYSeries<>("Random Data",
                new double[]{1.0, 5.0, 4.0, 12.5, 17.3, 21.2, 21.9, 25.6, 30.0},
                new double[]{400.2, 294.1, 100.0, 734.4, 453.2, 500.2, Double.NaN, 734.4, 453.2}
        );
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>(series);

        Marker marker = new IntervalMarker(400, 700);
        marker.setLabel("Target Range");
        marker.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        marker.setLabelAnchor(RectangleAnchor.LEFT);
        marker.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        marker.setPaint(new Color(222, 222, 255, 128));

        XYChart xyChart = XYChart.create()
                .axisNames("X", "Y")
                .showLegend(true)
                .addRangeMarker(marker, Layer.BACKGROUND)
                .addDataset(dataset, XYChartType.BAR)
                .barProps(0)
                .addTooltips(true).chart();
        xyChart.show();
    }
}
