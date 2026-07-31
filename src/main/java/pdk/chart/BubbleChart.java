package pdk.chart;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.StandardXYZToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBubbleRenderer;
import pdk.chart.urls.StandardXYZURLGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static pdk.chart.util.Args.nullNotPermitted;

/**
 * Bubble chart.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 8:31 AM
 */
public class BubbleChart extends XYChart {

    public enum ScaleType {
        /**
         * Default bubble scaling mode: Convert Z separately via X-axis and Y-axis unit-to-pixel ratios.
         * Horizontal width and vertical height of the bubble may differ, resulting in an oval (ellipse) shape.
         * Adjusting either X or Y axis bounds will change bubble appearance.
         */
        SCALE_ON_BOTH_AXES,
        /**
         * Bubble scaling mode: Convert Z value to pixel size using domain (X) axis scale only.
         * The vertical pixel size equals horizontal pixel size, bubble always renders as a perfect circle.
         * Resizing Y axis has no impact on bubble dimension.
         */
        SCALE_ON_DOMAIN_AXIS,
        /**
         * Bubble scaling mode: Convert Z value to pixel size using range (Y) axis scale only.
         * The horizontal pixel size equals vertical pixel size, bubble always renders as a perfect circle.
         * Resizing X axis has no impact on bubble dimension.
         */
        SCALE_ON_RANGE_AXIS
    }

    private final XYBubbleRenderer renderer;

    public BubbleChart(ScaleType scaleType, String title, Font titleFont, boolean createLegend) {
        super(title, titleFont, createLegend);
        renderer = new XYBubbleRenderer(scaleType.ordinal());
    }

    public BubbleChart(String title, Font titleFont, boolean createLegend) {
        this(ScaleType.SCALE_ON_RANGE_AXIS, title, titleFont, createLegend);
    }

    public BubbleChart(double[] x, double[] y, double[] size) {
        this(ScaleType.SCALE_ON_RANGE_AXIS, Data.createXYZ("", x, y, size),
                null, null, null,
                PlotOrientation.VERTICAL, false, true, false);
    }

    public BubbleChart(Double[] x, Double[] y, Double[] size, String[] seriesNames,
            String xAxisLabel, String yAxisLabel) {
        this(null, xAxisLabel, yAxisLabel, null);

        double rangeY = Data.getRange(y);
        double maxZ = Data.getMax(size);
        double scale = Math.max(maxZ, rangeY) * 4;

        Double[] z = new Double[size.length];
        for (int i = 0; i < size.length; i++) {
            z[i] = size[i] / scale;
        }

        Data.XYZDatasetBuilder<String> xyz = Data.xyz();
        Map<String, ArrayList<Double>[]> map = new HashMap<>();
        if (seriesNames != null) {
            // create multiple series
            for (int i = 0; i < seriesNames.length; i++) {
                ArrayList<Double>[] list = map.get(seriesNames[i]);
                if (list == null) {
                    list = new ArrayList[3];
                    // x, y, z
                    list[0] = new ArrayList<>();
                    list[1] = new ArrayList<>();
                    list[2] = new ArrayList<>();
                    map.put(seriesNames[i], list);
                }
                list[0].add(x[i]);
                list[1].add(y[i]);
                list[2].add(z[i]);
            }
            for (Map.Entry<String, ArrayList<Double>[]> entry : map.entrySet()) {
                ArrayList<Double>[] value = entry.getValue();
                xyz.addSeries(entry.getKey(), value[0].toArray(new Double[0]), value[1].toArray(new Double[0]), value[2].toArray(new Double[0]));
            }
        } else {
            // only one series
            xyz.addSeries("", x, y, z);
        }

        XYZDataset<String> dataset = xyz.build();
        plot_.setDataset(dataset);
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            renderer.setSeriesOutlinePaint(i, Color.WHITE);
        }
        JChartUtils.applyCurrentTheme(this);
    }

    /**
     * Creates a bubble chart with default settings.  The chart is composed of
     * an {@link XYPlot}, with a {@link NumberAxis} for the domain axis,
     * a {@link NumberAxis} for the range axis, and an {@link XYBubbleRenderer}
     * to draw the data items.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     */
    public BubbleChart(XYZDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        this(ScaleType.SCALE_ON_RANGE_AXIS, dataset, xAxisLabel, yAxisLabel, title, PlotOrientation.VERTICAL,
                true, true, false);
    }

    /**
     * Creates a bubble chart with default settings.  The chart is composed of
     * an {@link XYPlot}, with a {@link NumberAxis} for the domain axis,
     * a {@link NumberAxis} for the range axis, and an {@link XYBubbleRenderer}
     * to draw the data items.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     */
    public BubbleChart(ScaleType scaleType, XYZDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(scaleType, dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a bubble chart with default settings.  The chart is composed of
     * an {@link XYPlot}, with a {@link NumberAxis} for the domain axis,
     * a {@link NumberAxis} for the range axis, and an {@link XYBubbleRenderer}
     * to draw the data items.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     */
    public BubbleChart(ScaleType scaleType, XYZDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        this(scaleType, title, DEFAULT_TITLE_FONT, legend);
        nullNotPermitted(orientation, "plotOrientation");

        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);

        plot_.setDomainAxis(xAxis);
        plot_.setRangeAxis(yAxis);
        plot_.setRenderer(renderer);
        plot_.setDataset(dataset);
        plot_.setOrientation(orientation);

        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYZToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYZURLGenerator());
        }
        JChartUtils.applyCurrentTheme(this);
    }

    /**
     * Sets the paint used to draw the outline for a series and, if requested,
     * sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     * @param notify notify listeners?
     */
    public void setSeriesOutlinePaint(int series, Paint paint, boolean notify) {
        renderer.setSeriesOutlinePaint(series, paint, notify);
    }

    /**
     * Sets the paint used for a series and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     */
    public void setSeriesPaint(int series, Paint paint) {
        renderer.setSeriesPaint(series, paint);
    }

    /**
     * Sets the flag that controls whether a series is visible and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param visible the flag ({@code null} permitted).
     */
    public void setSeriesVisible(int series, Boolean visible) {
        renderer.setSeriesVisible(series, visible);
    }

    /**
     * Returns a boolean that indicates whether the specified series
     * should be drawn (this is typically used to hide an entire series).
     *
     * @param series the series index.
     * @return A boolean.
     */
    public boolean isSeriesVisible(int series) {
        return renderer.isSeriesVisible(series);
    }
}
