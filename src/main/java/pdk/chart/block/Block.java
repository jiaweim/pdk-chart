package pdk.chart.block;

import pdk.chart.model.Drawable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * A block is an arbitrary item that can be drawn (in Java2D space) within a
 * rectangular area, has a preferred size, and can be arranged by an
 * {@link Arrangement} manager.
 */
public interface Block extends Drawable {

    /**
     * Returns an ID for the block.
     *
     * @return An ID.
     */
    String getID();

    /**
     * Sets the ID for the block.
     *
     * @param id the ID.
     */
    void setID(String id);

    /**
     * Arranges the contents of the block, with no constraints, and returns
     * the block size.
     *
     * @param g2 the graphics device.
     * @return The size of the block.
     */
    Size2D arrange(Graphics2D g2);

    /**
     * Arranges the contents of the block, within the given constraints, and
     * returns the block size.
     *
     * @param g2         the graphics device.
     * @param constraint the constraint ({@code null} not permitted).
     * @return The block size (in Java2D units, never {@code null}).
     */
    Size2D arrange(Graphics2D g2, RectangleConstraint constraint);

    /**
     * Returns the current bounds of the block.
     *
     * @return The bounds.
     */
    Rectangle2D getBounds();

    /**
     * Sets the bounds of the block.
     *
     * @param bounds the bounds.
     */
    void setBounds(Rectangle2D bounds);

    /**
     * Draws the block within the specified area.  Refer to the documentation
     * for the implementing class for information about the {@code params}
     * and return value supported.
     *
     * @param g2     the graphics device.
     * @param area   the area.
     * @param params optional parameters ({@code null} permitted).
     * @return An optional return value (possibly {@code null}).
     */
    Object draw(Graphics2D g2, Rectangle2D area, Object params);

}
