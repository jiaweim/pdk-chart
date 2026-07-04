package pdk.chart.plot.dial;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardDialFrame} class.
 */
public class StandardDialFrameTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        StandardDialFrame f1 = new StandardDialFrame();
        StandardDialFrame f2 = new StandardDialFrame();
        assertEquals(f1, f2);

        // radius
        f1.setRadius(0.2);
        assertNotEquals(f1, f2);
        f2.setRadius(0.2);
        assertEquals(f1, f2);

        // backgroundPaint
        f1.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.WHITE, 3.0f,
                4.0f, Color.YELLOW));
        assertNotEquals(f1, f2);
        f2.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.WHITE, 3.0f,
                4.0f, Color.YELLOW));
        assertEquals(f1, f2);

        // foregroundPaint
        f1.setForegroundPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE, 3.0f,
                4.0f, Color.GREEN));
        assertNotEquals(f1, f2);
        f2.setForegroundPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE, 3.0f,
                4.0f, Color.GREEN));
        assertEquals(f1, f2);

        // stroke
        f1.setStroke(new BasicStroke(2.4f));
        assertNotEquals(f1, f2);
        f2.setStroke(new BasicStroke(2.4f));
        assertEquals(f1, f2);

        // check an inherited attribute
        f1.setVisible(false);
        assertNotEquals(f1, f2);
        f2.setVisible(false);
        assertEquals(f1, f2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        StandardDialFrame f1 = new StandardDialFrame();
        StandardDialFrame f2 = new StandardDialFrame();
        assertEquals(f1, f2);
        int h1 = f1.hashCode();
        int h2 = f2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardDialFrame f1 = new StandardDialFrame();
        StandardDialFrame f2 = CloneUtils.clone(f1);
        assertNotSame(f1, f2);
        assertSame(f1.getClass(), f2.getClass());
        assertEquals(f1, f2);

        // check that the listener lists are independent
        MyDialLayerChangeListener l1 = new MyDialLayerChangeListener();
        f1.addChangeListener(l1);
        assertTrue(f1.hasListener(l1));
        assertFalse(f2.hasListener(l1));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardDialFrame f1 = new StandardDialFrame();
        StandardDialFrame f2 = TestUtils.serialised(f1);
        assertEquals(f1, f2);
    }

}
