package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.AreaRendererEndType;
import pdk.chart.renderer.category.AreaRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;
import pdk.chart.util.Args;

import java.awt.*;

/**
 * Area chart with category domain axis.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 6:26 PM
 */
public class CategoryAreaChart extends CategoryChart {

    private final AreaRenderer renderer_;
    private final CategoryAxis domainAxis_;
    private final NumberAxis rangeAxis_;

    public CategoryAreaChart(String title, Font titleFont, boolean createLegend) {
        super(title, titleFont, createLegend);
        renderer_ = new AreaRenderer();
        renderer_.setEndType(AreaRendererEndType.LEVEL);
        setDefaultRenderer(renderer_);

        domainAxis_ = new CategoryAxis();
        domainAxis_.setCategoryMargin(0.0);

        rangeAxis_ = new NumberAxis();

        plot_.setDomainAxis(domainAxis_);
        plot_.setRangeAxis(rangeAxis_);
        plot_.setRenderer(renderer_);
        JChartUtils.applyCurrentTheme(this);
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
        this(title, DEFAULT_TITLE_FONT, legend);
        Args.nullNotPermitted(orientation, "orientation");

        if (tooltips) {
            renderer_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer_.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }
        domainAxis_.setLabel(categoryAxisLabel);
        rangeAxis_.setLabel(valueAxisLabel);
        plot_.setOrientation(orientation);
        plot_.setDataset(dataset);
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
}
