package pdk.chart.fluent;

import org.jspecify.annotations.Nullable;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Chart factory.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 26 Jun 2026, 21:03
 */
public final class JChart {

    private JChart() {}

    /**
     * Create a scatter chart.
     *
     * @param x x values
     * @param y y values
     * @return {@link XYChart}
     */
    public static XYChart scatter(double[] x, double[] y) {
        XYDataset<String> dataset = Data.create("", x, y);
        return XYChart.create(dataset, XYChartType.SCATTER);
    }

    /**
     * Create a scatter chart.
     *
     * @param x x values
     * @param y y values
     * @return {@link XYChart}
     */
    public static XYChart scatter(Double[] x, Double[] y) {
        XYDataset<String> dataset = Data.create("", x, y);
        return XYChart.create(dataset, XYChartType.SCATTER);
    }

    /**
     * Create a bubble chart.
     *
     * @param x     x values.
     * @param y     y values.
     * @param size  data for bubble size.
     * @param color series name for each entry.
     * @return {@link XYChart}
     */
    public static XYChart scatter(Double[] x, Double[] y, Double[] size, @Nullable String[] color) {
        // adjust size to make bubble size
        double minY = Data.getMin(y);
        double maxy = Data.getMax(y);
        double rangeY = maxy - minY;

        double maxZ = Data.getMax(size);
        double scale = Math.max(maxZ, rangeY) * 4;

        Double[] z = new Double[size.length];
        for (int i = 0; i < size.length; i++) {
            z[i] = size[i] / scale;
        }

        Data.XYZDatasetBuilder<String> xyz = Data.xyz();
        Map<String, ArrayList<Double>[]> map = new HashMap<>();
        if (color != null) {
            // create multiple series
            for (int i = 0; i < color.length; i++) {
                ArrayList<Double>[] list = map.get(color[i]);
                if (list == null) {
                    list = new ArrayList[3];
                    list[0] = new ArrayList<>();
                    list[1] = new ArrayList<>();
                    list[2] = new ArrayList<>();
                    map.put(color[i], list);
                }
                list[0].add(x[i]);
                list[1].add(y[i]);
                list[2].add(z[i]);
            }
            for (Map.Entry<String, ArrayList<Double>[]> entry : map.entrySet()) {
                ArrayList<Double>[] value = entry.getValue();
                xyz.addSeries(entry.getKey(), value[0].toArray(new Double[0]),
                        value[1].toArray(new Double[0]),
                        value[2].toArray(new Double[0]));
            }
        } else {
            // only one series
            xyz.addSeries("", x, y, z);
        }

        XYZDataset<String> dataset = xyz.build();
        XYChart chart = XYChart.create(dataset, XYChartType.BUBBLE);

        chart.foregroundAlpha(0.65f);
        chart.rangeAxisNumber().autoRangeIncludesZero(false);
        chart.bubbleRenderer(0)
                .defaultOutlinePaint(Color.WHITE)
                .defaultToolTipGenerator(new StandardXYToolTipGenerator());
        return chart;
    }
}
