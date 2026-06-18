package pdk.chart.fluent.prop;

import org.jspecify.annotations.Nullable;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.fluent.XYChart;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.labels.XYToolTipGenerator;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;

import java.awt.*;

/**
 * A class for configuring properties of {@link pdk.chart.renderer.xy.XYLineAndShapeRenderer},
 * designed with a fluent style API.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 9:33 AM
 */
public class XYLineAndShapeProps extends XYRendererProps {

    private final XYLineAndShapeRenderer renderer_;

    public XYLineAndShapeProps(XYChart chart, XYLineAndShapeRenderer renderer) {
        super(chart, renderer);

        this.renderer_ = renderer;
    }

    /**
     * Sets the default 'lines visible' flag.
     *
     * @param showLine whether show lines between data points.
     * @return this
     */
    public XYLineAndShapeProps defaultLinesVisible(boolean showLine) {
        renderer_.setDefaultLinesVisible(showLine);
        return this;
    }

    /**
     * Set true to create scatter chart.
     *
     * @param showShape whether show shapes of data points
     * @return this
     */
    public XYLineAndShapeProps defaultShapesVisible(boolean showShape) {
        renderer_.setDefaultShapesVisible(showShape);
        return this;
    }

    /**
     * Sets the default 'shapes filled' flag and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag the flag.
     */
    public XYLineAndShapeProps defaultShapesFilled(boolean flag) {
        renderer_.setDefaultShapesFilled(flag);
        return this;
    }


    /**
     * Set whether lines and shapes of the specified series are visible.
     *
     * @param showLine  whether line is visible.
     * @param showShape whether shape is visible.
     * @return {@link XYLineAndShapeProps}.
     */
    public XYLineAndShapeProps defaultVisible(boolean showLine, boolean showShape) {
        renderer_.setDefaultLinesVisible(showLine);
        renderer_.setDefaultShapesVisible(showShape);
        return this;
    }

    /**
     * Sets the 'lines visible' flag for a series.
     *
     * @param series  the series index (zero-based).
     * @param visible the flag.
     */
    public XYLineAndShapeProps seriesLinesVisible(int series, boolean visible) {
        renderer_.setSeriesLinesVisible(series, visible);
        return this;
    }

    /**
     * Sets the 'shapes visible' flag for a series.
     *
     * @param series  the series index (zero-based).
     * @param visible the flag.
     */
    public XYLineAndShapeProps seriesShapesVisible(int series, boolean visible) {
        renderer_.setSeriesShapesVisible(series, visible);
        return this;
    }

    /**
     * Sets the 'shapes visible' flag for a series.
     *
     * @param series        the series index (zero-based).
     * @param linesVisible  the flag.
     * @param shapesVisible the flag.
     */
    public XYLineAndShapeProps seriesVisible(int series, boolean linesVisible, boolean shapesVisible) {
        renderer_.setSeriesLinesVisible(series, linesVisible);
        renderer_.setSeriesShapesVisible(series, shapesVisible);
        return this;
    }

    /**
     * Sets the shape used for a series.
     *
     * @param series the series index (zero-based).
     * @param shape  the shape.
     */
    public XYLineAndShapeProps seriesShape(int series, @Nullable Shape shape) {
        renderer_.setSeriesShape(series, shape);
        return this;
    }

    /**
     * Sets the paint used for a series.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint.
     */
    public XYLineAndShapeProps seriesPaint(int series, @Nullable Paint paint) {
        renderer_.setSeriesPaint(series, paint);
        return this;
    }

    /**
     * Sets the stroke used for a series.
     *
     * @param series series index
     * @param stroke {@link Stroke}
     * @return this
     */
    public XYLineAndShapeProps seriesStroke(int series, @Nullable Stroke stroke) {
        renderer_.setSeriesStroke(series, stroke);
        return this;
    }

    /**
     * Set the line width of a given series
     *
     * @param series series index
     * @param width  line width
     * @return this
     */
    public XYLineAndShapeProps seriesLineWidth(int series, float width) {
        renderer_.setSeriesStroke(series, new BasicStroke(width));
        return this;
    }

    /**
     * Sets the paint used for a series fill.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint.
     */
    public XYLineAndShapeProps seriesFillPaint(int series, @Nullable Paint paint) {
        renderer_.setSeriesFillPaint(series, paint);
        return this;
    }

    /**
     * Whether the fill paint is used to fill shapes.
     *
     * @param flag the flag.
     */
    public XYLineAndShapeProps useFillPaint(boolean flag) {
        renderer_.setUseFillPaint(flag);
        return this;
    }

    /**
     * Sets the paint used for a series outline.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint.
     */
    public XYLineAndShapeProps seriesOutlinePaint(int series, @Nullable Paint paint) {
        renderer_.setSeriesOutlinePaint(series, paint, false);
        return this;
    }

    /**
     * Whether the outline paint is used to draw shape outlines.
     *
     * @param flag the flag.
     */
    public XYLineAndShapeProps useOutlinePaint(boolean flag) {
        renderer_.setUseOutlinePaint(flag);
        return this;
    }

    /**
     * Sets the flag that controls whether outlines are drawn for shapes.
     * <p>
     * In some cases, shapes look better if they do NOT have an outline, but
     * this flag allows you to set your own preference.
     *
     * @param flag the flag.
     */
    public XYLineAndShapeProps drawOutlines(boolean flag) {
        renderer_.setDrawOutlines(flag);
        return this;
    }

    /**
     * Configure chart to generate tool tips
     *
     * @param addTooltip true if generate tool tips
     * @return this
     */
    public XYLineAndShapeProps addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        return this;
    }

    /**
     * Sets the flag that controls whether each series is drawn as a
     * single path and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param flag the flag.
     */
    public XYLineAndShapeProps drawSeriesLineAsPath(boolean flag) {
        renderer_.setDrawSeriesLineAsPath(flag);
        return this;
    }

    /**
     * Sets the default tool tip generator.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public XYLineAndShapeProps defaultToolTipGenerator(XYToolTipGenerator generator) {
        renderer_.setDefaultToolTipGenerator(generator);
        return this;
    }
}
