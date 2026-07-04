package pdk.chart.annotations;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.TestUtils;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.DefaultTableXYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.util.CloneUtils;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYLineAndShapeRenderer;
import pdk.chart.title.TextTitle;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link XYTitleAnnotation} class.
 */
public class XYTitleAnnotationTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        TextTitle t = new TextTitle("Title");
        XYTitleAnnotation a1 = new XYTitleAnnotation(1.0, 2.0, t);
        XYTitleAnnotation a2 = new XYTitleAnnotation(1.0, 2.0, t);
        assertEquals(a1, a2);

        a1 = new XYTitleAnnotation(1.1, 2.0, t);
        assertNotEquals(a1, a2);
        a2 = new XYTitleAnnotation(1.1, 2.0, t);
        assertEquals(a1, a2);

        a1 = new XYTitleAnnotation(1.1, 2.2, t);
        assertNotEquals(a1, a2);
        a2 = new XYTitleAnnotation(1.1, 2.2, t);
        assertEquals(a1, a2);

        TextTitle t2 = new TextTitle("Title 2");
        a1 = new XYTitleAnnotation(1.1, 2.2, t2);
        assertNotEquals(a1, a2);
        a2 = new XYTitleAnnotation(1.1, 2.2, t2);
        assertEquals(a1, a2);
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        TextTitle t = new TextTitle("Title");
        XYTitleAnnotation a1 = new XYTitleAnnotation(1.0, 2.0, t);
        XYTitleAnnotation a2 = new XYTitleAnnotation(1.0, 2.0, t);
        assertEquals(a1, a2);
        int h1 = a1.hashCode();
        int h2 = a2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        TextTitle t = new TextTitle("Title");
        XYTitleAnnotation a1 = new XYTitleAnnotation(1.0, 2.0, t);
        XYTitleAnnotation a2 = CloneUtils.clone(a1);
        assertNotSame(a1, a2);
        assertSame(a1.getClass(), a2.getClass());
        assertEquals(a1, a2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        TextTitle t = new TextTitle("Title");
        XYTitleAnnotation a1 = new XYTitleAnnotation(1.0, 2.0, t);
        XYTitleAnnotation a2 = TestUtils.serialised(a1);
        assertEquals(a1, a2);
    }

    /**
     * Draws the chart with a {@code null} info object to make sure that
     * no exceptions are thrown.
     */
    @Test
    public void testDrawWithNullInfo() {
        try {
            DefaultTableXYDataset<String> dataset = new DefaultTableXYDataset<>();

            XYSeries<String> s1 = new XYSeries<>("Series 1", true, false);
            s1.add(5.0, 5.0);
            s1.add(10.0, 15.5);
            s1.add(15.0, 9.5);
            s1.add(20.0, 7.5);
            dataset.addSeries(s1);

            XYSeries<String> s2 = new XYSeries<>("Series 2", true, false);
            s2.add(5.0, 5.0);
            s2.add(10.0, 15.5);
            s2.add(15.0, 9.5);
            s2.add(20.0, 3.5);
            dataset.addSeries(s2);
            XYPlot<String> plot = new XYPlot<>(dataset,
                    new NumberAxis("X"), new NumberAxis("Y"),
                    new XYLineAndShapeRenderer());
            plot.addAnnotation(new XYTitleAnnotation(5.0, 6.0,
                    new TextTitle("Hello World!")));
            Chart chart = new Chart(plot);
            /* BufferedImage image = */
            chart.createBufferedImage(300, 200, null);
        } catch (NullPointerException e) {
            fail("There should be no exception.");
        }
    }

}
