package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link VectorDataItem} class.
 */
public class VectorDataItemTest {

    /**
     * Test that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        // default instances
        VectorDataItem v1 = new VectorDataItem(1.0, 2.0, 3.0, 4.0);
        VectorDataItem v2 = new VectorDataItem(1.0, 2.0, 3.0, 4.0);
        assertEquals(v1, v2);
        assertEquals(v2, v1);

        v1 = new VectorDataItem(1.1, 2.0, 3.0, 4.0);
        assertNotEquals(v1, v2);
        v2 = new VectorDataItem(1.1, 2.0, 3.0, 4.0);
        assertEquals(v1, v2);

        v1 = new VectorDataItem(1.1, 2.2, 3.0, 4.0);
        assertNotEquals(v1, v2);
        v2 = new VectorDataItem(1.1, 2.2, 3.0, 4.0);
        assertEquals(v1, v2);

        v1 = new VectorDataItem(1.1, 2.2, 3.3, 4.0);
        assertNotEquals(v1, v2);
        v2 = new VectorDataItem(1.1, 2.2, 3.3, 4.0);
        assertEquals(v1, v2);

        v1 = new VectorDataItem(1.1, 2.2, 3.3, 4.4);
        assertNotEquals(v1, v2);
        v2 = new VectorDataItem(1.1, 2.2, 3.3, 4.4);
        assertEquals(v1, v2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        VectorDataItem v1 = new VectorDataItem(1.0, 2.0, 3.0, 4.0);
        VectorDataItem v2 = new VectorDataItem(1.0, 2.0, 3.0, 4.0);
        assertEquals(v1, v2);
        int h1 = v1.hashCode();
        int h2 = v2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Check cloning.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        VectorDataItem v1 = new VectorDataItem(1.0, 2.0, 3.0, 4.0);
        VectorDataItem v2 = CloneUtils.clone(v1);
        assertNotSame(v1, v2);
        assertSame(v1.getClass(), v2.getClass());
        assertEquals(v1, v2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        VectorDataItem v1 = new VectorDataItem(1.0, 2.0, 3.0, 4.0);
        VectorDataItem v2 = TestUtils.serialised(v1);
        assertEquals(v1, v2);
    }

}
