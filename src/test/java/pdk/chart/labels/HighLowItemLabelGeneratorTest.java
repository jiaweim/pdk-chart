package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link HighLowItemLabelGenerator} class.
 */
public class HighLowItemLabelGeneratorTest {

    /**
     * Tests that the equals method can distinguish all fields.
     */
    @Test
    public void testEquals() {
        HighLowItemLabelGenerator g1 = new HighLowItemLabelGenerator();
        HighLowItemLabelGenerator g2 = new HighLowItemLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new HighLowItemLabelGenerator(new SimpleDateFormat("d-MMM-yyyy"),
                NumberFormat.getInstance());
        assertNotEquals(g1, g2);
        g2 = new HighLowItemLabelGenerator(new SimpleDateFormat("d-MMM-yyyy"),
                NumberFormat.getInstance());
        assertEquals(g1, g2);

        g1 = new HighLowItemLabelGenerator(new SimpleDateFormat("d-MMM-yyyy"),
                new DecimalFormat("0.000"));
        assertNotEquals(g1, g2);
        g2 = new HighLowItemLabelGenerator(new SimpleDateFormat("d-MMM-yyyy"),
                new DecimalFormat("0.000"));
        assertEquals(g1, g2);
    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        HighLowItemLabelGenerator g1 = new HighLowItemLabelGenerator();
        HighLowItemLabelGenerator g2 = new HighLowItemLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        HighLowItemLabelGenerator g1 = new HighLowItemLabelGenerator();
        HighLowItemLabelGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        HighLowItemLabelGenerator g1 = new HighLowItemLabelGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        HighLowItemLabelGenerator g1 = new HighLowItemLabelGenerator();
        HighLowItemLabelGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
