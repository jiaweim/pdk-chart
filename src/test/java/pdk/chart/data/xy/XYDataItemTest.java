package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link XYDataItem} class.
 */
public class XYDataItemTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        XYDataItem i1 = new XYDataItem(1.0, 1.1);
        XYDataItem i2 = new XYDataItem(1.0, 1.1);
        assertEquals(i1, i2);
        assertEquals(i2, i1);

        i1.setY(9.9);
        assertNotEquals(i1, i2);

        i2.setY(9.9);
        assertEquals(i1, i2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        XYDataItem i1 = new XYDataItem(1.0, 1.1);
        XYDataItem i2 = CloneUtils.clone(i1);
        assertNotSame(i1, i2);
        assertSame(i1.getClass(), i2.getClass());
        assertEquals(i1, i2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XYDataItem i1 = new XYDataItem(1.0, 1.1);
        XYDataItem i2 = TestUtils.serialised(i1);
        assertEquals(i1, i2);
    }

}
