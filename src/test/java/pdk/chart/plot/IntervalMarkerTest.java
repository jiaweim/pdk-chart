package pdk.chart.plot;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.event.MarkerChangeEvent;
import pdk.chart.event.MarkerChangeListener;
import pdk.chart.util.CloneUtils;
import pdk.chart.util.GradientPaintTransformType;
import pdk.chart.util.GradientPaintTransformer;
import pdk.chart.util.StandardGradientPaintTransformer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link IntervalMarker} class.
 */
public class IntervalMarkerTest implements MarkerChangeListener {

    MarkerChangeEvent lastEvent;

    /**
     * Records the last event.
     *
     * @param event the last event.
     */
    @Override
    public void markerChanged(MarkerChangeEvent event) {
        this.lastEvent = event;
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {

        IntervalMarker m1 = new IntervalMarker(45.0, 50.0);
        IntervalMarker m2 = new IntervalMarker(45.0, 50.0);
        assertEquals(m1, m2);
        assertEquals(m2, m1);

        m1 = new IntervalMarker(44.0, 50.0);
        assertNotEquals(m1, m2);
        m2 = new IntervalMarker(44.0, 50.0);
        assertEquals(m1, m2);

        m1 = new IntervalMarker(44.0, 55.0);
        assertNotEquals(m1, m2);
        m2 = new IntervalMarker(44.0, 55.0);
        assertEquals(m1, m2);

        GradientPaintTransformer t = new StandardGradientPaintTransformer(
                GradientPaintTransformType.HORIZONTAL);
        m1.setGradientPaintTransformer(t);
        assertNotEquals(m1, m2);
        m2.setGradientPaintTransformer(t);
        assertEquals(m1, m2);

    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        IntervalMarker m1 = new IntervalMarker(45.0, 50.0);
        IntervalMarker m2 = CloneUtils.clone(m1);
        assertNotSame(m1, m2);
        assertSame(m1.getClass(), m2.getClass());
        assertEquals(m1, m2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        IntervalMarker m1 = new IntervalMarker(45.0, 50.0);
        IntervalMarker m2 = TestUtils.serialised(m1);
        assertEquals(m1, m2);
    }

    private static final double EPSILON = 0.0000000001;

    /**
     * Some checks for the getStartValue() and setStartValue() methods.
     */
    @Test
    public void testGetSetStartValue() {
        IntervalMarker m = new IntervalMarker(1.0, 2.0);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(1.0, m.getStartValue(), EPSILON);
        m.setStartValue(0.5);
        assertEquals(0.5, m.getStartValue(), EPSILON);
        assertEquals(m, this.lastEvent.getMarker());
    }

    /**
     * Some checks for the getEndValue() and setEndValue() methods.
     */
    @Test
    public void testGetSetEndValue() {
        IntervalMarker m = new IntervalMarker(1.0, 2.0);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(2.0, m.getEndValue(), EPSILON);
        m.setEndValue(0.5);
        assertEquals(0.5, m.getEndValue(), EPSILON);
        assertEquals(m, this.lastEvent.getMarker());
    }
}
