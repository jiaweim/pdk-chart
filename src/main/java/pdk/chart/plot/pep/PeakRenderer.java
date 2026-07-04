package pdk.chart.plot.pep;

import pdk.chart.api.PublicCloneable;
import pdk.chart.api.RectangleEdge;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.Range;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.entity.EntityCollection;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.util.ShapeUtils;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.XYItemLabelGenerator;
import pdk.chart.plot.CrosshairState;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.PlotRenderingInfo;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.AbstractXYItemRenderer;
import pdk.chart.renderer.xy.XYItemRenderer;
import pdk.chart.renderer.xy.XYItemRendererState;
import pdk.chart.text.TextUtils;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Objects;

/**
 * A renderer that draws a line connecting the start and end Y values for an
 * {@link XYPlot}.
 */
public class PeakRenderer extends AbstractXYItemRenderer
        implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = -2951586537224143260L;

    /**
     * An additional item label generator.  If this is non-null, the item
     * label generated will be displayed near the lower y-value at the
     * position given by getNegativeItemLabelPosition().
     *
     * @since 1.0.10
     */
    private XYItemLabelGenerator additionalItemLabelGenerator;

    /**
     * The default constructor.
     */
    public PeakRenderer() {
        super();
        this.additionalItemLabelGenerator = null;
    }

    /**
     * Returns the generator for the item labels that appear near the lower
     * y-value.
     *
     * @return The generator (possibly {@code null}).
     * @see #setAdditionalItemLabelGenerator(XYItemLabelGenerator)
     * @since 1.0.10
     */
    public XYItemLabelGenerator getAdditionalItemLabelGenerator() {
        return this.additionalItemLabelGenerator;
    }

    /**
     * Sets the generator for the item labels that appear near the lower
     * y-value and sends a {@link RendererChangeEvent} to all registered
     * listeners.  If this is set to {@code null}, no item labels will be
     * drawn.
     *
     * @param generator the generator ({@code null} permitted).
     * @see #getAdditionalItemLabelGenerator()
     * @since 1.0.10
     */
    public void setAdditionalItemLabelGenerator(
            XYItemLabelGenerator generator) {
        this.additionalItemLabelGenerator = generator;
        fireChangeEvent();
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     *
     * @param dataset the dataset ({@code null} permitted).
     * @return The range ({@code null} if the dataset is {@code null} or empty).
     */
    @Override
    public Range findRangeBounds(XYDataset dataset) {
        return findRangeBounds(dataset, true);
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2             the graphics device.
     * @param state          the renderer state.
     * @param dataArea       the area within which the plot is being drawn.
     * @param info           collects information about the drawing.
     * @param plot           the plot (can be used to obtain standard color
     *                       information etc).
     * @param domainAxis     the domain axis.
     * @param rangeAxis      the range axis.
     * @param dataset        the dataset.
     * @param series         the series index (zero-based).
     * @param item           the item index (zero-based).
     * @param crosshairState crosshair information for the plot
     *                       ({@code null} permitted).
     * @param pass           the pass index (ignored here).
     */
    @Override
    public void drawItem(Graphics2D g2, XYItemRendererState state,
            Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot,
            ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset,
            int series, int item, CrosshairState crosshairState, int pass) {

        // do nothing if item is not visible
        if (!getItemVisible(series, item)) {
            return;
        }

        // setup for collecting optional entity info...
        EntityCollection entities = null;
        if (info != null) {
            entities = info.getOwner().getEntityCollection();
        }

        double x = dataset.getXValue(series, item);
        double y = dataset.getYValue(series, item);

        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();

        double xx = domainAxis.valueToJava2D(x, dataArea, xAxisLocation);
        double yyLow = rangeAxis.valueToJava2D(0, dataArea, yAxisLocation);
        double yyHigh = rangeAxis.valueToJava2D(y, dataArea, yAxisLocation);

        Paint p = getItemPaint(series, item);
        Stroke s = getItemStroke(series, item);

        Line2D line = null;
        PlotOrientation orientation = plot.getOrientation();
        if (orientation == PlotOrientation.HORIZONTAL) {
            line = new Line2D.Double(yyLow, xx, yyHigh, xx);
        } else if (orientation == PlotOrientation.VERTICAL) {
            line = new Line2D.Double(xx, yyLow, xx, yyHigh);
        } else {
            throw new IllegalStateException();
        }
//        System.out.println(yyLow + "\t" + yyHigh + "\t" + dataArea.getMaxY());
        g2.setPaint(p);
        g2.setStroke(s);
        g2.draw(line);

        // for item labels, we have a special case because there is the
        // possibility to draw (a) the regular item label near to just the
        // upper y-value, or (b) the regular item label near the upper y-value
        // PLUS an additional item label near the lower y-value.
        if (isItemLabelVisible(series, item)) {
            drawItemLabel(g2, orientation, dataset, series, item, xx, yyHigh,
                    false);
            drawAdditionalItemLabel(g2, orientation, dataset, series, item,
                    xx, yyLow);
        }

//        g2.setPaint(Color.RED);
//        g2.fill(new Rectangle2D.Double(xx - 1, yyLow - 1, 3, 3));

        // add an entity for the item...
        Shape hotspot = ShapeUtils.createLineRegion(line, 4.0f);
        if (entities != null && hotspot.intersects(dataArea)) {
            addEntity(entities, hotspot, dataset, series, item, 0.0, 0.0);
        }
    }

    /**
     * Draws an item label.
     *
     * @param g2          the graphics device.
     * @param orientation the orientation.
     * @param dataset     the dataset.
     * @param series      the series index (zero-based).
     * @param item        the item index (zero-based).
     * @param x           the x coordinate (in Java2D space).
     * @param y           the y coordinate (in Java2D space).
     */
    private void drawAdditionalItemLabel(Graphics2D g2,
            PlotOrientation orientation, XYDataset dataset, int series,
            int item, double x, double y) {

        if (this.additionalItemLabelGenerator == null) {
            return;
        }

        Font labelFont = getItemLabelFont(series, item);
        Paint paint = getItemLabelPaint(series, item);
        g2.setFont(labelFont);
        g2.setPaint(paint);
        String label = this.additionalItemLabelGenerator.generateLabel(dataset,
                series, item);

        ItemLabelPosition position = getNegativeItemLabelPosition(series, item);
        Point2D anchorPoint = calculateLabelAnchorPoint(
                position.getItemLabelAnchor(), x, y, orientation);
        TextUtils.drawRotatedString(label, g2,
                (float) anchorPoint.getX(), (float) anchorPoint.getY(),
                position.getTextAnchor(), position.getAngle(),
                position.getRotationAnchor());
    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PeakRenderer)) {
            return false;
        }
        PeakRenderer that = (PeakRenderer) obj;
        if (!Objects.equals(this.additionalItemLabelGenerator, that.additionalItemLabelGenerator)) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if the renderer cannot be cloned.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Sets the paint used for a series fill and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     */
    public PeakRenderer seriesFillPaint(int series, Paint paint) {
        setSeriesFillPaint(series, paint);
        return this;
    }

    /**
     * Sets the stroke for a series and, if requested, sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param stroke the stroke ({@code null} permitted).
     */
    public PeakRenderer seriesStroke(int series, Stroke stroke) {
        setSeriesStroke(series, stroke);
        return this;
    }

    /**
     * Sets the base flag that controls whether item labels are visible,
     * and sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param visible the flag.
     */
    public PeakRenderer defaultItemLabelsVisible(boolean visible) {
        setDefaultItemLabelsVisible(visible);
        return this;
    }

    /**
     * Sets the default item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator the generator ({@code null} permitted).
     */
    public PeakRenderer defaultItemLabelGenerator(XYItemLabelGenerator generator) {
        setDefaultItemLabelGenerator(generator);
        return this;
    }
}
