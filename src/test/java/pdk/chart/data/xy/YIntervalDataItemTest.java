package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link YIntervalDataItem} class.
 */
public class YIntervalDataItemTest {

    private static final double EPSILON = 0.00000000001;

    /**
     * Some checks for the constructor.
     */
    @Test
    public void testConstructor1() {
        YIntervalDataItem item1 = new YIntervalDataItem(1.0, 2.0, 3.0, 4.0);
        assertEquals(Double.valueOf(1.0), item1.getX());
        assertEquals(2.0, item1.getYValue(), EPSILON);
        assertEquals(3.0, item1.getYLowValue(), EPSILON);
        assertEquals(4.0, item1.getYHighValue(), EPSILON);
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        YIntervalDataItem item1 = new YIntervalDataItem(1.0, 2.0, 1.5, 2.5);
        YIntervalDataItem item2 = new YIntervalDataItem(1.0, 2.0, 1.5, 2.5);
        assertEquals(item1, item2);
        assertEquals(item2, item1);

        // x
        item1 = new YIntervalDataItem(1.1, 2.0, 1.5, 2.5);
        assertNotEquals(item1, item2);
        item2 = new YIntervalDataItem(1.1, 2.0, 1.5, 2.5);
        assertEquals(item1, item2);

        // y
        item1 = new YIntervalDataItem(1.1, 2.2, 1.5, 2.5);
        assertNotEquals(item1, item2);
        item2 = new YIntervalDataItem(1.1, 2.2, 1.5, 2.5);
        assertEquals(item1, item2);

        // yLow
        item1 = new YIntervalDataItem(1.1, 2.2, 1.55, 2.5);
        assertNotEquals(item1, item2);
        item2 = new YIntervalDataItem(1.1, 2.2, 1.55, 2.5);
        assertEquals(item1, item2);

        // yHigh
        item1 = new YIntervalDataItem(1.1, 2.2, 1.55, 2.55);
        assertNotEquals(item1, item2);
        item2 = new YIntervalDataItem(1.1, 2.2, 1.55, 2.55);
        assertEquals(item1, item2);
    }

    /**
     * Some checks for the clone() method
     *
     * @throws java.lang.CloneNotSupportedException if there is a problem cloning.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        YIntervalDataItem item1 = new YIntervalDataItem(1.0, 2.0, 1.5, 2.5);
        YIntervalDataItem item2 = CloneUtils.clone(item1);
        assertNotSame(item1, item2);
        assertSame(item1.getClass(), item2.getClass());
        assertEquals(item1, item2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        YIntervalDataItem item1 = new YIntervalDataItem(1.0, 2.0, 1.5, 2.5);
        YIntervalDataItem item2 = TestUtils.serialised(item1);
        assertEquals(item1, item2);
    }

}
