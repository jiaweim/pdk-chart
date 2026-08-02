package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.model.Data;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.AreaRendererEndType;
import pdk.chart.renderer.category.AreaRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;

import java.util.Objects;

/**
 * Area chart with category domain axis.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 6:26 PM
 */
public class CategoryAreaChart extends CategoryChart {

    protected AreaRenderer renderer1_;
    protected CategoryAxis xAxis_;
    protected NumberAxis yAxis_;

    protected CategoryAreaChart(String title, boolean createLegend) {
        super(title, createLegend);
    }

    /**
     * Creates an area chart with default settings.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation ({@code null} not
     *                          permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     */
    public CategoryAreaChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        Objects.requireNonNull(orientation);

        xAxis_ = new CategoryAxis(categoryAxisLabel);
        xAxis_.setCategoryMargin(0.0);
        yAxis_ = new NumberAxis(valueAxisLabel);

        renderer1_ = new AreaRenderer();
        renderer1_.setEndType(AreaRendererEndType.LEVEL);
        renderer0_ = renderer1_;

        if (tooltips) {
            renderer1_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer1_.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setOrientation(orientation);
        plot_.setRenderer(renderer1_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates an area chart with default settings.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation ({@code null} not
     *                          permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     */
    public CategoryAreaChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates an area chart with default settings.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     */
    public CategoryAreaChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates an area chart with default settings.
     *
     * @param dataset the dataset for the chart ({@code null} permitted).
     */
    public CategoryAreaChart(CategoryDataset dataset) {
        this(dataset, null, null, null);
    }

    /**
     * Creates an area chart with default settings.
     *
     * @param categories categories
     * @param values     values.
     */
    public CategoryAreaChart(String[] categories, double[] values) {
        this(Data.createCategory("", categories, values),
                null, null, null, PlotOrientation.VERTICAL, false, true);
    }

    /**
     * Sets a token that controls how the renderer draws the end points.
     *
     * @param type the end type ({@code null} not permitted).
     */
    public void setEndType(AreaRendererEndType type) {
        renderer1_.setEndType(type);
    }

}
