package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.IntervalBarRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;

/**
 * An interval bar chart where each bar spans a range defined by two values
 * (start and end), using an {@link IntervalBarRenderer}.
 * <p>
 * The domain axis is a {@link CategoryAxis} and the range axis is a
 * {@link NumberAxis}.  This chart extends {@link CategoryBarChart} but
 * replaces the default renderer with an {@link IntervalBarRenderer} that
 * draws bars for interval data (for example, confidence intervals, planned
 * vs actual ranges, etc.).
 * <p>
 * Tool‑tips and URLs can be enabled via constructor flags.  Further
 * customization is available through the inherited methods of
 * {@link CategoryChart}.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 2:23 PM
 */
public class CategoryIntervalBarChart extends CategoryBarChart {

    /**
     * The interval bar renderer used by this chart.
     */
    private IntervalBarRenderer renderer2_;

    /**
     * Initializes the renderer to an {@link IntervalBarRenderer} and
     * updates the parent renderer references so that inherited methods
     * operate on the correct renderer.
     */
    @Override
    protected void initRenderer() {
        renderer2_ = new IntervalBarRenderer();
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
    }

    /**
     * Returns the interval bar renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public IntervalBarRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Full constructor – every option is exposed.
     *
     * @param dataset      the interval dataset ({@code null} permitted)
     * @param xAxisLabel   the label for the category axis ({@code null} permitted)
     * @param yAxisLabel   the label for the value axis ({@code null} permitted)
     * @param title        the chart title ({@code null} permitted)
     * @param orientation  the plot orientation ({@code null} not permitted)
     * @param createLegend {@code true} to include a legend
     * @param tooltips     {@code true} to enable standard tool‑tips
     * @param urls         {@code true} to generate URLs for data points
     */
    public CategoryIntervalBarChart(CategoryDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean createLegend, boolean tooltips, boolean urls) {
        super(title, createLegend);

        CategoryAxis xAxis_ = new CategoryAxis(xAxisLabel);
        NumberAxis yAxis_ = new NumberAxis(yAxisLabel);
        if (tooltips) {
            renderer2_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer2_.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }
        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setOrientation(orientation);
        plot_.setRenderer(renderer2_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates an interval bar chart with the given parameters; URLs are
     * disabled.
     *
     * @param dataset      the interval dataset ({@code null} permitted)
     * @param xAxisLabel   the label for the category axis ({@code null} permitted)
     * @param yAxisLabel   the label for the value axis ({@code null} permitted)
     * @param title        the chart title ({@code null} permitted)
     * @param orientation  the plot orientation ({@code null} not permitted)
     * @param createLegend {@code true} to include a legend
     * @param tooltips     {@code true} to enable standard tool‑tips
     */
    public CategoryIntervalBarChart(CategoryDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, PlotOrientation orientation, boolean createLegend, boolean tooltips) {
        this(dataset, xAxisLabel, yAxisLabel, title, orientation, createLegend, tooltips, false);
    }
}
