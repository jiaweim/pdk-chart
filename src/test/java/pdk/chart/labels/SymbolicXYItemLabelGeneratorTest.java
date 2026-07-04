package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link SymbolicXYItemLabelGenerator} class.
 */
public class SymbolicXYItemLabelGeneratorTest {

    /**
     * Tests the equals method.
     */
    @Test
    public void testEquals() {
        SymbolicXYItemLabelGenerator g1 = new SymbolicXYItemLabelGenerator();
        SymbolicXYItemLabelGenerator g2 = new SymbolicXYItemLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g2, g1);
    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        SymbolicXYItemLabelGenerator g1
                = new SymbolicXYItemLabelGenerator();
        SymbolicXYItemLabelGenerator g2
                = new SymbolicXYItemLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        SymbolicXYItemLabelGenerator g1 = new SymbolicXYItemLabelGenerator();
        SymbolicXYItemLabelGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        SymbolicXYItemLabelGenerator g1 = new SymbolicXYItemLabelGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        SymbolicXYItemLabelGenerator g1 = new SymbolicXYItemLabelGenerator();
        SymbolicXYItemLabelGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
