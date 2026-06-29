package pdk.chart.swing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pdk.chart.Chart;
import pdk.chart.ChartFactory;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.DefaultXYDataset;
import pdk.chart.event.ChartChangeEvent;
import pdk.chart.event.ChartChangeListener;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;

import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link ChartPanel} class.
 */
public class ChartPanelTest implements ChartChangeListener, ChartMouseListener {

    private final List<ChartChangeEvent> chartChangeEvents = new ArrayList<>();

    @BeforeEach
    public void checkForHeadlessEnvironment() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        org.junit.jupiter.api.Assumptions.assumeTrue(ge.isHeadlessInstance() == false);
    }

    /**
     * Receives a chart change event and stores it in a list for later
     * inspection.
     *
     * @param event the event.
     */
    @Override
    public void chartChanged(ChartChangeEvent event) {
        this.chartChangeEvents.add(event);
    }

    /**
     * Test that the constructor will accept a null chart.
     */
    @Test
    public void testConstructor1() {
        ChartPanel panel = new ChartPanel(null);
        assertNull(panel.getChart());
    }

    /**
     * Test that it is possible to set the panel's chart to null.
     */
    @Test
    public void testSetChart() {
        Chart chart = new Chart(new XYPlot<String>());
        ChartPanel panel = new ChartPanel(chart);
        panel.setChart(null);
        assertNull(panel.getChart());
    }

    /**
     * Check the behaviour of the getListeners() method.
     */
    @Test
    public void testGetListeners() {
        ChartPanel p = new ChartPanel(null);
        p.addChartMouseListener(this);
        EventListener[] listeners = p.getListeners(ChartMouseListener.class);
        assertEquals(1, listeners.length);
        assertEquals(this, listeners[0]);
        // try a listener type that isn't registered
        listeners = p.getListeners(CaretListener.class);
        assertEquals(0, listeners.length);
        p.removeChartMouseListener(this);
        listeners = p.getListeners(ChartMouseListener.class);
        assertEquals(0, listeners.length);
        // try a null argument
        assertThrows(NullPointerException.class, () -> p.getListeners(null));
    }

    /**
     * Ignores a mouse click event.
     *
     * @param event the event.
     */
    @Override
    public void chartMouseClicked(ChartMouseEvent event) {
        // ignore
    }

    /**
     * Ignores a mouse move event.
     *
     * @param event the event.
     */
    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
        // ignore
    }

    /**
     * Checks that a call to the zoom() method generates just one
     * ChartChangeEvent.
     */
    @Test
    public void test2502355_zoom() {
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel panel = new ChartPanel(chart);
        chart.addChangeListener(this);
        this.chartChangeEvents.clear();
        panel.zoom(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        assertEquals(1, this.chartChangeEvents.size());
    }

    /**
     * Checks that a call to the zoomInBoth() method generates just one
     * ChartChangeEvent.
     */
    @Test
    public void test2502355_zoomInBoth() {
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel panel = new ChartPanel(chart);
        chart.addChangeListener(this);
        this.chartChangeEvents.clear();
        panel.zoomInBoth(1.0, 2.0);
        assertEquals(1, this.chartChangeEvents.size());
    }

    /**
     * Checks that a call to the zoomOutBoth() method generates just one
     * ChartChangeEvent.
     */
    @Test
    public void test2502355_zoomOutBoth() {
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel panel = new ChartPanel(chart);
        chart.addChangeListener(this);
        this.chartChangeEvents.clear();
        panel.zoomOutBoth(1.0, 2.0);
        assertEquals(1, this.chartChangeEvents.size());
    }

    /**
     * Checks that a call to the restoreAutoBounds() method generates just one
     * ChartChangeEvent.
     */
    @Test
    public void test2502355_restoreAutoBounds() {
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel panel = new ChartPanel(chart);
        chart.addChangeListener(this);
        this.chartChangeEvents.clear();
        panel.restoreAutoBounds();
        assertEquals(1, this.chartChangeEvents.size());
    }

    /**
     * Checks that a call to the zoomInDomain() method, for a plot with more
     * than one domain axis, generates just one ChartChangeEvent.
     */
    @Test
    public void test2502355_zoomInDomain() {
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot<?> plot = (XYPlot<?>) chart.getPlot();
        plot.setDomainAxis(1, new NumberAxis("X2"));
        ChartPanel panel = new ChartPanel(chart);
        chart.addChangeListener(this);
        this.chartChangeEvents.clear();
        panel.zoomInDomain(1.0, 2.0);
        assertEquals(1, this.chartChangeEvents.size());
    }

    /**
     * Checks that a call to the zoomInRange() method, for a plot with more
     * than one range axis, generates just one ChartChangeEvent.
     */
    @Test
    public void test2502355_zoomInRange() {
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot<?> plot = (XYPlot<?>) chart.getPlot();
        plot.setRangeAxis(1, new NumberAxis("X2"));
        ChartPanel panel = new ChartPanel(chart);
        chart.addChangeListener(this);
        this.chartChangeEvents.clear();
        panel.zoomInRange(1.0, 2.0);
        assertEquals(1, this.chartChangeEvents.size());
    }

    /**
     * Checks that a call to the zoomOutDomain() method, for a plot with more
     * than one domain axis, generates just one ChartChangeEvent.
     */
    @Test
    public void test2502355_zoomOutDomain() {
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot<?> plot = (XYPlot<?>) chart.getPlot();
        plot.setDomainAxis(1, new NumberAxis("X2"));
        ChartPanel panel = new ChartPanel(chart);
        chart.addChangeListener(this);
        this.chartChangeEvents.clear();
        panel.zoomOutDomain(1.0, 2.0);
        assertEquals(1, this.chartChangeEvents.size());
    }

    /**
     * Checks that a call to the zoomOutRange() method, for a plot with more
     * than one range axis, generates just one ChartChangeEvent.
     */
    @Test
    public void test2502355_zoomOutRange() {
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot<?> plot = (XYPlot<?>) chart.getPlot();
        plot.setRangeAxis(1, new NumberAxis("X2"));
        ChartPanel panel = new ChartPanel(chart);
        chart.addChangeListener(this);
        this.chartChangeEvents.clear();
        panel.zoomOutRange(1.0, 2.0);
        assertEquals(1, this.chartChangeEvents.size());
    }

    /**
     * Checks that a call to the restoreAutoDomainBounds() method, for a plot
     * with more than one range axis, generates just one ChartChangeEvent.
     */
    @Test
    public void test2502355_restoreAutoDomainBounds() {
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot<?> plot = (XYPlot<?>) chart.getPlot();
        plot.setDomainAxis(1, new NumberAxis("X2"));
        ChartPanel panel = new ChartPanel(chart);
        chart.addChangeListener(this);
        this.chartChangeEvents.clear();
        panel.restoreAutoDomainBounds();
        assertEquals(1, this.chartChangeEvents.size());
    }

    /**
     * Checks that a call to the restoreAutoRangeBounds() method, for a plot
     * with more than one range axis, generates just one ChartChangeEvent.
     */
    @Test
    public void test2502355_restoreAutoRangeBounds() {
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot<?> plot = (XYPlot<?>) chart.getPlot();
        plot.setRangeAxis(1, new NumberAxis("X2"));
        ChartPanel panel = new ChartPanel(chart);
        chart.addChangeListener(this);
        this.chartChangeEvents.clear();
        panel.restoreAutoRangeBounds();
        assertEquals(1, this.chartChangeEvents.size());
    }

    /**
     * In version 1.0.13 there is a bug where enabling the mouse wheel handler
     * twice would in fact disable it.
     */
    @Test
    public void testSetMouseWheelEnabled() {
        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        assertTrue(panel.isMouseWheelEnabled());
        panel.setMouseWheelEnabled(true);
        assertTrue(panel.isMouseWheelEnabled());
        panel.setMouseWheelEnabled(false);
        assertFalse(panel.isMouseWheelEnabled());
    }

    /**
     * Test that transient zoom paint properties moved to the strategy still saved properly
     */
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        DefaultXYDataset<String> dataset = new DefaultXYDataset<>();
        Chart chart = ChartFactory.line("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel panel = new ChartPanel(chart);
        panel.setZoomFillPaint(Color.MAGENTA);
        panel.setZoomOutlinePaint(Color.CYAN);

        objectOutputStream.writeObject(panel);

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Object read = objectInputStream.readObject();
        assertTrue(read instanceof ChartPanel);
        ChartPanel readPanel = (ChartPanel) read;
        assertEquals(Color.MAGENTA, readPanel.getZoomFillPaint());
        assertEquals(Color.CYAN, readPanel.getZoomOutlinePaint());
    }
}

