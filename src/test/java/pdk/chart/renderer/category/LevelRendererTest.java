package pdk.chart.renderer.category;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.util.CloneUtils;
import pdk.chart.legend.LegendItem;
import pdk.chart.plot.CategoryPlot;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link LevelRenderer} class.
 */
public class LevelRendererTest {

    /**
     * Test that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        LevelRenderer r1 = new LevelRenderer();
        LevelRenderer r2 = new LevelRenderer();
        assertEquals(r1, r2);
        assertEquals(r2, r1);

        r1.setItemMargin(0.123);
        assertNotEquals(r1, r2);
        r2.setItemMargin(0.123);
        assertEquals(r1, r2);

        r1.setMaximumItemWidth(0.234);
        assertNotEquals(r1, r2);
        r2.setMaximumItemWidth(0.234);
        assertEquals(r1, r2);

    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        LevelRenderer r1 = new LevelRenderer();
        LevelRenderer r2 = new LevelRenderer();
        assertEquals(r1, r2);
        int h1 = r1.hashCode();
        int h2 = r2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        LevelRenderer r1 = new LevelRenderer();
        r1.setItemMargin(0.123);
        r1.setMaximumItemWidth(0.234);
        LevelRenderer r2 = CloneUtils.clone(r1);
        assertNotSame(r1, r2);
        assertSame(r1.getClass(), r2.getClass());
        assertEquals(r1, r2);

        assertTrue(checkIndependence(r1, r2));
        TestUtils.checkIndependence(r1, r2);
    }

    /**
     * Check that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        LevelRenderer r1 = new LevelRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Checks that the two renderers are equal but independent of one another.
     *
     * @param r1 renderer 1.
     * @param r2 renderer 2.
     * @return A boolean.
     */
    private boolean checkIndependence(LevelRenderer r1, LevelRenderer r2) {

        // should be equal...
        boolean b0 = r1.equals(r2);

        // and independent...
        r1.setItemMargin(0.0);
        boolean b1 = !r1.equals(r2);
        r2.setItemMargin(0.0);
        boolean b2 = r1.equals(r2);

        return b0 && b1 && b2;

    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        LevelRenderer r1 = new LevelRenderer();
        LevelRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

    /**
     * Draws the chart with a {@code null} info object to make sure that
     * no exceptions are thrown (particularly by code in the renderer).
     */
    @Test
    public void testDrawWithNullInfo() {
        try {
            DefaultCategoryDataset<String, String> dataset
                    = new DefaultCategoryDataset<>();
            dataset.addValue(1.0, "S1", "C1");
            CategoryPlot<String, String> plot = new CategoryPlot<>(dataset,
                    new CategoryAxis("Category"), new NumberAxis("Value"),
                    new LevelRenderer());
            Chart chart = new Chart(plot);
            /* BufferedImage image = */
            chart.createBufferedImage(300, 200,
                    null);
        } catch (NullPointerException e) {
            fail("No exception should be thrown.");
        }
    }

    /**
     * A check for the datasetIndex and seriesIndex fields in the LegendItem
     * returned by the getLegendItem() method.
     */
    @Test
    public void testGetLegendItemSeriesIndex() {
        DefaultCategoryDataset<String, String> dataset0
                = new DefaultCategoryDataset<>();
        dataset0.addValue(21.0, "R1", "C1");
        dataset0.addValue(22.0, "R2", "C1");
        DefaultCategoryDataset<String, String> dataset1
                = new DefaultCategoryDataset<>();
        dataset1.addValue(23.0, "R3", "C1");
        dataset1.addValue(24.0, "R4", "C1");
        dataset1.addValue(25.0, "R5", "C1");
        LevelRenderer r = new LevelRenderer();
        CategoryPlot<String, String> plot = new CategoryPlot<>(dataset0, new CategoryAxis("x"),
                new NumberAxis("y"), r);
        plot.setDataset(1, dataset1);
        Chart chart = new Chart(plot);
        LegendItem li = r.getLegendItem(1, 2);
        assertEquals("R5", li.getLabel());
        assertEquals(1, li.getDatasetIndex());
        assertEquals(2, li.getSeriesIndex());
    }

}
