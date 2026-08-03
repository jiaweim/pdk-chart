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
 * A Gantt chart that displays tasks or intervals along a time axis.
 * <p>
 * The domain axis is a {@link CategoryAxis} (task names) and the range
 * axis is a {@link DateAxis} (time).  The chart is always rendered with
 * {@link PlotOrientation#HORIZONTAL} so that bars extend from left to
 * right across the time axis.
 * <p>
 * The dataset must implement {@link IntervalCategoryDataset} in order to
 * provide start and end values for each interval.  Tool‑tips and URLs
 * can be enabled via constructor flags; when tool‑tips are active, an
 * {@link IntervalCategoryToolTipGenerator} with the format pattern
 * {@code "{3} - {4}"} is used.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 02 Aug 2026, 1:32 PM
 */
public class GanttChart extends CategoryBarChart {

    private GanttRenderer renderer2_;

    @Override
    protected void initRenderer() {
        renderer2_ = new GanttRenderer();
        renderer1_ = renderer2_;
        renderer0_ = renderer2_;
    }

    @Override
    public GanttRenderer getRenderer() {
        return renderer2_;
    }

    /**
     * Full constructor – every option is exposed.
     *
     * @param dataset    the interval category dataset ({@code null} permitted)
     * @param xAxisLabel the label for the task (category) axis ({@code null} permitted)
     * @param yAxisLabel the label for the time (date) axis ({@code null} permitted)
     * @param title      the chart title ({@code null} permitted)
     * @param legend     {@code true} to include a legend
     * @param tooltips   {@code true} to enable tool‑tips with
     *                   interval formatting
     * @param urls       {@code true} to generate URLs for data items
     */
    public GanttChart(IntervalCategoryDataset dataset, String xAxisLabel, String yAxisLabel,
            String title, boolean legend, boolean tooltips, boolean urls) {
        super(title, legend);
        CategoryAxis xAxis_ = new CategoryAxis(xAxisLabel);
        DateAxis yAxis_ = new DateAxis(yAxisLabel);

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
     * Creates a Gantt chart with the given parameters; URLs are disabled.
     *
     * @param dataset           the interval category dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the task axis ({@code null} permitted)
     * @param dateAxisLabel     the label for the time axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     * @param legend            {@code true} to include a legend
     * @param tooltips          {@code true} to enable tool‑tips
     */
    public GanttChart(IntervalCategoryDataset dataset, String categoryAxisLabel, String dateAxisLabel,
            String title, boolean legend, boolean tooltips) {
        this(dataset, categoryAxisLabel, dateAxisLabel, title, legend, tooltips, false);
    }

    /**
     * Convenience constructor with legend and tool‑tips enabled, no URLs.
     *
     * @param dataset           the interval category dataset ({@code null} permitted)
     * @param categoryAxisLabel the label for the task axis ({@code null} permitted)
     * @param dateAxisLabel     the label for the time axis ({@code null} permitted)
     * @param title             the chart title ({@code null} permitted)
     */
    public GanttChart(IntervalCategoryDataset dataset, String categoryAxisLabel, String dateAxisLabel,
            String title) {
        this(dataset, categoryAxisLabel, dateAxisLabel, title, true, true);
    }
}
