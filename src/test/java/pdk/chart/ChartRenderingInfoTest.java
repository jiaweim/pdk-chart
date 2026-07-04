package pdk.chart;

import org.junit.jupiter.api.Test;
import pdk.chart.entity.ChartEntity;
import pdk.chart.entity.StandardEntityCollection;
import pdk.chart.util.CloneUtils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link ChartRenderingInfo} class.
 */
public class ChartRenderingInfoTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        ChartRenderingInfo i1 = new ChartRenderingInfo();
        ChartRenderingInfo i2 = new ChartRenderingInfo();
        assertEquals(i1, i2);

        i1.setChartArea(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        assertNotEquals(i1, i2);
        i2.setChartArea(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        assertEquals(i1, i2);

        i1.getPlotInfo().setDataArea(new Rectangle(1, 2, 3, 4));
        assertNotEquals(i1, i2);
        i2.getPlotInfo().setDataArea(new Rectangle(1, 2, 3, 4));
        assertEquals(i1, i2);

        StandardEntityCollection e1 = new StandardEntityCollection();
        e1.add(new ChartEntity(new Rectangle(1, 2, 3, 4)));
        i1.setEntityCollection(e1);
        assertNotEquals(i1, i2);
        StandardEntityCollection e2 = new StandardEntityCollection();
        e2.add(new ChartEntity(new Rectangle(1, 2, 3, 4)));
        i2.setEntityCollection(e2);
    }

    /**
     * Confirm that cloning works.
     *
     * @throws java.lang.CloneNotSupportedException
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        ChartRenderingInfo i1 = new ChartRenderingInfo();
        ChartRenderingInfo i2 = CloneUtils.clone(i1);

        assertNotSame(i1, i2);
        assertSame(i1.getClass(), i2.getClass());
        assertEquals(i1, i2);

        // check independence
        i1.getChartArea().setRect(4.0, 3.0, 2.0, 1.0);
        assertNotEquals(i1, i2);
        i2.getChartArea().setRect(4.0, 3.0, 2.0, 1.0);
        assertEquals(i1, i2);

        i1.getEntityCollection().add(new ChartEntity(new Rectangle(1, 2, 2,
                1)));
        assertNotEquals(i1, i2);
        i2.getEntityCollection().add(new ChartEntity(new Rectangle(1, 2, 2,
                1)));
        assertEquals(i1, i2);

    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        ChartRenderingInfo i1 = new ChartRenderingInfo();
        i1.setChartArea(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        ChartRenderingInfo i2 = TestUtils.serialised(i1);
        assertEquals(i1, i2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization2() {
        ChartRenderingInfo i1 = new ChartRenderingInfo();
        i1.getPlotInfo().setDataArea(new Rectangle2D.Double(1.0, 2.0, 3.0,
                4.0));
        ChartRenderingInfo i2 = TestUtils.serialised(i1);
        assertEquals(i1, i2);
        assertEquals(i2, i2.getPlotInfo().getOwner());
    }
}
