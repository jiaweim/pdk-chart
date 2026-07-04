package pdk.chart.data.gantt;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.data.UnknownKeyException;
import pdk.chart.util.CloneUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link SlidingGanttCategoryDataset} class.
 */
public class SlidingGanttCategoryDatasetTest {

    /**
     * Some checks for the equals() method.
     */
    @Test
    public void testEquals() {
        TaskSeries<String> s1 = new TaskSeries<>("Series");
        s1.add(new Task("Task 1", new Date(0L), new Date(1L)));
        s1.add(new Task("Task 2", new Date(10L), new Date(11L)));
        s1.add(new Task("Task 3", new Date(20L), new Date(21L)));
        TaskSeriesCollection<String, String> u1 = new TaskSeriesCollection<>();
        u1.add(s1);
        SlidingGanttCategoryDataset d1 = new SlidingGanttCategoryDataset(
                u1, 0, 5);
        TaskSeries<String> s2 = new TaskSeries<>("Series");
        s2.add(new Task("Task 1", new Date(0L), new Date(1L)));
        s2.add(new Task("Task 2", new Date(10L), new Date(11L)));
        s2.add(new Task("Task 3", new Date(20L), new Date(21L)));
        TaskSeriesCollection<String, String> u2 = new TaskSeriesCollection<>();
        u2.add(s2);
        SlidingGanttCategoryDataset d2 = new SlidingGanttCategoryDataset(
                u2, 0, 5);
        assertEquals(d1, d2);

        d1.setFirstCategoryIndex(1);
        assertNotEquals(d1, d2);
        d2.setFirstCategoryIndex(1);
        assertEquals(d1, d2);

        d1.setMaximumCategoryCount(99);
        assertNotEquals(d1, d2);
        d2.setMaximumCategoryCount(99);
        assertEquals(d1, d2);

        s1.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertNotEquals(d1, d2);
        s2.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertEquals(d1, d2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        TaskSeries<String> s1 = new TaskSeries<>("Series");
        s1.add(new Task("Task 1", new Date(0L), new Date(1L)));
        TaskSeriesCollection<String, String> u1 = new TaskSeriesCollection<>();
        u1.add(s1);
        SlidingGanttCategoryDataset d1 = new SlidingGanttCategoryDataset(
                u1, 0, 5);
        SlidingGanttCategoryDataset d2 = CloneUtils.clone(d1);
        assertNotSame(d1, d2);
        assertSame(d1.getClass(), d2.getClass());
        assertEquals(d1, d2);

        // basic check for independence
        s1.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertNotEquals(d1, d2);
        TaskSeriesCollection<String, String> u2
                = (TaskSeriesCollection) d2.getUnderlyingDataset();
        TaskSeries<String> s2 = u2.getSeries("Series");
        s2.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertEquals(d1, d2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        TaskSeries<String> s1 = new TaskSeries<>("Series");
        s1.add(new Task("Task 1", new Date(0L), new Date(1L)));
        TaskSeriesCollection<String, String> u1 = new TaskSeriesCollection<>();
        u1.add(s1);
        SlidingGanttCategoryDataset d1 = new SlidingGanttCategoryDataset(
                u1, 0, 5);
        SlidingGanttCategoryDataset d2 = TestUtils.serialised(d1);
        assertEquals(d1, d2);

        // basic check for independence
        s1.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertNotEquals(d1, d2);
        TaskSeriesCollection<String, String> u2
                = (TaskSeriesCollection) d2.getUnderlyingDataset();
        TaskSeries<String> s2 = u2.getSeries("Series");
        s2.add(new Task("Task 2", new Date(10L), new Date(11L)));
        assertEquals(d1, d2);
    }

    /**
     * Check that methods taking row keys and column keys throw reasonable exceptions.
     */
    @Test
    public void testKeys() {
        TaskSeries<String> s1 = new TaskSeries<>("Series");
        s1.add(new Task("Task 1", new Date(0L), new Date(1L)));
        s1.add(new Task("Task 2", new Date(10L), new Date(11L)));
        s1.add(new Task("Task 3", new Date(20L), new Date(21L)));
        TaskSeriesCollection<String, String> u1 = new TaskSeriesCollection<>();
        u1.add(s1);
        SlidingGanttCategoryDataset d1 = new SlidingGanttCategoryDataset(
                u1, 0, 5);

        boolean invalidRowKey = false;
        try {
            d1.getValue("Bad Value", "Task 1"); // Should be "Series", not "Bad Value"
        } catch (UnknownKeyException e) {
            if (e.getMessage().contains("rowKey")) {
                invalidRowKey = true;
            }
        }
        assertTrue(invalidRowKey);

        boolean invalidColumnKey = false;
        try {
            d1.getValue("Series", "Task 4"); // only three tasks!
        } catch (UnknownKeyException e) {
            if (e.getMessage().contains("columnKey")) {
                invalidColumnKey = true;
            }
        }
        assertTrue(invalidColumnKey);
    }
}
