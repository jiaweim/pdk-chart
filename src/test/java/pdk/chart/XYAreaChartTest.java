package pdk.chart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.Range;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.data.xy.XYSeries;
import pdk.chart.data.xy.XYSeriesCollection;
import pdk.chart.event.ChartChangeEvent;
import pdk.chart.event.ChartChangeListener;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.labels.XYToolTipGenerator;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYItemRenderer;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some tests for an XY area chart.
 */
public class XYAreaChartTest {

    /**
     * A chart.
     */
    private Chart chart;

    /**
     * Common test setup.
     */
    @BeforeEach
    public void setUp() {
        this.chart = createChart();
    }

    /**
     * Draws the chart with a null info object to make sure that no exceptions
     * are thrown (a problem that was occurring at one point).
     */
    @Test
    public void testDrawWithNullInfo() {
        try {
            BufferedImage image = new BufferedImage(200, 100,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            this.chart.draw(g2, new Rectangle2D.Double(0, 0, 200, 100), null,
                    null);
            g2.dispose();
        } catch (Exception e) {
            fail("No exception should be triggered.");
        }
    }

    /**
     * Replaces the dataset and checks that it has changed as expected.
     */
    @Test
    public void testReplaceDataset() {

        // create a dataset...
        XYSeries<String> series1 = new XYSeries<>("Series 1");
        series1.add(10.0, 10.0);
        series1.add(20.0, 20.0);
        series1.add(30.0, 30.0);
        XYDataset<String> dataset = new XYSeriesCollection<>(series1);

        LocalListener l = new LocalListener();
        this.chart.addChangeListener(l);
        XYPlot<String> plot = (XYPlot<String>) this.chart.getPlot();
        plot.setDataset(dataset);
        assertTrue(l.flag);
        ValueAxis axis = plot.getRangeAxis();
        Range range = axis.getRange();
        assertTrue(range.getLowerBound() <= 10,
                "Expecting the lower bound of the range to be around 10: " + range.getLowerBound());
        assertTrue(range.getUpperBound() >= 30,
                "Expecting the upper bound of the range to be around 30: " + range.getUpperBound());
    }

    /**
     * Check that setting a tool tip generator for a series does override the
     * default generator.
     */
    @Test
    public void testSetSeriesToolTipGenerator() {
        XYPlot<?> plot = (XYPlot<?>) this.chart.getPlot();
        XYItemRenderer renderer = plot.getRenderer();
        StandardXYToolTipGenerator tt = new StandardXYToolTipGenerator();
        renderer.setSeriesToolTipGenerator(0, tt);
        XYToolTipGenerator tt2 = renderer.getToolTipGenerator(0, 0);
        assertSame(tt2, tt);
    }

    /**
     * Create a chart for testing.
     *
     * @return The chart.
     */
    private static Chart createChart() {
        XYSeries<String> series1 = new XYSeries<>("Series 1");
        series1.add(1.0, 1.0);
        series1.add(2.0, 2.0);
        series1.add(3.0, 3.0);
        XYDataset<String> dataset = new XYSeriesCollection<>(series1);
        return JChart.areaXY(dataset, "Domain",
                "Range", "Area Chart");
    }

    /**
     * A chart change listener.
     *
     */
    static class LocalListener implements ChartChangeListener {

        /**
         * A flag.
         */
        private boolean flag = false;

        /**
         * Event handler.
         *
         * @param event the event.
         */
        @Override
        public void chartChanged(ChartChangeEvent event) {
            this.flag = true;
        }

    }

}
