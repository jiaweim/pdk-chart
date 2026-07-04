package pdk.chart.renderer.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.DefaultTableXYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.util.CloneUtils;
import pdk.chart.plot.XYPlot;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link XYStepRenderer} class.
 */
public class XYStepRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        XYStepRenderer r1 = new XYStepRenderer();
        XYStepRenderer r2 = new XYStepRenderer();
        assertEquals(r1, r2);

        r1.setStepPoint(0.44);
        assertNotEquals(r1, r2);
        r2.setStepPoint(0.44);
        assertEquals(r1, r2);

        // try something from the base class
        r1.setDefaultCreateEntities(false);
        assertNotEquals(r1, r2);
        r2.setDefaultCreateEntities(false);
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        XYStepRenderer r1 = new XYStepRenderer();
        r1.setStepPoint(0.123);
        XYStepRenderer r2 = new XYStepRenderer();
        r2.setStepPoint(0.123);
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
        XYStepRenderer r1 = new XYStepRenderer();
        XYStepRenderer r2 = CloneUtils.clone(r1);
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        XYStepRenderer r1 = new XYStepRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XYStepRenderer r1 = new XYStepRenderer();
        r1.setStepPoint(0.123);
        XYStepRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
    }

    /**
     * Draws the chart with a {@code null} info object to make sure that
     * no exceptions are thrown (particularly by code in the renderer).
     */
    @Test
    public void testDrawWithNullInfo() {
        try {
            DefaultTableXYDataset<String> dataset = new DefaultTableXYDataset<>();

            XYSeries<String> s1 = new XYSeries<>("Series 1", true, false);
            s1.add(5.0, 5.0);
            s1.add(10.0, 15.5);
            s1.add(15.0, 9.5);
            s1.add(20.0, 7.5);
            dataset.addSeries(s1);

            XYSeries<String> s2 = new XYSeries<>("Series 2", true, false);
            s2.add(5.0, 5.0);
            s2.add(10.0, 15.5);
            s2.add(15.0, 9.5);
            s2.add(20.0, 3.5);
            dataset.addSeries(s2);
            XYPlot<String> plot = new XYPlot<>(dataset,
                    new NumberAxis("X"), new NumberAxis("Y"),
                    new XYStepRenderer());
            Chart chart = new Chart(plot);
            /* BufferedImage image = */
            chart.createBufferedImage(300, 200,
                    null);
        } catch (NullPointerException e) {
            fail("No exception should be thrown.");
        }
    }

    /**
     * Draws the chart with a {@code null} value in the dataset to make
     * sure that no exceptions are thrown.
     */
    @Test
    public void testDrawWithNullValue() {
        try {
            DefaultTableXYDataset<String> dataset = new DefaultTableXYDataset<>();

            XYSeries<String> s1 = new XYSeries<>("Series 1", true, false);
            s1.add(5.0, 5.0);
            s1.add(10.0, null);
            s1.add(15.0, 9.5);
            s1.add(20.0, 7.5);
            dataset.addSeries(s1);

            XYSeries<String> s2 = new XYSeries<>("Series 2", true, false);
            s2.add(5.0, 5.0);
            s2.add(10.0, 15.5);
            s2.add(15.0, null);
            s2.add(20.0, null);
            dataset.addSeries(s2);
            XYPlot<String> plot = new XYPlot<>(dataset,
                    new NumberAxis("X"), new NumberAxis("Y"),
                    new XYStepRenderer());
            Chart chart = new Chart(plot);
            /* BufferedImage image = */
            chart.createBufferedImage(300, 200,
                    null);
        } catch (NullPointerException e) {
            fail("No exception should be thrown.");
        }
    }

}
