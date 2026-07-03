package pdk.chart.axis;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.TestUtils;
import pdk.chart.internal.CloneUtils;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link SubCategoryAxis} class.
 */
public class SubCategoryAxisTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        SubCategoryAxis a1 = new SubCategoryAxis("Test");
        SubCategoryAxis a2 = new SubCategoryAxis("Test");
        assertEquals(a1, a2);
        assertEquals(a2, a1);

        // subcategories
        a1.addSubCategory("Sub 1");
        assertNotEquals(a1, a2);
        a2.addSubCategory("Sub 1");
        assertEquals(a1, a2);

        // subLabelFont
        a1.setSubLabelFont(new Font("Serif", Font.BOLD, 15));
        assertNotEquals(a1, a2);
        a2.setSubLabelFont(new Font("Serif", Font.BOLD, 15));
        assertEquals(a1, a2);

        // subLabelPaint
        a1.setSubLabelPaint(Color.RED);
        assertNotEquals(a1, a2);
        a2.setSubLabelPaint(Color.RED);
        assertEquals(a1, a2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        SubCategoryAxis a1 = new SubCategoryAxis("Test");
        SubCategoryAxis a2 = new SubCategoryAxis("Test");
        assertEquals(a1, a2);
        int h1 = a1.hashCode();
        int h2 = a2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        SubCategoryAxis a1 = new SubCategoryAxis("Test");
        a1.addSubCategory("SubCategoryA");
        SubCategoryAxis a2 = CloneUtils.clone(a1);
        assertNotSame(a1, a2);
        assertSame(a1.getClass(), a2.getClass());
        assertEquals(a1, a2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        SubCategoryAxis a1 = new SubCategoryAxis("Test Axis");
        a1.addSubCategory("SubCategoryA");
        SubCategoryAxis a2 = TestUtils.serialised(a1);
        assertEquals(a1, a2);
    }

    /**
     * A check for the NullPointerException in bug 2275695.
     */
    @Test
    public void test2275695() {
        Chart chart = JChart.barStacked(null,
                "Category", "Value", "Test", PlotOrientation.VERTICAL,
                true, false, false);
        CategoryPlot<?, ?> plot = (CategoryPlot) chart.getPlot();
        plot.setDomainAxis(new SubCategoryAxis("SubCategoryAxis"));
        try {
            BufferedImage image = new BufferedImage(200, 100,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            chart.draw(g2, new Rectangle2D.Double(0, 0, 200, 100), null, null);
            g2.dispose();
        } catch (Exception e) {
            fail("There should be no exception.");
        }
    }

}
