package pdk.chart.renderer.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.data.Range;
import pdk.chart.data.xy.DefaultOHLCDataset;
import pdk.chart.data.xy.OHLCDataItem;
import pdk.chart.data.xy.OHLCDataset;
import pdk.chart.util.CloneUtils;

import java.awt.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link HighLowRenderer} class.
 */
public class HighLowRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        HighLowRenderer r1 = new HighLowRenderer();
        HighLowRenderer r2 = new HighLowRenderer();
        assertEquals(r1, r2);

        // drawOpenTicks
        r1.setDrawOpenTicks(false);
        assertNotEquals(r1, r2);
        r2.setDrawOpenTicks(false);
        assertEquals(r1, r2);

        // drawCloseTicks
        r1.setDrawCloseTicks(false);
        assertNotEquals(r1, r2);
        r2.setDrawCloseTicks(false);
        assertEquals(r1, r2);

        // openTickPaint
        r1.setOpenTickPaint(Color.RED);
        assertNotEquals(r1, r2);
        r2.setOpenTickPaint(Color.RED);
        assertEquals(r1, r2);

        // closeTickPaint
        r1.setCloseTickPaint(Color.BLUE);
        assertNotEquals(r1, r2);
        r2.setCloseTickPaint(Color.BLUE);
        assertEquals(r1, r2);

        // tickLength
        r1.setTickLength(99.9);
        assertNotEquals(r1, r2);
        r2.setTickLength(99.9);
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        HighLowRenderer r1 = new HighLowRenderer();
        HighLowRenderer r2 = new HighLowRenderer();
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
        HighLowRenderer r1 = new HighLowRenderer();
        r1.setCloseTickPaint(Color.GREEN);
        HighLowRenderer r2 = CloneUtils.clone(r1);
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        HighLowRenderer r1 = new HighLowRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        HighLowRenderer r1 = new HighLowRenderer();
        r1.setCloseTickPaint(new GradientPaint(1.0f, 2.0f, Color.WHITE, 3.0f, 4.0f, Color.BLACK));
        HighLowRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

    /**
     * Some checks for the findRangeBounds() method.
     */
    @Test
    public void testFindRangeBounds() {
        HighLowRenderer renderer = new HighLowRenderer();

        OHLCDataItem item1 = new OHLCDataItem(new Date(1L), 2.0, 4.0, 1.0, 3.0,
                100);
        OHLCDataset dataset = new DefaultOHLCDataset("S1",
                new OHLCDataItem[]{item1});
        Range range = renderer.findRangeBounds(dataset);
        assertEquals(new Range(1.0, 4.0), range);

        OHLCDataItem item2 = new OHLCDataItem(new Date(1L), -1.0, 3.0, -1.0,
                3.0, 100);
        dataset = new DefaultOHLCDataset("S1", new OHLCDataItem[]{item1,
                item2});
        range = renderer.findRangeBounds(dataset);
        assertEquals(new Range(-1.0, 4.0), range);

        // try an empty dataset - should return a null range
        dataset = new DefaultOHLCDataset("S1", new OHLCDataItem[]{});
        range = renderer.findRangeBounds(dataset);
        assertNull(range);

        // try a null dataset - should return a null range
        range = renderer.findRangeBounds(null);
        assertNull(range);
    }

}
