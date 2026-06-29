package pdk.chart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.Range;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.general.DatasetUtils;
import pdk.chart.event.ChartChangeEvent;
import pdk.chart.event.ChartChangeListener;
import pdk.chart.labels.CategoryToolTipGenerator;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.CategoryItemRenderer;
import pdk.chart.urls.CategoryURLGenerator;
import pdk.chart.urls.StandardCategoryURLGenerator;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for a bar chart.
 */
public class BarChartTest {

    /**
     * A chart.
     */
    private Chart chart;

    /**
     * Common test setup.
     */
    @BeforeEach
    public void setUp() {
        this.chart = createBarChart();
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
            fail("There should be no exception.");
        }
    }

    /**
     * Replaces the chart's dataset and then checks that the new dataset is OK.
     */
    @Test
    public void testReplaceDataset() {

        // create a dataset...
        Number[][] data = new Integer[][]{{-30, -20}, {-10, 10}, {20, 30}};

        CategoryDataset<String, String> newData
                = DatasetUtils.createCategoryDataset("S", "C", data);

        LocalListener l = new LocalListener();
        this.chart.addChangeListener(l);
        @SuppressWarnings("unchecked")
        CategoryPlot<String, String> plot = (CategoryPlot) this.chart.getPlot();
        plot.setDataset(newData);
        assertTrue(l.flag);
        ValueAxis axis = plot.getRangeAxis();
        Range range = axis.getRange();
        assertTrue(range.getLowerBound() <= -30,
                "Expecting the lower bound of the range to be around -30: " + range.getLowerBound());
        assertTrue(range.getUpperBound() >= 30,
                "Expecting the upper bound of the range to be around 30: " + range.getUpperBound());
    }

    /**
     * Check that setting a tool tip generator for a series does override the
     * default generator.
     */
    @Test
    public void testSetSeriesToolTipGenerator() {
        CategoryPlot<?, ?> plot = (CategoryPlot) this.chart.getPlot();
        CategoryItemRenderer renderer = plot.getRenderer();
        StandardCategoryToolTipGenerator tt
                = new StandardCategoryToolTipGenerator();
        renderer.setSeriesToolTipGenerator(0, tt);
        CategoryToolTipGenerator tt2 = renderer.getToolTipGenerator(0, 0);
        assertSame(tt2, tt);
    }

    /**
     * Check that setting a URL generator for a series does override the
     * default generator.
     */
    @Test
    public void testSetSeriesURLGenerator() {
        CategoryPlot<?, ?> plot = (CategoryPlot) this.chart.getPlot();
        CategoryItemRenderer renderer = plot.getRenderer();
        StandardCategoryURLGenerator url1
                = new StandardCategoryURLGenerator();
        renderer.setSeriesItemURLGenerator(0, url1);
        CategoryURLGenerator url2 = renderer.getItemURLGenerator(0, 0);
        assertSame(url2, url1);
    }

    /**
     * Create a bar chart with sample data in the range -3 to +3.
     *
     * @return The chart.
     */
    private static Chart createBarChart() {
        Number[][] data = new Integer[][]{{-3, -2}, {-1, 1}, {2, 3}};
        CategoryDataset<String, String> dataset
                = DatasetUtils.createCategoryDataset("S", "C", data);
        return ChartFactory.bar("Bar Chart", "Domain", "Range",
                dataset, PlotOrientation.HORIZONTAL, true, true, true);
    }

    /**
     * A chart change listener.
     */
    static class LocalListener implements ChartChangeListener {

        /**
         * Set to true after the listener is triggered.
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
