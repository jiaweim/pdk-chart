package pdk.chart;

import org.jspecify.annotations.NonNull;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.statistics.HistogramDataset;
import pdk.chart.data.xy.IntervalXYDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.StandardXYToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.plot.XYPlot;
import pdk.chart.renderer.xy.XYBarPainter;
import pdk.chart.renderer.xy.XYBarRenderer;
import pdk.chart.urls.StandardXYURLGenerator;
import pdk.chart.util.GradientPaintTransformer;

/**
 * A bar chart implementation that uses an {@link XYPlot} with an
 * {@link XYBarRenderer}.  Both the domain and range axes are
 * {@link ValueAxis} instances, whose concrete types are determined by
 * the supplied {@link AxisType} parameters.
 * <p>
 * <b>Important:</b> The renderer requires that the dataset implements
 * {@link IntervalXYDataset} (e.g. {@link HistogramDataset}) in order to
 * determine the width of each bar.  Passing a plain {@link XYDataset}
 * that does not provide interval information may result in runtime
 * errors or invisible bars.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 01 Aug 2026, 1:43 PM
 */
public class BarChart extends XYChart {

    protected XYBarRenderer renderer1_;

    /**
     * Constructor for subclass use.  Does not attach any dataset or axes;
     * these must be configured separately.
     *
     * @param title        the chart title ({@code null} permitted)
     * @param createLegend whether a legend should be displayed
     */
    protected BarChart(String title, boolean createLegend) {
        super(title, createLegend);
    }

    @Override
    protected void initRenderer() {
        renderer1_ = new XYBarRenderer();
        renderer1_.setShadowVisible(false);
        renderer0_ = renderer1_;
    }

    @Override
    public XYBarRenderer getRenderer() {
        return renderer1_;
    }

    /**
     * Fully parameterized constructor.
     *
     * @param dataset     the dataset (should implement {@link IntervalXYDataset}
     *                    for correct bar widths; {@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param xAxisType   the type of the domain axis ({@code null} not permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param yAxisType   the type of the range axis ({@code null} not permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tooltips
     * @param urls        {@code true} to generate URLs for data points
     */
    public BarChart(XYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        ValueAxis xAxis = xAxisType.createInstance(xAxisLabel);
        ValueAxis yAxis = yAxisType.createInstance(yAxisLabel);

        if (xAxis instanceof NumberAxis nAxis) {
            nAxis.setAutoRangeIncludesZero(false);
        }
        if (tooltips) {
            if (xAxisType == AxisType.DATE) {
                renderer1_.setDefaultToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
            } else {
                renderer1_.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
            }
        }
        if (urls) {
            renderer1_.setURLGenerator(new StandardXYURLGenerator());
        }
        plot_.setDomainAxis(xAxis);
        plot_.setRangeAxis(yAxis);
        plot_.setRenderer(renderer1_);
        plot_.setOrientation(orientation);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a bar chart with the given parameters and no URLs.
     *
     * @param dataset     the dataset (should implement {@link IntervalXYDataset};
     *                    {@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param xAxisType   the type of the domain axis
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param yAxisType   the type of the range axis ({@code null} not permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tooltips
     */
    public BarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, yAxisType, title,
                orientation, legend, tooltips, false);
    }


    /**
     * Creates a bar chart with the given parameters, using the default
     * Y‑axis type ({@link AxisType#NUMBER}) and no URLs.
     *
     * @param dataset     the dataset (should implement {@link IntervalXYDataset};
     *                    {@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param xAxisType   the type of the domain axis
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tooltips
     */
    public BarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, AxisType.NUMBER,
                title, orientation, legend, tooltips);
    }

