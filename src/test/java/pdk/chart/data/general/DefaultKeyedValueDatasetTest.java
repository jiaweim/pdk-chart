package pdk.chart.data.general;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link DefaultKeyedValueDataset} class.
 */
public class DefaultKeyedValueDatasetTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {

        DefaultKeyedValueDataset d1 = new DefaultKeyedValueDataset("Test", 45.5);
        DefaultKeyedValueDataset d2 = new DefaultKeyedValueDataset("Test", 45.5);
        assertEquals(d1, d2);
        assertEquals(d2, d1);

        d1 = new DefaultKeyedValueDataset("Test 1", 45.5);
        d2 = new DefaultKeyedValueDataset("Test 2", 45.5);
        assertNotEquals(d1, d2);

        d1 = new DefaultKeyedValueDataset("Test", 45.5);
        d2 = new DefaultKeyedValueDataset("Test", 45.6);
        assertNotEquals(d1, d2);

    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        DefaultKeyedValueDataset d1 = new DefaultKeyedValueDataset("Test", 45.5);
        DefaultKeyedValueDataset d2 = (DefaultKeyedValueDataset) d1.clone();
        assertNotSame(d1, d2);
        assertSame(d1.getClass(), d2.getClass());
        assertEquals(d1, d2);
    }

    /**
     * Confirm that the clone is independent of the original.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloneIndependence() throws CloneNotSupportedException {
        DefaultKeyedValueDataset d1
                = new DefaultKeyedValueDataset("Key", 10.0);
        DefaultKeyedValueDataset d2 = CloneUtils.clone(d1);
        assertEquals(d1, d2);
        d2.updateValue(99.9);
        assertNotEquals(d1, d2);
        d2.updateValue(10.0);
        assertEquals(d1, d2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultKeyedValueDataset d1 = new DefaultKeyedValueDataset("Test", 25.3);
        DefaultKeyedValueDataset d2 = TestUtils.serialised(d1);
        assertEquals(d1, d2);
    }

}
