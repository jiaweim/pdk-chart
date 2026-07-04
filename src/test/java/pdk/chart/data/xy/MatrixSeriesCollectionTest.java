package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link MatrixSeriesCollection} class.
 */
public class MatrixSeriesCollectionTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        MatrixSeries<String> s1 = new MatrixSeries<>("Series", 2, 3);
        s1.update(0, 0, 1.1);
        MatrixSeriesCollection<String> c1 = new MatrixSeriesCollection<>();
        c1.addSeries(s1);
        MatrixSeries<String> s2 = new MatrixSeries<>("Series", 2, 3);
        s2.update(0, 0, 1.1);
        MatrixSeriesCollection<String> c2 = new MatrixSeriesCollection<>();
        c2.addSeries(s2);
        assertEquals(c1, c2);
        assertEquals(c2, c1);

        c1.addSeries(new MatrixSeries<>("Empty Series", 1, 1));
        assertNotEquals(c1, c2);

        c2.addSeries(new MatrixSeries<>("Empty Series", 1, 1));
        assertEquals(c1, c2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        MatrixSeries<String> s1 = new MatrixSeries<>("Series", 2, 3);
        s1.update(0, 0, 1.1);
        MatrixSeriesCollection<String> c1 = new MatrixSeriesCollection<>();
        c1.addSeries(s1);
        MatrixSeriesCollection<String> c2 = CloneUtils.clone(c1);

        assertNotSame(c1, c2);
        assertSame(c1.getClass(), c2.getClass());
        assertEquals(c1, c2);

        // check independence
        c2.removeAllSeries();
        assertNotEquals(c1, c2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        MatrixSeriesCollection<String> c1 = new MatrixSeriesCollection<>();
        assertTrue(c1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        MatrixSeries<String> s1 = new MatrixSeries<>("Series", 2, 3);
        s1.update(0, 0, 1.1);
        MatrixSeriesCollection<String> c1 = new MatrixSeriesCollection<>();
        c1.addSeries(s1);
        MatrixSeriesCollection<String> c2 = TestUtils.serialised(c1);
        assertEquals(c1, c2);
    }

}
