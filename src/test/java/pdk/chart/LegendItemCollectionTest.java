package pdk.chart;

import org.junit.jupiter.api.Test;
import pdk.chart.util.CloneUtils;
import pdk.chart.legend.LegendItem;
import pdk.chart.legend.LegendItemCollection;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link LegendItemCollection} class.
 */
public class LegendItemCollectionTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        LegendItemCollection c1 = new LegendItemCollection();
        LegendItemCollection c2 = new LegendItemCollection();
        assertEquals(c1, c2);
        assertEquals(c2, c1);

        LegendItem item1 = new LegendItem("Label", "Description",
                "ToolTip", "URL", true,
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), true, Color.RED,
                true, Color.BLUE, new BasicStroke(1.2f), true,
                new Line2D.Double(1.0, 2.0, 3.0, 4.0),
                new BasicStroke(2.1f), Color.GREEN);
        LegendItem item2 = new LegendItem("Label", "Description",
                "ToolTip", "URL", true,
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0),
                true, Color.RED, true, Color.BLUE, new BasicStroke(1.2f), true,
                new Line2D.Double(1.0, 2.0, 3.0, 4.0), new BasicStroke(2.1f),
                Color.GREEN);
        c1.add(item1);
        assertNotEquals(c1, c2);
        c2.add(item2);
        assertEquals(c1, c2);

    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        LegendItemCollection c1 = new LegendItemCollection();
        c1.add(new LegendItem("Item", "Description", "ToolTip", "URL",
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), Color.RED));
        LegendItemCollection c2 = TestUtils.serialised(c1);
        assertEquals(c1, c2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        LegendItemCollection c1 = new LegendItemCollection();
        LegendItem item1 = new LegendItem("Item 1");
        c1.add(item1);
        LegendItemCollection c2 = CloneUtils.clone(c1);

        assertNotSame(c1, c2);
        assertSame(c1.getClass(), c2.getClass());
        assertEquals(c1, c2);

        Rectangle2D item1Shape = (Rectangle2D) item1.getShape();
        item1Shape.setRect(1.0, 2.0, 3.0, 4.0);
        assertNotEquals(c1, c2);
    }

}
