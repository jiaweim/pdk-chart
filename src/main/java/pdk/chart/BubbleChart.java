package pdk.chart;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.StandardXYZToolTipGenerator;
import pdk.chart.model.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBubbleRenderer;
import pdk.chart.urls.StandardXYZURLGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A bubble chart where the size of each data point reflects a third
 * dimension (Z value).
 * <p>
 * The chart is backed by an {@link XYPlot} with numeric axes and an
 * {@link XYBubbleRenderer}.  The domain and range axes are
 * {@link NumberAxis} instances with {@code autoRangeIncludesZero}
 * disabled by default.
 * <p>
 * <b>Important:</b> This chart requires an {@link XYZDataset} (or a
 * dataset that extends it).  The dataset's Z values are used to
 * determine bubble diameters.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 8:31 AM
 */
public class BubbleChart extends XYChart {

    private XYBubbleRenderer renderer1_;

    @Override
    protected void initRenderer() {}

    /**
     * Returns the bubble renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public XYBubbleRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Full constructor – every option is exposed.
     *
     * @param scaleType   the bubble scaling strategy (must not be
     *                    {@code null})
     * @param dataset     the dataset (must implement {@link XYZDataset};
     *                    {@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tooltips
     * @param urls        {@code true} to generate URLs for data points
     */
    public BubbleChart(XYBubbleRenderer.ScaleType scaleType, XYZDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);
        renderer1_ = new XYBubbleRenderer(scaleType);
        renderer0_ = renderer1_;

        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);

        plot_.setDomainAxis(xAxis);
        plot_.setRangeAxis(yAxis);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);
        plot_.setOrientation(orientation);

        if (tooltips) {
            renderer1_.setDefaultToolTipGenerator(new StandardXYZToolTipGenerator());
        }
        if (urls) {
            renderer1_.setURLGenerator(new StandardXYZURLGenerator());
        }
        JChart.applyCurrentTheme(this);
    }

    /**
     * Convenience constructor that creates a bubble chart from three
     * arrays of primitive {@code double} values.
     * <p>
     * Defaults: scale on range axis, vertical orientation, no legend,
     * tooltips enabled, no URLs, and all points belong to a single
     * unnamed series.
     *
     * @param x    the x-coordinates
     * @param y    the y-coordinates
     * @param size the bubble sizes (Z values)
     */
    public BubbleChart(double[] x, double[] y, double[] size) {
        this(XYBubbleRenderer.ScaleType.SCALE_ON_RANGE_AXIS, Data.createXYZ("", x, y, size),
                null, null, null,
                PlotOrientation.VERTICAL, false, true, false);
    }

    /**
     * Advanced convenience constructor for creating a multi‑series
     * bubble chart with automatic scaling of Z values.
     * <p>
     * The raw {@code size} values are normalised so that the largest
     * bubble does not dominate the plot.  If {@code seriesNames} is
     * {@code null}, all data points are placed in a single default
     * series.
     *
     * @param x           the x-coordinates
     * @param y           the y-coordinates
     * @param size        the raw bubble sizes (will be normalised)
     * @param seriesNames series names for each point, or {@code null}
     *                    for a single series
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     */
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
            renderer1_.setSeriesOutlinePaint(i, Color.WHITE);
        }
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a bubble chart with vertical orientation, legend and
     * tooltips enabled, no URLs, and scaling on the range axis.
     *
     * @param dataset    the dataset ({@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param yAxisLabel the range axis label ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     */
    public BubbleChart(XYZDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        this(XYBubbleRenderer.ScaleType.SCALE_ON_RANGE_AXIS, dataset, xAxisLabel, yAxisLabel,
                title, PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a bubble chart with the given scaling and orientation,
     * legend, tooltips, but no URLs.
     *
     * @param scaleType   the bubble scaling strategy
     * @param dataset     the dataset ({@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable tooltips
     */
    public BubbleChart(XYBubbleRenderer.ScaleType scaleType, XYZDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(scaleType, dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }


    /**
     * Sets the outline paint for a series.
     *
     * @param series the series index (zero-based)
     * @param paint  the outline paint ({@code null} permitted)
     * @param notify {@code true} to send a
     *               {@link RendererChangeEvent}, {@code false} otherwise
     */
    public void setSeriesOutlinePaint(int series, Paint paint, boolean notify) {
        renderer1_.setSeriesOutlinePaint(series, paint, notify);
    }

    /**
     * Sets the visibility of a series.
     *
     * @param series  the series index (zero-based)
     * @param visible {@code null} or {@code Boolean.FALSE} to hide,
     *                {@code Boolean.TRUE} to show
     */
    public void setSeriesVisible(int series, Boolean visible) {
        renderer1_.setSeriesVisible(series, visible);
    }

    /**
     * Returns a boolean that indicates whether the specified series
     * should be drawn (this is typically used to hide an entire series).
     *
     * @param series the series index.
     * @return A boolean.
     */
    public boolean isSeriesVisible(int series) {
        return renderer1_.isSeriesVisible(series);
    }
}
