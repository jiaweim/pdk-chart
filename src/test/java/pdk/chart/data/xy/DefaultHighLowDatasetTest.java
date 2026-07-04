package pdk.chart.data.xy;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.api.PublicCloneable;
import pdk.chart.util.CloneUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link DefaultHighLowDataset} class.
 */
public class DefaultHighLowDatasetTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        DefaultHighLowDataset d1 = new DefaultHighLowDataset("Series 1",
                new Date[0], new double[0], new double[0], new double[0],
                new double[0], new double[0]);
        DefaultHighLowDataset d2 = new DefaultHighLowDataset("Series 1",
                new Date[0], new double[0], new double[0], new double[0],
                new double[0], new double[0]);
        assertEquals(d1, d2);
        assertEquals(d2, d1);

        d1 = new DefaultHighLowDataset("Series 2",
                new Date[0], new double[0], new double[0], new double[0],
                new double[0], new double[0]);
        assertNotEquals(d1, d2);
        d2 = new DefaultHighLowDataset("Series 2",
                new Date[0], new double[0], new double[0], new double[0],
                new double[0], new double[0]);
        assertEquals(d1, d2);

        d1 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[1], new double[1],
                new double[1], new double[1], new double[1]);
        assertNotEquals(d1, d2);
        d2 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[1], new double[1],
                new double[1], new double[1], new double[1]);
        assertEquals(d1, d2);

        d1 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[]{1.2}, new double[1],
                new double[1], new double[1], new double[1]);
        assertNotEquals(d1, d2);
        d2 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[]{1.2}, new double[1],
                new double[1], new double[1], new double[1]);
        assertEquals(d1, d2);

        d1 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[]{1.2},
                new double[]{3.4}, new double[1], new double[1],
                new double[1]);
        assertNotEquals(d1, d2);
        d2 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[]{1.2},
                new double[]{3.4}, new double[1], new double[1],
                new double[1]);
        assertEquals(d1, d2);

        d1 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[]{1.2},
                new double[]{3.4}, new double[]{5.6}, new double[1],
                new double[1]);
        assertNotEquals(d1, d2);
        d2 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[]{1.2},
                new double[]{3.4}, new double[]{5.6}, new double[1],
                new double[1]);
        assertEquals(d1, d2);

        d1 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[]{1.2},
                new double[]{3.4}, new double[]{5.6}, new double[]{7.8},
                new double[1]);
        assertNotEquals(d1, d2);
        d2 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[]{1.2},
                new double[]{3.4}, new double[]{5.6}, new double[]{7.8},
                new double[1]);
        assertEquals(d1, d2);

        d1 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[]{1.2},
                new double[]{3.4}, new double[]{5.6}, new double[]{7.8},
                new double[]{99.9});
        assertNotEquals(d1, d2);
        d2 = new DefaultHighLowDataset("Series 2",
                new Date[]{new Date(123L)}, new double[]{1.2},
                new double[]{3.4}, new double[]{5.6}, new double[]{7.8},
                new double[]{99.9});
        assertEquals(d1, d2);

    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        DefaultHighLowDataset d1 = new DefaultHighLowDataset("Series 1",
                new Date[]{new Date(123L)}, new double[]{1.2},
                new double[]{3.4}, new double[]{5.6}, new double[]{7.8},
                new double[]{99.9});
        DefaultHighLowDataset d2 = CloneUtils.clone(d1);
        assertNotSame(d1, d2);
        assertSame(d1.getClass(), d2.getClass());
        assertEquals(d1, d2);
    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        DefaultHighLowDataset d1 = new DefaultHighLowDataset("Series 1",
                new Date[0], new double[0], new double[0], new double[0],
                new double[0], new double[0]);
        assertTrue(d1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        DefaultHighLowDataset d1 = new DefaultHighLowDataset("Series 1",
                new Date[]{new Date(123L)}, new double[]{1.2},
                new double[]{3.4}, new double[]{5.6}, new double[]{7.8},
                new double[]{99.9});
        DefaultHighLowDataset d2 = TestUtils.serialised(d1);
        assertEquals(d1, d2);
    }

}
