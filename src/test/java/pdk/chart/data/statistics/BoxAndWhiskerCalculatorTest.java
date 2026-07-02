package pdk.chart.data.statistics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link BoxAndWhiskerCalculator} class.
 */
public class BoxAndWhiskerCalculatorTest {

    /**
     * Some checks for the calculateBoxAndWhiskerStatistics() method.
     */
    @Test
    public void testCalculateBoxAndWhiskerStatistics() {

        // try null list
        assertThrows(NullPointerException.class, () -> BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(null));

        // try a list containing a single value
        List<Number> values = new ArrayList<>();
        values.add(1.1);
        BoxAndWhiskerItem item
                = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(values);
        assertEquals(1.1, item.getMean().doubleValue(), EPSILON);
        assertEquals(1.1, item.getMedian().doubleValue(), EPSILON);
        assertEquals(1.1, item.getQ1().doubleValue(), EPSILON);
        assertEquals(1.1, item.getQ3().doubleValue(), EPSILON);
    }

    private static final double EPSILON = 0.000000001;

    /**
     * Tests the Q1 calculation.
     */
    @Test
    public void testCalculateQ1() {

        // try null argument
        assertThrows(NullPointerException.class, () -> BoxAndWhiskerCalculator.calculateQ1(null));

        List<Double> values = new ArrayList<>();
        double q1 = BoxAndWhiskerCalculator.calculateQ1(values);
        assertTrue(Double.isNaN(q1));
        values.add(1.0);
        q1 = BoxAndWhiskerCalculator.calculateQ1(values);
        assertEquals(q1, 1.0, EPSILON);
        values.add(2.0);
        q1 = BoxAndWhiskerCalculator.calculateQ1(values);
        assertEquals(q1, 1.0, EPSILON);
        values.add(3.0);
        q1 = BoxAndWhiskerCalculator.calculateQ1(values);
        assertEquals(q1, 1.5, EPSILON);
        values.add(4.0);
        q1 = BoxAndWhiskerCalculator.calculateQ1(values);
        assertEquals(q1, 1.5, EPSILON);
    }

    /**
     * Tests the Q3 calculation.
     */
    @Test
    public void testCalculateQ3() {
        // try null argument
        assertThrows(NullPointerException.class, () -> BoxAndWhiskerCalculator.calculateQ3(null));

        List<Number> values = new ArrayList<>();
        double q3 = BoxAndWhiskerCalculator.calculateQ3(values);
        assertTrue(Double.isNaN(q3));
        values.add(1.0);
        q3 = BoxAndWhiskerCalculator.calculateQ3(values);
        assertEquals(q3, 1.0, EPSILON);
        values.add(2.0);
        q3 = BoxAndWhiskerCalculator.calculateQ3(values);
        assertEquals(q3, 2.0, EPSILON);
        values.add(3.0);
        q3 = BoxAndWhiskerCalculator.calculateQ3(values);
        assertEquals(q3, 2.5, EPSILON);
        values.add(4.0);
        q3 = BoxAndWhiskerCalculator.calculateQ3(values);
        assertEquals(q3, 3.5, EPSILON);
    }

    /**
     * The test case included in bug report 1593149.
     */
    @Test
    public void test1593149() {
        List<Double> list = new ArrayList<>(5);
        list.add(0, 1.0);
        list.add(1, 2.0);
        list.add(2, Double.NaN);
        list.add(3, 3.0);
        list.add(4, 4.0);
        BoxAndWhiskerItem item
                = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(list);
        assertEquals(1.0, item.getMinRegularValue().doubleValue(), EPSILON);
        assertEquals(4.0, item.getMaxRegularValue().doubleValue(), EPSILON);
    }
}
