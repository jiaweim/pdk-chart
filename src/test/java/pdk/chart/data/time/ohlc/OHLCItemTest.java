package pdk.chart.data.time.ohlc;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.data.time.Year;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link OHLCItem} class.
 */
public class OHLCItemTest {

    private static final double EPSILON = 0.00000000001;

    /**
     * Some checks for the constructor.
     */
    @Test
    public void testConstructor1() {
        OHLCItem item1 = new OHLCItem(new Year(2006), 2.0, 4.0, 1.0, 3.0);
        assertEquals(new Year(2006), item1.getPeriod());
        assertEquals(2.0, item1.getOpenValue(), EPSILON);
        assertEquals(4.0, item1.getHighValue(), EPSILON);
        assertEquals(1.0, item1.getLowValue(), EPSILON);
        assertEquals(3.0, item1.getCloseValue(), EPSILON);
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        OHLCItem item1 = new OHLCItem(new Year(2006), 2.0, 4.0, 1.0, 3.0);
        OHLCItem item2 = new OHLCItem(new Year(2006), 2.0, 4.0, 1.0, 3.0);
        assertEquals(item1, item2);
        assertEquals(item2, item1);

        // period
        item1 = new OHLCItem(new Year(2007), 2.0, 4.0, 1.0, 3.0);
        assertNotEquals(item1, item2);
        item2 = new OHLCItem(new Year(2007), 2.0, 4.0, 1.0, 3.0);
        assertEquals(item1, item2);

        // open
        item1 = new OHLCItem(new Year(2007), 2.2, 4.0, 1.0, 3.0);
        assertNotEquals(item1, item2);
        item2 = new OHLCItem(new Year(2007), 2.2, 4.0, 1.0, 3.0);
        assertEquals(item1, item2);

        // high
        item1 = new OHLCItem(new Year(2007), 2.2, 4.4, 1.0, 3.0);
        assertNotEquals(item1, item2);
        item2 = new OHLCItem(new Year(2007), 2.2, 4.4, 1.0, 3.0);
        assertEquals(item1, item2);

        // low
        item1 = new OHLCItem(new Year(2007), 2.2, 4.4, 1.1, 3.0);
        assertNotEquals(item1, item2);
        item2 = new OHLCItem(new Year(2007), 2.2, 4.4, 1.1, 3.0);
        assertEquals(item1, item2);

        // close
        item1 = new OHLCItem(new Year(2007), 2.2, 4.4, 1.1, 3.3);
        assertNotEquals(item1, item2);
        item2 = new OHLCItem(new Year(2007), 2.2, 4.4, 1.1, 3.3);
        assertEquals(item1, item2);
    }

    /**
     * Some checks for the clone() method.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        OHLCItem item1 = new OHLCItem(new Year(2006), 2.0, 4.0, 1.0, 3.0);
        OHLCItem item2 = CloneUtils.clone(item1);
        assertNotSame(item1, item2);
        assertSame(item1.getClass(), item2.getClass());
        assertEquals(item1, item2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        OHLCItem item1 = new OHLCItem(new Year(2006), 2.0, 4.0, 1.0, 3.0);
        OHLCItem item2 = TestUtils.serialised(item1);
        assertEquals(item1, item2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        OHLCItem i1 = new OHLCItem(new Year(2009), 2.0, 4.0, 1.0, 3.0);
        OHLCItem i2 = new OHLCItem(new Year(2009), 2.0, 4.0, 1.0, 3.0);
        assertEquals(i1, i2);
        int h1 = i1.hashCode();
        int h2 = i2.hashCode();
        assertEquals(h1, h2);
    }

}
