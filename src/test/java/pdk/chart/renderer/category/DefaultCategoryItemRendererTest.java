package pdk.chart.renderer.category;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link DefaultCategoryItemRenderer} class.
 */
public class DefaultCategoryItemRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        DefaultCategoryItemRenderer r1 = new DefaultCategoryItemRenderer();
        DefaultCategoryItemRenderer r2 = new DefaultCategoryItemRenderer();
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        DefaultCategoryItemRenderer r1 = new DefaultCategoryItemRenderer();
        DefaultCategoryItemRenderer r2 = new DefaultCategoryItemRenderer();
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
        DefaultCategoryItemRenderer r1 = new DefaultCategoryItemRenderer();
        DefaultCategoryItemRenderer r2 = CloneUtils.clone(r1);
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

    /**
     * Check that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        DefaultCategoryItemRenderer r1 = new DefaultCategoryItemRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultCategoryItemRenderer r1 = new DefaultCategoryItemRenderer();
        DefaultCategoryItemRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

}
