package pdk.chart.entity;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.util.CloneUtils;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link CategoryItemEntity} class.
 */
public class CategoryItemEntityTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DefaultCategoryDataset<String, String> d = new DefaultCategoryDataset<>();
        d.addValue(1.0, "R1", "C1");
        d.addValue(2.0, "R1", "C2");
        d.addValue(3.0, "R2", "C1");
        d.addValue(4.0, "R2", "C2");
        CategoryItemEntity<String, String> e1 = new CategoryItemEntity<>(
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), "ToolTip", "URL", d,
                "R2", "C2");
        CategoryItemEntity<String, String> e2 = new CategoryItemEntity<>(
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), "ToolTip", "URL", d,
                "R2", "C2");
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
        DefaultCategoryDataset<String, String> d = new DefaultCategoryDataset<>();
        d.addValue(1.0, "R1", "C1");
        d.addValue(2.0, "R1", "C2");
        d.addValue(3.0, "R2", "C1");
        d.addValue(4.0, "R2", "C2");
        CategoryItemEntity<String, String> e1 = new CategoryItemEntity<>(
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), "ToolTip", "URL", d,
                "R2", "C2");
        CategoryItemEntity<String, String> e2 = CloneUtils.clone(e1);
        assertNotSame(e1, e2);
        assertSame(e1.getClass(), e2.getClass());
        assertEquals(e1, e2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultCategoryDataset<String, String> d = new DefaultCategoryDataset<>();
        d.addValue(1.0, "R1", "C1");
        d.addValue(2.0, "R1", "C2");
        d.addValue(3.0, "R2", "C1");
        d.addValue(4.0, "R2", "C2");
        CategoryItemEntity<String, String> e1 = new CategoryItemEntity<>(
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), "ToolTip", "URL", d,
                "R2", "C2");
        CategoryItemEntity<String, String> e2 = TestUtils.serialised(e1);
        assertEquals(e1, e2);
    }

}
