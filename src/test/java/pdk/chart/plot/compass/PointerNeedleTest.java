package pdk.chart.plot.compass;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link PointerNeedle} class.
 */
public class PointerNeedleTest {

    /**
     * Check that the equals() method can distinguish all fields.
     */
    @Test
    public void testEquals() {
        PointerNeedle n1 = new PointerNeedle();
        PointerNeedle n2 = new PointerNeedle();
        assertEquals(n1, n2);
        assertEquals(n2, n1);
    }

    /**
     * Check that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        PointerNeedle n1 = new PointerNeedle();
        PointerNeedle n2 = CloneUtils.clone(n1);
        assertNotSame(n1, n2);
        assertSame(n1.getClass(), n2.getClass());
        assertEquals(n1, n2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        PointerNeedle n1 = new PointerNeedle();
        PointerNeedle n2 = TestUtils.serialised(n1);
        assertEquals(n1, n2);
    }

}
