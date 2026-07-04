package pdk.chart.api;

import pdk.chart.util.Args;

import java.awt.geom.Rectangle2D;

/**
 * Used to indicate how to align one rectangle with another rectangle.
 */
public enum RectangleAlignment {

    /**
     * Fill the frame
     */
    FILL,

    /**
     * Fill the height of the frame.
     */
    FILL_VERTICAL,

    /**
     * Fill the width of the frame.
     */
    FILL_HORIZONTAL,

    /**
     * Align to the top left of the frame.
     */
    TOP_LEFT,

    /**
     * Align to the top of the frame.
     */
    TOP_CENTER,

    /**
     * Align to the top right of the frame.
     */
    TOP_RIGHT,

    /**
     * Align to the left side of the frame, centered vertically.
     */
    CENTER_LEFT,

    /**
     * Align to the center of the frame.
     */
    CENTER,

    /**
     * Align to the right side of the frame, centered vertically.
     */
    CENTER_RIGHT,

    /**
     * Align to the bottom left of the frame.
     */
    BOTTOM_LEFT,

    /**
     * Align to the bottom of the frame.
     */
    BOTTOM_CENTER,

    /**
     * Align to the bottom right of the frame.
     */
    BOTTOM_RIGHT;

    /**
     * Returns the anchor point relative to the specified rectangle.
     *
     * @param rect  the rectangle to align ({@code null} not permitted).
     * @param frame the frame to align with ({@code null} not permitted).
     */
    public void align(Rectangle2D rect, Rectangle2D frame) {
        Args.nullNotPermitted(rect, "rect");
        Args.nullNotPermitted(frame, "frame");
        double x = rect.getX();
        double y = rect.getY();
        double w = rect.getWidth();
        double h = rect.getHeight();

        switch (this) {
            case BOTTOM_CENTER:
                x = frame.getCenterX() - rect.getWidth() / 2.0;
                y = frame.getMaxY() - h;
                break;
            case BOTTOM_LEFT:
                x = frame.getX();
                y = frame.getMaxY() - h;
                break;
            case BOTTOM_RIGHT:
                x = frame.getMaxX() - w;
                y = frame.getMaxY() - h;
                break;
            case CENTER:
                x = frame.getCenterX() - rect.getWidth() / 2.0;
                y = frame.getCenterY() - rect.getHeight() / 2.0;
                break;
            case FILL:
                x = frame.getX();
                y = frame.getY();
                w = frame.getWidth();
                h = frame.getHeight();
                break;
            case FILL_HORIZONTAL:
                x = frame.getX();
                w = frame.getWidth();
                break;
            case FILL_VERTICAL:
                y = frame.getY();
                h = frame.getHeight();
                break;
            case CENTER_LEFT:
                x = frame.getX();
                y = frame.getCenterY() - rect.getHeight() / 2.0;
                break;
            case CENTER_RIGHT:
                x = frame.getMaxX() - w;
                y = frame.getCenterY() - rect.getHeight() / 2.0;
                break;
            case TOP_CENTER:
                x = frame.getCenterX() - rect.getWidth() / 2.0;
                y = frame.getY();
                break;
            case TOP_LEFT:
                x = frame.getX();
                y = frame.getY();
                break;
            case TOP_RIGHT:
                x = frame.getMaxX() - w;
                y = frame.getY();
                break;
            default:
                throw new IllegalStateException("Unexpected RectangleAlignment value");
        }
        rect.setRect(x, y, w, h);
    }

}
