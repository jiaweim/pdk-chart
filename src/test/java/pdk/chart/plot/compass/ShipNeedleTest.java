package pdk.chart.plot.compass;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link ShipNeedle} class.
 */
public class ShipNeedleTest {

    /**
     * Check that the equals() method can distinguish all fields.
     */
    @Test
    public void testEquals() {
        ShipNeedle n1 = new ShipNeedle();
        ShipNeedle n2 = new ShipNeedle();
        assertEquals(n1, n2);
        assertEquals(n2, n1);
    }

    /**
     * Check that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        ShipNeedle n1 = new ShipNeedle();
        ShipNeedle n2 = CloneUtils.clone(n1);
        assertNotSame(n1, n2);
        assertSame(n1.getClass(), n2.getClass());
        assertEquals(n1, n2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        ShipNeedle n1 = new ShipNeedle();
        ShipNeedle n2 = TestUtils.serialised(n1);
        assertEquals(n1, n2);
    }

}
