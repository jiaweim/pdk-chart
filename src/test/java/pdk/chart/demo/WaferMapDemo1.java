package pdk.chart.demo;

import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.data.general.WaferMapDataset;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.WaferMapPlot;
import pdk.chart.renderer.LookupPaintScale;
import pdk.chart.renderer.WaferMapRenderer;
import pdk.chart.swing.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 11:20 AM
 */
public class WaferMapDemo1 {

    public static WaferMapDataset createDataset() {

        int chipX = 20;
        int chipY = 20;

        WaferMapDataset dataset = new WaferMapDataset(chipX, chipY);

        Random random = new Random(0);
        for (int x = 1; x <= chipX; x++) {
            for (int y = 1; y <= chipY; y++) {
                double value = random.nextDouble() * 100;
                dataset.addValue(value, x, y);
            }
        }

        return dataset;
    }

    public static Chart createChart() {

        WaferMapDataset dataset = createDataset();

        Chart chart = JChart.waferMap(
                dataset,
                "Wafer Map",
                PlotOrientation.VERTICAL, false);

        WaferMapPlot plot = (WaferMapPlot) chart.getPlot();

//        LookupPaintScale scale =
//                new LookupPaintScale(0, 100, Color.BLACK);
//
//        scale.add(0, new Color(49, 54, 149));
//        scale.add(20, new Color(69, 117, 180));
//        scale.add(40, new Color(116, 173, 209));
//        scale.add(60, new Color(171, 217, 233));
//        scale.add(80, new Color(253, 174, 97));
//        scale.add(100, new Color(215, 48, 39));
//
        WaferMapRenderer renderer = new WaferMapRenderer();
//        renderer.setSeriesPaint(0, new Color(49, 54, 149));
//        renderer.setSeriesPaint(1, new Color(69,117,180));
//        renderer.setSeriesPaint(2, new Color(116,173,209));
//        renderer.setSeriesPaint(3, new Color(171,217,233));
//        renderer.setSeriesPaint(4, new Color(224,243,248));
//        renderer.setSeriesPaint(5, new Color(254,224,144));
//        renderer.setSeriesPaint(6, new Color(253,174,97));
//        renderer.setSeriesPaint(7, new Color(244,109,67));
//        renderer.setSeriesPaint(8, new Color(215,48,39));
//        renderer.setSeriesPaint(9, new Color(165,0,38));

        for (int i = 0; i < 35; i++) {
            renderer.setSeriesPaint(i,
                    Color.getHSBColor(i / 35f, 0.9f, 1f));
        }
//
//        renderer.setPaintScale(scale);

        plot.setRenderer(renderer);

        return chart;
    }


    static void main() {
        createChart().show();
    }
}
