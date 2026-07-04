package pdk.chart.plot.compass;

import org.junit.jupiter.api.Test;
import pdk.chart.TestUtils;
import pdk.chart.data.general.DefaultValueDataset;
import pdk.chart.util.CloneUtils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link CompassPlot} class.
 */
class CompassPlotTest {

    /**
     * Test the equals() method.
     */
    @Test
    void testEquals() {
        CompassPlot plot1 = new CompassPlot();
        CompassPlot plot2 = new CompassPlot();
        assertEquals(plot1, plot2);

        // labelType...
        plot1.setLabelType(CompassPlot.VALUE_LABELS);
        assertNotEquals(plot1, plot2);
        plot2.setLabelType(CompassPlot.VALUE_LABELS);
        assertEquals(plot1, plot2);

        // labelFont
        plot1.setLabelFont(new Font("Serif", Font.PLAIN, 10));
        assertNotEquals(plot1, plot2);
        plot2.setLabelFont(new Font("Serif", Font.PLAIN, 10));
        assertEquals(plot1, plot2);

        // drawBorder
        plot1.setDrawBorder(true);
        assertNotEquals(plot1, plot2);
        plot2.setDrawBorder(true);
        assertEquals(plot1, plot2);

        // rosePaint
        plot1.setRosePaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.YELLOW));
        assertNotEquals(plot1, plot2);
        plot2.setRosePaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.YELLOW));
        assertEquals(plot1, plot2);

        // roseCenterPaint
        plot1.setRoseCenterPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.YELLOW));
        assertNotEquals(plot1, plot2);
        plot2.setRoseCenterPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.YELLOW));
        assertEquals(plot1, plot2);

        // roseHighlightPaint
        plot1.setRoseHighlightPaint(new GradientPaint(1.0f, 2.0f, Color.GREEN,
                3.0f, 4.0f, Color.YELLOW));
        assertNotEquals(plot1, plot2);
        plot2.setRoseHighlightPaint(new GradientPaint(1.0f, 2.0f, Color.GREEN,
                3.0f, 4.0f, Color.YELLOW));
        assertEquals(plot1, plot2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    void testSerialization() {
        CompassPlot p1 = new CompassPlot(null);
        p1.setRosePaint(new GradientPaint(1.0f, 2.0f, Color.RED, 3.0f, 4.0f,
                Color.BLUE));
        p1.setRoseCenterPaint(new GradientPaint(4.0f, 3.0f, Color.RED, 2.0f,
                1.0f, Color.GREEN));
        p1.setRoseHighlightPaint(new GradientPaint(4.0f, 3.0f, Color.RED, 2.0f,
                1.0f, Color.GREEN));
        CompassPlot p2 = TestUtils.serialised(p1);
        assertEquals(p1, p2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    void testCloning() throws CloneNotSupportedException {
        CompassPlot p1 = new CompassPlot(new DefaultValueDataset(15.0));
        CompassPlot p2 = CloneUtils.clone(p1);
        assertNotSame(p1, p2);
        assertSame(p1.getClass(), p2.getClass());
        assertEquals(p1, p2);
    }

    /**
     * Test faulty array bounds; CVE-2024-23077.
     */
    @Test
    void testArrayBounds() {
        CompassPlot p = new CompassPlot(new DefaultValueDataset(0));
        p.setSeriesNeedle(-1, new PointerNeedle());
    }

}
