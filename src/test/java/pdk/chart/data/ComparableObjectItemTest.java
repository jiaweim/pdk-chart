package pdk.chart.data;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link ComparableObjectItem} class.
 */
public class ComparableObjectItemTest {

    /**
     * Some checks for the constructor.
     */
    @Test
    public void testConstructor() {
        // check null argument 1
        try {
            /* ComparableObjectItem item1 = */
            new ComparableObjectItem(null,
                    "XYZ");
            fail("There should be an exception.");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        ComparableObjectItem item1 = new ComparableObjectItem(1, "XYZ");
        ComparableObjectItem item2 = new ComparableObjectItem(1, "XYZ");
        assertEquals(item1, item2);

        item1 = new ComparableObjectItem(2, "XYZ");
        assertNotEquals(item1, item2);
        item2 = new ComparableObjectItem(2, "XYZ");
        assertEquals(item1, item2);

        item1 = new ComparableObjectItem(2, null);
        assertNotEquals(item1, item2);
        item2 = new ComparableObjectItem(2, null);
        assertEquals(item1, item2);
    }

    /**
     * Some checks for the clone() method.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        ComparableObjectItem item1 = new ComparableObjectItem(1, "XYZ");
        ComparableObjectItem item2 = CloneUtils.clone(item1);
        assertNotSame(item1, item2);
        assertSame(item1.getClass(), item2.getClass());
        assertEquals(item1, item2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        ComparableObjectItem item1 = new ComparableObjectItem(1, "XYZ");
        ComparableObjectItem item2 = TestUtils.serialised(item1);
        assertEquals(item1, item2);
    }

    /**
     * Some checks for the compareTo() method.
     */
    @Test
    public void testCompareTo() {
        ComparableObjectItem item1 = new ComparableObjectItem(1, "XYZ");
        ComparableObjectItem item2 = new ComparableObjectItem(2, "XYZ");
        ComparableObjectItem item3 = new ComparableObjectItem(3, "XYZ");
        ComparableObjectItem item4 = new ComparableObjectItem(1, "XYZ");
        assertTrue(item2.compareTo(item1) > 0);
        assertTrue(item3.compareTo(item1) > 0);
        assertEquals(0, item4.compareTo(item1));
        assertTrue(item1.compareTo(item2) < 0);
    }

}
