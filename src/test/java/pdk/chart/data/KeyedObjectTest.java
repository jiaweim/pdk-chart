package pdk.chart.data;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.util.CloneUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link KeyedObject} class.
 */
public class KeyedObjectTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {

        KeyedObject<String> ko1 = new KeyedObject<>("Test", "Object");
        KeyedObject<String> ko2 = new KeyedObject<>("Test", "Object");
        assertEquals(ko1, ko2);
        assertEquals(ko2, ko1);

        ko1 = new KeyedObject<>("Test 1", "Object");
        ko2 = new KeyedObject<>("Test 2", "Object");
        assertNotEquals(ko1, ko2);

        ko1 = new KeyedObject<>("Test", "Object 1");
        ko2 = new KeyedObject<>("Test", "Object 2");
        assertNotEquals(ko1, ko2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        KeyedObject<String> ko1 = new KeyedObject<>("Test", "Object");
        KeyedObject<String> ko2 = CloneUtils.clone(ko1);
        assertNotSame(ko1, ko2);
        assertSame(ko1.getClass(), ko2.getClass());
        assertEquals(ko1, ko2);
    }

    /**
     * Confirm special features of cloning.
     */
    @Test
    public void testCloning2() throws CloneNotSupportedException {
        // case 1 - object is mutable but not PublicCloneable
        Object obj1 = new ArrayList<String>();
        KeyedObject<String> ko1 = new KeyedObject<>("Test", obj1);
        KeyedObject<String> ko2 = CloneUtils.clone(ko1);
        assertNotSame(ko1, ko2);
        assertSame(ko1.getClass(), ko2.getClass());
        assertEquals(ko1, ko2);

        // the clone contains a reference to the original object
        assertSame(ko2.getObject(), obj1);

        // CASE 2 - object is mutable AND PublicCloneable
        obj1 = new DefaultPieDataset<String>();
        ko1 = new KeyedObject<>("Test", obj1);
        ko2 = CloneUtils.clone(ko1);
        assertNotSame(ko1, ko2);
        assertSame(ko1.getClass(), ko2.getClass());
        assertEquals(ko1, ko2);

        // the clone contains a reference to a CLONE of the original object
        assertNotSame(ko2.getObject(), obj1);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        KeyedObject<String> ko1 = new KeyedObject<>("Test", "Object");
        KeyedObject<String> ko2 = TestUtils.serialised(ko1);
        assertEquals(ko1, ko2);
    }

}
