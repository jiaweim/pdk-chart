package pdk.chart.entity;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link CategoryLabelEntity} class.
 */
public class CategoryLabelEntityTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        CategoryLabelEntity<String> e1 = new CategoryLabelEntity<>("A",
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), "ToolTip", "URL");
        CategoryLabelEntity<String> e2 = new CategoryLabelEntity<>("A",
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), "ToolTip", "URL");
        assertEquals(e1, e2);

        e1 = new CategoryLabelEntity<>("B", new Rectangle2D.Double(1.0, 2.0,
                3.0, 4.0), "ToolTip", "URL");
        assertNotEquals(e1, e2);
        e2 = new CategoryLabelEntity<>("B", new Rectangle2D.Double(1.0, 2.0,
                3.0, 4.0), "ToolTip", "URL");
        assertEquals(e1, e2);

        e1.setArea(new Rectangle2D.Double(4.0, 3.0, 2.0, 1.0));
        assertNotEquals(e1, e2);
        e2.setArea(new Rectangle2D.Double(4.0, 3.0, 2.0, 1.0));
        assertEquals(e1, e2);

        e1.setToolTipText("New ToolTip");
        assertNotEquals(e1, e2);
        e2.setToolTipText("New ToolTip");
        assertEquals(e1, e2);

        e1.setURLText("New URL");
        assertNotEquals(e1, e2);
        e2.setURLText("New URL");
        assertEquals(e1, e2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        CategoryLabelEntity<String> e1 = new CategoryLabelEntity<>("A",
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), "ToolTip", "URL");
        CategoryLabelEntity<String> e2 = CloneUtils.clone(e1);
        assertNotSame(e1, e2);
        assertSame(e1.getClass(), e2.getClass());
        assertEquals(e1, e2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        CategoryLabelEntity<String> e1 = new CategoryLabelEntity<>("A",
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), "ToolTip", "URL");
        CategoryLabelEntity<String> e2 = TestUtils.serialised(e1);
        assertEquals(e1, e2);
    }

}
