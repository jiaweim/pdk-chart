package pdk.chart.data.gantt;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.data.time.SimpleTimePeriod;
import pdk.chart.util.CloneUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link Task} class.
 */
public class TaskTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        Task t1 = new Task("T", new Date(1), new Date(2));
        Task t2 = new Task("T", new Date(1), new Date(2));
        assertEquals(t1, t2);
        assertEquals(t2, t1);

        t1.setDescription("X");
        assertNotEquals(t1, t2);
        t2.setDescription("X");
        assertEquals(t1, t2);

        t1.setDuration(new SimpleTimePeriod(new Date(2), new Date(3)));
        assertNotEquals(t1, t2);
        t2.setDuration(new SimpleTimePeriod(new Date(2), new Date(3)));
        assertEquals(t1, t2);

        t1.setPercentComplete(0.5);
        assertNotEquals(t1, t2);
        t2.setPercentComplete(0.5);
        assertEquals(t1, t2);

        t1.addSubtask(new Task("T", new Date(22), new Date(33)));
        assertNotEquals(t1, t2);
        t2.addSubtask(new Task("T", new Date(22), new Date(33)));
        assertEquals(t1, t2);


    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        Task t1 = new Task("T", new Date(1), new Date(2));
        Task t2 = CloneUtils.clone(t1);
        assertNotSame(t1, t2);
        assertSame(t1.getClass(), t2.getClass());
        assertEquals(t1, t2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        Task t1 = new Task("T", new Date(1), new Date(2));
        Task t2 = TestUtils.serialised(t1);
        assertEquals(t1, t2);
    }

    /**
     * Check the getSubTaskCount() method.
     */
    @Test
    public void testGetSubTaskCount() {
        Task t1 = new Task("T", new Date(100), new Date(200));
        assertEquals(0, t1.getSubtaskCount());
        t1.addSubtask(new Task("S1", new Date(100), new Date(110)));
        assertEquals(1, t1.getSubtaskCount());
        Task s2 = new Task("S2", new Date(111), new Date(120));
        t1.addSubtask(s2);
        assertEquals(2, t1.getSubtaskCount());
        t1.addSubtask(new Task("S3", new Date(121), new Date(130)));
        assertEquals(3, t1.getSubtaskCount());
        t1.removeSubtask(s2);
        assertEquals(2, t1.getSubtaskCount());
    }

}
