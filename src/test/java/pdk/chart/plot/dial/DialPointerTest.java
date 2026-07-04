package pdk.chart.plot.dial;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link DialPointer} class.
 */
public class DialPointerTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DialPointer i1 = new DialPointer.Pin(1);
        DialPointer i2 = new DialPointer.Pin(1);
        assertEquals(i1, i2);

        // dataset index
        i1 = new DialPointer.Pin(2);
        assertNotEquals(i1, i2);
        i2 = new DialPointer.Pin(2);
        assertEquals(i1, i2);

        // check an inherited attribute
        i1.setVisible(false);
        assertNotEquals(i1, i2);
        i2.setVisible(false);
        assertEquals(i1, i2);
    }

    /**
     * Check the equals() method for the DialPointer.Pin class.
     */
    @Test
    public void testEqualsPin() {
        DialPointer.Pin p1 = new DialPointer.Pin();
        DialPointer.Pin p2 = new DialPointer.Pin();
        assertEquals(p1, p2);

        p1.setPaint(Color.GREEN);
        assertNotEquals(p1, p2);
        p2.setPaint(Color.GREEN);
        assertEquals(p1, p2);

        BasicStroke s = new BasicStroke(4.4f);
        p1.setStroke(s);
        assertNotEquals(p1, p2);
        p2.setStroke(s);
        assertEquals(p1, p2);
    }

    /**
     * Check the equals() method for the DialPointer.Pointer class.
     */
    @Test
    public void testEqualsPointer() {
        DialPointer.Pointer p1 = new DialPointer.Pointer();
        DialPointer.Pointer p2 = new DialPointer.Pointer();
        assertEquals(p1, p2);

        p1.setFillPaint(Color.GREEN);
        assertNotEquals(p1, p2);
        p2.setFillPaint(Color.GREEN);
        assertEquals(p1, p2);

        p1.setOutlinePaint(Color.GREEN);
        assertNotEquals(p1, p2);
        p2.setOutlinePaint(Color.GREEN);
        assertEquals(p1, p2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        DialPointer i1 = new DialPointer.Pin(1);
        DialPointer i2 = new DialPointer.Pin(1);
        assertEquals(i1, i2);
        int h1 = i1.hashCode();
        int h2 = i2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        DialPointer i1 = new DialPointer.Pin(1);
        DialPointer i2 = CloneUtils.clone(i1);
        assertNotSame(i1, i2);
        assertSame(i1.getClass(), i2.getClass());
        assertEquals(i1, i2);

        // check that the listener lists are independent
        MyDialLayerChangeListener l1 = new MyDialLayerChangeListener();
        i1.addChangeListener(l1);
        assertTrue(i1.hasListener(l1));
        assertFalse(i2.hasListener(l1));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        // test a default instance
        DialPointer i1 = new DialPointer.Pin(1);
        DialPointer i2 = TestUtils.serialised(i1);
        assertEquals(i1, i2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization2() {
        DialPointer i1 = new DialPointer.Pointer(1);
        DialPointer i2 = TestUtils.serialised(i1);
        assertEquals(i1, i2);
    }
}
