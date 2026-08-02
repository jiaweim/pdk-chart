package pdk.chart;

import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.DateAxis;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.labels.IntervalCategoryToolTipGenerator;
import pdk.chart.plot.PlotOrientation;
import pdk.chart.renderer.category.GanttRenderer;
import pdk.chart.urls.StandardCategoryURLGenerator;

import java.text.DateFormat;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 1:32 PM
 */
public class GanttChart extends CategoryBarChart {

    private final CategoryAxis xAxis_;
    private final DateAxis yAxis_;
    private final GanttRenderer renderer2_;

    /**
     * Creates a Gantt chart using the supplied attributes plus default values
     * where required.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel the label for the category axis
     *                   ({@code null} permitted).
     * @param yAxisLabel the label for the date axis
     *                   ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @param legend     a flag specifying whether a legend is required.
     * @param tooltips   configure chart to generate tool tips?
     * @param urls       configure chart to generate URLs?
     */
    public GanttChart(IntervalCategoryDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        xAxis_ = new CategoryAxis(xAxisLabel);
        yAxis_ = new DateAxis(yAxisLabel);
        renderer2_ = new GanttRenderer();
        renderer1_ = renderer2_;
        renderer0_ = renderer2_;

        if (tooltips) {
            renderer2_.setDefaultToolTipGenerator(
                    new IntervalCategoryToolTipGenerator("{3} - {4}", DateFormat.getDateInstance()));
        }
        if (urls) {
            renderer2_.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }
        plot_.setDomainAxis(xAxis_);
        plot_.setRangeAxis(yAxis_);
        plot_.setOrientation(PlotOrientation.HORIZONTAL);
        plot_.setRenderer(renderer2_);
        plot_.setDataset(dataset);
        JChart.applyCurrentTheme(this);
    }

    /**
     * Creates a Gantt chart using the supplied attributes plus default values
     * where required.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param dateAxisLabel     the label for the date axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     */
    public GanttChart(IntervalCategoryDataset dataset, String categoryAxisLabel, String dateAxisLabel,
            String title, boolean legend, boolean tooltips) {
        this(dataset, categoryAxisLabel, dateAxisLabel, title, legend, tooltips, false);
    }

    /**
     * Creates a Gantt chart using the supplied attributes plus default values
     * where required.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param dateAxisLabel     the label for the date axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     */
    public GanttChart(IntervalCategoryDataset dataset, String categoryAxisLabel, String dateAxisLabel,
            String title) {
        this(dataset, categoryAxisLabel, dateAxisLabel, title, true, true);
    }
}
