package pdk.chart;

import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.WindDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.renderer.xy.WindItemRenderer;
import pdk.chart.urls.StandardXYURLGenerator;

/**
 * A wind chart that displays wind direction and speed over time.
 * <p>
 * Each data point is rendered as a wind barb (or arrow) using a
 * {@link WindItemRenderer}.  The domain axis is a {@link DateAxis}
 * representing time, and the range axis is a {@link NumberAxis}
 * with a fixed range of {@code [-12.0, 12.0]} (suitable for wind
 * speed in knots or m/s).
 * <p>
 * The dataset must be a {@link WindDataset} which provides wind
 * direction and speed for each time period.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 4:50 PM
 */
public class WindChart extends XYChart {

    private WindItemRenderer renderer1_;

    @Override
    protected void initRenderer() {
        renderer1_ = new WindItemRenderer();
        renderer0_ = renderer1_;
    }

    @Override
    public WindItemRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Creates a wind chart with the specified dataset, axis labels,
     * title, legend, tooltip and URL flags.
     *
     * @param dataset    the wind dataset ({@code null} permitted)
     * @param xAxisLabel the label for the time axis ({@code null} permitted)
     * @param yAxisLabel the label for the wind speed axis ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     * @param legend     {@code true} to include a legend
     * @param tooltips   {@code true} to enable standard tool‑tips
     * @param urls       {@code true} to generate URLs for data points
     */
    public WindChart(WindDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        DateAxis xAxis_ = new DateAxis(xAxisLabel);
        NumberAxis yAxis_ = new NumberAxis(yAxisLabel);
        yAxis_.setRange(-12.0, 12.0);

        if (tooltips) {
            renderer1_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }

        if (urls) {
            renderer1_.setURLGenerator(new StandardXYURLGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);

        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a wind chart with the given parameters; URLs are disabled.
     *
     * @param dataset    the wind dataset ({@code null} permitted)
     * @param xAxisLabel the label for the time axis ({@code null} permitted)
     * @param yAxisLabel the label for the wind speed axis ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     * @param legend     {@code true} to include a legend
     * @param tooltips   {@code true} to enable standard tool‑tips
     */
    public WindChart(WindDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title, legend, tooltips, false);
    }
}
