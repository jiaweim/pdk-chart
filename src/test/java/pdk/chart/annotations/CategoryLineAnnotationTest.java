package pdk.chart.annotations;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link CategoryLineAnnotation} class.
 */
public class CategoryLineAnnotationTest {

    @Test
    public void testConstructorExceptions() {
        Stroke stroke = new BasicStroke(2.0f);
        assertThrows(NullPointerException.class, () -> {
            CategoryLineAnnotation a1 = new CategoryLineAnnotation(null, 20.0, "Cat2", 200.0,
                    Color.BLUE, stroke);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            CategoryLineAnnotation a1 = new CategoryLineAnnotation("Cat1", Double.NaN, "Cat2", 200.0,
                    Color.BLUE, stroke);
        });
        assertThrows(NullPointerException.class, () -> {
            CategoryLineAnnotation a1 = new CategoryLineAnnotation("Cat1", 20.0, null, 200.0,
                    Color.BLUE, stroke);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            CategoryLineAnnotation a1 = new CategoryLineAnnotation("Cat1", 20.0, "Cat2", Double.NaN,
                    Color.BLUE, stroke);
        });
        assertThrows(NullPointerException.class, () -> {
            CategoryLineAnnotation a1 = new CategoryLineAnnotation("Cat1", 20.0, "Cat2", 200.0,
                    null, stroke);
        });
        assertThrows(NullPointerException.class, () -> {
            CategoryLineAnnotation a1 = new CategoryLineAnnotation("Cat1", 20.0, "Cat2", 200.0,
                    Color.BLUE, null);
        });
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        BasicStroke s1 = new BasicStroke(1.0f);
        BasicStroke s2 = new BasicStroke(2.0f);
        CategoryLineAnnotation a1 = new CategoryLineAnnotation("Category 1",
                1.0, "Category 2", 2.0, Color.RED, s1);
        CategoryLineAnnotation a2 = new CategoryLineAnnotation("Category 1",
                1.0, "Category 2", 2.0, Color.RED, s1);
        assertEquals(a1, a2);
        assertEquals(a2, a1);

        // category 1
        a1.setCategory1("Category A");
        assertNotEquals(a1, a2);
        a2.setCategory1("Category A");
        assertEquals(a1, a2);

        // value 1
        a1.setValue1(0.15);
        assertNotEquals(a1, a2);
        a2.setValue1(0.15);
        assertEquals(a1, a2);

        // category 2
        a1.setCategory2("Category B");
        assertNotEquals(a1, a2);
        a2.setCategory2("Category B");
        assertEquals(a1, a2);

        // value 2
        a1.setValue2(0.25);
        assertNotEquals(a1, a2);
        a2.setValue2(0.25);
        assertEquals(a1, a2);

        // paint
        a1.setPaint(Color.YELLOW);
        assertNotEquals(a1, a2);
        a2.setPaint(Color.YELLOW);
        assertEquals(a1, a2);

        // stroke
        a1.setStroke(s2);
        assertNotEquals(a1, a2);
        a2.setStroke(s2);
        assertEquals(a1, a2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        CategoryLineAnnotation a1 = new CategoryLineAnnotation("Category 1",
                1.0, "Category 2", 2.0, Color.RED, new BasicStroke(1.0f));
        CategoryLineAnnotation a2 = new CategoryLineAnnotation("Category 1",
                1.0, "Category 2", 2.0, Color.RED, new BasicStroke(1.0f));
        assertEquals(a1, a2);
        int h1 = a1.hashCode();
        int h2 = a2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        CategoryLineAnnotation a1 = new CategoryLineAnnotation("Category 1",
                1.0, "Category 2", 2.0, Color.RED, new BasicStroke(1.0f));
        CategoryLineAnnotation a2 = (CategoryLineAnnotation) a1.clone();
        assertNotSame(a1, a2);
        assertSame(a1.getClass(), a2.getClass());
        assertEquals(a1, a2);
    }

    /**
     * Checks that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        CategoryLineAnnotation a1 = new CategoryLineAnnotation(
                "Category 1", 1.0, "Category 2", 2.0, Color.RED,
                new BasicStroke(1.0f));
        assertTrue(a1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        CategoryLineAnnotation a1 = new CategoryLineAnnotation("Category 1",
                1.0, "Category 2", 2.0, Color.RED, new BasicStroke(1.0f));
        CategoryLineAnnotation a2 = TestUtils.serialised(a1);
        assertEquals(a1, a2);
    }

}
