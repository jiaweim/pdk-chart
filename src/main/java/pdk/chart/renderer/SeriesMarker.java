package pdk.chart.renderer;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 27 Jul 2026, 11:16 AM
 */
public enum SeriesMarker {

    CIRCLE,
    DIAMOND,
    SQUARE,
    TRIANGLE_DOWN,
    TRIANGLE_UP,
    RECTANGLE;

    public Shape getShape() {
        double size = 6.0;
        double delta = size / 2.0;

        switch (this) {
            case CIRCLE -> {
                return new Ellipse2D.Double(-delta, -delta, size, size);
            }
            case SQUARE -> {
                return new Rectangle2D.Double(-delta, -delta, size, size);
            }
            case TRIANGLE_UP -> {
                int[] xpoints = intArray(0.0, delta, -delta);
                int[] ypoints = intArray(-delta, delta, delta);
                return new Polygon(xpoints, ypoints, 3);
            }
            case TRIANGLE_DOWN -> {
                int[] xpoints = intArray(-delta, +delta, 0.0);
                int[] ypoints = intArray(-delta, -delta, delta);
                return new Polygon(xpoints, ypoints, 3);
            }
            case DIAMOND -> {
                int[] xpoints = intArray(0.0, delta, 0.0, -delta);
                int[] ypoints = intArray(-delta, 0.0, delta, 0.0);
                return new Polygon(xpoints, ypoints, 4);
            }
        }
        return null;
    }

    /**
     * Helper method to avoid lots of explicit casts in getShape().  Returns
     * an array containing the provided doubles cast to ints.
     *
     * @param a x
     * @param b y
     * @param c z
     * @return int[3] with converted params.
     */
    private static int[] intArray(double a, double b, double c) {
        return new int[]{(int) a, (int) b, (int) c};
    }

    /**
     * Helper method to avoid lots of explicit casts in getShape().  Returns
     * an array containing the provided doubles cast to ints.
     *
     * @param a x
     * @param b y
     * @param c z
     * @param d t
     * @return int[4] with converted params.
     */
    private static int[] intArray(double a, double b, double c, double d) {
        return new int[]{(int) a, (int) b, (int) c, (int) d};
    }

}
