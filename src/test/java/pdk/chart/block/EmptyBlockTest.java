package pdk.chart.block;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link EmptyBlock} class.
 */
public class EmptyBlockTest {

    /**
     * Confirm that the equals() method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        EmptyBlock b1 = new EmptyBlock(1.0, 2.0);
        EmptyBlock b2 = new EmptyBlock(1.0, 2.0);
        assertEquals(b1, b2);
        assertEquals(b2, b2);

        b1 = new EmptyBlock(1.1, 2.0);
        assertNotEquals(b1, b2);
        b2 = new EmptyBlock(1.1, 2.0);
        assertEquals(b1, b2);

        b1 = new EmptyBlock(1.1, 2.2);
        assertNotEquals(b1, b2);
        b2 = new EmptyBlock(1.1, 2.2);
        assertEquals(b1, b2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() {
        EmptyBlock b1 = new EmptyBlock(1.0, 2.0);
        EmptyBlock b2 = null;

        try {
            b2 = CloneUtils.clone(b1);
        } catch (CloneNotSupportedException e) {
            fail(e.toString());
        }
        assertNotSame(b1, b2);
        assertSame(b1.getClass(), b2.getClass());
        assertEquals(b1, b2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        EmptyBlock b1 = new EmptyBlock(1.0, 2.0);
        EmptyBlock b2 = TestUtils.serialised(b1);
        assertEquals(b1, b2);
    }

}
