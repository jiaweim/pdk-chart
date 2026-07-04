package pdk.chart.urls;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link CustomPieURLGenerator} class.
 */
public class CustomPieURLGeneratorTest {

    /**
     * Some checks for the equals() method.
     */
    @Test
    public void testEquals() {
        CustomPieURLGenerator<String> g1 = new CustomPieURLGenerator<>();
        CustomPieURLGenerator<String> g2 = new CustomPieURLGenerator<>();
        assertEquals(g1, g2);

        Map<String, String> m1 = new HashMap<>();
        m1.put("A", "https://www.jfree.org/");
        g1.addURLs(m1);
        assertNotEquals(g1, g2);
        g2.addURLs(m1);
        assertEquals(g1, g2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException if there is a problem with cloning.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        CustomPieURLGenerator<String> g1 = new CustomPieURLGenerator<>();
        Map<String, String> m1 = new HashMap<>();
        m1.put("A", "https://www.jfree.org/");
        g1.addURLs(m1);
        CustomPieURLGenerator<String> g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);

        // check independence
        Map<String, String> m2 = new HashMap<>();
        m2.put("B", "XYZ");
        g1.addURLs(m2);
        assertNotEquals(g1, g2);
    }

    /**
     * Checks that the class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        CustomPieURLGenerator<String> g1 = new CustomPieURLGenerator<>();
        assertInstanceOf(PublicCloneable.class, g1);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        CustomPieURLGenerator<String> g1 = new CustomPieURLGenerator<>();
        Map<String, String> m1 = new HashMap<>();
        m1.put("A", "https://www.jfree.org/");
        g1.addURLs(m1);
        CustomPieURLGenerator<String> g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

}
