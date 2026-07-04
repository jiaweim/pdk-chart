package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardCategoryToolTipGenerator} class.
 */
public class StandardCategoryToolTipGeneratorTest {

    /**
     * Tests the equals() method.
     */
    @Test
    public void testEquals() {

        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator();
        StandardCategoryToolTipGenerator g2
                = new StandardCategoryToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new StandardCategoryToolTipGenerator("{0}",
                new DecimalFormat("0.000"));
        assertNotEquals(g1, g2);
        g2 = new StandardCategoryToolTipGenerator("{0}",
                new DecimalFormat("0.000"));
        assertEquals(g1, g2);

        g1 = new StandardCategoryToolTipGenerator("{1}",
                new DecimalFormat("0.000"));
        assertNotEquals(g1, g2);
        g2 = new StandardCategoryToolTipGenerator("{1}",
                new DecimalFormat("0.000"));
        assertEquals(g1, g2);

        g1 = new StandardCategoryToolTipGenerator("{2}",
                new SimpleDateFormat("d-MMM"));
        assertNotEquals(g1, g2);
        g2 = new StandardCategoryToolTipGenerator("{2}",
                new SimpleDateFormat("d-MMM"));
        assertEquals(g1, g2);

    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator();
        StandardCategoryToolTipGenerator g2
                = new StandardCategoryToolTipGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator();
        StandardCategoryToolTipGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator("{2}",
                DateFormat.getInstance());
        StandardCategoryToolTipGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

    /**
     * A test for bug 1481087.
     */
    @Test
    public void testEquals1481087() {
        StandardCategoryToolTipGenerator g1
                = new StandardCategoryToolTipGenerator("{0}",
                new DecimalFormat("0.00"));
        StandardCategoryItemLabelGenerator g2
                = new StandardCategoryItemLabelGenerator("{0}",
                new DecimalFormat("0.00"));
        assertNotEquals(g1, g2);
    }

}
