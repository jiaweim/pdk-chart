package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardXYToolTipGenerator} class.
 */
public class StandardXYToolTipGeneratorTest {

    /**
     * Tests the equals() method.
     */
    @Test
    public void testEquals() {

        // some setup...
        String f1 = "{1}";
        String f2 = "{2}";
        NumberFormat xnf1 = new DecimalFormat("0.00");
        NumberFormat xnf2 = new DecimalFormat("0.000");
        NumberFormat ynf1 = new DecimalFormat("0.00");
        NumberFormat ynf2 = new DecimalFormat("0.000");

        StandardXYToolTipGenerator g1 = null;
        StandardXYToolTipGenerator g2 = null;

        g1 = new StandardXYToolTipGenerator(f1, xnf1, ynf1);
        g2 = new StandardXYToolTipGenerator(f1, xnf1, ynf1);
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new StandardXYToolTipGenerator(f2, xnf1, ynf1);
        assertNotEquals(g1, g2);
        g2 = new StandardXYToolTipGenerator(f2, xnf1, ynf1);
        assertEquals(g1, g2);

        g1 = new StandardXYToolTipGenerator(f2, xnf2, ynf1);
        assertNotEquals(g1, g2);
        g2 = new StandardXYToolTipGenerator(f2, xnf2, ynf1);
        assertEquals(g1, g2);

        g1 = new StandardXYToolTipGenerator(f2, xnf2, ynf2);
        assertNotEquals(g1, g2);
        g2 = new StandardXYToolTipGenerator(f2, xnf2, ynf2);
        assertEquals(g1, g2);

        DateFormat xdf1 = new SimpleDateFormat("d-MMM");
        DateFormat xdf2 = new SimpleDateFormat("d-MMM-yyyy");
        DateFormat ydf1 = new SimpleDateFormat("d-MMM");
        DateFormat ydf2 = new SimpleDateFormat("d-MMM-yyyy");

        g1 = new StandardXYToolTipGenerator(f1, xdf1, ydf1);
        g2 = new StandardXYToolTipGenerator(f1, xdf1, ydf1);
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new StandardXYToolTipGenerator(f1, xdf2, ydf1);
        assertNotEquals(g1, g2);
        g2 = new StandardXYToolTipGenerator(f1, xdf2, ydf1);
        assertEquals(g1, g2);

        g1 = new StandardXYToolTipGenerator(f1, xdf2, ydf2);
        assertNotEquals(g1, g2);
        g2 = new StandardXYToolTipGenerator(f1, xdf2, ydf2);
        assertEquals(g1, g2);

    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        StandardXYToolTipGenerator g1
                = new StandardXYToolTipGenerator();
        StandardXYToolTipGenerator g2
                = new StandardXYToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardXYToolTipGenerator g1 = new StandardXYToolTipGenerator();
        StandardXYToolTipGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        StandardXYToolTipGenerator g1 = new StandardXYToolTipGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardXYToolTipGenerator g1 = new StandardXYToolTipGenerator();
        StandardXYToolTipGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
