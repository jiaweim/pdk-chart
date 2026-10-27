package pdk.chart.renderer.category;

import pdk.chart.api.RectangleEdge;
import pdk.chart.util.GradientPaintTransformer;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

/**
 * An implementation of the {@link BarPainter} interface that preserves the
 * behavior of bar painting that existed prior to the introduction of the
 * {@link BarPainter} interface.
 *
 * @see GradientBarPainter
 */
public class StandardBarPainter implements BarPainter, Serializable {

    /**
     * Creates a new instance.
     */
    public StandardBarPainter() {}

    /**
     * Paints a single bar instance.
     *
     * @param g2       the graphics target.
     * @param renderer the renderer.
     * @param row      the row index.
     * @param column   the column index.
     * @param bar      the bar
     * @param base     indicates which side of the rectangle is the base of the
     *                 bar.
     */
    @Override
    public void paintBar(Graphics2D g2, BarRenderer renderer, int row,
            int column, RectangularShape bar, RectangleEdge base) {

        Paint itemPaint = renderer.getItemPaint(row, column);
        GradientPaintTransformer t = renderer.getGradientPaintTransformer();
        if (t != null && itemPaint instanceof GradientPaint) {
            itemPaint = t.transform((GradientPaint) itemPaint, bar);
        }
        g2.setPaint(itemPaint);
        g2.fill(bar);

        // draw the outline...
        if (renderer.isDrawBarOutline()) {
            // && state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) {
            Stroke stroke = renderer.getItemOutlineStroke(row, column);
            Paint paint = renderer.getItemOutlinePaint(row, column);
            if (stroke != null && paint != null) {
                g2.setStroke(stroke);
                g2.setPaint(paint);
                g2.draw(bar);
            }
        }
    }

    /**
     * Paints a single bar instance.
     *
     * @param g2        the graphics target.
     * @param renderer  the renderer.
     * @param row       the row index.
     * @param column    the column index.
     * @param bar       the bar
     * @param base      indicates which side of the rectangle is the base of the
     *                  bar.
     * @param pegShadow peg the shadow to the base of the bar?
     */
    @Override
    public void paintBarShadow(Graphics2D g2, BarRenderer renderer, int row,
            int column, RectangularShape bar, RectangleEdge base,
            boolean pegShadow) {

        // handle a special case - if the bar colour has alpha == 0, it is
        // invisible so we shouldn't draw any shadow
        Paint itemPaint = renderer.getItemPaint(row, column);
        if (itemPaint instanceof Color c) {
            if (c.getAlpha() == 0) {
                return;
            }
        }

        RectangularShape shadow = createShadow(bar, renderer.getShadowXOffset(),
                renderer.getShadowYOffset(), base, pegShadow);
        g2.setPaint(renderer.getShadowPaint());
        g2.fill(shadow);

    }

    /**
     * Creates a shadow for the bar.
     *
     * @param bar       the bar shape.
     * @param xOffset   the x-offset for the shadow.
     * @param yOffset   the y-offset for the shadow.
     * @param base      the edge that is the base of the bar.
     * @param pegShadow peg the shadow to the base?
     * @return A rectangle for the shadow.
     */
    private Rectangle2D createShadow(RectangularShape bar, double xOffset,
            double yOffset, RectangleEdge base, boolean pegShadow) {
        double x0 = bar.getMinX();
        double x1 = bar.getMaxX();
        double y0 = bar.getMinY();
        double y1 = bar.getMaxY();
        if (base == RectangleEdge.TOP) {
            x0 += xOffset;
            x1 += xOffset;
            if (!pegShadow) {
                y0 += yOffset;
            }
            y1 += yOffset;
        } else if (base == RectangleEdge.BOTTOM) {
            x0 += xOffset;
            x1 += xOffset;
            y0 += yOffset;
            if (!pegShadow) {
                y1 += yOffset;
            }
        } else if (base == RectangleEdge.LEFT) {
            if (!pegShadow) {
                x0 += xOffset;
            }
            x1 += xOffset;
            y0 += yOffset;
            y1 += yOffset;
        } else if (base == RectangleEdge.RIGHT) {
            x0 += xOffset;
            if (!pegShadow) {
                x1 += xOffset;
            }
            y0 += yOffset;
            y1 += yOffset;
        }
        return new Rectangle2D.Double(x0, y0, (x1 - x0), (y1 - y0));
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj the obj ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardBarPainter)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int hash = 37;
        // no fields to compute...
        return hash;
    }
}
