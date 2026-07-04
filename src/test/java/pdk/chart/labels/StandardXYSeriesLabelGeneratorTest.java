package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardXYSeriesLabelGenerator} class.
 */
public class StandardXYSeriesLabelGeneratorTest {

    /**
     * Some checks for the generalLabel() method.
     */
    @Test
    public void testGenerateLabel() {
        StandardXYSeriesLabelGenerator g
                = new StandardXYSeriesLabelGenerator("Series {0}");
        XYSeriesCollection<String> dataset = new XYSeriesCollection<>();
        dataset.addSeries(new XYSeries<>("1"));
        dataset.addSeries(new XYSeries<>("2"));
        assertEquals("Series 1", g.generateLabel(dataset, 0));
        assertEquals("Series 2", g.generateLabel(dataset, 1));
    }

    /**
     * Some checks for the equals() method.
     */
    @Test
    public void testEquals() {
        StandardXYSeriesLabelGenerator g1
                = new StandardXYSeriesLabelGenerator("Series {0}");
        StandardXYSeriesLabelGenerator g2
                = new StandardXYSeriesLabelGenerator("Series {0}");
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new StandardXYSeriesLabelGenerator("{1}");
        assertNotEquals(g1, g2);
        g2 = new StandardXYSeriesLabelGenerator("{1}");
        assertEquals(g1, g2);
    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        StandardXYSeriesLabelGenerator g1
                = new StandardXYSeriesLabelGenerator();
        StandardXYSeriesLabelGenerator g2
                = new StandardXYSeriesLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardXYSeriesLabelGenerator g1
                = new StandardXYSeriesLabelGenerator("Series {0}");
        StandardXYSeriesLabelGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        StandardXYSeriesLabelGenerator g1
                = new StandardXYSeriesLabelGenerator("Series {0}");
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardXYSeriesLabelGenerator g1
                = new StandardXYSeriesLabelGenerator("Series {0}");
        StandardXYSeriesLabelGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }
}
