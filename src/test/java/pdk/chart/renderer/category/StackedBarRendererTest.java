package pdk.chart.renderer.category;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.data.Range;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StackedBarRenderer} class.
 */
public class StackedBarRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        StackedBarRenderer r1 = new StackedBarRenderer();
        StackedBarRenderer r2 = new StackedBarRenderer();
        assertEquals(r1, r2);
        assertEquals(r2, r1);

        r1.setRenderAsPercentages(true);
        assertNotEquals(r1, r2);
        r2.setRenderAsPercentages(true);
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        StackedBarRenderer r1 = new StackedBarRenderer();
        StackedBarRenderer r2 = new StackedBarRenderer();
        assertEquals(r1, r2);
        int h1 = r1.hashCode();
        int h2 = r2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StackedBarRenderer r1 = new StackedBarRenderer();
        StackedBarRenderer r2 = CloneUtils.clone(r1);
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

    /**
     * Check that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        StackedBarRenderer r1 = new StackedBarRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StackedBarRenderer r1 = new StackedBarRenderer();
        StackedBarRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

    /**
     * Some checks for the findRangeBounds() method.
     */
    @Test
    public void testFindRangeBounds() {
        StackedBarRenderer r = new StackedBarRenderer();
        assertNull(r.findRangeBounds(null));

        // an empty dataset should return a null range
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        assertNull(r.findRangeBounds(dataset));

        dataset.addValue(1.0, "R1", "C1");
        assertEquals(new Range(0.0, 1.0), r.findRangeBounds(dataset));

        dataset.addValue(-2.0, "R1", "C2");
        assertEquals(new Range(-2.0, 1.0), r.findRangeBounds(dataset));

        dataset.addValue(null, "R1", "C3");
        assertEquals(new Range(-2.0, 1.0), r.findRangeBounds(dataset));

        dataset.addValue(2.0, "R2", "C1");
        assertEquals(new Range(-2.0, 3.0), r.findRangeBounds(dataset));

        dataset.addValue(null, "R2", "C2");
        assertEquals(new Range(-2.0, 3.0), r.findRangeBounds(dataset));
    }

}
