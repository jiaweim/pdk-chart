package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link XIntervalDataItem} class.
 */
public class XIntervalDataItemTest {

    private static final double EPSILON = 0.00000000001;

    /**
     * Some checks for the constructor.
     */
    @Test
    public void testConstructor1() {
        XIntervalDataItem item1 = new XIntervalDataItem(1.0, 2.0, 3.0, 4.0);
        assertEquals(1.0, item1.getX());
        assertEquals(2.0, item1.getXLowValue(), EPSILON);
        assertEquals(3.0, item1.getXHighValue(), EPSILON);
        assertEquals(4.0, item1.getYValue(), EPSILON);
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        XIntervalDataItem item1 = new XIntervalDataItem(1.0, 2.0, 3.0, 4.0);
        XIntervalDataItem item2 = new XIntervalDataItem(1.0, 2.0, 3.0, 4.0);
        assertEquals(item1, item2);
        assertEquals(item2, item1);

        // x
        item1 = new XIntervalDataItem(1.1, 2.0, 3.0, 4.0);
        assertNotEquals(item1, item2);
        item2 = new XIntervalDataItem(1.1, 2.0, 3.0, 4.0);
        assertEquals(item1, item2);

        // xLow
        item1 = new XIntervalDataItem(1.1, 2.2, 3.0, 4.0);
        assertNotEquals(item1, item2);
        item2 = new XIntervalDataItem(1.1, 2.2, 3.0, 4.0);
        assertEquals(item1, item2);

        // xHigh
        item1 = new XIntervalDataItem(1.1, 2.2, 3.3, 4.0);
        assertNotEquals(item1, item2);
        item2 = new XIntervalDataItem(1.1, 2.2, 3.3, 4.0);
        assertEquals(item1, item2);

        // y
        item1 = new XIntervalDataItem(1.1, 2.2, 3.3, 4.4);
        assertNotEquals(item1, item2);
        item2 = new XIntervalDataItem(1.1, 2.2, 3.3, 4.4);
        assertEquals(item1, item2);

    }

    /**
     * Some checks for the clone() method.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        XIntervalDataItem item1 = new XIntervalDataItem(1.0, 2.0, 3.0, 4.0);
        XIntervalDataItem item2 = CloneUtils.clone(item1);
        assertNotSame(item1, item2);
        assertSame(item1.getClass(), item2.getClass());
        assertEquals(item1, item2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XIntervalDataItem item1 = new XIntervalDataItem(1.0, 2.0, 3.0, 4.0);
        XIntervalDataItem item2 = TestUtils.serialised(item1);
        assertEquals(item1, item2);
    }

}
