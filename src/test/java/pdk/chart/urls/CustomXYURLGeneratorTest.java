package pdk.chart.urls;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link CustomXYURLGenerator} class.
 */
public class CustomXYURLGeneratorTest {

    /**
     * Some checks for the equals() method.
     */
    @Test
    public void testEquals() {
        CustomXYURLGenerator g1 = new CustomXYURLGenerator();
        CustomXYURLGenerator g2 = new CustomXYURLGenerator();
        assertEquals(g1, g2);
        List<String> u1 = new ArrayList<>();
        u1.add("URL A1");
        u1.add("URL A2");
        u1.add("URL A3");
        g1.addURLSeries(u1);
        assertNotEquals(g1, g2);
        List<String> u2 = new ArrayList<>();
        u2.add("URL A1");
        u2.add("URL A2");
        u2.add("URL A3");
        g2.addURLSeries(u2);
        assertEquals(g1, g2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        CustomXYURLGenerator g1 = new CustomXYURLGenerator();
        List<String> u1 = new ArrayList<>();
        u1.add("URL A1");
        u1.add("URL A2");
        u1.add("URL A3");
        g1.addURLSeries(u1);
        CustomXYURLGenerator g2 = CloneUtils.clone(g1);
        assertNotSame(g1, g2);
        assertSame(g1.getClass(), g2.getClass());
        assertEquals(g1, g2);

        // check independence
        List<String> u2 = new ArrayList<>();
        u2.add("URL XXX");
        g1.addURLSeries(u2);
        assertNotEquals(g1, g2);
        g2.addURLSeries(new ArrayList<>(u2));
        assertEquals(g1, g2);
    }

    /**
     * Checks that the class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        CustomXYURLGenerator g1 = new CustomXYURLGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        List<String> u1 = new ArrayList<>();
        u1.add("URL A1");
        u1.add("URL A2");
        u1.add("URL A3");

        List<String> u2 = new ArrayList<>();
        u2.add("URL B1");
        u2.add("URL B2");
        u2.add("URL B3");

        CustomXYURLGenerator g1 = new CustomXYURLGenerator();
        g1.addURLSeries(u1);
        g1.addURLSeries(u2);
        CustomXYURLGenerator g2 = TestUtils.serialised(g1);
        assertEquals(g1, g2);
    }

    /**
     * Some checks for the addURLSeries() method.
     */
    @Test
    public void testAddURLSeries() {
        CustomXYURLGenerator g1 = new CustomXYURLGenerator();
        // you can add a null list - it would have been better if this
        // required EMPTY_LIST
        g1.addURLSeries(null);
        assertEquals(1, g1.getListCount());
        assertEquals(0, g1.getURLCount(0));

        List<String> list1 = new ArrayList<>();
        list1.add("URL1");
        g1.addURLSeries(list1);
        assertEquals(2, g1.getListCount());
        assertEquals(0, g1.getURLCount(0));
        assertEquals(1, g1.getURLCount(1));
        assertEquals("URL1", g1.getURL(1, 0));

        // if we modify the original list, it's best if the URL generator is
        // not affected
        list1.clear();
        assertEquals("URL1", g1.getURL(1, 0));
    }

}
