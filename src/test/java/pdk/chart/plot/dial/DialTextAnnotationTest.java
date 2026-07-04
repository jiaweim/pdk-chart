package pdk.chart.plot.dial;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link DialTextAnnotation} class.
 */
public class DialTextAnnotationTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DialTextAnnotation a1 = new DialTextAnnotation("A1");
        DialTextAnnotation a2 = new DialTextAnnotation("A1");
        assertEquals(a1, a2);

        // angle
        a1.setAngle(1.1);
        assertNotEquals(a1, a2);
        a2.setAngle(1.1);
        assertEquals(a1, a2);

        // radius
        a1.setRadius(9.9);
        assertNotEquals(a1, a2);
        a2.setRadius(9.9);
        assertEquals(a1, a2);

        // font
        Font f = new Font("SansSerif", Font.PLAIN, 14);
        a1.setFont(f);
        assertNotEquals(a1, a2);
        a2.setFont(f);
        assertEquals(a1, a2);

        // paint
        a1.setPaint(Color.RED);
        assertNotEquals(a1, a2);
        a2.setPaint(Color.RED);
        assertEquals(a1, a2);

        // label
        a1.setLabel("ABC");
        assertNotEquals(a1, a2);
        a2.setLabel("ABC");
        assertEquals(a1, a2);

        // check an inherited attribute
        a1.setVisible(false);
        assertNotEquals(a1, a2);
        a2.setVisible(false);
        assertEquals(a1, a2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        DialTextAnnotation a1 = new DialTextAnnotation("A1");
        DialTextAnnotation a2 = new DialTextAnnotation("A1");
        assertEquals(a1, a2);
        int h1 = a1.hashCode();
        int h2 = a2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        // test a default instance
        DialTextAnnotation a1 = new DialTextAnnotation("A1");
        DialTextAnnotation a2 = CloneUtils.clone(a1);
        assertNotSame(a1, a2);
        assertSame(a1.getClass(), a2.getClass());
        assertEquals(a1, a2);

        // check that the listener lists are independent
        MyDialLayerChangeListener l1 = new MyDialLayerChangeListener();
        a1.addChangeListener(l1);
        assertTrue(a1.hasListener(l1));
        assertFalse(a2.hasListener(l1));
    }


    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        // test a default instance
        DialTextAnnotation a1 = new DialTextAnnotation("A1");
        DialTextAnnotation a2 = TestUtils.serialised(a1);
        assertEquals(a1, a2);

        // test a custom instance
        a1 = new DialTextAnnotation("A1");
        a1.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f, 4.0f,
                Color.BLUE));

        a2 = TestUtils.serialised(a1);
        assertEquals(a1, a2);
    }

}
