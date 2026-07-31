package pdk.chart;

import org.jspecify.annotations.NonNull;
import pdk.chart.annotations.CategoryAnnotation;
import pdk.chart.api.Layer;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.labels.CategoryItemLabelGenerator;
import pdk.chart.plot.CategoryMarker;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.CategoryItemRenderer;

import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 1:42 PM
 */
public class CategoryChart extends Chart {

    public enum Type {
        LINE,
        AREA,
        BAR,
        BOX,
    }

    protected final CategoryPlot plot_;
    protected CategoryItemRenderer itemRenderer_;

    public CategoryChart() {
        this(null, DEFAULT_TITLE_FONT, true);
    }

    public CategoryChart(String title, Font titleFont, boolean createLegend) {
        super(title, titleFont, new CategoryPlot<>(), createLegend);
        this.plot_ = getCategoryPlot();
    }

    /**
     * Returns the domain axis for the plot.  If the domain axis for this plot
     * is {@code null}, then the method will return the parent plot's
     * domain axis (if there is a parent plot).
     *
     * @return The domain axis ({@code null} permitted).
     */
    public CategoryAxis getDomainAxis() {
        return this.plot_.getDomainAxis();
    }

    /**
     * Sets the range axis for the plot and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param axis the axis ({@code null} permitted).
     */
    public void setRangeAxis(ValueAxis axis) {
        plot_.setRangeAxis(axis);
    }

    /**
     * Sets the flag that enables or disables panning of the plot along
     * the range axes.
     *
     * @param pannable the new flag value.
     */
    public void setRangePannable(boolean pannable) {
        plot_.setRangePannable(pannable);
    }

    /**
     * Sets the orientation for the plot.
     *
     * @param orientation the orientation ({@code null} not permitted).
     */
    public void setOrientation(PlotOrientation orientation) {
        plot_.setOrientation(orientation);
    }

    public void setDefaultRenderer(CategoryItemRenderer renderer) {
        this.itemRenderer_ = renderer;
    }

    /**
     * Sets the default shape.
     *
     * @param shape the shape ({@code null} not permitted).
     */
    public void setDefaultShape(Shape shape) {
        itemRenderer_.setDefaultShape(shape);
    }

    /**
     * Sets the default fill paint and, if requested.
     *
     * @param paint the paint ({@code null} not permitted).
     */
    public void setDefaultFillPaint(Paint paint) {
        itemRenderer_.setDefaultFillPaint(paint);
    }

    /**
     * Sets the default item label generator.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public void setDefaultItemLabelGenerator(CategoryItemLabelGenerator generator) {
        itemRenderer_.setDefaultItemLabelGenerator(generator);
    }

    /**
     * Adds a marker for display against the domain axis and sends a
     * {@link PlotChangeEvent} to all registered listeners.  Typically a marker
     * will be drawn by the renderer as a line perpendicular to the domain
     * axis, however this is entirely up to the renderer.
     *
     * @param marker the marker ({@code null} not permitted).
     * @param layer  the layer (foreground or background) ({@code null}
     *               not permitted).
     */
    public void addDomainMarker(CategoryMarker marker, Layer layer) {
        plot_.addDomainMarker(marker, layer);
    }

    /**
     * Adds an annotation to the plot and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param annotation the annotation.
     */
    public void addAnnotation(@NonNull CategoryAnnotation annotation) {
        plot_.addAnnotation(annotation);
    }
}
