package pdk.chart.plot;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.event.MarkerChangeEvent;
import pdk.chart.event.MarkerChangeListener;
import pdk.chart.util.CloneUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some tests for the {@link CategoryMarker} class.
 */
public class CategoryMarkerTest implements MarkerChangeListener {

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
        CategoryMarker m1 = new CategoryMarker("A");
        CategoryMarker m2 = new CategoryMarker("A");
        assertEquals(m1, m2);
        assertEquals(m2, m1);

        //key
        m1 = new CategoryMarker("B");
        assertNotEquals(m1, m2);
        m2 = new CategoryMarker("B");
        assertEquals(m1, m2);

        //paint
        m1 = new CategoryMarker("A", new GradientPaint(1.0f, 2.0f, Color.WHITE,
                3.0f, 4.0f, Color.YELLOW), new BasicStroke(1.1f));
        assertNotEquals(m1, m2);
        m2 = new CategoryMarker("A", new GradientPaint(1.0f, 2.0f, Color.WHITE,
                3.0f, 4.0f, Color.YELLOW), new BasicStroke(1.1f));
        assertEquals(m1, m2);

        //stroke
        m1 = new CategoryMarker("A", new GradientPaint(1.0f, 2.0f, Color.WHITE,
                3.0f, 4.0f, Color.YELLOW), new BasicStroke(2.2f));
        assertNotEquals(m1, m2);
        m2 = new CategoryMarker("A", new GradientPaint(1.0f, 2.0f, Color.WHITE,
                3.0f, 4.0f, Color.YELLOW), new BasicStroke(2.2f));
        assertEquals(m1, m2);

        //outlinePaint
        m1 = new CategoryMarker("A", new GradientPaint(1.0f, 2.0f, Color.WHITE,
                3.0f, 4.0f, Color.YELLOW), new BasicStroke(2.2f), Color.RED,
                new BasicStroke(1.0f), 1.0f);
        assertNotEquals(m1, m2);
        m2 = new CategoryMarker("A", new GradientPaint(1.0f, 2.0f, Color.WHITE,
                3.0f, 4.0f, Color.YELLOW), new BasicStroke(2.2f), Color.RED,
                new BasicStroke(1.0f), 1.0f);
        assertEquals(m1, m2);

        //outlineStroke
        m1 = new CategoryMarker("A", new GradientPaint(1.0f, 2.0f, Color.WHITE,
                3.0f, 4.0f, Color.YELLOW), new BasicStroke(2.2f), Color.RED,
                new BasicStroke(3.3f), 1.0f);
        assertNotEquals(m1, m2);
        m2 = new CategoryMarker("A", new GradientPaint(1.0f, 2.0f, Color.WHITE,
                3.0f, 4.0f, Color.YELLOW), new BasicStroke(2.2f), Color.RED,
                new BasicStroke(3.3f), 1.0f);
        assertEquals(m1, m2);

        //alpha
        m1 = new CategoryMarker("A", new GradientPaint(1.0f, 2.0f, Color.WHITE,
                3.0f, 4.0f, Color.YELLOW), new BasicStroke(2.2f), Color.RED,
                new BasicStroke(1.0f), 0.5f);
        assertNotEquals(m1, m2);
        m2 = new CategoryMarker("A", new GradientPaint(1.0f, 2.0f, Color.WHITE,
                3.0f, 4.0f, Color.YELLOW), new BasicStroke(2.2f), Color.RED,
                new BasicStroke(1.0f), 0.5f);
        assertEquals(m1, m2);

    }

    /**
     * Check cloning.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        CategoryMarker m1 = new CategoryMarker("A", new GradientPaint(1.0f,
                2.0f, Color.WHITE, 3.0f, 4.0f, Color.YELLOW),
                new BasicStroke(1.1f));
        CategoryMarker m2 = CloneUtils.clone(m1);
        assertNotSame(m1, m2);
        assertSame(m1.getClass(), m2.getClass());
        assertEquals(m1, m2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        CategoryMarker m1 = new CategoryMarker("A", new GradientPaint(1.0f,
                2.0f, Color.WHITE, 3.0f, 4.0f, Color.YELLOW),
                new BasicStroke(1.1f));
        CategoryMarker m2 = TestUtils.serialised(m1);
        assertEquals(m1, m2);
    }

    /**
     * Some checks for the getKey() and setKey() methods.
     */
    @Test
    public void testGetSetKey() {
        CategoryMarker m = new CategoryMarker("X");
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals("X", m.getKey());
        m.setKey("Y");
        assertEquals("Y", m.getKey());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        try {
            m.setKey(null);
            fail("Expected an IllegalArgumentException for null.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * Some checks for the getDrawAsLine() and setDrawAsLine() methods.
     */
    @Test
    public void testGetSetDrawAsLine() {
        CategoryMarker m = new CategoryMarker("X");
        m.addChangeListener(this);
        this.lastEvent = null;
        assertFalse(m.getDrawAsLine());
        m.setDrawAsLine(true);
        assertTrue(m.getDrawAsLine());
        assertEquals(m, this.lastEvent.getMarker());
    }
}
