package pdk.chart.renderer;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link DefaultPolarItemRenderer} class.
 */
public class DefaultPolarItemRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        DefaultPolarItemRenderer r1 = new DefaultPolarItemRenderer();
        DefaultPolarItemRenderer r2 = new DefaultPolarItemRenderer();
        assertEquals(r1, r2);

        r1.setSeriesFilled(1, true);
        assertNotEquals(r1, r2);
        r2.setSeriesFilled(1, true);
        assertEquals(r1, r2);

    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        DefaultPolarItemRenderer r1 = new DefaultPolarItemRenderer();
        DefaultPolarItemRenderer r2 = new DefaultPolarItemRenderer();
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
        DefaultPolarItemRenderer r1 = new DefaultPolarItemRenderer();
        DefaultPolarItemRenderer r2 = CloneUtils.clone(r1);

        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);

        r1.setSeriesFilled(1, true);
        assertNotEquals(r1, r2);
        r2.setSeriesFilled(1, true);
        assertEquals(r1, r2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultPolarItemRenderer r1 = new DefaultPolarItemRenderer();
        DefaultPolarItemRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
    }

}
