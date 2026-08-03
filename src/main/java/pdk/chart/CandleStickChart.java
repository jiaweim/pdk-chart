package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.OHLCDataset;
import pdk.chart.renderer.xy.CandlestickRenderer;

/**
 * A candlestick chart for financial data.
 * <p>
 * Displays open, high, low, and close values using a
 * {@link CandlestickRenderer}.
 * <p>
 * The domain axis is a {@link DateAxis} representing the time periods,
 * while the range axis is a {@link NumberAxis} representing price.
 * The dataset must implement {@link OHLCDataset}, which provides the
 * four price components for each time period.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 1:28 PM
 */
public class CandleStickChart extends XYChart {

    private CandlestickRenderer renderer1_;

    /**
     * Initializes the renderer to a {@link CandlestickRenderer}.
     */
    @Override
    protected void initRenderer() {
        renderer1_ = new CandlestickRenderer();
        renderer0_ = renderer1_;
    }

    /**
     * Returns the candlestick renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public CandlestickRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Creates a new candlestick chart.
     *
     * @param dataset        the dataset containing OHLC data (must not be
     *                       {@code null})
     * @param timeAxisLabel  the label for the time axis ({@code null}
     *                       permitted)
     * @param valueAxisLabel the label for the price/value axis
     *                       ({@code null} permitted)
     * @param title          the chart title ({@code null} permitted)
     * @param legend         {@code true} to include a legend
     */
    public CandleStickChart(OHLCDataset dataset, String timeAxisLabel, String valueAxisLabel,
            String title, boolean legend) {
        super(title, legend);

        DateAxis xAxis = new DateAxis(timeAxisLabel);
        NumberAxis yAxis = new NumberAxis(valueAxisLabel);

        plot_.setDomainAxis(xAxis);
        plot_.setRangeAxis(yAxis);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);

        JChart.applyCurrentTheme(this);
    }
}
