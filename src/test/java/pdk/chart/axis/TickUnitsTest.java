package pdk.chart.axis;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link TickUnits} class.
 */
public class TickUnitsTest {

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        TickUnits t1 = new TickUnits();
        t1.add(new NumberTickUnit(10, new DecimalFormat("0.00")));
        TickUnits t2 = TestUtils.serialised(t1);
        assertEquals(t1, t2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        TickUnits t1 = new TickUnits();
        t1.add(new NumberTickUnit(10, new DecimalFormat("0.00")));
        TickUnits t2 = CloneUtils.clone(t1);
        assertNotSame(t1, t2);
        assertSame(t1.getClass(), t2.getClass());
        assertEquals(t1, t2);
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        TickUnits t1 = new TickUnits();
        t1.add(new NumberTickUnit(10, new DecimalFormat("0.00")));
        TickUnits t2 = new TickUnits();
        t2.add(new NumberTickUnit(10, new DecimalFormat("0.00")));
        assertEquals(t1, t2);
        assertEquals(t2, t1);
    }

}
