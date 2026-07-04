package pdk.chart.labels;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.util.CloneUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link StandardCategoryItemLabelGenerator} class.
 */
public class StandardCategoryItemLabelGeneratorTest {

    /**
     * Some checks for the generalLabel() method.
     */
    @Test
    public void testGenerateLabel() {
        StandardCategoryItemLabelGenerator g
                = new StandardCategoryItemLabelGenerator("{2}",
                new DecimalFormat("0.000"));
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(1.0, "R0", "C0");
        dataset.addValue(2.0, "R0", "C1");
        dataset.addValue(3.0, "R1", "C0");
        dataset.addValue(null, "R1", "C1");
        String s = g.generateLabel(dataset, 0, 0);
        assertTrue(s.startsWith("1"));
        assertTrue(s.endsWith("000"));

        // try a null value
        s = g.generateLabel(dataset, 1, 1);
        assertEquals("-", s);
    }

    /**
     * Some checks for the equals() method.
     */
    @Test
    public void testEquals() {
        StandardCategoryItemLabelGenerator g1
                = new StandardCategoryItemLabelGenerator();
        StandardCategoryItemLabelGenerator g2
                = new StandardCategoryItemLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g2, g1);

        g1 = new StandardCategoryItemLabelGenerator("{0}",
                new DecimalFormat("0.000"));
        assertNotEquals(g1, g2);
        g2 = new StandardCategoryItemLabelGenerator("{0}",
                new DecimalFormat("0.000"));
        assertEquals(g1, g2);

        g1 = new StandardCategoryItemLabelGenerator("{1}",
                new DecimalFormat("0.000"));
        assertNotEquals(g1, g2);
        g2 = new StandardCategoryItemLabelGenerator("{1}",
                new DecimalFormat("0.000"));
        assertEquals(g1, g2);

        g1 = new StandardCategoryItemLabelGenerator("{2}",
                new SimpleDateFormat("d-MMM"));
        assertNotEquals(g1, g2);
        g2 = new StandardCategoryItemLabelGenerator("{2}",
                new SimpleDateFormat("d-MMM"));
        assertEquals(g1, g2);

    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        StandardCategoryItemLabelGenerator g1
                = new StandardCategoryItemLabelGenerator();
        StandardCategoryItemLabelGenerator g2
                = new StandardCategoryItemLabelGenerator();
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardCategoryItemLabelGenerator g1
                = new StandardCategoryItemLabelGenerator();
        StandardCategoryItemLabelGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);
    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        StandardCategoryItemLabelGenerator g1
                = new StandardCategoryItemLabelGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardCategoryItemLabelGenerator g1
                = new StandardCategoryItemLabelGenerator("{2}",
                DateFormat.getInstance());
        StandardCategoryItemLabelGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

    /**
     * A test for bug 1481087.
     */
    @Test
    public void testEquals1481087() {
        StandardCategoryItemLabelGenerator g1
                = new StandardCategoryItemLabelGenerator("{0}",
                new DecimalFormat("0.00"));
        StandardCategoryToolTipGenerator g2
                = new StandardCategoryToolTipGenerator("{0}",
                new DecimalFormat("0.00"));
        assertNotEquals(g1, g2);
    }

}
