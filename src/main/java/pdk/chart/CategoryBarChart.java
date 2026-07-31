package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.labels.ItemLabelAnchor;
import pdk.chart.labels.ItemLabelPosition;
import pdk.chart.labels.StandardCategoryToolTipGenerator;
import pdk.chart.plot.CategoryPlot;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.BarRenderer;
import pdk.chart.text.TextAnchor;
import pdk.chart.urls.StandardCategoryURLGenerator;
import pdk.chart.util.Args;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 31 Jul 2026, 7:16 PM
 */
public class CategoryBarChart extends CategoryChart {

    private CategoryAxis xAxis_;
    private NumberAxis yAxis_;
    private BarRenderer renderer_;

    public CategoryBarChart(String title, PlotOrientation orientation,
            boolean createLegend) {
        super(title, DEFAULT_TITLE_FONT, createLegend);
        Args.nullNotPermitted(orientation, "orientation");

        xAxis_ = new CategoryAxis();
        yAxis_ = new NumberAxis();
        renderer_ = new BarRenderer();
        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
            renderer_.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
            renderer_.setDefaultNegativeItemLabelPosition(position2);
        } else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
            renderer_.setDefaultPositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
            renderer_.setDefaultNegativeItemLabelPosition(position2);
        }

        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setRenderer(renderer_);
        JChartUtils.applyCurrentTheme(this);
    }

    /**
     * Creates a bar chart.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        this(title, orientation, legend);

        xAxis_.setLabel(categoryAxisLabel);
        yAxis_.setLabel(valueAxisLabel);

        if (tooltips) {
            renderer_.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }
        if (urls) {
            renderer_.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }

        plot_.setDataset(dataset);
        plot_.setOrientation(orientation);
    }

    /**
     * Creates a bar chart.  The chart object returned by this method uses a
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link NumberAxis} as the range axis, and a
     * {@link BarRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a bar chart.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, true, true);
    }

    /**
     * Creates a bar chart.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title) {
        this(dataset, categoryAxisLabel, valueAxisLabel, title, PlotOrientation.VERTICAL);
    }

    /**
     * Creates a bar chart.
     *
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     */
    public CategoryBarChart(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel) {
        this(dataset, categoryAxisLabel, valueAxisLabel, null);
    }

    /**
     * Creates a bar chart.
     *
     * @param dataset the dataset for the chart ({@code null} permitted).
     */
    public CategoryBarChart(CategoryDataset dataset, PlotOrientation orientation) {
        this(dataset, null, null, null, orientation);
    }

    /**
     * Creates a bar chart.
     *
     * @param dataset the dataset for the chart ({@code null} permitted).
     */
    public CategoryBarChart(CategoryDataset dataset) {
        this(dataset, PlotOrientation.VERTICAL);
    }

    /**
     * Creates a bar chart.
     *
     * @param categories  category values.
     * @param values      values
     * @param orientation {@link PlotOrientation}
     */
    public CategoryBarChart(String[] categories, double[] values, PlotOrientation orientation) {
        this(Data.createCategory("", categories, values), orientation);
    }

    /**
     * Creates a bar chart.
     *
     * @param categories category values.
     * @param values     values
     */
    public CategoryBarChart(String[] categories, double[] values) {
        this(Data.createCategory("", categories, values), null, null, null,
                PlotOrientation.VERTICAL, false, false);
    }
}
