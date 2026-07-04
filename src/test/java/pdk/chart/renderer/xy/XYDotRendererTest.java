package pdk.chart.renderer.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.util.CloneUtils;
import pdk.chart.legend.LegendItem;
import pdk.chart.plot.XYPlot;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link XYDotRenderer} class.
 */
public class XYDotRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        XYDotRenderer r1 = new XYDotRenderer();
        XYDotRenderer r2 = new XYDotRenderer();
        assertEquals(r1, r2);

        r1.setDotWidth(11);
        assertNotEquals(r1, r2);
        r2.setDotWidth(11);
        assertEquals(r1, r2);

        r1.setDotHeight(12);
        assertNotEquals(r1, r2);
        r2.setDotHeight(12);
        assertEquals(r1, r2);

        r1.setLegendShape(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        assertNotEquals(r1, r2);
        r2.setLegendShape(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        XYDotRenderer r1 = new XYDotRenderer();
        XYDotRenderer r2 = new XYDotRenderer();
        assertEquals(r1, r2);
        int h1 = r1.hashCode();
        int h2 = r2.hashCode();
        assertEquals(h1, h2);

        r1.setDotHeight(12);
        r2.setDotHeight(12);
        assertEquals(r1, r2);
        h1 = r1.hashCode();
        h2 = r2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        XYDotRenderer r1 = new XYDotRenderer();
        XYDotRenderer r2 = CloneUtils.clone(r1);
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        XYDotRenderer r1 = new XYDotRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XYDotRenderer r1 = new XYDotRenderer();
        XYDotRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
    }

    /**
     * A check for the datasetIndex and seriesIndex fields in the LegendItem
     * returned by the getLegendItem() method.
     */
    @Test
    public void testGetLegendItemSeriesIndex() {
        XYSeriesCollection<String> d1 = new XYSeriesCollection<>();
        XYSeries<String> s1 = new XYSeries<>("S1");
        s1.add(1.0, 1.1);
        XYSeries<String> s2 = new XYSeries<>("S2");
        s2.add(1.0, 1.1);
        d1.addSeries(s1);
        d1.addSeries(s2);

        XYSeriesCollection<String> d2 = new XYSeriesCollection<>();
        XYSeries<String> s3 = new XYSeries<>("S3");
        s3.add(1.0, 1.1);
        XYSeries<String> s4 = new XYSeries<>("S4");
        s4.add(1.0, 1.1);
        XYSeries<String> s5 = new XYSeries<>("S5");
        s5.add(1.0, 1.1);
        d2.addSeries(s3);
        d2.addSeries(s4);
        d2.addSeries(s5);

        XYDotRenderer r = new XYDotRenderer();
        XYPlot<String> plot = new XYPlot<>(d1, new NumberAxis("x"),
                new NumberAxis("y"), r);
        plot.setDataset(1, d2);
        /*JFreeChart chart =*/
        new Chart(plot);
        LegendItem li = r.getLegendItem(1, 2);
        assertEquals("S5", li.getLabel());
        assertEquals(1, li.getDatasetIndex());
        assertEquals(2, li.getSeriesIndex());
    }

}
