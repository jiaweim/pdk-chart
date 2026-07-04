package pdk.chart.legend;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.axis.AxisLocation;
import pdk.chart.axis.NumberAxis;
import pdk.chart.util.CloneUtils;
import pdk.chart.renderer.GrayPaintScale;
import pdk.chart.renderer.LookupPaintScale;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link PaintScaleLegend} class.
 */
public class PaintScaleLegendTest {

    /**
     * Test that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {

        // default instances
        PaintScaleLegend l1 = new PaintScaleLegend(new GrayPaintScale(),
                new NumberAxis("X"));
        PaintScaleLegend l2 = new PaintScaleLegend(new GrayPaintScale(),
                new NumberAxis("X"));
        assertEquals(l1, l2);
        assertEquals(l2, l1);

        // paintScale
        l1.setScale(new LookupPaintScale());
        assertNotEquals(l1, l2);
        l2.setScale(new LookupPaintScale());
        assertEquals(l1, l2);

        // axis
        l1.setAxis(new NumberAxis("Axis 2"));
        assertNotEquals(l1, l2);
        l2.setAxis(new NumberAxis("Axis 2"));
        assertEquals(l1, l2);

        // axisLocation
        l1.setAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        assertNotEquals(l1, l2);
        l2.setAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        assertEquals(l1, l2);

        // axisOffset
        l1.setAxisOffset(99.0);
        assertNotEquals(l1, l2);
        l2.setAxisOffset(99.0);
        assertEquals(l1, l2);

        // stripWidth
        l1.setStripWidth(99.0);
        assertNotEquals(l1, l2);
        l2.setStripWidth(99.0);
        assertEquals(l1, l2);

        // stripOutlineVisible
        l1.setStripOutlineVisible(!l1.isStripOutlineVisible());
        assertNotEquals(l1, l2);
        l2.setStripOutlineVisible(l1.isStripOutlineVisible());
        assertEquals(l1, l2);

        // stripOutlinePaint
        l1.setStripOutlinePaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.BLUE));
        assertNotEquals(l1, l2);
        l2.setStripOutlinePaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.BLUE));
        assertEquals(l1, l2);

        // stripOutlineStroke
        l1.setStripOutlineStroke(new BasicStroke(1.1f));
        assertNotEquals(l1, l2);
        l2.setStripOutlineStroke(new BasicStroke(1.1f));
        assertEquals(l1, l2);

        // backgroundPaint
        l1.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.BLUE));
        assertNotEquals(l1, l2);
        l2.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.BLUE));
        assertEquals(l1, l2);

        l1.setSubdivisionCount(99);
        assertNotEquals(l1, l2);
        l2.setSubdivisionCount(99);
        assertEquals(l1, l2);

    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        PaintScaleLegend l1 = new PaintScaleLegend(new GrayPaintScale(),
                new NumberAxis("X"));
        PaintScaleLegend l2 = new PaintScaleLegend(new GrayPaintScale(),
                new NumberAxis("X"));
        assertEquals(l1, l2);
        int h1 = l1.hashCode();
        int h2 = l2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        PaintScaleLegend l1 = new PaintScaleLegend(new GrayPaintScale(),
                new NumberAxis("X"));
        PaintScaleLegend l2 = CloneUtils.clone(l1);
        assertNotSame(l1, l2);
        assertSame(l1.getClass(), l2.getClass());
        assertEquals(l1, l2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        PaintScaleLegend l1 = new PaintScaleLegend(new GrayPaintScale(),
                new NumberAxis("X"));
        PaintScaleLegend l2 = TestUtils.serialised(l1);
        assertEquals(l1, l2);
    }
}
