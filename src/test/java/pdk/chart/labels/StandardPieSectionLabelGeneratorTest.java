package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import java.text.AttributedString;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardPieSectionLabelGenerator} class.
 */
public class StandardPieSectionLabelGeneratorTest {

    /**
     * Test that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        StandardPieSectionLabelGenerator g1
                = new StandardPieSectionLabelGenerator();
        StandardPieSectionLabelGenerator g2
                = new StandardPieSectionLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new StandardPieSectionLabelGenerator("{0}",
                new DecimalFormat("#,##0.00"),
                NumberFormat.getPercentInstance());
        assertNotEquals(g1, g2);
        g2 = new StandardPieSectionLabelGenerator("{0}",
                new DecimalFormat("#,##0.00"),
                NumberFormat.getPercentInstance());
        assertEquals(g1, g2);

        g1 = new StandardPieSectionLabelGenerator("{0} {1}",
                new DecimalFormat("#,##0.00"),
                NumberFormat.getPercentInstance());
        assertNotEquals(g1, g2);
        g2 = new StandardPieSectionLabelGenerator("{0} {1}",
                new DecimalFormat("#,##0.00"),
                NumberFormat.getPercentInstance());
        assertEquals(g1, g2);

        g1 = new StandardPieSectionLabelGenerator("{0} {1}",
                new DecimalFormat("#,##0"), NumberFormat.getPercentInstance());
        assertNotEquals(g1, g2);
        g2 = new StandardPieSectionLabelGenerator("{0} {1}",
                new DecimalFormat("#,##0"), NumberFormat.getPercentInstance());
        assertEquals(g1, g2);

        g1 = new StandardPieSectionLabelGenerator("{0} {1}",
                new DecimalFormat("#,##0"), new DecimalFormat("0.000%"));
        assertNotEquals(g1, g2);
        g2 = new StandardPieSectionLabelGenerator("{0} {1}",
                new DecimalFormat("#,##0"), new DecimalFormat("0.000%"));
        assertEquals(g1, g2);

        AttributedString as = new AttributedString("XYZ");
        g1.setAttributedLabel(0, as);
        assertNotEquals(g1, g2);
        g2.setAttributedLabel(0, as);
        assertEquals(g1, g2);
    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        StandardPieSectionLabelGenerator g1
                = new StandardPieSectionLabelGenerator();
        StandardPieSectionLabelGenerator g2
                = new StandardPieSectionLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardPieSectionLabelGenerator g1
                = new StandardPieSectionLabelGenerator();
        StandardPieSectionLabelGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        StandardPieSectionLabelGenerator g1
                = new StandardPieSectionLabelGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardPieSectionLabelGenerator g1
                = new StandardPieSectionLabelGenerator();
        StandardPieSectionLabelGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
