package pdk.chart.renderer.category;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.Range;
import pdk.chart.data.category.DefaultIntervalCategoryDataset;
import pdk.chart.util.CloneUtils;
import pdk.chart.plot.CategoryPlot;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link IntervalBarRenderer} class.
 */
public class IntervalBarRendererTest {

    /**
     * Problem that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        IntervalBarRenderer r1 = new IntervalBarRenderer();
        IntervalBarRenderer r2 = new IntervalBarRenderer();
        assertEquals(r1, r2);

        // the renderer should not be equal to a BarRenderer
        BarRenderer br = new BarRenderer();
        assertNotEquals(r1, br);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        IntervalBarRenderer r1 = new IntervalBarRenderer();
        IntervalBarRenderer r2 = new IntervalBarRenderer();
        assertEquals(r1, r2);
        int h1 = r1.hashCode();
        int h2 = r2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        IntervalBarRenderer r1 = new IntervalBarRenderer();
        IntervalBarRenderer r2 = CloneUtils.clone(r1);
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

    /**
     * Check that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        IntervalBarRenderer r1 = new IntervalBarRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        IntervalBarRenderer r1 = new IntervalBarRenderer();
        IntervalBarRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

    /**
     * Draws the chart with a {@code null} info object to make sure that
     * no exceptions are thrown (particularly by code in the renderer).
     */
    @Test
    public void testDrawWithNullInfo() {
        try {
            double[][] starts = new double[][]{{0.1, 0.2, 0.3},
                    {0.3, 0.4, 0.5}};
            double[][] ends = new double[][]{{0.5, 0.6, 0.7}, {0.7, 0.8, 0.9}};
            DefaultIntervalCategoryDataset dataset
                    = new DefaultIntervalCategoryDataset(starts, ends);
            IntervalBarRenderer renderer = new IntervalBarRenderer();
            CategoryPlot<String, String> plot = new CategoryPlot<>(dataset,
                    new CategoryAxis("Category"), new NumberAxis("Value"),
                    renderer);
            Chart chart = new Chart(plot);
            /* BufferedImage image = */
            chart.createBufferedImage(300, 200,
                    null);
        } catch (NullPointerException e) {
            fail("No exception should be thrown.");
        }
    }

    /**
     * Some checks for the findRangeBounds() method.
     */
    @Test
    public void testFindRangeBounds() {
        IntervalBarRenderer r = new IntervalBarRenderer();
        assertNull(r.findRangeBounds(null));

        // an empty dataset should return a null range
        DefaultIntervalCategoryDataset dataset
                = new DefaultIntervalCategoryDataset(new double[0][0],
                new double[0][0]);
        assertNull(r.findRangeBounds(dataset));

        double[][] starts = new double[][]{{0.1, 0.2, 0.3}, {0.3, 0.4, 0.5}};
        double[][] ends = new double[][]{{0.5, 0.6, 0.7}, {0.7, 0.8, 0.9}};
        dataset = new DefaultIntervalCategoryDataset(starts, ends);
        assertEquals(new Range(0.0, 0.9), r.findRangeBounds(dataset));
        r.setIncludeBaseInRange(false);
        assertEquals(new Range(0.1, 0.9), r.findRangeBounds(dataset));
        r.setIncludeBaseInRange(true);

        r.setSeriesVisible(1, Boolean.FALSE);
        assertEquals(new Range(0.0, 0.7), r.findRangeBounds(dataset));
    }

}
