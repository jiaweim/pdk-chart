package pdk.chart.renderer.category;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.Range;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.util.CloneUtils;
import pdk.chart.legend.LegendItem;
import pdk.chart.plot.CategoryPlot;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link LineAndShapeRenderer} class.
 */
public class LineAndShapeRendererTest {

    /**
     * Test that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        LineAndShapeRenderer r1 = new LineAndShapeRenderer();
        LineAndShapeRenderer r2 = new LineAndShapeRenderer();
        assertEquals(r1, r2);

        r1.setDefaultLinesVisible(!r1.getDefaultLinesVisible());
        assertNotEquals(r1, r2);
        r2.setDefaultLinesVisible(r1.getDefaultLinesVisible());
        assertEquals(r1, r2);

        r1.setSeriesLinesVisible(1, true);
        assertNotEquals(r1, r2);
        r2.setSeriesLinesVisible(1, true);
        assertEquals(r1, r2);

        r1.setDefaultShapesVisible(!r1.getDefaultShapesVisible());
        assertNotEquals(r1, r2);
        r2.setDefaultShapesVisible(r1.getDefaultShapesVisible());
        assertEquals(r1, r2);

        r1.setSeriesShapesVisible(1, true);
        assertNotEquals(r1, r2);
        r2.setSeriesShapesVisible(1, true);
        assertEquals(r1, r2);

        r1.setSeriesShapesFilled(1, true);
        assertNotEquals(r1, r2);
        r2.setSeriesShapesFilled(1, true);
        assertEquals(r1, r2);

        r1.setDefaultShapesFilled(false);
        assertNotEquals(r1, r2);
        r2.setDefaultShapesFilled(false);
        assertEquals(r1, r2);

        r1.setUseOutlinePaint(true);
        assertNotEquals(r1, r2);
        r2.setUseOutlinePaint(true);
        assertEquals(r1, r2);

        r1.setUseSeriesOffset(true);
        assertNotEquals(r1, r2);
        r2.setUseSeriesOffset(true);
        assertEquals(r1, r2);

        r1.setItemMargin(0.14);
        assertNotEquals(r1, r2);
        r2.setItemMargin(0.14);
        assertEquals(r1, r2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        LineAndShapeRenderer r1 = new LineAndShapeRenderer();
        LineAndShapeRenderer r2 = new LineAndShapeRenderer();
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
        LineAndShapeRenderer r1 = new LineAndShapeRenderer();
        LineAndShapeRenderer r2 = CloneUtils.clone(r1);
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
        LineAndShapeRenderer r1 = new LineAndShapeRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Checks that the two renderers are equal but independent of one another.
     *
     * @param r1 renderer 1.
     * @param r2 renderer 2.
     * @return A boolean.
     */
    private boolean checkIndependence(LineAndShapeRenderer r1, LineAndShapeRenderer r2) {

        // should be equal...
        if (!r1.equals(r2)) {
            return false;
        }

        // and independent...
        r1.setDefaultLinesVisible(!r1.getDefaultLinesVisible());
        if (r1.equals(r2)) {
            return false;
        }
        r2.setDefaultLinesVisible(r1.getDefaultLinesVisible());
        if (!r1.equals(r2)) {
            return false;
        }

        r1.setSeriesLinesVisible(1, true);
        if (r1.equals(r2)) {
            return false;
        }
        r2.setSeriesLinesVisible(1, true);
        if (!r1.equals(r2)) {
            return false;
        }

        r1.setDefaultShapesVisible(!r1.getDefaultShapesVisible());
        if (r1.equals(r2)) {
            return false;
        }
        r2.setDefaultShapesVisible(r1.getDefaultShapesVisible());
        if (!r1.equals(r2)) {
            return false;
        }

        r1.setSeriesShapesVisible(1, true);
        if (r1.equals(r2)) {
            return false;
        }
        r2.setSeriesShapesVisible(1, true);
        if (!r1.equals(r2)) {
            return false;
        }

        r1.setSeriesShapesFilled(0, false);
        r2.setSeriesShapesFilled(0, true);
        if (r1.equals(r2)) {
            return false;
        }
        r2.setSeriesShapesFilled(0, false);
        if (!r1.equals(r2)) {
            return false;
        }

        r1.setDefaultShapesFilled(false);
        r2.setDefaultShapesFilled(true);
        if (r1.equals(r2)) {
            return false;
        }
        r2.setDefaultShapesFilled(false);
        return r1.equals(r2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        LineAndShapeRenderer r1 = new LineAndShapeRenderer();
        LineAndShapeRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
        TestUtils.checkIndependence(r1, r2);
    }

    /**
     * A check for the datasetIndex and seriesIndex fields in the LegendItem
     * returned by the getLegendItem() method.
     */
    @Test
    public void testGetLegendItemSeriesIndex() {
        DefaultCategoryDataset<String, String> dataset0 = new DefaultCategoryDataset<>();
        dataset0.addValue(21.0, "R1", "C1");
        dataset0.addValue(22.0, "R2", "C1");
        DefaultCategoryDataset<String, String> dataset1 = new DefaultCategoryDataset<>();
        dataset1.addValue(23.0, "R3", "C1");
        dataset1.addValue(24.0, "R4", "C1");
        dataset1.addValue(25.0, "R5", "C1");
        LineAndShapeRenderer r = new LineAndShapeRenderer();
        CategoryPlot<String, String> plot = new CategoryPlot<>(dataset0, new CategoryAxis("x"),
                new NumberAxis("y"), r);
        plot.setDataset(1, dataset1);
        Chart chart = new Chart(plot);
        LegendItem li = r.getLegendItem(1, 2);
        assertEquals("R5", li.getLabel());
        assertEquals(1, li.getDatasetIndex());
        assertEquals(2, li.getSeriesIndex());
    }

    /**
     * Some checks for the findRangeBounds() method.
     */
    @Test
    public void testFindRangeBounds() {
        LineAndShapeRenderer r = new LineAndShapeRenderer();
        assertNull(r.findRangeBounds(null));

        // an empty dataset should return a null range
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        assertNull(r.findRangeBounds(dataset));

        dataset.addValue(1.0, "R1", "C1");
        assertEquals(new Range(1.0, 1.0), r.findRangeBounds(dataset));

        dataset.addValue(-2.0, "R1", "C2");
        assertEquals(new Range(-2.0, 1.0), r.findRangeBounds(dataset));

        dataset.addValue(null, "R1", "C3");
        assertEquals(new Range(-2.0, 1.0), r.findRangeBounds(dataset));

        dataset.addValue(-6.0, "R2", "C1");
        assertEquals(new Range(-6.0, 1.0), r.findRangeBounds(dataset));

        r.setSeriesVisible(1, Boolean.FALSE);
        assertEquals(new Range(-2.0, 1.0), r.findRangeBounds(dataset));
    }

}
