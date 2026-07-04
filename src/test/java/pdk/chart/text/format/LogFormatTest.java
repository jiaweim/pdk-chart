package pdk.chart.text.format;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link LogFormat} class.
 */
public class LogFormatTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        LogFormat f1 = new LogFormat(10.0, "10", true);
        LogFormat f2 = new LogFormat(10.0, "10", true);
        assertEquals(f1, f2);

        f1 = new LogFormat(11.0, "10", true);
        assertNotEquals(f1, f2);
        f2 = new LogFormat(11.0, "10", true);
        assertEquals(f1, f2);

        f1 = new LogFormat(11.0, "11", true);
        assertNotEquals(f1, f2);
        f2 = new LogFormat(11.0, "11", true);
        assertEquals(f1, f2);

        f1 = new LogFormat(11.0, "11", false);
        assertNotEquals(f1, f2);
        f2 = new LogFormat(11.0, "11", false);
        assertEquals(f1, f2);

        f1.setExponentFormat(new DecimalFormat("0.000"));
        assertNotEquals(f1, f2);
        f2.setExponentFormat(new DecimalFormat("0.000"));
        assertEquals(f1, f2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        LogFormat f1 = new LogFormat(10.0, "10", true);
        LogFormat f2 = new LogFormat(10.0, "10", true);
        assertEquals(f1, f2);
        int h1 = f1.hashCode();
        int h2 = f2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        LogFormat f1 = new LogFormat(10.0, "10", true);
        LogFormat f2 = CloneUtils.clone(f1);
        assertNotSame(f1, f2);
        assertSame(f1.getClass(), f2.getClass());
        assertEquals(f1, f2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        LogFormat f1 = new LogFormat(10.0, "10", true);
        LogFormat f2 = TestUtils.serialised(f1);
        assertEquals(f1, f2);
    }

}
