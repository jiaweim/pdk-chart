package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardCategorySeriesLabelGenerator} class.
 */
public class StandardCategorySeriesLabelGeneratorTest {

    /**
     * Some checks for the generalLabel() method.
     */
    @Test
    public void testGenerateLabel() {
        StandardCategorySeriesLabelGenerator g
                = new StandardCategorySeriesLabelGenerator("{0}");
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(1.0, "R0", "C0");
        dataset.addValue(2.0, "R0", "C1");
        dataset.addValue(3.0, "R1", "C0");
        dataset.addValue(null, "R1", "C1");
        String s = g.generateLabel(dataset, 0);
        assertEquals("R0", s);
    }

    /**
     * Some checks for the equals() method.
     */
    @Test
    public void testEquals() {
        StandardCategorySeriesLabelGenerator g1
                = new StandardCategorySeriesLabelGenerator();
        StandardCategorySeriesLabelGenerator g2
                = new StandardCategorySeriesLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new StandardCategorySeriesLabelGenerator("{1}");
        assertNotEquals(g1, g2);
        g2 = new StandardCategorySeriesLabelGenerator("{1}");
        assertEquals(g1, g2);
    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        StandardCategorySeriesLabelGenerator g1
                = new StandardCategorySeriesLabelGenerator();
        StandardCategorySeriesLabelGenerator g2
                = new StandardCategorySeriesLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardCategorySeriesLabelGenerator g1
                = new StandardCategorySeriesLabelGenerator("{1}");
        StandardCategorySeriesLabelGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        StandardCategorySeriesLabelGenerator g1
                = new StandardCategorySeriesLabelGenerator("{1}");
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardCategorySeriesLabelGenerator g1
                = new StandardCategorySeriesLabelGenerator("{2}");
        StandardCategorySeriesLabelGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
