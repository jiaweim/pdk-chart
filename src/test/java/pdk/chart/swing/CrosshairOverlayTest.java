package pdk.chart.swing;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;
import pdk.chart.plot.Crosshair;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link CrosshairOverlay} class.
 */
public class CrosshairOverlayTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        CrosshairOverlay o1 = new CrosshairOverlay();
        CrosshairOverlay o2 = new CrosshairOverlay();
        assertEquals(o1, o2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        CrosshairOverlay o1 = new CrosshairOverlay();
        o1.addDomainCrosshair(new Crosshair(99.9));
        o1.addRangeCrosshair(new Crosshair(1.23, new GradientPaint(1.0f, 2.0f,
                Color.RED, 3.0f, 4.0f, Color.BLUE), new BasicStroke(1.1f)));
        CrosshairOverlay o2 = TestUtils.serialised(o1);
        assertEquals(o1, o2);
    }

    /**
     * Basic checks for cloning.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        CrosshairOverlay o1 = new CrosshairOverlay();
        o1.addDomainCrosshair(new Crosshair(99.9));
        o1.addRangeCrosshair(new Crosshair(1.23, new GradientPaint(1.0f, 2.0f,
                Color.RED, 3.0f, 4.0f, Color.BLUE), new BasicStroke(1.1f)));
        CrosshairOverlay o2 = CloneUtils.clone(o1);
        assertNotSame(o1, o2);
        assertSame(o1.getClass(), o2.getClass());
        assertEquals(o1, o2);

        o1.addDomainCrosshair(new Crosshair(3.21));
        o1.addRangeCrosshair(new Crosshair(4.32));
        assertNotEquals(o1, o2);
    }

}
