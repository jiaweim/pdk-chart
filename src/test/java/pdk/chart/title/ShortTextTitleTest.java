package pdk.chart.title;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link ShortTextTitle} class.
 */
public class ShortTextTitleTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        ShortTextTitle t1 = new ShortTextTitle("ABC");
        ShortTextTitle t2 = new ShortTextTitle("ABC");
        assertEquals(t1, t2);

        t1.setText("Test 1");
        assertNotEquals(t1, t2);
        t2.setText("Test 1");
        assertEquals(t1, t2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        ShortTextTitle t1 = new ShortTextTitle("ABC");
        ShortTextTitle t2 = new ShortTextTitle("ABC");
        assertEquals(t1, t2);
        int h1 = t1.hashCode();
        int h2 = t2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        ShortTextTitle t1 = new ShortTextTitle("ABC");
        ShortTextTitle t2 = CloneUtils.clone(t1);
        assertNotSame(t1, t2);
        assertSame(t1.getClass(), t2.getClass());
        assertEquals(t1, t2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        ShortTextTitle t1 = new ShortTextTitle("ABC");
        ShortTextTitle t2 = TestUtils.serialised(t1);
        assertEquals(t1, t2);
    }

}
