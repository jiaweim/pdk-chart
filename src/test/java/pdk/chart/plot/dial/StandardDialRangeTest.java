package pdk.chart.plot.dial;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardDialRange} class.
 */
public class StandardDialRangeTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        StandardDialRange r1 = new StandardDialRange();
        StandardDialRange r2 = new StandardDialRange();
        assertEquals(r1, r2);

        // lowerBound
        r1.setLowerBound(1.1);
        assertNotEquals(r1, r2);
        r2.setLowerBound(1.1);
        assertEquals(r1, r2);

        // upperBound
        r1.setUpperBound(11.1);
        assertNotEquals(r1, r2);
        r2.setUpperBound(11.1);
        assertEquals(r1, r2);

        // paint
        r1.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f, 4.0f,
                Color.BLUE));
        assertNotEquals(r1, r2);
        r2.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f, 4.0f,
                Color.BLUE));
        assertEquals(r1, r2);

        // check an inherited attribute
        r1.setVisible(false);
        assertNotEquals(r1, r2);
        r2.setVisible(false);
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        StandardDialRange r1 = new StandardDialRange();
        StandardDialRange r2 = new StandardDialRange();
        assertEquals(r1, r2);
        int h1 = r1.hashCode();
        int h2 = r2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardDialRange r1 = new StandardDialRange();
        StandardDialRange r2 = CloneUtils.clone(r1);
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);

        // check that the listener lists are independent
        MyDialLayerChangeListener l1 = new MyDialLayerChangeListener();
        r1.addChangeListener(l1);
        assertTrue(r1.hasListener(l1));
        assertFalse(r2.hasListener(l1));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardDialRange r1 = new StandardDialRange();
        StandardDialRange r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
    }

}
