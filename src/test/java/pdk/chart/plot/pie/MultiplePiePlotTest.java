package pdk.chart.plot.pie;

import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.JChart;
import pdk.chart.TestUtils;
import pdk.chart.api.TableOrder;
import pdk.chart.data.category.DefaultCategoryDataset;
import pdk.chart.event.PlotChangeEvent;
import pdk.chart.event.PlotChangeListener;
import pdk.chart.internal.CloneUtils;
import pdk.chart.legend.LegendItem;
import pdk.chart.legend.LegendItemCollection;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some tests for the {@link MultiplePiePlot} class.
 */
public class MultiplePiePlotTest implements PlotChangeListener {

    /**
     * The last event received.
     */
    PlotChangeEvent lastEvent;

    /**
     * Receives a plot change event and records it.  Some tests will use this
     * to check that events have been generated (or not) when required.
     *
     * @param event the event.
     */
    @Override
    public void plotChanged(PlotChangeEvent event) {
        this.lastEvent = event;
    }

    /**
     * Some checks for the constructors.
     */
    @Test
    public void testConstructor() {
        MultiplePiePlot plot = new MultiplePiePlot();
        assertNull(plot.getDataset());

        // the following checks that the plot registers itself as a listener
        // with the dataset passed to the constructor - see patch 1943021
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        plot = new MultiplePiePlot(dataset);
        assertTrue(dataset.hasListener(plot));
    }

    /**
     * Check that the equals() method distinguishes the required fields.
     */
    @Test
    public void testEquals() {
        MultiplePiePlot p1 = new MultiplePiePlot();
        MultiplePiePlot p2 = new MultiplePiePlot();
        assertEquals(p1, p2);
        assertEquals(p2, p1);

        p1.setDataExtractOrder(TableOrder.BY_ROW);
        assertNotEquals(p1, p2);
        p2.setDataExtractOrder(TableOrder.BY_ROW);
        assertEquals(p1, p2);

        p1.setLimit(1.23);
        assertNotEquals(p1, p2);
        p2.setLimit(1.23);
        assertEquals(p1, p2);

        p1.setAggregatedItemsKey("Aggregated Items");
        assertNotEquals(p1, p2);
        p2.setAggregatedItemsKey("Aggregated Items");
        assertEquals(p1, p2);

        p1.setAggregatedItemsPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.YELLOW));
        assertNotEquals(p1, p2);
        p2.setAggregatedItemsPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.YELLOW));
        assertEquals(p1, p2);

        p1.setPieChart(JChart.pie(null, "Title", true, true,
                true));
        assertNotEquals(p1, p2);
        p2.setPieChart(JChart.pie(null, "Title", true, true,
                true));
        assertEquals(p1, p2);

        p1.setLegendItemShape(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        assertNotEquals(p1, p2);
        p2.setLegendItemShape(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        assertEquals(p1, p2);
    }

    /**
     * Some basic checks for the clone() method.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        MultiplePiePlot p1 = new MultiplePiePlot();
        Rectangle2D rect = new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0);
        p1.setLegendItemShape(rect);
        MultiplePiePlot p2 = CloneUtils.clone(p1);
        assertNotSame(p1, p2);
        assertSame(p1.getClass(), p2.getClass());
        assertEquals(p1, p2);

        // check independence
        rect.setRect(2.0, 3.0, 4.0, 5.0);
        assertNotEquals(p1, p2);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        MultiplePiePlot p1 = new MultiplePiePlot(null);
        p1.setAggregatedItemsPaint(new GradientPaint(1.0f, 2.0f, Color.YELLOW,
                3.0f, 4.0f, Color.RED));
        MultiplePiePlot p2 = TestUtils.serialised(p1);
        assertEquals(p1, p2);
    }

    /**
     * Fetches the legend items and checks the values.
     */
    @Test
    public void testGetLegendItems() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(35.0, "S1", "C1");
        dataset.addValue(45.0, "S1", "C2");
        dataset.addValue(55.0, "S2", "C1");
        dataset.addValue(15.0, "S2", "C2");
        MultiplePiePlot plot = new MultiplePiePlot(dataset);
        Chart chart = new Chart(plot);
        LegendItemCollection legendItems = plot.getLegendItems();
        assertEquals(2, legendItems.getItemCount());
        LegendItem item1 = legendItems.get(0);
        assertEquals("S1", item1.getLabel());
        assertEquals("S1", item1.getSeriesKey());
        assertEquals(0, item1.getSeriesIndex());
        assertEquals(dataset, item1.getDataset());
        assertEquals(0, item1.getDatasetIndex());

        LegendItem item2 = legendItems.get(1);
        assertEquals("S2", item2.getLabel());
        assertEquals("S2", item2.getSeriesKey());
        assertEquals(1, item2.getSeriesIndex());
        assertEquals(dataset, item2.getDataset());
        assertEquals(0, item2.getDatasetIndex());
    }

}
