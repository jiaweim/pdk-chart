package pdk.chart.title;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.HorizontalAlignment;
import pdk.chart.util.CloneUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link TextTitle} class.
 */
public class TextTitleTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        TextTitle t1 = new TextTitle();
        TextTitle t2 = new TextTitle();
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

        t1.setTextAlignment(HorizontalAlignment.RIGHT);
        assertNotEquals(t1, t2);
        t2.setTextAlignment(HorizontalAlignment.RIGHT);
        assertEquals(t1, t2);

        // paint
        t1.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.BLUE));
        assertNotEquals(t1, t2);
        t2.setPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.BLUE));
        assertEquals(t1, t2);

        // backgroundPaint
        t1.setBackgroundPaint(new GradientPaint(4.0f, 3.0f, Color.RED,
                2.0f, 1.0f, Color.BLUE));
        assertNotEquals(t1, t2);
        t2.setBackgroundPaint(new GradientPaint(4.0f, 3.0f, Color.RED,
                2.0f, 1.0f, Color.BLUE));
        assertEquals(t1, t2);

        // maximumLinesToDisplay
        t1.setMaximumLinesToDisplay(3);
        assertNotEquals(t1, t2);
        t2.setMaximumLinesToDisplay(3);
        assertEquals(t1, t2);

        // toolTipText
        t1.setToolTipText("TTT");
        assertNotEquals(t1, t2);
        t2.setToolTipText("TTT");
        assertEquals(t1, t2);

        // urlText
        t1.setURLText(("URL"));
        assertNotEquals(t1, t2);
        t2.setURLText(("URL"));
        assertEquals(t1, t2);

        // expandToFitSpace
        t1.setExpandToFitSpace(!t1.getExpandToFitSpace());
        assertNotEquals(t1, t2);
        t2.setExpandToFitSpace(!t2.getExpandToFitSpace());
        assertEquals(t1, t2);

    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        TextTitle t1 = new TextTitle();
        TextTitle t2 = new TextTitle();
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
        TextTitle t1 = new TextTitle();
        TextTitle t2 = CloneUtils.clone(t1);
        assertNotSame(t1, t2);
        assertSame(t1.getClass(), t2.getClass());
        assertEquals(t1, t2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        TextTitle t1 = new TextTitle("Test");
        TextTitle t2 = TestUtils.serialised(t1);
        assertEquals(t1, t2);
    }

}
