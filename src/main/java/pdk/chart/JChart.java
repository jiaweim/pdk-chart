package pdk.chart;

import org.jspecify.annotations.Nullable;
import pdk.chart.api.Layer;
import pdk.chart.api.RectangleInsets;
import pdk.chart.api.TableOrder;
import pdk.chart.axis.CategoryAxis;
import pdk.chart.axis.DateAxis;
import pdk.chart.axis.NumberAxis;
import pdk.chart.axis.ValueAxis;
import pdk.chart.data.category.CategoryDataset;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.data.general.PieDataset;
import pdk.chart.data.general.WaferMapDataset;
import pdk.chart.data.xy.OHLCDataset;
import pdk.chart.data.xy.TableXYDataset;
import pdk.chart.data.xy.WindDataset;
import pdk.chart.data.xy.XYDataset;
import pdk.chart.labels.*;
import pdk.chart.plot.*;
import pdk.chart.plot.pie.MultiplePiePlot;
import pdk.chart.plot.pie.PiePlot;
import pdk.chart.renderer.DefaultPolarItemRenderer;
import pdk.chart.renderer.WaferMapRenderer;
import pdk.chart.renderer.category.*;
import pdk.chart.renderer.xy.*;
import pdk.chart.text.TextAnchor;
import pdk.chart.urls.*;

import java.awt.*;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * A collection of utility methods for creating some standard charts.
 */
public abstract class JChart {

    /**
     * The chart theme.
     */
    private static ChartTheme currentTheme = new StandardChartTheme("PDK");

    private JChart() {}

    /**
     * Returns the current chart theme used by the factory.
     *
     * @return The chart theme.
     * @see #setChartTheme(ChartTheme)
     * @see JChartUtils#applyCurrentTheme(Chart)
     */
    public static ChartTheme getChartTheme() {
        return currentTheme;
    }

