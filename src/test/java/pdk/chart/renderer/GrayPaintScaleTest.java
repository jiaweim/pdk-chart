package pdk.chart.renderer;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link GrayPaintScale} class.
 */
public class GrayPaintScaleTest {

    private static final double EPSILON = 0.000000001;

    /**
     * Simple check for the default constructor.
     */
    @Test
    public void testConstructor() {
        GrayPaintScale gps = new GrayPaintScale();
        assertEquals(0.0, gps.getLowerBound(), EPSILON);
        assertEquals(1.0, gps.getUpperBound(), EPSILON);
        assertEquals(255, gps.getAlpha());
    }

    /**
     * Some checks for the getPaint() method.
     */
    @Test
    public void testGetPaint() {
        GrayPaintScale gps = new GrayPaintScale();
        Color c = (Color) gps.getPaint(0.0);
        assertEquals(c, Color.BLACK);
        c = (Color) gps.getPaint(1.0);
        assertEquals(c, Color.WHITE);

        // check lookup values that are outside the bounds - see bug report
        // 1767315
        c = (Color) gps.getPaint(-0.5);
        assertEquals(c, Color.BLACK);
        c = (Color) gps.getPaint(1.5);
        assertEquals(c, Color.WHITE);
    }

    /**
     * A test for the equals() method.
     */
    @Test
    public void testEquals() {
        GrayPaintScale g1 = new GrayPaintScale();
        GrayPaintScale g2 = new GrayPaintScale();
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new GrayPaintScale(0.0, 1.0);
        g2 = new GrayPaintScale(0.0, 1.0);
        assertEquals(g1, g2);
        g1 = new GrayPaintScale(0.1, 1.0);
        assertNotEquals(g1, g2);
        g2 = new GrayPaintScale(0.1, 1.0);
        assertEquals(g1, g2);

        g1 = new GrayPaintScale(0.1, 0.9);
        assertNotEquals(g1, g2);
        g2 = new GrayPaintScale(0.1, 0.9);
        assertEquals(g1, g2);

        g1 = new GrayPaintScale(0.1, 0.9, 128);
        assertNotEquals(g1, g2);
        g2 = new GrayPaintScale(0.1, 0.9, 128);
        assertEquals(g1, g2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        GrayPaintScale g1 = new GrayPaintScale();
        GrayPaintScale g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        GrayPaintScale g1 = new GrayPaintScale();
        GrayPaintScale g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
