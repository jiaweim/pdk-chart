package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.event.RendererChangeEvent;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.StatisticalBarRenderer;

import java.awt.*;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 2:35 PM
 */
public class CategoryStatisticsBarChart extends CategoryBarChart {

    private StatisticalBarRenderer renderer2_;

    /**
     * Create a bar chart with statistical error bar.
     *
     * @param dataset           {@link CategoryDataset}
     * @param title             chart title
     * @param categoryAxisTitle x axis name
     * @param valueAxisTitle    y axis name
     * @param orientation       {@link PlotOrientation}
     * @param legend            true if create legend
     * @param tooltips          true if generate tooltips.
     */
    public CategoryStatisticsBarChart(CategoryDataset dataset,
            String categoryAxisTitle, String valueAxisTitle, String title, PlotOrientation orientation,
            boolean legend, boolean tooltips) {
        super(title, legend);
        xAxis_ = new CategoryAxis(categoryAxisTitle);
        yAxis_ = new NumberAxis(valueAxisTitle);
        renderer2_ = new StatisticalBarRenderer();
        renderer0_ = renderer2_;
        renderer1_ = renderer2_;
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
     * Create a bar chart with statistical error bar.
     *
     * @param dataset           {@link CategoryDataset}
     * @param title             chart title
     * @param categoryAxisTitle x axis name
     * @param valueAxisTitle    y axis name
     */
    public CategoryStatisticsBarChart(CategoryDataset dataset,
            String categoryAxisTitle, String valueAxisTitle, String title) {
        this(dataset, categoryAxisTitle, valueAxisTitle, title,
                PlotOrientation.VERTICAL, true, true);
    }

    /**
     * Sets the paint used for the error indicators (if {@code null},
     * the item outline paint is used instead) and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paint the paint ({@code null} permitted).
     */
    public void setErrorIndicatorPaint(Paint paint) {
        renderer2_.setErrorIndicatorPaint(paint);
    }

}
