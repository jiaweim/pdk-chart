package pdk.chart.plot.dial;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link AbstractDialLayer} class.
 */
public class AbstractDialLayerTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DialCap c1 = new DialCap();
        DialCap c2 = new DialCap();
        assertEquals(c1, c2);

        // visible
        c1.setVisible(false);
        assertNotEquals(c1, c2);
        c2.setVisible(false);
        assertEquals(c1, c2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        // test a default instance
        DialCap c1 = new DialCap();
        DialCap c2 = CloneUtils.clone(c1);
        assertNotSame(c1, c2);
        assertSame(c1.getClass(), c2.getClass());
        assertEquals(c1, c2);

        // check that the listener lists are independent
        MyDialLayerChangeListener l1 = new MyDialLayerChangeListener();
        c1.addChangeListener(l1);
        assertTrue(c1.hasListener(l1));
        assertFalse(c2.hasListener(l1));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        // test a default instance
        DialCap c1 = new DialCap();
        DialCap c2 = TestUtils.serialised(c1);
        assertEquals(c1, c2);

        // check that the listener lists are independent
        MyDialLayerChangeListener l1 = new MyDialLayerChangeListener();
        c1.addChangeListener(l1);
        assertTrue(c1.hasListener(l1));
        assertFalse(c2.hasListener(l1));
    }

}
