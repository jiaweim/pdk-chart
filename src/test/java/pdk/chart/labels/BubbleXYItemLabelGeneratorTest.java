package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.util.CloneUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link BubbleXYItemLabelGenerator} class.
 */
public class BubbleXYItemLabelGeneratorTest {

    /**
     * A series of tests for the equals() method.
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

        BubbleXYItemLabelGenerator g1 = null;
        BubbleXYItemLabelGenerator g2 = null;

        g1 = new BubbleXYItemLabelGenerator(f1, xnf1, ynf1, znf1);
        g2 = new BubbleXYItemLabelGenerator(f1, xnf1, ynf1, znf1);
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new BubbleXYItemLabelGenerator(f2, xnf1, ynf1, znf1);
        assertNotEquals(g1, g2);
        g2 = new BubbleXYItemLabelGenerator(f2, xnf1, ynf1, znf1);
        assertEquals(g1, g2);

        g1 = new BubbleXYItemLabelGenerator(f2, xnf2, ynf1, znf1);
        assertNotEquals(g1, g2);
        g2 = new BubbleXYItemLabelGenerator(f2, xnf2, ynf1, znf1);
        assertEquals(g1, g2);

        g1 = new BubbleXYItemLabelGenerator(f2, xnf2, ynf2, znf1);
        assertNotEquals(g1, g2);
        g2 = new BubbleXYItemLabelGenerator(f2, xnf2, ynf2, znf1);
        assertEquals(g1, g2);

        g1 = new BubbleXYItemLabelGenerator(f2, xnf2, ynf2, znf2);
        assertNotEquals(g1, g2);
        g2 = new BubbleXYItemLabelGenerator(f2, xnf2, ynf2, znf2);
        assertEquals(g1, g2);

        DateFormat xdf1 = new SimpleDateFormat("d-MMM");
        DateFormat xdf2 = new SimpleDateFormat("d-MMM-yyyy");
        DateFormat ydf1 = new SimpleDateFormat("d-MMM");
        DateFormat ydf2 = new SimpleDateFormat("d-MMM-yyyy");
        DateFormat zdf1 = new SimpleDateFormat("d-MMM");
        DateFormat zdf2 = new SimpleDateFormat("d-MMM-yyyy");

        g1 = new BubbleXYItemLabelGenerator(f1, xdf1, ydf1, zdf1);
        g2 = new BubbleXYItemLabelGenerator(f1, xdf1, ydf1, zdf1);
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new BubbleXYItemLabelGenerator(f1, xdf2, ydf1, zdf1);
        assertNotEquals(g1, g2);
        g2 = new BubbleXYItemLabelGenerator(f1, xdf2, ydf1, zdf1);
        assertEquals(g1, g2);

        g1 = new BubbleXYItemLabelGenerator(f1, xdf2, ydf2, zdf1);
        assertNotEquals(g1, g2);
        g2 = new BubbleXYItemLabelGenerator(f1, xdf2, ydf2, zdf1);
        assertEquals(g1, g2);

        g1 = new BubbleXYItemLabelGenerator(f1, xdf2, ydf2, zdf2);
        assertNotEquals(g1, g2);
        g2 = new BubbleXYItemLabelGenerator(f1, xdf2, ydf2, zdf2);
        assertEquals(g1, g2);
    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        BubbleXYItemLabelGenerator g1
                = new BubbleXYItemLabelGenerator();
        BubbleXYItemLabelGenerator g2
                = new BubbleXYItemLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        BubbleXYItemLabelGenerator g1 = new BubbleXYItemLabelGenerator();
        BubbleXYItemLabelGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        BubbleXYItemLabelGenerator g1 = new BubbleXYItemLabelGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        BubbleXYItemLabelGenerator g1 = new BubbleXYItemLabelGenerator();
        BubbleXYItemLabelGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

    /**
     * Some checks for the testGenerateLabel() method.
     */
    @Test
    public void testGenerateLabel() {
        // check handling when the dataset is a regular XYDataset, not an
        // XYZDataset...
        XYSeries<String> s1 = new XYSeries<>("S1");
        s1.add(1.0, 2.0);
        s1.add(2.2, 3.3);
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>(s1);
        BubbleXYItemLabelGenerator g = new BubbleXYItemLabelGenerator();
        assertEquals("{3}", g.generateLabel(dataset, 0, 0));
        assertEquals("{3}", g.generateLabel(dataset, 0, 1));
    }

}
