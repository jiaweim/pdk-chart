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
 * Tests for the {@link CategoryStepRenderer} class.
 */
public class CategoryStepRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        CategoryStepRenderer r1 = new CategoryStepRenderer(false);
        CategoryStepRenderer r2 = new CategoryStepRenderer(false);
        assertEquals(r1, r2);

        r1 = new CategoryStepRenderer(true);
        assertNotEquals(r1, r2);
        r2 = new CategoryStepRenderer(true);
        assertEquals(r1, r2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        CategoryStepRenderer r1 = new CategoryStepRenderer(false);
        CategoryStepRenderer r2 = CloneUtils.clone(r1);
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
        CategoryStepRenderer r1 = new CategoryStepRenderer(false);
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        CategoryStepRenderer r1 = new CategoryStepRenderer();
        CategoryStepRenderer r2 = TestUtils.serialised(r1);
        assertEquals(r1, r2);
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
        CategoryStepRenderer r = new CategoryStepRenderer();
        CategoryPlot<String, String> plot = new CategoryPlot<>(dataset0, new CategoryAxis("x"),
                new NumberAxis("y"), r);
        plot.setDataset(1, dataset1);
        /*JFreeChart chart =*/
        new Chart(plot);
        LegendItem li = r.getLegendItem(1, 2);
        assertEquals("R5", li.getLabel());
        assertEquals(1, li.getDatasetIndex());
        assertEquals(2, li.getSeriesIndex());
    }

}
