package pdk.chart.plot.dial;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.StandardGradientPaintTransformer;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link DialBackground} class.
 */
public class DialBackgroundTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DialBackground b1 = new DialBackground();
        DialBackground b2 = new DialBackground();
        assertEquals(b1, b2);

        // paint
        b1.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f, 4.0f,
                Color.YELLOW));
        assertNotEquals(b1, b2);
        b2.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f, 4.0f,
                Color.YELLOW));
        assertEquals(b1, b2);

        // gradient paint transformer
        b1.setGradientPaintTransformer(new StandardGradientPaintTransformer(
                GradientPaintTransformType.CENTER_VERTICAL));
        assertNotEquals(b1, b2);
        b2.setGradientPaintTransformer(new StandardGradientPaintTransformer(
                GradientPaintTransformType.CENTER_VERTICAL));
        assertEquals(b1, b2);

        // check an inherited attribute
        b1.setVisible(false);
        assertNotEquals(b1, b2);
        b2.setVisible(false);
        assertEquals(b1, b2);

    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        DialBackground b1 = new DialBackground(Color.RED);
        DialBackground b2 = new DialBackground(Color.RED);
        assertEquals(b1, b2);
        int h1 = b1.hashCode();
        int h2 = b2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        // test default instance
        DialBackground b1 = new DialBackground();
        DialBackground b2 = CloneUtils.clone(b1);
        assertNotSame(b1, b2);
        assertSame(b1.getClass(), b2.getClass());
        assertEquals(b1, b2);

        // test a customised instance
        b1 = new DialBackground();
        b1.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f, 4.0f,
                Color.GREEN));
        b1.setGradientPaintTransformer(new StandardGradientPaintTransformer(
                GradientPaintTransformType.CENTER_VERTICAL));
        b2 = (DialBackground) b1.clone();
        assertNotSame(b1, b2);
        assertSame(b1.getClass(), b2.getClass());
        assertEquals(b1, b2);

        // check that the listener lists are independent
        MyDialLayerChangeListener l1 = new MyDialLayerChangeListener();
        b1.addChangeListener(l1);
        assertTrue(b1.hasListener(l1));
        assertFalse(b2.hasListener(l1));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        // test a default instance
        DialBackground b1 = new DialBackground();
        DialBackground b2 = TestUtils.serialised(b1);
        assertEquals(b1, b2);

        // test a customised instance
        b1 = new DialBackground();
        b1.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f, 4.0f, Color.GREEN));
        b1.setGradientPaintTransformer(new StandardGradientPaintTransformer(
                GradientPaintTransformType.CENTER_VERTICAL));

        b2 = TestUtils.serialised(b1);
        assertEquals(b1, b2);
    }

}