    /**
     * Creates a bar chart with the given parameters, legend and tooltips
     * enabled, no URLs.
     *
     * @param dataset     the dataset (should implement {@link IntervalXYDataset};
     *                    {@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param xAxisType   the type of the domain axis
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param yAxisType   the type of the range axis ({@code null} not permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     */
    public BarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, AxisType yAxisType, String title,
            PlotOrientation orientation) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, yAxisType,
                title, orientation, true, true);
    }

    /**
     * Creates a bar chart with the given parameters, vertical orientation,
     * legend and tooltips enabled, no URLs.
     *
     * @param dataset    the dataset (should implement {@link IntervalXYDataset};
     *                   {@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param xAxisType  the type of the domain axis
     * @param yAxisLabel the range axis label ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     */
    public BarChart(IntervalXYDataset dataset,
            String xAxisLabel, AxisType xAxisType,
            String yAxisLabel, String title) {
        this(dataset, xAxisLabel, xAxisType, yAxisLabel, AxisType.NUMBER,
                title, PlotOrientation.VERTICAL);
    }

    /**
     * Convenience constructor that assumes both axes are
     * {@link NumberAxis} instances.
     *
     * @param dataset     the dataset (should implement {@link IntervalXYDataset};
     *                    {@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tooltips
     * @param urls        {@code true} to generate URLs
     */
    public BarChart(IntervalXYDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, orientation, legend, tooltips, urls);
    }

    /**
     * Convenience constructor that assumes both axes are
     * {@link NumberAxis} instances, no URLs.
     *
     * @param dataset     the dataset (should implement {@link IntervalXYDataset};
     *                    {@code null} permitted)
     * @param xAxisLabel  the domain axis label ({@code null} permitted)
     * @param yAxisLabel  the range axis label ({@code null} permitted)
     * @param title       the chart title ({@code null} permitted)
     * @param orientation the plot orientation ({@code null} not permitted)
     * @param legend      {@code true} to include a legend
     * @param tooltips    {@code true} to enable standard tooltips
     */
    public BarChart(IntervalXYDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a bar chart with vertical orientation, numeric axes,
     * legend, and tooltips enabled, no URLs.
     *
     * @param dataset    the dataset (should implement {@link IntervalXYDataset};
     *                   {@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param yAxisLabel the range axis label ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     */
    public BarChart(IntervalXYDataset dataset,
            String xAxisLabel, String yAxisLabel,
            String title) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, PlotOrientation.VERTICAL, true, false, false);
    }

    /**
     * Creates a bar chart specifically for a {@link HistogramDataset}.
     *
     * @param dataset    the histogram dataset ({@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param yAxisLabel the range axis label ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     */
    public BarChart(HistogramDataset dataset,
            String xAxisLabel, String yAxisLabel,
            String title) {
        this(dataset, xAxisLabel, AxisType.NUMBER, yAxisLabel, AxisType.NUMBER,
                title, PlotOrientation.VERTICAL, true, false, false);
    }

    /**
     * Creates a histogram bar chart with no title.
     *
     * @param dataset    the histogram dataset ({@code null} permitted)
     * @param xAxisLabel the domain axis label ({@code null} permitted)
     * @param yAxisLabel the range axis label ({@code null} permitted)
     */
    public BarChart(HistogramDataset dataset, String xAxisLabel, String yAxisLabel) {
        this(dataset, xAxisLabel, yAxisLabel, null);
    }

    /**
     * Sets whether bar outlines are drawn.
     *
     * @param draw {@code true} to draw outlines, {@code false} otherwise
     */
    public void setDrawBarOutline(boolean draw) {
        renderer1_.setDrawBarOutline(draw);
    }

    /**
     * Sets the bar painter, which controls the fill appearance of bars.
     *
     * @param painter the painter (not {@code null})
     */
    public void setBarPainter(@NonNull XYBarPainter painter) {
        renderer1_.setBarPainter(painter);
    }

    /**
     * Controls whether shadows are drawn under the bars.
     *
     * @param visible {@code true} to display shadows
     */
    public void setShadowVisible(boolean visible) {
        renderer1_.setShadowVisible(visible);
    }

    /**
     * Sets the margin between bars as a percentage of the axis range.
     *
     * @param margin the margin (typically between 0.0 and 1.0)
     */
    public void setBarMargin(double margin) {
        renderer1_.setMargin(margin);
    }

    /**
     * Sets the gradient paint transformer, allowing bars to be filled
     * with gradient paints that adapt to the bar’s orientation.
     *
     * @param transformer the transformer ({@code null} permitted)
     */
    public void setGradientPaintTransformer(
            GradientPaintTransformer transformer) {
        renderer1_.setGradientPaintTransformer(transformer);
    }
}
