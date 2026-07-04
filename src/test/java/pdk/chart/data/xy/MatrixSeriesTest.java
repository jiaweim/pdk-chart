package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests for the {@link MatrixSeries} class.
 */
public class MatrixSeriesTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        MatrixSeries<String> m1 = new MatrixSeries<>("Test", 8, 3);
        m1.update(0, 0, 11.0);
        m1.update(7, 2, 22.0);
        MatrixSeries<String> m2 = new MatrixSeries<>("Test", 8, 3);
        m2.update(0, 0, 11.0);
        m2.update(7, 2, 22.0);
        assertEquals(m1, m2);
        assertEquals(m2, m1);

        m1 = new MatrixSeries<>("Test 2", 8, 3);
        assertNotEquals(m1, m2);
        m2 = new MatrixSeries<>("Test 2", 8, 3);
        assertEquals(m1, m2);

        m1 = new MatrixSeries<>("Test 2", 10, 3);
        assertNotEquals(m1, m2);
        m2 = new MatrixSeries<>("Test 2", 10, 3);
        assertEquals(m1, m2);

        m1 = new MatrixSeries<>("Test 2", 10, 5);
        assertNotEquals(m1, m2);
        m2 = new MatrixSeries<>("Test 2", 10, 5);
        assertEquals(m1, m2);

        m1.update(0, 0, 99);
        assertNotEquals(m1, m2);
        m2.update(0, 0, 99);
        assertEquals(m1, m2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        MatrixSeries<String> m1 = new MatrixSeries<>("Test", 8, 3);
        m1.update(0, 0, 11.0);
        m1.update(7, 2, 22.0);
        MatrixSeries<String> m2 = CloneUtils.clone(m1);
        assertNotSame(m1, m2);
        assertSame(m1.getClass(), m2.getClass());
        assertEquals(m1, m2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        MatrixSeries<String> m1 = new MatrixSeries<>("Test", 8, 3);
        m1.update(0, 0, 11.0);
        m1.update(7, 2, 22.0);
        MatrixSeries<String> m2 = TestUtils.serialised(m1);
        assertEquals(m1, m2);
    }

    /**
     * Tests the getItemColumn() method.
     */
    @Test
    public void testGetItemColumn() {
        MatrixSeries<String> m = new MatrixSeries<>("Test", 3, 2);
        assertEquals(0, m.getItemColumn(0));
        assertEquals(1, m.getItemColumn(1));
        assertEquals(0, m.getItemColumn(2));
        assertEquals(1, m.getItemColumn(3));
        assertEquals(0, m.getItemColumn(4));
        assertEquals(1, m.getItemColumn(5));
    }

    /**
     * Tests the getItemRow() method.
     */
    @Test
    public void testGetItemRow() {
        MatrixSeries<String> m = new MatrixSeries<>("Test", 3, 2);
        assertEquals(0, m.getItemRow(0));
        assertEquals(0, m.getItemRow(1));
        assertEquals(1, m.getItemRow(2));
        assertEquals(1, m.getItemRow(3));
        assertEquals(2, m.getItemRow(4));
        assertEquals(2, m.getItemRow(5));
    }

    /**
     * Tests the getItem() method.
     */
    @Test
    public void testGetItem() {
        MatrixSeries<String> m = new MatrixSeries<>("Test", 3, 2);
        m.update(0, 0, 0.0);
        m.update(0, 1, 1.0);
        m.update(1, 0, 2.0);
        m.update(1, 1, 3.0);
        m.update(2, 0, 4.0);
        m.update(2, 1, 5.0);
        assertEquals(0.0, m.getItem(0).doubleValue(), 0.001);
        assertEquals(1.0, m.getItem(1).doubleValue(), 0.001);
        assertEquals(2.0, m.getItem(2).doubleValue(), 0.001);
        assertEquals(3.0, m.getItem(3).doubleValue(), 0.001);
        assertEquals(4.0, m.getItem(4).doubleValue(), 0.001);
        assertEquals(5.0, m.getItem(5).doubleValue(), 0.001);
    }
}
