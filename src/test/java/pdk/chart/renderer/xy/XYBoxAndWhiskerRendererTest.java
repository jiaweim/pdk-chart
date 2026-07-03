package pdk.chart.renderer.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.data.statistics.BoxAndWhiskerItem;
import pdk.chart.data.statistics.DefaultBoxAndWhiskerXYDataset;
import pdk.chart.internal.CloneUtils;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link XYBoxAndWhiskerRenderer} class.
 */
public class XYBoxAndWhiskerRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        XYBoxAndWhiskerRenderer r1 = new XYBoxAndWhiskerRenderer();
        XYBoxAndWhiskerRenderer r2 = new XYBoxAndWhiskerRenderer();
        assertEquals(r1, r2);

        r1.setArtifactPaint(new GradientPaint(1.0f, 2.0f, Color.GREEN,
                3.0f, 4.0f, Color.RED));
        assertNotEquals(r1, r2);
        r2.setArtifactPaint(new GradientPaint(1.0f, 2.0f, Color.GREEN,
                3.0f, 4.0f, Color.RED));
        assertEquals(r1, r2);

        r1.setBoxWidth(0.55);
        assertNotEquals(r1, r2);
        r2.setBoxWidth(0.55);
        assertEquals(r1, r2);

        r1.setFillBox(!r1.getFillBox());
        assertNotEquals(r1, r2);
        r2.setFillBox(!r2.getFillBox());
        assertEquals(r1, r2);

        r1.setBoxPaint(Color.YELLOW);
        assertNotEquals(r1, r2);
        r2.setBoxPaint(Color.YELLOW);
        assertEquals(r1, r2);

        // check boxPaint null also
        r1.setBoxPaint(null);
        assertNotEquals(r1, r2);
        r2.setBoxPaint(null);
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        XYBoxAndWhiskerRenderer r1 = new XYBoxAndWhiskerRenderer();
        XYBoxAndWhiskerRenderer r2 = new XYBoxAndWhiskerRenderer();
        assertEquals(r1, r2);
        int h1 = r1.hashCode();
        int h2 = r2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        XYBoxAndWhiskerRenderer r1 = new XYBoxAndWhiskerRenderer();
        XYBoxAndWhiskerRenderer r2 = CloneUtils.clone(r1);
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        XYBoxAndWhiskerRenderer r1 = new XYBoxAndWhiskerRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XYBoxAndWhiskerRenderer r1 = new XYBoxAndWhiskerRenderer();
        XYBoxAndWhiskerRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
    }

    /**
     * A test for bug report 2909215.
     */
    @Test
    public void test2909215() {
        DefaultBoxAndWhiskerXYDataset<String> d1 = new DefaultBoxAndWhiskerXYDataset<>("Series");
        d1.add(new Date(1L), new BoxAndWhiskerItem(1.0, 2.0, 3.0, 4.0, 5.0, 6.0,
                null, null, null));
        Chart chart = JChart.boxAndWhisker(d1, "X",
                "Y", "Title", true);
        try {
            BufferedImage image = new BufferedImage(400, 200,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            chart.draw(g2, new Rectangle2D.Double(0, 0, 400, 200), null, null);
            g2.dispose();
        } catch (Exception e) {
            fail("No exception should be thrown.");
        }
    }

}

