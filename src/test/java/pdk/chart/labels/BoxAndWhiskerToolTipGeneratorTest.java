package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link BoxAndWhiskerToolTipGenerator} class.
 */
public class BoxAndWhiskerToolTipGeneratorTest {

    /**
     * A series of tests for the equals() method.
     */
    @Test
    public void testEquals() {

        // standard test
        BoxAndWhiskerToolTipGenerator g1 = new BoxAndWhiskerToolTipGenerator();
        BoxAndWhiskerToolTipGenerator g2 = new BoxAndWhiskerToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        // tooltip format
        g1 = new BoxAndWhiskerToolTipGenerator("{0} --> {1} {2}",
                new DecimalFormat("0.0"));
        g2 = new BoxAndWhiskerToolTipGenerator("{1} {2}",
                new DecimalFormat("0.0"));
        assertNotEquals(g1, g2);
        g2 = new BoxAndWhiskerToolTipGenerator("{0} --> {1} {2}",
                new DecimalFormat("0.0"));
        assertEquals(g1, g2);

        // Y format
        g1 = new BoxAndWhiskerToolTipGenerator("{0} --> {1} {2}",
                new DecimalFormat("0.0"));
        g2 = new BoxAndWhiskerToolTipGenerator("{0} --> {1} {2}",
                new DecimalFormat("0.00"));
        assertNotEquals(g1, g2);
    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        BoxAndWhiskerToolTipGenerator g1 = new BoxAndWhiskerToolTipGenerator();
        BoxAndWhiskerToolTipGenerator g2 = new BoxAndWhiskerToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        BoxAndWhiskerToolTipGenerator g1 = new BoxAndWhiskerToolTipGenerator();
        BoxAndWhiskerToolTipGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        BoxAndWhiskerToolTipGenerator g1 = new BoxAndWhiskerToolTipGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        BoxAndWhiskerToolTipGenerator g1 = new BoxAndWhiskerToolTipGenerator();
        BoxAndWhiskerToolTipGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
