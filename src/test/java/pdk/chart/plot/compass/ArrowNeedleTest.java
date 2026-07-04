package pdk.chart.plot.compass;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link ArrowNeedle} class.
 */
public class ArrowNeedleTest {

    /**
     * Check that the equals() method can distinguish all fields.
     */
    @Test
    public void testEquals() {
        ArrowNeedle n1 = new ArrowNeedle(false);
        ArrowNeedle n2 = new ArrowNeedle(false);
        assertEquals(n1, n2);
        assertEquals(n2, n1);

        n1 = new ArrowNeedle(true);
        assertNotEquals(n1, n2);
        n2 = new ArrowNeedle(true);
        assertEquals(n1, n2);
    }

    /**
     * Check that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        ArrowNeedle n1 = new ArrowNeedle(false);
        ArrowNeedle n2 = CloneUtils.clone(n1);
        assertNotSame(n1, n2);
        assertSame(n1.getClass(), n2.getClass());
        assertEquals(n1, n2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        ArrowNeedle n1 = new ArrowNeedle(false);
        ArrowNeedle n2 = TestUtils.serialised(n1);
        assertEquals(n1, n2);
    }

}
