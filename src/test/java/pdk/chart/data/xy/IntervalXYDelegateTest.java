package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some checks for the {@link IntervalXYDelegate} class.
 */
public class IntervalXYDelegateTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        XYSeries<String> s1 = new XYSeries<>("Series");
        s1.add(1.2, 3.4);
        XYSeriesCollection<String> c1 = new XYSeriesCollection<>();
        c1.addSeries(s1);
        IntervalXYDelegate d1 = new IntervalXYDelegate(c1);

        XYSeries<String> s2 = new XYSeries<>("Series");
        XYSeriesCollection<String> c2 = new XYSeriesCollection<>();
        s2.add(1.2, 3.4);
        c2.addSeries(s2);
        IntervalXYDelegate d2 = new IntervalXYDelegate(c2);

        assertEquals(d1, d2);
        assertEquals(d2, d1);

        d1.setAutoWidth(false);
        assertNotEquals(d1, d2);
        d2.setAutoWidth(false);
        assertEquals(d1, d2);

        d1.setIntervalPositionFactor(0.123);
        assertNotEquals(d1, d2);
        d2.setIntervalPositionFactor(0.123);
        assertEquals(d1, d2);

        d1.setFixedIntervalWidth(1.23);
        assertNotEquals(d1, d2);
        d2.setFixedIntervalWidth(1.23);
        assertEquals(d1, d2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        XYSeries<String> s1 = new XYSeries<>("Series");
        s1.add(1.2, 3.4);
        XYSeriesCollection<String> c1 = new XYSeriesCollection<>();
        c1.addSeries(s1);
        IntervalXYDelegate d1 = new IntervalXYDelegate(c1);
        IntervalXYDelegate d2 = CloneUtils.clone(d1);
        assertNotSame(d1, d2);
        assertSame(d1.getClass(), d2.getClass());
        assertEquals(d1, d2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        XYSeries<String> s1 = new XYSeries<>("Series");
        s1.add(1.2, 3.4);
        XYSeriesCollection<String> c1 = new XYSeriesCollection<>();
        c1.addSeries(s1);
        IntervalXYDelegate d1 = new IntervalXYDelegate(c1);
        IntervalXYDelegate d2 = TestUtils.serialised(d1);
        assertEquals(d1, d2);
    }

}
