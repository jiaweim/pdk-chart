package pdk.chart.renderer.category;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.Range;
import pdk.chart.data.statistics.DefaultStatisticalCategoryDataset;
import pdk.chart.util.CloneUtils;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StatisticalBarRenderer} class.
 */
public class StatisticalBarRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        StatisticalBarRenderer r1 = new StatisticalBarRenderer();
        StatisticalBarRenderer r2 = new StatisticalBarRenderer();
        assertEquals(r1, r2);

        r1.setErrorIndicatorPaint(Color.RED);
        assertNotEquals(r1, r2);
        r2.setErrorIndicatorPaint(Color.RED);
        assertEquals(r2, r1);

        r1.setErrorIndicatorStroke(new BasicStroke(1.5f));
        assertNotEquals(r1, r2);
        r2.setErrorIndicatorStroke(new BasicStroke(1.5f));
        assertEquals(r2, r1);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        StatisticalBarRenderer r1 = new StatisticalBarRenderer();
        StatisticalBarRenderer r2 = new StatisticalBarRenderer();
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
        StatisticalBarRenderer r1 = new StatisticalBarRenderer();
        StatisticalBarRenderer r2 = CloneUtils.clone(r1);
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
        StatisticalBarRenderer r1 = new StatisticalBarRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StatisticalBarRenderer r1 = new StatisticalBarRenderer();
        StatisticalBarRenderer r2 = TestUtils.serialised(r1);
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
            DefaultStatisticalCategoryDataset<String, String> dataset
                    = new DefaultStatisticalCategoryDataset<>();
            dataset.add(1.0, 2.0, "S1", "C1");
            dataset.add(3.0, 4.0, "S1", "C2");
            CategoryPlot<String, String> plot = new CategoryPlot<>(dataset,
                    new CategoryAxis("Category"), new NumberAxis("Value"),
                    new StatisticalBarRenderer());
            Chart chart = new Chart(plot);
            /* BufferedImage image = */
            chart.createBufferedImage(300, 200,
                    null);
        } catch (NullPointerException e) {
            fail("No exception should be thrown.");
        }
    }

    /**
     * Draws the chart with a {@code null} mean value to make sure that
     * no exceptions are thrown (particularly by code in the renderer).  See
     * bug report 1779941.
     */
    @Test
    public void testDrawWithNullMeanVertical() {
        try {
            DefaultStatisticalCategoryDataset<String, String> dataset
                    = new DefaultStatisticalCategoryDataset<>();
            dataset.add(1.0, 2.0, "S1", "C1");
            dataset.add(null, 4.0, "S1", "C2");
            CategoryPlot<String, String> plot = new CategoryPlot<>(dataset,
                    new CategoryAxis("Category"), new NumberAxis("Value"),
                    new StatisticalBarRenderer());
            Chart chart = new Chart(plot);
            /* BufferedImage image = */
            chart.createBufferedImage(300, 200,
                    null);
        } catch (NullPointerException e) {
            fail("No exception should be thrown.");
        }
    }

    /**
     * Draws the chart with a {@code null} mean value to make sure that
     * no exceptions are thrown (particularly by code in the renderer).  See
     * bug report 1779941.
     */
    @Test
    public void testDrawWithNullMeanHorizontal() {
        try {
            DefaultStatisticalCategoryDataset<String, String> dataset
                    = new DefaultStatisticalCategoryDataset<>();
            dataset.add(1.0, 2.0, "S1", "C1");
            dataset.add(null, 4.0, "S1", "C2");
            CategoryPlot<String, String> plot = new CategoryPlot<>(dataset,
                    new CategoryAxis("Category"), new NumberAxis("Value"),
                    new StatisticalBarRenderer());
            plot.setOrientation(PlotOrientation.HORIZONTAL);
            Chart chart = new Chart(plot);
            /* BufferedImage image = */
            chart.createBufferedImage(300, 200,
                    null);
        } catch (NullPointerException e) {
            fail("No exception should be thrown.");
        }
    }

    /**
     * Draws the chart with a {@code null} standard deviation to make sure
     * that no exceptions are thrown (particularly by code in the renderer).
     * See bug report 1779941.
     */
    @Test
    public void testDrawWithNullDeviationVertical() {
        try {
            DefaultStatisticalCategoryDataset<String, String> dataset
                    = new DefaultStatisticalCategoryDataset<>();
            dataset.add(1.0, 2.0, "S1", "C1");
            dataset.add(4.0, null, "S1", "C2");
            CategoryPlot<String, String> plot = new CategoryPlot<>(dataset,
                    new CategoryAxis("Category"), new NumberAxis("Value"),
                    new StatisticalBarRenderer());
            Chart chart = new Chart(plot);
            /* BufferedImage image = */
            chart.createBufferedImage(300, 200,
                    null);
        } catch (NullPointerException e) {
            fail("No exception should be thrown.");
        }
    }

    /**
     * Draws the chart with a {@code null} standard deviation to make sure
     * that no exceptions are thrown (particularly by code in the renderer).
     * See bug report 1779941.
     */
    @Test
    public void testDrawWithNullDeviationHorizontal() {
        try {
            DefaultStatisticalCategoryDataset<String, String> dataset
                    = new DefaultStatisticalCategoryDataset<>();
            dataset.add(1.0, 2.0, "S1", "C1");
            dataset.add(4.0, null, "S1", "C2");
            CategoryPlot<String, String> plot = new CategoryPlot<>(dataset,
                    new CategoryAxis("Category"), new NumberAxis("Value"),
                    new StatisticalBarRenderer());
            plot.setOrientation(PlotOrientation.HORIZONTAL);
            Chart chart = new Chart(plot);
            /* BufferedImage image = */
            chart.createBufferedImage(300, 200,
                    null);
        } catch (NullPointerException e) {
            fail("No exception should be thrown.");
        }
    }

    /**
     * Some checks for the findRangeBounds() method.
     */
    @Test
    public void testFindRangeBounds() {
        StatisticalBarRenderer r = new StatisticalBarRenderer();
        assertNull(r.findRangeBounds(null));

        // an empty dataset should return a null range
        DefaultStatisticalCategoryDataset<String, String> dataset
                = new DefaultStatisticalCategoryDataset<>();
        assertNull(r.findRangeBounds(dataset));

        dataset.add(1.0, 0.5, "R1", "C1");
        assertEquals(new Range(0.0, 1.5), r.findRangeBounds(dataset));

        r.setIncludeBaseInRange(false);
        assertEquals(new Range(0.5, 1.5), r.findRangeBounds(dataset));
        r.setIncludeBaseInRange(true);

        dataset.add(-2.0, 0.2, "R1", "C2");
        assertEquals(new Range(-2.2, 1.5), r.findRangeBounds(dataset));

        dataset.add(null, null, "R1", "C3");
        assertEquals(new Range(-2.2, 1.5), r.findRangeBounds(dataset));

        dataset.add(5.0, 1.0, "R2", "C3");
        assertEquals(new Range(-2.2, 6.0), r.findRangeBounds(dataset));

        // check that the series visible flag is observed
        r.setSeriesVisible(1, Boolean.FALSE);
        assertEquals(new Range(-2.2, 1.5), r.findRangeBounds(dataset));
    }

}
