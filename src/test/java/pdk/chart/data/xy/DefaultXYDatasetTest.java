package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link DefaultXYDataset}.
 */
public class DefaultXYDatasetTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DefaultXYDataset<String> d1 = new DefaultXYDataset<>();
        DefaultXYDataset<String> d2 = new DefaultXYDataset<>();
        assertEquals(d1, d2);
        assertEquals(d2, d1);

        double[] x1 = new double[]{1.0, 2.0, 3.0};
        double[] y1 = new double[]{4.0, 5.0, 6.0};
        double[][] data1 = new double[][]{x1, y1};
        double[] x2 = new double[]{1.0, 2.0, 3.0};
        double[] y2 = new double[]{4.0, 5.0, 6.0};
        double[][] data2 = new double[][]{x2, y2};
        d1.addSeries("S1", data1);
        assertNotEquals(d1, d2);
        d2.addSeries("S1", data2);
        assertEquals(d1, d2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        DefaultXYDataset<String> d1 = new DefaultXYDataset<>();
        DefaultXYDataset<String> d2 = CloneUtils.clone(d1);
        assertNotSame(d1, d2);
        assertSame(d1.getClass(), d2.getClass());
        assertEquals(d1, d2);

        // try a dataset with some content...
        double[] x1 = new double[]{1.0, 2.0, 3.0};
        double[] y1 = new double[]{4.0, 5.0, 6.0};
        double[][] data1 = new double[][]{x1, y1};
        d1.addSeries("S1", data1);
        d2 = CloneUtils.clone(d1);
        assertNotSame(d1, d2);
        assertSame(d1.getClass(), d2.getClass());
        assertEquals(d1, d2);

        // check that the clone doesn't share the same underlying arrays.
        x1[1] = 2.2;
        assertNotEquals(d1, d2);
        x1[1] = 2.0;
        assertEquals(d1, d2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        DefaultXYDataset<String> d1 = new DefaultXYDataset<>();
        assertTrue(d1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultXYDataset<String> d1 = new DefaultXYDataset<>();
        DefaultXYDataset<String> d2 = TestUtils.serialised(d1);
        assertEquals(d1, d2);

        // try a dataset with some content...
        double[] x1 = new double[]{1.0, 2.0, 3.0};
        double[] y1 = new double[]{4.0, 5.0, 6.0};
        double[][] data1 = new double[][]{x1, y1};
        d1.addSeries("S1", data1);
        d2 = TestUtils.serialised(d1);
        assertEquals(d1, d2);
    }

    /**
     * Some checks for the getSeriesKey(int) method.
     */
    @Test
    public void testGetSeriesKey() {
        DefaultXYDataset<String> d = createSampleDataset1();
        assertEquals("S1", d.getSeriesKey(0));
        assertEquals("S2", d.getSeriesKey(1));

        // check for series key out of bounds
        boolean pass = false;
        try {
            /*Comparable k =*/
            d.getSeriesKey(-1);
        } catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);

        pass = false;
        try {
            /*Comparable k =*/
            d.getSeriesKey(2);
        } catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);
    }

    /**
     * Some checks for the indexOf(Comparable) method.
     */
    @Test
    public void testIndexOf() {
        DefaultXYDataset<String> d = createSampleDataset1();
        assertEquals(0, d.indexOf("S1"));
        assertEquals(1, d.indexOf("S2"));
        assertEquals(-1, d.indexOf("Green Eggs and Ham"));
        assertEquals(-1, d.indexOf(null));
    }

    static final double EPSILON = 0.0000000001;

    /**
     * Some tests for the addSeries() method.
     */
    @Test
    public void testAddSeries() {
        DefaultXYDataset<String> d = new DefaultXYDataset<>();
        d.addSeries("S1", new double[][]{{1.0}, {2.0}});
        assertEquals(1, d.getSeriesCount());
        assertEquals("S1", d.getSeriesKey(0));

        // check that adding a series will overwrite the old series
        d.addSeries("S1", new double[][]{{11.0}, {12.0}});
        assertEquals(1, d.getSeriesCount());
        assertEquals(12.0, d.getYValue(0, 0), EPSILON);

        // check null key
        boolean pass = false;
        try {
            d.addSeries(null, new double[][]{{1.0}, {2.0}});
        } catch (IllegalArgumentException e) {
            pass = true;
        }
        assertTrue(pass);
    }

    /**
     * Creates a sample dataset for testing.
     *
     * @return A sample dataset.
     */
    public DefaultXYDataset<String> createSampleDataset1() {
        DefaultXYDataset<String> d = new DefaultXYDataset<>();
        double[] x1 = new double[]{1.0, 2.0, 3.0};
        double[] y1 = new double[]{4.0, 5.0, 6.0};
        double[][] data1 = new double[][]{x1, y1};
        d.addSeries("S1", data1);

        double[] x2 = new double[]{1.0, 2.0, 3.0};
        double[] y2 = new double[]{4.0, 5.0, 6.0};
        double[][] data2 = new double[][]{x2, y2};
        d.addSeries("S2", data2);
        return d;
    }

}
