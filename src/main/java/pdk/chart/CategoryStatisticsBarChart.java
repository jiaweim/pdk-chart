package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.StatisticalBarRenderer;

import java.awt.*;

/**
 * A bar chart that displays statistical data with error indicators.
 * <p>
 * Uses a {@link StatisticalBarRenderer} to draw bars together with
 * error bars representing standard deviation or confidence intervals.
 * The domain axis is a {@link CategoryAxis} and the range axis is a
 * {@link NumberAxis}.
 * <p>
 * Tool‑tips are enabled by default and can be disabled via the
 * constructor.  URLs are not supported in this implementation.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 2:35 PM
 */
public class CategoryStatisticsBarChart extends CategoryBarChart {

    private StatisticalBarRenderer renderer2_;

    /**
     * Initializes the renderer to a {@link StatisticalBarRenderer}
     * and updates the parent renderer references.
     */
    @Override
    protected void initRenderer() {
        renderer2_ = new StatisticalBarRenderer();
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
    }

    /**
     * Returns the statistical bar renderer used by this chart.
     *
     * @return the renderer (never {@code null})
     */
    @Override
    public StatisticalBarRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Creates a statistics bar chart with error indicators.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisTitle the label for the category axis
     *                          ({@code null} permitted)
     * @param valueAxisTitle    the label for the value axis
     *                          ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param orientation       the plot orientation ({@code null} not
     *                          permitted)
     * @param legend            {@code true} to include a legend
     * @param tooltips          {@code true} to enable standard tool‑tips
     */
    public CategoryStatisticsBarChart(CategoryDataset dataset,
            String categoryAxisTitle, String valueAxisTitle, String title, PlotOrientation orientation,
            boolean legend, boolean tooltips) {
        super(title, legend);
        CategoryAxis xAxis_ = new CategoryAxis(categoryAxisTitle);
        NumberAxis yAxis_ = new NumberAxis(valueAxisTitle);

        if (tooltips) {
            renderer2_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer2_);
        plot_.setOrientation(orientation);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Convenience constructor with vertical orientation, legend and
     * tooltips enabled.
     *
     * @param dataset           the dataset ({@code null} permitted)
     * @param categoryAxisTitle the label for the category axis
     *                          ({@code null} permitted)
     * @param valueAxisTitle    the label for the value axis
     *                          ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     */
    public CategoryStatisticsBarChart(CategoryDataset dataset,
            String categoryAxisTitle, String valueAxisTitle, String title) {
        this(dataset, categoryAxisTitle, valueAxisTitle, title,
                PlotOrientation.VERTICAL, true, true);
    }

    /**
     * Sets the paint used for the error indicators (the lines showing
     * standard deviation or confidence intervals).
     * <p>
     * If set to {@code null}, the item outline paint is used instead.
     *
     * @param paint the paint ({@code null} permitted)
     */
    public void setErrorIndicatorPaint(Paint paint) {
        renderer2_.setErrorIndicatorPaint(paint);
    }

}
