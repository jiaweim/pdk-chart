package pdk.chart.plot;

import org.junit.jupiter.api.Test;
import pdk.chart.api.LengthAdjustmentType;
import pdk.chart.api.RectangleAnchor;
import pdk.chart.api.RectangleInsets;
import pdk.chart.event.MarkerChangeEvent;
import pdk.chart.event.MarkerChangeListener;
import pdk.chart.text.TextAnchor;

import java.awt.*;
import java.util.Arrays;
import java.util.EventListener;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link Marker} class.
 */
public class MarkerTest implements MarkerChangeListener {

    MarkerChangeEvent lastEvent;

    /**
     * Some checks for the getPaint() and setPaint() methods.
     */
    @Test
    public void testGetSetPaint() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(Color.GRAY, m.getPaint());
        m.setPaint(Color.BLUE);
        assertEquals(Color.BLUE, m.getPaint());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        assertThrows(NullPointerException.class, () -> m.setPaint(null));
    }

    /**
     * Some checks for the getStroke() and setStroke() methods.
     */
    @Test
    public void testGetSetStroke() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(new BasicStroke(0.5f), m.getStroke());
        m.setStroke(new BasicStroke(1.1f));
        assertEquals(new BasicStroke(1.1f), m.getStroke());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        assertThrows(NullPointerException.class, () -> m.setStroke(null));
    }

    /**
     * Some checks for the getOutlinePaint() and setOutlinePaint() methods.
     */
    @Test
    public void testGetSetOutlinePaint() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(Color.GRAY, m.getOutlinePaint());
        m.setOutlinePaint(Color.YELLOW);
        assertEquals(Color.YELLOW, m.getOutlinePaint());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        m.setOutlinePaint(null);
        assertNull(m.getOutlinePaint());
    }

    /**
     * Some checks for the getOutlineStroke() and setOutlineStroke() methods.
     */
    @Test
    public void testGetSetOutlineStroke() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(new BasicStroke(0.5f), m.getOutlineStroke());
        m.setOutlineStroke(new BasicStroke(1.1f));
        assertEquals(new BasicStroke(1.1f), m.getOutlineStroke());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        m.setOutlineStroke(null);
        assertNull(m.getOutlineStroke());
    }

    private static final float EPSILON = 0.000000001f;

    /**
     * Some checks for the getAlpha() and setAlpha() methods.
     */
    @Test
    public void testGetSetAlpha() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(0.8f, m.getAlpha(), EPSILON);
        m.setAlpha(0.5f);
        assertEquals(0.5f, m.getAlpha(), EPSILON);
        assertEquals(m, this.lastEvent.getMarker());
    }

    /**
     * Some checks for the getLabel() and setLabel() methods.
     */
    @Test
    public void testGetSetLabel() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertNull(m.getLabel());
        m.setLabel("XYZ");
        assertEquals("XYZ", m.getLabel());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        m.setLabel(null);
        assertNull(m.getLabel());
    }

    /**
     * Some checks for the getLabelFont() and setLabelFont() methods.
     */
    @Test
    public void testGetSetLabelFont() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(new Font("SansSerif", Font.PLAIN, 9), m.getLabelFont());
        m.setLabelFont(new Font("SansSerif", Font.BOLD, 10));
        assertEquals(new Font("SansSerif", Font.BOLD, 10), m.getLabelFont());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        assertThrows(NullPointerException.class, () -> m.setLabelFont(null));
    }

    /**
     * Some checks for the getLabelPaint() and setLabelPaint() methods.
     */
    @Test
    public void testGetSetLabelPaint() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(Color.BLACK, m.getLabelPaint());
        m.setLabelPaint(Color.RED);
        assertEquals(Color.RED, m.getLabelPaint());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        assertThrows(NullPointerException.class, () -> m.setLabelPaint(null));
    }

    /**
     * Some checks for the getLabelAnchor() and setLabelAnchor() methods.
     */
    @Test
    public void testGetSetLabelAnchor() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(RectangleAnchor.TOP_LEFT, m.getLabelAnchor());
        m.setLabelAnchor(RectangleAnchor.TOP);
        assertEquals(RectangleAnchor.TOP, m.getLabelAnchor());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        assertThrows(NullPointerException.class, () -> m.setLabelAnchor(null));
    }

    /**
     * Some checks for the getLabelOffset() and setLabelOffset() methods.
     */
    @Test
    public void testGetSetLabelOffset() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(new RectangleInsets(3, 3, 3, 3), m.getLabelOffset());
        m.setLabelOffset(new RectangleInsets(1, 2, 3, 4));
        assertEquals(new RectangleInsets(1, 2, 3, 4), m.getLabelOffset());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        assertThrows(NullPointerException.class, () -> m.setLabelOffset(null));
    }

    /**
     * Some checks for the getLabelOffsetType() and setLabelOffsetType()
     * methods.
     */
    @Test
    public void testGetSetLabelOffsetType() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(LengthAdjustmentType.CONTRACT, m.getLabelOffsetType());
        m.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        assertEquals(LengthAdjustmentType.EXPAND, m.getLabelOffsetType());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        assertThrows(NullPointerException.class, () -> m.setLabelOffsetType(null));
    }

    /**
     * Some checks for the getLabelTextAnchor() and setLabelTextAnchor()
     * methods.
     */
    @Test
    public void testGetSetLabelTextAnchor() {
        // we use ValueMarker for the tests, because we need a concrete
        // subclass...
        ValueMarker m = new ValueMarker(1.1);
        m.addChangeListener(this);
        this.lastEvent = null;
        assertEquals(TextAnchor.CENTER, m.getLabelTextAnchor());
        m.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);
        assertEquals(TextAnchor.BASELINE_LEFT, m.getLabelTextAnchor());
        assertEquals(m, this.lastEvent.getMarker());

        // check null argument...
        assertThrows(NullPointerException.class, () -> m.setLabelTextAnchor(null));
    }

    /**
     * Checks that a CategoryPlot deregisters listeners when clearing markers.
     */
    @Test
    public void testListenersWithCategoryPlot() {
        CategoryPlot<String, String> plot = new CategoryPlot<>();
        CategoryMarker marker1 = new CategoryMarker("X");
        ValueMarker marker2 = new ValueMarker(1.0);
        plot.addDomainMarker(marker1);
        plot.addRangeMarker(marker2);
        EventListener[] listeners1 = marker1.getListeners(
                MarkerChangeListener.class);
        assertTrue(Arrays.asList(listeners1).contains(plot));
        EventListener[] listeners2 = marker1.getListeners(
                MarkerChangeListener.class);
        assertTrue(Arrays.asList(listeners2).contains(plot));
        plot.clearDomainMarkers();
        plot.clearRangeMarkers();
        listeners1 = marker1.getListeners(MarkerChangeListener.class);
        assertFalse(Arrays.asList(listeners1).contains(plot));
        listeners2 = marker1.getListeners(MarkerChangeListener.class);
        assertFalse(Arrays.asList(listeners2).contains(plot));
    }

    /**
     * Checks that an XYPlot deregisters listeners when clearing markers.
     */
    @Test
    public void testListenersWithXYPlot() {
        XYPlot<String> plot = new XYPlot<>();
        ValueMarker marker1 = new ValueMarker(1.0);
        ValueMarker marker2 = new ValueMarker(2.0);
        plot.addDomainMarker(marker1);
        plot.addRangeMarker(marker2);
        EventListener[] listeners1 = marker1.getListeners(
                MarkerChangeListener.class);
        assertTrue(Arrays.asList(listeners1).contains(plot));
        EventListener[] listeners2 = marker1.getListeners(
                MarkerChangeListener.class);
        assertTrue(Arrays.asList(listeners2).contains(plot));
        plot.clearDomainMarkers();
        plot.clearRangeMarkers();
        listeners1 = marker1.getListeners(MarkerChangeListener.class);
        assertFalse(Arrays.asList(listeners1).contains(plot));
        listeners2 = marker1.getListeners(MarkerChangeListener.class);
        assertFalse(Arrays.asList(listeners2).contains(plot));
    }

    /**
     * Records the last event.
     *
     * @param event the event.
     */
    @Override
    public void markerChanged(MarkerChangeEvent event) {
        this.lastEvent = event;
    }

}