    /**
     * Sets the current chart theme.  This will be applied to all new charts
     * created via methods in this class.
     *
     * @param theme the theme ({@code null} not permitted).
     * @see #getChartTheme()
     * @see JChartUtils#applyCurrentTheme(Chart)
     */
    public static void setChartTheme(ChartTheme theme) {
        Objects.requireNonNull(theme, "theme");
        currentTheme = theme;

        // here we do a check to see if the user is installing the "Legacy"
        // theme, and reset the bar painters in that case...
        if (theme instanceof StandardChartTheme) {
            BarRenderer.setDefaultBarPainter(new StandardBarPainter());
            XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());
        }
    }

    /**
     * Creates a ring chart with default settings.
     * <p>
     * The chart object returned by this method uses a {@link RingPlot}
     * instance as the plot.
     *
     * @param title    the chart title ({@code null} permitted).
     * @param dataset  the dataset for the chart ({@code null} permitted).
     * @param legend   a flag specifying whether a legend is required.
     * @param tooltips configure chart to generate tool tips?
     * @param locale   the locale ({@code null} not permitted).
     * @return A ring chart.
     */
    public static Chart ring(PieDataset dataset, String title, boolean legend, boolean tooltips, Locale locale) {

        RingPlot plot = new RingPlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(locale));
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
        }
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a ring chart with default settings.
     * <p>
     * The chart object returned by this method uses a {@link RingPlot}
     * instance as the plot.
     *
     * @param title    the chart title ({@code null} permitted).
     * @param dataset  the dataset for the chart ({@code null} permitted).
     * @param legend   a flag specifying whether a legend is required.
     * @param tooltips configure chart to generate tool tips?
     * @param urls     configure chart to generate URLs?
     * @return A ring chart.
     */
    public static Chart ring(PieDataset dataset, String title, boolean legend, boolean tooltips, boolean urls) {

        RingPlot plot = new RingPlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator<>());
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator<>());
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator<>());
        }
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a chart that displays multiple pie plots.  The chart object
     * returned by this method uses a {@link MultiplePiePlot} instance as the
     * plot.
     *
     * @param title    the chart title ({@code null} permitted).
     * @param dataset  the dataset ({@code null} permitted).
     * @param order    the order that the data is extracted (by row or by column)
     *                 ({@code null} not permitted).
     * @param legend   include a legend?
     * @param tooltips generate tooltips?
     * @param urls     generate URLs?
     * @return A chart.
     */
    public static Chart pieMultiple(CategoryDataset dataset, String title, TableOrder order,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(order, "order");
        MultiplePiePlot plot = new MultiplePiePlot(dataset);
        plot.setDataExtractOrder(order);
        plot.setBackgroundPaint(null);
        plot.setOutlineStroke(null);

        if (tooltips) {
            PieToolTipGenerator tooltipGenerator = new StandardPieToolTipGenerator();
            PiePlot pp = (PiePlot) plot.getPieChart().getPlot();
            pp.setToolTipGenerator(tooltipGenerator);
        }

        if (urls) {
            PieURLGenerator urlGenerator = new StandardPieURLGenerator();
            PiePlot pp = (PiePlot) plot.getPieChart().getPlot();
            pp.setURLGenerator(urlGenerator);
        }

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Create a category chart.
     *
     * @param dataset           {@link CategoryDataset} instance.
     * @param chartType         {@link CategoryChartType}.
     * @param title             chart title
     * @param categoryAxisTitle category axis title.
     * @param valueAxisTitle    value axis title.
     * @param orientation       {@link PlotOrientation}
     * @param legend            whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @return a chart.
     */
    public static Chart create(CategoryDataset dataset, CategoryChartType chartType, String title,
            String categoryAxisTitle, String valueAxisTitle, PlotOrientation orientation, boolean legend, boolean tooltips) {
        CategoryAxis domainAxis = new CategoryAxis(categoryAxisTitle);
        NumberAxis valueAxis = new NumberAxis(valueAxisTitle);
        CategoryItemRenderer renderer = chartType.getRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator<>());
        }

        CategoryPlot plot = new CategoryPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Create a chart with {@link XYPlot}
     *
     * @param dataset         {@link XYDataset}.
     * @param chartType       {@link XYChartType}
     * @param domainAxisType  {@link AxisType} for domain-axis.
     * @param rangeAxisType   {@link AxisType} for range-axis.
     * @param title           chart title.
     * @param domainAxisTitle domain-axis name.
     * @param rangeAxisTitle  range-axis name.
     * @param orientation     {@link PlotOrientation}
     * @param legend          true if create legend.
     * @param tooltips        true if show tooltips.
     * @return a chart.
     */
    public static Chart createXY(@Nullable XYDataset dataset, XYChartType chartType,
            AxisType domainAxisType, AxisType rangeAxisType, String title, String domainAxisTitle,
            String rangeAxisTitle, PlotOrientation orientation, boolean legend, boolean tooltips) {
        Objects.requireNonNull(orientation, "orientation");

        ValueAxis domainAxis = domainAxisType.createInstance();
        domainAxis.setLabel(domainAxisTitle);

        ValueAxis rangeAxis = rangeAxisType.createInstance();
        rangeAxis.setLabel(rangeAxisTitle);

        XYItemRenderer renderer = chartType.getRenderer();

        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }

        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        plot.setOrientation(orientation);

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a stacked bar chart with default settings.  The chart object
     * returned by this method uses a {@link XYPlot} instance as the
     * plot, with a {@link NumberAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedXYBarRenderer}
     * as the renderer.
     *
     * @param title           the chart title.
     * @param domainAxisLabel the label for the category axis.
     * @param rangeAxisLabel  the label for the value axis.
     * @param dataset         the dataset for the chart.
     * @param orientation     the orientation of the chart (horizontal or
     *                        vertical).
     * @param legend          a flag specifying whether a legend is required.
     * @param tooltips        configure chart to generate tool tips?
     * @param urls            configure chart to generate URLs?
     * @return A stacked bar chart.
     */
    public static Chart barStackedXY(@Nullable XYDataset dataset, @Nullable String domainAxisLabel,
            @Nullable String rangeAxisLabel, @Nullable String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        NumberAxis domainAxis = new NumberAxis(domainAxisLabel);
        ValueAxis valueAxis = new NumberAxis(rangeAxisLabel);

        StackedXYBarRenderer renderer = new StackedXYBarRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }

        XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a stacked area chart with default settings.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedAreaRenderer}
     * as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @return A stacked area chart.
     */
    public static Chart stackedArea(CategoryDataset dataset, String categoryAxisLabel,
            String valueAxisLabel, String title) {
        return stackedArea(dataset, categoryAxisLabel, valueAxisLabel, title,
                PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a stacked area chart with default settings.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedAreaRenderer}
     * as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @return A stacked area chart.
     */
    public static Chart stackedArea(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        return stackedArea(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a stacked area chart with default settings.  The chart object
     * returned by this method uses a {@link CategoryPlot} instance as the
     * plot, with a {@link CategoryAxis} for the domain axis, a
     * {@link NumberAxis} as the range axis, and a {@link StackedAreaRenderer}
     * as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} not permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     * @return A stacked area chart.
     */
    public static Chart stackedArea(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        categoryAxis.setCategoryMargin(0.0);
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        StackedAreaRenderer renderer = new StackedAreaRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a Gantt chart using the supplied attributes plus default values
     * where required.  The chart object returned by this method uses a
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link DateAxis} as the range axis, and a
     * {@link GanttRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param dateAxisLabel     the label for the date axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @return A Gantt chart.
     */
    public static Chart gantt(IntervalCategoryDataset dataset, String categoryAxisLabel, String dateAxisLabel,
            String title) {
        return gantt(dataset, categoryAxisLabel, dateAxisLabel, title, true, true, false);
    }

    /**
     * Creates a Gantt chart using the supplied attributes plus default values
     * where required.  The chart object returned by this method uses a
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link DateAxis} as the range axis, and a
     * {@link GanttRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param dateAxisLabel     the label for the date axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @return A Gantt chart.
     */
    public static Chart gantt(IntervalCategoryDataset dataset, String categoryAxisLabel, String dateAxisLabel,
            String title, boolean legend, boolean tooltips) {
        return gantt(dataset, categoryAxisLabel, dateAxisLabel, title, legend, tooltips, false);
    }

    /**
     * Creates a Gantt chart using the supplied attributes plus default values
     * where required.  The chart object returned by this method uses a
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link DateAxis} as the range axis, and a
     * {@link GanttRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param dateAxisLabel     the label for the date axis
     *                          ({@code null} permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     * @return A Gantt chart.
     */
    public static Chart gantt(IntervalCategoryDataset dataset, String categoryAxisLabel, String dateAxisLabel,
            String title, boolean legend, boolean tooltips, boolean urls) {

        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        DateAxis dateAxis = new DateAxis(dateAxisLabel);

        CategoryItemRenderer renderer = new GanttRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new IntervalCategoryToolTipGenerator("{3} - {4}", DateFormat.getDateInstance()));
        }
        if (urls) {
            renderer.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, dateAxis, renderer);
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a waterfall chart.  The chart object returned by this method
     * uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link WaterfallBarRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} NOT permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @return A waterfall chart.
     */
    public static Chart waterfall(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips) {
        return waterfall(dataset, categoryAxisLabel, valueAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a waterfall chart.  The chart object returned by this method
     * uses a {@link CategoryPlot} instance as the plot, with a
     * {@link CategoryAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link WaterfallBarRenderer} as the renderer.
     *
     * @param title             the chart title ({@code null} permitted).
     * @param categoryAxisLabel the label for the category axis
     *                          ({@code null} permitted).
     * @param valueAxisLabel    the label for the value axis ({@code null}
     *                          permitted).
     * @param dataset           the dataset for the chart ({@code null} permitted).
     * @param orientation       the plot orientation (horizontal or vertical)
     *                          ({@code null} NOT permitted).
     * @param legend            a flag specifying whether a legend is required.
     * @param tooltips          configure chart to generate tool tips?
     * @param urls              configure chart to generate URLs?
     * @return A waterfall chart.
     */
    public static Chart waterfall(CategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel,
            String title, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        categoryAxis.setCategoryMargin(0.0);

        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        WaterfallBarRenderer renderer = new WaterfallBarRenderer();
        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, Math.PI / 2.0);
            renderer.setDefaultPositiveItemLabelPosition(position);
            renderer.setDefaultNegativeItemLabelPosition(position);
        } else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 0.0);
            renderer.setDefaultPositiveItemLabelPosition(position);
            renderer.setDefaultNegativeItemLabelPosition(position);
        }
        if (tooltips) {
            StandardCategoryToolTipGenerator generator = new StandardCategoryToolTipGenerator();
            renderer.setDefaultToolTipGenerator(generator);
        }
        if (urls) {
            renderer.setDefaultItemURLGenerator(new StandardCategoryURLGenerator());
        }

        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        plot.clearRangeMarkers();
        Marker baseline = new ValueMarker(0.0);
        baseline.setPaint(Color.BLACK);
        plot.addRangeMarker(baseline, Layer.FOREGROUND);
        plot.setOrientation(orientation);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a polar plot for the specified dataset (x-values interpreted as
     * angles in degrees).  The chart object returned by this method uses a
     * {@link PolarPlot} instance as the plot, with a {@link NumberAxis} for
     * the radial axis.
     *
     * @param title   the chart title ({@code null} permitted).
     * @param dataset the dataset ({@code null} permitted).
     * @param legend  legend required?
     * @return A chart.
     */
    public static Chart polar(XYDataset dataset, String title, boolean legend) {

        PolarPlot plot = new PolarPlot();
        plot.setDataset(dataset);
        NumberAxis rangeAxis = new NumberAxis();
        rangeAxis.setAxisLineVisible(false);
        rangeAxis.setTickMarksVisible(false);
        rangeAxis.setTickLabelInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
        plot.setAxis(rangeAxis);
        plot.setRenderer(new DefaultPolarItemRenderer());
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a stacked XY area plot.  The chart object returned by this
     * method uses an {@link XYPlot} instance as the plot, with a
     * {@link NumberAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link StackedXYAreaRenderer2} as the renderer.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @return A stacked XY area chart.
     */
    public static Chart stackedAreaXY(TableXYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        return stackedAreaXY(dataset, xAxisLabel, yAxisLabel, title, PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a stacked XY area plot.  The chart object returned by this
     * method uses an {@link XYPlot} instance as the plot, with a
     * {@link NumberAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link StackedXYAreaRenderer2} as the renderer.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @return A stacked XY area chart.
     */
    public static Chart stackedAreaXY(TableXYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        return stackedAreaXY(dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a stacked XY area plot.  The chart object returned by this
     * method uses an {@link XYPlot} instance as the plot, with a
     * {@link NumberAxis} for the domain axis, a {@link NumberAxis} as the
     * range axis, and a {@link StackedXYAreaRenderer2} as the renderer.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @return A stacked XY area chart.
     */
    public static Chart stackedAreaXY(TableXYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        StackedXYAreaRenderer2 renderer = new StackedXYAreaRenderer2(toolTipGenerator, urlGenerator);
        renderer.setOutline(true);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);

        plot.setRangeAxis(yAxis);  // forces recalculation of the axis range

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param dataset the dataset for the chart ({@code null} permitted).
     * @return The chart.
     */
    public static Chart lineSmooth(XYDataset dataset) {
        return lineSmooth(dataset, null, null, null, PlotOrientation.VERTICAL,
                true, true, false);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title       the chart title.
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @return The chart.
     */
    public static Chart lineSmooth(XYDataset dataset, String xAxisLabel, String yAxisLabel, @Nullable String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        return lineSmooth(dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title       the chart title.
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @return The chart.
     */
    public static Chart lineSmooth(XYDataset dataset, String xAxisLabel, String yAxisLabel, @Nullable String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYItemRenderer renderer = new XYSplineRenderer();
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a stepped XY plot with default settings.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @return A chart.
     */
    public static Chart stepXY(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        return stepXY(dataset, xAxisLabel, yAxisLabel, title, PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a stepped XY plot with default settings.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @return A chart.
     */
    public static Chart stepXY(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        return stepXY(dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a stepped XY plot with default settings.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @return A chart.
     */
    public static Chart stepXY(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");
        DateAxis xAxis = new DateAxis(xAxisLabel);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        XYItemRenderer renderer = new XYStepRenderer(toolTipGenerator, urlGenerator);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);
        plot.setDomainCrosshairVisible(false);
        plot.setRangeCrosshairVisible(false);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }


    /**
     * Creates a filled stepped XY plot with default settings.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel a label for the Y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @return A chart.
     */
    public static Chart stepAreaXY(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title) {
        return stepAreaXY(dataset, xAxisLabel, yAxisLabel, title, PlotOrientation.VERTICAL, true, true, false);
    }

    /**
     * Creates a filled stepped XY plot with default settings.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @return A chart.
     */
    public static Chart stepAreaXY(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips) {
        return stepAreaXY(dataset, xAxisLabel, yAxisLabel, title, orientation, legend, tooltips, false);
    }

    /**
     * Creates a filled stepped XY plot with default settings.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param xAxisLabel  a label for the X-axis ({@code null} permitted).
     * @param yAxisLabel  a label for the Y-axis ({@code null} permitted).
     * @param dataset     the dataset for the chart ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted).
     * @param legend      a flag specifying whether a legend is required.
     * @param tooltips    configure chart to generate tool tips?
     * @param urls        configure chart to generate URLs?
     * @return A chart.
     */
    public static Chart stepAreaXY(XYDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        XYItemRenderer renderer = new XYStepAreaRenderer(XYStepAreaRenderer.AREA_AND_SHAPES, toolTipGenerator, urlGenerator);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);
        plot.setDomainCrosshairVisible(false);
        plot.setRangeCrosshairVisible(false);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates and returns a default instance of a candlesticks chart.
     *
     * @param title          the chart title ({@code null} permitted).
     * @param timeAxisLabel  a label for the time axis ({@code null}
     *                       permitted).
     * @param valueAxisLabel a label for the value axis ({@code null}
     *                       permitted).
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @param legend         a flag specifying whether a legend is required.
     * @return A candlestick chart.
     */
    public static Chart candlestick(OHLCDataset dataset, String timeAxisLabel, String valueAxisLabel,
            String title, boolean legend) {

        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
        plot.setRenderer(new CandlestickRenderer());
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates and returns a default instance of a high-low-open-close chart.
     *
     * @param title          the chart title ({@code null} permitted).
     * @param timeAxisLabel  a label for the time axis ({@code null}
     *                       permitted).
     * @param valueAxisLabel a label for the value axis ({@code null}
     *                       permitted).
     * @param dataset        the dataset for the chart ({@code null} permitted).
     * @param legend         a flag specifying whether a legend is required.
     * @return A high-low-open-close chart.
     */
    public static Chart highLow(OHLCDataset dataset, String timeAxisLabel, String valueAxisLabel,
            String title, boolean legend) {
        DateAxis timeAxis = new DateAxis(timeAxisLabel);
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        HighLowRenderer renderer = new HighLowRenderer();
        renderer.setDefaultToolTipGenerator(new HighLowItemLabelGenerator());
        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a wind plot with default settings.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the x-axis ({@code null} permitted).
     * @param yAxisLabel a label for the y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @param legend     a flag that controls whether a legend is created.
     * @param tooltips   configure chart to generate tool tips?
     * @return A wind plot.
     */
    public static Chart wind(WindDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            boolean legend, boolean tooltips) {
        return wind(dataset, xAxisLabel, yAxisLabel, title, legend, tooltips, false);
    }

    /**
     * Creates a wind plot with default settings.
     *
     * @param title      the chart title ({@code null} permitted).
     * @param xAxisLabel a label for the x-axis ({@code null} permitted).
     * @param yAxisLabel a label for the y-axis ({@code null} permitted).
     * @param dataset    the dataset for the chart ({@code null} permitted).
     * @param legend     a flag that controls whether a legend is created.
     * @param tooltips   configure chart to generate tool tips?
     * @param urls       configure chart to generate URLs?
     * @return A wind plot.
     */
    public static Chart wind(WindDataset dataset, String xAxisLabel, String yAxisLabel, String title,
            boolean legend, boolean tooltips, boolean urls) {

        ValueAxis xAxis = new DateAxis(xAxisLabel);
        ValueAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setRange(-12.0, 12.0);

        WindItemRenderer renderer = new WindItemRenderer();
        if (tooltips) {
            renderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }

    /**
     * Creates a wafer map chart.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param dataset     the dataset ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted.
     * @param legend      display a legend?
     * @return A wafer map chart.
     */
    public static Chart waferMap(WaferMapDataset dataset, String title, PlotOrientation orientation,
            boolean legend) {
        return waferMap(dataset, title, orientation, legend, false, false);
    }

    /**
     * Creates a wafer map chart.
     *
     * @param title       the chart title ({@code null} permitted).
     * @param dataset     the dataset ({@code null} permitted).
     * @param orientation the plot orientation (horizontal or vertical)
     *                    ({@code null} NOT permitted.
     * @param legend      display a legend?
     * @param tooltips    generate tooltips?
     * @param urls        generate URLs?
     * @return A wafer map chart.
     */
    public static Chart waferMap(WaferMapDataset dataset, String title, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {
        Objects.requireNonNull(orientation, "orientation");

        WaferMapPlot plot = new WaferMapPlot(dataset);
        WaferMapRenderer renderer = new WaferMapRenderer();
        plot.setRenderer(renderer);

        Chart chart = new Chart(title, Chart.DEFAULT_TITLE_FONT, plot, legend);
        currentTheme.apply(chart);
        return chart;
    }
}
