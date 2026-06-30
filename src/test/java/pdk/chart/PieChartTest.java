package pdk.chart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pdk.chart.data.general.DefaultPieDataset;
import pdk.chart.event.ChartChangeEvent;
import pdk.chart.event.ChartChangeListener;
import pdk.chart.plot.pie.PiePlot;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for a pie chart.
 */
public class PieChartTest {

    /**
     * A chart.
     */
    private Chart pieChart;

    /**
     * Common test setup.
     */
    @BeforeEach
    public void setUp() {
        this.pieChart = createPieChart();
    }

    /**
     * Using a regular pie chart, we replace the dataset with null.  Expect to
     * receive notification of a chart change event, and (of course) the
     * dataset should be null.
     */
    @Test
    public void testReplaceDatasetOnPieChart() {
        LocalListener l = new LocalListener();
        this.pieChart.addChangeListener(l);
        PiePlot plot = (PiePlot) this.pieChart.getPlot();
        plot.setDataset(null);
        assertTrue(l.flag);
        assertNull(plot.getDataset());
    }

    /**
     * Creates a pie chart.
     *
     * @return The pie chart.
     */
    private static Chart createPieChart() {
        DefaultPieDataset<String> data = new DefaultPieDataset<>();
        data.setValue("Java", 43.2);
        data.setValue("Visual Basic", 0.0);
        data.setValue("C/C++", 17.5);
        return JChart.pie("Pie Chart", data);
    }

    /**
     * A chart change listener.
     */
    static class LocalListener implements ChartChangeListener {

        /**
         * Set to true when the listener is triggered.
         */
        private boolean flag;

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
