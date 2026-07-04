package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some tests for the {@link XYBarDataset} class.
 */
public class XYBarDatasetTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DefaultXYDataset<String> d1 = new DefaultXYDataset<>();
        double[] x1 = new double[]{1.0, 2.0, 3.0};
        double[] y1 = new double[]{4.0, 5.0, 6.0};
        double[][] data1 = new double[][]{x1, y1};
        d1.addSeries("S1", data1);
        DefaultXYDataset<String> d2 = new DefaultXYDataset<>();
        double[] x2 = new double[]{1.0, 2.0, 3.0};
        double[] y2 = new double[]{4.0, 5.0, 6.0};
        double[][] data2 = new double[][]{x2, y2};
        d2.addSeries("S1", data2);

        XYBarDataset<String> bd1 = new XYBarDataset<>(d1, 5.0);
        XYBarDataset<String> bd2 = new XYBarDataset<>(d2, 5.0);
        assertEquals(bd1, bd2);
        assertEquals(bd2, bd1);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        DefaultXYDataset<String> d1 = new DefaultXYDataset<>();
        double[] x1 = new double[]{1.0, 2.0, 3.0};
        double[] y1 = new double[]{4.0, 5.0, 6.0};
        double[][] data1 = new double[][]{x1, y1};
        d1.addSeries("S1", data1);
        XYBarDataset<String> bd1 = new XYBarDataset<>(d1, 5.0);
        XYBarDataset<String> bd2 = CloneUtils.clone(bd1);
        assertNotSame(bd1, bd2);
        assertSame(bd1.getClass(), bd2.getClass());
        assertEquals(bd1, bd2);

        // check independence
        d1 = (DefaultXYDataset) bd1.getUnderlyingDataset();
        d1.addSeries("S2", new double[][]{{1.0}, {2.0}});
        assertNotEquals(bd1, bd2);
        DefaultXYDataset<String> d2 = (DefaultXYDataset) bd2.getUnderlyingDataset();
        d2.addSeries("S2", new double[][]{{1.0}, {2.0}});
        assertEquals(bd1, bd2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        DefaultXYDataset<String> d1 = new DefaultXYDataset<>();
        double[] x1 = new double[]{1.0, 2.0, 3.0};
        double[] y1 = new double[]{4.0, 5.0, 6.0};
        double[][] data1 = new double[][]{x1, y1};
        d1.addSeries("S1", data1);
        XYBarDataset<String> bd1 = new XYBarDataset<>(d1, 5.0);
        assertTrue(bd1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultXYDataset<String> d1 = new DefaultXYDataset<>();
        double[] x1 = new double[]{1.0, 2.0, 3.0};
        double[] y1 = new double[]{4.0, 5.0, 6.0};
        double[][] data1 = new double[][]{x1, y1};
        d1.addSeries("S1", data1);
        XYBarDataset<String> bd1 = new XYBarDataset<>(d1, 5.0);
        XYBarDataset<String> bd2 = TestUtils.serialised(bd1);
        assertEquals(bd1, bd2);
    }

}
