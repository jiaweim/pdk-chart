package pdk.chart.title;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link DateTitle} class.
 */
public class DateTitleTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        DateTitle t1 = new DateTitle();
        DateTitle t2 = new DateTitle();
        assertEquals(t1, t2);

        t1.setText("Test 1");
        assertNotEquals(t1, t2);
        t2.setText("Test 1");
        assertEquals(t1, t2);

        Font f = new Font("SansSerif", Font.PLAIN, 15);
        t1.setFont(f);
        assertNotEquals(t1, t2);
        t2.setFont(f);
        assertEquals(t1, t2);

        t1.setPaint(Color.BLUE);
        assertNotEquals(t1, t2);
        t2.setPaint(Color.BLUE);
        assertEquals(t1, t2);

        t1.setBackgroundPaint(Color.BLUE);
        assertNotEquals(t1, t2);
        t2.setBackgroundPaint(Color.BLUE);
        assertEquals(t1, t2);

    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        DateTitle t1 = new DateTitle();
        DateTitle t2 = new DateTitle();
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
        DateTitle t1 = new DateTitle();
        DateTitle t2 = CloneUtils.clone(t1);
        assertNotSame(t1, t2);
        assertSame(t1.getClass(), t2.getClass());
        assertEquals(t1, t2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DateTitle t1 = new DateTitle();
        DateTitle t2 = TestUtils.serialised(t1);
        assertEquals(t1, t2);
    }

}
