package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.OHLCDataset;
import pdk.chart.labels.HighLowItemLabelGenerator;
import pdk.chart.renderer.xy.HighLowRenderer;

/**
 * A high‑low‑open‑close (HLOC) chart, commonly used in financial data
 * visualization.
 * <p>
 * Each data point is drawn as a vertical line from the low to the high
 * price, with small ticks marking the open and close prices. The chart uses
 * a {@link HighLowRenderer} and the dataset must implement
 * {@link OHLCDataset} (which provides the required open, high, low, and
 * close values).
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 2:49 PM
 */
public class HighLowChart extends XYChart {

    private HighLowRenderer renderer1_;

    /**
     * Initializes the renderer to a {@link HighLowRenderer}.
     */
    @Override
    protected void initRenderer() {
        renderer1_ = new HighLowRenderer();
        renderer0_ = renderer1_;
    }

    /**
     * Returns the high‑low renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public HighLowRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Creates a new high‑low‑open‑close chart.
     *
     * @param dataset        the dataset containing OHLC data ({@code null}
     *                       permitted)
     * @param timeAxisLabel  the label for the time axis ({@code null}
     *                       permitted)
     * @param valueAxisLabel the label for the value axis ({@code null}
     *                       permitted)
     * @param title          the chart title ({@code null} permitted)
     * @param legend         {@code true} to include a legend
     * @param tooltips       {@code true} to enable tool‑tips (using a
     *                       {@link HighLowItemLabelGenerator})
     */
    public HighLowChart(OHLCDataset dataset, String timeAxisLabel, String valueAxisLabel,
            String title, boolean legend, boolean tooltips) {
        super(title, legend);
        DateAxis xAxis_ = new DateAxis(timeAxisLabel);
        NumberAxis yAxis_ = new NumberAxis(valueAxisLabel);

        if (tooltips) {
            renderer1_.setDefaultToolTipGenerator(new HighLowItemLabelGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);

        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a new high‑low‑open‑close chart with tool‑tips enabled.
     *
     * @param dataset        the dataset containing OHLC data ({@code null}
     *                       permitted)
     * @param timeAxisLabel  the label for the time axis ({@code null}
     *                       permitted)
     * @param valueAxisLabel the label for the value axis ({@code null}
     *                       permitted)
     * @param title          the chart title ({@code null} permitted)
     * @param legend         {@code true} to include a legend
     */
    public HighLowChart(OHLCDataset dataset, String timeAxisLabel, String valueAxisLabel,
            String title, boolean legend) {
        this(dataset, timeAxisLabel, valueAxisLabel, title, legend, true);
    }
}
