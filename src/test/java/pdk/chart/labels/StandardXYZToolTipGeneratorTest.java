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
 * Tests for the {@link StandardXYZToolTipGenerator} class.
 */
public class StandardXYZToolTipGeneratorTest {

    /**
     * Tests that the equals() method can distinguish all fields.
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
        NumberFormat znf1 = new DecimalFormat("0.00");
        NumberFormat znf2 = new DecimalFormat("0.000");

        DateFormat xdf1 = new SimpleDateFormat("d-MMM");
        DateFormat xdf2 = new SimpleDateFormat("d-MMM-yyyy");
        DateFormat ydf1 = new SimpleDateFormat("d-MMM");
        DateFormat ydf2 = new SimpleDateFormat("d-MMM-yyyy");
        DateFormat zdf1 = new SimpleDateFormat("d-MMM");
        DateFormat zdf2 = new SimpleDateFormat("d-MMM-yyyy");

        StandardXYZToolTipGenerator g1 = null;
        StandardXYZToolTipGenerator g2 = null;

        g1 = new StandardXYZToolTipGenerator(f1, xnf1, ynf1, znf1);
        g2 = new StandardXYZToolTipGenerator(f1, xnf1, ynf1, znf1);
        assertEquals(g1, g2);

        // format string...
        g1 = new StandardXYZToolTipGenerator(f2, xnf1, ynf1, znf1);
        assertNotEquals(g1, g2);
        g2 = new StandardXYZToolTipGenerator(f2, xnf1, ynf1, znf1);
        assertEquals(g1, g2);

        // x number format
        g1 = new StandardXYZToolTipGenerator(f2, xnf2, ynf1, znf1);
        assertNotEquals(g1, g2);
        g2 = new StandardXYZToolTipGenerator(f2, xnf2, ynf1, znf1);
        assertEquals(g1, g2);

        // y number format
        g1 = new StandardXYZToolTipGenerator(f2, xnf2, ynf2, znf1);
        assertNotEquals(g1, g2);
        g2 = new StandardXYZToolTipGenerator(f2, xnf2, ynf2, znf1);
        assertEquals(g1, g2);

        // z number format
        g1 = new StandardXYZToolTipGenerator(f2, xnf2, ynf2, znf2);
        assertNotEquals(g1, g2);
        g2 = new StandardXYZToolTipGenerator(f2, xnf2, ynf2, znf2);
        assertEquals(g1, g2);

        g1 = new StandardXYZToolTipGenerator(f2, xdf1, ydf1, zdf1);
        g2 = new StandardXYZToolTipGenerator(f2, xdf1, ydf1, zdf1);
        assertEquals(g1, g2);

        // x date format
        g1 = new StandardXYZToolTipGenerator(f2, xdf2, ydf1, zdf1);
        assertNotEquals(g1, g2);
        g2 = new StandardXYZToolTipGenerator(f2, xdf2, ydf1, zdf1);
        assertEquals(g1, g2);

        // y date format
        g1 = new StandardXYZToolTipGenerator(f2, xdf2, ydf2, zdf1);
        assertNotEquals(g1, g2);
        g2 = new StandardXYZToolTipGenerator(f2, xdf2, ydf2, zdf1);
        assertEquals(g1, g2);

        // z date format
        g1 = new StandardXYZToolTipGenerator(f2, xdf2, ydf2, zdf2);
        assertNotEquals(g1, g2);
        g2 = new StandardXYZToolTipGenerator(f2, xdf2, ydf2, zdf2);
        assertEquals(g1, g2);

    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        StandardXYZToolTipGenerator g1
                = new StandardXYZToolTipGenerator();
        StandardXYZToolTipGenerator g2
                = new StandardXYZToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardXYZToolTipGenerator g1 = new StandardXYZToolTipGenerator();
        StandardXYZToolTipGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        StandardXYZToolTipGenerator g1 = new StandardXYZToolTipGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardXYZToolTipGenerator g1 = new StandardXYZToolTipGenerator();
        StandardXYZToolTipGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
