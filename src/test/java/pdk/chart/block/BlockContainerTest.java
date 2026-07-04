package pdk.chart.block;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link BlockContainer} class.
 */
public class BlockContainerTest {

    /**
     * Confirm that the equals() method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        BlockContainer c1 = new BlockContainer(new FlowArrangement());
        BlockContainer c2 = new BlockContainer(new FlowArrangement());
        assertEquals(c1, c2);
        assertEquals(c2, c2);

        c1.setArrangement(new ColumnArrangement());
        assertNotEquals(c1, c2);
        c2.setArrangement(new ColumnArrangement());
        assertEquals(c1, c2);

        c1.add(new EmptyBlock(1.2, 3.4));
        assertNotEquals(c1, c2);
        c2.add(new EmptyBlock(1.2, 3.4));
        assertEquals(c1, c2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        BlockContainer c1 = new BlockContainer(new FlowArrangement());
        c1.add(new EmptyBlock(1.2, 3.4));
        BlockContainer c2 = CloneUtils.clone(c1);
        assertNotSame(c1, c2);
        assertSame(c1.getClass(), c2.getClass());
        assertEquals(c1, c2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        BlockContainer c1 = new BlockContainer();
        c1.add(new EmptyBlock(1.2, 3.4));
        BlockContainer c2 = TestUtils.serialised(c1);
        assertEquals(c1, c2);
    }

}
