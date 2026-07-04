package pdk.chart.block;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.util.CloneUtils;
import pdk.chart.text.TextBlockAnchor;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some tests for the {@link LabelBlock} class.
 */
public class LabelBlockTest {

    /**
     * Confirm that the equals() method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        LabelBlock b1 = new LabelBlock("ABC", new Font("Dialog",
                Font.PLAIN, 12), Color.RED);
        LabelBlock b2 = new LabelBlock("ABC", new Font("Dialog",
                Font.PLAIN, 12), Color.RED);
        assertEquals(b1, b2);
        assertEquals(b2, b2);

        b1 = new LabelBlock("XYZ", new Font("Dialog", Font.PLAIN, 12),
                Color.RED);
        assertNotEquals(b1, b2);
        b2 = new LabelBlock("XYZ", new Font("Dialog", Font.PLAIN, 12),
                Color.RED);
        assertEquals(b1, b2);

        b1 = new LabelBlock("XYZ", new Font("Dialog", Font.BOLD, 12),
                Color.RED);
        assertNotEquals(b1, b2);
        b2 = new LabelBlock("XYZ", new Font("Dialog", Font.BOLD, 12),
                Color.RED);
        assertEquals(b1, b2);

        b1 = new LabelBlock("XYZ", new Font("Dialog", Font.BOLD, 12),
                Color.BLUE);
        assertNotEquals(b1, b2);
        b2 = new LabelBlock("XYZ", new Font("Dialog", Font.BOLD, 12),
                Color.BLUE);
        assertEquals(b1, b2);

        b1.setToolTipText("Tooltip");
        assertNotEquals(b1, b2);
        b2.setToolTipText("Tooltip");
        assertEquals(b1, b2);

        b1.setURLText("URL");
        assertNotEquals(b1, b2);
        b2.setURLText("URL");
        assertEquals(b1, b2);

        b1.setContentAlignmentPoint(TextBlockAnchor.CENTER_RIGHT);
        assertNotEquals(b1, b2);
        b2.setContentAlignmentPoint(TextBlockAnchor.CENTER_RIGHT);
        assertEquals(b1, b2);

        b1.setTextAnchor(RectangleAnchor.BOTTOM_RIGHT);
        assertNotEquals(b1, b2);
        b2.setTextAnchor(RectangleAnchor.BOTTOM_RIGHT);
        assertEquals(b1, b2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        LabelBlock b1 = new LabelBlock("ABC", new Font("Dialog",
                Font.PLAIN, 12), Color.RED);
        LabelBlock b2 = CloneUtils.clone(b1);
        assertNotSame(b1, b2);
        assertSame(b1.getClass(), b2.getClass());
        assertEquals(b1, b2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        GradientPaint gp = new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f, 4.0f,
                Color.BLUE);
        LabelBlock b1 = new LabelBlock("ABC", new Font("Dialog",
                Font.PLAIN, 12), gp);
        LabelBlock b2 = TestUtils.serialised(b1);
        assertEquals(b1, b2);
    }

}
