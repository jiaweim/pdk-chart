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
 * Tests for the {@link IntervalCategoryToolTipGenerator} class.
 */
public class IntervalCategoryToolTipGeneratorTest {

    /**
     * Tests the equals() method.
     */
    @Test
    public void testEquals() {
        IntervalCategoryToolTipGenerator g1
                = new IntervalCategoryToolTipGenerator();
        IntervalCategoryToolTipGenerator g2
                = new IntervalCategoryToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new IntervalCategoryToolTipGenerator("{3} - {4}",
                new DecimalFormat("0.000"));
        assertNotEquals(g1, g2);
        g2 = new IntervalCategoryToolTipGenerator("{3} - {4}",
                new DecimalFormat("0.000"));
        assertEquals(g1, g2);

        g1 = new IntervalCategoryToolTipGenerator("{3} - {4}",
                new SimpleDateFormat("d-MMM"));
        assertNotEquals(g1, g2);
        g2 = new IntervalCategoryToolTipGenerator("{3} - {4}",
                new SimpleDateFormat("d-MMM"));
        assertEquals(g1, g2);
    }

    /**
     * Check that the subclass is not equal to an instance of the superclass.
     */
    @Test
    public void testEquals2() {
        IntervalCategoryToolTipGenerator g1
                = new IntervalCategoryToolTipGenerator();
        StandardCategoryToolTipGenerator g2
                = new StandardCategoryToolTipGenerator(
                IntervalCategoryToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT_STRING,
                NumberFormat.getInstance());
        assertNotEquals(g1, g2);
    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        IntervalCategoryToolTipGenerator g1
                = new IntervalCategoryToolTipGenerator();
        IntervalCategoryToolTipGenerator g2
                = new IntervalCategoryToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        IntervalCategoryToolTipGenerator g1
                = new IntervalCategoryToolTipGenerator();
        IntervalCategoryToolTipGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        IntervalCategoryToolTipGenerator g1
                = new IntervalCategoryToolTipGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        IntervalCategoryToolTipGenerator g1
                = new IntervalCategoryToolTipGenerator("{3} - {4}",
                DateFormat.getInstance());
        IntervalCategoryToolTipGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
