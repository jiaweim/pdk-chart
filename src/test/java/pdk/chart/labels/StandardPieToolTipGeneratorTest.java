package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardPieToolTipGenerator} class.
 */
public class StandardPieToolTipGeneratorTest {

    /**
     * Test that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        StandardPieToolTipGenerator g1 = new StandardPieToolTipGenerator();
        StandardPieToolTipGenerator g2 = new StandardPieToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new StandardPieToolTipGenerator("{0}",
                new DecimalFormat("#,##0.00"),
                NumberFormat.getPercentInstance());
        assertNotEquals(g1, g2);
        g2 = new StandardPieToolTipGenerator("{0}",
                new DecimalFormat("#,##0.00"),
                NumberFormat.getPercentInstance());
        assertEquals(g1, g2);

        g1 = new StandardPieToolTipGenerator("{0} {1}",
                new DecimalFormat("#,##0.00"),
                NumberFormat.getPercentInstance());
        assertNotEquals(g1, g2);
        g2 = new StandardPieToolTipGenerator("{0} {1}",
                new DecimalFormat("#,##0.00"),
                NumberFormat.getPercentInstance());
        assertEquals(g1, g2);

        g1 = new StandardPieToolTipGenerator("{0} {1}",
                new DecimalFormat("#,##0"), NumberFormat.getPercentInstance());
        assertNotEquals(g1, g2);
        g2 = new StandardPieToolTipGenerator("{0} {1}",
                new DecimalFormat("#,##0"), NumberFormat.getPercentInstance());
        assertEquals(g1, g2);

        g1 = new StandardPieToolTipGenerator("{0} {1}",
                new DecimalFormat("#,##0"), new DecimalFormat("0.000%"));
        assertNotEquals(g1, g2);
        g2 = new StandardPieToolTipGenerator("{0} {1}",
                new DecimalFormat("#,##0"), new DecimalFormat("0.000%"));
        assertEquals(g1, g2);
    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        StandardPieToolTipGenerator g1
                = new StandardPieToolTipGenerator();
        StandardPieToolTipGenerator g2
                = new StandardPieToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Some checks for cloning.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardPieToolTipGenerator g1 = new StandardPieToolTipGenerator();
        StandardPieToolTipGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
        assertNotSame(g1.getNumberFormat(), g2.getNumberFormat());
        assertNotSame(g1.getPercentFormat(), g2.getPercentFormat());
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        StandardPieToolTipGenerator g1 = new StandardPieToolTipGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardPieToolTipGenerator g1 = new StandardPieToolTipGenerator();
        StandardPieToolTipGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
