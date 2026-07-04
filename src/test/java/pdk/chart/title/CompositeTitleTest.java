package pdk.chart.title;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.RectangleInsets;
import pdk.chart.block.BlockBorder;
import pdk.chart.block.BlockContainer;
import pdk.chart.util.CloneUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link CompositeTitle} class.
 */
public class CompositeTitleTest {

    /**
     * Some checks for the constructor.
     */
    @Test
    public void testConstructor() {
        CompositeTitle t = new CompositeTitle();
        assertNull(t.getBackgroundPaint());
    }

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        CompositeTitle t1 = new CompositeTitle(new BlockContainer());
        CompositeTitle t2 = new CompositeTitle(new BlockContainer());
        assertEquals(t1, t2);
        assertEquals(t2, t1);

        // margin
        t1.setMargin(new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertNotEquals(t1, t2);
        t2.setMargin(new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertEquals(t1, t2);

        // frame
        t1.setFrame(new BlockBorder(Color.RED));
        assertNotEquals(t1, t2);
        t2.setFrame(new BlockBorder(Color.RED));
        assertEquals(t1, t2);

        // padding
        t1.setPadding(new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertNotEquals(t1, t2);
        t2.setPadding(new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertEquals(t1, t2);

        // contained titles
        t1.getContainer().add(new TextTitle("T1"));
        assertNotEquals(t1, t2);
        t2.getContainer().add(new TextTitle("T1"));
        assertEquals(t1, t2);

        t1.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.YELLOW));
        assertNotEquals(t1, t2);
        t2.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.YELLOW));
        assertEquals(t1, t2);

    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        CompositeTitle t1 = new CompositeTitle(new BlockContainer());
        t1.getContainer().add(new TextTitle("T1"));
        CompositeTitle t2 = new CompositeTitle(new BlockContainer());
        t2.getContainer().add(new TextTitle("T1"));
        assertEquals(t1, t2);
        int h1 = t1.hashCode();
        int h2 = t2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() {
        CompositeTitle t1 = new CompositeTitle(new BlockContainer());
        t1.getContainer().add(new TextTitle("T1"));
        t1.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.YELLOW));
        CompositeTitle t2 = null;
        try {
            t2 = CloneUtils.clone(t1);
        } catch (CloneNotSupportedException e) {
            fail(e.toString());
        }
        assertNotSame(t1, t2);
        assertSame(t1.getClass(), t2.getClass());
        assertEquals(t1, t2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        CompositeTitle t1 = new CompositeTitle(new BlockContainer());
        t1.getContainer().add(new TextTitle("T1"));
        t1.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.BLUE));
        CompositeTitle t2 = TestUtils.serialised(t1);
        assertEquals(t1, t2);
    }

}
