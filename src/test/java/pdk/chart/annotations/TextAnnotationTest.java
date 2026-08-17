package pdk.chart.annotations;

import org.junit.jupiter.api.Test;
import pdk.chart.event.AnnotationChangeEvent;
import pdk.chart.event.AnnotationChangeListener;
import pdk.chart.text.TextAnchor;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link TextAnnotation} class.
 */
public class TextAnnotationTest implements AnnotationChangeListener {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        TextAnnotation a1 = new CategoryTextAnnotation("Test", "Category", 1.0);
        TextAnnotation a2 = new CategoryTextAnnotation("Test", "Category", 1.0);
        assertEquals(a1, a2);

        // text
        a1.setText("Text");
        assertNotEquals(a1, a2);
        a2.setText("Text");
        assertEquals(a1, a2);

        // font
        a1.setFont(new Font("Serif", Font.BOLD, 18));
        assertNotEquals(a1, a2);
        a2.setFont(new Font("Serif", Font.BOLD, 18));
        assertEquals(a1, a2);

        // paint
        a1.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.pink));
        assertNotEquals(a1, a2);
        a2.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.pink));
        assertEquals(a1, a2);

        // textAnchor
        a1.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        assertNotEquals(a1, a2);
        a2.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        assertEquals(a1, a2);

        // rotationAnchor
        a1.setRotationAnchor(TextAnchor.BOTTOM_LEFT);
        assertNotEquals(a1, a2);
        a2.setRotationAnchor(TextAnchor.BOTTOM_LEFT);
        assertEquals(a1, a2);

        // rotationAngle
        a1.setRotationAngle(Math.PI);
        assertNotEquals(a1, a2);
        a2.setRotationAngle(Math.PI);
        assertEquals(a1, a2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        TextAnnotation a1 = new CategoryTextAnnotation("Test", "Category", 1.0);
        TextAnnotation a2 = new CategoryTextAnnotation("Test", "Category", 1.0);
        assertEquals(a1, a2);
        int h1 = a1.hashCode();
        int h2 = a2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Test null-argument (Bug #3428870).
     */
    @Test
    public void testSetRotationAnchor() {
        TextAnnotation a = new CategoryTextAnnotation("Test", "Category", 1.0);
        try {
            a.setRotationAnchor(null);
            fail("Should have thrown Exception.");
        } catch (NullPointerException e) {
            // ok, exception is expected
        }
    }

    /**
     * Some tests to ensure that change events are generated as expected.
     */
    @Test
    public void testChangeEvents() {
        TextAnnotation a = new CategoryTextAnnotation("Test", "A", 1.0);
        a.addChangeListener(this);
        this.lastEvent = null;
        a.setText("B");
        assertNotNull(this.lastEvent);
        this.lastEvent = null;
        a.setText("B");
        assertNotNull(this.lastEvent);

        this.lastEvent = null;
        a.setFont(new Font("SansSerif", Font.PLAIN, 12));
        assertNotNull(this.lastEvent);

        this.lastEvent = null;
        a.setPaint(Color.BLUE);
        assertNotNull(this.lastEvent);

        this.lastEvent = null;
        a.setTextAnchor(TextAnchor.CENTER_LEFT);
        assertNotNull(this.lastEvent);

        this.lastEvent = null;
        a.setRotationAnchor(TextAnchor.CENTER_LEFT);
        assertNotNull(this.lastEvent);

        this.lastEvent = null;
        a.setRotationAngle(123.4);
        assertNotNull(this.lastEvent);
    }

    private AnnotationChangeEvent lastEvent;

    /**
     * Receives notification of a change to an annotation.
     *
     * @param event the event.
     */
    @Override
    public void annotationChanged(AnnotationChangeEvent event) {
        this.lastEvent = event;
    }

}
