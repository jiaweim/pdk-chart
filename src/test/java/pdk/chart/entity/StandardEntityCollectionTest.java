package pdk.chart.entity;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.util.CloneUtils;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardEntityCollection} class.
 */
public class StandardEntityCollectionTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        StandardEntityCollection c1 = new StandardEntityCollection();
        StandardEntityCollection c2 = new StandardEntityCollection();
        assertEquals(c1, c2);

        PieSectionEntity<String> e1 = new PieSectionEntity<>(
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0),
                new DefaultPieDataset<String>(), 0, 1, "Key", "ToolTip", "URL");
        c1.add(e1);
        assertNotEquals(c1, c2);
        PieSectionEntity<String> e2 = new PieSectionEntity<>(
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0),
                new DefaultPieDataset<String>(), 0, 1, "Key",
                "ToolTip", "URL");
        c2.add(e2);
        assertEquals(c1, c2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        PieSectionEntity<String> e1 = new PieSectionEntity<>(
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0),
                new DefaultPieDataset<String>(), 0, 1, "Key", "ToolTip", "URL");
        StandardEntityCollection c1 = new StandardEntityCollection();
        c1.add(e1);
        StandardEntityCollection c2 = CloneUtils.clone(c1);
        assertNotSame(c1, c2);
        assertSame(c1.getClass(), c2.getClass());
        assertEquals(c1, c2);

        // check independence
        c1.clear();
        assertNotEquals(c1, c2);
        c2.clear();
        assertEquals(c1, c2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        PieSectionEntity<String> e1 = new PieSectionEntity<>(
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0),
                new DefaultPieDataset<String>(), 0, 1, "Key", "ToolTip", "URL");
        StandardEntityCollection c1 = new StandardEntityCollection();
        c1.add(e1);
        StandardEntityCollection c2 = TestUtils.serialised(c1);
        assertEquals(c1, c2);
    }

}
