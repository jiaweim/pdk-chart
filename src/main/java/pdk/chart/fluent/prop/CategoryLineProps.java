package pdk.chart.fluent.prop;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.fluent.CategoryXYChart;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.renderer.category.LineAndShapeRenderer;

import java.awt.*;

/**
 * This class is used to configure the properties of LineAndShapeRenderer.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Jun 2026, 12:41 PM
 */
public class CategoryLineProps extends CategoryXYRendererProps {

    protected LineAndShapeRenderer renderer_;

    public CategoryLineProps(CategoryXYChart chart, LineAndShapeRenderer renderer) {
        super(chart, renderer);
        this.renderer_ = renderer;
    }

    /**
     * configure chart to generate tool tips
     *
     * @param addTooltip true if generate tool tips
     * @return this
     */
    public CategoryLineProps addTooltips(boolean addTooltip) {
        if (addTooltip) {
            renderer_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        return this;
    }

    /**
     * Sets the default shape.
     *
     * @param shape the shape.
     */
    public CategoryLineProps defaultShape(@NonNull Shape shape) {
        renderer_.setDefaultShape(shape, false);
        return this;
    }

    /**
     * Sets the shape of a given series.
     *
     * @param shape the shape.
     */
    public CategoryLineProps seriesShape(int series, @NonNull Shape shape) {
        renderer_.setSeriesShape(series, shape, false);
        return this;
    }

    /**
     * Sets the 'shapes visible' flag for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param visible the flag.
     */
    public CategoryLineProps seriesShapesVisible(int series, boolean visible) {
        renderer_.setSeriesShapesVisible(series, visible);
        return this;
    }

    /**
     * Sets the flag that controls whether outlines are drawn for
     * shapes.
     * <p>
     * In some cases, shapes look better if they do NOT have an outline, but
     * this flag allows you to set your own preference.
     *
     * @param flag the flag.
     */
    public CategoryLineProps drawOutlines(boolean flag) {
        renderer_.setDrawOutlines(flag);
        return this;
    }


    /**
     * Sets the outline stroke used for a series.
     * <p>
     * For example, it can be used to set the border width of each data point.
     *
     * @param series the series index (zero-based).
     * @param stroke the stroke ({@code null} permitted).
     */
    public CategoryLineProps seriesOutlineStroke(int series, @Nullable Stroke stroke) {
        renderer_.setSeriesOutlineStroke(series, stroke);
        return this;
    }

    /**
     * Sets the 'shapes filled' flag for a series.
     *
     * @param series the series index (zero-based).
     * @param filled the flag.
     */
    public CategoryLineProps seriesShapesFilled(int series, boolean filled) {
        renderer_.setSeriesShapesFilled(series, filled);
        return this;
    }

    /**
     * Set the line width of a given series
     *
     * @param series series index
     * @param width  line width
     * @return this
     */
    public CategoryLineProps seriesLinesWidth(int series, float width) {
        renderer_.setSeriesStroke(series, new BasicStroke(width));
        return this;
    }

    /**
     * Sets the flag that controls whether the fill paint is used to fill
     * shapes.
     *
     * @param flag the flag.
     */
    public CategoryLineProps useFillPaint(boolean flag) {
        renderer_.setUseFillPaint(flag);
        return this;
    }


    /**
     * Sets the default fill paint.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public CategoryLineProps defaultFillPaint(Paint paint) {
        renderer_.setDefaultFillPaint(paint, false);
        return this;
    }
}
