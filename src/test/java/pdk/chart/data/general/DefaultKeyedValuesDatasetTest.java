package pdk.chart.data.general;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link DefaultKeyedValuesDataset} class.
 */
public class DefaultKeyedValuesDatasetTest {

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        DefaultKeyedValuesDataset<String> d1 = new DefaultKeyedValuesDataset<>();
        d1.setValue("V1", 1);
        d1.setValue("V2", null);
        d1.setValue("V3", 3);
        DefaultKeyedValuesDataset<String> d2 = CloneUtils.clone(d1);
        assertNotSame(d1, d2);
        assertSame(d1.getClass(), d2.getClass());
        assertEquals(d1, d2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultKeyedValuesDataset<String> d1 = new DefaultKeyedValuesDataset<>();
        d1.setValue("C1", 234.2);
        d1.setValue("C2", null);
        d1.setValue("C3", 345.9);
        d1.setValue("C4", 452.7);

        KeyedValuesDataset<String> d2 = TestUtils.serialised(d1);
        assertEquals(d1, d2);
    }

}
