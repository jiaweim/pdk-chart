package pdk.chart;

import pdk.chart.axis.NumberAxis;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.labels.XYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.xy.StackedXYAreaRenderer2;
import pdk.chart.urls.StandardXYURLGenerator;

import java.util.Objects;

/**
 * A stacked area chart backed by a {@link TableXYDataset}.
 * <p>
 * Each series is drawn as a filled area, stacked on top of the previous
 * series, using a {@link StackedXYAreaRenderer2} with outlines enabled.
 * The domain and range axes are {@link NumberAxis} instances; the domain
 * axis margins are set to zero so that the area fills the entire width of
 * the data.
 * <p>
 * <b>Note:</b> The renderer requires a {@link TableXYDataset} because it
 * needs access to the series items in table format to compute the stacking.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 3:54 PM
 */
public class StackedAreaChart extends XYChart {

    private NumberAxis xAxis_;
    private NumberAxis yAxis_;
    private StackedXYAreaRenderer2 renderer1_;

    @Override
    protected void initRenderer() {
        renderer1_ = new StackedXYAreaRenderer2();
        renderer1_.setDrawOutline(true);
        renderer0_ = renderer1_;
    }

    @Override
    public StackedXYAreaRenderer2 getRenderer() {
        return renderer1_;
    }

    /**
     * Full constructor.
     *
     * @param dataset     the dataset (must be a {@link TableXYDataset};
     *                    {@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     * @param urls        {@code true} to generate URLs for data points
     */
    public StackedAreaChart(TableXYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);
        xAxis_ = new NumberAxis(xAxisLabel);
        xAxis_.setAutoRangeIncludesZero(false);
        xAxis_.setLowerMargin(0.0);
        xAxis_.setUpperMargin(0.0);
        yAxis_ = new NumberAxis(yAxisLabel);

        if (tooltips) {
            XYToolTipGenerator toolTipGenerator = new StandardXYToolTipGenerator();
            renderer1_.setDefaultToolTipGenerator(toolTipGenerator);
        }

        if (urls) {
            renderer1_.setURLGenerator(new StandardXYURLGenerator());
        }

        plot_.setOrientation(orientation);
        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a stacked area chart with the given parameters; URLs are
     * disabled.
     *
     * @param dataset     the dataset ({@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tool‑tips
     */
    public StackedAreaChart(TableXYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Convenience constructor with vertical orientation, legend and
     * tooltips enabled, and no URLs.
     *
     * @param dataset    the dataset ({@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param yAxisLabel the range axis label ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     */
    public StackedAreaChart(TableXYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        this(dataset, xAxisLabel, yAxisLabel, title,
                PlotOrientation.VERTICAL, true, true, false);
    }


    /**
     * Sets whether the x‑coordinates (in Java2D space) are rounded to
     * integer values.
     * <p>
     * This may improve rendering performance or crispness in some cases.
     *
     * @param round {@code true} to round x‑coordinates
     */
    public void setRoundXCoordinates(boolean round) {
        renderer1_.setRoundXCoordinates(round);
    }

}
