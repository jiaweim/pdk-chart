package pdk.chart.renderer.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.DefaultXYZDataset;
import pdk.chart.util.CloneUtils;
import pdk.chart.legend.LegendItem;
import pdk.chart.plot.XYPlot;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link XYBubbleRenderer} class.
 */
public class XYBubbleRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        XYBubbleRenderer r1 = new XYBubbleRenderer();
        XYBubbleRenderer r2 = new XYBubbleRenderer();
        assertEquals(r1, r2);

        r1 = new XYBubbleRenderer(XYBubbleRenderer.SCALE_ON_RANGE_AXIS);
        assertNotEquals(r1, r2);
        r2 = new XYBubbleRenderer(XYBubbleRenderer.SCALE_ON_RANGE_AXIS);
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        XYBubbleRenderer r1 = new XYBubbleRenderer();
        XYBubbleRenderer r2 = new XYBubbleRenderer();
        assertEquals(r1, r2);
        int h1 = r1.hashCode();
        int h2 = r2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        XYBubbleRenderer r1 = new XYBubbleRenderer();
        XYBubbleRenderer r2 = CloneUtils.clone(r1);
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        XYBubbleRenderer r1 = new XYBubbleRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XYBubbleRenderer r1 = new XYBubbleRenderer();
        XYBubbleRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
    }

    /**
     * A check for the datasetIndex and seriesIndex fields in the LegendItem
     * returned by the getLegendItem() method.
     */
    @Test
    public void testGetLegendItemSeriesIndex() {
        DefaultXYZDataset<String> d1 = new DefaultXYZDataset<>();
        double[] x = {2.1, 2.3, 2.3, 2.2, 2.2, 1.8, 1.8, 1.9, 2.3, 3.8};
        double[] y = {14.1, 11.1, 10.0, 8.8, 8.7, 8.4, 5.4, 4.1, 4.1, 25};
        double[] z = {2.4, 2.7, 2.7, 2.2, 2.2, 2.2, 2.1, 2.2, 1.6, 4};
        double[][] s1 = new double[][]{x, y, z};
        d1.addSeries("S1", s1);
        x = new double[]{2.1};
        y = new double[]{14.1};
        z = new double[]{2.4};
        double[][] s2 = new double[][]{x, y, z};
        d1.addSeries("S2", s2);

        DefaultXYZDataset<String> d2 = new DefaultXYZDataset<>();
        x = new double[]{2.1};
        y = new double[]{14.1};
        z = new double[]{2.4};
        double[][] s3 = new double[][]{x, y, z};
        d2.addSeries("S3", s3);
        x = new double[]{2.1};
        y = new double[]{14.1};
        z = new double[]{2.4};
        double[][] s4 = new double[][]{x, y, z};
        d2.addSeries("S4", s4);
        x = new double[]{2.1};
        y = new double[]{14.1};
        z = new double[]{2.4};
        double[][] s5 = new double[][]{x, y, z};
        d2.addSeries("S5", s5);

        XYBubbleRenderer r = new XYBubbleRenderer();
        XYPlot<String> plot = new XYPlot<>(d1, new NumberAxis("x"),
                new NumberAxis("y"), r);
        plot.setDataset(1, d2);
        Chart chart = new Chart(plot);
        LegendItem li = r.getLegendItem(1, 2);
        assertEquals("S5", li.getLabel());
        assertEquals(1, li.getDatasetIndex());
        assertEquals(2, li.getSeriesIndex());
    }

}
