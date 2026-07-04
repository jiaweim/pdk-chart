package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link XYIntervalDataItem} class.
 */
public class XYIntervalDataItemTest {

    private static final double EPSILON = 0.000000001;

    /**
     * Some checks for the constructor.
     */
    @Test
    public void testConstructor1() {
        XYIntervalDataItem item1 = new XYIntervalDataItem(1.0, 0.5, 1.5, 2.0,
                1.9, 2.1);
        assertEquals(Double.valueOf(1.0), item1.getX());
        assertEquals(0.5, item1.getXLowValue(), EPSILON);
        assertEquals(1.5, item1.getXHighValue(), EPSILON);
        assertEquals(2.0, item1.getYValue(), EPSILON);
        assertEquals(1.9, item1.getYLowValue(), EPSILON);
        assertEquals(2.1, item1.getYHighValue(), EPSILON);
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        XYIntervalDataItem item1 = new XYIntervalDataItem(1.0, 0.5, 1.5, 2.0,
                1.9, 2.1);
        XYIntervalDataItem item2 = new XYIntervalDataItem(1.0, 0.5, 1.5, 2.0,
                1.9, 2.1);
        assertEquals(item1, item2);
        assertEquals(item2, item1);

        // x
        item1 = new XYIntervalDataItem(1.1, 0.5, 1.5, 2.0, 1.9, 2.1);
        assertNotEquals(item1, item2);
        item2 = new XYIntervalDataItem(1.1, 0.5, 1.5, 2.0, 1.9, 2.1);
        assertEquals(item1, item2);

        // xLow
        item1 = new XYIntervalDataItem(1.1, 0.55, 1.5, 2.0, 1.9, 2.1);
        assertNotEquals(item1, item2);
        item2 = new XYIntervalDataItem(1.1, 0.55, 1.5, 2.0, 1.9, 2.1);
        assertEquals(item1, item2);

        // xHigh
        item1 = new XYIntervalDataItem(1.1, 0.55, 1.55, 2.0, 1.9, 2.1);
        assertNotEquals(item1, item2);
        item2 = new XYIntervalDataItem(1.1, 0.55, 1.55, 2.0, 1.9, 2.1);
        assertEquals(item1, item2);

        // y
        item1 = new XYIntervalDataItem(1.1, 0.55, 1.55, 2.2, 1.9, 2.1);
        assertNotEquals(item1, item2);
        item2 = new XYIntervalDataItem(1.1, 0.55, 1.55, 2.2, 1.9, 2.1);
        assertEquals(item1, item2);

        // yLow
        item1 = new XYIntervalDataItem(1.1, 0.55, 1.55, 2.2, 1.99, 2.1);
        assertNotEquals(item1, item2);
        item2 = new XYIntervalDataItem(1.1, 0.55, 1.55, 2.2, 1.99, 2.1);
        assertEquals(item1, item2);

        // yHigh
        item1 = new XYIntervalDataItem(1.1, 0.55, 1.55, 2.2, 1.99, 2.11);
        assertNotEquals(item1, item2);
        item2 = new XYIntervalDataItem(1.1, 0.55, 1.55, 2.2, 1.99, 2.11);
        assertEquals(item1, item2);
    }

    /**
     * Some checks for the clone() method.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        XYIntervalDataItem item1 = new XYIntervalDataItem(1.0, 0.5, 1.5, 2.0,
                1.9, 2.1);
        XYIntervalDataItem item2 = CloneUtils.clone(item1);
        assertNotSame(item1, item2);
        assertSame(item1.getClass(), item2.getClass());
        assertEquals(item1, item2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XYIntervalDataItem item1 = new XYIntervalDataItem(1.0, 0.5, 1.5, 2.0,
                1.9, 2.1);
        XYIntervalDataItem item2 = TestUtils.serialised(item1);
        assertEquals(item1, item2);
    }

}
