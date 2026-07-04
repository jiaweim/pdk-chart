package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link MultipleXYSeriesLabelGenerator} class.
 */
public class MultipleXYSeriesLabelGeneratorTest {

    /**
     * A series of tests for the equals() method.
     */
    @Test
    public void testEquals() {
        MultipleXYSeriesLabelGenerator g1
                = new MultipleXYSeriesLabelGenerator();
        MultipleXYSeriesLabelGenerator g2
                = new MultipleXYSeriesLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new MultipleXYSeriesLabelGenerator("Series {0}");
        assertNotEquals(g1, g2);
        g2 = new MultipleXYSeriesLabelGenerator("Series {0}");
        assertEquals(g1, g2);

        g1.addSeriesLabel(1, "Additional 1");
        assertNotEquals(g1, g2);
        g2.addSeriesLabel(1, "Additional 1");
        assertEquals(g1, g2);
    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        MultipleXYSeriesLabelGenerator g1 = new MultipleXYSeriesLabelGenerator();
        MultipleXYSeriesLabelGenerator g2 = new MultipleXYSeriesLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        MultipleXYSeriesLabelGenerator g1 = new MultipleXYSeriesLabelGenerator();
        MultipleXYSeriesLabelGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);

        // check independence
        g1.addSeriesLabel(3, "Add3");
        assertNotEquals(g1, g2);
        g2.addSeriesLabel(3, "Add3");
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        MultipleXYSeriesLabelGenerator g1 = new MultipleXYSeriesLabelGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        MultipleXYSeriesLabelGenerator g1 = new MultipleXYSeriesLabelGenerator();
        g1.addSeriesLabel(0, "Add0");
        g1.addSeriesLabel(0, "Add0b");
        g1.addSeriesLabel(1, "Add1");
        MultipleXYSeriesLabelGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
