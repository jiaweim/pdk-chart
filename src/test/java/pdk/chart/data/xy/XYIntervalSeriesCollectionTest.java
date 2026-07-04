package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link XYIntervalSeriesCollection} class.
 */
public class XYIntervalSeriesCollectionTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        XYIntervalSeriesCollection<String> c1 = new XYIntervalSeriesCollection<>();
        XYIntervalSeriesCollection<String> c2 = new XYIntervalSeriesCollection<>();
        assertEquals(c1, c2);

        // add a series
        XYIntervalSeries<String> s1 = new XYIntervalSeries<>("Series");
        s1.add(1.0, 1.1, 1.2, 1.3, 1.4, 1.5);
        c1.addSeries(s1);
        assertNotEquals(c1, c2);
        XYIntervalSeries<String> s2 = new XYIntervalSeries<>("Series");
        s2.add(1.0, 1.1, 1.2, 1.3, 1.4, 1.5);
        c2.addSeries(s2);
        assertEquals(c1, c2);

        // add an empty series
        c1.addSeries(new XYIntervalSeries<>("Empty Series"));
        assertNotEquals(c1, c2);
        c2.addSeries(new XYIntervalSeries<>("Empty Series"));
        assertEquals(c1, c2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        XYIntervalSeriesCollection<String> c1 = new XYIntervalSeriesCollection<>();
        XYIntervalSeries<String> s1 = new XYIntervalSeries<>("Series");
        s1.add(1.0, 1.1, 1.2, 1.3, 1.4, 1.5);
        XYIntervalSeriesCollection<String> c2 = CloneUtils.clone(c1);
        assertNotSame(c1, c2);
        assertSame(c1.getClass(), c2.getClass());
        assertEquals(c1, c2);

        // check independence
        c1.addSeries(new XYIntervalSeries<>("Empty"));
        assertNotEquals(c1, c2);
        c2.addSeries(new XYIntervalSeries<>("Empty"));
        assertEquals(c1, c2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        XYIntervalSeriesCollection<String> c1 = new XYIntervalSeriesCollection<>();
        assertTrue(c1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XYIntervalSeriesCollection<String> c1 = new XYIntervalSeriesCollection<>();
        XYIntervalSeries<String> s1 = new XYIntervalSeries<>("Series");
        s1.add(1.0, 1.1, 1.2, 1.3, 1.4, 1.5);
        XYIntervalSeriesCollection<String> c2 = TestUtils.serialised(c1);
        assertEquals(c1, c2);

        // check independence
        c1.addSeries(new XYIntervalSeries<>("Empty"));
        assertNotEquals(c1, c2);
        c2.addSeries(new XYIntervalSeries<>("Empty"));
        assertEquals(c1, c2);
    }

    /**
     * Some basic checks for the removeSeries() method.
     */
    @Test
    public void testRemoveSeries() {
        XYIntervalSeriesCollection<String> c = new XYIntervalSeriesCollection<>();
        XYIntervalSeries<String> s1 = new XYIntervalSeries<>("s1");
        c.addSeries(s1);
        c.removeSeries(0);
        assertEquals(0, c.getSeriesCount());
        c.addSeries(s1);

        boolean pass = false;
        try {
            c.removeSeries(-1);
        } catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);

        pass = false;
        try {
            c.removeSeries(1);
        } catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);
    }

    /**
     * A test for bug report 1170825 (originally affected XYSeriesCollection,
     * this test is just copied over).
     */
    @Test
    public void test1170825() {
        XYIntervalSeries<String> s1 = new XYIntervalSeries<>("Series1");
        XYIntervalSeriesCollection<String> dataset = new XYIntervalSeriesCollection<>();
        dataset.addSeries(s1);
        try {
            /* XYSeries s = */
            dataset.getSeries(1);
        } catch (IllegalArgumentException e) {
            // correct outcome
        } catch (IndexOutOfBoundsException e) {
            fail();  // wrong outcome
        }
    }

}
