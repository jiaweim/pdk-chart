package pdk.chart.data.gantt;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.util.CloneUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link TaskSeries} class.
 */
public class TaskSeriesTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        TaskSeries<String> s1 = new TaskSeries<>("S");
        s1.add(new Task("T1", new Date(1), new Date(2)));
        s1.add(new Task("T2", new Date(11), new Date(22)));
        TaskSeries<String> s2 = new TaskSeries<>("S");
        s2.add(new Task("T1", new Date(1), new Date(2)));
        s2.add(new Task("T2", new Date(11), new Date(22)));
        assertEquals(s1, s2);
        assertEquals(s2, s1);

        s1.add(new Task("T3", new Date(22), new Date(33)));
        assertNotEquals(s1, s2);
        s2.add(new Task("T3", new Date(22), new Date(33)));
        assertEquals(s1, s2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        TaskSeries<String> s1 = new TaskSeries<>("S");
        s1.add(new Task("T1", new Date(1), new Date(2)));
        s1.add(new Task("T2", new Date(11), new Date(22)));
        TaskSeries<String> s2 = CloneUtils.clone(s1);
        assertNotSame(s1, s2);
        assertSame(s1.getClass(), s2.getClass());
        assertEquals(s1, s2);

        // basic check for independence
        s1.add(new Task("T3", new Date(22), new Date(33)));
        assertNotEquals(s1, s2);
        s2.add(new Task("T3", new Date(22), new Date(33)));
        assertEquals(s1, s2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        TaskSeries<String> s1 = new TaskSeries<>("S");
        s1.add(new Task("T1", new Date(1), new Date(2)));
        s1.add(new Task("T2", new Date(11), new Date(22)));
        TaskSeries<String> s2 = TestUtils.serialised(s1);
        assertEquals(s1, s2);
    }

    /**
     * Some checks for the getTask() method.
     */
    @Test
    public void testGetTask() {
        TaskSeries<String> s1 = new TaskSeries<>("S");
        s1.add(new Task("T1", new Date(1), new Date(2)));
        s1.add(new Task("T2", new Date(11), new Date(22)));
        Task t1 = s1.get("T1");
        assertEquals(t1, new Task("T1", new Date(1), new Date(2)));
        Task t2 = s1.get("T2");
        assertEquals(t2, new Task("T2", new Date(11), new Date(22)));
        Task t3 = s1.get("T3");
        assertNull(t3);
    }

}
