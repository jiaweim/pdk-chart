package pdk.chart;

import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.color.JColorSequential;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.legend.PaintScaleLegend;
import pdk.chart.model.Data;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.GradientPaintScale;
import pdk.chart.renderer.xy.XYShapeRenderer;

import java.awt.*;

/**
 * A scatter chart that maps a third dimension (Z) to point colours using a
 * {@link GradientPaintScale} and displays a {@link PaintScaleLegend}.
 * <p>
 * The chart is backed by an {@link XYPlot} with numeric axes.  The
 * renderer is an {@link XYShapeRenderer} that applies the colour scale
 * to each data point according to its Z value.  A color bar legend is
 * added as a subtitle on the right side of the chart.
 * <p>
 * <b>Colour array requirement:</b> if a custom color array is supplied
 * it must contain at least two colors; a single‑color array will cause
 * a division by zero when building the paint scale steps.  If {@code null}
 * is passed, the default {@link JColorSequential#Plasma()} palette is used.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 06 Aug 2026, 9:10 AM
 */
public class ScatterChartV2 extends XYChart {

    protected XYShapeRenderer renderer1_;

    /**
     * Initializes the renderer to an {@link XYShapeRenderer} and sets it
     * as the primary renderer.
     */
    @Override
    protected void initRenderer() {
        renderer1_ = new XYShapeRenderer();
        renderer0_ = renderer1_;
    }

    /**
     * Returns the shape renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public XYShapeRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Constructs a scatter chart with Z‑value coloring.
     *
     * @param dataset   the dataset providing X, Y, and Z values
     *                  (must not be {@code null})
     * @param xAxisName the label for the domain (X) axis
     *                  ({@code null} permitted)
     * @param yAxisName the label for the range (Y) axis
     *                  ({@code null} permitted)
     * @param zAxisName the label for the color bar (Z) axis
     *                  ({@code null} permitted)
     * @param colors    color stops for the paint scale; if
     *                  {@code null} the {@link JColorSequential#Plasma()}
     *                  palette is used.  Must contain at least two
     *                  colors.
     * @param title     the chart title ({@code null} permitted)
     * @throws ArithmeticException if {@code colors} is non‑{@code null}
     *                             and contains fewer than two entries
     */
    public ScatterChartV2(XYZDataset dataset, String xAxisName, String yAxisName,
            String zAxisName, Color[] colors, String title) {
        super(title, false);

        NumberAxis xAxis = new NumberAxis(xAxisName);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisName);

        double zMin = Data.getMinZ(dataset);
        double zMax = Data.getMaxZ(dataset);

        if (colors == null) {
            colors = JColorSequential.Plasma();
        }
        GradientPaintScale ps = new GradientPaintScale(zMin, zMax, Color.GRAY);
        double stepSize = (zMax - zMin) / (colors.length - 1);
        for (int i = 0; i < colors.length; i++) {
            ps.add(zMin + i * stepSize, colors[i]);
        }
        renderer1_.setPaintScale(ps);

        NumberAxis zAxis = new NumberAxis(zAxisName);
        zAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        PaintScaleLegend legend1 = new PaintScaleLegend(ps, zAxis);
        legend1.setPosition(RectangleEdge.RIGHT);
        legend1.setAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        addSubtitle(legend1);

        plot_.setDomainAxis(xAxis);
        plot_.setRangeAxis(yAxis);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);

        JChart.applyCurrentTheme(this);
    }

    /**
     * Constructs a scatter chart from arrays of x, y and z values.
     * The arrays must be non‑null and have the same length.
     *
     * @param x         the array of x‑coordinates (must not be {@code null})
     * @param y         the array of y‑coordinates (must not be {@code null},
     *                  same length as {@code x})
     * @param z         the array of z (colour) values (must not be {@code null},
     *                  same length as {@code x})
     * @param xAxisName the label for the domain (X) axis
     *                  ({@code null} permitted)
     * @param yAxisName the label for the range (Y) axis
     *                  ({@code null} permitted)
     * @param zAxisName the label for the colour bar (Z) axis
     *                  ({@code null} permitted)
     * @param colors    colour stops for the paint scale; if
     *                  {@code null} the {@link JColorSequential#Plasma()}
     *                  palette is used.  Must contain at least two
     *                  colours.
     * @param title     the chart title ({@code null} permitted)
     * @throws IllegalArgumentException if the input arrays have different lengths
     * @throws ArithmeticException      if {@code colors} is non‑{@code null}
     *                                  and contains fewer than two entries
     */
    public ScatterChartV2(Double[] x, Double[] y, Double[] z, String xAxisName, String yAxisName,
            String zAxisName, Color[] colors, String title) {
        this(Data.createXYZ(x, y, z), xAxisName, yAxisName, zAxisName, colors, title);
    }

    /**
     * Constructs a scatter chart from arrays of x, y and z values, with no
     * chart title.
     *
     * @param x         the array of x‑coordinates (must not be {@code null})
     * @param y         the array of y‑coordinates (must not be {@code null},
     *                  same length as {@code x})
     * @param z         the array of z (colour) values (must not be {@code null},
     *                  same length as {@code x})
     * @param xAxisName the label for the domain (X) axis
     *                  ({@code null} permitted)
     * @param yAxisName the label for the range (Y) axis
     *                  ({@code null} permitted)
     * @param zAxisName the label for the colour bar (Z) axis
     *                  ({@code null} permitted)
     * @param colors    colour stops for the paint scale; if
     *                  {@code null} the {@link JColorSequential#Plasma()}
     *                  palette is used.  Must contain at least two
     *                  colours.
     * @throws IllegalArgumentException if the input arrays have different lengths
     * @throws ArithmeticException      if {@code colors} is non‑{@code null}
     *                                  and contains fewer than two entries
     */
    public ScatterChartV2(Double[] x, Double[] y, Double[] z, String xAxisName, String yAxisName,
            String zAxisName, Color[] colors) {
        this(Data.createXYZ(x, y, z), xAxisName, yAxisName, zAxisName, colors, null);
    }

    /**
     * Constructs a scatter chart from arrays of x, y and z values, using
     * the default {@link JColorSequential#Plasma()} colour palette and no
     * chart title.
     *
     * @param x         the array of x‑coordinates (must not be {@code null})
     * @param y         the array of y‑coordinates (must not be {@code null},
     *                  same length as {@code x})
     * @param z         the array of z (colour) values (must not be {@code null},
     *                  same length as {@code x})
     * @param xAxisName the label for the domain (X) axis
     *                  ({@code null} permitted)
     * @param yAxisName the label for the range (Y) axis
     *                  ({@code null} permitted)
     * @param zAxisName the label for the colour bar (Z) axis
     *                  ({@code null} permitted)
     * @throws IllegalArgumentException if the input arrays have different lengths
     */
    public ScatterChartV2(Double[] x, Double[] y, Double[] z, String xAxisName, String yAxisName,
            String zAxisName) {
        this(x, y, z, xAxisName, yAxisName, zAxisName, null);
    }


}
