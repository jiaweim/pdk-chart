package pdk.chart.renderer.category;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.util.CloneUtils;
import pdk.chart.plot.CategoryPlot;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link LayeredBarRenderer} class.
 */
public class LayeredBarRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        LayeredBarRenderer r1 = new LayeredBarRenderer();
        LayeredBarRenderer r2 = new LayeredBarRenderer();
        assertEquals(r1, r2);

        r1.setSeriesBarWidth(1, 10.0);
        assertNotEquals(r1, r2);
        r2.setSeriesBarWidth(1, 10.0);
        assertEquals(r1, r2);

        // try an inherited attribute 
        r1.setBase(3.0);
        assertNotEquals(r1, r2);
        r2.setBase(3.0);
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        LayeredBarRenderer r1 = new LayeredBarRenderer();
        LayeredBarRenderer r2 = new LayeredBarRenderer();
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
        LayeredBarRenderer r1 = new LayeredBarRenderer();
        LayeredBarRenderer r2 = CloneUtils.clone(r1);
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
        LayeredBarRenderer r1 = new LayeredBarRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        LayeredBarRenderer r1 = new LayeredBarRenderer();
        r1.setDefaultFillPaint(Color.RED);
        r1.setSeriesBarWidth(1, 9.0);
        LayeredBarRenderer r2 = TestUtils.serialised(r1);
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
            DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
            dataset.addValue(1.0, "S1", "C1");
            CategoryPlot<String, String> plot = new CategoryPlot<>(dataset,
                    new CategoryAxis("Category"), new NumberAxis("Value"),
                    new LayeredBarRenderer());
            Chart chart = new Chart(plot);
            /* BufferedImage image = */
            chart.createBufferedImage(300, 200,
                    null);
        } catch (NullPointerException e) {
            fail("No exception should be thrown.");
        }
    }

}
