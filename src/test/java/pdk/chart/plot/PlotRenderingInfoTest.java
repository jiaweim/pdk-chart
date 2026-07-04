package pdk.chart.plot;

import org.junit.jupiter.api.Test;
import pdk.chart.ChartRenderingInfo;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link PlotRenderingInfo} class.
 */
public class PlotRenderingInfoTest {

    /**
     * Test the equals() method.
     */
    @Test
    public void testEquals() {
        PlotRenderingInfo p1 = new PlotRenderingInfo(new ChartRenderingInfo());
        PlotRenderingInfo p2 = new PlotRenderingInfo(new ChartRenderingInfo());
        assertEquals(p1, p2);
        assertEquals(p2, p1);

        p1.setPlotArea(new Rectangle(2, 3, 4, 5));
        assertNotEquals(p1, p2);
        p2.setPlotArea(new Rectangle(2, 3, 4, 5));
        assertEquals(p1, p2);

        p1.setDataArea(new Rectangle(2, 4, 6, 8));
        assertNotEquals(p1, p2);
        p2.setDataArea(new Rectangle(2, 4, 6, 8));
        assertEquals(p1, p2);

        p1.addSubplotInfo(new PlotRenderingInfo(null));
        assertNotEquals(p1, p2);
        p2.addSubplotInfo(new PlotRenderingInfo(null));
        assertEquals(p1, p2);

        p1.getSubplotInfo(0).setDataArea(new Rectangle(1, 2, 3, 4));
        assertNotEquals(p1, p2);
        p2.getSubplotInfo(0).setDataArea(new Rectangle(1, 2, 3, 4));
        assertEquals(p1, p2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        PlotRenderingInfo p1 = new PlotRenderingInfo(new ChartRenderingInfo());
        p1.setPlotArea(new Rectangle2D.Double());
        PlotRenderingInfo p2 = CloneUtils.clone(p1);
        assertNotSame(p1, p2);
        assertSame(p1.getClass(), p2.getClass());
        assertEquals(p1, p2);

        // check independence
        p1.getPlotArea().setRect(1.0, 2.0, 3.0, 4.0);
        assertNotEquals(p1, p2);
        p2.getPlotArea().setRect(1.0, 2.0, 3.0, 4.0);
        assertEquals(p1, p2);

        p1.getDataArea().setRect(4.0, 3.0, 2.0, 1.0);
        assertNotEquals(p1, p2);
        p2.getDataArea().setRect(4.0, 3.0, 2.0, 1.0);
        assertEquals(p1, p2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        PlotRenderingInfo p1 = new PlotRenderingInfo(new ChartRenderingInfo());
        PlotRenderingInfo p2 = TestUtils.serialised(p1);
        assertEquals(p1, p2);
    }

}
